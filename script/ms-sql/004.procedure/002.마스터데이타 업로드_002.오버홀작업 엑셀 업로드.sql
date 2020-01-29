/****** Object:  StoredProcedure [dbo].[SP_MUP_PMINS]    Script Date: 2018-07-16 오전 8:45:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[SP_MUP_OHWORK] (
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
		 ,@v_wkor_id           bigint
		 ,@v_wkor_no           varchar(30)
		 ,@sv_type_code        varchar(20)
		 ,@v_dept_id           bigint
		 ,@v_cust_id           bigint
		 ,@v_cust_name         varchar(256)
		 ,@v_equip_id            bigint
		 ,@v_eqctg_id           bigint
		 


	declare     
	     @sunbeon        varchar(30)
        ,@cu_type        varchar(20)
		,@plant          varchar(20)
		,@item_no        varchar(50)
		,@item_desc      varchar(512)
		,@description    varchar(256)
		,@work_date      varchar(100)
		,@dept_no        varchar(100)
		,@sv_type        varchar(100)
		,@cust_no        varchar(50)
		,@cust_name      varchar(256)
		,@price          varchar(100)
		,@remark         varchar(256)
		,@SKEY_ID        numeric(18)
		,@is_success     varchar(100)

		
		
    DECLARE c_data CURSOR FOR
        select *
        from (
                select 
                    sunbeon
                    ,ltrim(rtrim(cu_type))        as cu_type
                    ,ltrim(rtrim(plant))          as plant
					,ltrim(rtrim(item_no))        as item_no
					,ltrim(rtrim(item_desc))      as item_desc
					,ltrim(rtrim(description))    as description
					,ltrim(rtrim(work_date))      as work_date
					,ltrim(rtrim(dept))           as dept_no
					,ltrim(rtrim(sv_type))        as sv_type
					,ltrim(rtrim(cust_no))        as cust_no
					,ltrim(rtrim(cust_name))      as cust_name
					,ltrim(rtrim(price))          as price
					,ltrim(rtrim(remark))         as remark
                    ,SKEY_ID 
					,IS_SUCCESS
                from TYOHWORK_EXCEL a
                where 1=1
                    and comp_no = @v_comp_no
                    and user_no = @v_user_no
                    and 0 = @v_key_id
                union all
                select 
                    sunbeon
                    ,ltrim(rtrim(cu_type))        as cu_type
                    ,ltrim(rtrim(plant))          as plant
					,ltrim(rtrim(item_no))        as item_no
					,ltrim(rtrim(item_desc))      as item_desc
					,ltrim(rtrim(description))    as description
					,ltrim(rtrim(work_date))      as work_date
					,ltrim(rtrim(dept))           as dept_no
					,ltrim(rtrim(sv_type))        as sv_type
					,ltrim(rtrim(cust_no))        as cust_no
					,ltrim(rtrim(cust_name))      as cust_name
					,ltrim(rtrim(price))          as price
					,ltrim(rtrim(remark))         as remark
                    ,SKEY_ID 
					,IS_SUCCESS
                from TYOHWORK_EXCEL a
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
    OPEN c_data
    FETCH NEXT FROM c_data INTO @sunbeon,@cu_type,@plant,@item_no,@item_desc,@description,@work_date,@dept_no,@sv_type,@cust_no,@cust_name,@price,@remark,@SKEY_ID,@is_success
    WHILE (@@FETCH_STATUS=0)
	BEGIN
	    --------------------------------------------------------------------------------------------------------------------------------------------------------------
		-- 기존 엑셀 데이타에 초기 데이타 setting
		UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'Y' ,message=''  WHERE COMP_NO = @v_comp_no AND USER_NO = @v_user_no   AND SKEY_ID = @skey_id

		-- Plant 코드 확인(업으면 에러)
		SELECT @v_count  = COUNT(*)
		FROM TAPLANT
		WHERE comp_no = @v_comp_no
			AND plant = @plant

        if @v_count != 1
		    UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message = 'Plant 코드를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
		
		-- 설버번호  확인(업으면 에러)
		SELECT @v_count  = COUNT(*)
		FROM TAEQUIPMENT
		WHERE comp_no = @v_comp_no
			AND item_no = @item_no
			AND IS_LAST_VERSION = 'Y'
	    if @v_count != 1
		    UPDATE TYPMINS_EXCEL  SET  IS_SUCCESS = 'N' ,message = message + '설버번호를 확인하세요' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id

		-- Description명 확인(공백이면 에러) 
		if (ltrim(rtrim(@description)) is null or ltrim(rtrim(@description)) = '') 
		    UPDATE TYPMINS_EXCEL  SET  IS_SUCCESS = 'N' ,message = message + '투자명을 확인하세요' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	    
		-- 계획시작일자 확인(값이 없어도, 날자가 아니어도 에러)
	    if (ltrim(rtrim(@description)) is null or ltrim(rtrim(@description)) = '')
	        UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '작업일자를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	    else 
	       begin
				begin try
				    set @work_date = convert(varchar(8), CAST(replace(replace(replace(replace(ltrim(rtrim(@work_date)),'.',''),'-',''),',',''),' ','') as datetime) ,112)
				end try
				begin catch
					UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '작업일자를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
				end catch
		   end
		
		-- 부서코드 확인(값이 있는데, 코드로 등록된게 없으면 에러)
		if (ltrim(rtrim(@dept_no)) is null or ltrim(rtrim(@dept_no)) = '')
	        UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '부서코드를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	    else
	       begin
				select @v_count = count(*) 
				from tadept
				where comp_no = @v_comp_no
					and dept_no = @dept_no
				if @v_count != 1
					UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '부서코드를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
		   end
		   
	   -- 자가/외주작업 구분(값이 있는데, 코드로 등록된게 없으면 에러)
	   if (ltrim(rtrim(@sv_type)) is not null and ltrim(rtrim(@sv_type)) != '') 
	   begin
		   select @v_count  = COUNT(*)
		   from TACDSYSD 
		   where cdsysm_id = (SELECT cdsysm_id                        
							  FROM TACDSYSM                        
							  WHERE list_type = 'SELF_VENDOR_TYPE'
								  and is_use = 'Y'
							  )
				and description = @sv_type
				and is_use = 'Y'
		   if @v_count != 1
				UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '작업분류을 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	   end
	   
	   -- 거래처코드(값이 있는데, 코드로 등록된게 없으면 에러)
	   if (ltrim(rtrim(@cust_no)) is not null and ltrim(rtrim(@cust_no)) != '') 
	   begin
			select @v_count = count(*) 
			from tavendor
			where comp_no = @v_comp_no
				and vendor_no = @cust_no
				and is_use = 'Y'
			if @v_count != 1
				UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '거래처를 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
	   end
	   
	   -- 금액 확인(값이 있는데,문자가 들어가면 에러)
	   if (ltrim(rtrim(@price)) is not null and ltrim(rtrim(@price)) != '') 
	   begin
			begin try
			    set @price = replace(@price,',','')
				set @price = convert(numeric, @price)
			end try
			begin catch
				UPDATE TYOHWORK_EXCEL  SET  IS_SUCCESS = 'N' ,message =  message + '금액을 확인하세요.' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id
			end catch
	   end

		--------------------------------------------------------------------------------------------------------------------------------------------------------------
		FETCH NEXT FROM c_data INTO @sunbeon,@cu_type,@plant,@item_no,@item_desc,@description,@work_date,@dept_no,@sv_type,@cust_no,@cust_name,@price,@remark,@SKEY_ID,@is_success
	END 
	CLOSE c_data
	-- [끝]먼저 오류가 있는지 체크하고 오류가 있다면 그 데이타는 처리하지 않음.


	-- [시작]실제 데이타 등록
	OPEN c_data
    FETCH NEXT FROM c_data INTO @sunbeon,@cu_type,@plant,@item_no,@item_desc,@description,@work_date,@dept_no,@sv_type,@cust_no,@cust_name,@price,@remark,@SKEY_ID,@is_success
    WHILE (@@FETCH_STATUS=0)
	BEGIN
	    --------------------------------------------------------------------------------------------------------------------------------------------------------------
		-- 100. 기존 엑셀 데이타에 초기 데이타 setting
		if @cu_type = 'C' and @is_success = 'Y'
			begin
				
				set @v_wkor_id = next value for sqawkor_id
				set @v_wkor_no = convert(varchar, @v_wkor_id ) 

				select @sv_type_code  = cdsysd_no
			    from TACDSYSD 
			    where cdsysm_id = (SELECT cdsysm_id                        
								  FROM TACDSYSM                        
								  WHERE list_type = 'SELF_VENDOR_TYPE'
									  and is_use = 'Y'
								  )
					and description = @sv_type
					and is_use = 'Y'

	            if (ltrim(rtrim(@dept_no)) is not null and ltrim(rtrim(@dept_no)) != '') 
			    begin
					select @v_dept_id = dept_id
					from tadept
					where comp_no = @v_comp_no
						and dept_no = @dept_no
			    end
					
		        set @price = replace(@price,',','')
			    set @price = convert(numeric, @price)
			    set @work_date = convert(varchar(8), CAST(replace(replace(replace(replace(ltrim(rtrim(@work_date)),'.',''),'-',''),',',''),' ','') as datetime) ,112)
			    
			    -- 거래처코드(값이 있는데, 코드로 등록된게 없으면 에러)
			   set @v_cust_id = null
			   set @v_cust_name = null
			   if (ltrim(rtrim(@cust_no)) is not null and ltrim(rtrim(@cust_no)) != '') 
			   begin
					select @v_cust_id = vendor_id, @v_cust_name = description
					from tavendor
					where comp_no = @v_comp_no
						and vendor_no = @cust_no
						and is_use = 'Y'
			   end

			   SELECT @v_equip_id  = equip_id, @v_eqctg_id  = eqctg_id
				FROM TAEQUIPMENT
				WHERE comp_no = @v_comp_no
					AND item_no = @item_no
					AND IS_LAST_VERSION = 'Y'

					
                insert into taworkorder(
                    comp_no, wkor_id, wo_no, plant, description, wo_status, wo_type, pm_type, wo_gen_type, wkor_date
                    ,tot_amt, self_vendor_type, vendor_id, vendor_desc, dept_id, perform
                    ,cre_time, upd_date, upd_by, is_deleted
                ) values(
                    @v_comp_no, @v_wkor_id, @v_wkor_no,@plant, @description, 'P','CM', 'RPL', 'WORSLT', @work_date
                    ,@price, @sv_type_code, @v_cust_id, @v_cust_name, @v_dept_id, @remark
                    ,replace(replace(replace(convert(varchar, getdate(), 120),'-',''),':',''),' ','')
                    ,convert(varchar, getdate(), 112)
                    ,@v_upload_emp_id
                    ,'N'
                );

				insert into TAWOEQUIP(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description)
				values(@v_comp_no, next value for sqawoequip_id, @v_wkor_id, @v_equip_id, @v_eqctg_id, @item_desc ) 

                UPDATE TYOHWORK_EXCEL  SET  cu_type = 'C', IS_SUCCESS = 'Y' ,message = 'Success' WHERE COMP_NO = @v_comp_no   AND USER_NO = @v_user_no  AND SKEY_ID = @skey_id

				
			end
		--------------------------------------------------------------------------------------------------------------------------------------------------------------
		FETCH NEXT FROM c_data INTO @sunbeon,@cu_type,@plant,@item_no,@item_desc,@description,@work_date,@dept_no,@sv_type,@cust_no,@cust_name,@price,@remark,@SKEY_ID,@is_success
	END 
	CLOSE c_data
    DEALLOCATE c_data
	-- [끝]실제 데이타 등록