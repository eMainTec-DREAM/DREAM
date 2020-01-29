CREATE OR REPLACE procedure SP_PM_MAKE_TALNWRKTIME(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                               number(4); 
    v_dow_count                       number(4); 
    v_dow_dtime                       number(18); 
    v_dow_ntime                       number(18); 
    v_dow_etime                       number(18); 
    v_lnwrktime_id                     number(18);
 
    
    -- exec SP_PM_MAKE_TALNWRKTIME('100','ADMIN'); 

    cursor c_data is
        select 
           comp_no, eqloc_id, plant
           ,nvl(dtime,0) as dtime
           ,nvl(ntime,0) as ntime
           ,nvl(etime,0) as etime
        from taeqloc 
        where 1=1
            and comp_no = v_comp_no
            and is_operation = 'Y'
            and is_use = 'Y'
        order by plant, eqloc_no
    ;
    
    cursor c_day(p_plant varchar2) is
        select cal_date as tday
        from TAWRKCALENDAR
        where comp_no = v_comp_no
            and plant = p_plant
            and is_work = 'Y'
            and cal_date >= to_char(sysdate,'yyyymmdd')
            --and cal_date >='20160829'
            and cal_date <= to_char(add_months(sysdate,1),'yyyymmdd')
       ;
    
        
begin

  
        
        

    for c1 in c_data loop
        
        for c2 in c_day(c1.plant) loop

            select count(*)
            into v_count
            from TALNWRKTIME
            where comp_no = v_comp_no
                and plant = c1.plant
                and eqloc_id = c1.eqloc_id
                and wrk_date = c2.tday
            ;
            
            select count(*)
            into v_dow_count
            from TAEQLOCDOWRUN
            where 1=1
            and comp_no = v_comp_no
            and eqloc_id = c1.eqloc_id
            and is_use='Y'
            and dow = (   select dow
                                 from taday
                                 where tday=c2.tday    )
            ;
            
            if v_count = 0 then
                 select sqalnwrktime_id.nextval 
                 into v_lnwrktime_id
                 from dual;
                if v_dow_count > 0 then 
                        select nvl(dtime,0), nvl(ntime,0), nvl(etime,0)
                        into v_dow_dtime,v_dow_ntime,v_dow_etime
                        from TAEQLOCDOWRUN
                        where 1=1
                        and comp_no = v_comp_no
                        and eqloc_id = c1.eqloc_id
                        and is_use='Y'
                        and dow = (   select dow
                                             from taday
                                             where tday=c2.tday    )
                        ;
                
                    insert into TALNWRKTIME (comp_no, lnwrktime_id, plant, wrk_date, eqloc_id, dtime, ntime, etime )
                    values(
                           v_comp_no, v_lnwrktime_id, c1.plant, c2.tday, c1.eqloc_id, v_dow_dtime,v_dow_ntime,v_dow_etime
                        );
                        
                else
                
                    insert into TALNWRKTIME (comp_no, lnwrktime_id, plant, wrk_date, eqloc_id, dtime, ntime, etime )
                    values(
                           v_comp_no, v_lnwrktime_id, c1.plant, c2.tday, c1.eqloc_id, c1.dtime, c1.ntime, c1.etime
                        );
                        
                end if;
                
                
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
        and batpgm_no = 'TALNWRKTIME'
    ;
    
    
    
end;
/
