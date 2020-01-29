CREATE PROCEDURE [dbo].[SP_PM_MAKE_WORKORDER] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    DECLARE @v_wo_res_before  int
           ,@v_wo_id          bigint
           ,@v_wo_no          bigint


    DECLARE c_pm CURSOR FOR
        select 
             a.pm_id
            ,isnull(a.wo_res_bef,7) as wo_res_bef
            ,(select aa.wcode_id from tadept aa where a.comp_no = aa.comp_no and a.dept_id = aa.dept_id) as wcode_id
            ,a.pm_type
        from TAPMLST a
        where 1=1
            and a.comp_no = @v_comp_no
            and a.is_active = 'Y'
           -- and a.pm_id = 5212
        ;
    
    --c_pm cursor parameter
    DECLARE @c_pm_id       bigint
            ,@c_wo_res_bef int
            ,@c_wcode_id   bigint
            ,@c_pm_type    varchar(20)
    
    --c_pm_sche_no cursor parameter            
    DECLARE @c_pmsched_id  bigint
            ,@c_sched_date varchar(8)
                        
    OPEN c_pm
    FETCH NEXT FROM c_pm INTO @c_pm_id,@c_wo_res_bef,@c_wcode_id,@c_pm_type 
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            SET @v_wo_res_before = @c_wo_res_bef
            
            DECLARE c_pm_sche_no CURSOR FOR
                select 
                     a.pmsched_id
                    ,a.sched_date
                from TAPMSCHED a
                where 1=1
                    and a.comp_no = @v_comp_no
                    and a.pm_id = @c_pm_id
                    and a.pmsched_status = 'P' -- not release work order 
                    and a.wkor_id is null
                    and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= @v_wo_res_before
                order by sched_date;  
                
            OPEN c_pm_sche_no
            FETCH NEXT FROM c_pm_sche_no INTO @c_pmsched_id, @c_sched_date
            WHILE (@@FETCH_STATUS=0)
                BEGIN
                
                    select @v_wo_id = NEXT VALUE FOR sqawkor_id;
                    
                    -- taworkorder
                    insert into taworkorder(
                         comp_no
                        ,wkor_id
                        ,wo_no
                        ,equip_id
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
                    )
                    select 
                         @v_comp_no  as comp_no
                        ,@v_wo_id      as wkor_id
                        ,@v_wo_id      as wo_no
                        ,a.equip_id   as equip_id
                        ,a.description  as description
                        ,'P'                 as wo_status -- stand by 
                        ,a.wo_type      as wo_type  -- preventmaint
                        ,a.pm_type      as pm_type
                        ,'PMPLAN'        as wo_gen_type
                        ,@c_sched_date  as start_date
                        ,@c_sched_date  as end_date
                        ,@c_sched_date  as wkor_date
                        ,a.dept_id         as dept_id
                        ,a.emp_id        as emp_id
                        ,a.remark         as perform
                        ,@c_pm_id         as pm_id
                        ,@c_pmsched_id  as pmsched_id
                        ,CONVERT(varchar(8), getdate(), 112) as upd_date
                        ,(select top 1 user_id from tauser where comp_no = @v_comp_no and user_no = @v_user_no) as upd_by
                        ,a.wkctr_id
                    from tapmlst a
                    where 1=1
                        and a.comp_no = @v_comp_no
                        and a.pm_id = @c_pm_id
                    ;
                    
                    -- tawoequip 
                    insert into tawoequip(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description)
                    select comp_no, NEXT VALUE FOR sqawoequip_id,  @v_wo_id,  equip_id, eqctg_id, description 
                    from tapmequip
                    where comp_no = @v_comp_no
                        and pm_id = @c_pm_id
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
                        ,@c_wcode_id as wcode_id
                        ,a.part_id     as part_id
                        ,'A'              as part_grade
                        ,a.use_qty   as use_qty
                        ,b.last_price  as unit_price
                        ,a.use_qty * b.last_price as tot_price
                    from tapmpart a
                        ,taparts b
                    where a.comp_no = b.comp_no
                        and a.part_id = b.part_id
                        and a.comp_no = @v_comp_no
                        and a.pm_id =  @c_pm_id
                    ;
                    
                    
                    -- inspection point
                    insert into tawopoint(
                        comp_no
                        ,wopoint_id
                        ,wkor_id
                        ,pm_point_id
                        ,pm_point_rslt_status
                        ,pm_point_rep_status
                    )
                    select @v_comp_no as comp_no
                        ,NEXT VALUE FOR sqawopoint_id as wopoint_id
                        ,@v_wo_id     as wkor_id
                        ,a.pm_point_id as pm_point_id
                        ,(select cdsysd_no 
                          from (select top 1 * 
                                from tacdsysd 
                                where list_type='PM_POINT_RSLT_STATUS' 
                                order by ord_no) a
                         )               as pm_point_rslt_status
                        ,'GD'               as pm_point_rep_status
                    from tapmpoint a
                    where 1=1
                        and a.comp_no = @v_comp_no
                        and a.pm_id =  @c_pm_id
                        and a.is_active = 'Y'
                    ;
                    
                    
                    -- stardard point insert 
                    insert into TAWOSTPOINT(
                         comp_no
                        ,wostpoint_id
                        ,wkor_id
                        ,stwrk_point_id
                        ,st_point_rslt_status
                    )
                    select @v_comp_no as comp_no
                        ,NEXT VALUE FOR sqawostpoint_id as wostpoint_id
                        ,@v_wo_id     as wkor_id
                        ,a.stwrk_point_id as stwrk_point_id
                        ,'GD'               as st_point_rslt_status
                    from TASTWRKPOINT a, TASTWRKLST b
                    where 1=1
                        and a.comp_no = @v_comp_no
                        and a.comp_no = b.comp_no
                        and a.stwrk_id = b.stwrk_id
                        and a.is_active = 'Y'
                        and b.pm_type = @c_pm_type
                        and b.is_active = 'Y'
                        and b.is_force ='Y'
                    ;
                    
                    -- update wo_no in TAPMSCHED
                    update TAPMSCHED set
                         wkor_id = @v_wo_id
                        ,pmsched_status =  'S' 
                    where comp_no = @v_comp_no
                        and pmsched_id = @c_pmsched_id
                    ;
                    
                    exec SP_WOMAKE_4WP_BYONE @v_comp_no, @v_wo_id ;

                
                
                    FETCH NEXT FROM c_pm_sche_no INTO @c_pmsched_id, @c_sched_date
                END
            CLOSE c_pm_sche_no
            DEALLOCATE c_pm_sche_no

            FETCH NEXT FROM c_pm INTO @c_pm_id,@c_wo_res_bef,@c_wcode_id,@c_pm_type         
        END
    CLOSE c_pm
    DEALLOCATE c_pm
    
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAPMWORK'
    ;
  