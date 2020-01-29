CREATE   FUNCTION [dbo].[IS_NUMBER](
    @P_STRING VARCHAR(30)
)
RETURNS BIGINT
    BEGIN
      DECLARE @RTN_VALUE AS BIGINT;
      DECLARE @ISNUM AS INT;
      
      SELECT @ISNUM = IsNumeric(@P_STRING + 'e0');
      IF @ISNUM = 0 
         SELECT @RTN_VALUE = 0;
      ELSE
         SELECT @RTN_VALUE = CONVERT(numeric, @P_STRING);
      
      RETURN @RTN_VALUE
      
    END
;
