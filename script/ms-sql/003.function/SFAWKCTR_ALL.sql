CREATE FUNCTION [dbo].[SFAWKCTR_ALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @P_WKCTR_ID BIGINT
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.WKCTR_ID
           , A.P_WKCTR_ID
           , 1 AS LVL
           , A.DESCRIPTION
        FROM TAWKCTR A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
            and P_WKCTR_ID = @P_WKCTR_ID
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.WKCTR_ID
           , B.P_WKCTR_ID
           , C.LVL+ 1
           , B.DESCRIPTION
        FROM TAWKCTR B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_WKCTR_ID = C.WKCTR_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT * FROM DATATABLE
)

-- 2017.06.01 차한결추가. 재귀함수 정렬 추가
ALTER FUNCTION [dbo].[SFAWKCTR_ALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @P_WKCTR_ID BIGINT
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.WKCTR_ID
           , A.P_WKCTR_ID
           , 1 AS LVL
           , A.DESCRIPTION
		   , CONVERT(VARCHAR, A.WKCTR_ID) AS SORT
        FROM TAWKCTR A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and P_WKCTR_ID = @P_WKCTR_ID
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.WKCTR_ID
           , B.P_WKCTR_ID
           , C.LVL+ 1
           , B.DESCRIPTION
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.WKCTR_ID)) AS SORT
        FROM TAWKCTR B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_WKCTR_ID = C.WKCTR_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)