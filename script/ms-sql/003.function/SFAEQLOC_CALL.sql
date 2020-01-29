CREATE FUNCTION [dbo].[SFAEQLOC_CALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @EQLOC_ID BIGINT
    , @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.EQLOC_ID
           , A.P_EQLOC_ID
           , 1 AS LVL
           , A.DESCRIPTION
        FROM TAEQLOC A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
            and EQLOC_ID = CASE @EQLOC_ID WHEN '' THEN EQLOC_ID ELSE @EQLOC_ID END
            and upper(FULL_DESC) like CASE @EQLOC_ID WHEN '' THEN '%'+upper(@DESCRIPTION)+'%' ELSE upper(FULL_DESC) END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.EQLOC_ID
           , B.P_EQLOC_ID
           , C.LVL+ 1
           , B.DESCRIPTION
        FROM TAEQLOC B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_EQLOC_ID = C.EQLOC_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT * FROM DATATABLE
)

-- 2017.06.01 차한결추가. 재귀함수 정렬 추가
ALTER FUNCTION [dbo].[SFAEQLOC_CALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @EQLOC_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.EQLOC_ID
           , A.P_EQLOC_ID
           , 1 AS LVL
           , A.DESCRIPTION
		   , CONVERT(VARCHAR, A.EQLOC_ID) AS SORT
        FROM TAEQLOC A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and EQLOC_ID = CASE @EQLOC_ID WHEN '' THEN EQLOC_ID ELSE @EQLOC_ID END
			and upper(FULL_DESC) like CASE @EQLOC_ID WHEN '' THEN upper(@DESCRIPTION)+'%' ELSE upper(FULL_DESC) END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.EQLOC_ID
           , B.P_EQLOC_ID
           , C.LVL+ 1
           , B.DESCRIPTION
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.EQLOC_ID)) AS SORT
        FROM TAEQLOC B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_EQLOC_ID = C.EQLOC_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)

-- 2018.08.10 김영주추가. ISNULL 추가
ALTER FUNCTION [dbo].[SFAEQLOC_CALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @EQLOC_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.EQLOC_ID
           , A.P_EQLOC_ID
           , 1 AS LVL
           , A.DESCRIPTION
		   , CONVERT(VARCHAR, A.EQLOC_ID) AS SORT
        FROM TAEQLOC A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and EQLOC_ID = CASE @EQLOC_ID WHEN '' THEN EQLOC_ID ELSE @EQLOC_ID END
			and ISNULL(upper(FULL_DESC),'') like CASE @EQLOC_ID WHEN '' THEN '%'+upper(@DESCRIPTION)+'%' ELSE ISNULL(upper(FULL_DESC),'') END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.EQLOC_ID
           , B.P_EQLOC_ID
           , C.LVL+ 1
           , B.DESCRIPTION
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.EQLOC_ID)) AS SORT
        FROM TAEQLOC B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_EQLOC_ID = C.EQLOC_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)