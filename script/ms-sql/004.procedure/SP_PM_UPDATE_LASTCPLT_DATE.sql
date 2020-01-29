USE [dream_dev]
GO
/****** Object:  StoredProcedure [dbo].[SP_PM_UPDATE_LASTCPLT_DATE]    Script Date: 2018-10-24 오후 6:46:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER  PROCEDURE [dbo].[SP_PM_UPDATE_LASTCPLT_DATE](
     @v_comp_no             varchar(6)
    ,@v_pm_id               bigint
    ,@v_sched_id            bigint
) as

    SET NOCOUNT ON;
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

	
	declare @v_count                                            numeric(4)
    declare @v_wo_type                                          varchar(20);
    declare @v_pm_type                                          varchar(20);   
    declare @v_last_cplt_date                                   varchar(8);
    declare @v_next_plan_date                                   varchar(8);
    declare @v_last_cplt_by                                     varchar(8);
    declare @v_pm_result_status                                 varchar(20);
  


    select
            @v_wo_type = isnull(max(a.wo_type),'XXX')
        ,@v_pm_type = isnull(max(a.pm_type),'YYY')
    from TAPMLST A
    where a.comp_no = @v_comp_no
        and a.pm_id     = @v_pm_id
    ;

         
    if @v_wo_type = 'PMI' and @v_pm_type in ('RINS','EINS')
	    begin
			select @v_last_cplt_date = max(actual_date)
			from TAPMINSSCHED a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminssched_id = @v_sched_id
									)
			;

			select top 1 @v_last_cplt_by = b.emp_id
			from TAPMINSSCHED a INNER JOIN TAPMINSLIST b
               ON a.comp_no = b.comp_no
               AND a.pminssched_id = b.pminssched_id
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
                and a.actual_date = @v_last_cplt_date
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminssched_id = @v_sched_id
									)
			;

			select top 1 @v_pm_result_status = b.result_value
			from TAPMINSSCHED a INNER JOIN TAPMINSLIST b
               ON a.comp_no = b.comp_no
               AND a.pminssched_id = b.pminssched_id
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
                and a.actual_date = @v_last_cplt_date
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminssched_id = @v_sched_id
									)
			;
               
			select @v_next_plan_date = min(plan_date)
			from TAPMINSSCHED a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status in ('P', 'S')
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminssched_id = @v_sched_id
									)
			;

			update TAPMEQUIP set 
				last_cplt_date = @v_last_cplt_date
				,next_plan_date = @v_next_plan_date
                ,last_cplt_by = @v_last_cplt_by
                ,pm_result_status = @v_pm_result_status
			where 1=1
				and comp_no = @v_comp_no
				and pmequip_id =  (select aa.pmequip_id
									from TAPMINSSCHED aa
									where aa.comp_no = @v_comp_no
											and aa.pminssched_id = @v_sched_id
									)
			;
		end
                      
                      
    else if @v_wo_type = 'PMI' and @v_pm_type in ('DINS')
	    begin
			select @v_last_cplt_date = max(actual_date)
			from TAPMINSDSCHED a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSDSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminsdsched_id = @v_sched_id
									)
			;

			select top 1 @v_last_cplt_by = b.emp_id
			from TAPMINSDSCHED a INNER JOIN TAPMINSDLIST b
               ON a.comp_no = b.comp_no
               AND a.pminsdsched_id = b.pminsdsched_id
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
                and a.actual_date = @v_last_cplt_date
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSDSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminsdsched_id = @v_sched_id
									)
			;

			select top 1 @v_pm_result_status = b.result_value
			from TAPMINSDSCHED a INNER JOIN TAPMINSDLIST b
               ON a.comp_no = b.comp_no
               AND a.pminsdsched_id = b.pminsdsched_id
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
                and a.actual_date = @v_last_cplt_date
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSDSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminsdsched_id = @v_sched_id
									)
			;
               
			select @v_next_plan_date = min(plan_date)
			from TAPMINSDSCHED a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status in('P', 'S')
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMINSDSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pminsdsched_id = @v_sched_id
									)
			;

			update TAPMEQUIP set 
				last_cplt_date = @v_last_cplt_date
				,next_plan_date = @v_next_plan_date
                ,last_cplt_by = @v_last_cplt_by
                ,pm_result_status = @v_pm_result_status
			where 1=1
				and comp_no = @v_comp_no
				and pmequip_id =  (select aa.pmequip_id
									from TAPMINSDSCHED aa
									where aa.comp_no = @v_comp_no
											and aa.pminsdsched_id = @v_sched_id
									)
			;
		end
               
    else if @v_wo_type = 'PMP' and @v_pm_type in ('PTRL')
	    begin
               
			select @v_last_cplt_date = max(actual_date)
			from TAPMPTRLSCHED a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMPTRLSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pmptrlsched_id = @v_sched_id
									)
			;

			select top 1 @v_last_cplt_by = b.emp_id
			from TAPMPTRLSCHED a INNER JOIN TAPMPTRLRSLTLIST b
               ON a.comp_no = b.comp_no
               AND a.pmptrlsched_id = b.pmptrlsched_id
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
                and a.actual_date = @v_last_cplt_date
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMPTRLSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pmptrlsched_id = @v_sched_id
									)
			;
               
			select @v_next_plan_date = min(plan_date)
			from TAPMPTRLSCHED a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status in('P', 'S')
				and a.pmequip_id = (select aa.pmequip_id
									from TAPMPTRLSCHED aa
									where aa.comp_no = @v_comp_no
										and aa.pmptrlsched_id = @v_sched_id
									)
			;

			update TAPMEQUIP set 
					last_cplt_date = @v_last_cplt_date
					,next_plan_date = @v_next_plan_date
					,last_cplt_by = @v_last_cplt_by
					,pm_result_status = @v_pm_result_status
			where 1=1
				and comp_no = @v_comp_no
				and pmequip_id =  (select aa.pmequip_id
									from TAPMPTRLSCHED aa
									where aa.comp_no = @v_comp_no
											and aa.pmptrlsched_id = @v_sched_id
									)
			;
		end
               

    else if @v_wo_type = 'XXX' and @v_pm_type in ('YYY')
         
        select 'Invalid wo_type,pm_type';
         
    else
	    begin
               
			select @v_last_cplt_date = max(actual_date)
			from tapmsched a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
				and a.pmequip_id = (select aa.pmequip_id
									from tapmsched aa
									where aa.comp_no = @v_comp_no
										and aa.pmsched_id = @v_sched_id
									)
			;
			
			select top 1 @v_last_cplt_by = b.emp_id
			from TAPMSCHED a INNER JOIN TAWORKORDER b
               ON a.comp_no = b.comp_no
               AND a.pmsched_id = b.pmsched_id
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
                and a.actual_date = @v_last_cplt_date
				and a.pmequip_id = (select aa.pmequip_id
									from tapmsched aa
									where aa.comp_no = @v_comp_no
										and aa.pmsched_id = @v_sched_id
									)
			;

			select top 1 @v_pm_result_status = c.calib_result_status
			from TAPMSCHED a INNER JOIN TAWORKORDER b
               ON a.comp_no = b.comp_no
               AND a.pmsched_id = b.pmsched_id
               INNER JOIN TAWOCALIB c
               ON b.comp_no = c.comp_no
               AND b.wkor_id = c.wkor_id
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status = 'C'
                and a.actual_date = @v_last_cplt_date
				and a.pmequip_id = (select aa.pmequip_id
									from tapmsched aa
									where aa.comp_no = @v_comp_no
										and aa.pmsched_id = @v_sched_id
									)
			;
               
			select @v_next_plan_date = min(plan_date)
			from tapmsched a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @v_pm_id
				and a.pmsched_status in('P', 'S')
				and a.pmequip_id = (select aa.pmequip_id
									from tapmsched aa
									where aa.comp_no = @v_comp_no
										and aa.pmsched_id = @v_sched_id
									)
			;

			update TAPMEQUIP set 
				last_cplt_date = @v_last_cplt_date
				,next_plan_date = @v_next_plan_date
				,last_cplt_by = @v_last_cplt_by
				,pm_result_status = @v_pm_result_status
			where 1=1
				and comp_no = @v_comp_no
				and pmequip_id =  (select aa.pmequip_id
									from tapmsched aa
									where aa.comp_no = @v_comp_no
											and aa.pmsched_id = @v_sched_id
									)
			;       
		end  
         