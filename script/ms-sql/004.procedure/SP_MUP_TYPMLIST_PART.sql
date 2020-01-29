CREATE PROCEDURE [dbo].[SP_MUP_TYPMLIST_PART] (
      @v1_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    DECLARE
             @v1_count          bigint
            ,@v1_item_no        nvarchar(20)
            ,@v1_equip_id       numeric(18)
            ,@v1_dept_id        numeric(18)
            ,@v1_emp_id         numeric(18)
            ,@v1_wkctr_id       numeric(18)
            ,@v1_pm_id          numeric(18)
            ,@v1_pm_no          nvarchar(30)
            ,@v1_eqloc_id       numeric(18) 
            ,@v1_part_id        numeric(18)
            ,@v1_init_wrk_date  date
            ,@v1_pm_categ       nvarchar(20)    
            
            ,@ERROR_MSG        nvarchar(1024)

           --|| ===================================
           --|| C1 커서 c_dept_data   
            ,@c1_comp_no      nvarchar(6)
            ,@c1_dept_id      numeric(18)
            ,@c1_ins_cnt      numeric(4)

           --|| ===================================
           --|| C2 커서 c_excel_data
            ,@c2_sn                nvarchar(30)        --sn                                                                           
            ,@c2_excel_no          nvarchar(30)        --rtrim(excel_no)                                              
            ,@c2_item_no           nvarchar(300)       --rtrim(item_no)                                                
            ,@c2_description       nvarchar(300)       --rtrim(description)                                            
            ,@c2_pm_categ_desc     nvarchar(100)       --rtrim(pm_type)                                                
            ,@c2_measure_tools     nvarchar(100)       --rtrim(measure_tools)                                         
            ,@c2_cycle             nvarchar(30)        --rtrim(cycle)                                                 
            ,@c2_period_type       nvarchar(30)        --rtrim(period_type)                                           
            ,@c2_wo_res_bef        nvarchar(30)        --rtrim(wo_res_bef)                                            
            ,@c2_work_id           nvarchar(30)        --rtrim(work_id)                                               
            ,@c2_work_name         nvarchar(100)       --rtrim(work_name)                                             
            ,@c2_wkctr_desc        nvarchar(200)       --rtrim(wkctr_desc)                                            
            ,@c2_asmb              nvarchar(30)        --rtrim(asmb)                                                  
            ,@c2_init_wrk_date     nvarchar(30)        --replace(replace(rtrim(init_wrk_date),'-',''),'.','')  
            ,@c2_remark            nvarchar(300)       --rtrim(remark)                                                
            ,@c2_pno1              nvarchar(30)        --rtrim(pno1)                                                  
            ,@c2_pname1            nvarchar(100)       --rtrim(pname1)                                                
            ,@c2_pspec1            nvarchar(100)       --rtrim(pspec1)                                                
            ,@c2_qty1              numeric(21,3)       --ISNULL(qty1,0)                                               
            ,@c2_pno2              nvarchar(30)        --rtrim(pno2)                                                  
            ,@c2_pname2            nvarchar(100)       --rtrim(pname2)                                                
            ,@c2_pspec2            nvarchar(100)       --rtrim(pspec2)                                                
            ,@c2_qty2              nvarchar(10)        --ISNULL(qty2,0)                                               
            ,@c2_pno3              nvarchar(30)        --rtrim(pno3)                                                  
            ,@c2_pname3            nvarchar(100)       --rtrim(pname3)                                                
            ,@c2_pspec3            nvarchar(100)       --rtrim(pspec3)                                                
            ,@c2_qty3              numeric(21,3)       --ISNULL(qty3,0)                                               
            ,@c2_pno4              nvarchar(30)        --rtrim(pno4)                                                  
            ,@c2_pname4            nvarchar(100)       --rtrim(pname4)                                                
            ,@c2_pspec4            nvarchar(100)       --rtrim(pspec4)                                                
            ,@c2_qty4              numeric(21,3)       --ISNULL(qty4,0)                                               
            ,@c2_pno5              nvarchar(30)        --rtrim(pno5)                                                  
            ,@c2_pname5            nvarchar(100)       --rtrim(pname5)                                                
            ,@c2_pspec5            nvarchar(100)       --rtrim(pspec5)                                                
            ,@c2_qty5              numeric(21,3)       --ISNULL(qty5,0)                                               
            ,@c2_is_valid          nvarchar(10)        --is_valid    

   --|| ===================================
   --|| C1 커서 c_dept_data    
    DECLARE c_dept_data CURSOR FOR
        select  b.comp_no
               ,b.dept_id
               ,count(*) as ins_cnt
          from TYPMLIST_PART a
              ,taequipment b
        where 1=1
            and a.excel_no = b.excel_no
            and b.comp_no  = @v1_comp_no
       group by 
                b.comp_no
               ,b.dept_id
        ;         
        

       --|| ===================================
       --|| C2 커서 c_excel_data
      DECLARE c_excel_data CURSOR FOR
                select 
                           sn                                                     as sn
                          ,rtrim(excel_no)                                        as excel_no 
                          ,rtrim(item_no)                                         as item_no 
                          ,rtrim(description)                                     as description 
                          ,rtrim(pm_type)                                         as pm_categ_desc 
                          ,rtrim(measure_tools)                                   as measure_tools 
                          ,rtrim(cycle)                                           as cycle 
                          ,rtrim(period_type)                                     as period_type 
                          ,rtrim(wo_res_bef)                                      as wo_res_bef
                          ,rtrim(work_id)                                         as work_id
                          ,rtrim(work_name)                                       as work_name
                          ,rtrim(wkctr_desc)                                      as wkctr_desc
                          ,rtrim(asmb)                                            as asmb
                          ,replace(replace(rtrim(init_wrk_date),'-',''),'.','')   as init_wrk_date
                          ,rtrim(remark)                                          as remark 
                          ,rtrim(pno1)                                            as pno1 
                          ,rtrim(pname1)                                          as pname1
                          ,rtrim(pspec1)                                          as pspec1
                          ,ISNULL(qty1,0)                                         as qty1
                          ,rtrim(pno2)                                            as pno2
                          ,rtrim(pname2)                                          as pname2
                          ,rtrim(pspec2)                                          as pspec2
                          ,ISNULL(qty2,0)                                         as qty2
                          ,rtrim(pno3)                                            as pno3
                          ,rtrim(pname3)                                          as pname3
                          ,rtrim(pspec3)                                          as pspec3
                          ,ISNULL(qty3,0)                                         as qty3
                          ,rtrim(pno4)                                            as pno4
                          ,rtrim(pname4)                                          as pname4
                          ,rtrim(pspec4)                                          as pspec4
                          ,ISNULL(qty4,0)                                         as qty4
                          ,rtrim(pno5)                                            as pno5
                          ,rtrim(pname5)                                          as pname5
                          ,rtrim(pspec5)                                          as pspec5
                          ,ISNULL(qty5,0)                                         as qty5
                          ,is_valid                                               as is_valid
                from TYPMLIST_PART a
                where 1=1
                order by sn    ;         

     
    OPEN c_dept_data
    FETCH NEXT FROM c_dept_data  INTO 
                         @c1_comp_no
                        ,@c1_dept_id
                        ,@c1_ins_cnt
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
                begin
          
                delete from TAPMSCHED 
                where comp_no = @c1_comp_no
                    and pm_id in ( select pm_id
                                          from TAPMLST
                                          where 1=1
                                              and comp_no = @c1_comp_no
                                              and dept_id = @c1_dept_id
                                              and pm_type <> 'INS'
                                        );
                                        
                                        
                
                delete from tapmequip 
                where comp_no = @c1_comp_no
                    and pm_id in ( select pm_id
                                          from TAPMLST
                                          where 1=1
                                              and comp_no = @c1_comp_no
                                              and dept_id = @c1_dept_id
                                              and pm_type <> 'INS'
                                        );
                
                delete from tapmpart 
                where 1=1
                    and comp_no = @c1_comp_no
                    and pm_id in ( select pm_id
                                          from TAPMLST
                                          where 1=1
                                              and comp_no = @c1_comp_no
                                              and dept_id = @c1_dept_id
                                              and pm_type <> 'INS'
                                        );
                
                delete from TAPMLST 
                where 1=1
                    and comp_no  = @c1_comp_no 
                    and  dept_id = @c1_dept_id
                    and pm_type <> 'INS';
               end
             
    FETCH NEXT FROM c_dept_data  INTO 
                                                                 @c1_comp_no
                                                                ,@c1_dept_id
                                                                ,@c1_ins_cnt
          END
      CLOSE c_dept_data
      DEALLOCATE c_dept_data
      
      update TYPMLIST_PART set  is_valid = 'Y';

--|| ====================================================================
      
    OPEN c_excel_data
    FETCH NEXT FROM c_excel_data  INTO 
                   @c2_sn                                                             
            ,@c2_excel_no                                       
            ,@c2_item_no                                        
            ,@c2_description                                    
            ,@c2_pm_categ_desc                                  
            ,@c2_measure_tools                                  
            ,@c2_cycle                                          
            ,@c2_period_type                                    
            ,@c2_wo_res_bef                                     
            ,@c2_work_id                                        
            ,@c2_work_name                                      
            ,@c2_wkctr_desc                                     
            ,@c2_asmb                                           
            ,@c2_init_wrk_date     
            ,@c2_remark                                         
            ,@c2_pno1                                           
            ,@c2_pname1                                         
            ,@c2_pspec1                                         
            ,@c2_qty1                                           
            ,@c2_pno2                                           
            ,@c2_pname2                                         
            ,@c2_pspec2                                         
            ,@c2_qty2                                           
            ,@c2_pno3                                           
            ,@c2_pname3                                         
            ,@c2_pspec3                                         
            ,@c2_qty3                                           
            ,@c2_pno4                                           
            ,@c2_pname4                                         
            ,@c2_pspec4                                         
            ,@c2_qty4                                           
            ,@c2_pno5                                           
            ,@c2_pname5                                         
            ,@c2_pspec5                                         
            ,@c2_qty5                                           
            ,@c2_is_valid          
    
      WHILE (@@FETCH_STATUS=0)
     BEGIN  --00
          
          
       
       if @c2_is_valid = 'Y' 
                begin   --9
                select top 1
                       @v1_equip_id  =  equip_id
                      ,@v1_dept_id   =  dept_id
                      ,@v1_eqloc_id  =  eqloc_id

                from TAEQUIPMENT
                where 1=1
                  and comp_no  = @v1_comp_no
                  and excel_no = @c2_excel_no
                  --and rownum   = 1
                ;
                
                select @v1_pm_id = NEXT VALUE FOR sqapm_id
                  from dual;
                
                set @v1_pm_no = @v1_pm_id
                
                select @v1_count = count(*)
                from tacdsysd
                where 1=1
                  and list_type   = 'PM_CATEG'
                  and description = @c2_pm_categ_desc
               ;
               end;  --9

               begin   --8 
               if @v1_count > 0 
                    select top  1
                           @v1_pm_categ = cdsysd_no
                      from tacdsysd
                     where 1=1
                        and list_type   = 'PM_CATEG'
                        and description = @c2_pm_categ_desc
                        --and rownum = 1
                   ;
               else
                   set @v1_pm_categ = null
               
               end;   --8

               begin   --6
                if @c2_work_id is not null 
                    begin   --7
                       select @v1_count =  count(*)
                         from TAEMP
                        where 1=1
                          and comp_no = @v1_comp_no
                          and emp_no  = @c2_work_id
                       ;
                       
                       if @v1_count > 0 
                           select @v1_emp_id  =  emp_id
                             from TAEMP
                            where 1=1
                              and comp_no = @v1_comp_no
                              and emp_no  = @c2_work_id
                           ;
                       else
                           set @v1_emp_id = null
                    end   --7

                else
                     set @v1_emp_id = null
                end   --6
                
                begin  --4
                 if @c2_wkctr_desc is not null 

                       begin   --5
                       select @v1_count = count(*)
                         from TAWKCTR
                        where 1=1
                          and comp_no           = @v1_comp_no
                          and rtrim(description) = @c2_wkctr_desc
                       ;

                       
                       if @v1_count > 0 
                           select @v1_wkctr_id = wkctr_id
                             from TAWKCTR
                            where 1=1
                              and comp_no            = @v1_comp_no
                              and rtrim(description) = @c2_wkctr_desc
                           ;
                       else
                           
                           set @v1_wkctr_id = null
                       
                       end   --5
                else
                     set @v1_emp_id = null
                 
                end   --4

                begin   --3
                insert into TAPMLST
                      (
                        comp_no
                       ,pm_id
                       ,pm_no
                       ,description
                       ,equip_id
                       ,dept_id
                       ,pm_type
                       ,schedule_type
                       ,cycle
                       ,period_type
                       , wo_res_bef
                       ,init_wrk_date
                       ,last_sch_date
                       ,is_active
                       ,remark
                       ,emp_id
                       ,wo_type
                       ,eqloc_id
                       ,pm_categ
                       ,wkctr_id
                      ) 
               values(
                        @v1_comp_no
                       ,@v1_pm_id 
                       ,@v1_pm_no
                       ,@c2_description
                       ,@v1_equip_id
                       ,@v1_dept_id
                       ,'RPL'
                       ,'T'
                       ,@c2_cycle
                       ,@c2_period_type
                       ,@c2_wo_res_bef
                       ,@c2_init_wrk_date
                       ,@c2_init_wrk_date
                       ,'Y'
                       ,@c2_remark
                       ,@v1_emp_id 
                       ,'PMW'
                       ,@v1_eqloc_id
                       ,@v1_pm_categ
                       ,@v1_wkctr_id
                     );
                    
               insert into tapmequip
                     (
                       comp_no
                      , pmequip_id
                      , pm_id
                      , equip_id
                      )
               values(
                       @v1_comp_no
                      ,NEXT VALUE FOR  sqapmequip_id
                      ,@v1_pm_id
                      ,@v1_equip_id
                      )
                    ;
               end    --3
               
                begin    --2 
                if  @c2_pno1 is not null and
                    @c2_qty1  >  0       
                
                       select @v1_count = count(*)
                       from taparts
                      where 1=1
                        and comp_no = @v1_comp_no
                      and part_no = @c2_pno1
                   ;

                   begin   --1
                   if @v1_count > 0 

                       
                       select @v1_part_id = part_id
                         from taparts
                        where 1=1
                          and comp_no = @v1_comp_no
                          and part_no = @c2_pno1
                       ;
                   
                        insert into 
                           tapmpart(
                                     comp_no
                                    ,pm_part_id
                                    ,pm_id
                                    ,part_id
                                    ,use_qty
                                   ) 
                             values(
                                     @v1_comp_no
                                    ,NEXT VALUE FOR  sqapm_part_id
                                    ,@v1_pm_id
                                    ,@v1_part_id
                                    ,@c2_qty1
                                    );
                        
                        select @v1_count = count(*)
                         from TAEQPART
                        where 1=1
                          and comp_no  = @v1_comp_no
                          and equip_id = @v1_equip_id
                          and part_id  = @v1_part_id
                        ;
                   end  --1
                end   --2
                        begin  --10
                        if @v1_count = 0 
                            insert into 
                               TAEQPART
                                       (
                                         comp_no
                                        , eqpart_id
                                        , equip_id
                                        , part_id
                                        , consist_qty
                                        )
                                 values
                                       (
                                         @v1_comp_no
                                        ,NEXT VALUE FOR   sqaeqpart_id
                                        ,@v1_equip_id
                                        ,@v1_part_id
                                        ,@c2_qty1
                                        );
                        end    --10
                        
                    begin  --11
                    if @c2_pno2 is not null and 
                       @c2_qty2     >   0   
                        

                       select @v1_count = count(*)
                         from taparts
                        where 1=1
                          and comp_no = @v1_comp_no
                          and part_no = @c2_pno2
                       ;

                       begin   --12
                       if @v1_count > 0 

                           select @v1_part_id = part_id
                             from taparts
                            where 1=1
                              and comp_no = @v1_comp_no
                              and part_no = @c2_pno2
                           ;
                       
                            insert into 
                               tapmpart(
                                         comp_no
                                        ,pm_part_id
                                        ,pm_id
                                        ,part_id
                                        ,use_qty
                                        ) 
                                 values(
                                         @v1_comp_no
                                        ,NEXT VALUE FOR    sqapm_part_id
                                        ,@v1_pm_id
                                        ,@v1_part_id
                                        ,@c2_qty2
                                        );
                            
                            select @v1_count = count(*)
                              from TAEQPART
                             where 1=1
                               and comp_no  = @v1_comp_no
                               and equip_id = @v1_equip_id
                               and part_id  = @v1_part_id
                            ;

                            if @v1_count = 0 
                                insert into 
                                   TAEQPART(
                                              comp_no
                                             ,eqpart_id
                                             ,equip_id
                                             ,part_id
                                             ,consist_qty
                                             )
                                      values(
                                              @v1_comp_no
                                             ,NEXT VALUE FOR    sqaeqpart_id
                                             ,@v1_equip_id
                                             ,@v1_part_id
                                             ,@c2_qty2
                                             );
                          end  --12   
                    end   --11

                    begin   --13
                    if @c2_pno3 is not null and 
                       @c2_qty3    >   0    
                       
                       select @v1_count = count(*)
                         from taparts
                        where 1=1
                          and comp_no = @v1_comp_no
                          and part_no = @c2_pno3
                       ;
                       
                       begin   --15
                       if @v1_count > 0 
                           
                           select @v1_part_id = part_id
                             from taparts
                             where 1=1
                               and comp_no = @v1_comp_no
                               and part_no = @c2_pno3
                           ;
                       
                            insert into 
                               tapmpart(
                                         comp_no
                                        ,pm_part_id
                                        ,pm_id
                                        ,part_id
                                        ,use_qty
                                        ) 
                                 values(
                                          @v1_comp_no
                                         ,NEXT VALUE FOR  sqapm_part_id
                                         ,@v1_pm_id
                                         ,@v1_part_id
                                         ,@c2_qty3
                                         );
                            
                            select @v1_count = count(*)
                              from TAEQPART
                              where 1=1
                                and comp_no  = @v1_comp_no
                                and equip_id = @v1_equip_id
                                and part_id  = @v1_part_id
                            ;
                            

                            begin   --14
                            if @v1_count = 0 
                            
                                insert into 
                                   TAEQPART(
                                             comp_no
                                            , eqpart_id
                                            , equip_id
                                            , part_id
                                            , consist_qty
                                            )
                                     values(
                                             @v1_comp_no
                                            ,NEXT VALUE FOR  sqaeqpart_id
                                            ,@v1_equip_id
                                            ,@v1_part_id
                                            ,@c2_qty3
                                            );
                            end    --14
                       end   --15

                       end   --13


                     if @c2_pno4 is not null and 
                        @c2_qty4    >   0    
                       begin    --16
                       select @v1_count = count(*)
                         from taparts
                        where 1=1
                          and comp_no = @v1_comp_no
                          and part_no = @c2_pno4
                       ;
                       end    --16

                       
                       if @v1_count > 0 
                           begin  --17
                           select @v1_part_id = part_id
                             from taparts
                            where 1=1
                              and comp_no = @v1_comp_no
                              and part_no = @c2_pno4
                           ;
                       
                            insert into 
                               tapmpart(
                                         comp_no
                                        , pm_part_id
                                        , pm_id
                                        , part_id
                                        , use_qty
                                        ) 
                                 values(
                                         @v1_comp_no
                                        ,NEXT VALUE FOR   sqapm_part_id
                                        ,@v1_pm_id
                                        ,@v1_part_id
                                        ,@c2_qty4
                                        );
                            
                            select @v1_count = count(*)
                              from TAEQPART
                             where 1=1
                               and comp_no  = @v1_comp_no
                               and equip_id = @v1_equip_id
                               and part_id  = @v1_part_id
                            ;
                            end    --17
                            

                            begin   --18
                            if @v1_count = 0 
                                insert into 
                                   TAEQPART(
                                             comp_no
                                            , eqpart_id
                                            , equip_id
                                            , part_id
                                            , consist_qty
                                            )
                                     values(
                                             @v1_comp_no
                                            ,NEXT VALUE FOR   sqaeqpart_id
                                            ,@v1_equip_id
                                            ,@v1_part_id
                                            ,@c2_qty4
                                            );
                             end   --18



                    if @c2_pno5 is not null and 
                       @c2_qty5    >   0    
                       
                       begin   --19
                       select @v1_count = count(*)
                         from taparts
                        where 1=1
                          and comp_no = @v1_comp_no
                          and part_no = @c2_pno5
                       ;
                       
                       if @v1_count > 0 
                           begin   --20
                           select @v1_part_id = part_id
                             from taparts
                            where 1=1
                              and comp_no = @v1_comp_no
                              and part_no = @c2_pno5
                           ;
                       
                            insert into 
                               tapmpart(
                                         comp_no
                                        , pm_part_id
                                        , pm_id
                                        , part_id
                                        , use_qty
                                        ) 
                                 values(
                                         @v1_comp_no
                                        ,NEXT VALUE FOR   sqapm_part_id
                                        ,@v1_pm_id
                                        ,@v1_part_id
                                        ,@c2_qty5
                                        );
                            
                            select @v1_count = count(*)
                              from TAEQPART
                             where 1=1
                               and comp_no  = @v1_comp_no
                               and equip_id = @v1_equip_id
                               and part_id  = @v1_part_id
                            ;
                            end   --20
                            end   --19

                            begin  --21
                            if @v1_count = 0 
                                insert into 
                                   TAEQPART(
                                             comp_no
                                            ,eqpart_id
                                            ,equip_id
                                            ,part_id
                                            ,consist_qty
                                            )
                                     values(
                                             @v1_comp_no
                                            ,NEXT VALUE FOR   sqaeqpart_id
                                            ,@v1_equip_id
                                            ,@v1_part_id
                                            ,@c2_qty5
                                            );
                            end    --21
              

                    
   FETCH NEXT FROM c_excel_data  INTO 
                   @c2_sn                                                             
            ,@c2_excel_no                                       
            ,@c2_item_no                                        
            ,@c2_description                                    
            ,@c2_pm_categ_desc                                  
            ,@c2_measure_tools                                  
            ,@c2_cycle                                          
            ,@c2_period_type                                    
            ,@c2_wo_res_bef                                     
            ,@c2_work_id                                        
            ,@c2_work_name                                      
            ,@c2_wkctr_desc                                     
            ,@c2_asmb                                           
            ,@c2_init_wrk_date     
            ,@c2_remark                                         
            ,@c2_pno1                                           
            ,@c2_pname1                                         
            ,@c2_pspec1                                         
            ,@c2_qty1                                           
            ,@c2_pno2                                           
            ,@c2_pname2                                         
            ,@c2_pspec2                                         
            ,@c2_qty2                                           
            ,@c2_pno3                                           
            ,@c2_pname3                                         
            ,@c2_pspec3                                         
            ,@c2_qty3                                           
            ,@c2_pno4                                           
            ,@c2_pname4                                         
            ,@c2_pspec4                                         
            ,@c2_qty4                                           
            ,@c2_pno5                                           
            ,@c2_pname5                                         
            ,@c2_pspec5                                         
            ,@c2_qty5                                           
            ,@c2_is_valid                       
            
        END     --00           
   
      CLOSE c_excel_data
      DEALLOCATE c_excel_data      

     
