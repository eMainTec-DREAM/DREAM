CREATE PROCEDURE [dbo].[SP_EQ_UPD_EQASMB] (
      @v_comp_no     VARCHAR(6)
     ,@v_eqasmb_id    BIGINT
)
as
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    
	SET NOCOUNT ON
    DECLARE @V_COUNT AS INT;
    DECLARE @FULL_DESC AS VARCHAR(1024);
    
    UPDATE TAEQASMB SET full_desc = ( SELECT   MAX(CASE WHEN LVL =9 THEN description + '-' ELSE '' END)
	                                        + MAX(CASE WHEN LVL =8 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =7 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =6 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =5 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =4 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =3 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =2 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =1 THEN description + '' ELSE '' END)
                                     FROM dbo.SFAEQASMB_PALL(@v_comp_no,@v_eqasmb_id,'')
                                   )
                WHERE eqasmb_id = @v_eqasmb_id 
                    and comp_no = @v_comp_no     
            ; 
            

