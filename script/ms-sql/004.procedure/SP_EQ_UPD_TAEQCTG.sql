CREATE PROCEDURE [dbo].[SP_EQ_UPD_TAEQCTG] (
      @v_comp_no     VARCHAR(6)
     ,@v_eqctg_id    BIGINT
)
as
    SET NOCOUNT ON
    DECLARE @V_COUNT AS INT;
    DECLARE @FULL_DESC AS VARCHAR(1024);
    
    UPDATE TAEQCTG SET full_desc = ( SELECT   MAX(CASE WHEN LVL =8 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =7 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =6 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =5 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =4 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =3 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =2 THEN description + ' > ' ELSE '' END)
                                            + MAX(CASE WHEN LVL =1 THEN description + ' ' ELSE '' END)
                                     FROM dbo.SFAEQCTG_PALL(@v_comp_no,@v_eqctg_id,'')
                                   )
                      , lvl = ( SELECT MAX(lvl) from SFAEQCTG_PALL(@v_comp_no,@v_eqctg_id,'') )
                WHERE eqctg_id = @v_eqctg_id 
                    and comp_no = @v_comp_no     
            ; 
            
    update TAEQCTG set 
        IS_LOWEST_LVL = 'N'                        
    where 1=1                                                         
        And comp_no = @v_comp_no        
        and eqctg_id in (select eqctg_id                          
                         from dbo.SFAEQCTG_CALL(@v_comp_no, @v_eqctg_id,'')
                         )    
    ;
    
    
    SELECT @v_count = count(a.eqctg_id)
     from TAEQCTG a                                                
     where 1=1                                                     
          and a.comp_no = @v_comp_no 
          and eqctg_id in (select eqctg_id                          
                          from dbo.SFAEQCTG_CALL(@v_comp_no, @v_eqctg_id,'')
                          ) 
     ;   
     
     if @v_count = 1 
         update TAEQCTG set 
            IS_LOWEST_LVL = 'Y'             
         where 1=1                                              
            and comp_no = @v_comp_no
            and eqctg_id = @v_eqctg_id
         ;

           
            
                                                          

