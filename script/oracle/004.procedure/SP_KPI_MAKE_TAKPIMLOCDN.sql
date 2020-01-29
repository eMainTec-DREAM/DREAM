CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMLOCDN(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_ma_stoptime                        number(18);
    v_ma_repairtime                      number(18);
    v_ma_stopfreq                         number(18);
    v_ma_repairfreq                      number(18);
    
    
    -- exec SP_KPI_MAKE_TAKPIMLOCDN('100','ADMIN'); 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    cursor c_lnwrktime is
        select 
            a.comp_no    
            ,a.plant
            ,substr(a.wrk_date,1,6) as yyyymm
            ,a.eqloc_id
            ,sum(a.dtime) + sum(a.etime)+ sum(a.ntime) as ma_worktime
        from TALNWRKTIME a
        where 1=1
            and a.comp_no = v_comp_no
            and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= to_char(sysdate,'yyyymmdd')
        group by a.comp_no, a.plant, substr(a.wrk_date,1,6), a.eqloc_id
       ;
       
     /*고장시간 집계대상일자 및 라인*/
     cursor  c_eqloc_list is
         select 
                 comp_no
                 ,plant
                 ,substr(wrk_date,1,6) as yyyymm
                 ,eqloc_id
            from TALNWRKTIME a
            where 1=1
                and a.comp_no = v_comp_no
                and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= to_char(sysdate,'yyyymmdd')
            group by comp_no, plant, substr(wrk_date,1,6), eqloc_id
            order by 1,2,3,4
           ;
        
begin

    
        
   for c1 in c_lnwrktime loop
   
       --  가동시간 Setting 
       select count(*)
       into v_count
       from takpimlocdn
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and eqloc_id = c1.eqloc_id
       ;
       
       if v_count > 0 then
           update takpimlocdn set
                    ma_worktime = c1.ma_worktime
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and eqloc_id = c1.eqloc_id
            ;
       else
           insert into takpimlocdn( comp_no, plant, yyyymm, eqloc_id, ma_worktime
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm,  c1.eqloc_id, c1.ma_worktime
           );
       end if;
       
   end loop;
   
   for c1 in c_eqloc_list loop
   
         --//1일 ~ 현재일까지 누적 데이타 추출...
          select sum(a.d_stoptime)
                  ,sum(a.d_repairtime)
                  ,sum(a.d_stopfreq)
                  ,sum(a.d_repairfreq)
          into v_ma_stoptime, v_ma_repairtime, v_ma_stopfreq, v_ma_repairfreq
          from (SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
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
                    AND a.wkor_date >= to_char(trunc(to_date(c1.yyyymm,'yyyymm'),'mm'),'yyyymmdd')
                    AND a.wkor_date <= to_char(last_day(to_date(c1.yyyymm,'yyyymm')),'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6), d.eqloc_id
            ) a
          where comp_no = c1.comp_no
              and plant = c1.plant
              and a.eqloc_id in (select eqloc_id
                                        from taeqloc
                                        start with eqloc_id =c1.eqloc_id
                                        connect by prior  eqloc_id = p_eqloc_id
                                       )
          ;
          
          update takpimlocdn set
                     ma_stoptime = v_ma_stoptime
                    ,ma_bd_rate = trunc( (v_ma_stoptime/decode(ma_worktime,0,1,ma_worktime)) * 100,2) 
                    ,ma_bd_eq_rate = trunc( (v_ma_repairtime/decode(ma_worktime,0,1,ma_worktime)) * 100,2) 
                    ,ma_repairtime = v_ma_repairtime
                    ,ma_stopfreq = v_ma_stopfreq
                    ,ma_repairfreq = v_ma_repairfreq
                    ,mtbf = ma_worktime/decode(v_ma_stopfreq,0,1,v_ma_stopfreq)
                    ,mtbf_eq = ma_worktime/decode(v_ma_repairfreq,0,1,v_ma_repairfreq)
                    ,mttr = v_ma_stoptime/decode(v_ma_stopfreq,0,1,v_ma_stopfreq)
                    ,mttr_eq = v_ma_repairtime/decode(v_ma_repairfreq,0,1,v_ma_repairfreq)
                    ,mtba = ma_worktime/decode(v_ma_repairfreq,0,1,v_ma_repairfreq)
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
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
        and batpgm_no = 'TAKPIMLOCDN'
    ;
    
    
    
end;
/
