CREATE FUNCTION [dbo].[SFADEPT_CALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @DEPT_ID BIGINT
    , @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.DEPT_ID
           , A.P_DEPT_ID
           , 0 AS LVL
        FROM TADEPT A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
            and DEPT_ID = CASE @DEPT_ID WHEN '' THEN DEPT_ID ELSE @DEPT_ID END
            and upper(description) like CASE @DEPT_ID WHEN '' THEN '%'+upper(@DESCRIPTION)+'%' ELSE upper(description) END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.DEPT_ID
           , B.P_DEPT_ID
           , C.LVL+ 1
        FROM TADEPT B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_DEPT_ID = C.DEPT_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT * FROM DATATABLE
)

-- 2017.06.01 차한결추가. 재귀함수 정렬 추가
ALTER FUNCTION [dbo].[SFADEPT_CALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @DEPT_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.DEPT_ID
           , A.P_DEPT_ID
           , 0 AS LVL
		   , CONVERT(VARCHAR, A.DEPT_ID) AS SORT
        FROM TADEPT A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and DEPT_ID = CASE @DEPT_ID WHEN '' THEN DEPT_ID ELSE @DEPT_ID END
			and upper(description) like CASE @DEPT_ID WHEN '' THEN upper(@DESCRIPTION)+'%' ELSE upper(description) END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.DEPT_ID
           , B.P_DEPT_ID
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.DEPT_ID)) AS SORT
        FROM TADEPT B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_DEPT_ID = C.DEPT_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)