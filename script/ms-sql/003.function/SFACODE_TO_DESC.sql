CREATE   FUNCTION [dbo].[SFACODE_TO_DESC](
      @pCode NVARCHAR(40)
    , @pCodeType NVARCHAR(40)
    , @pClassType NVARCHAR(20)
    , @pCompNo VARCHAR(6)
    , @pUserLang NVARCHAR(20)
)
    RETURNS NVARCHAR(256)
BEGIN
      DECLARE @DESCRIPTION AS NVARCHAR(200);
      DECLARE @USERLANG AS NVARCHAR(20);
      SET @USERLANG = @pUserLang;
      
      IF @pUserLang = ''
         SET @USERLANG = 'en';
      
      IF @pClassType = 'SYS'
        BEGIN 
            SELECT 
                 @DESCRIPTION = isnull((select aa.key_name 
                                        from talang aa
                                        where  lang = @userLang
                                            and x.key_type = aa.key_type
                                            and x.key_no = aa.key_no)
                                    , description)
            FROM TACDSYSD x
            WHERE x.CDSYSD_NO = @pCode
                 AND x.list_type=@pCodeType
            ;                                          
         END
      ELSE IF @pClassType = 'USR'
          BEGIN
              SELECT top 1 @DESCRIPTION = description
              FROM TACDUSRD
              WHERE CDUSRD_NO = @pCode
                AND comp_no = @pCompNo
                AND cdusrm_id = (SELECT top 1 cdusrm_id
                                 FROM TACDUSRM
                                 WHERE dir_type=@pCodeType
                                       AND comp_no = @pCompNo
                                );
          END
      ELSE
          SET @DESCRIPTION = '';

   RETURN @DESCRIPTION
END
