CREATE PROCEDURE [dbo].[SP_RESET_WORKDAY] (
       @v_comp_no      varchar(6)
      ,@v_plant        varchar(20)
      ,@v_start_sched_date  varchar(8)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    declare @v_target_date    as varchar(8)
    declare @v_last_sched_date  varchar(8)
            ,@v_user_id        bigint
            
            
    set @v_target_date = CONVERT(VARCHAR(8),DATEADD(MONTH,12,CAST(GETDATE() AS DATETIME)), 112)
            
    DECLARE c_pm_data CURSOR FOR
        select 
              a.pm_id
            , a.init_wrk_date
            , a.schedule_type
        from tapmlst a, taeqloc b
        where 1=1
            and a.comp_no = b.comp_no
            and a.eqloc_id = b.eqloc_id
            and a.comp_no = @v_comp_no
            and b.plant = @v_plant
        ;
    
    --c_pm cursor parameter
    DECLARE @c_pm_id              bigint
            , @c_init_wrk_date    varchar(8)
            , @c_schedule_type    varchar(20)
            
            
     select @v_count = count(*) 
     from tauser 
     where comp_no = @v_comp_no
         and user_no = @v_user_no
     ;
     
     if @v_count > 0
        select top 1 @v_user_id = user_id
        from tauser
        where  comp_no = @v_comp_no
             and user_no = @v_user_no
        ;

     /*Line Run time ready to reschedule*/
    update TALNWRKTIME set 
                   dtime=0
                 , ntime=0
                 , etime=0
    where 1=1
        and comp_no = @v_comp_no
        and plant = @v_plant
        and wrk_date in ( select aa.cal_date
                                     from TAWRKCALENDAR aa
                                     where aa.comp_no = @v_comp_no
                                         and aa.plant = @v_plant
                                         and aa.cal_date >= @v_start_sched_date
                                         and aa.is_work = 'N'
                                   )
    ;
    
    /*All Default Run time is reseted*/
    exec SP_PM_MAKE_TALNWRKTIME @v_comp_no, @v_user_no

    
    /*PM Master schedule ready to reschedule*/
    OPEN c_pm_data
    FETCH NEXT FROM c_pm_data INTO @c_pm_id, @c_init_wrk_date, @c_schedule_type
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            select @v_last_sched_date = max(b.sched_date)
            from tapmlst a, tapmsched b
            where a.comp_no = b.comp_no
               and a.pm_id = b.pm_id
               and a.comp_no = @v_comp_no
               and a.pm_id = @c_pm_id
               and b.sched_date <=@v_start_sched_date
            ;
           
            if @v_last_sched_date is null 
                set @v_last_sched_date = @c_init_wrk_date;
            
            update tapmlst set 
                last_sch_date = @v_last_sched_date
            where comp_no = @v_comp_no
                and pm_id = @c_pm_id
            ;
            
             /*All Schedule is rescheduled*/
             exec SP_PM_MAKE_SCHEDULE_BYONE @v_comp_no, @c_pm_id, @v_user_id,@v_target_date  
             
             
             
        
            FETCH NEXT FROM c_pm_data INTO @c_pm_id, @c_init_wrk_date, @c_schedule_type
        END
    CLOSE c_pm_data
    DEALLOCATE c_pm_data
        
        
        
        