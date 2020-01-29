CREATE PROCEDURE [dbo].[SP_DEPT_UPD_VALUE] (
      @v_comp_no     varchar(6)
     ,@v_dept_id     bigint
) 
as
    SET NOCOUNT ON
    
    declare @v_count as int;
    
    update TADEPT set 
        IS_LOWEST_LVL = 'N'                        
    where 1=1                                                         
        and comp_no = @v_comp_no        
        and dept_id in (select dept_id                          
                        from dbo.SFADEPT_CALL(@v_comp_no, @v_dept_id, '')
                        )    
     ;
     
     SELECT @v_count = count(a.dept_id)
     from TADEPT a                                                
     where 1=1                                                     
          and a.comp_no = @v_comp_no 
          and dept_id in (select dept_id                          
                          from dbo.SFADEPT_CALL(@v_comp_no, @v_dept_id, '')
                          ) 
     ;   
     
     if @v_count = 1 
         update tadept set 
            IS_LOWEST_LVL = 'Y'             
         where 1=1                                              
            and comp_no = @v_comp_no
            and dept_id = @v_dept_id
         ;
