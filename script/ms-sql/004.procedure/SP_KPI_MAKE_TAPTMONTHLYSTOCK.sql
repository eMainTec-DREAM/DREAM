CREATE PROCEDURE [dbo].[SP_KPI_MAKE_TAPTMONTHLYSTOCK] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint

	-- c_used_part cursor parameter
    DECLARE  @c_comp_no   varchar(6)
             ,@c_wcode_id  bigint
             ,@c_yyyymm    varchar(6)
             ,@c_part_id    bigint
             ,@c_part_grade  varchar(6)
             ,@c_qty  numeric(18,3)
             ,@c_unit_price numeric(18,3)
             ,@c_tot_price numeric(18,3)  
     
	DECLARE c_base CURSOR FOR
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,left(CONVERT(varchar(8), getdate(), 112),6) as yyyymm
                ,a.part_id
                ,a.part_grade
                ,a.result_tot as base_qty
                ,a.result_unit_price as base_unit_price
                ,a.result_tot_price as base_tot_price
            FROM TAPTMONTHLYSTOCK a
            WHERE 1=1
                AND a.comp_no = @v_comp_no
                AND a.yyyymm = left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)  -- 한달전
            ;
			
	DECLARE c_rec_part CURSOR FOR
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,left(a.rec_date,6) AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,sum(CASE WHEN a.ptrec_mode ='C' THEN a.rec_qty 
                        WHEN a.ptrec_mode = 'R' THEN a.rec_qty * -1
                        END) AS rec_qty
                ,sum(CASE WHEN a.ptrec_mode ='C' THEN a.tot_price 
                        WHEN a.ptrec_mode = 'R' THEN a.tot_price * -1
                        END) AS tot_price
            FROM taptrechist a
            WHERE 1=1
                AND a.comp_no = @v_comp_no
                AND a.ptrec_type = 'PRREC'
                AND a.rec_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
                AND a.rec_date <= CONVERT(varchar(8), getdate(), 112)
            GROUP BY a.comp_no,a.wcode_id,left(a.rec_date,6),a.part_id,a.part_grade   
            ;

    DECLARE c_used_part CURSOR FOR
           select 
                a.comp_no
                ,a.wcode_id
                ,left(a.iss_date,6) as yyyymm
                ,a.part_id
                ,a.part_grade
                ,sum(case when a.ptiss_mode ='C' then a.iss_qty 
                        when a.ptiss_mode = 'R' then a.iss_qty * -1
                        end) as iss_qty
                ,sum(case when a.ptiss_mode ='C' then a.tot_price 
                        when a.ptiss_mode = 'R' then a.tot_price * -1
                        end) as tot_price
            from taptisshist a
            where 1=1
                and a.comp_no = @v_comp_no
                and a.ptiss_type = 'WOISS'
                and a.iss_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
                and a.iss_date <= CONVERT(varchar(8), getdate(), 112)
            group by a.comp_no,a.wcode_id,left(a.iss_date,6),a.part_id,a.part_grade   
            ;
            
    DECLARE c_stock CURSOR FOR
           select 
                a.comp_no
                ,a.wcode_id
                ,CONVERT(varchar(6), getdate(), 112) as yyyymm
                ,a.part_id
                ,a.part_grade
                ,a.stock_qty
                ,isnull(a.unit_price,0) as unit_price
                ,a.stock_qty * isnull(a.unit_price,0) as tot_price
            from taptstock a
            where 1=1
                and a.comp_no = @v_comp_no
                and a.stock_qty > 0
            ;
            

		UPDATE a set 
			a.unit_price = (SELECT last_price FROM taparts
                              WHERE comp_no = a.comp_no
                              AND part_id = a.part_id)
		FROM taptstock a
		WHERE a.comp_no = @v_comp_no
		;

       update a set 
			a.rec_qty = 0
           ,a.rec_tot_price = 0
           ,a.issue_qty = 0
           ,a.issue_tot_price = 0
       from taptmonthlystock a
       where 1=1
           and a.yyyymm >= convert(varchar(6), DATEADD(month,-1,GETDATE()), 112)
           and a.yyyymm <= CONVERT(varchar(6), getdate(), 112)
       ;
       
       update a set 
			a.base_qty = 0
           ,a.base_unit_price = 0
           ,a.base_tot_price = 0
           ,a.result_tot = 0
           ,a.result_unit_price = 0
           ,a.result_tot_price = 0
       from taptmonthlystock a
       where 1=1
           and a.yyyymm = CONVERT(varchar(6), getdate(), 112)
       ;
       
    
	OPEN c_base
    FETCH NEXT FROM c_base INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_unit_price,@c_tot_price
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            select @v_count = count(*)
            from taptmonthlystock
            where comp_no = @c_comp_no
                and wcode_id = @c_wcode_id
                and yyyymm = @c_yyyymm
                and part_id = @c_part_id
                and part_grade = @c_part_grade
            ;
           
            if @v_count > 0
               update   a set
                         a.base_qty = @c_qty
                        ,a.base_unit_price = @c_unit_price
                        ,a.base_tot_price = @c_tot_price
                from taptmonthlystock a
                where a.comp_no = @c_comp_no
                   and a.wcode_id = @c_wcode_id
                   and a.yyyymm = @c_yyyymm
                   and a.part_id = @c_part_id
                   and a.part_grade = @c_part_grade
                    ;
           else
               insert into taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, base_qty, base_unit_price, base_tot_price
               ) values (
                   @c_comp_no, @c_wcode_id, @c_yyyymm, @c_part_id, @c_part_grade, @c_qty,  @c_unit_price, @c_tot_price
               );
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_base INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_unit_price,@c_tot_price
        END
    CLOSE c_base
    DEALLOCATE c_base



	OPEN c_rec_part
    FETCH NEXT FROM c_rec_part INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_tot_price
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            select @v_count = count(*)
            from taptmonthlystock
            where comp_no = @c_comp_no
                and wcode_id = @c_wcode_id
                and yyyymm = @c_yyyymm
                and part_id = @c_part_id
                and part_grade = @c_part_grade
            ;
           
            if @v_count > 0
               update   a set
                         a.rec_qty = @c_qty
                        ,a.rec_tot_price = @c_tot_price
                from taptmonthlystock a
                where a.comp_no = @c_comp_no
                   and a.wcode_id = @c_wcode_id
                   and a.yyyymm = @c_yyyymm
                   and a.part_id = @c_part_id
                   and a.part_grade = @c_part_grade
                    ;
           else
               insert into taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, rec_qty, rec_tot_price
               ) values (
                   @c_comp_no, @c_wcode_id, @c_yyyymm, @c_part_id, @c_part_grade, @c_qty,  @c_tot_price
               );

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_rec_part INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_tot_price
        END
    CLOSE c_rec_part
    DEALLOCATE c_rec_part



    OPEN c_used_part
    FETCH NEXT FROM c_used_part INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_tot_price
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            select @v_count = count(*)
            from taptmonthlystock
            where comp_no = @c_comp_no
                and wcode_id = @c_wcode_id
                and yyyymm = @c_yyyymm
                and part_id = @c_part_id
                and part_grade = @c_part_grade
            ;
           
            if @v_count > 0
               update   a set
                         a.issue_qty = @c_qty
                        ,a.issue_tot_price = @c_tot_price
                from taptmonthlystock a
                where a.comp_no = @c_comp_no
                   and a.wcode_id = @c_wcode_id
                   and a.yyyymm = @c_yyyymm
                   and a.part_id = @c_part_id
                   and a.part_grade = @c_part_grade
                    ;
           else
               insert into taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, issue_qty, issue_tot_price
               ) values (
                   @c_comp_no, @c_wcode_id, @c_yyyymm, @c_part_id, @c_part_grade, @c_qty,  @c_tot_price
               );

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_used_part INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_tot_price
        END
    CLOSE c_used_part
    DEALLOCATE c_used_part
    
    
    
    OPEN c_stock
    FETCH NEXT FROM c_stock INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_unit_price,@c_tot_price
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            select @v_count = count(*)
            from taptmonthlystock
            where comp_no = @c_comp_no
                and wcode_id = @c_wcode_id
                and yyyymm = @c_yyyymm
                and part_id = @c_part_id
                and part_grade = @c_part_grade
            ;
           
            if @v_count > 0
               update   a set
                         a.result_tot = @c_qty
                        ,a.result_unit_price = @c_unit_price
                        ,a.result_tot_price = @c_tot_price
                from taptmonthlystock a
                where a.comp_no = @c_comp_no
                   and a.wcode_id = @c_wcode_id
                   and a.yyyymm = @c_yyyymm
                   and a.part_id = @c_part_id
                   and a.part_grade = @c_part_grade
                    ;
           else
               insert into taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, result_tot, result_unit_price, result_tot_price
               ) values (
                   @c_comp_no, @c_wcode_id, @c_yyyymm, @c_part_id, @c_part_grade, @c_qty,  @c_unit_price, @c_tot_price
               );
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_stock INTO @c_comp_no,@c_wcode_id,@c_yyyymm,@c_part_id,@c_part_grade,@c_qty,@c_unit_price,@c_tot_price
        END
    CLOSE c_stock
    DEALLOCATE c_stock
    

   
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAPTMONTHLYSTOCK'
    ;