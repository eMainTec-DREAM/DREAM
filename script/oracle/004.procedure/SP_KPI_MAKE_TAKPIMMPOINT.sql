CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMMPOINT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_bd_actual                           number(18,3);
    v_btbf_actual                         number(18,3);
    v_mttr_actual                        number(18,3);
    v_pm_actual                         number(18);
    v_tr_actual                           number(18,3);
    
    -- exec SP_KPI_MAKE_TAKPIMMPOINT('100','ADMIN'); 
    
    
--BD,MTBF,MTTR,PM
    cursor c_bd_plan is
        select 
            a.comp_no    
            ,a.plant
            ,a.yyyymm
            ,a.mtlnpoint mtpoint
            ,avg(nvl(a.pvalue,0)) pvalue
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= a.yyyymm
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 
                      (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                  ,2) as acm_plan
             ,(select avg(nvl(aa.pvalue,0))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
             ) as next_plan
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 12
                  ,2) as y_plan
        from TAMTLNPOINT a
        where 1=1
            and a.comp_no = v_comp_no
            and a.mtlnpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
            group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
       ;
 
     cursor c_bd_actual is
                SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
                    ,(select nvl(sum(aa.dtime),0) + nvl(sum(aa.etime),0) + nvl(sum(aa.ntime),0) 
                      from TALNWRKTIME aa
                      where a.comp_no = aa.comp_no
                          and  aa.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                          AND aa.wrk_date <= to_char(sysdate,'yyyymmdd') 
                          and e.plant = aa.plant
                     ) as m_worktime
                    ,nvl(SUM (b.lndn_work_time),0) as m_stoptime
                    ,nvl(SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_stopfreq
                    ,nvl(SUM (b.eqdn_work_time),0) as m_repairtime
                    ,nvl(SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE  a.comp_no = v_comp_no
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                    AND a.wkor_date <= to_char(sysdate,'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6)
        ;

    cursor c_pm_plan_actual is       
       select 
            a.comp_no
            ,a.plant
            ,a.yyyymm
            ,a.mtpoint
            ,a.pvalue
            ,a.avalue
            ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_plan
              ,(select nvl(sum(aa.avalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_actual
            ,(select aa.pvalue
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
              ) as next_plan
              ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as y_plan
        from vwkpimpmrslt a
        where 1=1
            and a.comp_no = v_comp_no
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
    ;

        
begin

    
        
   for c1 in c_bd_plan loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                     m_plan = c1.pvalue
                     ,acm_plan = c1.acm_plan
                     ,next_plan = c1.next_plan
                     ,y_plan = c1.y_plan
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, acm_plan, next_plan,y_plan,m_actual,acm_actual,y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, c1.pvalue, c1.acm_plan, c1.next_plan, c1.y_plan,0,0,0
           );
       end if;
       
   end loop;

   for c1 in c_bd_actual loop

     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_stoptime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD'
            ;
     update takpimmpoint set
          m_actual = nvl(c1.m_stopfreq,0)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BDN'
            ;
            
     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_repairtime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD_EQ'
            ;
     update takpimmpoint set
          m_actual = nvl(c1.m_repairfreq,0)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BDN_EQ'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF_EQ'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_stoptime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_repairtime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR_EQ'
            ;

       --누적실적 넣기
       update takpimmpoint a set a.acm_actual = ( select   nvl(sum(aa.m_actual),0) 
                                                                                / (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                                                                     from takpimmpoint aa
                                                                     where 1=1
                                                                         and aa.comp_no = a.comp_no
                                                                         and aa.plant = a.plant
                                                                         and aa.yyyymm <=  a.yyyymm
                                                                         and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                                                                         and aa.kpi_mpoint = a.kpi_mpoint 
                                                                  )
      where a.comp_no = c1.comp_no
          and a.plant = c1.plant
          and a.yyyymm = c1.yyyymm
          and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
      ;           
       
       
   end loop;
   

   
   for c1 in c_pm_plan_actual loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                      m_plan = nvl(c1.pvalue,0)
                     ,m_actual = nvl(c1.avalue,0)
                     ,acm_plan = nvl(c1.acm_plan,0)
                     ,acm_actual = nvl(c1.acm_actual,0)
                     ,next_plan = nvl(c1.next_plan,0)
                     ,y_plan = nvl(c1.y_plan,0)
                     ,y_actual = nvl(c1.acm_actual,0)
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, m_actual, acm_plan, acm_actual, next_plan, y_plan, y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, nvl(c1.pvalue,0), nvl(c1.avalue,0), nvl(c1.acm_plan,0), nvl(c1.acm_actual,0), nvl(c1.next_plan,0),nvl(c1.y_plan,0),  nvl(c1.acm_actual,0)
           );
       end if;
       
   end loop;
   
   
   update takpimmpoint a set
       a.m_diff = nvl(a.m_plan,0) - nvl(a.m_actual,0)
       ,a.m_ach = trunc( nvl(a.m_actual,0)/decode(nvl(a.m_plan,0),0,1,nvl(a.m_plan,0))* 100,2)
       ,a.acm_diff = nvl(a.acm_plan,0) - nvl(a.acm_actual,0)
       ,a.acm_ach = trunc(nvl(a.acm_actual,0)/decode(nvl(a.acm_plan,0),0,1,nvl(a.acm_plan,0)) * 100 , 2)
   where comp_no = v_comp_no
       and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
        and a.yyyymm <= to_char(sysdate,'yyyymm')
        --and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')   
    ;
   
   
   
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
        and batpgm_no = 'TAKPIMMPOINT'
    ;
    
    
    
    
end;
/
