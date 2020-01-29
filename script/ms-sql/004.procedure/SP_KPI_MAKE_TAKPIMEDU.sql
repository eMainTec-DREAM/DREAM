CREATE PROCEDURE [dbo].[SP_KPI_MAKE_TAKPIMEDU] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint


    DECLARE c_tr_plan_actual CURSOR FOR
        select  
            (select top 1 plant FROM TAPLANT where comp_no= @v_comp_no) plant
            ,left(a.wkor_date, 6) AS yyyymm
            ,f.pm_type AS tr_type
            ,COUNT (*) AS pvalue
            ,SUM (CASE WHEN a.wo_status = 'C' THEN 1 ELSE 0 END) AS avalue
        FROM taworkorder a,
            tapmsched b,
            tapmlst f
        WHERE     a.comp_no = b.comp_no
            AND a.pmsched_id = b.pmsched_id
            AND a.comp_no = f.comp_no
            AND a.pm_id = f.pm_id
            AND a.wo_status IN ('P', 'C')
            and f.wo_type = 'TR'
            and a.wkor_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6) + '01'
            and a.wkor_date <= CONVERT(varchar(8), getdate(), 112)
            AND f.pm_type IS NOT NULL
            AND a.wkor_date IS NOT NULL
        GROUP BY a.comp_no,left(a.wkor_date, 6),f.pm_type
        ;
    
    -- c_tr_plan_actual cursor parameter
    DECLARE @c_plant   varchar(6)
            ,@c_yyyymm  varchar(6)
            ,@c_tr_type varchar(20)
            ,@c_pvalue numeric(18)
            ,@c_avalue numeric(18)
            
    OPEN c_tr_plan_actual
    FETCH NEXT FROM c_tr_plan_actual INTO @c_plant,@c_yyyymm,@c_tr_type,@c_pvalue,@c_avalue
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            select @v_count = count(*)
            from takpimedu
            where comp_no = @v_comp_no
               and plant = @c_plant
               and yyyymm = @c_yyyymm
               and tr_type = @c_tr_type
            ;
           
            if @v_count > 0
               update takpimedu set
                         m_plan = @c_pvalue
                         ,m_actual = @c_avalue
                where comp_no = @v_comp_no
                       and plant = @c_plant
                       and yyyymm = @c_yyyymm
                       and tr_type = @c_tr_type
                ;
            else
               insert into takpimedu( comp_no, plant, yyyymm, tr_type, m_plan, m_actual
               ) values (
                   @v_comp_no, @c_plant, @c_yyyymm, @c_tr_type, @c_pvalue, @c_avalue
               );
            

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_tr_plan_actual INTO @c_plant,@c_yyyymm,@c_tr_type,@c_pvalue,@c_avalue
        END
    CLOSE c_tr_plan_actual
    DEALLOCATE c_tr_plan_actual
            
    
   
   
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAKPIMEDU'
    ;
    
