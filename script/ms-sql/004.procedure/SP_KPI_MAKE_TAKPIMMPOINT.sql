CREATE PROCEDURE [dbo].[SP_KPI_MAKE_TAKPIMMPOINT] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count                                as bigint
    declare  @v_bd_actual                           numeric(18,3)
            ,@v_btbf_actual                         numeric(18,3)
            ,@v_mttr_actual                         numeric(18,3)
            ,@v_pm_actual                           numeric(18)
            ,@v_tr_actual                           numeric(18,3)
            
    
    
    --BD,MTBF,MTTR,PM
    DECLARE c_bd_plan CURSOR FOR
        select 
             a.comp_no
            ,a.plant
            ,a.yyyymm
            ,a.mtlnpoint mtpoint
            ,avg(isnull(a.pvalue,0)) pvalue
            ,round((select (avg(isnull(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= a.yyyymm
                           and aa.yyyymm >= left(a.yyyymm,4) + '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      ) / 
                      (datediff(month, cast(left(a.yyyymm,4)+'0101' as datetime),cast(a.yyyymm+'31' as datetime) )+1) 
                  ,2) as acm_plan
             ,(select avg(isnull(aa.pvalue,0))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm = convert(varchar(6), DATEADD(month,1,cast(a.yyyymm+'01' as datetime)), 112)
                           and aa.mtlnpoint = a.mtlnpoint
             ) as next_plan
            ,round((select (avg(isnull(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= left(a.yyyymm,4) + '12'
                           and aa.yyyymm >= left(a.yyyymm,4) + '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      ) / 12
                  ,2) as y_plan
         from TAMTLNPOINT a
         where 1=1
            and a.comp_no = @v_comp_no
            and a.mtlnpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
            and a.yyyymm >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= CONVERT(varchar(8), getdate(), 112)
         group by a.comp_no, a.plant, a.yyyymm, a.mtlnpoint
         ;
         
         
    -- c_lnwrktime cursor parameter
    DECLARE  @c_comp_no   varchar(6) 
            ,@c_plant     varchar(20)
            ,@c_yyyymm  varchar(6)
            ,@c_mtpoint  varchar(40)
            ,@c_pvalue    numeric(18,3)
            ,@c_acm_plan  numeric(18,3)
            ,@c_next_plan numeric(18,3)
            ,@c_y_plan    numeric(18,3)
            
       
       
     DECLARE c_bd_actual CURSOR FOR
                SELECT
                     e.plant
                    ,left(a.wkor_date,6)  as yyyymm
                    ,(select isnull(sum(aa.dtime),0) + isnull(sum(aa.etime),0) + isnull(sum(aa.ntime),0) 
                      from TALNWRKTIME aa
                      where a.comp_no = aa.comp_no
                          and  aa.wrk_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
                          AND aa.wrk_date <= CONVERT(varchar(8), getdate(), 112)
                          and e.plant = aa.plant
                     ) as m_worktime
                    ,isnull(SUM (b.lndn_work_time),0) as m_stoptime
                    ,isnull(SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_stopfreq
                    ,isnull(SUM (b.eqdn_work_time),0) as m_repairtime
                    ,isnull(SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE  a.comp_no = @v_comp_no
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'   -- 한달전 1일 부터 재 계산 처리...
                    AND a.wkor_date <= CONVERT(varchar(8), getdate(), 112)
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, left(a.wkor_date,6)
        ;
        
    -- c_lnwrktime cursor parameter
    DECLARE  @c_m_worktime    numeric(18,3)
            ,@c_m_stoptime    numeric(18,3)
            ,@c_m_stopfreq  numeric(18,3)
            ,@c_m_repairtime numeric(18,3)
            ,@c_m_repairfreq    numeric(18,3)
            
        

    DECLARE c_pm_plan_actual CURSOR FOR     
       select 
             a.plant
            ,a.yyyymm
            ,a.mtpoint
            ,a.pvalue
            ,a.avalue
            ,(select isnull(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= left(a.yyyymm,4) + '01'
              ) as acm_plan
              ,(select isnull(sum(aa.avalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= left(a.yyyymm,4) + '01'
              ) as acm_actual
            ,(select aa.pvalue
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm = left(convert(varchar(8), DATEADD(month,1,cast(a.yyyymm+'01' as datetime)), 112),6)
              ) as next_plan
              ,(select isnull(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= left(a.yyyymm,4) + '12'
                  and aa.yyyymm >= left(a.yyyymm,4) + '01'
              ) as y_plan
        from vwkpimpmrslt a
        where 1=1
            and a.comp_no = @v_comp_no
            and a.yyyymm >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= CONVERT(varchar(8), getdate(), 112)
        ;
    
    
    -- c_pm_plan_actual cursor parameter
    DECLARE  @c_avalue    numeric(18,3)
            ,@c_acm_actual  numeric(18,3)
            
            
            
    OPEN c_bd_plan
    FETCH NEXT FROM c_bd_plan INTO @c_comp_no, @c_plant,@c_yyyymm,@c_mtpoint,@c_pvalue,@c_acm_plan,@c_next_plan,@c_y_plan
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            select @v_count = count(*)
            from takpimmpoint
            where comp_no = @v_comp_no
                and plant = @c_plant
                and yyyymm = @c_yyyymm
                and kpi_mpoint = @c_mtpoint
            ;
           
           if @v_count > 0
               update takpimmpoint set
                         m_plan = @c_pvalue
                         ,acm_plan = @c_acm_plan
                         ,next_plan = @c_next_plan
                         ,y_plan = @c_y_plan
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and yyyymm = @c_yyyymm
                    and kpi_mpoint = @c_mtpoint
                ;
           else
               insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, acm_plan, next_plan,y_plan,m_actual,acm_actual,y_actual
               ) values (
                   @v_comp_no, @c_plant, @c_yyyymm, @c_mtpoint, @c_pvalue, @c_acm_plan, @c_next_plan, @c_y_plan,0,0,0
               );

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_bd_plan INTO @c_comp_no, @c_plant,@c_yyyymm,@c_mtpoint,@c_pvalue,@c_acm_plan,@c_next_plan,@c_y_plan
        END
    CLOSE c_bd_plan
    DEALLOCATE c_bd_plan 
    
    
    
    OPEN c_bd_actual
    FETCH NEXT FROM c_bd_actual INTO @c_plant,@c_yyyymm,@c_m_worktime,@c_m_stoptime,@c_m_stopfreq,@c_m_repairtime,@c_m_repairfreq
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
             update takpimmpoint set
                  m_actual = round((isnull(@c_m_stoptime,0)/(case when isnull(@c_m_worktime,0)=0 then 1 else @c_m_worktime end)) * 100, 2)
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'BD'
                    ;
                    
             update takpimmpoint set
                  m_actual = isnull(@c_m_stopfreq,0)
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'BDN'
                    ;
                    
             update takpimmpoint set
                  m_actual = round( (isnull(@c_m_repairtime,0)/(case when isnull(@c_m_worktime,0)=0 then 1 else @c_m_worktime end)) * 100,2)
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'BD_EQ'
                    ;
             update takpimmpoint set
                  m_actual = isnull(@c_m_repairfreq,0)
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'BDN_EQ'
                    ;
              update takpimmpoint set
                  m_actual = round((isnull(@c_m_worktime,0)/(case when isnull(@c_m_stopfreq,0)=0 then 1 else @c_m_stopfreq end)),0) 
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'MTBF'
                    ;
              update takpimmpoint set
                  m_actual = round((isnull(@c_m_worktime,0)/(case when isnull(@c_m_repairfreq,0)=0 then 1 else @c_m_repairfreq end)),0) 
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'MTBF_EQ'
                    ;
               update takpimmpoint set
                  m_actual = round((isnull(@c_m_stoptime,0)/(case when isnull(@c_m_stopfreq,0)=0 then 1 else @c_m_stopfreq end)),0) 
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'MTTR'
                    ;
               update takpimmpoint set
                  m_actual = round((isnull(@c_m_repairtime,0)/(case when isnull(@c_m_repairfreq,0)=0 then 1 else @c_m_repairfreq end)),0) 
             where comp_no = @v_comp_no
                        and plant = @c_plant
                        and yyyymm = @c_yyyymm
                        and kpi_mpoint = 'MTTR_EQ'
                    ;

               --누적실적 넣기
               update  a set a.acm_actual = ( select   isnull(sum(aa.m_actual),0) 
                                                                                        / (datediff(month, cast(left(a.yyyymm,4)+'0101' as datetime),cast(a.yyyymm+'31' as datetime) )+1) 
                                                                             from takpimmpoint aa
                                                                             where 1=1
                                                                                 and aa.comp_no = a.comp_no
                                                                                 and aa.plant = a.plant
                                                                                 and aa.yyyymm <=  a.yyyymm
                                                                                 and aa.yyyymm >= left(a.yyyymm,4) + '01'
                                                                                 and aa.kpi_mpoint = a.kpi_mpoint 
                                                                          )
              from takpimmpoint a
              where  a.comp_no = @v_comp_no
                  and a.plant = @c_plant
                  and a.yyyymm = @c_yyyymm
                  and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
              ;   

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_bd_actual INTO @c_plant,@c_yyyymm,@c_m_worktime,@c_m_stoptime,@c_m_stopfreq,@c_m_repairtime,@c_m_repairfreq
        END
    CLOSE c_bd_actual
    DEALLOCATE c_bd_actual   
    
    
    
             
              
              
    
    OPEN c_pm_plan_actual
    FETCH NEXT FROM c_pm_plan_actual INTO @c_plant,@c_yyyymm,@c_mtpoint,@c_pvalue,@c_avalue,@c_acm_plan,@c_acm_actual,@c_next_plan,@c_y_plan
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            select @v_count = count(*)
            from takpimmpoint
            where comp_no = @v_comp_no
                and plant = @c_plant
                and yyyymm = @c_yyyymm
                and kpi_mpoint = @c_mtpoint
            ;
           
            if @v_count > 0
               update a  set
                          m_plan = isnull(@c_pvalue,0)
                         ,m_actual = isnull(@c_avalue,0)
                         ,acm_plan = isnull(@c_acm_plan,0)
                         ,acm_actual = isnull(@c_acm_actual,0)
                         ,next_plan = isnull(@c_next_plan,0)
                         ,y_plan = isnull(@c_y_plan,0)
                         ,y_actual = isnull(@c_acm_actual,0)
                from takpimmpoint a
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and yyyymm = @c_yyyymm
                    and kpi_mpoint = @c_mtpoint
                ;
            else
               insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, m_actual, acm_plan, acm_actual, next_plan, y_plan, y_actual
               ) values (
                   @v_comp_no, @c_plant, @c_yyyymm, @c_mtpoint, isnull(@c_pvalue,0), isnull(@c_avalue,0), isnull(@c_acm_plan,0), isnull(@c_acm_actual,0), isnull(@c_next_plan,0),isnull(@c_y_plan,0),  isnull(@c_acm_actual,0)
               );
       
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_pm_plan_actual INTO @c_plant,@c_yyyymm,@c_mtpoint,@c_pvalue,@c_avalue,@c_acm_plan,@c_acm_actual,@c_next_plan,@c_y_plan
        END
    CLOSE c_pm_plan_actual
    DEALLOCATE c_pm_plan_actual         
  
    update a set
       a.m_diff = isnull(a.m_plan,0) - isnull(a.m_actual,0)
       ,a.m_ach = round( isnull(a.m_actual,0)/(case when isnull(a.m_plan,0)=0 then 1 else isnull(a.m_plan,0)end)* 100,2)
       ,a.acm_diff = isnull(a.acm_plan,0) - isnull(a.acm_actual,0)
       ,a.acm_ach = round(isnull(a.acm_actual,0)/(case when isnull(a.acm_plan,0)=0 then 1 else isnull(a.acm_plan,0)end) * 100 , 2)
   from takpimmpoint a
   where comp_no = @v_comp_no
       and a.yyyymm >= left(convert(varchar(6), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
        and a.yyyymm <= CONVERT(varchar(6), getdate(), 112)
        --and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')   
    ;
   
   
   
   
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAKPIMLOCCTGDN'
    ;