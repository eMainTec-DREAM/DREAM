CREATE FUNCTION SFA_GET_ONLY_NUMERIC (@strAlphaNumeric VARCHAR(256)) 
returns VARCHAR(256) 
AS 
  BEGIN 
      DECLARE @intAlpha INT 

      SET @intAlpha = Patindex('%[^0-9]%', @strAlphaNumeric) 

      BEGIN 
          WHILE @intAlpha > 0 
            BEGIN 
                SET @strAlphaNumeric = Stuff(@strAlphaNumeric, @intAlpha, 1, '') 
                SET @intAlpha = Patindex('%[^0-9]%', @strAlphaNumeric) 
            END 
      END 

      RETURN Isnull(@strAlphaNumeric, 0) 
  END 