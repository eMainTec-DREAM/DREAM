CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIWMPOINT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_w_worktime                       number(18); 
    v_w_stoptime                        number(18);
    v_w_stopfreq                         number(18);
    v_w_repairtime                        number(18);
    v_w_repairfreq                         number(18);
    
    -- exec SP_KPI_MAKE_TAKPIWMPOINT('100','ADMIN'); 
    
    
    cursor c_date is
           select 
                 tyyyy            as yyyy
                ,min(tmonth)  as yyyymm
                ,week            as week
                ,min(tday)      as fdate
                ,max(tday)     as tdate
            from taday a
            where a.tmonth >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
                 and a.tmonth <= to_char(sysdate,'yyyymm')
            group by tyyyy, week     
            order by tyyyy,week
            ;
            
     cursor c_plan is
            select 
                a.comp_no
                ,a.plant
                ,a.yyyymm
                ,a.mtlnpoint mtpoint
                ,avg(nvl(a.pvalue,0)) pvalue
            from TAMTLNPOINT a
            where 1=1
                and a.comp_no = v_comp_no
                and a.mtlnpoint in ('BD','BD_EQ')
                and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
                and a.yyyymm <= to_char(sysdate,'yyyymm')
                group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
           ;
    
begin

    
        
   for c1 in c_date loop
   
       for c2 in c_plan loop
       
                select count(*)
                into v_count 
                from (
                        select a.comp_no, e.plant
                        FROM taworkorder a,
                            tawofail b,
                            tawoequip c,
                            taequipment d,
                            taeqloc e
                       WHERE  a.comp_no =c2.comp_no
                            AND a.comp_no = b.comp_no
                            AND a.wkor_id = b.wkor_id
                            AND a.comp_no = c.comp_no
                            AND a.wkor_id = c.wkor_id
                            AND c.comp_no = d.comp_no
                            AND c.equip_id = d.equip_id
                            and d.comp_no = e.comp_no
                            and d.eqloc_id = e.eqloc_id
                            AND a.wo_status = 'C'       
                            AND a.wkor_date >= c1.fdate
                            AND a.wkor_date <= c1.tdate
                             and e.plant = c2.plant
                            AND a.wo_type = 'BM'        
                    GROUP BY a.comp_no, e.plant
               ) a
             ;
       
            if v_count > 0 then
                        select
                             (select nvl(sum(aa.dtime),0) +nvl(sum(aa.etime),0)+ nvl(sum(aa.ntime),0) 
                              from TALNWRKTIME aa
                              where a.comp_no = aa.comp_no
                                  and  aa.wrk_date >= c1.fdate
                                  AND aa.wrk_date <= c1.tdate
                                  and e.plant = aa.plant
                             ) as w_worktime
                            ,nvl(SUM (b.lndn_work_time),0) as w_stoptime
                            ,nvl(SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END),0) AS w_stopfreq
                            ,nvl(SUM (b.eqdn_work_time),0) as w_repairtime
                            ,nvl(SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END),0) AS w_repairfreq
                        into v_w_worktime, v_w_stoptime, v_w_stopfreq, v_w_repairtime, v_w_repairfreq
                        FROM taworkorder a,
                            tawofail b,
                            tawoequip c,
                            taequipment d,
                            taeqloc e
                       WHERE  a.comp_no =c2.comp_no
                            AND a.comp_no = b.comp_no
                            AND a.wkor_id = b.wkor_id
                            AND a.comp_no = c.comp_no
                            AND a.wkor_id = c.wkor_id
                            AND c.comp_no = d.comp_no
                            AND c.equip_id = d.equip_id
                            and d.comp_no = e.comp_no
                            and d.eqloc_id = e.eqloc_id
                            AND a.wo_status = 'C'       
                            AND a.wkor_date >= c1.fdate
                            AND a.wkor_date <= c1.tdate
                            and e.plant = c2.plant
                            AND a.wo_type = 'BM'        
                    GROUP BY a.comp_no, e.plant
                     ;
             else
                 v_w_worktime :=0;
                 v_w_stoptime :=0;
                 v_w_stopfreq :=0;
                 v_w_repairtime :=0;
                 v_w_repairfreq :=0;
             end if;
        
        
           
           select count(*)
           into v_count
           from takpiwmpoint
           where comp_no = c2.comp_no
               and plant = c2.plant
               and yyyymm = c2.yyyymm
               and week = c1.week
               and kpi_mpoint = c2.mtpoint
           ;
           
           if v_count > 0 then
               update takpiwmpoint set
                   from_to_date = substr(c1.fdate,1,4)|| '.' || substr(c1.fdate,5,2) || '.' ||substr(c1.fdate,7,2) ||' - ' || substr(c1.tdate,1,4)|| '.' || substr(c1.tdate,5,2) ||'.' || substr(c1.tdate,7,2)
                   ,w_plan = nvl(c2.pvalue,0)
                   ,w_actual = 
                                    DECODE(c2.mtpoint,'BD',
                                                 trunc((nvl(v_w_stoptime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2)
                                                                ,'BD_EQ',
                                                 trunc((nvl(v_w_repairtime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2),0)
                   ,w_diff = 
                                    DECODE(c2.mtpoint,'BD',
                                                nvl(c2.pvalue,0) - (trunc((nvl(v_w_stoptime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2))
                                                                ,'BD_EQ',
                                                nvl(c2.pvalue,0) - (trunc((nvl(v_w_repairtime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2)),0)
                                                                
               where comp_no = c2.comp_no
                   and plant = c2.plant
                   and yyyymm = c2.yyyymm
                   and week = c1.week
                   and kpi_mpoint = c2.mtpoint
               ;
           else
               insert into takpiwmpoint(comp_no, plant, yyyymm, week, kpi_mpoint, from_to_date, w_plan, w_actual, w_diff)
               values(c2.comp_no, c2.plant, c2.yyyymm, c1.week, c2.mtpoint
                         ,substr(c1.fdate,1,4)|| '.' || substr(c1.fdate,5,2) || substr(c1.fdate,7,2) ||' - ' || substr(c1.tdate,1,4)|| '.' || substr(c1.tdate,5,2) || substr(c1.tdate,7,2)
                         ,nvl(c2.pvalue,0) 
                         , DECODE(c2.mtpoint,'BD',
                                                 trunc((nvl(v_w_stoptime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2)
                                                                ,'BD_EQ',
                                                 trunc((nvl(v_w_repairtime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2),0)
                         , DECODE(c2.mtpoint,'BD',
                                                nvl(c2.pvalue,0) - (trunc((nvl(v_w_stoptime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2))
                                                                ,'BD_EQ',
                                                nvl(c2.pvalue,0) - (trunc((nvl(v_w_repairtime,0) / decode(nvl(v_w_worktime,0),0,1,v_w_worktime)) * 100, 2)),0)
               );
           end if;  
           
       
       
       
       end loop;
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
        and batpgm_no = 'TAKPIWMPOINT'
    ;
    
    
    
    
end;
/
