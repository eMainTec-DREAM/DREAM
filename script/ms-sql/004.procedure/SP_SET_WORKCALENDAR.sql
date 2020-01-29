CREATE PROCEDURE [dbo].[SP_SET_WORKCALENDAR] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    DECLARE @v_is_work        varchar(20)
    declare @start_month      int
            ,@end_month       int
    set @start_month = 112
    set @end_month = 120
    
    
    /*set default value in calendar and work calendar*/
    DECLARE c_calendar_data CURSOR FOR
        select  
            CONVERT(VARCHAR(8),DATEADD(DAY,(RN-1), DATEADD(MONTH, @end_month, DATEADD(MONTH,-2, GETDATE()))), @start_month) AS tday
           ,CONVERT(VARCHAR(6),DATEADD(DAY,(RN-1), DATEADD(MONTH, @end_month, DATEADD(MONTH,-2, GETDATE()))), @start_month) AS tmonth
           ,CONVERT(VARCHAR(4),DATEADD(DAY,(RN-1), DATEADD(MONTH, @end_month, DATEADD(MONTH,-2, GETDATE()))), @start_month) AS tyear
           ,CONVERT(VARCHAR(1), DATEPART(weekday,  DATEADD(DAY,(RN-1), DATEADD(MONTH, @end_month, DATEADD(MONTH,-2, GETDATE()))))) AS dow
           ,DATEPART(iso_week, DATEADD(DAY,(RN-1), DATEADD(MONTH, @end_month, DATEADD(MONTH,-2, GETDATE())))) AS week
           ,CONVERT(VARCHAR(2), DATEPART(quarter,  DATEADD(DAY,(RN-1), DATEADD(MONTH, @end_month, DATEADD(MONTH,-2, GETDATE()))))) AS tquarter
        from (
            SELECT top 365 ROW_NUMBER() over (order by name asc)  as rn
            FROM sys.all_columns 
        ) A
        order by 1
      ;
    -- c_plant cursor parameter
    DECLARE   @tday    varchar(8)
             ,@tmonth  varchar(6)
             ,@tyear   varchar(4)
             ,@dow     varchar(1)    --요일1:일, 2:월...7:토
             ,@week    int  --ISO 표준주차(월 ~ 일)
             ,@tquarter varchar(1)  --분기..
    
    OPEN c_calendar_data
    FETCH NEXT FROM c_calendar_data INTO @tday,@tmonth,@tyear,@dow,@week,@tquarter
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            select @v_count = count(*)
            from taday
            where 1=1
                and tday = @tday
            ;
              
            if @v_count  = 0
                 insert into taday(seq, tday,tmonth, tquarter, thalf, tyyyy,week,dow)
                 values( (select max(seq) + 1 from taday), @tday, @tmonth, @tquarter
                             ,case when @tquarter < '3' then '1' else '2' end
                             , @tyear, @week, @dow
                 );
            
            select @v_count = count(*)
            from tamonth
            where 1=1
                and tmonth = @tmonth
            ;
              
            if @v_count  = 0
                insert into tamonth(seq, tmonth,tquarter,thalf,tyyyy)
                values( (select max(seq) + 1 from tamonth), @tmonth, @tquarter
                               ,case when @tquarter < '3' then '1' else '2' end
                               , @tyear
                );
                
            select @v_count = count(*)
            from tayear
            where 1=1
                and tyyyy = @tyear
            ;
              
            if @v_count  = 0
                 insert into tayear(seq, tyyyy)
                 values( (select max(seq) + 1 from tayear) , @tyear
                 );
            
        
            FETCH NEXT FROM c_calendar_data INTO @tday,@tmonth,@tyear,@dow,@week,@tquarter
        END
    CLOSE c_calendar_data
    DEALLOCATE c_calendar_data
    
    
    
    /*set default value in calendar and work calendar*/
    DECLARE c_plant CURSOR FOR
        select plant 
        from TAPLANT
        where comp_no = @v_comp_no
       ;
    
    -- c_plant cursor parameter
    DECLARE @c_plant       varchar(20)
    
    
    DECLARE c_wrk_calendar_data CURSOR FOR
        select 
             tday
            ,dow
        from taday
        where   1=1
            and tday >= convert(varchar(8), DATEADD(month,-2,GETDATE()), 112)
            and tday <= convert(varchar(8), dateadd(month, 24, DATEADD(month,-2,GETDATE())), 112)
        order by tday    
        ;
        
    -- c_wrk_calendar_data cursor parameter
    DECLARE @c_tday       varchar(20)
            ,@c_dow       varchar(20)
            
            
            
    OPEN c_wrk_calendar_data
    FETCH NEXT FROM c_wrk_calendar_data INTO @c_tday,@c_dow
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            OPEN c_plant
            FETCH NEXT FROM c_plant INTO @c_plant
            WHILE (@@FETCH_STATUS=0)
                BEGIN
                
                    select @v_count = count(*)
                    from TAWRKCALENDAR
                    where 1=1
                        and plant = @c_plant
                        and cal_date = @c_tday
                    ;
                    
                    
                    if @v_count = 0
                        begin 
                            if @c_dow in ('1','7')
                                set @v_is_work = 'N'; 
                        end 
                    else
                        -- 국각별 국경일 추가하여 적용필요...
                        begin
                            if  right(@c_tday,4) in ('0101','0501','0508','0705','0706','0928','1028','1117','1224','1225','1226')
                                  set @v_is_work = 'N'; 
                              else
                                   set @v_is_work = 'Y'; 
                        end
                        
                                        
                    insert into TAWRKCALENDAR(comp_no, wrkcalendar_id, plant, cal_date, is_work)
                    values(@v_comp_no, NEXT VALUE FOR sqawrkcalendar_id, @c_plant,@c_tday,@v_is_work );
              
              
              
                
                
                    FETCH NEXT FROM c_plant INTO @c_plant
                END
            CLOSE c_plant
            DEALLOCATE c_plant
            
        
            FETCH NEXT FROM c_wrk_calendar_data INTO @c_tday,@c_dow
        END
    CLOSE c_wrk_calendar_data
    DEALLOCATE c_wrk_calendar_data
    
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(6), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAWRKCALENDAR'
    ;
    