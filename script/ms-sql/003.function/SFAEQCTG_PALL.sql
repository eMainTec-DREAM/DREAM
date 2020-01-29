CREATE FUNCTION [dbo].[SFAEQCTG_PALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @EQCTG_ID BIGINT
    , @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.EQCTG_ID
           , A.P_EQCTG_ID
           , 1 AS LVL
           , A.DESCRIPTION
        FROM TAEQCTG A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
            and EQCTG_ID = CASE @EQCTG_ID WHEN '' THEN EQCTG_ID ELSE @EQCTG_ID END
            and upper(FULL_DESC) like CASE @EQCTG_ID WHEN '' THEN '%'+upper(@DESCRIPTION)+'%' ELSE upper(FULL_DESC) END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.EQCTG_ID
           , B.P_EQCTG_ID
           , C.LVL+ 1
           , B.DESCRIPTION
        FROM TAEQCTG B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.EQCTG_ID = C.P_EQCTG_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT * FROM DATATABLE
)

-- 2017.06.01 차한결추가. 재귀함수 정렬 추가
ALTER FUNCTION [dbo].[SFAEQCTG_PALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @EQCTG_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.EQCTG_ID
           , A.P_EQCTG_ID
           , 1 AS LVL
           , A.DESCRIPTION
		   , CONVERT(VARCHAR, A.EQCTG_ID) AS SORT
        FROM TAEQCTG A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and EQCTG_ID = CASE @EQCTG_ID WHEN '' THEN EQCTG_ID ELSE @EQCTG_ID END
			and upper(FULL_DESC) like CASE @EQCTG_ID WHEN '' THEN upper(@DESCRIPTION)+'%' ELSE upper(FULL_DESC) END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.EQCTG_ID
           , B.P_EQCTG_ID
           , C.LVL+ 1
           , B.DESCRIPTION
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.EQCTG_ID)) AS SORT
        FROM TAEQCTG B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.EQCTG_ID = C.P_EQCTG_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)

-- 2018.08.10 김영주추가. ISNULL 추가
ALTER FUNCTION [dbo].[SFAEQCTG_PALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @EQCTG_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.EQCTG_ID
           , A.P_EQCTG_ID
           , 1 AS LVL
           , A.DESCRIPTION
		   , CONVERT(VARCHAR, A.EQCTG_ID) AS SORT
        FROM TAEQCTG A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and EQCTG_ID = CASE @EQCTG_ID WHEN '' THEN EQCTG_ID ELSE @EQCTG_ID END
			and ISNULL(upper(FULL_DESC),'') like CASE @EQCTG_ID WHEN '' THEN '%'+upper(@DESCRIPTION)+'%' ELSE ISNULL(upper(FULL_DESC),'') END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.EQCTG_ID
           , B.P_EQCTG_ID
           , C.LVL+ 1
           , B.DESCRIPTION
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.EQCTG_ID)) AS SORT
        FROM TAEQCTG B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.EQCTG_ID = C.P_EQCTG_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)