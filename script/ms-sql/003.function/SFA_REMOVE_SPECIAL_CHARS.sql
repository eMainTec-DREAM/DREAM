CREATE FUNCTION DBO.SFA_REMOVE_SPECIAL_CHARS (
    @mString varchar(256)

)

RETURNS VARCHAR(256)

BEGIN

    IF @mString IS NULL BEGIN

        RETURN NULL;

    END

 

    DECLARE @mString2 VARCHAR(256) = '';

    DECLARE @mLen INT = len(@mString);

    DECLARE @mInt INT = 1;

 

    WHILE @mInt <= @mLen BEGIN

        DECLARE @i INT;

        SET @i = ASCII(SUBSTRING(@mString, @mInt, 1));

        IF (@i BETWEEN 48 AND 57) OR (@i BETWEEN 65 AND 90) OR (@i BETWEEN 97 AND 122) BEGIN

            SET @mString2 = @mString2 + CHAR(@i);

        END

        SET @mInt = @mInt + 1;

    END

 

    IF len(@mString2) = 0 BEGIN

        RETURN NULL;

    END

    RETURN @mString2;

END