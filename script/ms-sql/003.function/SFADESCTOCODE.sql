CREATE   FUNCTION [dbo].[SFADESCTOCODE](
     @pCode NVARCHAR(40)
    ,@pCodeType NVARCHAR(40)
    ,@pClassType NVARCHAR(20)
    ,@pCompNo VARCHAR(6)
)
RETURNS NVARCHAR(20)

BEGIN
      DECLARE @DESCRIPTION AS NVARCHAR(20);

      IF(@pClassType = 'SYS')
          SELECT TOP 1 @DESCRIPTION = CDSYSD_NO
          FROM TACDSYSD
          WHERE description = @pCode
              AND cdsysm_id = (SELECT TOP 1 cdsysm_id
                               FROM TACDSYSM
                               WHERE list_type= @pCodeType
                               );
      ELSE IF(@pClassType = 'USR')
          SELECT TOP 1 @DESCRIPTION = CDUSRD_NO
          FROM TACDUSRD
          WHERE description = @pCode
              AND comp_no = @pCompNo
              AND cdusrm_id = (SELECT cdusrm_id
                               FROM TACDUSRM
                               WHERE dir_type=@pCodeType
                                    AND comp_no = @pCompNo
                               );
      ELSE
         SET @DESCRIPTION = '';

   RETURN @DESCRIPTION
END;