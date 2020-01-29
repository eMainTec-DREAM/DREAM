CREATE FUNCTION [dbo].[SFACDUSRD_PALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @CDUSRD_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.cdusrd_id
		   , A.cdusrd_no
           , A.P_cdusrd_id
           , 0 AS LVL
		   , CONVERT(VARCHAR, A.cdusrd_id) AS SORT
        FROM TACDUSRD A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
            and cdusrd_id = CASE @cdusrd_id WHEN '' THEN cdusrd_id ELSE @cdusrd_id END
			and isnull(description,'') like CASE @cdusrd_id WHEN '' THEN '%'+@DESCRIPTION+'%' ELSE '%' END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.cdusrd_id
		   , B.cdusrd_no
           , B.P_cdusrd_id
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.cdusrd_id)) AS SORT
        FROM TACDUSRD B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.cdusrd_id = C.P_cdusrd_id AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)