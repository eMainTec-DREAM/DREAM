CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIDLOCCTGDN(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_d_stoptime_ds                    number(18);
    v_d_stopfreq_ds                     number(18);
    v_d_stoptime_es                    number(18);
    v_d_stopfreq_es                     number(18);
    v_d_stoptime_ns                    number(18);
    v_d_stopfreq_ns                     number(18);
    v_d_stoptime                         number(18);
    v_d_repairtime_ds                  number(18);
    v_d_repairfreq_ds                  number(18);
    v_d_repairtime_es                  number(18);
    v_d_repairfreq_es                  number(18);
    v_d_repairtime_ns                  number(18);
    v_d_repairfreq_ns                   number(18);
    v_d_repairtime                       number(18);
    v_m_stoptime                       number(18);
    v_m_repairtime                     number(18);
    v_m_stopfreq                        number(18);
    v_m_repairfreq                      number(18);
    
    -- exec SP_KPI_MAKE_TAKPIDLOCCTGDN('100','ADMIN'); 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    cursor c_lnctgwrktime is
        select 
            a.comp_no    
            ,a.plant
            ,a.wrk_date
            ,a.eqloc_id
            ,b.eqctg_id
            ,a.dtime  as d_worktime_ds
            ,a.etime  as d_worktime_es
            ,a.ntime  as d_worktime_ns
            ,nvl(a.dtime,0) + nvl(a.etime,0) + nvl(a.ntime,0) as d_worktime
            ,(select nvl(sum(aa.dtime),0) + nvl(sum(aa.etime),0)  + nvl(sum(aa.ntime),0) 
              from TALNWRKTIME aa
              where a.comp_no = aa.comp_no
                  and a.plant = aa.plant
                  and a.eqloc_id = aa.eqloc_id
                  and aa.wrk_date >= to_char(trunc(to_date(a.wrk_date,'yyyymmdd'),'mm'),'yyyymmdd')
                  and aa.wrk_date <= a.wrk_date
              ) as m_work_time
        from TALNWRKTIME a
               ,taeqctg b
        where 1=1
            and a.comp_no = b.comp_no
            and b.lvl = 1
            and b.is_use = 'Y'
            and a.comp_no = v_comp_no
            and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= to_char(sysdate,'yyyymmdd')
       ;
       
     /*고장시간 집계대상일자 및 라인*/
     cursor  c_eqlocctg_list is
         select 
                 a.comp_no
                 ,a.plant
                 ,a.wrk_date
                 ,a.eqloc_id
                 ,b.eqctg_id
            from TALNWRKTIME a
                  ,taeqctg b
            where 1=1
                 and a.comp_no = b.comp_no
                 and b.lvl = 1
                 and b.is_use = 'Y'
                and a.comp_no = v_comp_no
                and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= to_char(sysdate,'yyyymmdd')
            group by a.comp_no, a.plant, a.wrk_date, a.eqloc_id,b.eqctg_id
            order by 1,2,3,4,5
           ;
        
begin

    
        
   for c1 in c_lnctgwrktime loop
   
       --  가동시간 Setting 
       select count(*)
       into v_count
       from takpidlocctgdn
       where comp_no = c1.comp_no
           and plant = c1.plant
           and wrk_date = c1.wrk_date
           and eqloc_id = c1.eqloc_id
           and eqctg_id = c1.eqctg_id
       ;
       
       if v_count > 0 then
           update takpidlocctgdn set
                     d_worktime = c1.d_worktime
                    ,m_worktime = c1.m_work_time
            where comp_no = c1.comp_no
                and plant = c1.plant
                and wrk_date = c1.wrk_date
                and eqloc_id = c1.eqloc_id
                and eqctg_id = c1.eqctg_id
            ;
       else
           insert into takpidlocctgdn( comp_no, plant, wrk_date, eqloc_id, eqctg_id, d_worktime, m_worktime
           ) values (
               c1.comp_no, c1.plant, c1.wrk_date,  c1.eqloc_id, c1.eqctg_id, c1.d_worktime, c1.m_work_time
           );
       end if;
       
   end loop;
   
   
   for c1 in c_eqlocctg_list loop
   
        -- 일일 데이타 추출..
        select 
                 sum(d_stoptime_ds)
                ,sum(d_stopfreq_ds)
                ,sum(d_stoptime_es)
                ,sum(d_stopfreq_es)
                ,sum(d_stoptime_ns)
                ,sum(d_stopfreq_ns)
                ,sum(d_stoptime)
                ,sum(d_repairtime_ds)
                ,sum(d_repairfreq_ds)
                ,sum(d_repairtime_es)
                ,sum(d_repairfreq_es)
                ,sum(d_repairtime_ns)
                ,sum(d_repairfreq_ns)
                ,sum(d_repairtime)
        into v_d_stoptime_ds, v_d_stopfreq_ds, v_d_stoptime_es, v_d_stopfreq_es, v_d_stoptime_ns, v_d_stopfreq_ns, v_d_stoptime
              ,v_d_repairtime_ds,v_d_repairfreq_ds,v_d_repairtime_es,v_d_repairfreq_es, v_d_repairtime_ns,v_d_repairfreq_ns,v_d_repairtime
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
                    AND a.wkor_date = c1.wrk_date
                    and d.eqctg_id = c1.eqctg_id
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id, f.eqctg_id
            ) a
        where 1=1
            and comp_no = c1.comp_no
            and plant = c1.plant
            and wrk_date = c1.wrk_date
            and eqloc_id in (select eqloc_id
                                    from taeqloc
                                    where 1=1
                                    start with eqloc_id = c1.eqloc_id
                                    connect by prior  eqloc_id = p_eqloc_id
                                   )
         ;

         
         --//1일 ~ 현재일까지 누적 데이타 추출...
          select sum(a.m_stoptime)
                  ,sum(a.m_repairtime)
                  ,sum(a.m_stopfreq)
                  ,sum(a.m_repairfreq)
          into v_m_stoptime, v_m_repairtime, v_m_stopfreq, v_m_repairfreq
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
                    AND a.wkor_date >= to_char(trunc(to_date(c1.wrk_date,'yyyymmdd'),'mm'),'yyyymmdd')
                    AND a.wkor_date <= c1.wrk_date
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id, f.eqctg_id
            ) a
          where comp_no = c1.comp_no
              and plant = c1.plant
              and a.eqloc_id in (select eqloc_id
                                        from taeqloc
                                        start with eqloc_id =c1.eqloc_id
                                        connect by prior  eqloc_id = p_eqloc_id
                                       )
              and a.eqctg_id in (select eqctg_id
                                        from taeqctg
                                        start with eqctg_id =c1.eqctg_id
                                        connect by prior  eqctg_id = p_eqctg_id
                                       )
          ;

          
          update takpidlocctgdn set
                      d_stoptime_ds = v_d_stoptime_ds
                     ,d_stopfreq_ds = v_d_stopfreq_ds
                     ,d_stoptime_es =  v_d_stoptime_es
                     ,d_stopfreq_es = v_d_stopfreq_es
                     ,d_stoptime_ns =  v_d_stoptime_ns
                     ,d_stopfreq_ns = v_d_stopfreq_ns
                     ,d_stoptime = v_d_stoptime
                     ,d_repairtime_ds = v_d_repairtime_ds
                     ,d_repairfreq_ds = v_d_repairfreq_ds
                     ,d_repairtime_ns =  v_d_repairtime_ns
                     ,d_repairfreq_es = v_d_repairfreq_es
                     ,d_repairtime_es =  v_d_repairtime_es
                     ,d_repairfreq_ns = v_d_repairfreq_ns
                     ,d_repairtime = v_d_repairtime
                     ,m_stoptime = v_m_stoptime
                     ,m_repairtime = v_m_repairtime
                     ,m_stopfreq = v_m_stopfreq
                     ,m_repairfreq = v_m_repairfreq
                    
                    ,mtbf = d_worktime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mttr = v_m_stoptime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mtba = d_worktime/decode(m_repairfreq,0,1,m_repairfreq)
                    ,ratio =trunc( (m_stoptime/decode(d_worktime,0,1,d_worktime)) * 100,2) 
            where comp_no = c1.comp_no
                and plant = c1.plant
                and wrk_date = c1.wrk_date
                and eqloc_id = c1.eqloc_id
                and eqctg_id = c1.eqctg_id
            ;

            
   end loop;
   
   
   
   



    
    update TABATPGM set 
         exe_by = (select user_id 
                        from tauser 
                        where comp_no = v_comp_no 
                            and user_no = v_user_no 
                            and rownum = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    where comp_no = v_comp_no
        and batpgm_no = 'TAKPIDLOCCTGDN'
    ;
    
    
    
end;
/
