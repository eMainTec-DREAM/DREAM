CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMLOCCTGDN(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_m_stoptime                       number(18);
    v_m_repairtime                     number(18);
    v_m_stopfreq                        number(18);
    v_m_repairfreq                      number(18);
    
    -- exec SP_KPI_MAKE_TAKPIMLOCCTGDN('100','ADMIN'); 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    cursor c_lnctgwrktime is
        select 
            a.comp_no    
            ,a.plant
            ,substr(a.wrk_date,1,6) as yyyymm
            ,a.eqloc_id
            ,b.eqctg_id
            ,sum(a.dtime) + sum(a.etime) + sum(a.ntime) as m_worktime
        from TALNWRKTIME a
               ,taeqctg b
        where 1=1
            and a.comp_no = b.comp_no
            and b.lvl = 1
            and b.is_use = 'Y'
            and a.comp_no = v_comp_no
            and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= to_char(sysdate,'yyyymmdd')
        group by a.comp_no, a.plant, substr(a.wrk_date,1,6), a.eqloc_id, b.eqctg_id
       ;
       
     /*고장시간 집계대상일자 및 라인*/
     cursor  c_eqlocctg_list is
         select 
                 a.comp_no
                 ,a.plant
                 ,substr(a.wrk_date,1,6) as yyyymm
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
            group by a.comp_no, a.plant, substr(a.wrk_date,1,6) , a.eqloc_id,b.eqctg_id
            order by 1,2,3,4,5
           ;
        
begin

    
        
   for c1 in c_lnctgwrktime loop
   
       --  가동시간 Setting 
       select count(*)
       into v_count
       from takpimlocctgdn
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and eqloc_id = c1.eqloc_id
           and eqctg_id = c1.eqctg_id
       ;
       
       if v_count > 0 then
           update takpimlocctgdn set
                     m_worktime = c1.m_worktime
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and eqloc_id = c1.eqloc_id
                and eqctg_id = c1.eqctg_id
            ;
       else
           insert into takpimlocctgdn( comp_no, plant, yyyymm, eqloc_id, eqctg_id, m_worktime
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm,  c1.eqloc_id, c1.eqctg_id, c1.m_worktime
           );
       end if;
       
   end loop;
   
   
   for c1 in c_eqlocctg_list loop
   
         --//월  데이타 추출...
          select sum(a.m_stoptime)
                  ,sum(a.m_repairtime)
                  ,sum(a.m_stopfreq)
                  ,sum(a.m_repairfreq)
          into v_m_stoptime, v_m_repairtime, v_m_stopfreq, v_m_repairfreq
          from (SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
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
                    AND a.wkor_date >= to_char(trunc(to_date(c1.yyyymm,'yyyymm'),'mm'),'yyyymmdd')
                    AND a.wkor_date <= to_char(last_day(to_date(c1.yyyymm,'yyyymm')),'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6), d.eqloc_id, f.eqctg_id
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

          
          update takpimlocctgdn set
                     m_stoptime = v_m_stoptime
                     ,m_repairtime = v_m_repairtime
                     ,m_stopfreq = v_m_stopfreq
                     ,m_repairfreq = v_m_repairfreq
                    
                    ,mtbf = m_worktime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mtbf_eq = m_worktime/decode(v_m_repairfreq,0,1,v_m_repairfreq)
                    ,mttr = v_m_stoptime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mttr_eq = v_m_repairtime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mtba = m_worktime/decode(v_m_repairfreq,0,1,v_m_repairfreq)
                    ,ratio =trunc( (m_stoptime/decode(m_worktime,0,1,m_worktime)) * 100,2) 
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
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
        and batpgm_no = 'TAKPIMLOCCTGDN'
    ;
    
    
    
end;
/
