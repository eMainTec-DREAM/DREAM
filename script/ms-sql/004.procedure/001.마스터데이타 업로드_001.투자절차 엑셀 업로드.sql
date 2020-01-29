/****** Object:  StoredProcedure [dbo].[SP_MUP_INVESTLIST]    Script Date: 2018-07-16 오전 8:45:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[SP_MUP_INVESTLIST] (
       @v_comp_no     varchar(6)
	  ,@v_user_no     varchar(20)
	  ,@v_key_id      bigint
) 
as
    SET NOCOUNT ON;
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
       
    declare     
          @v_count         numeric(4)
		 ,@v_upload_emp_id     bigint
		 ,@invtprctp_id        bigint
		 ,@v_invtlist_id       bigint
		 ,@v_invtlist_no       varchar(30)
		 ,@invt_categ_code     varchar(20)
		 ,@invt_type_code      varchar(20)
		 ,@v_dept_id           bigint
		 ,@v_emp_id            bigint


	declare     
	     @sunbeon        varchar(30)
        ,@cu_type        varchar(20)
		,@plant          varchar(20)
		,@description    varchar(256)
		,@invtprctp      varchar(100)
		,@invt_categ     varchar(100)
		,@invt_type      varchar(100)
		,@dept_no        varchar(100)
		,@emp_no         varchar(100)
		,@plan_amt       varchar(100)
		,@plan_sdate     varchar(100)
		,@plan_edate     varchar(100)
		,@remark         varchar(256)
		,@SKEY_ID        numeric(18)
		,@is_success     varchar(100)

		
    DECLARE c_data_invest_list CURSOR FOR
        select *
        from (
                select 
                    sunbeon
                    ,ltrim(rtrim(cu_type))        as cu_type
                    ,ltrim(rtrim(plant))          as plant
					,ltrim(rtrim(description))    as description
					,ltrim(rtrim(invtprctp))      as invtprctp
					,ltrim(rtrim(invt_categ))     as invt_categ
					,ltrim(rtrim(invt_type))      as invt_type
					,ltrim(rtrim(dept))           as dept_no
					,ltrim(rtrim(emp))            as emp_no
					,ltrim(rtrim(plan_amt))       as plan_amt
					,ltrim(rtrim(plan_sdate))     as plan_sdate
					,ltrim(rtrim(plan_edate))     as plan_edate
					,ltrim(rtrim(remark))         as remark
                    ,SKEY_ID 
					,IS_SUCCESS
                from TYINVEST_EXCEL a
                where 1=1
                    and comp_no = @v_comp_no
                    and user_no = @v_user_no
                    and 0 = @v_key_id
                union all
                select 
                    sunbeon
                    ,ltrim(rtrim(cu_type))        as cu_type
                    ,ltrim(rtrim(plant))          as plant
					,ltrim(rtrim(description))    as description
					,ltrim(rtrim(invtprctp))      as invtprctp
					,ltrim(rtrim(invt_categ))     as invt_categ
					,ltrim(rtrim(invt_type))      as invt_type
					,ltrim(rtrim(dept))           as dept_no
					,ltrim(rtrim(emp))            as emp_no
					,ltrim(rtrim(plan_amt))       as plan_amt
					,ltrim(rtrim(plan_sdate))     as plan_sdate
					,ltrim(rtrim(plan_edate))     as plan_edate
					,ltrim(rtrim(remark))         as remark
                    ,SKEY_ID 
					,IS_SUCCESS
                from TYINVEST_EXCEL a
                where 1=1
                    and comp_no = @v_comp_no
                    and user_no = @v_user_no
                    and SKEY_ID = @v_key_id
            ) a
            where 1=1
            order by convert(numeric, a.SUNBEON) asc 
			
			
    select @v_count  = COUNT(*)
    from taemp 
    where comp_no = @v_comp_no
        and emp_no = @v_user_no
    
    if @v_count > 0
           select top 1 @v_upload_emp_id = emp_id
           from taemp 
           where comp_no = @v_comp_no
               and emp_no =@v_user_no
   



    -- [시작]먼저 오류가 있는지 체크하고 오류가 있다면 그 데이타는 처리하지 않음.
    OPEN c_data_invest_list
    FETCH NEXT FROM c_data_invest_list INTO @sunbeon,@cu_type,@plant,@description,@invtprctp,@invt_categ,@invt_type,@dept_no,@emp_no,@plan_amt,@plan_sdate,@plan_edate,@remark,@SKEY_ID,@is_success
    WHILE (@@FETCH_STATUS=0)
	BEGIN
	    --------------------------------------------------------------------------------------------------------------------------------------------------------------
		-- 기존 엑셀 데이타에 초기 데이타 setting
		UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'Y' ,message=''  WHERE COMP_NO = @v_comp_no AND USER_NO = @v_user_no   AND SKEY_ID = @skey_id

		-- Plant 코드 확인(업으면 에러)
		SELECT @v_count  = COUNT(*)
		FROM TAPLANT
		WHERE comp_no = @v_comp_no
			AND plant = @plant

        if @v_count != 1
		    UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message = 'Plant 코드를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
		   
		-- Description명 확인(공백이면 에러) 
		if (ltrim(rtrim(@description)) is null or ltrim(rtrim(@description)) = '') 
		    UPDATE TYPMINS_EXCEL  SET  IS_SUCCESS = 'N' ,message = message + '투자명을 확인하세요' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	    
		-- 투자절차 확인(투자절차가 없으면 에러)
		select @v_count  = COUNT(*)
        from TAINVTPRCTP 
        where comp_no = @v_comp_no 
            and description = @invtprctp
            and is_use = 'Y'
       if @v_count != 1
		    UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '투자절차를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	   
	   -- 투자구분 확인(값이 있는데, 코드로 등록된게 없으면 에러)
	   if (ltrim(rtrim(@invt_categ)) is not null and ltrim(rtrim(@invt_categ)) != '') 
	   begin
		   select @v_count  = COUNT(*)
		   from TACDSYSD 
		   where cdsysm_id = (SELECT cdsysm_id                        
							  FROM TACDSYSM                        
							  WHERE list_type = 'INVT_CATEG'
								  and is_use = 'Y'
							  )
				and description = @invt_categ
				and is_use = 'Y'
		   if @v_count != 1
				UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '투자구분을 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	   end

	   -- 투자분류 확인(값이 있는데, 코드로 등록된게 없으면 에러)
	   if (ltrim(rtrim(@invt_categ)) is not null and ltrim(rtrim(@invt_categ)) != '') 
	   begin
		   select @v_count  = COUNT(*)
		   from TACDSYSD 
		   where cdsysm_id = (SELECT cdsysm_id                        
							  FROM TACDSYSM                        
							  WHERE list_type = 'INVT_TYPE'
								  and is_use = 'Y'
							  )
				and description = @invt_type
				and is_use = 'Y'
		   if @v_count != 1
				UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '투자분류을 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	   end

	   -- 부서코드 확인(값이 있는데, 코드로 등록된게 없으면 에러)
	   if (ltrim(rtrim(@dept_no)) is not null and ltrim(rtrim(@dept_no)) != '') 
	   begin
			select @v_count = count(*) 
			from tadept
			where comp_no = @v_comp_no
				and dept_no = @dept_no
			if @v_count != 1
				UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '부서코드를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	   end

	   -- 사원번호 확인(값이 있는데, 코드로 등록된게 없으면 에러)
	   if (ltrim(rtrim(@emp_no)) is not null and ltrim(rtrim(@emp_no)) != '') 
	   begin
			select @v_count = count(*) 
			from taemp
			where comp_no = @v_comp_no
				and emp_no = @emp_no
			if @v_count != 1
				UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '사원번호를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	   end

	   -- 계획금액 확인(값이 있는데,문자가 들어가면 에러)
	   if (ltrim(rtrim(@plan_amt)) is not null and ltrim(rtrim(@plan_amt)) != '') 
	   begin
			begin try
			    set @plan_amt = replace(@plan_amt,',','')
				set @plan_amt = convert(numeric, @plan_amt)
			end try
			begin catch
				UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '예상금액을 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
			end catch
	   end

	   -- 계획시작일자 확인(값이 있는데,날짜가 아니면 에러)
	   if (ltrim(rtrim(@plan_sdate)) is not null and ltrim(rtrim(@plan_sdate)) != '') 
	   begin
			begin try
			    set @plan_sdate = convert(varchar(8), CAST(replace(replace(replace(replace(ltrim(rtrim(@plan_sdate)),'.',''),'-',''),',',''),' ','') as datetime) ,112)
			end try
			begin catch
				UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '시작일자을 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
			end catch
	   end

	   -- 계획종료일자 확인(값이 있는데,날짜가 아니면 에러)
	   if (ltrim(rtrim(@plan_sdate)) is not null and ltrim(rtrim(@plan_sdate)) != '') 
	   begin
			begin try
			    set @plan_edate = convert(varchar(8), CAST(replace(replace(replace(replace(ltrim(rtrim(@plan_edate)),'.',''),'-',''),',',''),' ','') as datetime) ,112)
			end try
			begin catch
				UPDATE TYINVEST_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '시작일자을 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
			end catch
	   end


		--------------------------------------------------------------------------------------------------------------------------------------------------------------
		FETCH NEXT FROM c_data_invest_list INTO @sunbeon,@cu_type,@plant,@description,@invtprctp,@invt_categ,@invt_type,@dept_no,@emp_no,@plan_amt,@plan_sdate,@plan_edate,@remark,@SKEY_ID,@is_success
	END 
	CLOSE c_data_invest_list
	-- [끝]먼저 오류가 있는지 체크하고 오류가 있다면 그 데이타는 처리하지 않음.


	-- [시작]실제 데이타 등록
	OPEN c_data_invest_list
    FETCH NEXT FROM c_data_invest_list INTO @sunbeon,@cu_type,@plant,@description,@invtprctp,@invt_categ,@invt_type,@dept_no,@emp_no,@plan_amt,@plan_sdate,@plan_edate,@remark,@SKEY_ID,@is_success
    WHILE (@@FETCH_STATUS=0)
	BEGIN
	    --------------------------------------------------------------------------------------------------------------------------------------------------------------
		-- 100. 기존 엑셀 데이타에 초기 데이타 setting
		if @cu_type = 'C' and @is_success = 'Y'
			begin
				select @invtprctp_id = invtprctp_id
				from TAINVTPRCTP 
				where comp_no = @v_comp_no 
					and description = @invtprctp
					and is_use = 'Y'
				;
				
				set @v_invtlist_id = next value for sqainvtlist_id
				set @v_invtlist_no = convert(varchar, @v_invtlist_id ) 

				select @invt_categ_code  = cdsysd_no
			    from TACDSYSD 
			    where cdsysm_id = (SELECT cdsysm_id                        
								  FROM TACDSYSM                        
								  WHERE list_type = 'INVT_CATEG'
									  and is_use = 'Y'
								  )
					and description = @invt_categ
					and is_use = 'Y'

			    select @invt_type_code  = cdsysd_no
			    from TACDSYSD 
			    where cdsysm_id = (SELECT cdsysm_id                        
								  FROM TACDSYSM                        
								  WHERE list_type = 'INVT_TYPE'
									  and is_use = 'Y'
								  )
					and description = @invt_type
					and is_use = 'Y'

	            if (ltrim(rtrim(@dept_no)) is not null and ltrim(rtrim(@dept_no)) != '') 
			    begin
					select @v_dept_id = dept_id
					from tadept
					where comp_no = @v_comp_no
						and dept_no = @dept_no
			    end

			   if (ltrim(rtrim(@emp_no)) is not null and ltrim(rtrim(@emp_no)) != '') 
			   begin
					select @v_emp_id = emp_id
					from taemp
					where comp_no = @v_comp_no
						and emp_no = @emp_no
			   end

		       set @plan_amt = replace(@plan_amt,',','')
			   set @plan_amt = convert(numeric, @plan_amt)
			   set @plan_sdate = convert(varchar(8), CAST(replace(replace(replace(replace(ltrim(rtrim(@plan_sdate)),'.',''),'-',''),',',''),' ','') as datetime) ,112)
			   set @plan_edate = convert(varchar(8), CAST(replace(replace(replace(replace(ltrim(rtrim(@plan_edate)),'.',''),'-',''),',',''),' ','') as datetime) ,112)

                insert into TAINVTLIST(
                    comp_no, invtlist_id, invtlist_no, description, invtlist_status, invtprctp_id, invt_categ, invt_type
                    ,dept_id, emp_id, plan_amt, plan_sdate, plan_edate, remark, plant
                ) values(
                    @v_comp_no, @v_invtlist_id, @v_invtlist_no, @description, 'W',@invtprctp_id, @invt_categ_code, @invt_type_code
                    ,@v_dept_id, @v_emp_id, @plan_amt, @plan_sdate, @plan_edate, @remark, @plant
                );
                   
                   
                INSERT INTO TAINVTPHASE (
                    comp_no ,invtphase_id ,invtlist_id ,invtprcph_id ,ord_no ,invt_proc_ltype
                    ,invt_proc_stype ,ref_depart ,ref_doc ,invtphase_status , invt_doc_no 
                ) SELECT @v_comp_no ,next value for SQAINVTPHASE_ID ,@v_invtlist_id ,invtprcph_id ,y.ord_no ,y.invt_proc_ltype
                            ,y.invt_proc_stype , y.ref_depart ,y.ref_doc ,'W' ,y.doc_prefix                                        
                    FROM   TAINVTPRCPH y    
                    WHERE  y.is_use = 'Y'    
                        and comp_no = @v_comp_no
                        AND  invtprctp_id = @invtprctp_id
                ;
                   
                UPDATE TYINVEST_EXCEL  SET  cu_type = 'C', IS_SUCCESS = 'Y' ,message = 'Success' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id

				
			end
		--------------------------------------------------------------------------------------------------------------------------------------------------------------
		FETCH NEXT FROM c_data_invest_list INTO @sunbeon,@cu_type,@plant,@description,@invtprctp,@invt_categ,@invt_type,@dept_no,@emp_no,@plan_amt,@plan_sdate,@plan_edate,@remark,@SKEY_ID,@is_success
	END 
	CLOSE c_data_invest_list
    DEALLOCATE c_data_invest_list
	-- [끝]실제 데이타 등록