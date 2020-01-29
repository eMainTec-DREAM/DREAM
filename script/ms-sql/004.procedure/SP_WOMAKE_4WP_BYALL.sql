CREATE PROCEDURE [dbo].[SP_WOMAKE_4WP_BYALL](
     @v_comp_no     varchar(6) 
) as
    SET NOCOUNT ON;
    declare @v_count   as bigint
    declare @v_start_date as varchar(8)
    declare @v_to_date as varchar(8)
    
    DECLARE c_workorder CURSOR FOR
        select a.wkor_id
        from TAWORKORDER a
        where 1=1
               and a.wkor_date >= @v_start_date
               and a.wkor_date <= @v_to_date
               and a.wo_status = 'P'
        ;
        
    -- c_workorder cursor parameter
    DECLARE @c_wkor_id     bigint
    
    set @v_start_date =  convert(varchar(8), DATEADD(DAY,-31,getdate()) , 112)
    set @v_to_date    =  convert(varchar(8), DATEADD(DAY,31,getdate()) , 112)
    
    OPEN c_workorder
    FETCH NEXT FROM c_workorder INTO @c_wkor_id
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            exec SP_WOMAKE_4WP_BYONE @v_comp_no, @c_wkor_id
        
            FETCH NEXT FROM c_workorder INTO @c_wkor_id
        END
    CLOSE c_workorder
    DEALLOCATE c_workorder
   