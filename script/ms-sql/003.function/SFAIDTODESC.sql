CREATE   FUNCTION  [dbo].[SFAIDTODESC](
      @pId BIGINT
    , @pCodeType NVARCHAR(40)
    , @pClassType NVARCHAR(20)
    , @pCompNo VARCHAR(6)
)

RETURNS NVARCHAR(256)

BEGIN
      DECLARE @DESCRIPTION AS NVARCHAR(256);
      SET @DESCRIPTION = '';
      
      IF(@pClassType = 'SYS')   
            SELECT TOP 1 @DESCRIPTION = description
            FROM TACDSYSD
            WHERE cdsysd_id = @pId
            AND cdsysm_id = (SELECT TOP 1 cdsysm_id
                             FROM TACDSYSM
                             WHERE list_type=@pCodeType
                             );
      ELSE IF(@pClassType = 'USR')
            SELECT TOP 1 @DESCRIPTION = description
            FROM TACDUSRD
            WHERE cdusrd_id = @pId
                AND comp_no = @pCompNo
                AND cdusrm_id = (SELECT TOP 1 cdusrm_id
                                 FROM TACDUSRM
                                 WHERE dir_type=@pCodeType
                                      AND comp_no = @pCompNo
                                 );
                                            
                                        
      ELSE IF(@pClassType = 'USER')
           SELECT TOP 1 @DESCRIPTION = user_name
           FROM    TAUSER
           WHERE  user_id = @pId
               AND  comp_no = @pCompNo ;                                                
                                        
       ELSE IF(@pClassType = 'EMP')
           SELECT TOP 1 @DESCRIPTION = emp_name
           FROM    TAEMP
           WHERE  emp_id = @pId
              AND  comp_no = @pCompNo  ;     
                                        
       ELSE IF(@pClassType = 'DEPT')
           SELECT TOP 1 @DESCRIPTION = description
           FROM    TADEPT
           WHERE  dept_id = @pId
               AND  comp_no = @pCompNo ;               
                                        
       ELSE IF(@pClassType = 'USRGRP')
           SELECT TOP 1 @DESCRIPTION = group_name
           FROM    TAUSRGRP
           WHERE  usrgrp_id = @pId
               AND  comp_no = @pCompNo ;                                              
                                       
       ELSE IF(@pClassType = 'MENU')
           SELECT TOP 1 @DESCRIPTION = description
           FROM    TAMENU
           WHERE  menu_id = @pId
           ;                                                              
                                       
       ELSE IF(@pClassType = 'VENDOR')
           SELECT TOP 1 @DESCRIPTION = description
           FROM    TAVENDOR
           WHERE  vendor_id = @pId
           AND  COMP_NO = @pCompNo ;
       ELSE
        SET @DESCRIPTION = '';

   RETURN @DESCRIPTION                                              
                                                                               
   
END;