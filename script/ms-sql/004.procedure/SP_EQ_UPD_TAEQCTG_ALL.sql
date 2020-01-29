CREATE PROCEDURE [dbo].[SP_EQ_UPD_TAEQCTG_ALL] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count as bigint;
    DECLARE @v_eqctg_id AS bigint;
    
    DECLARE c_data CURSOR FOR
        SELECT comp_no, eqctg_id                         
        FROM TAEQCTG                                            
        WHERE comp_no=@v_comp_no
        order by eqctg_id            
        ;
        
     
    OPEN c_data
    FETCH NEXT FROM c_data INTO @v_eqctg_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
             
              exec dbo.sp_eq_upd_taeqctg @v_comp_no, @v_eqctg_id;
          
              FETCH NEXT FROM c_data INTO  @v_eqctg_id
          END
      CLOSE c_data
      DEALLOCATE c_data
     
