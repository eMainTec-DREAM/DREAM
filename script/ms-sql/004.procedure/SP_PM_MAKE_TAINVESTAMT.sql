CREATE PROCEDURE [dbo].[SP_PM_MAKE_TAINVESTAMT] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    declare  @v_mtlnpoint_id                    bigint
            ,@v_bd_monthly_tvalue_eq            numeric(18,3)
            ,@v_bdh_monthly_tvalue_eq           numeric(18,3)
            ,@v_mttr_monthly_tvalue_eq          numeric(15)
            ,@v_mtbf_monthly_tvalue_eq          numeric(15)
            ,@v_bd_monthly_tvalue               numeric(18,3)
            ,@v_bdh_monthly_tvalue              numeric(18,3)
            ,@v_mttr_monthly_tvalue             numeric(15)
            ,@v_mtbf_monthly_tvalue             numeric(15)
            ,@v_stock_monthly_tvalue            numeric(15)
            ,@v_pvalue                          numeric(18,3)

    select @v_count = COUNT(*)
    from taconfig
    where name='BD_MONTHLY_TVALUE' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_bd_monthly_tvalue = value$
        from taconfig
        where name='BD_MONTHLY_TVALUE' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_bd_monthly_tvalue = 0.3;
        
        
    select @v_count = COUNT(*)
    from taconfig
    where name='BD_MONTHLY_TVALUE_EQ' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_bd_monthly_tvalue_eq = value$
        from taconfig
        where name='BD_MONTHLY_TVALUE_EQ' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_bd_monthly_tvalue_eq = 0.3;
        
    
    select @v_count = COUNT(*)
    from taconfig
    where name='BDH_MONTHLY_TVALUE' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_bdh_monthly_tvalue = value$
        from taconfig
        where name='BDH_MONTHLY_TVALUE' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_bdh_monthly_tvalue = 1.0;
        
        
    select @v_count = COUNT(*)
    from taconfig
    where name='BDH_MONTHLY_TVALUE_EQ' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_bdh_monthly_tvalue_eq = value$
        from taconfig
        where name='BDH_MONTHLY_TVALUE_EQ' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_bdh_monthly_tvalue_eq = 2.0;
        
        
        
    select @v_count = COUNT(*)
    from taconfig
    where name='MTTR_MONTHLY_TVALUE' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_mttr_monthly_tvalue = value$
        from taconfig
        where name='MTTR_MONTHLY_TVALUE' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_mttr_monthly_tvalue = 20;
        
    
    select @v_count = COUNT(*)
    from taconfig
    where name='MTTR_MONTHLY_TVALUE_EQ' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_mttr_monthly_tvalue_eq = value$
        from taconfig
        where name='MTTR_MONTHLY_TVALUE_EQ' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_mttr_monthly_tvalue_eq = 20;
        
        
        
    select @v_count = COUNT(*)
    from taconfig
    where name='MTBF_MONTHLY_TVALUE' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_mtbf_monthly_tvalue = value$
        from taconfig
        where name='MTBF_MONTHLY_TVALUE' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_mtbf_monthly_tvalue = 20000;
        
    
    select @v_count = COUNT(*)
    from taconfig
    where name='MTBF_MONTHLY_TVALUE_EQ' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_mtbf_monthly_tvalue_eq = value$
        from taconfig
        where name='MTBF_MONTHLY_TVALUE_EQ' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_mtbf_monthly_tvalue_eq = 20000;
        
        
        
    select @v_count = COUNT(*)
    from taconfig
    where name='STOCK_MONTHLY_TVALUE' 
        and COMP_NO = @v_comp_no
    ;
    if @v_count > 0
        select @v_stock_monthly_tvalue = value$
        from taconfig
        where name='STOCK_MONTHLY_TVALUE' 
            and COMP_NO = @v_comp_no
        ;
    else
        set @v_stock_monthly_tvalue = 6000000;
        
        
    DECLARE c_data CURSOR FOR
        select 
           eqloc_id, plant
        from taeqloc 
        where 1=1
            and comp_no = @v_comp_no
            and is_use = 'Y'
            and is_kpi = 'Y'
        order by plant, eqloc_no
        ;
    
    --c_plant cursor parameter
    DECLARE @c_eqloc_id       bigint
            ,@c_plant       varchar(20)
    
    --c_month cursor parameter
    DECLARE @c_tmonth       varchar(20)
    
    --c_point cursor parameter
    DECLARE @c_cdsysd_no       varchar(20)
    
    --c_warehouse cursor parameter
    DECLARE @c_wcode_id       bigint
    
    
    OPEN c_data
    FETCH NEXT FROM c_data INTO @c_eqloc_id, @c_plant
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            DECLARE c_month CURSOR FOR
                select  tmonth
                from tamonth 
                where tmonth >= CONVERT(varchar(6), getdate(), 112)
                    and tmonth <= convert(varchar(6), DATEADD(month,1,GETDATE()), 112)
                order by 1
                ;         
        
        
            OPEN c_month
            FETCH NEXT FROM c_month INTO @c_tmonth
            WHILE (@@FETCH_STATUS=0)
                BEGIN
                    -------------------------------------------------------------------------------------------
                    DECLARE c_point CURSOR FOR
                        select cdsysd_no
                        from tacdsysd
                        where list_type = 'MTLNPOINT'
                           and is_use = 'Y'
                       ;    
                    OPEN c_point
                    FETCH NEXT FROM c_point INTO @c_cdsysd_no
                    WHILE (@@FETCH_STATUS=0)
                        BEGIN
                            -------------------------------------------------------------------------------------------
                            
                            select @v_count = count(*)
                            from TAMTLNPOINT
                            where comp_no = @v_comp_no
                                 and plant =  @c_plant
                                 and yyyymm = @c_tmonth
                                 and eqloc_id =  @c_eqloc_id
                                 and mtlnpoint = @c_cdsysd_no
                             ;
                             
                             if @v_count = 0
                                 begin
                                     select @v_mtlnpoint_id = NEXT VALUE FOR sqamtlnpoint_id
                                     
                                     set @v_pvalue = 0;
                                     if @c_cdsysd_no = 'BD'   set @v_pvalue = @v_bd_monthly_tvalue;
                                     if @c_cdsysd_no = 'BD_HOURS' set @v_pvalue = @v_bdh_monthly_tvalue;
                                     if @c_cdsysd_no = 'BD_HOURS_EQ' set @v_pvalue = @v_bdh_monthly_tvalue_eq;
                                     if @c_cdsysd_no = 'MTTR' set @v_pvalue = @v_mttr_monthly_tvalue;
                                     if @c_cdsysd_no = 'MTBF' set @v_pvalue = @v_mtbf_monthly_tvalue;
                                     if @c_cdsysd_no = 'BD_EQ' set @v_pvalue = @v_bdh_monthly_tvalue_eq;
                                     if @c_cdsysd_no = 'MTTR_EQ' set @v_pvalue = @v_mttr_monthly_tvalue_eq;
                                     if @c_cdsysd_no = 'MTBF_EQ' set @v_pvalue = @v_mtbf_monthly_tvalue_eq;
                                     
                                     
                                    insert into TAMTLNPOINT (comp_no, mtlnpoint_id, plant, yyyymm, eqloc_id, mtlnpoint, pvalue, avalue)
                                    values(
                                               @v_comp_no, @v_mtlnpoint_id, @c_plant, @c_tmonth, @c_eqloc_id, @c_cdsysd_no, @v_pvalue, 0
                                    );
                                     
                                 end
                                 
                            -------------------------------------------------------------------------------------------
                            FETCH NEXT FROM c_point INTO @c_cdsysd_no
                        END
                    CLOSE c_point
                    DEALLOCATE c_point
                    
                    DECLARE c_warehouse CURSOR FOR
                        select wcode_id
                        from tawarehouse 
                        where comp_no = @v_comp_no
                            and is_use = 'Y' 
                            and wh_categ = 'PART'
                       ;
                    OPEN c_warehouse
                    FETCH NEXT FROM c_warehouse INTO @c_wcode_id
                    WHILE (@@FETCH_STATUS=0)
                        BEGIN
                            -------------------------------------------------------------------------------------------
                            
                            select @v_count = count(*)
                            from TAPTMSTOCKPLAN
                            where comp_no = @v_comp_no
                                and wcode_id = @c_wcode_id
                                and yyyymm = @c_tmonth
                            ;
                             
                            if @v_count = 0
                                 insert into TAPTMSTOCKPLAN (comp_no, wcode_id, yyyymm, stock_plan_amt, plant )
                                 values(@v_comp_no, @c_wcode_id, @c_tmonth,@v_stock_monthly_tvalue, @c_plant);
                            
                            -------------------------------------------------------------------------------------------
                            FETCH NEXT FROM c_warehouse INTO @c_wcode_id
                        END
                    CLOSE c_warehouse
                    DEALLOCATE c_warehouse
                    
                    
                    -------------------------------------------------------------------------------------------
                    FETCH NEXT FROM c_month INTO @c_tmonth
                END
            CLOSE c_month
            DEALLOCATE c_month
            
            
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_data INTO @c_eqloc_id, @c_plant
        END
    CLOSE c_data
    DEALLOCATE c_data
        
    -------------------------------------------------------------------------
    
    
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAINVESTAMT'
    ;
    