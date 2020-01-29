CREATE PROCEDURE [dbo].[SP_DEPT_UPD_VALUE_ALL] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count as bigint;
    DECLARE @v_dept_id AS bigint;
    
    DECLARE c_data CURSOR FOR
        SELECT dept_id                         
        FROM TADEPT                                            
        WHERE comp_no=@v_comp_no
        order by dept_id            
        ;
        
     
    OPEN c_data
    FETCH NEXT FROM c_data INTO @v_dept_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
             
              exec dbo.sp_dept_upd_value @v_comp_no, @v_dept_id;
          
              FETCH NEXT FROM c_data INTO  @v_dept_id
          END
      CLOSE c_data
      DEALLOCATE c_data
     
