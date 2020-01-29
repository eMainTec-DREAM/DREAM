CREATE OR REPLACE PROCEDURE SP_IF_UPD_POPLNRUNLIST   (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
   v_hour number(4);
   v_minute number(4);
   v_second number(4);
   v_is_lnbm varchar2(20);

    cursor c_data is
        select
             a.lnrunlist_id
             ,a.plant
             ,a.dept_no
             ,a.line
             ,a.work_date
             ,a.plan_time
             ,a.run_time
             ,a.bm_time
             ,a.run_rate
             ,a.bm_cnt
             ,a.plan_stime
             ,a.plan_etime
             ,a.ord_no
             ,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as received_date
        from TXLNRUNLIST a
        where 1=1
            and a.ifresult = 'N'
        order by a.plant, a.dept_no, a.line
        ;
        
   cursor c_bm_data(p_plant varchar2, p_dept_no varchar2, p_line varchar2, p_work_date varchar2) is
        select 
            a.lnbmlist_id
            ,a.plant
            ,a.dept_no
            ,a.line
            ,a.work_date
            ,a.bm_stime
            ,a.bm_etime
            ,a.bm_time
            ,a.ord_no
            ,a.remark
        from TXLNBMLIST a
        where 1=1
            and a.plant = p_plant
            and a.dept_no = p_dept_no
            and a.line = p_line
            and a.work_date = p_work_date
        order by a.plant, a.dept_no, a.line, a.work_date, a.bm_stime
    ;
    
--update TXLNRUNLIST set ifresult = 'N';
--delete from tapopline;
--delete from tapoplnrunlist;
--delete from tapoplnbmlist;
--exec SP_IF_UPD_POPLNRUNLIST('100','ADMIN');

begin


    for c1 in c_data loop
    
    
        -- plant master create
        select count(*)
        into v_count
        from tapopline
        where comp_no = v_comp_no
            and pop_plant = c1.plant
            and pop_dept_no = c1.dept_no
            and pop_line_no = c1.line
        ;
        if v_count = 0 then

            
            
            insert into tapopline(comp_no, pop_plant, pop_dept_no, pop_line_no)
            values(v_comp_no, c1.plant, c1.dept_no, c1.line);
            
    
        end if;
        
                
                
    
       -- run list in line
        select count(*)
        into v_count
        from tapoplnrunlist
        where comp_no = v_comp_no
            and pop_plant = c1.plant
            and pop_dept_no = c1.dept_no
            and pop_line_no =c1.line
            and work_date = c1.work_date
        ;

        if v_count > 0 then
             null;-- if v_count > 0 then this case is error
        else
            insert into tapoplnrunlist(
                 comp_no
                ,poplnrunlist_id
                ,lnrunlist_id
                ,pop_plant
                ,pop_dept_no
                ,pop_line_no
                ,work_date
                ,plan_time
                ,run_time
                ,bm_time
                ,run_rate
                ,bm_cnt
                ,plan_stime
                ,plan_etime
                ,ord_no
                ,is_close
                ,ori_plan_time
                ,ori_run_time
                ,ori_bm_time
                ,ori_run_rate
                ,ori_bm_cnt
                ,ori_plan_stime
                ,ori_plan_etime
            )values(
                v_comp_no
                ,sqapoplnrunlist_id.nextval
                ,c1.lnrunlist_id
                ,c1.plant
                ,c1.dept_no
                ,c1.line
                ,c1.work_date
                ,c1.plan_time
                ,c1.run_time
                ,c1.bm_time
                ,c1.run_rate
                ,c1.bm_cnt
                ,c1.plan_stime
                ,c1.plan_etime
                ,c1.ord_no
                ,'N'
                ,c1.plan_time
                ,c1.run_time
                ,c1.bm_time
                ,c1.run_rate
                ,c1.bm_cnt
                ,c1.plan_stime
                ,c1.plan_etime
            );

        end if;
        
        
        update TXLNRUNLIST set
             ifresult = 'Y'
            ,received_date = c1.received_date
        where lnrunlist_id = c1.lnrunlist_id
        ;
        
        for c2 in c_bm_data(c1.plant, c1.dept_no, c1.line, c1.work_date) loop
        
            select   nvl(to_number(min(case when t_type = 1 then t_value end) ),0) as hour
                      ,nvl(to_number(min(case when t_type = 2 then t_value end) ),0) as minute
                      ,nvl(to_number(min(case when t_type = 3 then t_value end) ),0) as second
             into v_hour, v_minute, v_second
             from (
                     select 
                         level as t_type
                        ,regexp_substr(translate(c2.bm_time, '0123456789:'||c2.bm_time, '0123456789:'),'[^:]+', 1, level) as t_value 
                    from dual
                    connect by regexp_substr(translate(c2.bm_time, '0123456789:'||c2.bm_time, '0123456789:'), '[^:]+', 1, level) is not null
                    ) a
            where 1=1
            ;
            
            if (v_hour*60) + v_minute >= 1 then
                v_is_lnbm := 'Y';
            else
                v_is_lnbm := 'N'; 
            end if;
            
           
            
            select count(*)
            into v_count
            from tapoplnbmlist
            where comp_no = v_comp_no
                and pop_plant = c1.plant
                and pop_dept_no = c1.dept_no
                and pop_line_no =c1.line
                and work_date = c1.work_date
                and bm_stime = c2.bm_stime
            ;
            
            if v_count > 0 then
                null; -- if v_count > 0 then this case is error
            else
                insert into tapoplnbmlist(
                    comp_no
                    ,poplnbmlist_id
                    ,lnbmlist_id
                    ,pop_plant
                    ,pop_dept_no
                    ,pop_line_no
                    ,work_date
                    ,bm_stime
                    ,bm_etime
                    ,bm_time
                    ,ord_no
                    ,ori_bm_stime
                    ,ori_bm_etime
                    ,ori_bm_time
                    ,is_lnbm
                    ,remark
                ) values(
                    v_comp_no
                    ,sqapoplnbmlist_id.nextval
                    ,c2.lnbmlist_id
                    ,c2.plant
                    ,c2.dept_no
                    ,c2.line
                    ,c2.work_date
                    ,c2.bm_stime
                    ,c2.bm_etime
                    ,c2.bm_time
                    ,c2.ord_no
                    ,c2.bm_stime
                    ,c2.bm_etime
                    ,c2.bm_time
                    ,v_is_lnbm
                    ,c2.remark
                );
            end if;
        
            update TXLNBMLIST set
                 ifresult = 'Y'
                ,received_date = c1.received_date
            where lnbmlist_id = c2.lnbmlist_id
            ;
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
        and batpgm_no = 'POPLNRUNLIST'
    ;

end;
/
