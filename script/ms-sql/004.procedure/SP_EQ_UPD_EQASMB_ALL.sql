CREATE PROCEDURE [dbo].[SP_EQ_UPD_EQASMB_ALL] (
      @v_comp_no     varchar(6)
) 
as
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    
	SET NOCOUNT ON;
    
    declare @v_count as bigint;
    DECLARE @v_eqasmb_id AS bigint;
    
    DECLARE c_data CURSOR FOR
        SELECT eqasmb_id                         
        FROM TAEQASMB                                            
        WHERE comp_no=@v_comp_no
        order by eqasmb_id            
        ;
        
     
    OPEN c_data
    FETCH NEXT FROM c_data INTO @v_eqasmb_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
             
              exec dbo.sp_eq_upd_eqasmb @v_comp_no, @v_eqasmb_id;
          
              FETCH NEXT FROM c_data INTO  @v_eqasmb_id
          END
      CLOSE c_data
      DEALLOCATE c_data
     

