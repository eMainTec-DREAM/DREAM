CREATE PROCEDURE [dbo].[SP_PM_MAKE_TALNWRKTIME] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count           bigint
            ,@v_dow_count      int
            ,@v_dow_dtime      bigint
            ,@v_dow_ntime      bigint
            ,@v_dow_etime      bigint
            ,@v_lnwrktime_id   bigint

    DECLARE c_data CURSOR FOR
        select 
            eqloc_id
           ,plant
           ,isnull(dtime,0) as dtime
           ,isnull(ntime,0) as ntime
           ,isnull(etime,0) as etime
        from taeqloc 
        where 1=1
            and comp_no = @v_comp_no
            and is_operation = 'Y'
            and is_use = 'Y'
        order by plant, eqloc_no
        ;
    
    --c_data cursor parameter
    DECLARE @c_eqloc_id as bigint
           ,@c_plant    as varchar(20)
           ,@c_dtime    as bigint
           ,@c_ntime    as bigint
           ,@c_etime    as bigint   

    --c_day cursor parameter
    DECLARE @c_tday as varchar(20)
    
    
    OPEN c_data
    FETCH NEXT FROM c_data INTO @c_eqloc_id,@c_plant,@c_dtime,@c_ntime,@c_etime
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            
            
            DECLARE c_day CURSOR FOR
                select cal_date as tday
                from TAWRKCALENDAR
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and is_work = 'Y'
                    and cal_date >= CONVERT(varchar(8), getdate(), 112)
                    --and cal_date >='20160829'
                    and cal_date <= CONVERT(varchar(8), DATEADD(month,1,GETDATE()), 112)
               ;
                
            OPEN c_day
            FETCH NEXT FROM c_day INTO @c_tday
            WHILE (@@FETCH_STATUS=0)
                BEGIN
                
                    select @v_count = count(*)
                    from TALNWRKTIME
                    where comp_no = @v_comp_no
                        and plant = @c_plant
                        and eqloc_id = @c_eqloc_id
                        and wrk_date = @c_tday
                    ;
                    
                    
                    select @v_dow_count = count(*)
                    from TAEQLOCDOWRUN
                    where 1=1
                    and comp_no = @v_comp_no
                    and eqloc_id = @c_eqloc_id
                    and is_use='Y'
                    and dow = (   select dow
                                  from taday
                                  where tday=@c_tday    )
                    ;
                    
                    
                    
                    if @v_count = 0 
                       begin
                            select @v_lnwrktime_id = NEXT VALUE FOR sqalnwrktime_id
                             
                             if @v_dow_count > 0
                                 begin
                                     select @v_dow_dtime = isnull(dtime,0), @v_dow_ntime = isnull(ntime,0), @v_dow_etime = isnull(etime,0)
                                     from TAEQLOCDOWRUN
                                     where 1=1
                                     and comp_no = @v_comp_no
                                     and eqloc_id = @c_eqloc_id
                                     and is_use='Y'
                                      and dow = ( select dow
                                                  from taday
                                                  where tday=@c_tday    )
                                      ;
                            
                                    insert into TALNWRKTIME (comp_no, lnwrktime_id, plant, wrk_date, eqloc_id, dtime, ntime, etime )
                                    values(
                                           @v_comp_no, @v_lnwrktime_id, @c_plant, @c_tday, @c_eqloc_id, @v_dow_dtime,@v_dow_ntime,@v_dow_etime
                                        );
                                 end
                                    
                                    
                            else
                            
                                insert into TALNWRKTIME (comp_no, lnwrktime_id, plant, wrk_date, eqloc_id, dtime, ntime, etime )
                                values(
                                       @v_comp_no, @v_lnwrktime_id, @c_plant, @c_tday, @c_eqloc_id, @c_dtime, @c_ntime, @c_etime
                                    );
                                    
                            
                       end
                
                
                    FETCH NEXT FROM c_day INTO @c_tday
                END
            CLOSE c_day
            DEALLOCATE c_day

            FETCH NEXT FROM c_data INTO @c_eqloc_id,@c_plant,@c_dtime,@c_ntime,@c_etime
        END
    CLOSE c_data
    DEALLOCATE c_data
    
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TALNWRKTIME'
    ;
