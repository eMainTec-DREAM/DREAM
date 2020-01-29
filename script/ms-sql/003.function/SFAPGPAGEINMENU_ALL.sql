-- 2017.09.22 차한결추가
CREATE FUNCTION [dbo].[SFAPGPAGEINMENU_ALL]
(
      @MENU_ID BIGINT
)
RETURNS TABLE 
AS
RETURN(
    WITH PGPAGE AS (
		SELECT 
				y.C_PAGE_id
				,x.page_id
		FROM	TAPAGE x left outer join TAPGPAGE y on x.page_id = y.page_id
	),DATATABLE AS ( 
        SELECT 
             A.C_PAGE_ID
           , A.PAGE_ID
           , 1 AS LVL
		   , CONVERT(VARCHAR, A.C_PAGE_ID) AS SORT
        FROM PGPAGE A
        WHERE 1=1
			AND PAGE_ID IN (SELECT x.page_id 
							FROM [dbo].[SFAMENU_CALL](@MENU_ID,'') as x
							WHERE x.page_id IS NOT NULL )
        UNION ALL 
        SELECT 
             B.C_PAGE_ID
           , B.PAGE_ID
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.C_PAGE_ID)) AS SORT
        FROM PGPAGE B INNER JOIN DATATABLE C  ON B.PAGE_ID = C.C_PAGE_ID 
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)