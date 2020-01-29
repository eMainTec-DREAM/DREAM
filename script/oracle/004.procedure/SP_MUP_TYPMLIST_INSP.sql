CREATE OR REPLACE PROCEDURE SP_MUP_TYPMLIST_INSP (
    v_comp_no varchar2 DEFAULT '100'
) IS


-- truncate table TYPMLIST_INSP 

-- delete from tapmequip where pm_id in (select pm_id from TAPMLST where pm_type = 'INS')
-- delete from TAPMPOINT where pm_id in (select pm_id from TAPMLST where pm_type = 'INS')
-- delete from TAPMSCHED where pm_id in (select pm_id from TAPMLST where pm_type = 'INS')
-- delete from TAPMLST where pm_type = 'INS'

-- exec SP_MUP_TYPMLIST_INSP('120')


   
   v_count     number(4);
   v_item_no varchar2(20);
   v_equip_id number(18);
   v_eqloc_id number(18);
   v_dept_id number(18);
   v_emp_id number(18);
   v_wkctr_id number(18);
   v_pm_id number(18);
   v_pm_no varchar2(30);
   v_description varchar2(512);
   v_ord_no number(4);
   v_emp_no varchar2(20);
   --v_wkctr_no varchar2(20);
   v_pm_check_type varchar2(20);
   v_init_wrk_date date;

   CURSOR c_dept_data IS
            SELECT b.comp_no, b.dept_id
            FROM TYPMLIST_INSP a
                   ,TAEQUIPMENT b
            WHERE 1=1
                AND a.excel_no = b.excel_no
                AND b.comp_no = v_comp_no
            GROUP BY b.comp_no, b.dept_id
            ;

            
   CURSOR c_pmi_list IS
        SELECT excel_no, cycle, period_type, replace(replace(trim(init_wrk_date),'-',''),'.','')   as init_wrk_date
                        ,wkctr_desc as wkctr_desc
        FROM TYPMLIST_INSP
        WHERE 1=1
            AND cycle IS NOT NULL
            AND period_type IS NOT NULL
            AND init_wrk_date IS NOT NULL
            AND excel_no IS NOT NULL
        GROUP BY excel_no, cycle, period_type, replace(replace(trim(init_wrk_date),'-',''),'.',''),wkctr_desc
   ;
   
   
   

    CURSOR c_pmi_dtl(p_excel_no varchar2, p_cycle varchar2, p_period_type varchar2, p_init_wrk_date varchar2) IS      
         SELECT 
                         sn
                        ,excel_no
                        ,item_no
                        ,description
                        ,cycle
                        ,period_type
                        ,work_id  as work_id
                        ,work_name  as work_name
                        ,replace(replace(trim(init_wrk_date),'-',''),'.','')  as init_wrk_date
                        ,asmb
                        ,check_point
                        ,check_method
                        ,fit_basis
                        ,pm_check_type
                        ,check_min
                        ,chck_basis_val
                        ,check_max
                        ,uom
                        ,REMARK
                        ,14 as wo_res_bef
                FROM TYPMLIST_INSP a
            WHERE 1=1
                AND excel_no = p_excel_no
                AND cycle = p_cycle
                AND period_type = p_period_type
                AND replace(replace(trim(init_wrk_date),'-',''),'.','')  = p_init_wrk_date
            ORDER BY sn
           ;

                                
BEGIN


  FOR c1 IN c_dept_data LOOP
    
        DELETE FROM TAPMSCHED 
        WHERE comp_no = c1.comp_no
            AND pm_id IN ( SELECT pm_id
                                  FROM TAPMLST
                                  WHERE comp_no = c1.comp_no
                                      AND dept_id = c1.dept_id
                                      AND pm_type = 'INS'
                                );
                                
                                
        
        DELETE FROM tapmequip 
        WHERE comp_no = c1.comp_no
            AND pm_id IN ( SELECT pm_id
                                  FROM TAPMLST
                                  WHERE comp_no = c1.comp_no
                                      AND dept_id = c1.dept_id
                                      AND pm_type = 'INS'
                                );
                                
        
        DELETE FROM TAPMPOINT 
        WHERE comp_no = c1.comp_no
            AND pm_id IN ( SELECT pm_id
                                  FROM TAPMLST
                                  WHERE comp_no = c1.comp_no
                                      AND dept_id = c1.dept_id
                                      AND pm_type = 'INS'
                                );
        
        DELETE FROM TAPMLST 
        WHERE comp_no = c1.comp_no 
            AND  dept_id = c1.dept_id
            AND pm_type = 'INS';
    
    END LOOP;
    
    FOR c1 IN c_pmi_list LOOP
    
               SELECT COUNT(*)
               INTO v_count
               FROM TAEQUIPMENT
               WHERE comp_no = v_comp_no
                   AND excel_no = c1.excel_no
               ;
               
               IF v_count > 0 THEN
                    
                    SELECT equip_id, dept_id, eqloc_id
                    INTO v_equip_id, v_dept_id, v_eqloc_id
                    FROM TAEQUIPMENT
                    WHERE comp_no = v_comp_no
                        AND excel_no = c1.excel_no
                        AND ROWNUM = 1
                    ;
                    
                    SELECT description
                    INTO v_description
                    FROM TYPMLIST_INSP
                    WHERE 1=1
                        AND excel_no = c1.excel_no
                        AND cycle = c1.cycle
                        AND period_type = c1.period_type
                        AND replace(replace(trim(init_wrk_date),'-',''),'.','')  = c1.init_wrk_date
                        AND ROWNUM = 1
                    ;
                    
                    select count(*)
                    into v_count
                    from TAWKCTR
                    where comp_no = v_comp_no
                        and TRIM(description) = TRIM(c1.wkctr_desc)
                    ;
                      
                    if v_count > 0 then
                        select wkctr_id
                        into v_wkctr_id
                        from TAWKCTR
                        where comp_no = v_comp_no
                            and TRIM(description) = TRIM(c1.wkctr_desc)
                            AND rownum = 1
                        ;
                    else
                        v_wkctr_id := null;
                    end if;
                    
                    
                    SELECT max(work_id) as work_id
                    INTO v_emp_no
                    FROM TYPMLIST_INSP
                    WHERE 1=1
                        AND excel_no = c1.excel_no
                        AND cycle = c1.cycle
                        AND period_type = c1.period_type
                        AND replace(replace(trim(init_wrk_date),'-',''),'.','')  = c1.init_wrk_date
                        AND ROWNUM = 1
                    ;
                    
                    select count(*)
                    into v_count
                    from TAEMP
                    where comp_no = v_comp_no
                        and emp_no = v_emp_no
                    ;
                      
                    if v_count > 0 then
                        select emp_id
                        into v_emp_id
                        from TAEMP
                        where comp_no = v_comp_no
                            and emp_no = v_emp_no
                        ;
                    else
                        v_emp_id := null;
                    end if;
                    
                    
                    
                    
                    v_count := 1;
                    begin
                       select to_date(c1.init_wrk_date,'yyyymmdd')
                       into v_init_wrk_date
                       from dual
                       ;
                   exception
                      when others then
                          v_count := 0;
                   end;
                   
                   if v_count > 0 then 
               
                            SELECT sqapm_id.NEXTVAL
                            INTO v_pm_id 
                            FROM dual
                            ;
                            
                            v_pm_no := v_pm_id;
                            
                             INSERT INTO TAPMLST(
                                comp_no, pm_id, pm_no, description, equip_id
                                ,dept_id, pm_type, schedule_type, cycle
                                ,period_type,  wo_res_bef
                                ,init_wrk_date, last_sch_date, is_active
                                ,emp_id, wo_type, eqloc_id, wkctr_id
                            ) VALUES(
                                v_comp_no, v_pm_id , v_pm_no, v_description, v_equip_id
                                ,v_dept_id, 'INS', 'T',c1.cycle
                                ,c1.period_type,14
                                ,c1.init_wrk_date, c1.init_wrk_date, 'Y'
                                ,v_emp_id, 'PMI', v_eqloc_id, v_wkctr_id
                            );
                            
                            INSERT INTO TAPMEQUIP(
                                comp_no, pmequip_id, pm_id
                                ,equip_id, eqctg_id, description
                            ) VALUES(
                                v_comp_no,  SQAPMEQUIP_ID.NEXTVAL,  v_pm_id
                                ,v_equip_id, '', ''
                            );
                            
                              v_ord_no := 0;
                              
                                FOR c2 IN c_pmi_dtl(c1.excel_no, c1.cycle, c1.period_type, c1.init_wrk_date) LOOP

                                    v_ord_no := v_ord_no + 1;
                                    v_pm_check_type := CASE WHEN c2.pm_check_type = 'Sense'  THEN 'SEN' ELSE 'VAL' END ;
                            
                                    INSERT INTO TAPMPOINT(
                                        comp_no, pm_point_id, pm_id, step_num
                                        ,check_point, check_method, fit_basis, check_type
                                        ,check_min, check_basis_val, check_max, uom
                                        ,is_active
                                        ,REMARK
                                    )VALUES(
                                        v_comp_no, sqapm_point_id.NEXTVAL, v_pm_id, v_ord_no
                                        ,c2.check_point, c2.check_method, c2.fit_basis, v_pm_check_type
                                        ,c2.check_min, c2.chck_basis_val, c2.check_max, c2.uom
                                        ,'Y'
                                        ,c2.remark
                                    );
                                    
                                    
                               END LOOP;
                       end if;
                    
               END IF;
                    

               
    END LOOP;
    
    
    
END;
/
