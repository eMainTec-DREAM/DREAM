CREATE OR REPLACE PROCEDURE "SP_MUP_TYPMLIST_PART" (
    v_comp_no varchar2 default '100'
) is

   v_count     number(4);
   v_item_no varchar2(20);
   v_equip_id number(18);
   v_dept_id number(18);
   v_emp_id number(18);
   v_wkctr_id number(18);
   v_pm_id number(18);
   v_pm_no varchar2(30);
   v_eqloc_id number(18); 
   v_part_id number(18);
   v_init_wrk_date date;
   v_pm_categ varchar2(20);
   ERROR_MSG varchar2(1024);

   -- truncate table TYPMLIST_PART
   
   
   -- delete from tapmequip where pm_id in (select pm_id from TAPMLST where pm_type <> 'INS')
   -- delete from tapmpart where pm_id in (select pm_id from TAPMLST where pm_type <> 'INS')
   -- delete from TAPMSCHED where pm_id in (select pm_id from TAPMLST where pm_type <> 'INS')
   -- delete from TAPMLST where pm_type <> 'INS'
   -- truncate table TAEQPART
   -- exec SP_MUP_TYPMLIST_PART('120')
   
   
   
   
   cursor c_dept_data is
            select b.comp_no
                    , b.dept_id
                    ,count(*) as ins_cnt
            from TYPMLIST_PART a
                   ,taequipment b
            where 1=1
                and a.excel_no = b.excel_no
                and b.comp_no = v_comp_no
            group by b.comp_no, b.dept_id
            ;
            
            
      
    cursor c_excel_data is
                select 
                           sn
                          ,trim(excel_no) as excel_no 
                          ,trim(item_no) as item_no 
                          ,trim(description) as description 
                          ,trim(pm_type) as pm_categ_desc 
                          ,trim(measure_tools) as measure_tools 
                          ,trim(cycle) as cycle 
                          ,trim(period_type) as period_type 
                          ,trim(wo_res_bef) as wo_res_bef
                          ,trim(work_id) as work_id
                          ,trim(work_name) as work_name
                          ,trim(wkctr_desc) as wkctr_desc
                          ,trim(asmb) as asmb
                          ,replace(replace(trim(init_wrk_date),'-',''),'.','') as init_wrk_date
                          ,trim(remark) as remark 
                          ,trim(pno1) as pno1 
                          ,trim(pname1) as pname1
                          ,trim(pspec1) as pspec1
                          ,nvl(qty1,0) as qty1
                          ,trim(pno2) as pno2
                          ,trim(pname2) as pname2
                          ,trim(pspec2) as pspec2
                          ,nvl(qty2,0) as qty2
                          ,trim(pno3) as pno3
                          ,trim(pname3) as pname3
                          ,trim(pspec3) as pspec3
                          ,nvl(qty3,0) as qty3
                          ,trim(pno4) as pno4
                          ,trim(pname4) as pname4
                          ,trim(pspec4) as pspec4
                          ,nvl(qty4,0) as qty4
                          ,trim(pno5) as pno5
                          ,trim(pname5) as pname5
                          ,trim(pspec5) as pspec5
                          ,nvl(qty5,0) as qty5
                          ,is_valid
                from TYPMLIST_PART a
                where 1=1
                order by sn
                ;
                                
begin

    
    for c1 in c_dept_data loop
    
        delete from TAPMSCHED 
        where comp_no = c1.comp_no
            and pm_id in ( select pm_id
                                  from TAPMLST
                                  where comp_no = c1.comp_no
                                      and dept_id = c1.dept_id
                                      and pm_type <> 'INS'
                                );
                                
                                
        
        delete from tapmequip 
        where comp_no = c1.comp_no
            and pm_id in ( select pm_id
                                  from TAPMLST
                                  where comp_no = c1.comp_no
                                      and dept_id = c1.dept_id
                                      and pm_type <> 'INS'
                                );
        
        delete from tapmpart 
        where comp_no = c1.comp_no
            and pm_id in ( select pm_id
                                  from TAPMLST
                                  where comp_no = c1.comp_no
                                      and dept_id = c1.dept_id
                                      and pm_type <> 'INS'
                                );
        
        delete from TAPMLST 
        where comp_no = c1.comp_no 
            and  dept_id = c1.dept_id
            and pm_type <> 'INS';
    
    end loop;
    
    update TYPMLIST_PART set  is_valid = 'Y';
    
    for c1 in c_excel_data loop
               
               select count(*)
               into v_count
               from TAEQUIPMENT
               where comp_no = v_comp_no
                   and excel_no = c1.excel_no
               ;
               if v_count = 0 then
                   update TYPMLIST_PART set
                       is_valid = 'N'
                       ,msg = 'There is no equipment master. check the excel#  in excel file'
                   where sn = c1.sn
                   ;
               end if;
               
               
               if length(c1.init_wrk_date) <> 8 then
                   update TYPMLIST_PART set
                       is_valid = 'N'
                       ,msg = 'check the init_wrk_date length in excel file'
                   where sn = c1.sn
                   ;
               end if;
               
               begin
                   select to_date(c1.init_wrk_date,'yyyymmdd')
                   into v_init_wrk_date
                   from dual
                   ;
               exception
                  when others then
                      update TYPMLIST_PART set
                           is_valid = 'N'
                           ,msg = 'init_wrk_date is not date format.  check the init_wrk_date in excel file'
                       where sn = c1.sn
                       ;
               end;
               
    end loop;
    
    
    
    
    
    
    for c1 in c_excel_data loop
        if c1.is_valid = 'Y' then
                
                select equip_id, dept_id, eqloc_id
                into v_equip_id, v_dept_id, v_eqloc_id
                from TAEQUIPMENT
                where comp_no = v_comp_no
                     and excel_no = c1.excel_no
                     and rownum = 1
                ;
                
                select sqapm_id.nextval into v_pm_id from dual;
                
                v_pm_no := v_pm_id;
                
                select count(*)
                into v_count
                from tacdsysd
                where list_type = 'PM_CATEG'
                    and description = c1.pm_categ_desc
               ;
               if v_count > 0 then
                    select cdsysd_no
                    into v_pm_categ
                    from tacdsysd
                    where list_type = 'PM_CATEG'
                        and description = c1.pm_categ_desc
                        and rownum = 1
                   ;
               else
                   v_pm_categ := '';
               end if;
               
                if c1.work_id is not null then
                       select count(*)
                       into v_count
                       from TAEMP
                       where comp_no = v_comp_no
                           and emp_no = c1.work_id
                       ;
                       
                       if v_count > 0 then
                           select emp_id
                           into v_emp_id
                           from TAEMP
                           where comp_no = v_comp_no
                               and emp_no = c1.work_id
                           ;
                       else
                           v_emp_id := null;
                       end if;
                else
                     v_emp_id := null;
                end if;
                
                 if c1.wkctr_desc is not null then
                       select count(*)
                       into v_count
                       from TAWKCTR
                       where comp_no = v_comp_no
                           and trim(description) = c1.wkctr_desc
                       ;
                       
                       if v_count > 0 then
                           select wkctr_id
                           into v_wkctr_id
                           from TAWKCTR
                           where comp_no = v_comp_no
                               and trim(description) = c1.wkctr_desc
                           ;
                       else
                           v_wkctr_id := null;
                       end if;
                else
                     v_emp_id := null;
                end if;
                
                insert into TAPMLST(
                        comp_no, pm_id, pm_no, description, equip_id
                        ,dept_id, pm_type, schedule_type, cycle
                        ,period_type,  wo_res_bef
                        ,init_wrk_date, last_sch_date, is_active, remark
                        ,emp_id, wo_type, eqloc_id
                        ,pm_categ,wkctr_id
                    ) values(
                        v_comp_no, v_pm_id , v_pm_no, c1.description, v_equip_id
                        ,v_dept_id, 'RPL', 'T',c1.cycle
                        ,c1.period_type, c1.wo_res_bef
                        ,c1.init_wrk_date, c1.init_wrk_date, 'Y', c1.remark
                        ,v_emp_id ,'PMW', v_eqloc_id
                        ,v_pm_categ,v_wkctr_id
                    );
                    
                    insert into tapmequip(comp_no, pmequip_id, pm_id, equip_id)
                    values(v_comp_no, sqapmequip_id.nextval, v_pm_id, v_equip_id)
                    ;
                    
                    if c1.pno1 is not null and c1.qty1 > 0 then
                    
                       select count(*)
                       into v_count
                       from taparts
                       where comp_no = v_comp_no
                           and part_no = c1.pno1
                       ;
                       
                       if v_count > 0 then
                       
                           select part_id
                           into v_part_id
                           from taparts
                           where comp_no = v_comp_no
                               and part_no = c1.pno1
                           ;
                       
                            insert into tapmpart(
                                comp_no, pm_part_id, pm_id, part_id, use_qty
                            ) values(
                                v_comp_no, sqapm_part_id.nextval, v_pm_id, v_part_id, c1.qty1
                            );
                            
                            select count(*)
                            into v_count
                            from TAEQPART
                            where comp_no = v_comp_no
                                and equip_id = v_equip_id
                                and part_id = v_part_id
                            ;
                            
                            if v_count = 0 then
                                insert into TAEQPART(comp_no, eqpart_id, equip_id, part_id, consist_qty)
                                values(v_comp_no, sqaeqpart_id.nextval, v_equip_id, v_part_id, c1.qty1);
                            end if;
                            
                       end if;
                    end if;
                    
                    
                    if c1.pno2 is not null and c1.qty2 > 0then
                    
                       select count(*)
                       into v_count
                       from taparts
                       where comp_no = v_comp_no
                           and part_no = c1.pno2
                       ;
                       
                       if v_count > 0 then
                       
                           select part_id
                           into v_part_id
                           from taparts
                           where comp_no = v_comp_no
                               and part_no = c1.pno2
                           ;
                       
                            insert into tapmpart(
                                comp_no, pm_part_id, pm_id, part_id, use_qty
                            ) values(
                                v_comp_no, sqapm_part_id.nextval, v_pm_id, v_part_id, c1.qty2
                            );
                            
                            select count(*)
                            into v_count
                            from TAEQPART
                            where comp_no = v_comp_no
                                and equip_id = v_equip_id
                                and part_id = v_part_id
                            ;
                            
                            if v_count = 0 then
                                insert into TAEQPART(comp_no, eqpart_id, equip_id, part_id, consist_qty)
                                values(v_comp_no, sqaeqpart_id.nextval, v_equip_id, v_part_id, c1.qty2);
                            end if;
                            
                            
                       end if;
                    end if;
                    
                    
                    if c1.pno3 is not null and c1.qty3 > 0then
                    
                       select count(*)
                       into v_count
                       from taparts
                       where comp_no = v_comp_no
                           and part_no = c1.pno3
                       ;
                       
                       if v_count > 0 then
                       
                           select part_id
                           into v_part_id
                           from taparts
                           where comp_no = v_comp_no
                               and part_no = c1.pno3
                           ;
                       
                            insert into tapmpart(
                                comp_no, pm_part_id, pm_id, part_id, use_qty
                            ) values(
                                v_comp_no, sqapm_part_id.nextval, v_pm_id, v_part_id, c1.qty3
                            );
                            
                            select count(*)
                            into v_count
                            from TAEQPART
                            where comp_no = v_comp_no
                                and equip_id = v_equip_id
                                and part_id = v_part_id
                            ;
                            
                            if v_count = 0 then
                                insert into TAEQPART(comp_no, eqpart_id, equip_id, part_id, consist_qty)
                                values(v_comp_no, sqaeqpart_id.nextval, v_equip_id, v_part_id, c1.qty3);
                            end if;
                            
                       end if;
                    end if;
                    
                    
                    
                     if c1.pno4 is not null and c1.qty4 > 0then
                    
                       select count(*)
                       into v_count
                       from taparts
                       where comp_no = v_comp_no
                           and part_no = c1.pno4
                       ;
                       
                       if v_count > 0 then
                       
                           select part_id
                           into v_part_id
                           from taparts
                           where comp_no = v_comp_no
                               and part_no = c1.pno4
                           ;
                       
                            insert into tapmpart(
                                comp_no, pm_part_id, pm_id, part_id, use_qty
                            ) values(
                                v_comp_no, sqapm_part_id.nextval, v_pm_id, v_part_id, c1.qty4
                            );
                            
                            select count(*)
                            into v_count
                            from TAEQPART
                            where comp_no = v_comp_no
                                and equip_id = v_equip_id
                                and part_id = v_part_id
                            ;
                            
                            if v_count = 0 then
                                insert into TAEQPART(comp_no, eqpart_id, equip_id, part_id, consist_qty)
                                values(v_comp_no, sqaeqpart_id.nextval, v_equip_id, v_part_id, c1.qty4);
                            end if;
                            
                       end if;
                    end if;
                    
                    
                    if c1.pno5 is not null and c1.qty5 > 0then
                    
                       select count(*)
                       into v_count
                       from taparts
                       where comp_no = v_comp_no
                           and part_no = c1.pno5
                       ;
                       
                       if v_count > 0 then
                       
                           select part_id
                           into v_part_id
                           from taparts
                           where comp_no = v_comp_no
                               and part_no = c1.pno5
                           ;
                       
                            insert into tapmpart(
                                comp_no, pm_part_id, pm_id, part_id, use_qty
                            ) values(
                                v_comp_no, sqapm_part_id.nextval, v_pm_id, v_part_id, c1.qty5
                            );
                            
                            select count(*)
                            into v_count
                            from TAEQPART
                            where comp_no = v_comp_no
                                and equip_id = v_equip_id
                                and part_id = v_part_id
                            ;
                            
                            if v_count = 0 then
                                insert into TAEQPART(comp_no, eqpart_id, equip_id, part_id, consist_qty)
                                values(v_comp_no, sqaeqpart_id.nextval, v_equip_id, v_part_id, c1.qty5);
                            end if;
                            
                       end if;
                    end if;
                    
        end if;
        
    end loop;
    
    
    
end;
/
