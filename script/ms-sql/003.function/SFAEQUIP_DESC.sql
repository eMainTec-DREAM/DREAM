CREATE   FUNCTION [dbo].[SFAEQUIP_DESC](
     @p_equip_no NVARCHAR(40)
    ,@p_comp_no VARCHAR(6)
) 
RETURNS NVARCHAR(512)

BEGIN
      DECLARE @V_DESC AS NVARCHAR(512);
      DECLARE @description AS NVARCHAR(512);
      DECLARE @Cnt AS INT;
      
      DECLARE EQ_INFO CURSOR FOR
          select
             description
          from TAEQUIPMENT
          where 1=1
              AND COMP_NO = @p_comp_no
              AND ITEM_NO = @p_equip_no
          ;
      
      SET @V_DESC = '';
      SET @Cnt = 0;
      
      OPEN EQ_INFO
      FETCH NEXT FROM EQ_INFO INTO @description
      WHILE (@@FETCH_STATUS=0)
          BEGIN
             IF @Cnt = 0  
                SET @V_DESC = @description
             ELSE
                SET @V_DESC = @V_DESC + '-' + @description
             
             SET @Cnt = @Cnt + 1
          
          
              FETCH NEXT FROM EQ_INFO INTO  @description
          END
      CLOSE EQ_INFO
      DEALLOCATE EQ_INFO
    
 RETURN @V_DESC
   
END