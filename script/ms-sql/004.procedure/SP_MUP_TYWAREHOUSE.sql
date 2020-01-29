CREATE PROCEDURE [dbo].[SP_MUP_TYWAREHOUSE] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count as bigint;
    
    DECLARE  @c_sn          varchar(10)
            ,@c_code        nvarchar(20)
            ,@c_code_desc   nvarchar(200)
            ,@c_plant       varchar(20)
            ,@c_plant_DESC  nvarchar(200)
    
    DECLARE c_excel_data CURSOR FOR
                select 
                       sn
                      ,code
                      ,code_desc
                      ,plant
                     ,plant_DESC
                from TYWAREHOUSE a
                where 1=1
                order by sn
        ;         
        
     
    OPEN c_excel_data
    FETCH NEXT FROM c_excel_data INTO @c_sn           
                                    ,@c_code        
                                    ,@c_code_desc   
                                    ,@c_plant       
                                    ,@c_plant_DESC  
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
          -----------------------------------------------------------------------------------------------------------------------------------------
          -----------------------------------------------------------------------------------------------------------------------------------------
          
          select @v_count = count(*)
          from TAWAREHOUSE
          where comp_no = @v_comp_no
              and wcode = @c_code
          ;
          
          if @v_count > 0 
              update TAWAREHOUSE set
                     wname   = @c_code_desc
                    ,plant   = @c_plant
                    ,is_use  = 'Y'
                    ,wh_type = 'MWARE'
                   where comp_no = @v_comp_no
                       and wcode = @c_code
               ;
          
          else
               insert into TAWAREHOUSE
                          (
                          comp_no
                         ,wcode_id
                         ,wcode
                         ,wname
                         ,wh_type
                         ,is_use
                         ,plant
                         )
                    values (
                            @v_comp_no
                           ,NEXT VALUE FOR  sqawcode_id
                           ,@c_code
                           ,@c_code_desc
                           ,'MWARE'
                           ,'Y'
                           ,@c_plant
                           )
               ;
             
              

          end
          
      FETCH NEXT FROM c_excel_data INTO @c_sn           
                                    ,@c_code        
                                    ,@c_code_desc   
                                    ,@c_plant       
                                    ,@c_plant_DESC  
      CLOSE c_excel_data
      DEALLOCATE c_excel_data

     
