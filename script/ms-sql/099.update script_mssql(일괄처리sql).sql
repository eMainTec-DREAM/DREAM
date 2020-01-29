
/**  2018-08-08 김정우 */
update tamaillist set mail_scope_type = 'I' ;


/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */
/** 2018-08-17 국도 반영 */
/** 2018-08-21 DREAM 반영 */
/** 2018-08-22 매일유업 반영 */
/** 2018-08-28 국도 반영 */ 
/** 2018-09-04 국도 반영 */
/** 2018-09-06 매일유업 반영 */
/** 2018-09-10 국도 반영 */

/** 2018-09-13 양소영 */
SELECT 'ALTER SEQUENCE SQAPTSTKTAKEITEM_ID INCREMENT BY '+
 	CONVERT(VARCHAR, (SELECT (CASE WHEN ( SELECT current_value FROM sys.sequences WHERE name='SQAPTPRITEM_ID' ) > ( SELECT current_value FROM sys.sequences WHERE name='SQAPTSTKTAKEITEM_ID' )
    							   THEN CONVERT(int, (SELECT current_value FROM sys.sequences WHERE name='SQAPTPRITEM_ID' )) - convert(int, (SELECT current_value FROM sys.sequences WHERE name='SQAPTSTKTAKEITEM_ID' ) )
								   ELSE (1)
								END) )  )
 	+'; SELECT NEXT VALUE FOR SQAPTSTKTAKEITEM_ID; ALTER SEQUENCE SQAPTSTKTAKEITEM_ID INCREMENT BY 1; ' ;
	

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */
 	
/** 2018-09-20 양소영 */
--이상점검처리 기존 데이타 이관 scrip **중복실행하여 중복되지 않도록 주의요청(한번 실행)**
-- (작업요청)이상점검처리 기존 데이타 이관 scrip
INSERT INTO TAWONGPOINTRES(comp_no, wongpointres_id , wongpoint_id, ngpointres_method, woreq_id, res_date, res_time, dept_id, emp_id)
SELECT 
     x.comp_no ,NEXT VALUE FOR sqawongpointres_id AS wongpointres_id ,x.wongpoint_id ,'WOREQ' AS ngpointres_method ,x.woreq_id 
    ,x.repair_date AS res_date ,'' AS res_time  ,(SELECT dept_id FROM TAEMP WHERE comp_no=x.comp_no AND emp_id=x.repair_by) AS dept_id ,x.repair_by AS emp_id
FROM TAWONGPOINT x
WHERE PM_REP_METHOD_TYPE = 'REQ';
-- (작업계획)이상점검처리 기존 데이타 이관 scrip
INSERT INTO TAWONGPOINTRES(comp_no, wongpointres_id , wongpoint_id, ngpointres_method, wkor_id, res_date, res_time, dept_id, emp_id)
SELECT
     x.comp_no ,NEXT VALUE FOR sqawongpointres_id AS wongpointres_id ,x.wongpoint_id ,'WOPLAN' AS ngpointres_method ,x.WOPLAN_WKOR_ID AS wkor_id
    ,x.repair_date AS res_date ,'' AS res_time ,(SELECT dept_id FROM TAEMP WHERE comp_no=x.comp_no AND emp_id=x.repair_by) AS dept_id, x.repair_by AS emp_id
FROM TAWONGPOINT x
WHERE PM_REP_METHOD_TYPE = 'PLAN';
-- (작업결과)이상점검처리 기존 데이타 이관 scrip
INSERT INTO TAWONGPOINTRES(comp_no, wongpointres_id , wongpoint_id, ngpointres_method, wkor_id, res_date, res_time, dept_id, emp_id)
SELECT
     x.comp_no ,NEXT VALUE FOR sqawongpointres_id AS wongpointres_id ,x.wongpoint_id ,'WORSLT' AS ngpointres_method ,x.PM_WKOR_ID AS wkor_id
    ,x.repair_date AS res_date ,'' AS res_time ,(SELECT dept_id FROM TAEMP WHERE comp_no=x.comp_no AND emp_id=x.repair_by) AS dept_id ,x.repair_by AS emp_id
FROM TAWONGPOINT x
WHERE PM_REP_METHOD_TYPE = 'WO';

/** 2018-09-27 이근환 */
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   FUNCTION [dbo].[SFAGETDATE](
	  @pCompNo VARCHAR(6)
	, @pTimezone SMALLINT
	, @pIntervalType NVARCHAR(15)
	, @pInterval SMALLINT
	, @pIsModified CHAR(1)
)
    RETURNS DATETIME
BEGIN
    DECLARE @v_date AS DATETIME;

	BEGIN
		SELECT @v_date = dateadd(hh, @pTimezone, GETUTCDATE());
	END

	IF @pIsModified = 'Y'
		BEGIN
			SELECT @v_date = CASE WHEN replace(CONVERT(CHAR(5), @v_date, 24),':','') < isnull(value$,'0000') THEN dateadd(day, -1, @v_date) ELSE @v_date END
			FROM TACONFIG
			WHERE comp_no = @pCompNo
			AND NAME = 'WORK_START_BASE_TIME';
		END

	IF @pIntervalType = 'YEAR'
		BEGIN
			SELECT @v_date = dateadd(YEAR, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'MONTH'
		BEGIN
			SELECT @v_date = dateadd(MONTH, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'WEEK'
		BEGIN
			SELECT @v_date = dateadd(WEEK, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'DAY'
		BEGIN
			SELECT @v_date = dateadd(DAY, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'HOUR'
		BEGIN
			SELECT @v_date = dateadd(HOUR, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'MINUTE'
		BEGIN
			SELECT @v_date = dateadd(MINUTE, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'SECOND'
		BEGIN
			SELECT @v_date = dateadd(SECOND, @pInterval, @v_date);
		END
	ELSE
		BEGIN
			SELECT @v_date = dateadd(DAY, @pInterval, @v_date);
		END

	RETURN @v_date
END;

/** 2018-10-11 이근환 */
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER   FUNCTION [dbo].[SFAGETDATE](
	  @pOffset SMALLINT
	, @pIntervalType NVARCHAR(15)
	, @pInterval SMALLINT
)
    RETURNS DATETIME
BEGIN
    DECLARE @v_date AS DATETIME;

	BEGIN
		SELECT @v_date = dateadd(hh, @pOffset, GETUTCDATE());
	END

	IF @pIntervalType = 'YEAR'
		BEGIN
			SELECT @v_date = dateadd(YEAR, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'MONTH'
		BEGIN
			SELECT @v_date = dateadd(MONTH, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'WEEK'
		BEGIN
			SELECT @v_date = dateadd(WEEK, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'DAY'
		BEGIN
			SELECT @v_date = dateadd(DAY, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'HOUR'
		BEGIN
			SELECT @v_date = dateadd(HOUR, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'MINUTE'
		BEGIN
			SELECT @v_date = dateadd(MINUTE, @pInterval, @v_date);
		END
	ELSE IF @pIntervalType = 'SECOND'
		BEGIN
			SELECT @v_date = dateadd(SECOND, @pInterval, @v_date);
		END
	ELSE
		BEGIN
			SELECT @v_date = dateadd(DAY, @pInterval, @v_date);
		END

	RETURN @v_date
END;

 /**  2018-09-01 박철완 */ 
/* 구매신청 프로그램이 패치될때 그때 1회 적용해야 함.
update TAPTPRLIST set ptprlist_status = 'W111' where ptprlist_status = 'W';
update TAPTPRLIST set ptprlist_status = 'W' where ptprlist_status = 'C';
update TAPTPRLIST set ptprlist_status = 'C' where ptprlist_status = 'W111';

DELETE TACDSYSD WHERE LIST_TYPE = 'PTPRLIST_STATUS';

 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'W', '작성중', '010', 'Y', '작성중', '', '', 'CODESET', 'PTPRLIST_STATUS.W'); 
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'WRA', '결재요청', '020', 'Y', '결재요청', '', '', 'CODESET', 'PTPRLIST_STATUS.WRA'); 
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'WOA', '결재중', '030', 'Y', '결재중', '', '', 'CODESET', 'PTPRLIST_STATUS.WOA'); 
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'WDA', '결재반려', '040', 'Y', '결재반려', '', '', 'CODESET', 'PTPRLIST_STATUS.WDA'); 
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'C', '구매신청완료', '050', 'Y', '요청완료', '', '', 'CODESET', 'PTPRLIST_STATUS.C'); 
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'POC', '발주완료', '060', 'Y', '발주완료', '', '', 'CODESET', 'PTPRLIST_STATUS.POC'); 
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'GRW', '입고대기', '070', 'Y', '입고대기', '', '', 'CODESET', 'PTPRLIST_STATUS.GRW'); 
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'GRC', '입고완료', '080', 'Y', '입고완료', '', '', 'CODESET', 'PTPRLIST_STATUS.GRC');
 INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(NEXT VALUE FOR SQACDSYSD_ID,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPNLIST_STATUS'),'PTPNLIST_STATUS', 'POW', '발주대기', '050', 'Y', '발주대기', '', '', 'CODESET', 'PTPNLIST_STATUS.POW'); 
  
*/

/** 2018-10-18 국도화학 반영 */
/** 2018-10-25 국도화학 반영 */
/** 2018-10-26 국도화학 반영 */

/** 2018-10-30 이근환 */
UPDATE a set a.ptisslist_id = (SELECT ptisslist_id FROM taptisslist WHERE comp_no = a.comp_no AND wopart_id = a.wopart_id) from tawoparts a;

/** 2018-10-30 김영주 */
CREATE PROCEDURE [dbo].[SP_UPD_TADOCCTG] (
      @v_comp_no     VARCHAR(6)
     ,@v_docctg_id    BIGINT
)
as
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    
	SET NOCOUNT ON
    DECLARE @V_COUNT AS INT;
    DECLARE @FULL_DESC AS VARCHAR(1024);
    
    UPDATE TADOCCTG SET full_desc = ( SELECT   MAX(CASE WHEN LVL =8 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =7 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =6 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =5 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =4 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =3 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =2 THEN description + '-' ELSE '' END)
                                            + MAX(CASE WHEN LVL =1 THEN description + '' ELSE '' END)
                                     FROM dbo.SFADOCCTG_PALL(@v_comp_no,@v_docctg_id,'')
                                   )
                WHERE docctg_id = @v_docctg_id 
                    and comp_no = @v_comp_no     
            ; 

/** 2018-10-30 김영주 */
CREATE PROCEDURE [dbo].[SP_UPD_TADOCCTG_ALL] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count as bigint;
    DECLARE @v_docctg_id AS bigint;
    
    DECLARE c_data CURSOR FOR
        SELECT docctg_id                         
        FROM TADOCCTG                                            
        WHERE comp_no=@v_comp_no
        order by docctg_id            
        ;
        
     
    OPEN c_data
    FETCH NEXT FROM c_data INTO @v_docctg_id
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
             
              exec dbo.sp_upd_TADOCCTG @v_comp_no, @v_docctg_id;
          
              FETCH NEXT FROM c_data INTO  @v_docctg_id
          END
      CLOSE c_data
      DEALLOCATE c_data
     
/** 2018-10-30 매일유업 반영 */
/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */
/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */
/** 2018-11-15 국도화학 반영 */
/** 2018-11-16 국도화학 반영 */
/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */ 
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */
/** 2018-11-28 국도화학 반영 */
/** 2018-11-29 이근환*/
ALTER PROCEDURE [dbo].[SP_KPI_MAKE_TAPTMONTHLYSTOCK] (
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
                ,a.yyyymm
                ,a.part_id
                ,a.part_grade
                ,a.result_tot
                ,a.result_unit_price
                ,a.result_tot_price
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
    
    
/** 2018-11-29 국도화학 반영 */
/** 2018-12-03 국도화학 반영 */
    
    /** 2018-12-05 김정우-> 1개의 PM_ID에 대한 리스트를 뽑아와서  스케쥴을 만들어줘야하는데 조건에 pm_id가 빠져있어서 에러(파라미터)가 나거나 전체 PM을 다시 만드는 행위를 계속하여,
 *  수정하였음. pm_id 조건 추가 */
    ALTER procedure [dbo].[SP_PM_MAKE_TO_ONESCHED](
      @v_comp_no          varchar(6)    
     ,@v_pm_id            bigint
) as
    SET NOCOUNT ON;
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

	declare @v_count                         numeric(4); 
    
    -- exec SP_PM_MAKE_WORKORDER('100','ADMIN');
	DECLARE c_pm_sched_list_SP_PM_MAKE_TO_ONESCHED CURSOR FOR
        select 
             b.pm_type
            ,b.wo_type
            ,a.pmsched_id
            ,b.pm_id
        from TAPMSCHED a, TAPMLST b
        where 1=1
            and a.comp_no = b.comp_no
            and a.pm_id = b.pm_id
            and a.pm_id = @v_pm_id
            and a.comp_no = @v_comp_no
            and b.is_active = 'Y'
            and a.pmsched_status = 'P' -- not release work order 
            and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)
        order by sched_date
        ;
        

	DECLARE c_pmins_sched_list_SP_PM_MAKE_TO_ONESCHED CURSOR FOR
        select 
             b.pm_type
            ,b.wo_type
            ,a.pminsdsched_id
            ,b.pm_id
        from TAPMINSDSCHED a, TAPMLST b
        where 1=1
            and a.comp_no = b.comp_no
            and a.pm_id = b.pm_id
            and a.pm_id = @v_pm_id
            and a.comp_no = @v_comp_no
            and b.is_active = 'Y'
            and a.pmsched_status = 'P' -- not release work order 
            and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)
        order by sched_date
        ;
        
	DECLARE c_pminsday_sched_list_SP_PM_MAKE_TO_ONESCHED CURSOR FOR
        select 
             b.pm_type
            ,b.wo_type
            ,a.pminsdsched_id
            ,b.pm_id
        from TAPMINSDSCHED a, TAPMLST b
        where 1=1
            and a.comp_no = b.comp_no
            and a.pm_id = b.pm_id
            and a.pm_id = @v_pm_id
            and a.comp_no = @v_comp_no
            and b.is_active = 'Y'
            and a.pmsched_status = 'P' -- not release work order 
            and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)
        order by sched_date
        ;
        
	DECLARE c_pmpatrol_sched_list_SP_PM_MAKE_TO_ONESCHED CURSOR FOR
        select 
             b.pm_type
            ,b.wo_type
            ,a.pmptrlsched_id
            ,b.pm_id
        from TAPMPTRLSCHED a, TAPMLST b
        where 1=1
            and a.comp_no = b.comp_no
            and a.pm_id = b.pm_id
            and a.pm_id = @v_pm_id
            and a.comp_no = @v_comp_no
            and b.is_active = 'Y'
            and a.pmsched_status = 'P' -- not release work order 
            and (datediff(day,GETDATE(), cast(a.sched_date as datetime))) <= isnull(b.wo_res_bef,7)
        order by sched_date
        ;


		DECLARE   @pm_type    varchar(20)
                 ,@wo_type    varchar(20)
				 ,@pmsched_id bigint
				 ,@pm_id      bigint
    

        set @v_count = 0;
		OPEN c_pm_sched_list_SP_PM_MAKE_TO_ONESCHED
		FETCH NEXT FROM c_pm_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pmsched_id, @pm_id
		WHILE (@@FETCH_STATUS=0)
			BEGIN
		        exec SP_PM_MAKE_TO_WO @v_comp_no, @pmsched_id;
                if @v_count = 0
                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pmsched_id;
                set @v_count = @v_count + 1; 
				FETCH NEXT FROM c_pm_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pmsched_id, @pm_id
			END
		CLOSE c_pm_sched_list_SP_PM_MAKE_TO_ONESCHED
		DEALLOCATE c_pm_sched_list_SP_PM_MAKE_TO_ONESCHED	      
		
		
		set @v_count = 0;
		DECLARE   @pminssched_id bigint

		OPEN c_pmins_sched_list_SP_PM_MAKE_TO_ONESCHED
		FETCH NEXT FROM c_pmins_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pminssched_id, @pm_id
		WHILE (@@FETCH_STATUS=0)
			BEGIN
		        exec SP_PM_MAKE_TO_PMI @v_comp_no, @pminssched_id;
                if @v_count = 0
                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pminssched_id;
                set @v_count = @v_count + 1; 
				FETCH NEXT FROM c_pmins_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pminssched_id, @pm_id
			END
		CLOSE c_pmins_sched_list_SP_PM_MAKE_TO_ONESCHED
		DEALLOCATE c_pmins_sched_list_SP_PM_MAKE_TO_ONESCHED	    
		
		
		set @v_count = 0;
		DECLARE   @pminsdsched_id bigint

		OPEN c_pminsday_sched_list_SP_PM_MAKE_TO_ONESCHED
		FETCH NEXT FROM c_pminsday_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pminsdsched_id, @pm_id
		WHILE (@@FETCH_STATUS=0)
			BEGIN
		        exec SP_PM_MAKE_TO_PMIDAY @v_comp_no, @pminsdsched_id;
                if @v_count = 0
                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pminsdsched_id;
                set @v_count = @v_count + 1; 
				FETCH NEXT FROM c_pminsday_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pminsdsched_id, @pm_id
			END
		CLOSE c_pminsday_sched_list_SP_PM_MAKE_TO_ONESCHED
		DEALLOCATE c_pminsday_sched_list_SP_PM_MAKE_TO_ONESCHED
		
			      

		set @v_count = 0;
		DECLARE   @pmptrlsched_id bigint

		OPEN c_pmpatrol_sched_list_SP_PM_MAKE_TO_ONESCHED
		FETCH NEXT FROM c_pmpatrol_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pmptrlsched_id, @pm_id
		WHILE (@@FETCH_STATUS=0)
			BEGIN
		        exec SP_PM_MAKE_TO_PATROL @v_comp_no, @pmptrlsched_id;
                if @v_count = 0
                    exec SP_PM_UPDATE_LASTCPLT_DATE @v_comp_no, @pm_id, @pmptrlsched_id;
                set @v_count = @v_count + 1; 
				FETCH NEXT FROM c_pmpatrol_sched_list_SP_PM_MAKE_TO_ONESCHED INTO @pm_type,@wo_type, @pmptrlsched_id, @pm_id
			END
		CLOSE c_pmpatrol_sched_list_SP_PM_MAKE_TO_ONESCHED
		DEALLOCATE c_pmpatrol_sched_list_SP_PM_MAKE_TO_ONESCHED
		

/** 2018-12-05 본사 DREAM 반영 */
/** 2018-12-06 매일유업 반영 */

/** 2018-12-06 이근환 */
ALTER PROCEDURE [dbo].[SP_KPI_MAKE_TAPTMONTHLYSTOCK] (
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
    
/** 2018-12-06 이지수 */
ALTER PROCEDURE [dbo].[SP_PM_MAKE_SCHEDPMI_TIME](
     @v_comp_no     varchar(6)
     ,@v_pm_id       bigint
     ,@v_target_date varchar(8)
) as
    
	SET NOCOUNT ON;
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

	declare @v_count                         numeric(4); 
	declare @v_del_start_date      varchar(8);
    declare @v_last_sch_date       varchar(8);
	declare @v_next_sch_date       varchar(8);
    declare @v_to_day              varchar(8);

    
    DECLARE c_SP_PM_MAKE_SCHEDPMI_TIME CURSOR FOR
            SELECT
                 A.pm_id
                ,A.CYCLE
                ,A.period_type 
                ,b.last_sch_date    -- 다음일정 예정일[주말보정전]
                ,b.equip_id
				,A.wrkcallist_id
				,B.PMEQUIP_ID
            FROM TAPMLST A, TAPMEQUIP B
            WHERE 1=1
                AND A.COMP_NO = B.COMP_NO
                AND A.PM_ID = B.PM_ID
                AND A.comp_no = @v_comp_no
                AND A.pm_id     = @v_pm_id
                AND A.is_active = 'Y'
                AND B.last_sch_date <= @v_target_date
        ;

		set @v_to_day = CONVERT(varchar(8), getdate(), 112)

		DECLARE  @pm_id bigint
            ,@CYCLE numeric(4)
            ,@period_type  varchar(20)
            ,@last_sch_date varchar(8)
            ,@equip_id  bigint
            ,@wrkcallist_id  bigint
			,@PMEQUIP_ID  bigint

		OPEN c_SP_PM_MAKE_SCHEDPMI_TIME
		FETCH NEXT FROM c_SP_PM_MAKE_SCHEDPMI_TIME INTO @pm_id,@CYCLE,@period_type,@last_sch_date,@equip_id,@wrkcallist_id,@PMEQUIP_ID
		WHILE (@@FETCH_STATUS=0)
			BEGIN

			    -- 스케쥴을 시작하기로 한 날짜 이후에 점검결과가 완료되었는지 확인.
                -- 발행된건이 있으면 그 건의 날짜를 시작날짜로 지정하여 스케쥴을 생성함.
                SELECT @v_count = COUNT(*)
                FROM TAPMINSSCHED
                WHERE comp_no = @v_comp_no
                    AND pm_id = @pm_id
                     AND pmequip_id = @pmequip_id
                    AND pmsched_status IN ('C') -- work order가 완료되었는지 확인.
                    AND plan_init_date >= @last_sch_date  -- 스케쥴을 시작하기로 한 날짜 이후에 점검결과가 완료되었는지 확인.
                ;
                
                IF @v_count > 0
				    begin
                        -- 오늘부터 스케쥴시작 날짜 이전까지의  완료되지 않는 스케쥴,설비,점검결과를 삭제해야 함. 
                        SELECT @v_last_sch_date = isnull(MAX(plan_init_date),@last_sch_date)
                        FROM TAPMINSSCHED
                        WHERE comp_no = @v_comp_no
                            AND pm_id = @pm_id
                             AND pmequip_id = @pmequip_id
                            AND pmsched_status IN ('C') -- work order가 완료된 상태
                            AND plan_init_date >= @last_sch_date
                        ;
                        
                        -- 스케쥴 시작할 날짜가 오늘보다 큰지 비교해서 기존에 생성된 작업중에 완료안된 작업을 삭제함.
                        -- 미 작업이라고 하더라도 오늘일자 이후에 데이타만 삭제함. 오늘이전 데이타는 삭제하지 않음.
                        -----------------------------------------------------------------------------------------------------------------------------------------------
                        if @v_to_day > @v_last_sch_date
                            set @v_del_start_date = @v_to_day;
                        else
                            set @v_del_start_date = @v_last_sch_date;
                        -- 작업이 완료되지 않은 점검설비를 삭제함. 
                        exec SP_PM_MAKE_SCHEDPMI_DELETE @v_comp_no , @pm_id, @pmequip_id, @v_del_start_date;
                       -----------------------------------------------------------------------------------------------------------------------------------------------
                       
                       -- 예방작업을 완료한 최종일자를 확인했으므로 그 다음 일정을 언제 생성해야 할지를 알아냄.
                       SELECT @v_last_sch_date =     
                            CASE 
									WHEN @period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									WHEN @period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * 7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									WHEN @period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									WHEN @period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@cycle * 12 ),CAST(@v_last_sch_date AS DATETIME)), 112)
							END
                        ;
					end

                ELSE 
				   begin
                        -- 사용자가 입력한 날짜를 작업일정의 시작으로 결정함.
                        set @v_last_sch_date = @last_sch_date;
                       
                       -- 미 작업이라고 하더라도 오늘일자 이후에 데이타만 삭제함. 오늘이전 데이타는 삭제하지 않음. 
                        if @v_to_day > @v_last_sch_date
                            set @v_del_start_date = @v_to_day;
                        else
                            set @v_del_start_date = @v_last_sch_date;

                        
                        -- 작업이 완료되지 않은 오더를 삭제함. 오더에 상태가 결재중이거나, 취소이거나 하면 어떻게 처리해야 할지 추가로 결정해야 함.
                        exec SP_PM_MAKE_SCHEDPMI_DELETE @v_comp_no , @pm_id, @pmequip_id, @v_del_start_date;
				   end
                       
                -- 여기서 부터는 예방작업 일정을 만드는 작업을 수행함.
                -- 먼저 예방작업을 언제 만들지를 갱신하고 시작업..v_last_sch_date는 이 날짜 부터 일정을 생성해야 한다는 의미임.
                UPDATE TAPMEQUIP SET
                       last_sch_date = @v_last_sch_date
                      ,next_sch_date = @v_last_sch_date
                WHERE comp_no = @v_comp_no
                      AND pm_id = @pm_id
                      AND pmequip_id = @pmequip_id
                ; 
                
                
                --  v_target_date까지 예방작업일정을 생성함.
                WHILE @v_last_sch_date <= @v_target_date
				   begin
                        
                        -- v_last_sch_date 는 이 날짜에 일정을 생성해야 하는 일자인데 이 날짜가 근무일이 아니면 근무일을 찾아서 보정해 줘야 함.
                        -- v_last_sch_date 이 작업을 만들어야 하는 일자. cycle,period_type을 체크해서 이 과거 범위한에 최대한 근접한 근무일자에 작업을 만들어 줌.
                            SELECT @v_next_sch_date =  isnull(max(cal_date), '19000101')
                            FROM TAWRKCALENDAR A
                            WHERE A.comp_no = @v_comp_no
                                AND a.wrkcallist_id = @wrkcallist_id
                                AND A.cal_date <=@v_last_sch_date
                                and a.cal_date >  (CASE 
														WHEN @period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
														WHEN @period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * -7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
														WHEN @period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(1 * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)  --한달로 제한
														WHEN @period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(1 * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)  -- 한달로 제한
												END )
                                AND A.is_work = 'Y'
                            ;
                            
							IF @v_next_sch_date != '19000101'
								begin
								-- 단 그 근무일에 과거이면 생성하지 않고 오늘이후 조건시에만 생성해야 함.
								IF @v_next_sch_date > @v_to_day
									INSERT INTO TAPMINSSCHED(comp_no, pminssched_id, pmequip_id, equip_id,  pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
									values( @v_comp_no,NEXT VALUE FOR sqapminssched_id, @pmequip_id, @equip_id, @pm_id, @v_last_sch_date, @v_next_sch_date, @v_next_sch_date,'P' );
                                end;
							ELSE
								-- 19000101이면 근무일이 없다는 얘기이므로 상황이 복잡하기 때문에 경우에 따라서 다른 논리를 적용함.
                                -- 1. 일상점검이 아닌경우 휴일이라고 하더라도 일정을 생성함.                                
                                -- 2. 일상점검을 일정을 생성하지 않음.(일상점검의 기준: cycle < 7, period_type=D)
								begin
									set @v_next_sch_date = @v_last_sch_date
									IF @v_next_sch_date > @v_to_day and @period_type != 'D'
										INSERT INTO TAPMINSSCHED(comp_no, pminssched_id, pmequip_id, equip_id,  pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
										values( @v_comp_no,NEXT VALUE FOR sqapminssched_id, @pmequip_id, @equip_id, @pm_id, @v_last_sch_date, @v_next_sch_date, @v_next_sch_date,'P' );
									
								end
								;
                            UPDATE TAPMEQUIP SET
                                       last_sch_date = @v_last_sch_date   -- 휴일 보전전 일정, 이 일정으로 다음 작업을 생성해야 함
                                      ,next_sch_date = @v_next_sch_date  -- 휴일을 보정하고 난 실제 작업일정.
                            WHERE comp_no = @v_comp_no
                                AND pm_id = @pm_id
                                AND pmequip_id = @pmequip_id
                            ; 
                            
                            -- 다음에는 언제 일정을 생성해야할지 찾기.
                         SELECT @v_last_sch_date =    
                            CASE 
                                WHEN @period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
								WHEN @period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * 7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
								WHEN @period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
								WHEN @period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@cycle * 12 ),CAST(@v_last_sch_date AS DATETIME)), 112)
                            END
                        ;
                        
                    end



			FETCH NEXT FROM c_SP_PM_MAKE_SCHEDPMI_TIME INTO @pm_id,@CYCLE,@period_type,@last_sch_date,@equip_id,@wrkcallist_id,@PMEQUIP_ID
		    END
		CLOSE c_SP_PM_MAKE_SCHEDPMI_TIME
		DEALLOCATE c_SP_PM_MAKE_SCHEDPMI_TIME	   


     ;
     
/** 2018-12-14 국도화학 반영 */
/** 2018-12-17 10:08 국도화학 반영 */
/** 2018-12-17 12:20 국도화학 반영 */
/** 2018-12-19 11:00 국도화학 반영 */
/** 2018-12-19 17:25 국도화학 반영 */
/** 2018-12-20 17:10 국도화학 반영 */
     
/** 2018-12-21 이근환 */
ALTER PROCEDURE [dbo].[SP_KPI_MAKE_TAPTMONTHLYSTOCK] (
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
                AND EXISTS(SELECT 1 FROM tawarehouse 
                           WHERE comp_no = a.comp_no 
                           AND wcode_id = a.wcode_id
                           AND wh_type = 'DREAM'
                           AND wh_categ = 'PART')
                AND EXISTS(SELECT 1 FROM taparts
                           WHERE comp_no = a.comp_no
                           AND part_id = a.part_id
                           AND part_categ = 'SPPT'
                           AND is_stock_control = 'Y')
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
    

/** 2018-12-21 17:50 국도화학 반영 */
/** 2018-12-28 14:20 국도화학 반영 */
/** 2019-01-30 국도화학 반영 */
/** 2019-01-31 국도화학 반영 */


/** 2018-12-28 이근환 */
ALTER FUNCTION [dbo].[SFACDUSRD_ALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @P_CDUSRD_ID BIGINT
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.CDUSRD_ID
		   , A.CDUSRD_NO
           , A.P_CDUSRD_ID
           , 0 AS LVL
		   , CONVERT(VARCHAR, A.CDUSRD_ID) AS SORT
        FROM TACDUSRD A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and P_CDUSRD_ID = @P_CDUSRD_ID
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.CDUSRD_ID
		   , B.CDUSRD_NO
           , B.P_CDUSRD_ID
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.CDUSRD_ID)) AS SORT
        FROM TACDUSRD B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_CDUSRD_ID = C.CDUSRD_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)
;

ALTER FUNCTION [dbo].[SFACDUSRD_CALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @CDUSRD_ID BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.CDUSRD_ID
		   , A.CDUSRD_NO
           , A.P_CDUSRD_ID
           , 0 AS LVL
		   , CONVERT(VARCHAR, A.CDUSRD_ID) AS SORT
        FROM TACDUSRD A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
			and CDUSRD_ID = CASE @CDUSRD_ID WHEN '' THEN CDUSRD_ID ELSE @CDUSRD_ID END
			and isnull(description,'') like CASE @cdusrd_id WHEN '' THEN '%'+@DESCRIPTION+'%' ELSE '%' END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.CDUSRD_ID
		   , B.CDUSRD_NO
           , B.P_CDUSRD_ID
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.CDUSRD_ID)) AS SORT
        FROM TACDUSRD B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.P_CDUSRD_ID = C.CDUSRD_ID AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)
;

ALTER FUNCTION [dbo].[SFACDUSRD_PALL]
(
      @PCOMP_NO NVARCHAR(40)
    , @cdusrd_id BIGINT
	, @DESCRIPTION NVARCHAR(40)
)
RETURNS TABLE 
AS
RETURN(
    WITH DATATABLE AS ( 
        SELECT 
             A.COMP_NO 
           , A.cdusrd_id
		   , A.cdusrd_no
           , A.P_cdusrd_id
           , 0 AS LVL
		   , CONVERT(VARCHAR, A.cdusrd_id) AS SORT
        FROM TACDUSRD A
        WHERE 1=1
            and COMP_NO  = @PCOMP_NO
            and cdusrd_id = CASE @cdusrd_id WHEN '' THEN cdusrd_id ELSE @cdusrd_id END
			and isnull(description,'') like CASE @cdusrd_id WHEN '' THEN '%'+@DESCRIPTION+'%' ELSE '%' END
        UNION ALL 
        SELECT 
             B.COMP_NO 
           , B.cdusrd_id
		   , B.cdusrd_no
           , B.P_cdusrd_id
           , C.LVL+ 1
		   , CONVERT(VARCHAR, C.SORT + '-' + CONVERT(VARCHAR, B.cdusrd_id)) AS SORT
        FROM TACDUSRD B INNER JOIN DATATABLE C  ON B.COMP_NO = C.COMP_NO AND B.cdusrd_id = C.P_cdusrd_id AND B.COMP_NO= @PCOMP_NO
        WHERE 1=1
    ) 
    SELECT TOP (SELECT COUNT(*) FROM DATATABLE) * FROM DATATABLE
	ORDER BY SORT
)
;

/** 2019-01-03 고려용접봉(본사) 반영 */
/** 2019-01-07 국도화학(본사) 반영 */
/** 2019-01-11 국도화학 반영 */
/** 2019-01-17 국도화학 반영 */

/** 2019-01-17 양소영 */
UPDATE TAWORKORDER SET labor_amt = tot_amt;
UPDATE x SET x.part_amt = (SELECT ISNULL(SUM(aa.tot_price),0) FROM TAWOPARTS aa WHERE aa.comp_no = x.comp_no AND aa.wkor_id = x.wkor_id )					
			,x.tot_amt = ( (SELECT ISNULL(SUM(aa.tot_price),0) FROM TAWOPARTS aa WHERE aa.comp_no = x.comp_no AND aa.wkor_id = x.wkor_id) + ISNULL(x.labor_amt,0) )	
FROM TAWORKORDER x;
UPDATE x SET x.tot_amt = (ISNULL(x.labor_amt,0) + ISNULL(part_amt,0)) FROM TAWORKORDER x;

/** 2019-01-22 고려용접봉(본사) 반영 */
/** 2019-01-22 국도화학 반영 */
/** 2019-01-25 국도화학 반영 */
/** 2019-01-30 국도화학 반영 */
/** 2019-02-14 국도화학 반영 */
/** 2019-02-20 국도화학 반영 */
/** 2019-02-26 국도화학 반영 */



/** 2019-01-25 김남현 */
USE [dream_dev]
GO
/****** Object:  StoredProcedure [dbo].[SP_PM_MAKE_SCHEDULE_BYONE]    Script Date: 2019-01-23 오후 1:21:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_PM_MAKE_SCHEDULE_BYONE](
	  @v_comp_no     varchar(6)
     ,@v_pm_id       bigint
	 ,@v_user_id       bigint
     ,@v_target_date varchar(8)
	   
) as
    
	SET NOCOUNT ON;
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

	declare @v_count               numeric(4); 
    declare @v_is_manage_pmi_with_order                       varchar(20);
    
	DECLARE c_SP_PM_MAKE_SCHEDULE_BYONE CURSOR FOR
        SELECT
             A.pm_id
            ,a.wo_type
            ,a.pm_type
            ,isnull(A.schedule_type,'T') as schedule_type -- T:Time, U:Usage
            ,a.lnwrklist_id
			,a.wrkcallist_id
        FROM TAPMLST A
        WHERE 1=1
            AND A.comp_no = @v_comp_no
            AND A.pm_id     = @v_pm_id
            AND A.is_active = 'Y'
    ;

	DECLARE  @pm_id bigint
            ,@wo_type varchar(20)
            ,@pm_type  varchar(20)
            ,@schedule_type varchar(20)
            ,@lnwrklist_id  bigint
			,@wrkcallist_id  bigint
  

    OPEN c_SP_PM_MAKE_SCHEDULE_BYONE
	FETCH NEXT FROM c_SP_PM_MAKE_SCHEDULE_BYONE INTO @pm_id,@wo_type,@pm_type,@schedule_type,@lnwrklist_id,@wrkcallist_id
	WHILE (@@FETCH_STATUS=0)
	BEGIN
		IF (@wo_type = 'PMI' and @pm_type = 'RINS')  -- 점검중에서 점검, 정기점검, Event점검은  
		    begin
                IF @schedule_type = 'T'     -- Period Time Based Scheduling
				    --if @wrkcallist_id is not null
                        exec SP_PM_MAKE_SCHEDPMI_TIME @v_comp_no, @pm_id, @v_target_date ;
                else if @schedule_type = 'U'     -- Run Time Based Scheduling
                        --IF @lnwrklist_id IS NOT NULL  -- 가동시간이 설정되어 있지 않으면 SKIP
                            exec SP_PM_MAKE_SCHEDPMI_USE @v_comp_no, @pm_id, @v_target_date ;
            end
        else if @wo_type = 'PMI' and @pm_type = 'DINS'  -- 일상점검일 경우 어떤 오더도 생성하지 않음. 일상점검 테이블로 생성함.
			if @wrkcallist_id is not null
				exec SP_PM_MAKE_SCHEDDAY_TIME @v_comp_no, @pm_id, @v_target_date ;
        --else if @wo_type = 'PMI' and @pm_type ='EINS'   -- 이벤트 점검일 경우 Event 일자에 점검계획을 생성 함.
		--        exec SP_PM_MAKE_SCHEDPMIEVT_TIME @v_comp_no, @pm_id, @v_target_date ;
	    --else if @wo_type = 'PMI' and @pm_type = 'PINS'      -- 순회점검일 경우 
		--    if @wrkcallist_id is not null
		--        exec SP_PM_MAKE_SCHEDPTRL_TIME @v_comp_no, @pm_id, @v_target_date ;
        --else if @wo_type = 'PMI' and @pm_type ='HINS'    -- 시간점검일 경우
		     -- 아무일도 안함..
		--	 select 'No Work';
		else if @wo_type = 'PMI' and @pm_type ='CINS'    -- 파트체인지 점검
		    -- 아무일도 안함..
			select 'No Work';
        ELSE  -- 나머지는 모두 Work Order로 관리
			print('1');
			begin
                if @schedule_type = 'T'     -- Period Time Based Scheduling
                        --if @wrkcallist_id is not null
						    exec SP_PM_MAKE_SCHEDWO_TIME @v_comp_no, @pm_id, @v_target_date;
                else if @schedule_type = 'U'     -- Run Time Based Scheduling
                        --IF @LNWRKLIST_ID IS NOT NULL  -- 가동시간이 설정되어 있지 않으면 SKIP
                            exec SP_PM_MAKE_SCHEDWO_USE @v_comp_no, @pm_id, @v_target_date ;
			end
                
    FETCH NEXT FROM c_SP_PM_MAKE_SCHEDULE_BYONE INTO @pm_id,@wo_type,@pm_type,@schedule_type,@lnwrklist_id,@wrkcallist_id
	END
	CLOSE c_SP_PM_MAKE_SCHEDULE_BYONE
	DEALLOCATE c_SP_PM_MAKE_SCHEDULE_BYONE	


    
    
     UPDATE TABATPGM SET 
         exe_by = @v_user_id
        ,last_exe_date = CONVERT(varchar(8), getdate(), 112)
        ,last_exe_time = getdate()
    WHERE comp_no = @v_comp_no
        AND batpgm_no = 'TAPMSCHED_ONE'
    ;
    
    USE [dream_dev]
	GO
	/****** Object:  StoredProcedure [dbo].[SP_PM_MAKE_SCHEDDAY_TIME]    Script Date: 2019-01-23 오후 1:23:04 ******/
	SET ANSI_NULLS ON
	GO
	SET QUOTED_IDENTIFIER ON
	GO
	ALTER PROCEDURE [dbo].[SP_PM_MAKE_SCHEDDAY_TIME](
	      @v_comp_no     varchar(6)
	     ,@v_pm_id       bigint
	     ,@v_target_date varchar(8)
	) as
	    
		SET NOCOUNT ON;
		SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
	
		declare @v_count               numeric(4); 
		declare @v_del_start_date      varchar(8);
	    declare @v_last_sch_date       varchar(8);
		declare @v_next_sch_date       varchar(8);
	    declare @v_to_day              varchar(8);
		declare @cnt                   INT = 0;
	
	    
	    DECLARE C_SP_PM_MAKE_SCHEDDAY_TIME CURSOR FOR
	            SELECT
	                 A.pm_id
	                ,A.CYCLE
	                ,A.period_type 
	                ,b.last_sch_date    -- 다음일정 예정일[주말보정전]
	                ,b.equip_id
					,A.wrkcallist_id
					,B.PMEQUIP_ID
	                ,isnull(a.work_number,1) AS work_number
	                ,isnull(a.wo_res_bef,3) AS wo_res_bef
	            FROM TAPMLST A, TAPMEQUIP B
	            WHERE 1=1
	                AND A.COMP_NO = B.COMP_NO
	                AND A.PM_ID = B.PM_ID
	                AND A.comp_no = @v_comp_no
	                AND A.pm_id     = @v_pm_id
	                AND A.is_active = 'Y'
	                AND B.last_sch_date <= @v_target_date
	        ;
	
			set @v_to_day = CONVERT(varchar(8), getdate(), 112)
	
			DECLARE  
			     @pm_id bigint
	            ,@CYCLE numeric(4)
	            ,@period_type  varchar(20)
	            ,@last_sch_date varchar(8)
	            ,@equip_id  bigint
				,@wrkcallist_id  bigint
	            ,@PMEQUIP_ID  bigint
				,@work_number numeric(4)
				,@wo_res_bef numeric(5)
	            
	
			OPEN C_SP_PM_MAKE_SCHEDDAY_TIME
			FETCH NEXT FROM C_SP_PM_MAKE_SCHEDDAY_TIME INTO @pm_id,@CYCLE,@period_type,@last_sch_date,@equip_id,@wrkcallist_id,@PMEQUIP_ID,@work_number,@wo_res_bef
			WHILE (@@FETCH_STATUS=0)
				BEGIN
	
				    -- 스케쥴을 시작하기로 한 날짜 이후에 점검결과가 완료되었는지 확인.
	                -- 발행된건이 있으면 그 건의 날짜를 시작날짜로 지정하여 스케쥴을 생성함.
	                SELECT @v_count = COUNT(*)
	                FROM TAPMINSDSCHED
	                WHERE comp_no = @v_comp_no
	                    AND pm_id = @pm_id
	                     AND pmequip_id = @pmequip_id
	                    AND pmsched_status IN ('C') -- work order가 완료되었는지 확인.
	                    AND plan_init_date >= @last_sch_date  -- 스케쥴을 시작하기로 한 날짜 이후에 점검결과가 완료되었는지 확인.
	                ;
	                
	                IF @v_count > 0
					    begin
	                        -- 오늘부터 스케쥴시작 날짜 이전까지의  완료되지 않는 스케쥴,설비,점검결과를 삭제해야 함. 
	                        SELECT @v_last_sch_date = isnull(MAX(plan_init_date),@last_sch_date)
	                        FROM TAPMINSDSCHED
	                        WHERE comp_no = @v_comp_no
	                            AND pm_id = @pm_id
	                             AND pmequip_id = @pmequip_id
	                            AND pmsched_status IN ('C') -- work order가 완료된 상태
	                            AND plan_init_date >= @last_sch_date
	                        ;
	                        
	                        -- 스케쥴 시작할 날짜가 오늘보다 큰지 비교해서 기존에 생성된 작업중에 완료안된 작업을 삭제함.
	                        -- 미 작업이라고 하더라도 오늘일자 이후에 데이타만 삭제함. 오늘이전 데이타는 삭제하지 않음.
	                        -----------------------------------------------------------------------------------------------------------------------------------------------
	                        if @v_to_day > @v_last_sch_date
	                            set @v_del_start_date = @v_to_day;
	                        else
	                            set @v_del_start_date = @v_last_sch_date;
	                        
	                        -- 작업이 완료되지 않은 점검설비를 삭제함. 
	                        exec SP_PM_MAKE_SCHEDDAY_DELETE @v_comp_no , @pm_id, @pmequip_id, @v_del_start_date;
	                       -----------------------------------------------------------------------------------------------------------------------------------------------
	                       
	                       -- 예방작업을 완료한 최종일자를 확인했으므로 그 다음 일정을 언제 생성해야 할지를 알아냄.
	                       SELECT @v_last_sch_date =    
	                            CASE 
									 WHEN @period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									 WHEN @period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * 7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									 WHEN @period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									 WHEN @period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(@cycle * 12 ),CAST(@v_last_sch_date AS DATETIME)), 112)
	
	                            END
	                        ;
					   end
	
	                ELSE 
					   begin
	                        -- 사용자가 입력한 날짜를 작업일정의 시작으로 결정함.
	                        set @v_last_sch_date = @last_sch_date;
	                       
	                       -- 미 작업이라고 하더라도 오늘일자 이후에 데이타만 삭제함. 오늘이전 데이타는 삭제하지 않음. 
	                        if @v_to_day > @v_last_sch_date
	                            set @v_del_start_date = @v_to_day;
	                        else
	                            set @v_del_start_date = @v_last_sch_date;
	                        
	                        -- 작업이 완료되지 않은 오더를 삭제함. 오더에 상태가 결재중이거나, 취소이거나 하면 어떻게 처리해야 할지 추가로 결정해야 함.
	                        
	                         exec SP_PM_MAKE_SCHEDDAY_DELETE @v_comp_no , @pm_id, @pmequip_id, @v_del_start_date;
	                       
	                   end
	                -- 여기까지 정리...
	                -- 예방작업 주기의 시작일자를 기준으로 기존 데이타를 정리했음.
	                -- 예방작업 주기의 시작일자 이후에 완료건이 없으면 그 날짜 이후에 작업이나 작업일정을 삭제함.
	                -- 예방작업 주기의 시작일자 이후에 완교건이 있으면 그 완료된 일자를 시점으로 작업이나 작업예정일정을 삭제함.
	                
	                
	                
	                -- 여기서 부터는 예방작업 일정을 만드는 작업을 수행함.
	                -- 먼저 예방작업을 언제 만들지를 갱신하고 시작업..v_last_sch_date는 이 날짜 부터 일정을 생성해야 한다는 의미임.
	                UPDATE TAPMEQUIP SET
	                       last_sch_date = @v_last_sch_date
	                      ,next_sch_date = @v_last_sch_date
	                WHERE comp_no = @v_comp_no
	                      AND pm_id = @pm_id
	                      AND pmequip_id = @pmequip_id
	                ; 
	                
	                -- 현재일을 기준으로 3일 후까지의 물량을 생성
	                WHILE @v_last_sch_date <= CONVERT(VARCHAR(8), DATEADD(DAY,(@wo_res_bef),getdate()), 112) 
					   begin
	                        
	                        -- v_last_sch_date 는 이 날짜에 일정을 생성해야 하는 일자인데 이 날짜가 근무일이 아니면 근무일을 찾아서 보정해 줘야 함.
	                        -- v_last_sch_date 이 작업을 만들어야 하는 일자. cycle,period_type을 체크해서 이 과거 범위한에 최대한 근접한 근무일자에 작업을 만들어 줌.
	                            SELECT @v_next_sch_date = isnull(max(cal_date), '19721126')
	                            FROM TAWRKCALENDAR A
	                            WHERE A.comp_no = @v_comp_no
	                                AND a.wrkcallist_id = @wrkcallist_id
	                                AND A.cal_date <=@v_last_sch_date
	                                and a.cal_date >=  (CASE 
															  WHEN @period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
															  WHEN @period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * -7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
															  WHEN @period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(1 * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
															  WHEN @period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(1 * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)  --연간이라도 한달범위로 제한.
	                                                    END )
	                                AND A.is_work = 'Y'
	                            ;
	
	                            -- 19721126일 아니라면 근무일이 있다는 얘기이므로 그 날짜를 기준으로 작업을 생성하면 됨.
	                            if @v_next_sch_date <> '19721126'
								    begin
	                                    -- 단 그 근무일에 과거이면 생성하지 않고 오늘이후 조건시에만 생성해야 함.
	                                   IF @v_next_sch_date > @v_to_day
									       begin
										     SET @cnt = 0;
										       while @cnt < @work_number
											   begin
	                                              SET @cnt = @cnt + 1;
	
												  INSERT INTO TAPMINSDSCHED(comp_no, pminsdsched_id, pmequip_id, equip_id,  pm_id, plan_init_date, plan_date,sched_date, pmsched_status, work_number)
	                                              values( @v_comp_no, NEXT VALUE FOR sqapminsdsched_id, @pmequip_id, @equip_id, @pm_id, @v_last_sch_date, @v_next_sch_date, @v_next_sch_date,'P',@cnt);
	
											   end
										   end
	                                end
								else
								   begin
								     set @v_next_sch_date = @v_last_sch_date;
								   end
								    							   
	                            UPDATE TAPMEQUIP SET
	                                       last_sch_date = @v_last_sch_date   -- 휴일 보전전 일정, 이 일정으로 다음 작업을 생성해야 함
	                                      ,next_sch_date = @v_next_sch_date  -- 휴일을 보정하고 난 실제 작업일정.
	                            WHERE comp_no = @v_comp_no
	                                AND pm_id = @pm_id
	                                AND pmequip_id = @pmequip_id
	                            ; 
	                            
	                            -- 다음에는 언제 일정을 생성해야할지 찾기.
	                         SELECT    @v_last_sch_date = 
	                            CASE 
									 WHEN @period_type = 'D' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * 1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									 WHEN @period_type = 'W' THEN CONVERT(VARCHAR(8), DATEADD(DAY,(@cycle * -7 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									 WHEN @period_type = 'M' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(1 * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
									 WHEN @period_type = 'Y' THEN CONVERT(VARCHAR(8), DATEADD(MONTH,(1 * -1 ),CAST(@v_last_sch_date AS DATETIME)), 112)
	                            END
	                        ;
					   end
	
	
				FETCH NEXT FROM C_SP_PM_MAKE_SCHEDDAY_TIME INTO @pm_id,@CYCLE,@period_type,@last_sch_date,@equip_id,@wrkcallist_id,@PMEQUIP_ID,@work_number,@wo_res_bef
			    END
			CLOSE C_SP_PM_MAKE_SCHEDDAY_TIME
			DEALLOCATE C_SP_PM_MAKE_SCHEDDAY_TIME
			;
			
/** 2019-02-19 매일유업 반영 */
/** 2019-03-05 매일유업 반영 */
/** 2019-03-13 국도화학 반영 */
/** 2019-03-13 고려용접봉 반영 */
/** 2019-03-19 매일유업 반영 */
/** 2019-04-04 매일유업 반영 */
/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */
/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */
/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */

/** 2019-05-14 이지수 */
UPDATE TAEQASSLIST SET EQASSLIST_TYPE = 'LT';
			
/** 2019-05-21 이지수 */
insert into TAUGPGPGAU
select next value for sqaugpgpgau_id, x.usrgrp_id, (select pgpage_id  from TAPGPAGE  where c_page_id = (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail')), x.comp_no
from TAUGPGPGAU x
where x.pgpage_id in (select pgpage_id  from TAPGPAGE  where c_page_id = (select page_id from TAPAGE where file_name = 'workPmiPointDetail'));
insert into TAUGPGPGAU
select next value for sqaugpgpgau_id, x.usrgrp_id, (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail')), x.comp_no
from TAUGPGPGAU x
where x.pgpage_id in (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointDetail'));
insert into TAUGPGPGAU
select next value for sqaugpgpgau_id, x.usrgrp_id, (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList')), x.comp_no
from TAUGPGPGAU x
where x.pgpage_id in (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibList'));

insert into TAUGPGAU
select next value for sqaugpgau_id, x.usrgrp_id, (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail'), x.comp_no
from TAUGPGAU x
where x.page_id in (select page_id from TAPAGE where file_name = 'workPmiPointDetail');
insert into TAUGPGAU
select next value for sqaugpgau_id, x.usrgrp_id, (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList'), x.comp_no
from TAUGPGAU x
where x.page_id in (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibList');
insert into TAUGPGAU
select next value for sqaugpgau_id, x.usrgrp_id, (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibDetail'), x.comp_no
from TAUGPGAU x
where x.page_id in (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibDetail');

insert into TAUGPGBTNAU
select next value for SQAUGPGBTN_ID, x.usrgrp_id, (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail' and button_id = (select button_id from TAPGBTN where pgbtn_id = x.pgbtn_id))), x.comp_no
from TAUGPGBTNAU x
where x.pgbtn_id in (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointDetail'));
insert into TAUGPGBTNAU
select next value for SQAUGPGBTN_ID, x.usrgrp_id,(select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList' and button_id = (select button_id from TAPGBTN where pgbtn_id = x.pgbtn_id))), x.comp_no
from TAUGPGBTNAU x
where x.pgbtn_id in (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibList'));
insert into TAUGPGBTNAU
select next value for SQAUGPGBTN_ID, x.usrgrp_id,(select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibDetail' and button_id = (select button_id from TAPGBTN where pgbtn_id = x.pgbtn_id))), x.comp_no
from TAUGPGBTNAU x
where x.pgbtn_id in (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibDetail')); 

/** 2019-05-24 고려용접봉 반영 */
/** 2019-05-24 국도화학 반영 */


/** 2019-05-27 이근환 */
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'APP10','결재대기시 대기자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'APP20','결재완료시 기안자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'REQ10','작업요청시 접수자(부)에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'RQC10','작업불가,접수시 요청자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'WRK10','작업완료시 요청자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'PRI10','구매신청시 발주담당자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'ISS10','출고처리시 수령자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'QNA20','QNA완료시 요청자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'ERR10','오류발생시 담당자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'USR10','사용자계정 등록시 사용자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'USR20','사용자 비밀번호 리셋시 사용자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'MAL01','메일링주기설정에서 전송한 내용을 사용자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'PPR10','현장구매신청 시 구매 담당자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'WRK20','작업완료시 작업부서 전원에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (next value for sqamessagecateg_id,'RQC20','접수자 변경 시 변경된 접수자에게 전송','Y','N','Y');

INSERT INTO TAMSGCOMPSET(comp_no, msgcompset_id, message_object_type,mail_use,sms_use,is_use)
SELECT b.comp_no, next value for sqamsgcompset_id,a.message_object_type,a.mail_use,a.sms_use,a.is_use
FROM TAMESSAGECATEG a, TACOMP b;

INSERT INTO TAMSGEMPSET(comp_no,msgempset_id,message_object_type,mail_use,sms_use,is_use,emp_id)
SELECT a.comp_no, next value for sqamsgempset_id, a.message_object_type,a.mail_use,a.sms_use,a.is_use,b.emp_id
FROM TAMSGCOMPSET a INNER JOIN TAEMP b
ON a.comp_no = b.comp_no;

/** 2019-05-29 고려용접봉 반영 */
/** 2019-05-30 매일유업 반영 */
/** 2019-06-04 매일유업 반영 */

/** 2019-06-14 김남현 */
insert into TAINITALARMEMPSET(comp_no, initalarmempset_id, emp_id, init_alarm_list, is_notice, is_use, remark)
select b.comp_no, next value for sqainitalarmempset_id,  b.emp_id, a.cdsysd_no, a.is_use, a.is_use, a.remark
from tacdsysd a inner join taemp b on a.list_type = 'INIT_ALARM_LIST' and b.is_join = 'Y'
where 1=1;

/** 2019-06-17 경보제약 반영 */
/** 2019-06-17 고려용접봉 반영 */
/** 2019-06-26 고려용접봉 반영 */

/** 2019-06-26 이근환 */
ALTER PROCEDURE [dbo].[SP_IF_UPD_TXEQUIPMENT]  
   @V_COMP_NO varchar(max),
   @V_EQUIP_ID bigint,
   @V_USER_NO varchar(max)
AS 
   BEGIN

      DECLARE
         @C1$EQUIP_ID bigint, 
         @C1$ITEM_NO varchar(max), 
         @C1$DESCRIPTION varchar(max), 
         @C1$FULL_DESC varchar(4000), 
         @C1$IFDATE varchar(max)

      DECLARE
          C_DATA CURSOR LOCAL FORWARD_ONLY FOR 
            SELECT 
               A.EQUIP_ID, 
               A.ITEM_NO, 
               A.DESCRIPTION, 
               
                  (
                     SELECT AA.FULL_DESC
                     FROM dbo.TAEQLOC  AS AA
                     WHERE A.COMP_NO = AA.COMP_NO AND A.EQLOC_ID = AA.EQLOC_ID
                  ) AS FULL_DESC, 
               CONVERT(varchar(max), sysdatetime(), 112) AS IFDATE
            FROM dbo.TAEQUIPMENT  AS A
            WHERE 
               1 = 1 AND 
               A.COMP_NO = @V_COMP_NO AND 
               A.EQUIP_ID = @V_EQUIP_ID

      OPEN C_DATA

      WHILE 1 = 1
      
         BEGIN

            FETCH C_DATA
                INTO 
                  @C1$EQUIP_ID, 
                  @C1$ITEM_NO, 
                  @C1$DESCRIPTION, 
                  @C1$FULL_DESC, 
                  @C1$IFDATE

            IF @@FETCH_STATUS = -1
               BREAK

            UPDATE dbo.TXEQUIPMENT
               SET 
                  DESCRIPTION = @C1$DESCRIPTION, 
                  FULL_DESC = @C1$FULL_DESC
            WHERE TXEQUIPMENT.COMP_NO = @V_COMP_NO AND TXEQUIPMENT.EQUIP_ID = @C1$EQUIP_ID

            IF @@ROWCOUNT = 0
               INSERT dbo.TXEQUIPMENT(
                  COMP_NO, 
                  EQUIP_ID, 
                  ITEM_NO, 
                  DESCRIPTION, 
                  FULL_DESC, 
                  IFDATE)
                  VALUES (
                     @V_COMP_NO, 
                     @C1$EQUIP_ID, 
                     @C1$ITEM_NO, 
                     @C1$DESCRIPTION, 
                     @C1$FULL_DESC, 
                     @C1$IFDATE)

         END

      CLOSE C_DATA

      DEALLOCATE C_DATA

      UPDATE dbo.TABATPGM
         SET 
            EXE_BY = 
               (
                  SELECT SSMAROWNUM.USER_ID
                  FROM 
                     (
                        SELECT USER_ID, COMP_NO, USER_NO, ROW_NUMBER() OVER(
                           ORDER BY SSMAPSEUDOCOLUMN) AS ROWNUM
                        FROM 
                           (
                              SELECT TAUSER.USER_ID, TAUSER.COMP_NO, TAUSER.USER_NO, 0 AS SSMAPSEUDOCOLUMN
                              FROM dbo.TAUSER
                              WHERE 
                                 TAUSER.COMP_NO = @V_COMP_NO AND 
                                 TAUSER.USER_NO = @V_USER_NO AND 
                                 1 = 1
                           )  AS SSMAPSEUDO
                     )  AS SSMAROWNUM
                  WHERE 
                     SSMAROWNUM.COMP_NO = @V_COMP_NO AND 
                     SSMAROWNUM.USER_NO = @V_USER_NO AND 
                     SSMAROWNUM.ROWNUM = 1
               ), 
            LAST_EXE_DATE = CONVERT(varchar(max), sysdatetime(), 112), 
            LAST_EXE_TIME = CONVERT(NVARCHAR,GETDATE(),112)+REPLACE(CONVERT(NVARCHAR,GETDATE(),108),':','')
      WHERE TABATPGM.COMP_NO = @V_COMP_NO AND TABATPGM.BATPGM_NO = 'TXEQUIPMENT'

   END
/** 2019-06-26 고려용접봉 반영 */   
/** 2019-06-26 매일유업 반영 */
/** 2019-06-26 국도화학 반영 */
/** 2019-06-28 매일유업 반영 */
/** 2019-07-10 경보제약 반영 */
/** 2019-07-18 경보제약 반영 */
   
  /** 2019-07-23 김정우 */
/**
 * TACONFIG 의 값이 각 사이트에 맞게 아래 형식으로 변경되어야 합니다.
 * TACONFIG.ANT_APK_URL = http://ip:port/path/android_apk/ant/
 * TACONFIG.BEE_APK_URL = http://ip:port/path/android_apk/bee/
 * TACONFIG.CRICKET_APK_URL = http://ip:port/path/android_apk/cricket/
 * 
 */
   
/** 2019-07-25 경보제약 반영 */
/** 2019-07-30 매일유업 반영 */
/** 2019-08-07 경보제약 반영 */
/** 2019-08-09 경보제약 반영 */
/** 2019-08-12 경보제약 반영 */
/** 2019-08-13 경보제약 반영 */
/** 2019-08-19 경보제약 반영 */
   
/* 310 김남현  */
UPDATE TACDUSRM 
   SET description = (SELECT key_name FROM TALANG WHERE key_no = 'docType' AND lang = 'ko')  
 WHERE dir_type = 'DOC_CATEG';
 

/** 2019-08-22 경보제약 반영 */
/** 2019-08-23 국도화학 반영 */
 
 /** 298 소영 */
CREATE PROCEDURE [dbo].update_SEQ (
	@SEQ_NAME1 varchar(50)
	,@SEQ_NAME2 varchar(50)
)AS
	DECLARE
	 @INCVAL     bigint
    ,@L_VAL      bigint
	SELECT @INCVAL
			 = (SELECT (CASE WHEN ( SELECT current_value FROM sys.sequences WHERE name= @SEQ_NAME2 ) > ( SELECT current_value FROM sys.sequences WHERE name= @SEQ_NAME1 )
    							   THEN CONVERT(int, (SELECT current_value FROM sys.sequences WHERE name=@SEQ_NAME2 )) - convert(int, (SELECT current_value FROM sys.sequences WHERE name= @SEQ_NAME1 ) )
								   ELSE (1)
								 END) );
BEGIN
	exec ('ALTER SEQUENCE ' + @SEQ_NAME1 + ' INCREMENT BY ' + @INCVAL);
	exec ('SELECT NEXT VALUE FOR ' + @SEQ_NAME1 );
	exec ('ALTER SEQUENCE ' + @SEQ_NAME1 + ' INCREMENT BY 1 ');
	exec ('SELECT NEXT VALUE FOR ' + @SEQ_NAME1 );
END;

exec update_SEQ 'SQAPTWHBINNO_ID', 'SQAWHUSER_ID';

drop procedure update_SEQ;

/** 2019-08-26 본사 경보제약 반영 */
/** 2019-08-27 경보제약 반영 */
/** 2019-08-29 매일유업 반영 */
/** 2019-09-05 경보제약 반영 */

/** 308 + 은아 */
update taplant set plant_id = NEXT VALUE FOR SQAPLANT_ID;

/** 2019-09-11 경보제약 반영 */
/** 2019-09-18 본사 국도화학 반영 */

/** 300 + ghlee */
/**

UPDATE a SET a.pmsched_status=(SELECT pmsched_status FROM TAPMINSLIST WHERE comp_no=a.comp_no AND pminssched_id=a.pminssched_id)
FROM TAPMINSSCHED a
WHERE EXISTS(SELECT 1 FROM TAPMINSLIST WHERE comp_no=a.comp_no AND pminssched_id=a.pminssched_id AND pmsched_status!=a.pmsched_status);

UPDATE a SET a.pmsched_status=(SELECT pmsched_status FROM TAPMINSDLIST WHERE comp_no=a.comp_no AND pminsdsched_id=a.pminsdsched_id)
FROM TAPMINSDSCHED a
WHERE EXISTS(SELECT 1 FROM TAPMINSDLIST WHERE comp_no=a.comp_no AND pminsdsched_id=a.pminsdsched_id AND pmsched_status!=a.pmsched_status);

UPDATE a SET a.pmsched_status=(SELECT pmsched_status FROM TAWORKORDER WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id)
FROM TAPMSCHED a
WHERE EXISTS(SELECT 1 FROM TAWORKORDER WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id AND pmsched_status!=a.pmsched_status);


UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMPOINT a
WHERE EXISTS(SELECT pm_id FROM TAPMLST WHERE comp_no=a.comp_no AND pm_id=a.pm_id AND is_deleted='Y')
AND is_deleted='N';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMPART a
WHERE EXISTS(SELECT pm_id FROM TAPMLST WHERE comp_no=a.comp_no AND pm_id=a.pm_id AND is_deleted='Y')
AND is_deleted='N';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMEQUIP a
WHERE EXISTS(SELECT pm_id FROM TAPMLST WHERE comp_no=a.comp_no AND pm_id=a.pm_id AND is_deleted='Y')
AND is_deleted='N';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMINSSCHED a
WHERE EXISTS(SELECT pmequip_id FROM TAPMEQUIP WHERE comp_no=a.comp_no AND pmequip_id=a.pmequip_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMINSLIST a
WHERE EXISTS(SELECT pminssched_id FROM TAPMINSSCHED WHERE comp_no=a.comp_no AND pminssched_id=a.pminssched_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMINSDSCHED a
WHERE EXISTS(SELECT pmequip_id FROM TAPMEQUIP WHERE comp_no=a.comp_no AND pmequip_id=a.pmequip_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMINSDLIST a
WHERE EXISTS(SELECT pminsdsched_id FROM TAPMINSDSCHED WHERE comp_no=a.comp_no AND pminsdsched_id=a.pminsdsched_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAPMSCHED a
WHERE EXISTS(SELECT pmequip_id FROM TAPMEQUIP WHERE comp_no=a.comp_no AND pmequip_id=a.pmequip_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAWOPLAN a
WHERE EXISTS(SELECT pmsched_id FROM TAPMSCHED WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id AND is_deleted='Y')
AND is_deleted='N'
AND wo_status != 'C';

UPDATE a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')
FROM TAWORKORDER a
WHERE EXISTS(SELECT pmsched_id FROM TAPMSCHED WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id AND is_deleted='Y')
AND is_deleted='N'
AND wo_status != 'C';

UPDATE a set ptemg_task_status='W', wkor_id=NULL
FROM TAPTEMGISSLIST a
WHERE EXISTS(SELECT wkor_id FROM TAWORKORDER WHERE comp_no=a.comp_no AND wkor_id=a.wkor_id AND is_deleted='Y');
*/

/** 2019-10-01 매일유업 반영 */
/** 2019-10-02 경보제약 반영 */

/** 446 소영 */
UPDATE TAWOREQ SET req_plant = plant;

/** 2019-10-14 매일유업 반영 */
/** 2019-10-17 매일유업 반영 */

/** 445 + euna0207 */

/** 작업자 : 김은아 
 * 패치 시 불러주세요 ................ !! 
 * 0. copy table 생성문
 * 1. 설비부위의 수정시간, 생성시간의 특수문자 및 공백제거하는 일괄처리문 
 * 2. upd_date와 cre_date가 14자리가 아닐 경우, 뒤에 00 붙여주는 일괄처리문
 * 3. 설비부위와, 설비부위유닛 페이지에서 column_id가 UPDDATE, CREDATE인 그리드컬럼들의 TYPE을 'dhxCalendar'타입으로 변경하는 쿼리 
 * 
 *  사이트 패치 시, 기존의 설비부위 데이타 UPD_DATE, CRE_DATE가 어떤 형식으로 들어가있는지 확인 후 작업 */

/**

select * into copy_table_asmb from taeqasmb;

update taeqasmb set upd_date = dbo.SFAREMOVEALPHACHAR(upd_date)
from taeqasmb
where upd_date is not null;

update taeqasmb set cre_date= dbo.SFAREMOVEALPHACHAR(cre_date)
from taeqasmb
where cre_date is not null;


UPDATE TAEQASMB
SET UPD_DATE = upd_date+'00'
WHERE len(upd_date) != 14
and upd_date is not null;

UPDATE TAEQASMB
SET CRE_DATE= cre_date+'00'
WHERE len(cre_date) != 14
and cre_date is not null;

UPDATE TAPGGRIDCOL
SET TYPE = 'dhxCalendar'
WHERE UPPER(column_id) = 'UPDDATE'
AND pggrid_id = (SELECT pggrid_id FROM TAPGGRID WHERE page_id  = (SELECT PAGE_iD FROM TAPAGE WHERE FILE_NAME = 'maEqMstrAsmbList'));

UPDATE TAPGGRIDCOL
SET TYPE = 'dhxCalendar'
WHERE UPPER(column_id) = 'CREDATE'
AND pggrid_id = (SELECT pggrid_id FROM TAPGGRID WHERE page_id  = (SELECT PAGE_iD FROM TAPAGE WHERE FILE_NAME = 'maEqMstrAsmbList'));

UPDATE TAPGGRIDCOL
SET TYPE = 'dhxCalendar'
WHERE UPPER(column_id) = 'UPDDATE'
AND pggrid_id = (SELECT pggrid_id FROM TAPGGRID WHERE page_id  = (SELECT PAGE_iD FROM TAPAGE WHERE FILE_NAME = 'assetRptEqUnits'));

*/

/** youngjoo38_360 + 김영주 */
UPDATE TALNWRKLIST SET LNWRK_CALENDAR_TYPE = 'R';

/** 552 이근환 */
/**
UPDATE a set a.wcode_id=(SELECT wcode_id FROM tadept WHERE comp_no=a.comp_no AND dept_id=a.dept_id) FROM taptprlist a
WHERE a.wcode_id IS NULL;

UPDATE a 
set a.gr_on_qty = (SELECT sum(rec_qty) FROM taptprreclist WHERE comp_no=a.comp_no AND ptpoitem_id=a.ptpoitem_id)
from taptpoitem a;
UPDATE a 
set a.gr_on_qty = (SELECT sum(gr_on_qty) FROM taptpoitem WHERE comp_no=a.comp_no AND ptpritem_id=a.ptpritem_id)
, a.po_on_qty = (SELECT sum(po_qty) FROM taptpoitem WHERE comp_no=a.comp_no AND ptpritem_id=a.ptpritem_id)
from taptpritem a;
UPDATE a 
set a.gr_on_qty = (SELECT sum(gr_on_qty) FROM taptpritem WHERE comp_no=a.comp_no AND ptpnlist_id=a.ptpnlist_id)
, a.po_on_qty = (SELECT sum(po_on_qty) FROM taptpritem WHERE comp_no=a.comp_no AND ptpnlist_id=a.ptpnlist_id)
, a.pr_on_qty = (SELECT sum(rec_qty) FROM taptpritem WHERE comp_no=a.comp_no AND ptpnlist_id=a.ptpnlist_id)
from taptpnlist a;
*/

/** 2019-10-22 고려용접봉 반영 */

/*532 김남현*/
UPDATE TAEQHISTORY SET eqhist_gen_type = 'WORKORDER' where wkor_id is not null;
UPDATE TAEQHISTORY SET eqhist_gen_type = 'MANUAL' where wkor_id is null;
/** 2019-10-29 경보제약 반영 */
/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */
/** 2019-11-14 고려용접봉 반영 */

/** 670 이근환 */
/** 위치 별 MTTR/MTBF 지표를 사용하는 사이트의 경우 해당 쿼리를 적용해야 함
 *  TALNWRKLIST의 eqloc_id는 더이상 사용하지 않는 컬럼이고 TAEQLOC의 lnwrklist_id를 사용하기 때문.
 *  쿼리에 오류가 있을 경우 셋팅이 잘 못 되어있는 경우이므로 TALNWRKLIST의 eqloc_id 데이터를 직접 보고 수정해 주어야 함.
update a set a.lnwrklist_id=(select lnwrklist_id from TALNWRKLIST where comp_no=a.comp_no and eqloc_id=a.eqloc_id)
from TAEQLOC a
where a.lnwrklist_id is null;
*/

/** 2019-11-20 매일유업 반영 */
/** 2019-12-02 매일유업 반영 */ 

/** 758 이근환 */
/**
 * 운영환경에 배포 시 svr_addr의 주소에 운영환경에 맞는 아이피, 포트를 넣어야 함
 * 
UPDATE tacdsysd set param1='http://localhost:8080/dream/report.do'
WHERE list_type='RPTFILE_TYPE'
AND cdsysd_no='RDX';
UPDATE tacdsysd set param1='http://202.133.21.180:9040/ubi4/reportViewer.jsp'
WHERE list_type='RPTFILE_TYPE'
AND cdsysd_no='UBI';
INSERT INTO tarptcplist(comp_no,rptcplist_id,rptlist_no,description,is_use,remark)
SELECT comp_no, next value for sqarptcplist_id,'K001','설비대장','Y','설비관리 정보를 표시하는 출력물'
FROM tacomp
WHERE is_use='Y';
INSERT INTO tarptcpfile(comp_no,rptcpfile_id,rptcplist_id,rptlist_no,rptlist_name,rptfile_type,svr_addr,design_file,query_file,reversion_no,is_use)
SELECT comp_no,next value for sqarptcpfile_id,rptcplist_id,rptlist_no,'maEqMstrDetail_v1.0','RDX',(SELECT param1 FROM tacdsysd WHERE list_type='RPTFILE_TYPE' and cdsysd_no='RDX'),'/dream/print/rd/maEqMstrDetail.mrd','/dream/print/rd/Mssql/maEqMstrDetail.sql','1.0','Y'
FROM tarptcplist;
INSERT INTO tarptcpfile(comp_no,rptcpfile_id,rptcplist_id,rptlist_no,rptlist_name,rptfile_type,svr_addr,design_file,query_file,reversion_no,is_use)
SELECT comp_no,next value for sqarptcpfile_id,rptcplist_id,rptlist_no,'maEqMstrDetail_v1.0','UBI',(SELECT param1 FROM tacdsysd WHERE list_type='RPTFILE_TYPE' and cdsysd_no='UBI'),'/dream/print/ubiReport/maEqMstrDetail.jrf','/dream/print/ubiReport/Mssql/maEqMstrDetail.sql','1.0','Y'
FROM tarptcplist;
*/

/** 2019-12-13 한국내화 반영 */
/** 2019-12-17 표준 Dream 반영 */

/** 616 추가 */
UPDATE tapggridcol set column_id='ITEMNO'
WHERE pggrid_id=(SELECT pggrid_id FROM tapggrid WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='workPmiList'))
AND upper(column_id) = 'EQUIPNO';

/* 629 김남현 */

UPDATE tawoequip 
SET 
   tawoequip.eqasmb_id = x.eqasmb_id
from TAWORKORDER x
WHERE 1=1
and x.wkor_id = tawoequip.wkor_id;

/**
 *  TAWOEQUIP 테이블에 INSERT 시 eqasmb_id 의 값을 같이 넣어주도록 프로시져 수정
 * 	각 사이트 별 패치시 추가가 필요합니다. 
 * 	패치 전에 불러주세요.
 */

/**
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
                     
            insert into taworkorder(
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
				,'P'                 as wo_status -- stand by 
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
                    
			if(@v_wo_type='PMC')
				BEGIN
					INSERT INTO TAWOCALIB (comp_no , wkor_id , calib_type) VALUES (@v_comp_no,@v_wo_id,'R');
				END

            -- tawoequip 
            insert into tawoequip(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description, eqasmb_id)
            select a.comp_no, NEXT VALUE FOR sqawoequip_id,  @v_wo_id,  a.equip_id, a.eqctg_id, a.description, @eqasmb_id
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


			set @v_count = 0;
			select @v_count = COUNT(*)
			from tawoplan
			where comp_no = @v_comp_no
			and wkor_id = @v_wo_id;



			FETCH NEXT FROM c_pm_sched_id_SP_PM_MAKE_TO_WO INTO @pm_id,@wcode_id,@wo_type,@pm_type,@pmsched_id,@sched_date,@pmequip_id,@equip_id,@eqasmb_id
		END
    CLOSE c_pm_sched_id_SP_PM_MAKE_TO_WO
    DEALLOCATE c_pm_sched_id_SP_PM_MAKE_TO_WO	         
   ;
*/

/** 2019-12-20 고려용접봉 반영 */

/** 447 + 이지수 */


update  taday set dow_key_type = 'LABEL' 
                         , dow_key_no = 
                                    (case when dow = '1' then 'sun'
                                         when dow = '2' then 'mon'
                                         when dow = '3' then 'tue'
                                         when dow = '4' then 'wed'
                                         when dow = '5' then 'thu'
                                         when dow = '6' then 'fri'
                                         when dow = '7' then 'sat'
                                   end) ;


ALTER PROCEDURE [dbo].[SP_SETDEFAULT_DUMYDAYS]  
   @V_COMP_NO varchar(max),
   @V_USER_NO varchar(max)
AS 
   BEGIN

      DECLARE
         @V_COUNT numeric(4)

      DECLARE
         @C1$TDAY varchar(max), 
         @C1$TMONTH varchar(max), 
         @C1$TYEAR varchar(max), 
         @C1$DOW varchar(max), 
         @C1$WEEK varchar(max), 
         @C1$TQUARTER varchar(max)

      /* 오늘일자를 기준으로 올해 1일 부터 미래 2년 1개월의 일자를 새로 만듬.*/
      DECLARE
          C_DUMY_DATA CURSOR LOCAL FORWARD_ONLY FOR 
            WITH fci AS
			(
			SELECT  1 AS rn
			UNION ALL
			SELECT  rn + 1
			FROM    fci
			WHERE   rn < datediff(d, sysdatetime(), dateadd(m, 36, sysdatetime()))
			)
			SELECT 
				CONVERT(varchar(max), dateadd(d, CAST(fci.RN AS float(53)) - 1, convert(datetime, datename(year,sysdatetime())+'0101', 112)), 112) AS TDAY, 
				CONVERT(varchar(6), dateadd(d, CAST(fci.RN AS float(53)) - 1, convert(datetime, datename(year,sysdatetime())+'0101', 112)), 112) AS TMONTH, 
				DATEPART(YYYY,dateadd(d, CAST(fci.RN AS float(53)) - 1, convert(datetime, datename(year,sysdatetime())+'0101', 112))) AS TYEAR, 
				DATEPART(WEEKDAY,dateadd(d, CAST(fci.RN AS float(53)) - 1, convert(datetime, datename(year,sysdatetime())+'0101', 112)))/*요일1:일, 2:월...7:토*/ AS DOW, 
				DATEPART(ISO_WEEK,dateadd(d, CAST(fci.RN AS float(53)) - 1, convert(datetime, datename(year,sysdatetime())+'0101', 112)))/*ISO 표준주차(월 ~ 일)*/ AS WEEK, 
				DATEPART(QQ,dateadd(d, CAST(fci.RN AS float(53)) - 1, convert(datetime, datename(year,sysdatetime())+'0101', 112)))/*분기..*/ AS TQUARTER
			FROM 
				fci option(maxrecursion 0)



      OPEN C_DUMY_DATA

      /* 미래 2년 + 1개월치 날짜를 새로게 만듬.*/
      WHILE 1 = 1
      
         BEGIN

            FETCH C_DUMY_DATA
                INTO 
                  @C1$TDAY, 
                  @C1$TMONTH, 
                  @C1$TYEAR, 
                  @C1$DOW, 
                  @C1$WEEK, 
                  @C1$TQUARTER

            IF @@FETCH_STATUS = -1
               BREAK

            SELECT @V_COUNT = count_big(*)
            FROM dbo.TADAY
            WHERE 1 = 1 AND TADAY.TDAY = @C1$TDAY

            IF @V_COUNT = 0
               INSERT dbo.TADAY(
					  SEQ, 
					  TDAY, 
					  TMONTH, 
					  TQUARTER, 
					  THALF, 
					  TYYYY, 
					  WEEK, 
					  DOW,
					  DOW_KEY_TYPE,
					  DOW_KEY_NO
				  )VALUES (
                     
                        (
                           SELECT max(TADAY.SEQ) + 1 AS expr
                           FROM dbo.TADAY
                        ), 
                     @C1$TDAY, 
                     @C1$TMONTH, 
                     @C1$TQUARTER, 
                     
                        CASE 
                           WHEN @C1$TQUARTER < '3' THEN '1'
                           ELSE '2'
                        END, 
                     @C1$TYEAR, 
                     @C1$WEEK, 
                     @C1$DOW
					 ,'LABEL'
					, (case when @C1$DOW = '1' then 'sun'
								when @C1$DOW = '2' then 'mon'
								when @C1$DOW = '3' then 'tue'
								when @C1$DOW = '4' then 'wed'
								when @C1$DOW = '5' then 'thu'
								when @C1$DOW = '6' then 'fri'
								when @C1$DOW = '7' then 'sat'
						end)
			)


               DECLARE
                  @db_null_statement int

            SELECT @V_COUNT = count_big(*)
            FROM dbo.TAMONTH
            WHERE 1 = 1 AND TAMONTH.TMONTH = @C1$TMONTH

            IF @V_COUNT = 0
               INSERT dbo.TAMONTH(
                  SEQ, 
                  TMONTH, 
                  TQUARTER, 
                  THALF, 
                  TYYYY)
                  VALUES (
                     
                        (
                           SELECT max(TAMONTH.SEQ) + 1 AS expr
                           FROM dbo.TAMONTH
                        ), 
                     @C1$TMONTH, 
                     @C1$TQUARTER, 
                     
                        CASE 
                           WHEN @C1$TQUARTER < '3' THEN '1'
                           ELSE '2'
                        END, 
                     @C1$TYEAR)


               DECLARE
                  @db_null_statement$2 int

            SELECT @V_COUNT = count_big(*)
            FROM dbo.TAYEAR
            WHERE 1 = 1 AND TAYEAR.TYYYY = @C1$TYEAR

            IF @V_COUNT = 0
               INSERT dbo.TAYEAR(SEQ, TYYYY)
                  VALUES (
                     (
                        SELECT max(TAYEAR.SEQ) + 1 AS expr
                        FROM dbo.TAYEAR
                     ), @C1$TYEAR)


               DECLARE
                  @db_null_statement$3 int

         END

      CLOSE C_DUMY_DATA

      DEALLOCATE C_DUMY_DATA

      /* 근무카렌다 전체에 대해서 default값 셋팅..*/
      EXECUTE dbo.SP_SETDEFAULT_WRKCAL_BYALL @V_COMP_NO = @V_COMP_NO, @V_USER_NO = @V_USER_NO

      /* 표준항목의 다음달 단가를 default 셋팅해줌.*/
      EXECUTE dbo.SP_SETDEFAULT_STDCHKPOINTPIRCE @V_COMP_NO = @V_COMP_NO, @V_USER_NO = @V_USER_NO

      UPDATE dbo.TABATPGM
         SET 
            EXE_BY = 
               (
                  SELECT SSMAROWNUM.USER_ID
                  FROM 
                     (
                        SELECT USER_ID, COMP_NO, USER_NO, ROW_NUMBER() OVER(
                           ORDER BY SSMAPSEUDOCOLUMN) AS ROWNUM
                        FROM 
                           (
                              SELECT TAUSER.USER_ID, TAUSER.COMP_NO, TAUSER.USER_NO, 0 AS SSMAPSEUDOCOLUMN
                              FROM dbo.TAUSER
                              WHERE 
                                 TAUSER.COMP_NO = @V_COMP_NO AND 
                                 TAUSER.USER_NO = @V_USER_NO AND 
                                 1 = 1
                           )  AS SSMAPSEUDO
                     )  AS SSMAROWNUM
                  WHERE 
                     SSMAROWNUM.COMP_NO = @V_COMP_NO AND 
                     SSMAROWNUM.USER_NO = @V_USER_NO AND 
                     SSMAROWNUM.ROWNUM = 1
               ), 
            LAST_EXE_DATE = CONVERT(varchar(max), sysdatetime(), 112), 
            LAST_EXE_TIME = CONVERT(NVARCHAR,GETDATE(),112)+REPLACE(CONVERT(NVARCHAR,GETDATE(),108),':','')
      WHERE TABATPGM.COMP_NO = @V_COMP_NO AND TABATPGM.BATPGM_NO = 'TAWRKCALENDAR'

   END
 
   
   
/** 2019-12-31 표준Dream 반영 */
   
   /** 731 이근환 */
update tapggridcol set column_id='ISJOINDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maEmpList'))
and upper(column_id)='ISJOIN';
update tapggridcol set column_id='ISDIRECTDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maEmpList'))
and upper(column_id)='ISDIRECT';
update tapggridcol set column_id='ISMAILRECDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maEmpList'))
and upper(column_id)='ISEMAILREC';
update tapgfield set field_id='maEmpDetailDTO.isDirectDesc'
where page_id=(select page_id from tapage where file_name='maEmpDetail')
and field_id='maEmpDetailDTO.isDirect';
update tapggridcol set column_id='USRGRPNAME'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maUserList'))
and upper(column_id)='GROUPNAME';
update tapggridcol set column_id='ISUSEDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maUserList'))
and upper(column_id)='ISUSE';
update tapggridcol set column_id='ISLOCKEDDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maUserList'))
and upper(column_id)='ISLOCKED';
update tapggridcol set column_id='ISMONITORDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maUserList'))
and upper(column_id)='ISMONITOR';
update tapggridcol set column_id='ISDIRECTDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maUserList'))
and upper(column_id)='ISDIRECT';
update tapgfield set field_id='maUserDetailDTO.isDirectDesc'
where page_id=(select page_id from tapage where file_name='maUserDetail')
and field_id='maUserDetailDTO.isDirect';
update tapggridcol set column_id='EQCTGTYPEDESC'
where pggrid_id=(select pggrid_id from tapggrid where page_id=(select page_id from tapage where file_name='maUserList'))
and upper(column_id)='MNGEQCTGTYPEDESC';

/*750 김남현*/
UPDATE TAWOPLAN SET woplan_status = (SELECT a.wo_status FROM TAWOPLAN a WHERE TAWOPLAN.wkor_id = a.wkor_id);

/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */

/*784 김남현*/
/*
 * TAPARTS에 최신 단가 갱신 시, 화폐도 같이 업데이트
 * 사이트 패치 시 각 사이트의 프로시저 확인 후 currency 입력 
 * 확인이 안되는 부분은 불러주세요.
 */

/*
ALTER PROCEDURE [dbo].[SP_PT_INSTOCK] (
       @v_comp_no      varchar(6)
      ,@v_ptrechist_id      bigint
) 
as
    SET NOCOUNT ON;
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
    
    declare @v_count          as bigint
    
    DECLARE c_rec_list_SP_PT_INSTOCK CURSOR FOR
        select 
             ptrechist_id
            ,ptrec_mode
            ,ptrec_type
            ,dept_id
            ,wcode_id
            ,rec_date
            ,part_id
            ,case when part_grade = 'A' then 'A'
                  when part_grade = 'B' then 'B'
                  else 'B' end as part_grade
            ,isnull(rec_qty,0) as rec_qty
            ,isnull(unit_price,0) as unit_price
			,currency -- 
        from TAPTRECHIST
        where 1=1
            and comp_no = @v_comp_no
            and ptrechist_id = @v_ptrechist_id
        ;
        
    -- c_rec_list cursor parameter
    DECLARE  @c_ptrechist_id bigint
            ,@c_ptrec_mode   varchar(20)
            ,@c_ptrec_type   varchar(20) 
            ,@c_dept_id      bigint
            ,@c_wcode_id     bigint
            ,@c_rec_date     varchar(8)
            ,@c_part_id      bigint
            ,@c_part_grade   varchar(20)
            ,@c_rec_qty      numeric(18,3)
            ,@c_unit_price   numeric(18,3)
            ,@c_currency	 varchar(20) --
    OPEN c_rec_list_SP_PT_INSTOCK
    FETCH NEXT FROM c_rec_list_SP_PT_INSTOCK INTO @c_ptrechist_id,@c_ptrec_mode,@c_ptrec_type,@c_dept_id,@c_wcode_id,@c_rec_date,@c_part_id,@c_part_grade,@c_rec_qty,@c_unit_price,@c_currency --
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            
            IF @c_ptrec_mode = 'C' -- 입고
               BEGIN
                    select @v_count = count(*)
                    from taptstock
                    where 1=1
                        and comp_no = @v_comp_no
                        and wcode_id =@c_wcode_id
                        and part_id = @c_part_id
                        and part_grade = @c_part_grade
                    ;
                       
                    if @v_count > 0
                        update taptstock set
                             stock_qty = isnull(stock_qty,0) + @c_rec_qty
                        where 1=1
                             and comp_no = @v_comp_no
                             and wcode_id =@c_wcode_id
                             and part_id = @c_part_id
                             and part_grade = @c_part_grade
                        ;
                    else
                        insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                        values(@v_comp_no, @c_wcode_id, @c_part_id, @c_part_grade, @c_rec_qty)
                        ;

                       
                    -- 최신단가 갱신
                    update taparts set
                        last_price = @c_unit_price
						,last_gr_date = convert(varchar(8), getdate(), 112)
						, currency = @c_currency --
                    where comp_no = @v_comp_no
                        and part_id = @c_part_id
                    ;
               END
               
            IF @c_ptrec_mode = 'R' -- 취소
               BEGIN
               
                   select @v_count = count(*)
                   from taptstock
                   where 1=1
                        and comp_no = @v_comp_no
                        and wcode_id =@c_wcode_id
                        and part_id = @c_part_id
                        and part_grade = @c_part_grade
                   ;
                       
                   if @v_count > 0
                       update taptstock set
                            stock_qty = isnull(stock_qty,0) - @c_rec_qty
                       where 1=1
                            and comp_no = @v_comp_no
                            and wcode_id =@c_wcode_id
                            and part_id = @c_part_id
                            and part_grade = @c_part_grade
                       ;
                   else
                       insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                       values(@v_comp_no, @c_wcode_id, @c_part_id, @c_part_grade, @c_rec_qty * -1)
                       ;
               
               
               END
        
            FETCH NEXT FROM c_rec_list_SP_PT_INSTOCK INTO @c_ptrechist_id,@c_ptrec_mode,@c_ptrec_type,@c_dept_id,@c_wcode_id,@c_rec_date,@c_part_id,@c_part_grade,@c_rec_qty,@c_unit_price,@c_currency
        END
    CLOSE c_rec_list_SP_PT_INSTOCK
    DEALLOCATE c_rec_list_SP_PT_INSTOCK
*/

/*985 김남현*/
/*
 * 페이지 / 탭 / 버튼 권한 일괄 업데이트 CURSOR
 * 각 사이트 패치시 comp_no 변경해서 패치 부탁드립니다.
 * 패치 전에 확인이 필요해서 불러주세요.
 */

/*
DECLARE @v_usrgrp_id NUMERIC(18)
DECLARE @v_comp_no NVARCHAR(12)

SET @v_comp_no = '100';

-- 커서 생성
DECLARE TAUSRGRP_CURSOR CURSOR FOR
-- 커서를 돌릴 테이블 조회
SELECT 
	usrgrp_id
  FROM TAUSRGRP
 WHERE 1 = 1
   AND comp_no = @v_comp_no

-- 커서 열기
OPEN TAUSRGRP_CURSOR

-- 해당 ROW의 컬럼들의 값들을 순서대로 넣는다.
FETCH NEXT FROM TAUSRGRP_CURSOR INTO @v_usrgrp_id

-- 커서를 이용해서 한 ROW 씩 읽기 시작
-- @@FATCH_STATUS 이상 없을시에 0 / 마지막 행에 도달시에 -1
WHILE(@@FETCH_STATUS = 0)
BEGIN
	
	-- 페이지 권한
	DELETE FROM TAUGPGAU 				
	 WHERE page_id in (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail'))
	   AND usrgrp_id  	= (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = @v_comp_no AND usrgrp_id = @v_usrgrp_id) 				
       AND comp_no  	=  @v_comp_no

	INSERT INTO TAUGPGAU 
	SELECT 
		NEXT VALUE FOR SQAUGPGAU_ID
		, (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = @v_comp_no AND usrgrp_id = @v_usrgrp_id)
		, page_id
		, @v_comp_no 
	  FROM TAPAGE 
	 WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail')
	
	-- 탭 페이지 권한
	DELETE FROM TAUGPGPGAU 				
	 WHERE pgpage_id in (SELECT pgpage_id FROM TAPGPAGE WHERE c_page_id in (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail')))
	   AND usrgrp_id = (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = @v_comp_no AND usrgrp_id = @v_usrgrp_id) 				
	   AND comp_no   = @v_comp_no

	INSERT INTO TAUGPGPGAU
	SELECT 
		NEXT VALUE FOR SQAUGPGPGAU_ID
		, (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = @v_comp_no AND usrgrp_id = @v_usrgrp_id)
		, pgpage_id
		, @v_comp_no 
	  FROM TAPGPAGE 
	 WHERE c_page_id in (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail'))
	
	-- 페이지 버튼 권한
	DELETE FROM TAUGPGBTNAU 				
	 WHERE pgbtn_id  	in (SELECT pgbtn_id FROM TAPGBTN WHERE page_id in (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail')))
	   AND usrgrp_id = (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = @v_comp_no AND usrgrp_id = @v_usrgrp_id) 				
	   AND comp_no 	 =  @v_comp_no

	INSERT INTO TAUGPGBTNAU
	SELECT 
		NEXT VALUE FOR sqaugpgbtn_id
		, (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = @v_comp_no AND usrgrp_id = @v_usrgrp_id)
		, pgbtn_id
		, @v_comp_no
	  FROM TAPGBTN 
	 WHERE page_id in (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail'))
	 
	 -- 다음 row를 가지고 온다.
	 FETCH NEXT FROM TAUSRGRP_CURSOR INTO @v_usrgrp_id;
END

-- 커서를 닫는다.
CLOSE TAUSRGRP_CURSOR

-- 메모리에 할당한 커서를 해제한다.
DEALLOCATE TAUSRGRP_CURSOR
*/

/** 715 은아 */

update tatracelog 
set update_time = replace(replace(replace(update_time,'-',''),':',''),' ','');

/** 2020-01-14 한국내화 kref 반영 */

/** 2020-01-17 김정우 */
update TAMESSAGECATEG set kakao_use='N';
update TAMSGCOMPSET set kakao_use='N';
update TAMSGEMPSET set kakao_use='N';
 
update taconfig set value$='adf3924bfd774f5b130a7c5c43d81501dd657124' where name='KAKAO_ALARM_SENDER_KEY';
update taconfig set value$='QaDDmgS71u1O3hFqKVvC' where name='KAKAO_ALARM_PW';
update taconfig set value$='https://msggw.supersms.co:9443/v1/send/kko' where name='KAKAO_ALARM_API_URL';
update taconfig set value$='emaintec' where name='KAKAO_ALARM_ID';
update taconfig set value$='N' where name='IS_USE_KAKAO_ALARM_SERVICE';
update taconfig set value$='' where name='MESSAGE_KEY_VALUE';
update taconfig set value$='https://dream.emaintec.com/messageSend.do' where name='MESSAGE_SERVICE_URL';


update  taconfig set value$ = replace(value$,'index.do','') where name='DREAM_URL';


/** 2020-01-17 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-17 매일유업 반영 */

