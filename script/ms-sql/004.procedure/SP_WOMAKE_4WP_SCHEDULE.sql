CREATE PROCEDURE [dbo].[SP_WOMAKE_4WP_SCHEDULE] (
        @v_comp_no       varchar(6)
       ,@v_before_peroid int
       ,@v_to_period     int
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    DECLARE @v_start_date      varchar(8)
           ,@v_to_date         varchar(8)
           
    set @v_start_date =  convert(varchar(8), DATEADD(DAY,-1*@v_before_peroid,getdate()) , 112)
    set @v_to_date    =  convert(varchar(8), DATEADD(DAY,@v_to_period,getdate()) , 112)
    
    
    -- delete all data
    delete from taworkorder4wp where comp_no = @v_comp_no;
    delete from tawoequip4wp where comp_no = @v_comp_no; 
    delete from tawoparts4wp where comp_no = @v_comp_no;
    delete from tawocraft4wp where comp_no = @v_comp_no;
    delete from tawopoint4wp where comp_no = @v_comp_no;
    
    insert into taworkorder4wp(comp_no,wkor_id,wo_no,equip_id,description,wo_status
            ,wo_type,pm_type,start_date,start_time,end_date,end_time
            ,work_time,dept_id,emp_id,perform,pm_id,pmsched_id,wo_gen_type
            ,wopoint_id,upd_date,upd_by,self_vendor_type,vendor_id,p_wkor_id
            ,wkor_date,shift_type,tot_amt,pmaction,eqloc_id,wkctr_id
            ,close_id,close_date,supuser_id
    )select comp_no,wkor_id,wo_no,equip_id,description,wo_status
            ,wo_type,pm_type,start_date,start_time,end_date,end_time
            ,work_time,dept_id,emp_id,perform,pm_id,pmsched_id,wo_gen_type
            ,wopoint_id,upd_date,upd_by,self_vendor_type,vendor_id,p_wkor_id
            ,wkor_date,shift_type,tot_amt,pmaction,eqloc_id,wkctr_id
            ,close_id,close_date,supuser_id
    from taworkorder a
    where 1=1
            and a.comp_no = @v_comp_no
            and a.wkor_date >= @v_start_date
            and a.wkor_date <= @v_to_date
            and a.wo_status = 'P'
    ;
    
    
    insert into tawoequip4wp(comp_no,woequip_id,wkor_id,equip_id
                                        ,eqctg_id,description,work_time,remark)
    select comp_no,woequip_id,wkor_id,equip_id
                ,eqctg_id,description,work_time,remark
    from tawoequip a
    where comp_no = @v_comp_no
        and exists (select b.comp_no
                        from taworkorder4wp b
                        where b.comp_no = @v_comp_no
                            and a.wkor_id = b.wkor_id
                            and a.comp_no = b.comp_no
                        )
    ;
    
    
    insert into tawoparts4wp(comp_no,wopart_id,wkor_id,wcode_id,part_id
                                        ,part_grade,use_qty,ptrepairlist_id,remark
                                        ,unit_price,tot_price,ptisslist_id)
    select comp_no,wopart_id,wkor_id,wcode_id,part_id
                ,part_grade,use_qty,ptrepairlist_id,remark
                ,unit_price,tot_price,ptisslist_id
    from tawoparts a
    where comp_no = @v_comp_no
        and exists (select b.comp_no
                        from taworkorder4wp b
                        where b.comp_no = @v_comp_no
                            and a.wkor_id = b.wkor_id
                            and a.comp_no = b.comp_no
                        )
    ;
    
    
    insert into tawocraft4wp(comp_no,wocraft_id,wkor_id,emp_id,start_date
                            ,start_time,end_date,end_time,work_time,remark)
    select comp_no,wocraft_id,wkor_id,emp_id,start_date
                    ,start_time,end_date,end_time,work_time,remark 
    from tawocraft a
    where comp_no = @v_comp_no
        and exists (select b.comp_no
                        from taworkorder4wp b
                        where b.comp_no = @v_comp_no
                            and a.wkor_id = b.wkor_id
                            and a.comp_no = b.comp_no
                        )
    ;
    
    
    
    insert into tawopoint4wp(comp_no,wopoint_id,wkor_id,pm_point_id,pm_point_rslt_status
                                        ,result_value,remark,repair_date,pm_rep_method_type,pm_point_rep_status
                                        ,repair_desc,repair_by,pm_wkor_id)
    select comp_no,wopoint_id,wkor_id,pm_point_id,pm_point_rslt_status
                            ,result_value,remark,repair_date,pm_rep_method_type,pm_point_rep_status
                            ,repair_desc,repair_by,pm_wkor_id
    from tawopoint a
    where comp_no = @v_comp_no
        and exists (select b.comp_no
                        from taworkorder4wp b
                        where b.comp_no = @v_comp_no
                            and a.wkor_id = b.wkor_id
                            and a.comp_no = b.comp_no
                        )
    ;
    
