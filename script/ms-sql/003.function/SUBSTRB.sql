CREATE function [dbo].[SUBSTRB](@str varchar(8000), @start int, @len int) 
returns varchar(8000) 
as 
begin 
declare @i int, @k int 
set @i = 0 
set @k = datalength(substring(@str,@start,@len)) 

if (@k <= @len) 
set @i = @len 
else 
begin 
set @i= @len 
while (@i > 0) 
begin 
set @i = @i -1 
if (datalength(substring(@str, @start, @i)) <= @len) 
break 
end 
end 

return (substring(@str,@start,@i)) 
end 
