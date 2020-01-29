/*2017.06.12 박철완*/
CREATE PROC SP_ADD_TABLE_COMMENT(

 @SET_TABLE VARCHAR(50), --// 테이블명
 @SET_COMMENT NVARCHAR(1000) --// 등록할 COMMENT
 
)
AS
BEGIN

    declare @v_count          as bigint

    SELECT @v_count = count(*)
    FROM ::FN_LISTEXTENDEDPROPERTY (NULL, 'SCHEMA', 'dbo', 'TABLE', 'TATABLE', DEFAULT, DEFAULT)
    WHERE name = @SET_TABLE
    ;
    
    if @v_count > 0
        EXEC sp_updateextendedproperty 'MS_Description', @SET_COMMENT, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE
    else
        EXEC sp_addextendedproperty 'MS_Description', @SET_COMMENT, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE
    
END




/*2017.06.12 박철완*/
ALTER PROC [dbo].[SP_ADD_TABLE_COMMENT](
 @SET_TABLE VARCHAR(50), --// 테이블명
 @SET_COMMENT NVARCHAR(1000) --// 등록할 COMMENT
)
AS
BEGIN

    declare @v_count          as bigint

    SELECT @v_count = count(*)
    FROM ::FN_LISTEXTENDEDPROPERTY (NULL, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE, DEFAULT, DEFAULT)
    WHERE OBJNAME = @SET_TABLE
    ;
    
    if @v_count > 0
        EXEC sp_updateextendedproperty 'MS_Description', @SET_COMMENT, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE
    else
        EXEC sp_addextendedproperty 'MS_Description', @SET_COMMENT, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE
    
END
