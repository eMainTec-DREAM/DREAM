CREATE FUNCTION [dbo].[TRANSLATE](
    @source varchar(8000),
    @search varchar(8000),
    @replacement varchar(8000)
)

returns varchar(8000)
as
begin

declare @i int, @iMax int

set @iMax = len(@search)
set @i = 1

while @i <= @iMax
begin
  set @source = replace(@source, substring(@search, @i, 1), substring(@replacement, @i, 1))
  set @i = @i + 1
end

return @source

end
