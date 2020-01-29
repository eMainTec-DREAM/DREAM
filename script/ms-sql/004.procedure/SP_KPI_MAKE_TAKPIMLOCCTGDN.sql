CREATE PROCEDURE [dbo].[SP_KPI_MAKE_TAKPIMLOCCTGDN] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    declare @v_m_stoptime                        numeric(18)
            ,@v_m_repairtime                     numeric(18)
            ,@v_m_stopfreq                        numeric(18)
            ,@v_m_repairfreq                     numeric(18)


    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    DECLARE c_lnctgwrktime CURSOR FOR
        select 
             a.plant
            ,left(a.wrk_date,6) as yyyymm
            ,a.eqloc_id
            ,b.eqctg_id
            ,sum(a.dtime) + sum(a.etime) + sum(a.ntime) as m_worktime
        from TALNWRKTIME a
               ,taeqctg b
        where 1=1
            and a.comp_no = b.comp_no
            and b.lvl = 1
            and b.is_use = 'Y'
            and a.comp_no = @v_comp_no
            and a.wrk_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= CONVERT(varchar(8), getdate(), 112)
        group by a.comp_no, a.plant, left(a.wrk_date,6), a.eqloc_id, b.eqctg_id
       ;
       
     -- c_lnwrktime cursor parameter
     DECLARE @c_plant   varchar(20)
            ,@c_yyyymm varchar(6)
            ,@c_eqloc_id  bigint
            ,@c_eqctg_id  bigint
            ,@c_m_worktime numeric(18)
       
       
       
     /*고장시간 집계대상일자 및 라인*/
     DECLARE c_eqlocctg_list CURSOR FOR
         select 
                  a.plant
                 ,left(a.wrk_date,6) as yyyymm
                 ,a.eqloc_id
                 ,b.eqctg_id
            from TALNWRKTIME a
                  ,taeqctg b
            where 1=1
                 and a.comp_no = b.comp_no
                 and b.lvl = 1
                 and b.is_use = 'Y'
                and a.comp_no = @v_comp_no
                and a.wrk_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= CONVERT(varchar(8), getdate(), 112)
            group by a.comp_no, a.plant, left(a.wrk_date,6) , a.eqloc_id,b.eqctg_id
            order by 1,2,3,4
           ;
    
    OPEN c_lnctgwrktime
    FETCH NEXT FROM c_lnctgwrktime INTO @c_plant,@c_yyyymm,@c_eqloc_id,@c_eqctg_id,@c_m_worktime
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            --  가동시간 Setting 
            select @v_count = count(*)
            from takpimlocctgdn
            where comp_no = @v_comp_no
               and plant = @c_plant
               and yyyymm = @c_yyyymm
               and eqloc_id = @c_eqloc_id
               and eqctg_id = @c_eqctg_id
            ;
           
            if @v_count > 0
               update takpimlocctgdn set
                         m_worktime = @c_m_worktime
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and yyyymm = @c_yyyymm
                    and eqloc_id = @c_eqloc_id
                    and eqctg_id = @c_eqctg_id
                ;
            else
               insert into takpimlocctgdn( comp_no, plant, yyyymm, eqloc_id, eqctg_id, m_worktime
               ) values (
                   @v_comp_no, @c_plant, @c_yyyymm,  @c_eqloc_id, @c_eqctg_id, @c_m_worktime
               );
            
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_lnctgwrktime INTO @c_plant,@c_yyyymm,@c_eqloc_id,@c_eqctg_id,@c_m_worktime
        END
    CLOSE c_lnctgwrktime
    DEALLOCATE c_lnctgwrktime           
    
    
    
    OPEN c_eqlocctg_list
    FETCH NEXT FROM c_eqlocctg_list INTO @c_plant,@c_yyyymm,@c_eqloc_id,@c_eqctg_id
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            
            --//월  데이타 추출...
              select @v_m_stoptime = sum(a.m_stoptime)
                      ,@v_m_repairtime = sum(a.m_repairtime)
                      ,@v_m_stopfreq = sum(a.m_stopfreq)
                      ,@v_m_repairfreq = sum(a.m_repairfreq)
              from (SELECT
                         a.comp_no
                        ,e.plant
                        ,left(a.wkor_date,6)  as yyyymm
                        ,d.eqloc_id as eqloc_id
                        ,f.eqctg_id
                        ,SUM (b.lndn_work_time) as m_stoptime
                        ,SUM (b.eqdn_work_time) as m_repairtime
                        ,SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) AS m_stopfreq
                        ,SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) AS m_repairfreq
                    FROM taworkorder a,
                        tawofail b,
                        tawoequip c,
                        taequipment d,
                        taeqloc e,
                        taeqctg f
                   WHERE     1 = 1
                        AND a.comp_no = b.comp_no
                        AND a.wkor_id = b.wkor_id
                        AND a.comp_no = c.comp_no
                        AND a.wkor_id = c.wkor_id
                        AND c.comp_no = d.comp_no
                        AND c.equip_id = d.equip_id
                        and d.comp_no = e.comp_no
                        and d.eqloc_id = e.eqloc_id
                        and d.comp_no = f.comp_no
                        and d.eqctg_id = f.eqctg_id
                        AND a.wo_status = 'C'                                      -- 작업완료
                        AND a.wkor_date >= @c_yyyymm + '01'
                        AND a.wkor_date <= @c_yyyymm + '31'
                        AND a.wo_type = 'BM'                                     --고장작업만..
                GROUP BY a.comp_no, e.plant, left(a.wkor_date,6), d.eqloc_id, f.eqctg_id
                ) a
              where comp_no = @v_comp_no
                  and plant = @c_plant
                  and a.eqloc_id in (select eqloc_id from dbo.sfaeqloc_call(@v_comp_no, @c_eqloc_id, ''))
                  and a.eqctg_id in (select eqctg_id from dbo.sfaeqctg_call(@v_comp_no, @c_eqctg_id, ''))
              ;

              
              update takpimlocctgdn set
                         m_stoptime = @v_m_stoptime
                         ,m_repairtime = @v_m_repairtime
                         ,m_stopfreq = @v_m_stopfreq
                         ,m_repairfreq = @v_m_repairfreq
                        ,mtbf = m_worktime/(case when @v_m_stopfreq = 0 then 1 else @v_m_stopfreq end)
                        ,mtbf_eq = m_worktime/(case when @v_m_repairfreq = 0 then 1 else @v_m_repairfreq end)
                        ,mttr = @v_m_stoptime/(case when @v_m_stopfreq = 0 then 1 else @v_m_stopfreq end)
                        ,mttr_eq = @v_m_repairtime/(case when @v_m_stopfreq=0 then 1 else @v_m_stopfreq end)
                        ,mtba = m_worktime/(case when @v_m_repairfreq=0 then 1 else @v_m_repairfreq end)
                        ,ratio =round( (m_stoptime/(case when m_worktime=0 then 1 else m_worktime end)) * 100,2) 
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and yyyymm = @c_yyyymm
                    and eqloc_id = @c_eqloc_id
                    and eqctg_id = @c_eqctg_id
                ;
            
            
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_eqlocctg_list INTO @c_plant,@c_yyyymm,@c_eqloc_id,@c_eqctg_id
        END
    CLOSE c_eqlocctg_list
    DEALLOCATE c_eqlocctg_list 
    
        
   

    
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
    
