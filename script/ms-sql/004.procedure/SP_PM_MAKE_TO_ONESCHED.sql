CREATE procedure [dbo].[SP_PM_MAKE_TO_ONESCHED](      @v_comp_no          varchar(6)         ,@v_pm_id            bigint) as    SET NOCOUNT ON;	declare @v_count                         numeric(4);         -- exec SP_PM_MAKE_WORKORDER('100','ADMIN');	DECLARE c_pm_sched_list CURSOR FOR        select              b.pm_type            ,b.wo_type            ,a.pmsched_id            ,b.pm_id        from TAPMSCHED a, TAPMLST b        where 1=1            and a.comp_no = b.comp_no            and a.pm_id = b.pm_id            and a.pm_id = @v_pm_id            and a.comp_no = @v_comp_no            and b.is_active = 'Y'            and a.pmsched_status = 'P' -- not release work order             and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)        order by sched_date        ;        	DECLARE c_pmins_sched_list CURSOR FOR        select              b.pm_type            ,b.wo_type            ,a.pminsdsched_id            ,b.pm_id        from TAPMINSDSCHED a, TAPMLST b        where 1=1            and a.comp_no = b.comp_no            and a.pm_id = b.pm_id            and a.pm_id = @v_pm_id            and a.comp_no = @v_comp_no            and b.is_active = 'Y'            and a.pmsched_status = 'P' -- not release work order             and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)        order by sched_date        ;        	DECLARE c_pminsday_sched_list CURSOR FOR        select              b.pm_type            ,b.wo_type            ,a.pminsdsched_id            ,b.pm_id        from TAPMINSDSCHED a, TAPMLST b        where 1=1            and a.comp_no = b.comp_no            and a.pm_id = b.pm_id            and a.comp_no = @v_comp_no            and b.is_active = 'Y'            and a.pmsched_status = 'P' -- not release work order             and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)        order by sched_date        ;        	DECLARE c_pmpatrol_sched_list CURSOR FOR        select              b.pm_type            ,b.wo_type            ,a.pmptrlsched_id            ,b.pm_id        from TAPMPTRLSCHED a, TAPMLST b        where 1=1            and a.comp_no = b.comp_no            and a.pm_id = b.pm_id            and a.comp_no = @v_comp_no            and b.is_active = 'Y'            and a.pmsched_status = 'P' -- not release work order             and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)        order by sched_date        ;		DECLARE   @pm_type    varchar(20)                 ,@wo_type    varchar(20)				 ,@pmsched_id bigint				 ,@pm_id      bigint            set @v_count = 0;		OPEN c_pm_sched_list		FETCH NEXT FROM c_pm_sched_list INTO @pm_type,@wo_type, @pmsched_id, @pm_id		WHILE (@@FETCH_STATUS=0)			BEGIN		        exec SP_PM_MAKE_TO_WO @v_comp_no, @pmsched_id;                if @v_count = 0                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pmsched_id;                set @v_count = @v_count + 1; 				FETCH NEXT FROM c_pm_sched_list INTO @pm_type,@wo_type, @pmsched_id, @pm_id			END		CLOSE c_pm_sched_list		DEALLOCATE c_pm_sched_list	      						set @v_count = 0;		DECLARE   @pminssched_id bigint		OPEN c_pmins_sched_list		FETCH NEXT FROM c_pmins_sched_list INTO @pm_type,@wo_type, @pminssched_id, @pm_id		WHILE (@@FETCH_STATUS=0)			BEGIN		        exec SP_PM_MAKE_TO_PMI @v_comp_no, @pminssched_id;                if @v_count = 0                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pminssched_id;                set @v_count = @v_count + 1; 				FETCH NEXT FROM c_pmins_sched_list INTO @pm_type,@wo_type, @pminssched_id, @pm_id			END		CLOSE c_pmins_sched_list		DEALLOCATE c_pmins_sched_list	    						set @v_count = 0;		DECLARE   @pminsdsched_id bigint		OPEN c_pminsday_sched_list		FETCH NEXT FROM c_pminsday_sched_list INTO @pm_type,@wo_type, @pminsdsched_id, @pm_id		WHILE (@@FETCH_STATUS=0)			BEGIN		        exec SP_PM_MAKE_TO_PMIDAY @v_comp_no, @pminsdsched_id;                if @v_count = 0                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pminsdsched_id;                set @v_count = @v_count + 1; 				FETCH NEXT FROM c_pminsday_sched_list INTO @pm_type,@wo_type, @pminsdsched_id, @pm_id			END		CLOSE c_pminsday_sched_list		DEALLOCATE c_pminsday_sched_list					      		set @v_count = 0;		DECLARE   @pmptrlsched_id bigint		OPEN c_pmpatrol_sched_list		FETCH NEXT FROM c_pmpatrol_sched_list INTO @pm_type,@wo_type, @pmptrlsched_id, @pm_id		WHILE (@@FETCH_STATUS=0)			BEGIN		        exec SP_PM_MAKE_TO_PMIDAY @v_comp_no, @pmptrlsched_id;                if @v_count = 0                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pmptrlsched_id;                set @v_count = @v_count + 1; 				FETCH NEXT FROM c_pmpatrol_sched_list INTO @pm_type,@wo_type, @pmptrlsched_id, @pm_id			END		CLOSE c_pmpatrol_sched_list		DEALLOCATE c_pmpatrol_sched_list