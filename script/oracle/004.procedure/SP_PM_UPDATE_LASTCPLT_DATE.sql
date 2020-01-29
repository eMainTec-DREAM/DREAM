CREATE OR REPLACE PROCEDURE DREAM_DEV.SP_PM_UPDATE_LASTCPLT_DATE(
     v_comp_no           IN varchar2    
    ,v_pm_id               IN number  
    ,v_sched_id           IN number 
) IS
    v_count                                                       number(4);
    v_wo_type                                                   varchar2(8);
    v_pm_type                                                   varchar2(8);   
    v_last_cplt_date                                           varchar2(8);
    v_next_plan_date                                         varchar2(8);
    v_last_cplt_by                                              number(18);
    v_pm_result_status                                      varchar2(30);
  
BEGIN

          select
                 nvl(max(a.wo_type),'XXX') as wo_type
                ,nvl(max(a.pm_type),'YYY') as pm_type
          into v_wo_type, v_pm_type
          from TAPMLST A
          where a.comp_no = v_comp_no
                and a.pm_id     = v_pm_id
         ;
         
         if v_wo_type = 'PMI' and v_pm_type in ('RINS', 'EINS') then
               
               select max(actual_date)
               into v_last_cplt_date
               from TAPMINSSCHED a
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMINSSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pminssched_id = v_sched_id
                                                  )
               ;
               
               select max(b.emp_id)
               into v_last_cplt_by
               from TAPMINSSCHED a INNER JOIN TAPMINSLIST b
               ON a.comp_no = b.comp_no
               AND a.pminssched_id = b.pminssched_id
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.actual_date = v_last_cplt_date
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMINSSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pminssched_id = v_sched_id
                                                  )
               ;
               
               select max(b.result_value)
               into v_pm_result_status
               from TAPMINSSCHED a INNER JOIN TAPMINSLIST b
               ON a.comp_no = b.comp_no
               AND a.pminssched_id = b.pminssched_id
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.actual_date = v_last_cplt_date
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMINSSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pminssched_id = v_sched_id
                                                  )
               ;
               select min(plan_date)
                   into v_next_plan_date
                   from TAPMINSSCHED a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from TAPMINSSCHED aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pminssched_id = v_sched_id
                                                      )
                       and actual_date > v_last_cplt_date
                       ;
               
               if(v_next_plan_date='') then 
                  select min(plan_date)
                   into v_next_plan_date
                   from TAPMINSSCHED a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from TAPMINSSCHED aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pminssched_id = v_sched_id
                                                      )
                   ;
               end if;
               

               update TAPMEQUIP set 
                    last_cplt_date = v_last_cplt_date
                   ,next_plan_date = v_next_plan_date
                   ,last_cplt_by = v_last_cplt_by
                   ,pm_result_status = v_pm_result_status
               where 1=1
                   and comp_no = v_comp_no
                   and pmequip_id =  (select aa.pmequip_id
                                                from TAPMINSSCHED aa
                                                where aa.comp_no = v_comp_no
                                                     and aa.pminssched_id = v_sched_id
                                               )
               ;
                      
                      
         elsif v_wo_type = 'PMI' and v_pm_type in ('DINS') then
               
               select max(actual_date)
               into v_last_cplt_date
               from TAPMINSDSCHED a
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMINSDSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pminsdsched_id = v_sched_id
                                                  )
               ;
               
               select max(b.emp_id)
               into v_last_cplt_by
               from TAPMINSDSCHED a INNER JOIN TAPMINSDLIST b
               ON a.comp_no = b.comp_no
               AND a.pminsdsched_id = b.pminsdsched_id
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.actual_date = v_last_cplt_date
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMINSDSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pminsdsched_id = v_sched_id
                                                  )
               ;
               select max(b.result_value)
               into v_pm_result_status
               from TAPMINSDSCHED a INNER JOIN TAPMINSDLIST b
               ON a.comp_no = b.comp_no
               AND a.pminsdsched_id = b.pminsdsched_id
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.actual_date = v_last_cplt_date
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMINSDSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pminsdsched_id = v_sched_id
                                                  )
               ;
               
               select min(plan_date)
                   into v_next_plan_date
                   from TAPMINSDSCHED a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from TAPMINSDSCHED aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pminsdsched_id = v_sched_id
                                                      )
                       and actual_date > v_last_cplt_date
                   ;
                   
               if(v_next_plan_date = '') then 
                   select min(plan_date)
                   into v_next_plan_date
                   from TAPMINSDSCHED a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from TAPMINSDSCHED aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pminsdsched_id = v_sched_id
                                                      )
                   ;
                   
               end if;
               
               

               update TAPMEQUIP set 
                    last_cplt_date = v_last_cplt_date
                   ,next_plan_date = v_next_plan_date
                   ,last_cplt_by = v_last_cplt_by
                   ,pm_result_status = v_pm_result_status
               where 1=1
                   and comp_no = v_comp_no
                   and pmequip_id =  (select aa.pmequip_id
                                                from TAPMINSDSCHED aa
                                                where aa.comp_no = v_comp_no
                                                     and aa.pminsdsched_id = v_sched_id
                                               )
               ;
               
         elsif v_wo_type = 'PMI' and v_pm_type in ('PINS') then
               
               select max(actual_date)
               into v_last_cplt_date
               from TAPMPTRLSCHED a
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMPTRLSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pmptrlsched_id = v_sched_id
                                                  )
               ;
               
               select max(b.emp_id)
               into v_last_cplt_by
               from TAPMPTRLSCHED a INNER JOIN TAPMPTRLRSLTLIST b
               ON a.comp_no = b.comp_no
               AND a.pmptrlsched_id = b.pmptrlsched_id
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.actual_date = v_last_cplt_date
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from TAPMPTRLSCHED aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pmptrlsched_id = v_sched_id
                                                  )
               ;
               
               select min(plan_date)
                   into v_next_plan_date
                   from TAPMPTRLSCHED a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from TAPMPTRLSCHED aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pmptrlsched_id = v_sched_id
                                                      )
                      and actual_date > v_last_cplt_date
                   ;
                   
               if(v_next_plan_date = '') then 
                   select min(plan_date)
                   into v_next_plan_date
                   from TAPMPTRLSCHED a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from TAPMPTRLSCHED aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pmptrlsched_id = v_sched_id
                                                      )
                   ;
               end if;
               
               

               update TAPMEQUIP set 
                    last_cplt_date = v_last_cplt_date
                   ,next_plan_date = v_next_plan_date
                   ,last_cplt_by = v_last_cplt_by
                   ,pm_result_status = v_pm_result_status
               where 1=1
                   and comp_no = v_comp_no
                   and pmequip_id =  (select aa.pmequip_id
                                                from TAPMPTRLSCHED aa
                                                where aa.comp_no = v_comp_no
                                                     and aa.pmptrlsched_id = v_sched_id
                                               )
               ;
               

         elsif v_wo_type = 'XXX' and v_pm_type in ('YYY') then
         
             null;
         
         else

               select max(actual_date)
               into v_last_cplt_date
               from tapmsched a
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from tapmsched aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pmsched_id = v_sched_id
                                                  )
               ;
               
               select max(b.emp_id)
               into v_last_cplt_by
               from TAPMSCHED a INNER JOIN TAWORKORDER b
               ON a.comp_no = b.comp_no
               AND a.pmsched_id = b.pmsched_id
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.actual_date = v_last_cplt_date
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from tapmsched aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pmsched_id = v_sched_id
                                                  )
               ;
               
               select max(c.calib_result_status)
               into v_pm_result_status
               from TAPMSCHED a INNER JOIN TAWORKORDER b
               ON a.comp_no = b.comp_no
               AND a.pmsched_id = b.pmsched_id
               INNER JOIN TAWOCALIB c
               ON b.comp_no = c.comp_no
               AND b.wkor_id = c.wkor_id
               where 1=1
                   and a.comp_no = v_comp_no
                   and a.pm_id = v_pm_id
                   and a.pmsched_status = 'C'
                   and a.actual_date = v_last_cplt_date
                   and a.pmequip_id = (select aa.pmequip_id
                                                  from tapmsched aa
                                                  where aa.comp_no = v_comp_no
                                                      and aa.pmsched_id = v_sched_id
                                                  )
               ;
               
                select NVL(min(plan_date),-1)
                   into v_next_plan_date
                   from tapmsched a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from tapmsched aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pmsched_id = v_sched_id
                                                      )
                       and actual_date > v_last_cplt_date
                   ;
                   
               if(v_next_plan_date=-1) then 
                   select min(plan_date)
                   into v_next_plan_date
                   from tapmsched a
                   where 1=1
                       and a.comp_no = v_comp_no
                       and a.pm_id = v_pm_id
                       and a.pmsched_status in('P', 'S')
                       and a.pmequip_id = (select aa.pmequip_id
                                                      from tapmsched aa
                                                      where aa.comp_no = v_comp_no
                                                          and aa.pmsched_id = v_sched_id
                                                      )
                   ;
               end if;
               
               

               update TAPMEQUIP set 
                    last_cplt_date = v_last_cplt_date
                   ,next_plan_date = v_next_plan_date
                   ,last_cplt_by = v_last_cplt_by
                   ,pm_result_status = v_pm_result_status
               where 1=1
                   and comp_no = v_comp_no
                   and pmequip_id =  (select aa.pmequip_id
                                                from tapmsched aa
                                                where aa.comp_no = v_comp_no
                                                     and aa.pmsched_id = v_sched_id
                                               )
               ;         
         
         end if;
    
END;
/
