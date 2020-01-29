CREATE   FUNCTION [dbo].[SFACODETOID](
     @pCode NVARCHAR(40)
    ,@pCodeType NVARCHAR(40)
    ,@pClassType NVARCHAR(20)
    ,@pCompNo VARCHAR(6)
)
RETURNS BIGINT

BEGIN

      DECLARE @return_id AS BIGINT;

     IF(@pClassType = 'SYS')
         BEGIN
             SELECT TOP 1 @return_id = cdsysd_id
             FROM TACDSYSD
             WHERE CDSYSD_NO = @pCode
                 AND cdsysm_id = (SELECT TOP 1 cdsysm_id
                                  FROM TACDSYSM
                                  WHERE list_type=@pCodeType
                                  );
         END
     ELSE IF(@pClassType = 'USR')
         BEGIN
             SELECT TOP 1 @return_id = cdusrd_id
             FROM TACDUSRD
             WHERE CDUSRD_NO = @pCode
                AND comp_no = @pCompNo
                AND cdusrm_id = (SELECT TOP 1 cdusrm_id
                                 FROM TACDUSRM
                                 WHERE dir_type = @pCodeType
                                            AND comp_no = @pCompNo
                                );
         END
     

   RETURN @return_id
   
END;