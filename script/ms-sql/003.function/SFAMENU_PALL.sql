CREATE FUNCTION [dbo].[SFAMENU_PALL]
(
      @MENU_ID BIGINT
    , @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.MENU_ID
           , A.P_MENU_ID
           , 0 AS LVL
        FROM TAMENU A
        WHERE 1=1
            and MENU_ID = CASE @MENU_ID WHEN '' THEN MENU_ID ELSE @MENU_ID END
            and upper(description) like CASE @MENU_ID WHEN '' THEN '%'+upper(@DESCRIPTION)+'%' ELSE upper(description) END
        UNION ALL 
        SELECT 
             B.MENU_ID
           , B.P_MENU_ID
           , C.LVL+ 1
        FROM TAMENU B INNER JOIN DATATABLE C  ON B.MENU_ID = C.P_MENU_ID 
        WHERE 1=1
    ) 
    SELECT * FROM DATATABLE
)

-- 2017.06.01 차한결추가. 재귀함수 정렬 추가
ALTER FUNCTION [dbo].[SFAMENU_PALL]
(
      @MENU_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.MENU_ID
           , A.P_MENU_ID
           , 0 AS LVL
		   , CONVERT(VARCHAR, A.MENU_ID) AS SORT
        FROM TAMENU A
        WHERE 1=1
            and MENU_ID = CASE @MENU_ID WHEN '' THEN MENU_ID ELSE @MENU_ID END
			and upper(description) like CASE @MENU_ID WHEN '' THEN upper(@DESCRIPTION)+'%' ELSE upper(description) END
        UNION ALL 
        SELECT 
             B.MENU_ID
           , B.P_MENU_ID
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.MENU_ID)) AS SORT
        FROM TAMENU B INNER JOIN DATATABLE C  ON B.MENU_ID = C.P_MENU_ID 
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)

-- 2017.09.22 차한결추가. PAGE_ID  컬럼 추가
ALTER FUNCTION [dbo].[SFAMENU_PALL]
(
      @MENU_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.MENU_ID
		   , A.PAGE_ID
           , A.P_MENU_ID
           , 0 AS LVL
		   , CONVERT(VARCHAR, A.MENU_ID) AS SORT
        FROM TAMENU A
        WHERE 1=1
            and MENU_ID = CASE @MENU_ID WHEN '' THEN MENU_ID ELSE @MENU_ID END
			and upper(description) like CASE @MENU_ID WHEN '' THEN upper(@DESCRIPTION)+'%' ELSE upper(description) END
        UNION ALL 
        SELECT 
             B.MENU_ID
		   , B.PAGE_ID
           , B.P_MENU_ID
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.MENU_ID)) AS SORT
        FROM TAMENU B INNER JOIN DATATABLE C  ON B.MENU_ID = C.P_MENU_ID 
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)