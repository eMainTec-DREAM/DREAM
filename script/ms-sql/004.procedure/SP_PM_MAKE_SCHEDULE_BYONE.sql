CREATE PROCEDURE [dbo].[SP_PM_MAKE_SCHEDULE_BYONE] (
      @v_comp_no      varchar(6)
      ,@v_pm_id       bigint
      ,@v_user_id     bigint
      ,@v_target_date varchar(8)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint;
    DECLARE @v_last_sch_date  varchar(8)
            ,@v_next_sch_date varchar(8)
            ,@v_to_day        varchar(8)
            ,@v_is_work_calendar varchar(20)

    select @v_to_day = CONVERT(varchar(8), getdate(), 112)

    -- use work calendar
    select @v_count = count(*)
    from taconfig
    where comp_no = @v_comp_no
        and name = 'IS_USE_WORK_CALENDAR'
    ;
    
    if @v_count > 0
        select @v_is_work_calendar = isnull(value$,'N')
        from taconfig
        where comp_no = @v_comp_no
            and name = 'IS_USE_WORK_CALENDAR'
        ;
    else
        select @v_is_work_calendar = 'N'
        ;            
                
    
    DECLARE c_pm CURSOR FOR
        SELECT
             a.pm_id
            ,a.equip_id
            ,a.schedule_type -- T:Time, U:Usage
            ,a.cycle
            ,a.period_type 
            ,case when isnull(a.USAGE, 99999999999999) <=0 then 99999999999999 else isnull(a.USAGE, 99999999999999)*60 end as usage  --분으로 보정작업함. 
            ,a.last_sch_date    -- 다음일정 예정일[주말보정전]
            ,isnull(b.plant,(select top 1 plant FROM TAPLANT where comp_no= @v_comp_no)) plant
            ,b.eqloc_id
        FROM TAPMLST a left outer join taeqloc b on a.comp_no = b.comp_no and a.eqloc_id = b.eqloc_id
        WHERE 1=1
            AND a.comp_no = @v_comp_no
            AND a.pm_id     = @v_pm_id
            AND a.is_active = 'Y'
            AND a.last_sch_date <= @v_target_date
        ; 
        
    DECLARE @c_pm_id bigint
            ,@c_equip_id bigint
            ,@c_schedule_type varchar(20)
            ,@c_cycle varchar(20)
            ,@c_period_type varchar(20)
            ,@c_usage numeric(18)
            ,@c_last_sch_date varchar(8)
            ,@c_plant varchar(20)
            ,@c_eqloc_id bigint
        
    OPEN c_pm
    FETCH NEXT FROM c_pm INTO @c_pm_id,@c_equip_id,@c_schedule_type,@c_cycle,@c_period_type,@c_usage,@c_last_sch_date,@c_plant,@c_eqloc_id
    
    WHILE (@@FETCH_STATUS=0)
    BEGIN
        -----------------------------------------------------------------------------------------------------------------------------------------
        -----------------------------------------------------------------------------------------------------------------------------------------
        IF @C_schedule_type = 'T'    -- Period Time Based Scheduling
            BEGIN

                SELECT @V_COUNT = COUNT(*)
                FROM TAPMSCHED
                WHERE comp_no = @V_COMP_NO
                    AND pm_id = @C_pm_id
                    AND pmsched_status IN ('C') -- Schedlued, Completed.
                    AND plan_date >= @C_last_sch_date
                ;
                    
                IF @v_count > 0
                    BEGIN
                        SELECT @v_last_sch_date = ISNULL(MAX(plan_date),@C_last_sch_date)
                        FROM TAPMSCHED
                        WHERE comp_no = @V_COMP_NO
                            AND pm_id = @C_pm_id
                            AND pmsched_status IN ('C') -- Schedlued, Completed.
                            AND plan_date >= @C_last_sch_date
                        ;
                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = @V_COMP_NO
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                            WHERE comp_no = @V_COMP_NO
                                                               AND pm_id = @C_pm_id
                                                               AND pmsched_status IN ('P','S') -- Planned.
                                                               AND plan_date >= @v_last_sch_date )
                       ;
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = @V_COMP_NO
                           AND pm_id = @C_pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= @v_last_sch_date
                       ;
                       
                       SELECT @v_last_sch_date =    
                            CASE 
                                 WHEN @C_period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@C_cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                                 WHEN @C_period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@C_cycle * 7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                                 WHEN @C_period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@C_cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                                 WHEN @C_period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@C_cycle * 12 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                            END
                        ;
                    END
                ELSE 
                    BEGIN
                        SELECT @v_last_sch_date = @C_last_sch_date;

                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = @V_COMP_NO
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                           WHERE comp_no = @V_COMP_NO
                                                               AND pm_id = @C_pm_id
                                                               AND pmsched_status IN ('P','S') -- Planned.
                                                               AND plan_date >= @v_last_sch_date )
                       ;
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = @V_COMP_NO
                           AND pm_id = @C_pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= @v_last_sch_date
                       ;
                    END
                           
                    
                UPDATE TAPMLST SET
                       last_sch_date = @v_last_sch_date
                      ,next_sch_date = @v_last_sch_date
                WHERE comp_no = @V_COMP_NO
                      AND pm_id = @C_pm_id
                ; 
                    
                    
                WHILE @v_last_sch_date <= @v_target_date
                    BEGIN
                        -- 생성할 일자가 주말이면 주말이 아닌 다음날짜를 찾기...
                        if @v_is_work_calendar = 'Y'
                            select  @v_next_sch_date = MIN(cal_date)
                            from TAWRKCALENDAR a
                            where a.comp_no = @V_COMP_NO
                                and a.plant = @C_plant
                                and a.cal_date >= @v_last_sch_date
                                and a.is_work = 'Y'
                            ;
                        else
                            SELECT  @v_next_sch_date = MIN(tday)
                            FROM TADAY
                            WHERE tday >= @v_last_sch_date
                                AND dow NOT IN ('1','7')
                            ;
                        
                        
                        -- 월간 작업일 경우 달을 넘기지 않도록 한다.
                        IF @v_next_sch_date IS NOT NULL
                            BEGIN
                        
                                IF @C_period_type = 'M' AND LEFT(@v_last_sch_date,6) <> LEFT(@v_next_sch_date,6) 
                                    BEGIN
                                        if @v_is_work_calendar = 'Y'
                                            select @v_next_sch_date =  max(cal_date)
                                            from TAWRKCALENDAR a
                                            where a.comp_no = @V_COMP_NO
                                                and a.plant = @C_plant
                                                and a.cal_date <=@v_last_sch_date
                                                and a.is_work = 'Y'
                                            ;
                                        else
                                            SELECT  @v_next_sch_date = MAX(tday)
                                            FROM TADAY
                                            WHERE tday <= @v_last_sch_date
                                                AND dow NOT IN ('1','7')
                                            ;
                                    END
                                
                                IF @v_next_sch_date > @v_to_day
                                     INSERT INTO TAPMSCHED(comp_no, pmsched_id, pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
                                     VALUES(@V_comp_no,NEXT VALUE FOR sqapmsched_id, @C_pm_id, @v_last_sch_date, @v_next_sch_date, @v_next_sch_date,'P' )
                                     ;

                               
                                UPDATE TAPMLST SET
                                       last_sch_date = @v_last_sch_date
                                      ,next_sch_date = @v_next_sch_date
                                WHERE comp_no = @V_COMP_NO
                                      AND pm_id = @C_pm_id
                                ; 
                                
                                -- 주기가 일일일 경우 휴일이면 스케쥴이 건너뜀..
                                -- 1D, 2D, 3D, 4D도 모두 포함...
                                IF @C_period_type = 'D'
                                    SELECT @v_last_sch_date = @v_next_sch_date;

                        
                                SELECT @v_last_sch_date = CASE 
                                                                 WHEN @C_period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@C_cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                                                                 WHEN @C_period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@C_cycle * 7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                                                                 WHEN @C_period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@C_cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                                                                 WHEN @C_period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@C_cycle * 12 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                                                          END
                            END
                            
                    END   -- WHILE END
                    
                
            END -- IF END
        IF @C_schedule_type = 'U'     -- Run Time Based Scheduling
            BEGIN
               --.해당 pm번호에 해당하는 가동라인 알아내기
               -- 가동라인에 설정한 일자 이후에 가동시간을 합계하고 가동시간을 초과하는 일자가 며칠인지 알아내기
               -- 이 일자로 일정을 생성하기.
                SELECT @v_count = COUNT(*)
                FROM TAPMSCHED
                WHERE comp_no = @V_comp_no
                    AND pm_id = @C_pm_id
                    AND pmsched_status IN ('C') -- Schedlued, Completed.
                    AND plan_date >= @C_last_sch_date
                ;
                
                IF @v_count > 0
                    BEGIN
                        SELECT @v_last_sch_date = ISNULL(MAX(plan_date),@C_last_sch_date)
                        FROM TAPMSCHED
                        WHERE comp_no = @V_comp_no
                            AND pm_id = @C_pm_id
                            AND pmsched_status IN ('C') -- Schedlued, Completed.
                            AND plan_date >= @C_last_sch_date
                        ;
                        
                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = @V_comp_no
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                            WHERE comp_no = @V_comp_no
                                                               AND pm_id = @C_pm_id
                                                               AND pmsched_status IN ('P','S') -- Planned.
                                                               AND plan_date >= @C_last_sch_date )
                       ;
                        
                        
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = @V_comp_no
                           AND pm_id = @C_pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= @C_last_sch_date
                       ;
                   END
                ELSE 
                    BEGIN
                        SELECT @v_last_sch_date = @C_last_sch_date;
                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = @V_comp_no
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                            WHERE comp_no = @V_comp_no
                                                                   AND pm_id = @C_pm_id
                                                                   AND pmsched_status IN ('P','S') -- Planned.
                                                                   AND plan_date >= @v_last_sch_date )
                       ;
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = @V_comp_no
                           AND pm_id = @C_pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= @v_last_sch_date
                        ;
                     END
                
                WHILE @v_last_sch_date < @v_target_date
                    BEGIN
                
                       
                       IF @v_last_sch_date > @v_to_day
                             INSERT INTO TAPMSCHED(comp_no, pmsched_id, pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
                             VALUES(@V_comp_no,NEXT VALUE FOR sqapmsched_id, @C_pm_id, @v_last_sch_date, @v_last_sch_date, @v_last_sch_date,'P' )
                             ;

                       
                       UPDATE TAPMLST SET
                               last_sch_date = @v_last_sch_date
                       WHERE comp_no = @V_comp_no
                              AND pm_id = @C_pm_id
                       ; 
                       
                       select @v_next_sch_date = min(wrk_date)
                       from (
                               select 
                                    a.wrk_date
                                   ,sum(ISNULL(sum(a.dtime),0) + ISNULL(sum(a.ntime),0) + ISNULL(sum(a.etime),0)) over (order by a.wrk_date) run_time
                               from TALNWRKTIME a
                               where a.comp_no = @V_comp_no
                                   and a.plant = @C_plant
                                   and a.wrk_date > @v_last_sch_date
                                   and a.eqloc_id in (SELECT eqloc_id FROM dbo.SFAEQCTG_CALL(@V_comp_no, @C_eqloc_id, ''))
                               group by a.wrk_date 
                           ) a
                       where a.run_time >= @C_usage  
                       ;
                       
                        IF @v_next_sch_date IS NOT NULL
                               UPDATE TAPMLST SET
                                       next_sch_date = @v_next_sch_date
                                WHERE comp_no = @V_comp_no
                                      AND pm_id = @C_pm_id
                                ; 
                       
                       SELECT @v_last_sch_date = ISNULL(@v_next_sch_date,'99991231');
                    END
                    
            END  --ELSE IF END        
        -----------------------------------------------------------------------------------------------------------------------------------------
        -----------------------------------------------------------------------------------------------------------------------------------------
        FETCH NEXT FROM c_pm INTO @c_pm_id,@c_equip_id,@c_schedule_type,@c_cycle,@c_period_type,@c_usage,@c_last_sch_date,@c_plant,@c_eqloc_id
    END
    CLOSE c_pm
    DEALLOCATE c_pm
    

    UPDATE TABATPGM SET 
         exe_by = @v_user_id
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    WHERE comp_no = @v_comp_no
        AND batpgm_no = 'TAPMSCHED_ONE';