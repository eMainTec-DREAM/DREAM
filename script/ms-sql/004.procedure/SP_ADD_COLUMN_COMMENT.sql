/*2017.06.12 박철완*/
CREATE PROC SP_ADD_COLUMN_COMMENT(
 @SET_TABLE VARCHAR(50), --// 테이블명
 @SET_COL_NAME VARCHAR(50), --// 컬럼명
 @SET_COMMENT NVARCHAR(1000) --// 등록할 COMMENT
)

AS

BEGIN

    declare @v_count          as bigint

    SELECT @v_count = count(*)
    FROM ::FN_LISTEXTENDEDPROPERTY(NULL, 'SCHEMA', 'DBO', 'TABLE', @SET_TABLE, 'COLUMN', DEFAULT)
    WHERE objname = @SET_COL_NAME
    ;
    
    if @v_count > 0
        EXEC sp_updateextendedproperty 'MS_Description', @SET_COL_NAME, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE, 'COLUMN', @SET_COMMENT
    else
        EXEC sp_addextendedproperty 'MS_Description', @SET_COL_NAME, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE, 'COLUMN', @SET_COMMENT
    
END


/*2017.06.12 박철완*/

ALTER  PROC [dbo].[SP_ADD_COLUMN_COMMENT](
 @SET_TABLE VARCHAR(50), --// 테이블명
 @SET_COL_NAME VARCHAR(50), --// 컬럼명
 @SET_COMMENT NVARCHAR(1000) --// 등록할 COMMENT
)

AS

BEGIN

    declare @v_count          as bigint

    SELECT @v_count = count(*)
    FROM ::FN_LISTEXTENDEDPROPERTY(NULL, 'SCHEMA', 'DBO', 'TABLE', @SET_TABLE, 'COLUMN', DEFAULT)
    WHERE objname = @SET_COL_NAME
    ;
    
    if @v_count > 0
        EXEC sp_updateextendedproperty 'MS_Description', @SET_COMMENT, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE, 'COLUMN', @SET_COL_NAME
    else
        EXEC sp_addextendedproperty 'MS_Description', @SET_COMMENT, 'SCHEMA', 'dbo', 'TABLE', @SET_TABLE, 'COLUMN', @SET_COL_NAME
    
END
