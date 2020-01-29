CREATE PROCEDURE [dbo].[SP_UPD_PART_NO] (
       @v_comp_no      varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    DECLARE @v_part_id        bigint
           ,@v_part_no        varchar(40)
           
    
    DECLARE c_pm CURSOR FOR
        select 
             part_id
            ,part_no
            ,description
            ,pt_size
            ,model
            ,maker
        from taparts a
        where 1=1
        order by comp_no, part_id
        ;
    
    --c_pm cursor parameter
    DECLARE  @part_id      bigint
            ,@part_no      nvarchar(80)
            ,@description  nvarchar(1024)
            ,@pt_size      nvarchar(1024)
            ,@model        nvarchar(400)
            ,@maker        nvarchar(400)

    update taparts set mro_type = 'R';
    
    OPEN c_pm
    FETCH NEXT FROM c_pm INTO @part_id,@part_no,@description,@pt_size,@model,@maker
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            update taparts set 
               full_desc = @part_no + ',' + @description + ',' + @pt_size+ ',' + @model + ',' + @maker
           where comp_no = @v_comp_no
               and part_id = @part_id
           ;
               
        
        
            FETCH NEXT FROM c_pm INTO @part_id,@part_no,@description,@pt_size,@model,@maker
        END
    CLOSE c_pm
    DEALLOCATE c_pm
    
    