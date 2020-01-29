CREATE PROCEDURE [dbo].[SP_EQ_UPD_EQLOC] (
      @v_comp_no     VARCHAR(6)
     ,@v_eqloc_id    BIGINT
)
as
    SET NOCOUNT ON
    DECLARE @V_COUNT AS INT;
    DECLARE @FULL_DESC AS VARCHAR(1024);
    
    UPDATE TAEQLOC SET full_desc = ( SELECT   MAX(CASE WHEN LVL =8 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =7 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =6 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =5 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =4 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =3 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =2 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =1 THEN description + ' ' ELSE '' END)
                                     FROM dbo.SFAEQLOC_PALL(@v_comp_no,@v_eqloc_id,'')
                                   )
                      , lvl = ( SELECT MAX(lvl) from dbo.SFAEQLOC_PALL(@v_comp_no,@v_eqloc_id,'') )
                WHERE eqloc_id = @v_eqloc_id 
                    and comp_no = @v_comp_no     
            ; 
            
    update TAEQLOC set 
        IS_LOWEST_LVL = 'N'                        
    where 1=1                                                         
        And comp_no = @v_comp_no        
        and eqloc_id in (select eqloc_id                          
                         from dbo.SFAEQLOC_CALL(@v_comp_no, @v_eqloc_id,'')
                         )    
    ;
    
    
    SELECT @v_count = count(a.eqloc_id)
     from TAEQLOC a                                                
     where 1=1                                                     
          and a.comp_no = @v_comp_no 
          and eqloc_id in (select eqloc_id                          
                          from dbo.SFAEQLOC_CALL(@v_comp_no, @v_eqloc_id,'')
                          ) 
     ;   
     
     if @v_count = 1 
         update TAEQLOC set 
            IS_LOWEST_LVL = 'Y'             
         where 1=1                                              
            and comp_no = @v_comp_no
            and eqloc_id = @v_eqloc_id
         ;

