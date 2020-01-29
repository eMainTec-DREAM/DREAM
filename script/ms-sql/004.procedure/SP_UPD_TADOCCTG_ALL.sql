CREATE PROCEDURE [dbo].[SP_UPD_TADOCCTG_ALL] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count as bigint;
    DECLARE @v_docctg_id AS bigint;
    
    DECLARE c_data CURSOR FOR
        SELECT docctg_id                         
        FROM TADOCCTG                                            
        WHERE comp_no=@v_comp_no
        order by docctg_id            
        ;
        
     
    OPEN c_data
    FETCH NEXT FROM c_data INTO @v_docctg_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
             
              exec dbo.sp_upd_TADOCCTG @v_comp_no, @v_docctg_id;
          
              FETCH NEXT FROM c_data INTO  @v_docctg_id
          END
      CLOSE c_data
      DEALLOCATE c_data
     
