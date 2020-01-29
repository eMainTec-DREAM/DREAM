CREATE   FUNCTION [dbo].[SFACODETODESC](
     @pCode NVARCHAR(40)
    , @pCodeType NVARCHAR(40)
    , @pClassType NVARCHAR(20)
    , @pCompNo VARCHAR(6)
)
RETURNS NVARCHAR(256)

BEGIN
      DECLARE @DESCRIPTION AS NVARCHAR(200);
      DECLARE @USERLANG AS NVARCHAR(20);
      
      IF(@pClassType = 'SYS' AND @pCodeType='PM_TYPE')
          BEGIN
              SELECT TOP 1 @DESCRIPTION = description
              FROM TACDSYSD
              WHERE CDSYSD_NO = @pCode
                AND cdsysm_id IN (SELECT cdsysm_id
                                  FROM TACDSYSM
                                  WHERE list_type='PMI_TYPE' OR list_type='PMW_TYPE');
          END
      ELSE IF (@pClassType = 'SYS'  AND  @pCodeType!='PM_TYPE')
          BEGIN
              SELECT TOP 1 @DESCRIPTION = description
              FROM TACDSYSD
              WHERE CDSYSD_NO = @pCode
                 AND cdsysm_id = (SELECT TOP 1 cdsysm_id
                                  FROM TACDSYSM
                                  WHERE list_type = @pCodeType
                                  );
          END
      ELSE IF (@pClassType = 'USR')
          BEGIN
              SELECT TOP 1 @DESCRIPTION = description
              FROM TACDUSRD
              WHERE CDUSRD_NO = @pCode
                  AND comp_no = @pCompNo
                  AND cdusrm_id = (SELECT TOP 1 cdusrm_id
                                   FROM TACDUSRM
                                   WHERE dir_type = @pCodeType
                                        AND comp_no = @pCompNo
                                    );
         END
     ELSE
        SET @DESCRIPTION = '';

   RETURN @DESCRIPTION
   
END
