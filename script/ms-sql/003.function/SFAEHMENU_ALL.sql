CREATE FUNCTION [dbo].[SFAEHMENU_ALL]
(
    @P_MENU_ID BIGINT
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.EHMENU_ID
           , A.P_EHMENU_ID
           , 1 AS LVL
        FROM TAEHMENU A
        WHERE 1=1
			and P_EHMENU_ID = @P_MENU_ID
        UNION ALL 
        SELECT 
             B.EHMENU_ID
           , B.P_EHMENU_ID
           , C.LVL+ 1
        FROM TAEHMENU B INNER JOIN DATATABLE C  ON B.P_EHMENU_ID = C.EHMENU_ID 
        WHERE 1=1
    ) 
    SELECT * FROM DATATABLE
)