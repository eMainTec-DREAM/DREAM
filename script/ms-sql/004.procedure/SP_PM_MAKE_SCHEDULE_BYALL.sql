CREATE PROCEDURE [dbo].[SP_PM_MAKE_SCHEDULE_BYALL](
     @v_comp_no     varchar(6) 
    ,@v_user_no     varchar(40)
) as
    SET NOCOUNT ON;
    declare @v_count   as bigint
    declare @v_user_id as bigint
    declare @v_target_date as varchar(8)
    
    select @v_target_date = CONVERT(VARCHAR(8), DATEADD(MONTH,12,CAST(GETDATE() AS DATETIME)), 112) 
    
    
    DECLARE c_pm CURSOR FOR
        SELECT a.pm_id
        FROM TAPMLST a
        WHERE 1=1
            AND a.comp_no = @v_comp_no
            AND a.dept_id IN (SELECT dept_id from dbo.sfadept_call(@v_comp_no, 0, '') )
            AND a.is_active = 'Y'
            AND a.last_sch_date <= @v_target_date
    ;
    
    declare @c_pm_id as bigint
    
    SELECT @v_user_id = user_id 
    FROM TAUSER 
    WHERE comp_no = @v_comp_no
        AND user_no = @v_user_no 
    ;
    
    OPEN c_pm
    FETCH NEXT FROM c_pm INTO @c_pm_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
          --------------------------------------------------------------------------------------------------------------------------------
          
          exec SP_PM_MAKE_SCHEDULE_BYONE @v_comp_no, @c_pm_id, @v_user_id, @v_target_date

          --------------------------------------------------------------------------------------------------------------------------------
          FETCH NEXT FROM c_data INTO  @c_pm_id
          END
    CLOSE c_pm
    DEALLOCATE c_pm
    
    
    
    UPDATE TABATPGM SET 
         exe_by = @v_user_id
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    WHERE comp_no = @v_comp_no
        AND batpgm_no = 'TAPMSCHED_ALL'
    ;
 