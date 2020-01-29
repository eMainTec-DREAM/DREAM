/* 예방작업 스케쥴(TAPMSCHED)을 확인하여 work order발행을 발행
 * 1. 예방작업 마스터를 저장하면 예방작업 스케쥴을 생성하고 즉시 work order를 발행
 * 2. 야간에 scheduler를 이용하여 예방작업 스케쥴을 확인하고 work order를 발행 
 * 2017.07.20. 박철완 수정
 *    수정내용: work order발행시 발행할 일자가 휴일인 경우 해당 건은 일정을 발행하지 않고 스케쥴을 삭제함.
 * */
CREATE OR REPLACE procedure SP_PM_MAKE_WORKORDER(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                         number(4); 
    v_wo_res_before           number(4);  
    v_wo_id                        number(18);
    v_wo_no                      number(18);  
    v_sched_date               varchar2(8);
    v_is_work                    varchar2(20);
    
    -- exec SP_PM_MAKE_WORKORDER('100','ADMIN');
    cursor c_pm_sched_list is
        select 
             b.pm_id
            ,b.wo_res_bef 
            ,(select aa.wcode_id from tadept aa where b.comp_no = aa.comp_no and b.dept_id = aa.dept_id) as wcode_id
            ,b.pm_type
            --------------------------------------------------------------------------------------------------------------------------------------
            ,b.wo_type
            ,b.period_type
            ,b.cycle
            ,a.pmsched_id
            ,a.sched_date
        from TAPMSCHED a, TAPMLST b
        where 1=1
            and a.comp_no = b.comp_no
            and a.pm_id = b.pm_id
            and a.comp_no = v_comp_no
            and a.pmsched_status = 'P' -- not release work order 
            and a.wkor_id is null
             and ( to_date(a.sched_date,'yyyymmdd') - trunc(sysdate) ) <= nvl(b.wo_res_bef,7)
        order by sched_date
        ;
     
        
begin
        
        
    for c1 in c_pm_sched_list loop
        
                -- 점검(PMI)이고 주기구분이 일자(D)일 경우, 그리고 7일 보다 작은 경우 work ordeR를 발행하지 않고
                -- 그 이외는 휴일이 아닌날로 발행한다.
                select nvl(min(is_work), 'Y')
                into v_is_work
                from TAWRKCALENDAR 
                where comp_no =v_comp_no
                    and cal_date = c1.sched_date
                ;
                
                if c1.wo_type = 'PMI' and c1.period_type = 'D' and c1.cycle < 7  and v_is_work = 'N' then
                   
                 -- work order를 발행하지 않고 일정을 삭제함..
                    delete from  TAPMSCHED 
                    where comp_no = v_comp_no
                        and pmsched_id = c1.pmsched_id
                    ;
                    
                else
                    select nvl(min(cal_date), c1.sched_date)
                    into v_sched_date
                    from TAWRKCALENDAR 
                    where comp_no =v_comp_no
                        and cal_date >= c1.sched_date
                        and is_work ='Y' 
                    ;
                    
                     select sqawkor_id.nextval 
                     into v_wo_id
                     from dual;
                     
                      insert into taworkorder(
                         comp_no
                        ,wkor_id
                        ,wo_no
                        ,equip_id
                        ,description
                        ,wo_status 
                        ,wo_type
                        ,pm_type
                        ,wo_gen_type
                        ,start_date
                        ,end_date
                        ,wkor_date
                        ,dept_id
                        ,emp_id
                        ,perform
                        ,pm_id
                        ,pmsched_id
                        ,upd_date
                        ,upd_by
                        ,wkctr_id
                    )
                    select 
                         v_comp_no  as comp_no
                        ,v_wo_id      as wkor_id
                        ,v_wo_id      as wo_no
                        ,a.equip_id   as equip_id
                        ,a.description  as description
                        ,'P'                 as wo_status -- stand by 
                        ,a.wo_type      as wo_type  -- preventmaint
                        ,a.pm_type      as pm_type
                        ,'PMPLAN'        as wo_gen_type
                        ,v_sched_date  as start_date
                        ,v_sched_date  as end_date
                        ,v_sched_date  as wkor_date
                        ,a.dept_id         as dept_id
                        ,a.emp_id        as emp_id
                        ,a.remark         as perform
                        ,c1.pm_id         as pm_id
                        ,c1.pmsched_id  as pmsched_id
                        ,to_char(sysdate,'yyyymmdd') as upd_date
                        ,(select user_id from tauser where comp_no = v_comp_no 
                                    and user_no = v_user_no and rownum = 1
                         ) as upd_by
                        ,a.wkctr_id
                    from tapmlst a
                    where 1=1
                        and a.comp_no = v_comp_no
                        and a.pm_id = c1.pm_id
                    ;
                    
                    -- tawoequip 
                    insert into tawoequip(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description)
                    select comp_no, sqawoequip_id.nextval,  v_wo_id,  equip_id, eqctg_id, description 
                    from tapmequip
                    where comp_no = v_comp_no
                        and pm_id = c1.pm_id
                    ;
                    
                    -- parts
                    insert into tawoparts(
                        comp_no
                        ,wopart_id
                        ,wkor_id
                        ,wcode_id
                        ,part_id
                        ,part_grade
                        ,use_qty
                        ,unit_price
                        ,tot_price
                    )
                    select v_comp_no as comp_no
                        ,sqawopart_id.nextval as wopart_id
                        ,v_wo_id     as wkor_id
                        ,c1.wcode_id as wcode_id
                        ,a.part_id     as part_id
                        ,'A'              as part_grade
                        ,a.use_qty   as use_qty
                        ,b.last_price  as unit_price
                        ,a.use_qty * b.last_price as tot_price
                    from tapmpart a
                        ,taparts b
                    where a.comp_no = b.comp_no
                        and a.part_id = b.part_id
                        and a.comp_no = v_comp_no
                        and a.pm_id =  c1.pm_id
                    ;
                    
                    -- inspection point
                    insert into tawopoint(
                        comp_no
                        ,wopoint_id
                        ,wkor_id
                        ,pm_point_id
                        ,pm_point_rslt_status
                        ,pm_point_rep_status
                    )
                    select v_comp_no as comp_no
                        ,sqawopoint_id.nextval as wopoint_id
                        ,v_wo_id     as wkor_id
                        ,a.pm_point_id as pm_point_id
                        ,(select cdsysd_no from 
                                (select * from tacdsysd
                                where list_type='PM_POINT_RSLT_STATUS'
                                order by ord_no)
                                where rownum=1)               as pm_point_rslt_status
                        ,'GD'               as pm_point_rep_status
                    from tapmpoint a
                    where 1=1
                        and a.comp_no = v_comp_no
                        and a.pm_id =  c1.pm_id
                        and a.is_active = 'Y'
                    ;
                    
                    
                     -- stardard point insert 
                    insert into TAWOSTPOINT(
                         comp_no
                        ,wostpoint_id
                        ,wkor_id
                        ,stwrk_point_id
                        ,st_point_rslt_status
                    )
                    select v_comp_no as comp_no
                        ,sqawostpoint_id.nextval as wostpoint_id
                        ,v_wo_id     as wkor_id
                        ,a.stwrk_point_id as stwrk_point_id
                        ,'GD'               as st_point_rslt_status
                    from TASTWRKPOINT a, TASTWRKLST b
                    where 1=1
                        and a.comp_no = v_comp_no
                        and a.comp_no = b.comp_no
                        and a.stwrk_id = b.stwrk_id
                        and a.is_active = 'Y'
                        and b.pm_type = c1.pm_type
                        and b.is_active = 'Y'
                        and b.is_force ='Y'
                    ;
                    
                    -- update wo_no in TAPMSCHED
                    update TAPMSCHED set
                         wkor_id = v_wo_id
                        ,pmsched_status =  'S' 
                    where comp_no = v_comp_no
                        and pmsched_id = c1.pmsched_id
                    ;
                    
                    SP_WOMAKE_4WP_BYONE(v_comp_no, v_wo_id);
            
            
                end if;
            
            
            
            
            
        
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
        and batpgm_no = 'TAPMWORK'
    ;
    
    
    
end;
/
