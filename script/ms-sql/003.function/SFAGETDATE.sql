SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   FUNCTION [dbo].[SFAGETDATE](
	  @pOffset SMALLINT
	, @pIntervalType NVARCHAR(15)
	, @pInterval SMALLINT
)
    RETURNS DATETIME
BEGIN
    DECLARE @v_date AS DATETIME;

	BEGIN
		SELECT @v_date = dateadd(hh, @pOffset, GETUTCDATE());
	END

	IF @pIntervalType = 'YEAR'
		BEGIN
			SELECT @v_date = dateadd(YEAR, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'MONTH'
		BEGIN
			SELECT @v_date = dateadd(MONTH, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'WEEK'
		BEGIN
			SELECT @v_date = dateadd(WEEK, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'DAY'
		BEGIN
			SELECT @v_date = dateadd(DAY, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'HOUR'
		BEGIN
			SELECT @v_date = dateadd(HOUR, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'MINUTE'
		BEGIN
			SELECT @v_date = dateadd(MINUTE, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'SECOND'
		BEGIN
			SELECT @v_date = dateadd(SECOND, @pInterval, @v_date);
		END
	ELSE
		BEGIN
			SELECT @v_date = dateadd(DAY, @pInterval, @v_date);
		END

	RETURN @v_date
END;
