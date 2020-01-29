CREATE   FUNCTION   [dbo].[SFAIDTOCODE](
      @pId BIGINT
    , @pCodeType NVARCHAR(40)
    , @pClassType NVARCHAR(20)
    , @pCompNo VARCHAR(6)
)

RETURNS NVARCHAR(256)

BEGIN
      DECLARE @DESCRIPTION AS NVARCHAR(256);

      IF(@pClassType = 'SYS')
          BEGIN   
            SELECT TOP 1 @DESCRIPTION = CDSYSD_NO
            FROM TACDSYSD
            WHERE cdsysd_id = @pId
            AND cdsysm_id = (SELECT TOP 1 cdsysm_id
                             FROM TACDSYSM
                             WHERE list_type = @pCodeType
                             );
          END
                                                                                    
      ELSE IF(@pClassType = 'USR')
          BEGIN
              SELECT TOP 1 @DESCRIPTION = CDUSRD_NO
              FROM TACDUSRD
              WHERE cdusrd_id = @pId
                  AND comp_no = @pCompNo
                  AND cdusrm_id = (SELECT cdusrm_id
                                   FROM TACDUSRM
                                   WHERE dir_type=@pCodeType
                                            AND comp_no = @pCompNo
                                  );
          END 
                                        
      ELSE IF(@pClassType = 'USER')
          BEGIN
              SELECT TOP 1 @DESCRIPTION = user_no
              FROM    TAUSER
              WHERE  user_id = @pId
                  AND  comp_no = @pCompNo ;                                                
          END
                                        
      ELSE IF(@pClassType = 'EMP')
          BEGIN 
              SELECT TOP 1 @DESCRIPTION = emp_no
              FROM    TAEMP
              WHERE  emp_id = @pId
                   AND  comp_no = @pCompNo ;     
          END
                                        
      ELSE IF(@pClassType = 'DEPT')
          BEGIN
              SELECT TOP 1 @DESCRIPTION = dept_no
              FROM    TADEPT
              WHERE  dept_id = @pId
                  AND  comp_no = @pCompNo ;               
          END

      ELSE IF(@pClassType = 'USRGRP')
          BEGIN
              SELECT  TOP 1 @DESCRIPTION = usrgrp_no
              FROM    TAUSRGRP
              WHERE  usrgrp_id = @pId
                  AND  comp_no = @pCompNo ;   
          END
      ELSE
        SET @DESCRIPTION = '';

   RETURN @DESCRIPTION
          
END;