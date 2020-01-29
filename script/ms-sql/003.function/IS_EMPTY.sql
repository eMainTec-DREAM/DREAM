USE [dream]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   FUNCTION [dbo].[IS_EMPTY](
 @P_STRING VARCHAR(200)
)
RETURNS NVARCHAR(200)
 BEGIN
   DECLARE @RETURN AS NVARCHAR(200);   
   
   IF @P_STRING = '' 
      SELECT @RETURN = NULL;
   ELSE
      SELECT @RETURN = @P_STRING
   
   RETURN @RETURN
   
 END
;