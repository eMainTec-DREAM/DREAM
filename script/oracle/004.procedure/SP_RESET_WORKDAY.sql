CREATE OR REPLACE PROCEDURE SP_RESET_WORKDAY(
     v_comp_no                 IN varchar2          
    ,v_plant                       IN varchar2
    ,v_start_sched_date      IN varchar2
    ,v_user_no                   IN varchar2  
) IS
    v_count                 number(4);   
    v_last_sched_date   varchar2(8);
    v_user_id                 number(18);
   
    
    CURSOR c_pm_data IS
                select 
                      a.comp_no
                    , a.pm_id
                    , a.init_wrk_date
                    , a.schedule_type
                from tapmlst a, taeqloc b
                where 1=1
                    and a.comp_no = b.comp_no
                    and a.eqloc_id = b.eqloc_id
                    and a.comp_no = v_comp_no
                    and b.plant = v_plant
                ;
  
BEGIN

     select count(*) 
     into v_count
     from tauser 
     where comp_no = v_comp_no
         and user_no = v_user_no
     ;
     
     if v_count > 0 then
        select user_id
        into v_user_id
        from tauser
        where  comp_no = v_comp_no
             and user_no = v_user_no
             and rownum = 1
        ;
     end if;
     
     /*Line Run time ready to reschedule*/
    update TALNWRKTIME a set 
                   a.dtime=0
                 , a.ntime=0
                 , a.etime=0
    where 1=1
        and a.comp_no = v_comp_no
        and a.plant = v_plant
        and a.wrk_date in ( select aa.cal_date
                                     from TAWRKCALENDAR aa
                                     where aa.comp_no = v_comp_no
                                         and aa.plant = v_plant
                                         and aa.cal_date >= v_start_sched_date
                                         and aa.is_work = 'N'
                                   )
    ;
    
    /*All Default Run time is reseted*/
    SP_PM_MAKE_TALNWRKTIME(v_comp_no, v_user_no);

    
    /*PM Master schedule ready to reschedule*/
    FOR c1 IN c_pm_data LOOP
    
                select max(b.sched_date)
                into v_last_sched_date
                from tapmlst a, tapmsched b
                where a.comp_no = b.comp_no
                   and a.pm_id = b.pm_id
                   and a.comp_no = c1.comp_no
                   and a.pm_id = c1.pm_id
                   and b.sched_date <=v_start_sched_date
               ;
               
               if v_last_sched_date is null then
                   v_last_sched_date := c1.init_wrk_date;
               end if;
               
               
                update tapmlst a set 
                    a.last_sch_date = v_last_sched_date
                where a.comp_no = c1.comp_no
                    and a.pm_id = c1.pm_id
                ;
                
                 /*All Schedule is rescheduled*/
                SP_PM_MAKE_SCHEDULE_BYONE(v_comp_no, c1.pm_id, v_user_id,  TO_CHAR(ADD_MONTHS(SYSDATE,12),'yyyymmdd'));
                
    END LOOP;
    
    
   
    
    
END;
/
