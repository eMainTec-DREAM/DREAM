CREATE procedure [dbo].[SP_KPI_MAKE_TAKPIDLOCCTGDN](
      @v_comp_no     varchar(6)
     ,@v_user_no     varchar(40)
) as
    SET NOCOUNT ON;
    
    declare @v_count                            numeric(4)
           ,@v_d_stoptime_ds                    numeric(18)
           ,@v_d_stopfreq_ds                    numeric(18)
           ,@v_d_stoptime_es                    numeric(18)
           ,@v_d_stopfreq_es                    numeric(18)
           ,@v_d_stoptime_ns                    numeric(18)
           ,@v_d_stopfreq_ns                    numeric(18)
           ,@v_d_stoptime                       numeric(18)
           ,@v_d_repairtime_ds                  numeric(18)
           ,@v_d_repairfreq_ds                  numeric(18)
           ,@v_d_repairtime_es                  numeric(18)
           ,@v_d_repairfreq_es                  numeric(18)
           ,@v_d_repairtime_ns                  numeric(18)
           ,@v_d_repairfreq_ns                  numeric(18)
           ,@v_d_repairtime                     numeric(18)
           ,@v_m_stoptime                       numeric(18)
           ,@v_m_repairtime                     numeric(18)
           ,@v_m_stopfreq                       numeric(18)
           ,@v_m_repairfreq                     numeric(18)
    
    -- exec dbo.SP_KPI_MAKE_TAKPIDLOCCTGDN '100','ADMIN'; 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    DECLARE c_lnctgwrktime CURSOR FOR
        select 
             a.plant
            ,a.wrk_date
            ,a.eqloc_id
            ,b.eqctg_id
            ,a.dtime  as d_worktime_ds
            ,a.etime  as d_worktime_es
            ,a.ntime  as d_worktime_ns
            ,isnull(a.dtime,0) + isnull(a.etime,0) + isnull(a.ntime,0) as d_worktime
            ,(select isnull(sum(aa.dtime),0) + isnull(sum(aa.etime),0)  + isnull(sum(aa.ntime),0) 
              from TALNWRKTIME aa
              where a.comp_no = aa.comp_no
                  and a.plant = aa.plant
                  and a.eqloc_id = aa.eqloc_id
                  and aa.wrk_date >= left(a.wrk_date,6) + '01'
                  and aa.wrk_date <= a.wrk_date
              ) as m_work_time
        from TALNWRKTIME a
               ,taeqctg b
        where 1=1
            and a.comp_no = b.comp_no
            and b.lvl = 1
            and b.is_use = 'Y'
            and a.comp_no = @v_comp_no
            and a.wrk_date >= left(convert(varchar(8), DATEADD(MONTH, -1, GETDATE()), 112),6) + '01'  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= convert(varchar(8), getdate(), 112) 
       ;
       
       
       
     declare @c_plant            varchar(40)
           ,@c_wrk_date        varchar(8)
           ,@c_eqloc_id        bigint
           ,@c_eqctg_id        bigint
           ,@c_d_worktime_ds   numeric(18)
           ,@c_d_worktime_es   numeric(18)
           ,@c_d_worktime_ns   numeric(18)
           ,@c_d_worktime      numeric(18)
           ,@c_m_work_time     numeric(18)
       

    
     OPEN c_lnctgwrktime
     FETCH NEXT FROM c_lnctgwrktime INTO @c_plant,@c_wrk_date,@c_eqloc_id,@c_eqctg_id,@c_d_worktime_ds,@c_d_worktime_es,@c_d_worktime_ns,@c_d_worktime,@c_m_work_time
     WHILE (@@FETCH_STATUS=0)
        BEGIN
             
              --실행구문처리.
              ------------------------------------------------------------------------
              ------------------------------------------------------------------------
              --  가동시간 Setting 
               select @v_count = count(*)
               from takpidlocctgdn
               where comp_no = @v_comp_no
                   and plant = @c_plant
                   and wrk_date = @c_wrk_date
                   and eqloc_id = @c_eqloc_id
                   and eqctg_id = @c_eqctg_id
               ;
               
               if @v_count > 0
                   update takpidlocctgdn set
                             d_worktime = @c_d_worktime
                            ,m_worktime = @c_m_work_time
                    where comp_no = @v_comp_no
                       and plant = @c_plant
                       and wrk_date = @c_wrk_date
                       and eqloc_id = @c_eqloc_id
                       and eqctg_id = @c_eqctg_id
                    ;
               else
                   insert into takpidlocctgdn( comp_no, plant, wrk_date, eqloc_id, eqctg_id, d_worktime, m_worktime
                   ) values (
                       @v_comp_no, @c_plant, @c_wrk_date,  @c_eqloc_id, @c_eqctg_id, @c_d_worktime, @c_m_work_time
                   );
          
              ------------------------------------------------------------------------
              ------------------------------------------------------------------------
              FETCH NEXT FROM c_lnctgwrktime INTO @c_plant,@c_wrk_date,@c_eqloc_id,@c_eqctg_id,@c_d_worktime_ds,@c_d_worktime_es,@c_d_worktime_ns,@c_d_worktime,@c_m_work_time
            END
     CLOSE c_lnctgwrktime
     DEALLOCATE c_lnctgwrktime
   
       
          
       
     /*고장시간 집계대상일자 및 라인*/
     DECLARE c_eqlocctg_list CURSOR FOR
         select 
                  a.plant
                 ,a.wrk_date
                 ,a.eqloc_id
                 ,b.eqctg_id
            from TALNWRKTIME a
                  ,taeqctg b
            where 1=1
                 and a.comp_no = b.comp_no
                 and b.lvl = 1
                 and b.is_use = 'Y'
                and a.comp_no = @v_comp_no
                and a.wrk_date >= left(convert(varchar(8), DATEADD(MONTH, -1, GETDATE()), 112),6) + '01'   -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= convert(varchar(8), getdate(), 112) 
            group by a.comp_no, a.plant, a.wrk_date, a.eqloc_id,b.eqctg_id
            order by a.plant, a.wrk_date, a.eqloc_id, b.eqctg_id
           ;

   
     OPEN c_eqlocctg_list
     FETCH NEXT FROM c_eqlocctg_list INTO @c_plant
                                       ,@c_wrk_date
                                       ,@c_eqloc_id
                                       ,@c_eqctg_id
     WHILE (@@FETCH_STATUS=0)
        BEGIN
             
              --실행구문처리.
              ------------------------------------------------------------------------
              ------------------------------------------------------------------------
                select 
                         @v_d_stoptime_ds = sum(d_stoptime_ds)
                        ,@v_d_stopfreq_ds = sum(d_stopfreq_ds)
                        ,@v_d_stoptime_es = sum(d_stoptime_es)
                        ,@v_d_stopfreq_es = sum(d_stopfreq_es)
                        ,@v_d_stoptime_ns = sum(d_stoptime_ns)
                        ,@v_d_stopfreq_ns = sum(d_stopfreq_ns)
                        ,@v_d_stoptime = sum(d_stoptime)
                        ,@v_d_repairtime_ds = sum(d_repairtime_ds)
                        ,@v_d_repairfreq_ds = sum(d_repairfreq_ds)
                        ,@v_d_repairtime_es = sum(d_repairtime_es)
                        ,@v_d_repairfreq_es = sum(d_repairfreq_es)
                        ,@v_d_repairtime_ns = sum(d_repairtime_ns)
                        ,@v_d_repairfreq_ns = sum(d_repairfreq_ns)
                        ,@v_d_repairtime = sum(d_repairtime)
                from (SELECT
                             a.comp_no
                            ,e.plant
                            ,a.wkor_date as wrk_date
                            ,d.eqloc_id as eqloc_id
                            ,f.eqctg_id as eqctg_id
                            ,sum(case when a.shift_type = 'DAY' then b.lndn_work_time end) as d_stoptime_ds
                            ,sum(case when a.shift_type = 'DAY' then (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) end) as d_stopfreq_ds
                            ,sum(case when a.shift_type = 'EVEN' then b.lndn_work_time end) as d_stoptime_es
                            ,sum(case when a.shift_type = 'EVEN' then (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) end) as d_stopfreq_es
                            ,sum(case when a.shift_type = 'NIGHT' then b.lndn_work_time end) as d_stoptime_ns
                            ,sum(case when a.shift_type = 'NIGHT' then (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) end) as d_stopfreq_ns
                            ,SUM (b.lndn_work_time) as d_stoptime
                            ,sum(case when a.shift_type = 'DAY' then b.eqdn_work_time end) as d_repairtime_ds
                            ,sum(case when a.shift_type = 'DAY' then (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) end) as d_repairfreq_ds
                            ,sum(case when a.shift_type = 'EVEN' then b.eqdn_work_time end) as d_repairtime_es
                            ,sum(case when a.shift_type = 'EVEN' then (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) end) as d_repairfreq_es
                            ,sum(case when a.shift_type = 'NIGHT' then b.eqdn_work_time end) as d_repairtime_ns
                            ,sum(case when a.shift_type = 'NIGHT' then (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) end) as d_repairfreq_ns
                            ,SUM (b.eqdn_work_time) as d_repairtime
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
                            AND a.wkor_date = @c_wrk_date
                            and d.eqctg_id = @c_eqctg_id
                            AND a.wo_type = 'BM'                                     --고장작업만..
                    GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id, f.eqctg_id
                    ) a
                where 1=1
                    and comp_no = @v_comp_no
                    and plant = @c_plant
                    and wrk_date = @c_wrk_date
                    and eqloc_id in (select eqloc_id from dbo.sfaeqloc_call(@v_comp_no, @c_eqloc_id,'') )
                 ;

                 
                 --//1일 ~ 현재일까지 누적 데이타 추출...
                  select   @v_m_stoptime   = sum(a.m_stoptime)
                          ,@v_m_repairtime = sum(a.m_repairtime)
                          ,@v_m_stopfreq   = sum(a.m_stopfreq)
                          ,@v_m_repairfreq = sum(a.m_repairfreq)
                  from (SELECT
                             a.comp_no
                            ,e.plant
                            ,a.wkor_date as wrk_date
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
                            AND a.wkor_date >= left(@c_wrk_date,6) + '01'
                            AND a.wkor_date <= @c_wrk_date
                            AND a.wo_type = 'BM'                                     --고장작업만..
                    GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id, f.eqctg_id
                    ) a
                  where comp_no = @v_comp_no
                      and plant = @c_plant
                      and a.eqloc_id in (select eqloc_id from dbo.sfaeqloc_call(@v_comp_no, @c_eqloc_id,'') )
                      and a.eqctg_id in (select eqctg_id from dbo.sfaeqctg_call(@v_comp_no, @c_eqctg_id,'') )
                  ;

                  
                  update takpidlocctgdn set
                              d_stoptime_ds = @v_d_stoptime_ds
                             ,d_stopfreq_ds = @v_d_stopfreq_ds
                             ,d_stoptime_es =  @v_d_stoptime_es
                             ,d_stopfreq_es = @v_d_stopfreq_es
                             ,d_stoptime_ns =  @v_d_stoptime_ns
                             ,d_stopfreq_ns = @v_d_stopfreq_ns
                             ,d_stoptime = @v_d_stoptime
                             ,d_repairtime_ds = @v_d_repairtime_ds
                             ,d_repairfreq_ds = @v_d_repairfreq_ds
                             ,d_repairtime_ns =  @v_d_repairtime_ns
                             ,d_repairfreq_es = @v_d_repairfreq_es
                             ,d_repairtime_es =  @v_d_repairtime_es
                             ,d_repairfreq_ns = @v_d_repairfreq_ns
                             ,d_repairtime = @v_d_repairtime
                             ,m_stoptime = @v_m_stoptime
                             ,m_repairtime = @v_m_repairtime
                             ,m_stopfreq = @v_m_stopfreq
                             ,m_repairfreq = @v_m_repairfreq
                            ,mtbf = d_worktime/case when @v_m_stopfreq = 0 then 1 else @v_m_stopfreq end
                            ,mttr = @v_m_stoptime/case when @v_m_stopfreq = 0 then 1 else @v_m_stopfreq end
                            ,mtba = d_worktime/case when m_repairfreq = 0 then 1 else m_repairfreq end 
                            --,ratio =trunc( (m_stoptime/case when d_worktime = 0 then 1 else d_worktime end  ) * 100,2) 
                    where comp_no = @v_comp_no
                        and plant = @c_plant
                        and wrk_date = @c_wrk_date
                        and eqloc_id = @c_eqloc_id
                        and eqctg_id = @c_eqctg_id
                    ;
          
              ------------------------------------------------------------------------
              ------------------------------------------------------------------------
              FETCH NEXT FROM c_eqlocctg_list INTO  @c_plant
                                                   ,@c_wrk_date
                                                   ,@c_eqloc_id
                                                   ,@c_eqctg_id
            END
     CLOSE c_eqlocctg_list
     DEALLOCATE c_eqlocctg_list

    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(6), getdate(), 112)
        ,last_exe_time = getdate()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAKPIDLOCCTGDN'
    ;
    
 