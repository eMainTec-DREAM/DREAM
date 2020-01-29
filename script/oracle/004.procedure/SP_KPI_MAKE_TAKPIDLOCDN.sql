CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIDLOCDN(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_d_stoptime                        number(18);
    v_d_repairtime                      number(18);
    v_d_stopfreq                         number(18);
    v_d_repairfreq                      number(18);
    v_m_stoptime                        number(18);
    v_m_repairtime                      number(18);
    v_m_stopfreq                         number(18);
    v_m_repairfreq                      number(18);
    
    
    -- exec SP_KPI_MAKE_TAKPIDLOCDN('100','ADMIN'); 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    cursor c_lnwrktime is
        select 
            a.comp_no    
            ,a.plant
            ,a.wrk_date
            ,a.eqloc_id
            ,a.dtime  as d_worktime_ds
            ,a.ntime  as d_worktime_es -- TALNWRKTIME에서 ntime이 2Shift 
            ,a.etime  as d_worktime_ns -- TALNWRKTIME에서 etime이 3Shift 
            ,nvl(a.dtime,0) + nvl(a.etime,0)+ nvl(a.ntime,0) as d_worktime
            ,(select nvl(sum(aa.dtime),0) +nvl(sum(aa.etime),0) + nvl(sum(aa.ntime),0) 
              from TALNWRKTIME aa
              where a.comp_no = aa.comp_no
                  and a.plant = aa.plant
                  and a.eqloc_id = aa.eqloc_id
                  and aa.wrk_date >= to_char(trunc(to_date(a.wrk_date,'yyyymmdd'),'mm'),'yyyymmdd')
                  and aa.wrk_date <= a.wrk_date
              ) as m_work_time
        from TALNWRKTIME a
        where 1=1
            and a.comp_no = v_comp_no
            and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= to_char(sysdate,'yyyymmdd')
       ;
       
     /*고장시간 집계대상일자 및 라인*/
     cursor  c_eqloc_list is
         select 
                 comp_no
                 ,plant
                 ,wrk_date
                 ,eqloc_id
            from TALNWRKTIME a
            where 1=1
                and a.comp_no = v_comp_no
                and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= to_char(sysdate,'yyyymmdd')
            group by comp_no, plant, wrk_date, eqloc_id
            order by 1,2,3,4
           ;
        
begin

    
        
   for c1 in c_lnwrktime loop
   
       --  가동시간 Setting 
       select count(*)
       into v_count
       from takpidlocdn
       where comp_no = c1.comp_no
           and plant = c1.plant
           and wrk_date = c1.wrk_date
           and eqloc_id = c1.eqloc_id
       ;
       
       if v_count > 0 then
           update takpidlocdn set
                    d_worktime_ds = c1.d_worktime_ds
                    ,d_worktime_es = c1.d_worktime_es
                    ,d_worktime_ns = c1.d_worktime_ns
                    ,d_worktime = c1.d_worktime
                    ,m_worktime = c1.m_work_time
            where comp_no = c1.comp_no
                and plant = c1.plant
                and wrk_date = c1.wrk_date
                and eqloc_id = c1.eqloc_id
            ;
       else
           insert into takpidlocdn( comp_no, plant, wrk_date, eqloc_id, d_worktime_ds, d_worktime_es, d_worktime_ns, d_worktime, m_worktime
           ) values (
               c1.comp_no, c1.plant, c1.wrk_date,  c1.eqloc_id, c1.d_worktime_ds,c1.d_worktime_es, c1.d_worktime_ns, c1.d_worktime,c1.m_work_time
           );
       end if;
       
   end loop;
   
   for c1 in c_eqloc_list loop
   
        -- 일일 데이타 추출..
        select 
                 NVL(sum(d_stoptime),0)
                ,NVL(sum(d_repairtime),0)
                ,NVL(sum(d_stopfreq),0)
                ,NVL(sum(d_repairfreq),0)
        into v_d_stoptime, v_d_repairtime, v_d_stopfreq, v_d_repairfreq
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
                    AND a.wkor_date = c1.wrk_date
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id
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
          select NVL(sum(a.d_stoptime),0)
                  ,NVL(sum(a.d_repairtime),0)
                  ,NVL(sum(a.d_stopfreq),0)
                  ,NVL(sum(a.d_repairfreq),0)
          into v_m_stoptime, v_m_repairtime, v_m_stopfreq, v_m_repairfreq
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
                    AND a.wkor_date >= to_char(trunc(to_date(c1.wrk_date,'yyyymmdd'),'mm'),'yyyymmdd')
                    AND a.wkor_date <= c1.wrk_date
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id
            ) a
          where comp_no = c1.comp_no
              and plant = c1.plant
              and a.eqloc_id in (select eqloc_id
                                        from taeqloc
                                        start with eqloc_id =c1.eqloc_id
                                        connect by prior  eqloc_id = p_eqloc_id
                                       )
          ;
          
          update takpidlocdn set
                     d_stoptime = v_d_stoptime
                    ,d_repairtime = v_d_repairtime
                    ,d_stopfreq = v_d_stopfreq
                    ,d_repairfreq = v_d_repairfreq
                    ,m_stoptime = v_m_stoptime
                    ,m_repairtime = v_m_repairtime
                    ,m_stopfreq = v_m_stopfreq
                    ,m_repairfreq = v_m_repairfreq
                    ,mtbf = d_worktime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mtbf_eq = d_worktime/decode(v_d_repairfreq,0,1,v_d_repairfreq)
                    ,mttr = v_m_stoptime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mttr_eq = v_m_repairtime/decode(v_d_repairfreq,0,1,v_d_repairfreq)
                    ,mtba = d_worktime/decode(v_d_repairfreq,0,1,v_d_repairfreq)
                    ,ratio =trunc( (v_m_stoptime/decode(d_worktime,0,1,d_worktime)) * 100,2) 
            where comp_no = c1.comp_no
                and plant = c1.plant
                and wrk_date = c1.wrk_date
                and eqloc_id = c1.eqloc_id
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
        and batpgm_no = 'TAKPIDLOCDN'
    ;
    
    
    
end;
/
