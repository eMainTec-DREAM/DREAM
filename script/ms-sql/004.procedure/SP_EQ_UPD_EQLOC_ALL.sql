CREATE PROCEDURE [dbo].[SP_EQ_UPD_EQLOC_ALL] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count as bigint;
    DECLARE @v_eqloc_id AS bigint;
    
    DECLARE c_data CURSOR FOR
        SELECT eqloc_id                         
        FROM TAEQLOC                                            
        WHERE comp_no=@v_comp_no
        order by eqloc_id            
        ;
        
     
    OPEN c_data
    FETCH NEXT FROM c_data INTO @v_eqloc_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
             
              exec dbo.sp_eq_upd_eqloc @v_comp_no, @v_eqloc_id;
          
              FETCH NEXT FROM c_data INTO  @v_eqloc_id
          END
      CLOSE c_data
      DEALLOCATE c_data
     
