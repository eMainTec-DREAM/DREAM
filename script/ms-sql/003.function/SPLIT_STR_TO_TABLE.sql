CREATE FUNCTION [dbo].[SPLIT_STR_TO_TABLE]
(
 @param varchar(8000) -- 대상 문자열
, @div char(1) -- 구분자
)
RETURNS @RESULT TABLE 
(
 [VALUE] varchar(8000)
, [ORDER] int
) AS
begin

declare @INDEX int
,  @POS int
,  @ORDER int
,  @TEMP varchar(8000)

SET @INDEX = 1
SET @POS = 1
SET @ORDER = 1

while @POS > 0 begin
 SET @POS = CHARINDEX(@div, @param, @INDEX)

 if @POS = 0 begin
  SET @TEMP = RIGHT(@param, LEN(@param) - @INDEX + 1)
 end
 else begin
  SET @TEMP = SUBSTRING(@param, @INDEX, @POS - @INDEX)
 end

 if len(@TEMP) > 0 begin
  SET @TEMP = RTRIM(LTRIM(@TEMP))
  INSERT INTO @RESULT VALUES (@TEMP, @ORDER)
 end

 SET @INDEX = @POS + 1
 SET @ORDER = @ORDER + 1
end

RETURN 
end