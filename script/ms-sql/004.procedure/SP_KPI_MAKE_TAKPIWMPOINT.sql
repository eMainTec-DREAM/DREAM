CREATE PROCEDURE [dbo].[SP_KPI_MAKE_TAKPIWMPOINT] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count                                as bigint
    declare  @v_w_worktime                       numeric(18) 
            ,@v_w_stoptime                        numeric(18)
            ,@v_w_stopfreq                         numeric(18)
            ,@v_w_repairtime                        numeric(18)
            ,@v_w_repairfreq                         numeric(18)
    
    -- exec SP_KPI_MAKE_TAKPIWMPOINT('100','ADMIN'); 
    
    
    DECLARE c_date CURSOR FOR
           select 
                 tyyyy            as yyyy
                ,min(tmonth)  as yyyymm
                ,week            as week
                ,min(tday)      as fdate
                ,max(tday)     as tdate
            from taday a
            where a.tmonth >= convert(varchar(6), DATEADD(month,-1,GETDATE()), 112)  -- 한달전 1일 부터 재 계산 처리...
                 and a.tmonth <= CONVERT(varchar(6), getdate(), 112)
            group by tyyyy, week     
            order by tyyyy,week
            ;
            
    -- c_date cursor parameter
    DECLARE   @c_yyyy varchar(4)
             ,@c_yyyymm varchar(6)
             ,@c_week  bigint
             ,@c_fdate varchar(8)
             ,@c_tdate varchar(8)
                        
            
     DECLARE c_plan CURSOR FOR
            select 
                 a.comp_no
                ,a.plant
                ,a.yyyymm
                ,a.mtlnpoint mtpoint
                ,avg(isnull(a.pvalue,0)) pvalue
            from TAMTLNPOINT a
            where 1=1
                and a.comp_no = @v_comp_no
                and a.mtlnpoint in ('BD','BD_EQ')
                and a.yyyymm >= convert(varchar(6), DATEADD(month,-1,GETDATE()), 112)  -- 한달전 1일 부터 재 계산 처리...
                and a.yyyymm <= CONVERT(varchar(6), getdate(), 112)
                group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
           ;
           
     -- c_plan cursor parameter
    DECLARE   @c_comp_no varchar(6)
             ,@c_plant varchar(20)
             ,@c_2yyyymm varchar(6)
             ,@c_mtpoint varchar(20)
             ,@c_pvalue numeric(18)

    
    
    OPEN c_date
    FETCH NEXT FROM c_date INTO @c_yyyy,@c_yyyymm,@c_week,@c_fdate,@c_tdate
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            OPEN c_plan
            FETCH NEXT FROM c_plan INTO @c_comp_no,@c_plant,@c_2yyyymm,@c_mtpoint,@c_pvalue
            WHILE (@@FETCH_STATUS=0)
                BEGIN
                -------------------------------------------------------------------------------------------
                    select @v_count = count(*)
                    from (
                            select a.comp_no, e.plant
                            FROM taworkorder a,
                                tawofail b,
                                tawoequip c,
                                taequipment d,
                                taeqloc e
                           WHERE  a.comp_no =@v_comp_no
                                AND a.comp_no = b.comp_no
                                AND a.wkor_id = b.wkor_id
                                AND a.comp_no = c.comp_no
                                AND a.wkor_id = c.wkor_id
                                AND c.comp_no = d.comp_no
                                AND c.equip_id = d.equip_id
                                and d.comp_no = e.comp_no
                                and d.eqloc_id = e.eqloc_id
                                AND a.wo_status = 'C'       
                                AND a.wkor_date >= @c_fdate
                                AND a.wkor_date <= @c_tdate
                                 and e.plant = @c_plant
                                AND a.wo_type = 'BM'        
                        GROUP BY a.comp_no, e.plant
                   ) a
                   ;
                   
                   if @v_count > 0
                            select
                                 @v_w_worktime = (select isnull(sum(aa.dtime),0) +isnull(sum(aa.etime),0)+ isnull(sum(aa.ntime),0) 
                                  from TALNWRKTIME aa
                                  where a.comp_no = aa.comp_no
                                      and  aa.wrk_date >= @c_fdate
                                      AND aa.wrk_date <= @c_tdate
                                      and e.plant = aa.plant
                                 )
                                ,@v_w_stoptime = isnull(SUM (b.lndn_work_time),0)
                                ,@v_w_stopfreq = isnull(SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END),0)
                                ,@v_w_repairtime = isnull(SUM (b.eqdn_work_time),0)
                                ,@v_w_repairfreq = isnull(SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END),0)
                            FROM taworkorder a,
                                tawofail b,
                                tawoequip c,
                                taequipment d,
                                taeqloc e
                           WHERE  a.comp_no =@v_comp_no
                                AND a.comp_no = b.comp_no
                                AND a.wkor_id = b.wkor_id
                                AND a.comp_no = c.comp_no
                                AND a.wkor_id = c.wkor_id
                                AND c.comp_no = d.comp_no
                                AND c.equip_id = d.equip_id
                                and d.comp_no = e.comp_no
                                and d.eqloc_id = e.eqloc_id
                                AND a.wo_status = 'C'       
                                AND a.wkor_date >= @c_fdate
                                AND a.wkor_date <= @c_tdate
                                and e.plant = @c_plant
                                AND a.wo_type = 'BM'        
                        GROUP BY a.comp_no, e.plant
                         ;
                   else
                       select @v_w_worktime =0
                               ,@v_w_stoptime =0
                               ,@v_w_stopfreq =0
                               ,@v_w_repairtime =0
                               ,@v_w_repairfreq =0
                   
                   select @v_count = count(*)
                   from takpiwmpoint
                   where comp_no = @v_comp_no
                       and plant = @c_plant
                       and yyyymm = @c_2yyyymm
                       and week = @c_week
                       and kpi_mpoint = @c_mtpoint
                   ;
                   
                                             
                   if @v_count > 0
                       update takpiwmpoint set
                            from_to_date = left(@c_fdate,4)+ '.' + substring(@c_fdate,5,2) + '.' + substring(@c_fdate,7,2) +' - ' + substring(@c_tdate,1,4)+ '.' + substring(@c_tdate,5,2) +'.' + substring(@c_tdate,7,2)
                           ,w_plan = isnull(@c_pvalue,0)
                           ,w_actual = 
                                            (case when @c_mtpoint = 'BD' then (round((isnull(@v_w_stoptime,0) / case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end ) * 100, 2))
                                                  when @c_mtpoint = 'BD_EQ' then (round((isnull(@v_w_repairtime,0) / case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end ) * 100, 2))
                                             end )
                           ,w_diff = 
                                            (case when @c_mtpoint = 'BD' then isnull(@c_pvalue,0) - (round((isnull(@v_w_stoptime,0) / (case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end)) * 100, 2))
                                                  when @c_mtpoint = 'BD_EQ' then isnull(@c_pvalue,0) - (round((isnull(@v_w_repairtime,0) / (case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end)) * 100, 2))
                                              end )    
                       where comp_no = @v_comp_no
                           and plant = @c_plant
                           and yyyymm = @c_2yyyymm
                           and week = @c_week
                           and kpi_mpoint = @c_mtpoint
                       ;
                       
                   else
                       insert into takpiwmpoint(comp_no, plant, yyyymm, week, kpi_mpoint, from_to_date, w_plan, w_actual, w_diff)
                       values(@v_comp_no, @c_plant, @c_2yyyymm, @c_week, @c_mtpoint
                                 ,left(@c_fdate,4)+ '.' + substring(@c_fdate,5,2) + '.' + substring(@c_fdate,7,2) +' - ' + substring(@c_tdate,1,4)+ '.' + substring(@c_tdate,5,2) +'.' + substring(@c_tdate,7,2)
                                 ,isnull(@c_pvalue,0)
                                 , (case when @c_mtpoint = 'BD' then (round((isnull(@v_w_stoptime,0) / case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end ) * 100, 2))
                                                  when @c_mtpoint = 'BD_EQ' then (round((isnull(@v_w_repairtime,0) / case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end ) * 100, 2))
                                             end )
                                 , (case when @c_mtpoint = 'BD' then isnull(@c_pvalue,0) - (round((isnull(@v_w_stoptime,0) / (case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end)) * 100, 2))
                                                  when @c_mtpoint = 'BD_EQ' then isnull(@c_pvalue,0) - (round((isnull(@v_w_repairtime,0) / (case when isnull(@v_w_worktime,0)=0 then 1 else @v_w_worktime end)) * 100, 2))
                                              end )    
                       );
                
                
                
                
                -------------------------------------------------------------------------------------------
                    FETCH NEXT FROM c_plan INTO @c_comp_no,@c_plant,@c_2yyyymm,@c_mtpoint,@c_pvalue
                END
            CLOSE c_plan
            DEALLOCATE c_plan
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_date INTO @c_yyyy,@c_yyyymm,@c_week,@c_fdate,@c_tdate
        END
    CLOSE c_date
    DEALLOCATE c_date
    
    
    
   
   
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAKPIWMPOINT'
    ;
    
    