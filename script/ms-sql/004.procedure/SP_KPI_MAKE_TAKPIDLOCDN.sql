CREATE PROCEDURE [dbo].[SP_KPI_MAKE_TAKPIDLOCDN] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    declare @v_d_stoptime                        numeric(18)
            ,@v_d_repairtime                     numeric(18)
            ,@v_d_stopfreq                        numeric(18)
            ,@v_d_repairfreq                     numeric(18)
            ,@v_m_stoptime                        numeric(18)
            ,@v_m_repairtime                      numeric(18)
            ,@v_m_stopfreq                         numeric(18)
            ,@v_m_repairfreq                      numeric(18)
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    DECLARE c_lnwrktime CURSOR FOR
        select 
             a.plant
            ,a.wrk_date
            ,a.eqloc_id
            ,a.dtime  as d_worktime_ds
            ,a.ntime  as d_worktime_es -- TALNWRKTIME에서 ntime이 2Shift 
            ,a.etime  as d_worktime_ns -- TALNWRKTIME에서 etime이 3Shift 
            ,isnull(a.dtime,0) + isnull(a.etime,0)+ isnull(a.ntime,0) as d_worktime
            ,(select isnull(sum(aa.dtime),0) +isnull(sum(aa.etime),0) + isnull(sum(aa.ntime),0) 
              from TALNWRKTIME aa
              where a.comp_no = aa.comp_no
                  and a.plant = aa.plant
                  and a.eqloc_id = aa.eqloc_id
                  and aa.wrk_date >= left(a.wrk_date,6) + '01'
                  and aa.wrk_date <= a.wrk_date
              ) as m_work_time
        from TALNWRKTIME a
        where 1=1
            and a.comp_no = @v_comp_no
            and a.wrk_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= CONVERT(varchar(8), getdate(), 112)
       ;
       
    -- c_lnwrktime cursor parameter
    DECLARE @c_plant   varchar(20)
            ,@c_wrk_date varchar(8)
            ,@c_eqloc_id  bigint
            ,@c_d_worktime_ds numeric(18)
            ,@c_d_worktime_es numeric(18)
            ,@c_d_worktime_ns numeric(18)
            ,@c_d_worktime numeric(18)
            ,@c_m_work_time   numeric(18)
       
     /*고장시간 집계대상일자 및 라인*/
     DECLARE c_eqloc_list CURSOR FOR
         select 
                  plant
                 ,wrk_date
                 ,eqloc_id
            from TALNWRKTIME a
            where 1=1
                and a.comp_no = @v_comp_no
                and a.wrk_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= CONVERT(varchar(8), getdate(), 112)
            group by comp_no, plant, wrk_date, eqloc_id
            order by 1,2,3
           ;
    
    OPEN c_lnwrktime
    FETCH NEXT FROM c_lnwrktime INTO @c_plant,@c_wrk_date,@c_eqloc_id,@c_d_worktime_ds,@c_d_worktime_es,@c_d_worktime_ns,@c_d_worktime,@c_m_work_time
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            --  가동시간 Setting 
            select @v_count = count(*)
            from takpidlocdn
            where comp_no = @v_comp_no
               and plant = @c_plant
               and wrk_date = @c_wrk_date
               and eqloc_id = @c_eqloc_id
            ;
           
           if @v_count > 0
               update takpidlocdn set
                        d_worktime_ds = @c_d_worktime_ds
                        ,d_worktime_es = @c_d_worktime_es
                        ,d_worktime_ns = @c_d_worktime_ns
                        ,d_worktime = @c_d_worktime
                        ,m_worktime = @c_m_work_time
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and wrk_date = @c_wrk_date
                    and eqloc_id = @c_eqloc_id
                ;
           else
               insert into takpidlocdn( comp_no, plant, wrk_date, eqloc_id, d_worktime_ds, d_worktime_es, d_worktime_ns, d_worktime, m_worktime
               ) values (
                   @v_comp_no, @c_plant, @c_wrk_date,  @c_eqloc_id, @c_d_worktime_ds,@c_d_worktime_es, @c_d_worktime_ns, @c_d_worktime,@c_m_work_time
               );

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_lnwrktime INTO @c_plant,@c_wrk_date,@c_eqloc_id,@c_d_worktime_ds,@c_d_worktime_es,@c_d_worktime_ns,@c_d_worktime,@c_m_work_time
        END
    CLOSE c_lnwrktime
    DEALLOCATE c_lnwrktime
    
    
    OPEN c_eqloc_list
    FETCH NEXT FROM c_eqloc_list INTO @c_plant,@c_wrk_date,@c_eqloc_id
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            -- 일일 데이타 추출..
            select 
                     @v_d_stoptime = isnull(sum(d_stoptime),0)
                    ,@v_d_repairtime = isnull(sum(d_repairtime),0)
                    ,@v_d_stopfreq = isnull(sum(d_stopfreq),0)
                    ,@v_d_repairfreq = isnull(sum(d_repairfreq),0)
            from (SELECT
                         a.comp_no
                        ,e.plant
                        ,a.wkor_date as wrk_date
                        ,d.eqloc_id as eqloc_id
                        ,SUM (b.lndn_work_time) as d_stoptime
                        ,SUM (b.eqdn_work_time) as d_repairtime
                        ,SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) AS d_stopfreq
                        ,SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) AS d_repairfreq
                    FROM taworkorder a,
                        tawofail b,
                        tawoequip c,
                        taequipment d,
                        taeqloc e
                   WHERE     1 = 1
                        AND a.comp_no = b.comp_no
                        AND a.wkor_id = b.wkor_id
                        AND a.comp_no = c.comp_no
                        AND a.wkor_id = c.wkor_id
                        AND c.comp_no = d.comp_no
                        AND c.equip_id = d.equip_id
                        and d.comp_no = e.comp_no
                        and d.eqloc_id = e.eqloc_id
                        AND a.wo_status = 'C'                                      -- 작업완료
                        AND a.wkor_date = @c_wrk_date
                        AND a.wo_type = 'BM'                                     --고장작업만..
                GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id
                ) a
            where 1=1
                and comp_no = @v_comp_no
                and plant = @c_plant
                and wrk_date = @c_wrk_date
                and eqloc_id in (select eqloc_id from dbo.sfaeqloc_call(@v_comp_no,  @c_eqloc_id, ''))
             ;
             
             
             
             --//1일 ~ 현재일까지 누적 데이타 추출...
              select @v_m_stoptime = isnull(sum(a.d_stoptime),0)
                      ,@v_m_repairtime = isnull(sum(a.d_repairtime),0)
                      ,@v_m_stopfreq = isnull(sum(a.d_stopfreq),0)
                      ,@v_m_repairfreq = isnull(sum(a.d_repairfreq),0)
              from (SELECT
                         a.comp_no
                        ,e.plant
                        ,a.wkor_date as wrk_date
                        ,d.eqloc_id as eqloc_id
                        ,SUM (b.lndn_work_time) as d_stoptime
                        ,SUM (b.eqdn_work_time) as d_repairtime
                        ,SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) AS d_stopfreq
                        ,SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) AS d_repairfreq
                    FROM taworkorder a,
                        tawofail b,
                        tawoequip c,
                        taequipment d,
                        taeqloc e
                   WHERE     1 = 1
                        AND a.comp_no = b.comp_no
                        AND a.wkor_id = b.wkor_id
                        AND a.comp_no = c.comp_no
                        AND a.wkor_id = c.wkor_id
                        AND c.comp_no = d.comp_no
                        AND c.equip_id = d.equip_id
                        and d.comp_no = e.comp_no
                        and d.eqloc_id = e.eqloc_id
                        AND a.wo_status = 'C'                                      -- 작업완료
                        AND a.wkor_date >= left(@c_wrk_date,6) + '01'
                        AND a.wkor_date <= @c_wrk_date
                        AND a.wo_type = 'BM'                                     --고장작업만..
                GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id
                ) a
              where comp_no = @v_comp_no
                  and plant = @c_plant
                  and a.eqloc_id in (select eqloc_id from dbo.sfaeqloc_call(@v_comp_no,  @c_eqloc_id, ''))
              ;
              
              update takpidlocdn set
                     d_stoptime = @v_d_stoptime
                    ,d_repairtime = @v_d_repairtime
                    ,d_stopfreq = @v_d_stopfreq
                    ,d_repairfreq = @v_d_repairfreq
                    ,m_stoptime = @v_m_stoptime
                    ,m_repairtime = @v_m_repairtime
                    ,m_stopfreq = @v_m_stopfreq
                    ,m_repairfreq = @v_m_repairfreq
                    ,mtbf = d_worktime/(case when @v_m_stopfreq = 0 then 1 else @v_m_stopfreq end)
                    ,mtbf_eq = d_worktime/(case when @v_d_repairfreq = 0 then 1 else @v_d_repairfreq end)
                    ,mttr = @v_m_stoptime/(case when @v_m_stopfreq = 0 then 1 else @v_m_stopfreq end)
                    ,mttr_eq = @v_m_repairtime/(case when @v_d_repairfreq = 0 then 1 else @v_d_repairfreq end)
                    ,mtba = d_worktime/(case when @v_d_repairfreq = 0 then 1 else @v_d_repairfreq end)
                    ,ratio =round( (@v_m_stoptime/(case when d_worktime = 0 then 1 else d_worktime end)) * 100,2) 
              where comp_no = @v_comp_no
                  and plant = @c_plant
                  and wrk_date = @c_wrk_date
                  and eqloc_id = @c_eqloc_id
              ;

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_eqloc_list INTO @c_plant,@c_wrk_date,@c_eqloc_id
        END
    CLOSE c_eqloc_list
    DEALLOCATE c_eqloc_list
    
    
    
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAKPIDLOCDN'
    ;
    
  