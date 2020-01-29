CREATE PROCEDURE [dbo].[SP_MUP_TYPMLIST_INSP] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare  @v_count as bigint
    
    
    declare     
             @v1_item_no           nvarchar(20)
            ,@v1_equip_id          numeric(18)
            ,@v1_eqloc_id          numeric(18)
            ,@v1_dept_id           numeric(18)
            ,@v1_emp_id            numeric(18)
            ,@v1_wkctr_id          numeric(18)
            ,@v1_pm_id             numeric(18)
            ,@v1_pm_no             nvarchar(30)
            ,@v1_description       nvarchar(512)
            ,@v1_ord_no            numeric(4)
            ,@v1_emp_no            nvarchar(20)
            ,@v1_pm_check_type     nvarchar(20)
            ,@v1_init_wrk_date     date


    DECLARE  
            --|| for c_dept_data CURSOR
             @c1_comp_no          nvarchar(6)
            ,@c1_dept_id          numeric(18)
            
            
            --|| for c_pmi_list CURSOR
            ,@c2_excel_no        nvarchar(30)
            ,@c2_cycle           nvarchar(30)
            ,@c2_period_type     nvarchar(30)
            ,@c2_init_wrk_date   nvarchar(30)
            ,@c2_wkctr_desc      nvarchar(200)
            
                    
    DECLARE c_dept_data CURSOR FOR
        SELECT 
               b.comp_no
              ,b.dept_id
         FROM 
              TYPMLIST_INSP a
             ,TAEQUIPMENT   b
        WHERE 1=1
         AND a.excel_no = b.excel_no
         AND b.comp_no  = @v_comp_no
     GROUP BY 
             b.comp_no
            ,b.dept_id
        ;
        

    DECLARE c_pmi_list CURSOR FOR
        SELECT 
               excel_no
              ,cycle
              ,period_type
              ,replace(replace(rtrim(init_wrk_date),'-',''),'.','')   as init_wrk_date
              ,wkctr_desc as wkctr_desc
        FROM TYPMLIST_INSP
        WHERE 1=1
          AND cycle         IS NOT NULL
          AND period_type   IS NOT NULL
          AND init_wrk_date IS NOT NULL
          AND excel_no      IS NOT NULL
     GROUP BY 
              excel_no
             ,cycle
             ,period_type
             ,replace(replace(rtrim(init_wrk_date),'-',''),'.','')
             ,wkctr_desc
   ;    
     
    OPEN c_dept_data
    FETCH NEXT FROM c_dept_data  INTO @c1_comp_no
                                     ,@c1_dept_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
          -----------------------------------------------------------------------------------------------------------------------------------------
          -----------------------------------------------------------------------------------------------------------------------------------------
          
      DELETE FROM TAPMSCHED 
        WHERE comp_no = @c1_comp_no
            AND pm_id IN ( SELECT pm_id
                                  FROM TAPMLST
                                  WHERE comp_no = @c1_comp_no
                                      AND dept_id = @c1_dept_id
                                      AND pm_type = 'INS'
                                );
                                
                                
        
        DELETE FROM tapmequip 
        WHERE comp_no = @c1_comp_no
            AND pm_id IN ( SELECT pm_id
                                  FROM TAPMLST
                                  WHERE comp_no = @c1_comp_no
                                      AND dept_id = @c1_dept_id
                                      AND pm_type = 'INS'
                                );
                                
        
        DELETE FROM TAPMPOINT 
        WHERE comp_no = @c1_comp_no
            AND pm_id IN ( SELECT pm_id
                                  FROM TAPMLST
                                  WHERE comp_no = @c1_comp_no
                                      AND dept_id = @c1_dept_id
                                      AND pm_type = 'INS'
                                );
        
        DELETE FROM TAPMLST 
        WHERE comp_no = @c1_comp_no 
            AND  dept_id = @c1_dept_id
            AND pm_type = 'INS';          
          
              
          -----------------------------------------------------------------------------------------------------------------------------------------
          -----------------------------------------------------------------------------------------------------------------------------------------
        FETCH NEXT FROM c_dept_data INTO @c1_comp_no
                                        ,@c1_dept_id
          END
      CLOSE c_dept_data
      DEALLOCATE c_dept_data
     


    OPEN c_pmi_list
    FETCH NEXT FROM c_pmi_list  INTO @c2_excel_no        
                                    ,@c2_cycle          
                                    ,@c2_period_type    
                                    ,@c2_init_wrk_date   
                                    ,@c2_wkctr_desc      
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
          -----------------------------------------------------------------------------------------------------------------------------------------
          -----------------------------------------------------------------------------------------------------------------------------------------
           SELECT @v_count  = COUNT(*)
               FROM TAEQUIPMENT
               WHERE comp_no = @v_comp_no
                   AND excel_no = @c2_excel_no
               ;
               
               IF @v_count > 0 
                    begin  --1      
                    SELECT top 1 
                          @v1_equip_id  = equip_id
                        , @v1_dept_id   = dept_id
                        , @v1_eqloc_id  = eqloc_id
                    FROM TAEQUIPMENT
                    WHERE comp_no    = @v_comp_no
                        AND excel_no = @c2_excel_no

                    ;
                    
                    SELECT top 1
                           @v1_description = description
                    FROM TYPMLIST_INSP
                    WHERE 1=1
                        AND excel_no    = @c2_excel_no
                        AND cycle       = @c2_cycle
                        AND period_type = @c2_period_type
                        AND replace(replace(rtrim(init_wrk_date),'-',''),'.','')  = @c2_init_wrk_date

                    ;
                    
                    select @v_count  = count(*)
                    from TAWKCTR
                    where comp_no = @v_comp_no
                        and RTRIM(description) = RTRIM(@c2_wkctr_desc)
                    ;
                    end  --1

                    begin  --2
                    if @v_count > 0 
                        select top 1
                               @v1_wkctr_id = wkctr_id
                        from TAWKCTR
                        where comp_no = @v_comp_no
                            and RTRIM(description) = RTRIM(@c2_wkctr_desc)
                        ;
                    else
                        set @v1_wkctr_id = null

                    end  --2
                    
                    begin  --3
                    SELECT top 1  
                           @v1_emp_no  = max(work_id) 
                    FROM TYPMLIST_INSP
                    WHERE 1=1
                        AND excel_no    = @c2_excel_no
                        AND cycle       = @c2_cycle
                        AND period_type = @c2_period_type
                        AND replace(replace(rtrim(init_wrk_date),'-',''),'.','')  = @c2_init_wrk_date
                    ;
                    
                    select @v_count  = count(*)
                    from TAEMP
                    where 1=1
                      and comp_no  = @v_comp_no
                      and emp_no   = @v1_emp_no
                    ;
                    end  --3
                    
                    begin  --4
                    if @v_count > 0 
                        select @v1_emp_id  = emp_id
                        from TAEMP
                        where 1=1
                          and comp_no = @v_comp_no
                          and emp_no  = @v1_emp_no
                        ;
                    else
                        set  @v1_emp_id =  null
                    end --4
                    
                  
                    
                    set @v_count = 1


                    begin
                       select @v1_init_wrk_date =  cast(@c2_init_wrk_date as datetime)
                       --to_date(@c2_init_wrk_date,'yyyymmdd')   --원본

                       --and (datediff(day,GETDATE(), cast(a.sched_date as datetime)))  --참조
                       --and ( to_date(a.sched_date,'yyyymmdd') - trunc(sysdate) )      --참조

                       ;
                     IF @v1_init_wrk_date is null

                          set  @v_count = 0
                     end;
                    
                                       
                   if @v_count > 0  
                   begin --5
                            SELECT @v1_pm_id = sqapm_id.NEXTVAL
                            FROM dual
                            ;
                            
                            set @v1_pm_no = @v1_pm_id
                            
                             INSERT INTO TAPMLST(
                                comp_no, pm_id, pm_no, description, equip_id
                                ,dept_id, pm_type, schedule_type, cycle
                                ,period_type,  wo_res_bef
                                ,init_wrk_date, last_sch_date, is_active
                                ,emp_id, wo_type, eqloc_id, wkctr_id
                            ) VALUES(
                                @v_comp_no, @v1_pm_id , @v1_pm_no, @v1_description, @v1_equip_id
                                ,@v1_dept_id, 'INS', 'T',@c2_cycle
                                ,@c2_period_type,14
                                ,@c2_init_wrk_date, @c2_init_wrk_date, 'Y'
                                ,@v1_emp_id, 'PMI', @v1_eqloc_id, @v1_wkctr_id
                            );
                            
                            INSERT INTO TAPMEQUIP(
                                comp_no, pmequip_id, pm_id
                                ,equip_id, eqctg_id, description
                            ) VALUES(
                                @v_comp_no,  NEXT VALUE FOR  SQAPMEQUIP_ID,  @v1_pm_id
                                ,@v1_equip_id, '', ''
                            );
                            
                              set  @v1_ord_no = 0
 
                                --|| for c_pmi_dtl CURSOR
                                DECLARE
                                         @c3_sn              nvarchar(30)
                                        ,@c3_excel_no        nvarchar(30)
                                        ,@c3_item_no         nvarchar(300)
                                        ,@c3_description     nvarchar(300)
                                        ,@c3_cycle           nvarchar(30)
                                        ,@c3_period_type     nvarchar(30)
                                        ,@c3_work_id         nvarchar(30)
                                        ,@c3_work_name       nvarchar(100)
                                        ,@c3_init_wrk_date   nvarchar(30)
                                        ,@c3_asmb            nvarchar(50)
                                        ,@c3_check_point     nvarchar(200)
                                        ,@c3_check_method    nvarchar(200)
                                        ,@c3_fit_basis       nvarchar(200)
                                        ,@c3_pm_check_type   nvarchar(20)
                                        ,@c3_check_min       nvarchar(30)
                                        ,@c3_chck_basis_val  nvarchar(30)
                                        ,@c3_check_max       nvarchar(30)
                                        ,@c3_uom             nvarchar(30)
                                        ,@c3_REMARK          nvarchar(300)
                                        ,@c3_wo_res_bef      nvarchar(10)

                                DECLARE c_pmi_dtl CURSOR FOR
                                      SELECT 
                                             sn
                                            ,excel_no
                                            ,item_no
                                            ,description
                                            ,cycle
                                            ,period_type
                                            ,work_id  as work_id
                                            ,work_name  as work_name
                                            ,replace(replace(rtrim(init_wrk_date),'-',''),'.','')  as init_wrk_date
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
                                         AND excel_no    = @c2_excel_no
                                         AND cycle       = @c2_cycle
                                         AND period_type = @c2_period_type
                                         AND replace(replace(rtrim(init_wrk_date),'-',''),'.','')  = @c2_init_wrk_date
                                        ORDER BY sn
                                       ;                             


                                        OPEN c_pmi_dtl
                                        FETCH NEXT FROM c_pmi_dtl    INTO 
                                             @c3_sn              
                                            ,@c3_excel_no        
                                            ,@c3_item_no         
                                            ,@c3_description     
                                            ,@c3_cycle           
                                            ,@c3_period_type     
                                            ,@c3_work_id         
                                            ,@c3_work_name       
                                            ,@c3_init_wrk_date   
                                            ,@c3_asmb            
                                            ,@c3_check_point     
                                            ,@c3_check_method    
                                            ,@c3_fit_basis       
                                            ,@c3_pm_check_type   
                                            ,@c3_check_min       
                                            ,@c3_chck_basis_val  
                                            ,@c3_check_max       
                                            ,@c3_uom             
                                            ,@c3_REMARK          
                                            ,@c3_wo_res_bef 
    
                                          WHILE (@@FETCH_STATUS=0)
                                              BEGIN
                                              --------------------------------------------------------------------------------------------
                                              --------------------------------------------------------------------------------------------
                                               set @v1_ord_no = @v1_ord_no + 1

                                               IF @c3_pm_check_type = 'Sense'
                                                   set @v1_pm_check_type = 'SEN'
                                               ELSE
                                                   set @v1_pm_check_type = 'VAL'
                               
                                               INSERT INTO TAPMPOINT
                                                   (
                                                   comp_no
                                                  ,pm_point_id
                                                  ,pm_id
                                                  ,step_num
                                                  ,check_point
                                                  ,check_method
                                                  ,fit_basis
                                                  ,check_type
                                                  ,check_min
                                                  ,check_basis_val
                                                  ,check_max
                                                  ,uom
                                                  ,is_active
                                                  ,REMARK
                                                 )
                                                VALUES
                                                 (
                                                  @v_comp_no
                                                 ,NEXT VALUE FOR sqapm_point_id
                                                 ,@v1_pm_id
                                                 ,@v1_ord_no
                                                 ,@c3_check_point
                                                 ,@c3_check_method
                                                 ,@c3_fit_basis
                                                 ,@v1_pm_check_type
                                                 ,@c3_check_min
                                                 ,@c3_chck_basis_val
                                                 ,@c3_check_max
                                                 ,@c3_uom
                                                 ,'Y'
                                                 ,@c3_remark
                                                  );
                                    


                                              --------------------------------------------------------------------------------------------
                                              --------------------------------------------------------------------------------------------
                                            FETCH NEXT FROM c_pmi_dtl INTO @c1_comp_no
                                                                            ,@c1_dept_id
                                              END
                                          CLOSE c_pmi_dtl
                                          DEALLOCATE c_pmi_dtl

                            end  --5
                    
                        
          -----------------------------------------------------------------------------------------------------------------------------------------
          -----------------------------------------------------------------------------------------------------------------------------------------
    FETCH NEXT FROM c_pmi_list INTO @c2_excel_no        
                                ,@c2_cycle          
                                ,@c2_period_type    
                                ,@c2_init_wrk_date   
                                ,@c2_wkctr_desc  
          END
      CLOSE c_pmi_list
      DEALLOCATE c_pmi_list
   