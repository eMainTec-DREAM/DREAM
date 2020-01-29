USE [dream]
GO
/****** Object:  StoredProcedure [dbo].[SP_PM_MAKE_TO_WO]    Script Date: 2018-05-11 오전 8:37:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[SP_PM_MAKE_TO_WO] (
      @v_comp_no     varchar(6)      
     ,@v_pmsched_id  bigint
) as

    SET NOCOUNT ON;
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

    declare @v_count                   numeric(4); 
    declare @v_wo_res_before           numeric(4);  
    declare @v_wo_id                   bigint;
    declare @v_wo_no                   numeric(18);  
    declare @v_sched_date              varchar(8);
    declare @v_is_work                 varchar(20);
    declare @v_wo_type                 varchar(20);
    declare @v_is_work_plan            varchar(20);
    declare @v_wo_status               varchar(20);
	
	DECLARE c_pm_sched_id_SP_PM_MAKE_TO_WO CURSOR FOR
        select 
             b.pm_id
            ,(select aa.wcode_id from tadept aa where b.comp_no = aa.comp_no and b.dept_id = aa.dept_id) as wcode_id
            ,b.wo_type
            ,b.pm_type
            ,a.pmsched_id
            ,a.sched_date
            ,a.pmequip_id
            ,a.equip_id
			,c.eqasmb_id
        from TAPMSCHED a, TAPMLST b, TAPMEQUIP c
        where 1=1
            and a.comp_no = b.comp_no
            and a.pm_id = b.pm_id
			and b.comp_no = c.comp_no
			and b.pm_id = c.pm_id
            and b.is_active = 'Y'
			and c.is_deleted = 'N'
			and c.is_active = 'Y'
            and a.pmsched_status = 'P' -- not release work order 
            and a.comp_no = @v_comp_no
            and a.pmsched_id = @v_pmsched_id
        ;


    DECLARE   @pm_id       bigint
             ,@wcode_id    bigint
             ,@wo_type     varchar(20)
             ,@pm_type     varchar(20)
             ,@pmsched_id  bigint
             ,@sched_date  varchar(8)
             ,@pmequip_id  bigint
             ,@equip_id    bigint
			 ,@eqasmb_id    bigint



	-- Y인경우 작업계획(TAWOPLAN)으로 발행
    -- N인경우 작업계획(TAWOPLAN), 작업실적(TAWORKORDER) 2가지 모두 발행 
    -- 작업계획을 자동 확정되고 사용자는 작업실적을 사용
    select @v_count = count(*)
    from taconfig
    where comp_no = @v_comp_no
        and name = 'IS_WORK_PLAN'
    ;
    
    if @v_count > 0
        select @v_is_work_plan = isnull(value$,'N')
        from taconfig
        where comp_no = @v_comp_no
            and name = 'IS_WORK_PLAN'
        ;
    else
        select @v_is_work_plan = 'N'
        ;            

	if @v_is_work_plan = 'N' 
        select @v_wo_status = 'PRW' -- 작업결과작성중
        ;
    else
        select @v_wo_status = 'P'	-- 작업대기
        ; 

	-- 작업계획(TAWOPLAN) 발행 
	OPEN c_pm_sched_id_SP_PM_MAKE_TO_WO
    FETCH NEXT FROM c_pm_sched_id_SP_PM_MAKE_TO_WO INTO @pm_id,@wcode_id,@wo_type,@pm_type,@pmsched_id,@sched_date,@pmequip_id,@equip_id,@eqasmb_id
    WHILE (@@FETCH_STATUS=0)
        BEGIN

			select @v_wo_id = NEXT VALUE FOR sqawkor_id;

			select @v_wo_type = a.wo_type
			from tapmlst a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @pm_id
			;
                     
            insert into TAWOPLAN(
                comp_no
				,wkor_id
				,wo_no
				,description
				,wo_status 
				,wo_type
				,pm_type
				,wo_gen_type
				,start_date
				,end_date
				,wkor_date
				,dept_id
				,emp_id
				,perform
				,pm_id
				,pmsched_id
				,upd_date
				,upd_by
				,wkctr_id
				,plant
			)
			select 
				 @v_comp_no  as comp_no
				,@v_wo_id      as wkor_id
				,@v_wo_id      as wo_no
				,a.description  as description
				,@v_wo_status   as wo_status -- stand by 
				,a.wo_type      as wo_type  -- preventmaint
				,a.pm_type      as pm_type
				,'PMPLAN'        as wo_gen_type
				,@sched_date  as start_date
				,@sched_date  as end_date
				,@sched_date  as wkor_date
				,a.dept_id         as dept_id
				,a.emp_id        as emp_id
				,a.remark         as perform
				,@pm_id         as pm_id
				,@pmsched_id  as pmsched_id
				,CONVERT(varchar(8), getdate(), 112) as upd_date
				,a.emp_id          as upd_by
				,a.wkctr_id
				,a.plant
			from tapmlst a
			where 1=1
				and a.comp_no = @v_comp_no
				and a.pm_id = @pm_id
			;
                    
			if(@v_wo_type='PMC')
				BEGIN
					INSERT INTO TAWOCALIB (comp_no , wkor_id , calib_type) VALUES (@v_comp_no,@v_wo_id,'R');
				END

            -- tawoequip 
            insert into tawoequip(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description)
            select a.comp_no, NEXT VALUE FOR sqawoequip_id,  @v_wo_id,  a.equip_id, a.eqctg_id, a.description 
            from taequipment a
            where a.comp_no = @v_comp_no
                and a.equip_id = @equip_id
            ;
                    
            -- parts
            insert into tawoparts(
                comp_no
                ,wopart_id
                ,wkor_id
                ,wcode_id
                ,part_id
                ,part_grade
                ,use_qty
                ,unit_price
                ,tot_price
            )
            select @v_comp_no as comp_no
                ,NEXT VALUE FOR sqawopart_id as wopart_id
                ,@v_wo_id     as wkor_id
                ,@wcode_id as wcode_id
                ,a.part_id     as part_id
                --,'A'              as part_grade
				,isnull((select VALUE$ from taconfig where comp_no = @v_comp_no and name = 'PART_GRADE'),'A') as part_grade
                ,a.use_qty   as use_qty
                ,b.last_price  as unit_price
                ,a.use_qty * b.last_price as tot_price
            from tapmpart a
                ,taparts b
            where a.comp_no = b.comp_no
                and a.part_id = b.part_id
                and a.comp_no = @v_comp_no
                and a.pm_id =  @pm_id
            ;

            -- inspection point
            insert into tawopoint(
                comp_no
                ,wopoint_id
                ,wkor_id
                ,pm_point_id
                ,pm_point_rslt_status
                ,pm_point_rep_status
				,is_saved
            )
            select @v_comp_no as comp_no
                ,NEXT VALUE FOR sqawopoint_id as wopoint_id
                ,@v_wo_id     as wkor_id
                ,a.pm_point_id as pm_point_id
                ,(select top 1 cdsysd_no 
				  from tacdsysd 
				  where list_type='PM_POINT_RSLT_STATUS'
				      and is_use = 'Y'
				  order by ord_no
						)         as pm_point_rslt_status
                ,'GD'               as pm_point_rep_status
				,'N'                  AS is_saved
            from tapmpoint a
            where 1=1
                and a.comp_no = @v_comp_no
                and a.pm_id =  @pm_id
                and a.is_active = 'Y'
            ;
                    
                
            -- update wo_no in TAPMSCHED
            update TAPMSCHED set
                    wkor_id = @v_wo_id
                ,pmsched_status =  'S' 
            where comp_no = @v_comp_no
                and pmsched_id = @pmsched_id
            ;
                    
            exec SP_WOMAKE_4WP_BYONE @v_comp_no, @v_wo_id;
		    

			--  N인경우 작업계획(TAWOPLAN), 작업실적(TAWORKORDER) 2가지 모두 발행 
			--  사용자는 작업실적을 사용
			if @v_is_work_plan = 'N'

				select @v_wo_id = max(wkor_id) from tawoplan;

				insert into TAWORKORDER(
					comp_no
					,wkor_id
					,wo_no
					,description
					,wo_status 
					,wo_type
					,pm_type
					,wo_gen_type
					,start_date
					,end_date
					,wkor_date
					,dept_id
					,emp_id
					,perform
					,pm_id
					,pmsched_id
					,upd_date
					,upd_by
					,wkctr_id
					,plant
					,eqasmb_id
				)
				select 
						@v_comp_no  as comp_no
					,@v_wo_id      as wkor_id
					,@v_wo_id      as wo_no
					,a.description  as description
					,'P'		    as wo_status -- stand by 
					,a.wo_type      as wo_type  -- preventmaint
					,a.pm_type      as pm_type
					,'PMPLAN'        as wo_gen_type
					,@sched_date  as start_date
					,@sched_date  as end_date
					,@sched_date  as wkor_date
					,a.dept_id         as dept_id
					,a.emp_id        as emp_id
					,a.remark         as perform
					,@pm_id         as pm_id
					,@pmsched_id  as pmsched_id
					,CONVERT(varchar(8), getdate(), 112) as upd_date
					,a.emp_id          as upd_by
					,a.wkctr_id
					,a.plant
					,@eqasmb_id
				from tapmlst a
				where 1=1
					and a.comp_no = @v_comp_no
					and a.pm_id = @pm_id
				;
                    
				-- update wo_no in TAPMSCHED
				update TAPMSCHED set
						wkor_id = @v_wo_id
					,pmsched_status =  'S' 
				where comp_no = @v_comp_no
					and pmsched_id = @pmsched_id
				;
                    
				exec SP_WOMAKE_4WP_BYONE @v_comp_no, @v_wo_id;
		    
			FETCH NEXT FROM c_pm_sched_id_SP_PM_MAKE_TO_WO INTO @pm_id,@wcode_id,@wo_type,@pm_type,@pmsched_id,@sched_date,@pmequip_id,@equip_id,@eqasmb_id
		END

    CLOSE c_pm_sched_id_SP_PM_MAKE_TO_WO
    DEALLOCATE c_pm_sched_id_SP_PM_MAKE_TO_WO	         
   
      