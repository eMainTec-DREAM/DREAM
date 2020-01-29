
/* Index 변경 */
DROP INDEX IDX02_TAWORKORDER;

CREATE INDEX IDX02_TAWORKORDER ON TAWORKORDER
(COMP_NO, WO_TYPE, WKOR_DATE, WO_STATUS);







 
 /*2016.12.14일 금형에 사용자 코드 추가건. 박철완**/
 insert into TACDUSRD(comp_no, cdusrd_id, cdusrm_id,cdusrd_no, p_cdusrd_id, description, remark, ord_no, is_use)
select 
    (select comp_no from tacomp where init_ct_path_yn = 'Y')   as comp_no
    ,sqacdusrd_id.nextval as cdusrd_id
    ,(select cdusrm_id from tacdusrm where dir_type = 'EQSTRLOC_NO') as cdusem_id
    ,'IR' as cdusrd_no
    ,0 as p_cdusrd_id
    ,'Internal Repair' as description
    ,'Internal Repairing' as remark
    ,900  as ord_no
    ,'N'  as is_use
from dual;



insert into TACDUSRD(comp_no, cdusrd_id, cdusrm_id,cdusrd_no, p_cdusrd_id, description, remark, ord_no, is_use)
select 
    (select comp_no from tacomp where init_ct_path_yn = 'Y')   as comp_no
    ,sqacdusrd_id.nextval as cdusrd_id
    ,(select cdusrm_id from tacdusrm where dir_type = 'EQSTRLOC_NO') as cdusem_id
    ,'ER' as cdusrd_no
    ,0 as p_cdusrd_id
    ,'External Repair' as description
    ,'External Repairing' as remark
    ,910  as ord_no
    ,'N'  as is_use
from dual;


delete  from TACDUSRD where description like '%Mold Stock%';

insert into TACDUSRD(comp_no, cdusrd_id, cdusrm_id,cdusrd_no, p_cdusrd_id, description, remark, ord_no, is_use)
select 
    (select comp_no from tacomp where init_ct_path_yn = 'Y')   as comp_no
    ,sqacdusrd_id.nextval as cdusrd_id
    ,(select cdusrm_id from tacdusrm where dir_type = 'EQSTRLOC_NO') as cdusem_id
    ,'STOCK' as cdusrd_no
    ,0 as p_cdusrd_id
    ,'Mold Stock' as description
    ,'Mold Stock Storage Location' as remark
    ,920  as ord_no
    ,'Y'  as is_use
from dual;



/*2016.01.13 권한그룹에 일괄매뉴 생성*/
select * from TAUSRGRP;
--그룹별 매뉴
select comp_no, usrgrpmenu_id, usrgrp_id, menu_id from TAUSRGRPMENU where usrgrp_id = 68;

insert into TAUSRGRPMENU(comp_no, usrgrpmenu_id, usrgrp_id, menu_id)
select '100' as comp_no
          ,sqausrgrpmenu_id.nextval as usrgrpmenu_id
          ,68  as usrgrp_id
          ,menu_id
from TAMENU
where is_use = 'Y'
;
-- 그룹별 페이지.
select ugpgau_id, usrgrp_id, page_id from TAUGPGAU where usrgrp_id = 68;

insert into TAUGPGAU(ugpgau_id, usrgrp_id, page_id)
select sqaugpgau_id.nextval as ugpgau_id
       , 68 as usrgrp_id
       , page_id
 from TAPAGE where is_use = 'Y'
 ;
 
 -- 그룹별 탭
select ugpgpgau_id, usrgrp_id, pgpage_id from  TAUGPGPGAU where usrgrp_id = 68;

insert into TAUGPGPGAU(ugpgpgau_id, usrgrp_id, pgpage_id)
select 
    sqaugpgpgau_id.nextval as ugpgpgau_id
    ,68 as usrgrp_id
    ,pgpage_id
from TAPGPAGE
where is_use = 'Y'
;

-- 그룹별 탭 권한..
select ugpgbtnau_id, usrgrp_id, pgbtn_id from TAUGPGBTNAU where usrgrp_id = 68;

insert into TAUGPGBTNAU(ugpgbtnau_id, usrgrp_id, pgbtn_id)
select 
    SQAUGPGBTN_ID.nextval as ugpgbtnau_id
    , 68 as usrgrp_id
    , pgbtn_id
 from TAPGBTN
 where is_use = 'Y'
 ;

 
 /*2016.01.13 다름권한 그룹  권한 카피하기.*/
 -- 그룹별 페이지.
insert into TAUSRGRPMENU(comp_no, usrgrpmenu_id, usrgrp_id, menu_id)
select comp_no, sqausrgrpmenu_id.nextval as usrgrpmenu_id, 68 as usrgrp_id, menu_id  from TAUSRGRPMENU where usrgrp_id = 25;

-- 그룹별 페이지.
insert into TAUGPGAU(ugpgau_id, usrgrp_id, page_id)
select sqaugpgau_id.nextval as ugpgau_id, 68 as usrgrp_id, page_id from TAUGPGAU where usrgrp_id = 25;

 
 -- 그룹별 탭
 insert into TAUGPGPGAU(ugpgpgau_id, usrgrp_id, pgpage_id)
select sqaugpgpgau_id.nextval as ugpgpgau_id, 68 as usrgrp_id, pgpage_id  from  TAUGPGPGAU where usrgrp_id = 25;

-- 그룹별 탭 권한..
insert into TAUGPGBTNAU(ugpgbtnau_id, usrgrp_id, pgbtn_id)
select SQAUGPGBTN_ID.nextval as ugpgbtnau_id, 68 as usrgrp_id, pgbtn_id  from TAUGPGBTNAU where usrgrp_id = 25;

/*영어로 등록되어 있는 다국어를 다른 언어에 영어로 바로 넣기..*/

 insert into TALANG(lang_id, lang, key_type, key_no, key_name, upd_date, is_comm_Js_use, remark)
 select sqalang_id.nextval as lang_id, 'br' as lang, a.key_type, a.key_no, a.key_name, a.upd_date, a.is_comm_Js_use, a.remark
 from (
     select a.lang, a.key_type, a.key_no, a.key_name, a.upd_date, a.is_comm_Js_use, a.remark, b.lang as b_lang
     from talang a,  talang b 
     where a.key_type = b.key_type(+)
               and a.key_no = b.key_no(+)
               and a.lang = 'en' 
               and b.lang (+) = 'br'
        ) a
where a.b_lang is null
;

--SELECT 'INSERT INTO TAUSRGRP(COMP_NO, USRGRP_ID, USRGRP_NO, GROUP_NAME, REMARK) VALUES('''||COMP_NO||''','''||USRGRP_ID||''', '''||USRGRP_NO||''', '''||GROUP_NAME||''','''||REMARK||''');' FROM TAUSRGRP
--SELECT 'INSERT INTO TADEPT(COMP_NO, DEPT_ID, DEPT_NO, DESCRIPTION, P_DEPT_ID) VALUES('''||COMP_NO||''','''||DEPT_ID||''', '''||DEPT_NO||''', '''||DESCRIPTION||''','''||P_DEPT_ID||''');' FROM TADEPT
--SELECT 'INSERT INTO TAEMP(COMP_NO, EMP_ID, EMP_NO, EMP_NAME, DEPT_ID,IS_JOIN) VALUES('''||COMP_NO||''','''||EMP_ID||''', '''||EMP_NO||''', '''||EMP_NAME||''','''||DEPT_ID||''','''||IS_JOIN||''');'  FROM TAEMP
--SELECT  'INSERT INTO TAUSER(COMP_NO, USER_ID, USER_NO, PASSWORD, USER_NAME,USRGRP_ID, INIT_MENU_ID, EMP_ID, IS_USE, MENU_DISPLAY) VALUES('''||COMP_NO||''','''||USER_ID||''', '''||USER_NO||''', '''||PASSWORD||''','''||USER_NAME||''','''||USRGRP_ID||''','''||INIT_MENU_ID||''','''||EMP_ID||''','''||IS_USE||''','''||MENU_DISPLAY||''');'   FROM TAUSER

/**  2018-08-08 김정우 */
update tamaillist set mail_scope_type = 'I' ;


/** 2018-08-10 10:00 로얄캐닌 적용 */
/** 2018-08-17 평화오일씰 반영 */
/** 2018-08-22 평화오일씰 반영 */
/** 2018-08-30 평화오일씰 반영 */
/** 2018-09-05 평화오일씰 반영 */

/**  2018-09-13 양소영 */
SELECT 
	'ALTER SEQUENCE SQAPTSTKTAKEITEM_ID INCREMENT BY '||
    (SELECT
    	(CASE WHEN
        	(SELECT last_number FROM user_sequences WHERE sequence_name='SQAPTPRITEM_ID') > (SELECT last_number FROM user_sequences WHERE sequence_name='SQAPTSTKTAKEITEM_ID')
			THEN ( (SELECT last_number FROM user_sequences WHERE sequence_name='SQAPTPRITEM_ID') - (SELECT last_number FROM user_sequences WHERE sequence_name='SQAPTSTKTAKEITEM_ID') ) 
			ELSE (1)
		 END )  
	FROM DUAL)||
	';  SELECT SQAPTSTKTAKEITEM_ID.NEXTVAL FROM DUAL; ALTER SEQUENCE SQAPTSTKTAKEITEM_ID INCREMENT BY 1; '
FROM dual;

/** 2018-09-18 평화오일씰 반영 */

/** 2018-09-20 양소영 */
--이상점검처리 기존 데이타 이관 scrip **중복실행하여 중복되지 않도록 주의요청(한번 실행)**
-- (작업요청)이상점검처리 기존 데이타 이관 scrip
INSERT INTO TAWONGPOINTRES(comp_no, wongpointres_id , wongpoint_id, ngpointres_method, woreq_id, res_date, res_time, dept_id, emp_id)
SELECT 
     x.comp_no ,sqawongpointres_id.NEXTVAL AS wongpointres_id ,x.wongpoint_id ,'WOREQ' AS ngpointres_method ,x.woreq_id 
    ,x.repair_date AS res_date ,'' AS res_time  ,(SELECT dept_id FROM TAEMP WHERE comp_no=x.comp_no AND emp_id=x.repair_by) AS dept_id ,x.repair_by AS emp_id
FROM TAWONGPOINT x
WHERE PM_REP_METHOD_TYPE = 'REQ';
-- (작업계획)이상점검처리 기존 데이타 이관 scrip
INSERT INTO TAWONGPOINTRES(comp_no, wongpointres_id , wongpoint_id, ngpointres_method, wkor_id, res_date, res_time, dept_id, emp_id)
SELECT
     x.comp_no ,sqawongpointres_id.NEXTVAL AS wongpointres_id ,x.wongpoint_id ,'WOPLAN' AS ngpointres_method ,x.WOPLAN_WKOR_ID AS wkor_id
    ,x.repair_date AS res_date ,'' AS res_time ,(SELECT dept_id FROM TAEMP WHERE comp_no=x.comp_no AND emp_id=x.repair_by) AS dept_id, x.repair_by AS emp_id
FROM TAWONGPOINT x
WHERE PM_REP_METHOD_TYPE = 'PLAN';
-- (작업결과)이상점검처리 기존 데이타 이관 scrip
INSERT INTO TAWONGPOINTRES(comp_no, wongpointres_id , wongpoint_id, ngpointres_method, wkor_id, res_date, res_time, dept_id, emp_id)
SELECT
     x.comp_no ,sqawongpointres_id.NEXTVAL AS wongpointres_id ,x.wongpoint_id ,'WORSLT' AS ngpointres_method ,x.PM_WKOR_ID AS wkor_id
    ,x.repair_date AS res_date ,'' AS res_time ,(SELECT dept_id FROM TAEMP WHERE comp_no=x.comp_no AND emp_id=x.repair_by) AS dept_id ,x.repair_by AS emp_id
FROM TAWONGPOINT x
WHERE PM_REP_METHOD_TYPE = 'WO';

/** 2018-09-21 08:30 오뚜기협력사 반영 */

/** 2018-09-27 이근환 */
CREATE OR REPLACE FUNCTION SFAGETDATE(pCompNo VARCHAR2, pTimezone NUMBER, pIntervalType VARCHAR2, pInterval NUMBER, pIsModified VARCHAR2)
    RETURN DATE
IS
    v_date DATE;
BEGIN

    SELECT sys_extract_utc(systimestamp)+pTimezone/24
    INTO v_date
    FROM dual;
    
    IF(pIsModified = 'Y') THEN
    
        SELECT CASE WHEN to_char(v_date, 'hh24mi') < nvl(value$,'0000') THEN v_date-1 ELSE v_date END
        INTO v_date
        FROM TACONFIG
        WHERE comp_no = pCompNo
        AND NAME = 'WORK_START_BASE_TIME';
        
    END IF;
    
    IF(pIntervalType = 'YEAR') THEN
        SELECT add_months(v_date, pInterval*12)
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'MONTH') THEN
        SELECT add_months(v_date, pInterval)
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'WEEK') THEN
        SELECT v_date+pInterval*7
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'DAY') THEN
        SELECT v_date+pInterval
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'HOUR') THEN
        SELECT v_date+pInterval/24
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'MINUTE') THEN
        SELECT v_date+pInterval/24/60
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'SECOND') THEN
        SELECT v_date+pInterval/24/60/60
        INTO v_date
        FROM dual;
    ELSE
        SELECT v_date+pInterval
        INTO v_date
        FROM dual;
    END IF;
    
    RETURN v_date;
END;

/** 2018-09-28 평화오일씰 반영 */
/** 2018-10-02 평화오일씰 반영 */
/** 2018-10-05 평화오일씰 반영 */
/** 2018-10-10 평화오일씰 반영 */

/** 2018-10-11 이근환 */
CREATE OR REPLACE FUNCTION SFAGETDATE(pOffset NUMBER, pIntervalType VARCHAR2, pInterval NUMBER)
    RETURN TIMESTAMP
IS
    v_date TIMESTAMP;
BEGIN

    SELECT sys_extract_utc(systimestamp)+NUMTODSINTERVAL(pOffset,'HOUR')
    INTO v_date
    FROM dual;
    
    IF(pIntervalType = 'YEAR') THEN
        SELECT v_date+NUMTOYMINTERVAL(pInterval,'YEAR')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'MONTH') THEN
        SELECT v_date+NUMTOYMINTERVAL(pInterval,'MONTH')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'WEEK') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval*7,'DAY')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'DAY') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'DAY')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'HOUR') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'HOUR')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'MINUTE') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'MINUTE')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'SECOND') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'SECOND')
        INTO v_date
        FROM dual;
    ELSE
        SELECT v_date+NUMTODSINTERVAL(pInterval,'DAY')
        INTO v_date
        FROM dual;
    END IF;
    
    RETURN v_date;
END;
/

/** 2018-10-11 평화오일씰 반영 */
/** 2018-10-16 09:30 동국제약 반영 */

 /** 2018-10-16 12:00 오뚜기 협력사 반영 */
/** 2018-10-18 평화오일씰 반영 */

/**  2018-09-01 박철완 */ 
/* 구매신청 프로그램이 패치될때 그때 1회 적용해야 함.
update TAPTPRLIST set ptprlist_status = 'W111' where PTPRLIST_STATUS = 'W';
update TAPTPRLIST set ptprlist_status = 'W' where ptprlist_status = 'C';
update TAPTPRLIST set ptprlist_status = 'C' where ptprlist_status = 'W111';

DELETE TACDSYSD WHERE LIST_TYPE = 'PTPRLIST_STATUS';

INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'W', '작성중', '010', 'Y', '작성중', '', '', 'CODESET', 'PTPRLIST_STATUS.W'); 
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'WRA', '결재요청', '020', 'Y', '결재요청', '', '', 'CODESET', 'PTPRLIST_STATUS.WRA'); 
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'WOA', '결재중', '030', 'Y', '결재중', '', '', 'CODESET', 'PTPRLIST_STATUS.WOA'); 
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'WDA', '결재반려', '040', 'Y', '결재반려', '', '', 'CODESET', 'PTPRLIST_STATUS.WDA'); 
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'C', '구매신청완료', '050', 'Y', '요청완료', '', '', 'CODESET', 'PTPRLIST_STATUS.C'); 
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'POC', '발주완료', '060', 'Y', '발주완료', '', '', 'CODESET', 'PTPRLIST_STATUS.POC'); 
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'GRW', '입고대기', '070', 'Y', '입고대기', '', '', 'CODESET', 'PTPRLIST_STATUS.GRW'); 
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPRLIST_STATUS'),'PTPRLIST_STATUS', 'GRC', '입고완료', '080', 'Y', '입고완료', '', '', 'CODESET', 'PTPRLIST_STATUS.GRC');
INSERT INTO TACDSYSD(CDSYSD_ID,CDSYSM_ID,LIST_TYPE,CDSYSD_NO,DESCRIPTION,ORD_NO,IS_USE,REMARK,param1, param2,key_type, key_no) VALUES(SQACDSYSD_ID.NEXTVAL,(SELECT AA.CDSYSM_ID FROM TACDSYSM AA WHERE LIST_TYPE= 'PTPNLIST_STATUS'),'PTPNLIST_STATUS', 'POW', '발주대기', '050', 'Y', '발주대기', '', '', 'CODESET', 'PTPNLIST_STATUS.POW'); 
 
*/

/** 2018-10-19 10:00 동국제약 반영 */
/** 2018-10-23 평화오일씰 반영 */
/** 2018-10-24 평화오일씰 반영 */
/** 2018-10-23 오뚜기 협력사 반영 */
/** 2018-10-25 11:20 동국제약 반영 */
/** 2018-10-26 평화오일씰 반영 */
/** 2018-10-27 평화오일씰 반영 */
/** 2018-10-29 본사Dream 반영 */

/** 2018-10-30 이근환 */
UPDATE tawoparts a set a.ptisslist_id = (SELECT ptisslist_id FROM taptisslist WHERE comp_no = a.comp_no AND wopart_id = a.wopart_id);

/** 2018-10-30 평화오일씰 반영 */
/** 2018-10-30 안국약품 반영 */

/** 2018-10-30 김영주 */
CREATE OR REPLACE PROCEDURE SP_UPD_TADOCCTG (
      v_comp_no     IN varchar2
     ,v_docctg_id      IN number
) IS
        v_count number(4);
BEGIN
    UPDATE TADOCCTG    SET                                       
                full_desc = (                                                    
                SELECT 
                        MIN(DECODE(lev,5,description||'-',''))||
                        MIN(DECODE(lev,4,description||'-',''))||                
                        MIN(DECODE(lev,3,description||'-',''))||                
                        MIN(DECODE(lev,2,description||'-',''))||                
                        MIN(DECODE(lev,1,description,'')) a                      
                FROM (                                                        
                        SELECT description,LEVEL lev                             
                        FROM TADOCCTG                                            
                        WHERE comp_no=v_comp_no  
                        START WITH docctg_id=v_docctg_id    
                        CONNECT BY PRIOR  p_docctg_id = docctg_id                   
                    )                                                            
                )            
                WHERE docctg_id = v_docctg_id      
            ;                 
END;
/

/** 2018-10-30 김영주 */
CREATE OR REPLACE PROCEDURE SP_UPD_TADOCCTG_ALL (
      v_comp_no     IN varchar2
) IS
   v_count number(4);
    CURSOR c_data IS
        SELECT comp_no, docctg_id                         
        FROM TADOCCTG                                            
        WHERE comp_no=v_comp_no
        ORDER BY docctg_id            
        ;
BEGIN
    FOR c1 IN c_data LOOP
       SP_UPD_TADOCCTG(c1.comp_no, c1.docctg_id);
    END LOOP;
END;
/

/** 2018-10-30 15:10 동국제약 반영 */
/** 2018-10-31 평화오일씰 반영 */
/** 2018-11-01 10:40 동국제약 반영 */
/** 2018-11-01 평화오일씰 반영 */
/** 2018-11-02 평화오일씰 반영 */
/** 2018-11-02 오뚜기 협력사 반영 */
/** 2018-11-05 대웅제약 반영 */
/** 2018-11-06 1030 오뚜기협력사 반영 */
/** 2018-11-06 평화오일씰 반영 */
/** 2018-11-07 16:30 동국제약 반영 */
/** 2018-11-07 평화오일씰 반영 */
/** 2018-11-09 평화오일씰 반영 */
/** 2018-11-13 평화오일씰 반영 */
/** 2018-11-13 안국약품 반영 */
/** 2018-11-14 1015 오뚜기협력사 반영 */
/** 2018-11-14 평화오일씰 반영 */
/** 2018-11-15 08:15 동국제약 반영 */
/** 2018-11-15 안국약품 반영 */
/** 2018-11-15 평화오일씰 반영 */
/** 2018-11-16 평화오일씰 반영 */
/** 2018-11-19 안국약품 반영 */
/** 2018-11-20 평화오일씰 반영 */
/** 2018-11-21 평화오일씰 반영 */
/** 2018-11-22 평화오일씰 반영 */
/** 2018-11-23 평화오일씰 반영 */
/** 2018-11-26 평화오일씰 반영 */
/** 2018-11-27 안국약품 반영 */
/** 2018-11-27 평화오일씰 반영 */
/** 2018-11-27 오뚜기협력사 반영 */
/** 2018-11-27 05:30 동국제약 반영 */
/** 2018-11-28 평화오일씰 반영 */
/** 2018-11-28 안국약품 반영 */
/** 2018-11-28 본사Dream 반영 */
/** 2018-11-28 15:45 동국제약 반영 */

/** 2018-11-29 이근환 */
CREATE OR REPLACE PROCEDURE SP_KPI_MAKE_TAPTMONTHLYSTOCK(
      v_comp_no     IN varchar2      
     ,v_user_no      IN varchar2
) IS
    v_count                                 number(4); 
    
    -- exec SP_KPI_MAKE_TAPTMONTHLYSTOCK('100','ADMIN');
    
    CURSOR c_base IS
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
                AND a.comp_no = v_comp_no
                AND a.yyyymm = to_char(add_months(sysdate,-1),'yyyymm')  -- 한달전
            ;
            
    CURSOR c_rec_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.rec_date,1,6) AS yyyymm
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
                AND a.comp_no = v_comp_no
                AND a.ptrec_type = 'PRREC'
                AND a.rec_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.rec_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.rec_date,1,6),a.part_id,a.part_grade   
            ;
     
    CURSOR c_used_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.iss_date,1,6) AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.iss_qty 
                        WHEN a.ptiss_mode = 'R' THEN a.iss_qty * -1
                        END) AS iss_qty
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.tot_price 
                        WHEN a.ptiss_mode = 'R' THEN a.tot_price * -1
                        END) AS tot_price
            FROM taptisshist a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.ptiss_type = 'WOISS'
                AND a.iss_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.iss_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.iss_date,1,6),a.part_id,a.part_grade   
            ;
            
       
    
        
BEGIN

   UPDATE taptstock a set 
        a.unit_price = (SELECT last_price FROM taparts
                              WHERE comp_no = a.comp_no
                              AND part_id = a.part_id)
   WHERE a.comp_no = v_comp_no
   ;

   UPDATE taptmonthlystock a set 
       a.rec_qty = 0
       ,a.rec_tot_price = 0
       ,a.issue_qty = 0
       ,a.issue_tot_price = 0
   WHERE 1=1
       AND a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm') 
       AND a.yyyymm <= to_char(sysdate,'yyyymm')
   ;
   
   UPDATE taptmonthlystock a set 
       a.base_qty = 0
       ,a.base_unit_price = 0
       ,a.base_tot_price = 0
       ,a.result_tot = 0
       ,a.result_unit_price = 0
       ,a.result_tot_price = 0
   WHERE 1=1
       AND a.yyyymm = to_char(sysdate,'yyyymm')
   ;


    FOR c1 IN c_base LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.base_qty = c1.result_tot
                    ,a.base_unit_price = c1.result_unit_price
                    ,a.base_tot_price = c1.result_tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, base_qty, base_unit_price, base_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.result_tot,  c1.result_unit_price, c1.result_tot_price
           );
       END IF;
       
   END LOOP;
    
   FOR c1 IN c_rec_part LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.rec_qty = c1.rec_qty
                    ,a.rec_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, rec_qty, rec_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.rec_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
        
   FOR c1 IN c_used_part LOOP
   
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.issue_qty = c1.iss_qty
                    ,a.issue_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, issue_qty, issue_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.iss_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   FOR c1 IN (SELECT 
                        a.comp_no
                        ,a.wcode_id
                        ,to_char(sysdate,'yyyymm') AS yyyymm
                        ,a.part_id
                        ,a.part_grade
                        ,a.stock_qty
                        ,nvl(a.unit_price,0) AS unit_price
                        ,a.stock_qty * nvl(a.unit_price,0) AS tot_price
                    FROM taptstock a
                    WHERE 1=1
                        AND a.comp_no = v_comp_no
                        AND a.stock_qty > 0) 
   LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.result_tot = c1.stock_qty
                    ,a.result_unit_price = c1.unit_price
                    ,a.result_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, result_tot, result_unit_price, result_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.stock_qty,  c1.unit_price, c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   
   
   
   
    UPDATE TABATPGM set 
         exe_by = (SELECT user_id 
                        FROM tauser 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPTMONTHLYSTOCK'
    ;
    
    
    
    
END;
/

/** 2018-11-29 평화오일씰 반영 */
/** 2018-12-04 평화오일씰 반영 */


/** 2018-12-05 김정우 -> 1개의 PM_ID에 대한 리스트를 뽑아와서  스케쥴을 만들어줘야하는데 조건에 pm_id가 빠져있어서 에러(파라미터)가 나거나 전체 PM을 다시 만드는 행위를 계속하여,
 *  수정하였음. pm_id 조건 추가*/
CREATE OR REPLACE PROCEDURE SP_PM_MAKE_TO_ONESCHED(
      v_comp_no          IN varchar2      
     ,v_pm_id              IN number
) IS
    v_count                         number(4); 
    
    -- exec SP_PM_MAKE_WORKORDER('100','ADMIN');
    CURSOR c_pm_sched_list IS
    select * from (
        SELECT 
             b.pm_type
            ,b.wo_type
            ,a.pmsched_id
            ,b.pm_id
            ,a.sched_date
        FROM TAPMSCHED a, TAPMLST b
        WHERE 1=1
            AND a.comp_no = b.comp_no
            AND a.pm_id = b.pm_id
            AND a.pm_id = v_pm_id
            AND a.comp_no = v_comp_no
            AND b.is_active = 'Y'
            AND a.pmsched_status = 'P' -- not release work order
            and b.is_deleted = 'N' 
            and b.schedule_type = 'T' 
            AND ( to_date(a.sched_date,'yyyymmdd') - trunc(sysdate) ) <= nvl(b.wo_res_bef,7)
        union all
        SELECT 
             b.pm_type
            ,b.wo_type
            ,a.pmsched_id
            ,b.pm_id
            ,a.sched_date
        FROM TAPMSCHED a, TAPMLST b
        WHERE 1=1
            AND a.comp_no = b.comp_no
            AND a.pm_id = b.pm_id
            AND a.pm_id = v_pm_id
            AND a.comp_no = v_comp_no
            AND b.is_active = 'Y'
            AND a.pmsched_status = 'P' -- not release work order
            and b.is_deleted = 'N' 
            and b.schedule_type in ( 'R','U') 
      ) a
       ORDER BY sched_date
        ;
        
    CURSOR c_pmins_sched_list IS
     select * from (
        SELECT 
             b.pm_type
            ,b.wo_type
            ,a.pminssched_id
            ,b.pm_id
            ,a.sched_date
        FROM TAPMINSSCHED a, TAPMLST b
        WHERE 1=1
            AND a.comp_no = b.comp_no
            AND a.pm_id = b.pm_id
            AND a.pm_id = v_pm_id
            AND a.comp_no = v_comp_no
            AND b.is_active = 'Y'
            and b.is_deleted = 'N'
            AND a.pmsched_status = 'P' -- not release work order
            and b.schedule_type = 'T'  
            AND ( to_date(a.sched_date,'yyyymmdd') - trunc(sysdate) ) <= nvl(b.wo_res_bef,7)
        union all
         SELECT 
             b.pm_type
            ,b.wo_type
            ,a.pminssched_id
            ,b.pm_id
            ,a.sched_date
        FROM TAPMINSSCHED a, TAPMLST b
        WHERE 1=1
            AND a.comp_no = b.comp_no
            AND a.pm_id = b.pm_id
            AND a.pm_id = v_pm_id
            AND a.comp_no = v_comp_no
            AND b.is_active = 'Y'
            and b.is_deleted = 'N'
            AND a.pmsched_status = 'P' -- not release work order 
            and b.schedule_type in ( 'R','U')
        ) a
        ORDER BY sched_date
        ;
        
    CURSOR c_pminsday_sched_list IS
        SELECT 
             b.pm_type
            ,b.wo_type
            ,a.pminsdsched_id
            ,b.pm_id
        FROM TAPMINSDSCHED a, TAPMLST b
        WHERE 1=1
            AND a.comp_no = b.comp_no
            AND a.pm_id = b.pm_id
            AND a.pm_id = v_pm_id
            AND a.comp_no = v_comp_no
            AND b.is_active = 'Y'
            and b.is_deleted = 'N'
            AND a.pmsched_status = 'P' -- not release work order 
            AND ( to_date(a.sched_date,'yyyymmdd') - trunc(sysdate) ) <= nvl(b.wo_res_bef,7)
        ORDER BY sched_date
        ;
        
    CURSOR c_pmpatrol_sched_list IS
        SELECT 
             b.pm_type
            ,b.wo_type
            ,a.pmptrlsched_id
            ,b.pm_id
        FROM TAPMPTRLSCHED a, TAPMLST b
        WHERE 1=1
            AND a.comp_no = b.comp_no
            AND a.pm_id = b.pm_id
            AND a.pm_id = v_pm_id
            AND a.comp_no = v_comp_no
            AND b.is_active = 'Y'
            and b.is_deleted = 'N'
            AND a.pmsched_status = 'P' -- not release work order 
            AND ( to_date(a.sched_date,'yyyymmdd') - trunc(sysdate) ) <= nvl(b.wo_res_bef,7)
        ORDER BY sched_date
        ;
     
        
        
BEGIN
    
        v_count := 0;
        FOR c1 IN c_pm_sched_list LOOP
                SP_PM_MAKE_TO_WO(v_comp_no, c1.pmsched_id);
                IF v_count = 0 THEN
                    SP_PM_UPDATE_LASTCPLT_DATE(v_comp_no, c1.pm_id,c1.pmsched_id);
                END IF;
                v_count := v_count + 1; 
        END LOOP;

        v_count := 0;
        FOR c1 IN c_pmins_sched_list LOOP
                SP_PM_MAKE_TO_PMI(v_comp_no, c1.pminssched_id);
                IF v_count = 0 THEN
                    SP_PM_UPDATE_LASTCPLT_DATE(v_comp_no, c1.pm_id,c1.pminssched_id);
                END IF;
                v_count := v_count + 1; 
        END LOOP;
        
        v_count := 0;
        FOR c1 IN c_pminsday_sched_list LOOP
                SP_PM_MAKE_TO_PMIDAY(v_comp_no, c1.pminsdsched_id);
                IF v_count = 0 THEN
                    SP_PM_UPDATE_LASTCPLT_DATE(v_comp_no, c1.pm_id,c1.pminsdsched_id);
                END IF;
                v_count := v_count + 1; 
        END LOOP;
        
        v_count := 0;
        FOR c1 IN c_pmpatrol_sched_list LOOP
                SP_PM_MAKE_TO_PATROL(v_comp_no, c1.pmptrlsched_id);
                IF v_count = 0 THEN
                    SP_PM_UPDATE_LASTCPLT_DATE(v_comp_no, c1.pm_id,c1.pmptrlsched_id);
                END IF;
                v_count := v_count + 1; 
        END LOOP;

    
END;
/

/** 2018-12-05 09:30 동국제약 반영 */
/** 2018-12-05 평화오일씰 반영 */
/** 2018-12-06 평화오일씰 반영 */

/** 2018-12-06 이근환 */
CREATE OR REPLACE PROCEDURE SP_KPI_MAKE_TAPTMONTHLYSTOCK(
      v_comp_no     IN varchar2      
     ,v_user_no      IN varchar2
) IS
    v_count                                 number(4); 
    
    -- exec SP_KPI_MAKE_TAPTMONTHLYSTOCK('100','ADMIN');
    
    CURSOR c_base IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,to_char(sysdate,'yyyymm') AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,a.result_tot AS base_qty
                ,a.result_unit_price AS base_unit_price
                ,a.result_tot_price AS base_tot_price
            FROM TAPTMONTHLYSTOCK a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.yyyymm = to_char(add_months(sysdate,-1),'yyyymm')  -- 한달전
            ;
            
    CURSOR c_rec_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.rec_date,1,6) AS yyyymm
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
                AND a.comp_no = v_comp_no
                AND a.ptrec_type = 'PRREC'
                AND a.rec_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.rec_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.rec_date,1,6),a.part_id,a.part_grade   
            ;
     
    CURSOR c_used_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.iss_date,1,6) AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.iss_qty 
                        WHEN a.ptiss_mode = 'R' THEN a.iss_qty * -1
                        END) AS iss_qty
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.tot_price 
                        WHEN a.ptiss_mode = 'R' THEN a.tot_price * -1
                        END) AS tot_price
            FROM taptisshist a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.ptiss_type = 'WOISS'
                AND a.iss_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.iss_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.iss_date,1,6),a.part_id,a.part_grade   
            ;
            
       
    
        
BEGIN

   UPDATE taptstock a set 
        a.unit_price = (SELECT last_price FROM taparts
                              WHERE comp_no = a.comp_no
                              AND part_id = a.part_id)
   WHERE a.comp_no = v_comp_no
   ;

   UPDATE taptmonthlystock a set 
       a.rec_qty = 0
       ,a.rec_tot_price = 0
       ,a.issue_qty = 0
       ,a.issue_tot_price = 0
   WHERE 1=1
       AND a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm') 
       AND a.yyyymm <= to_char(sysdate,'yyyymm')
   ;
   
   UPDATE taptmonthlystock a set 
       a.base_qty = 0
       ,a.base_unit_price = 0
       ,a.base_tot_price = 0
       ,a.result_tot = 0
       ,a.result_unit_price = 0
       ,a.result_tot_price = 0
   WHERE 1=1
       AND a.yyyymm = to_char(sysdate,'yyyymm')
   ;


    FOR c1 IN c_base LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.base_qty = c1.base_qty
                    ,a.base_unit_price = c1.base_unit_price
                    ,a.base_tot_price = c1.base_tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, base_qty, base_unit_price, base_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.base_qty,  c1.base_unit_price, c1.base_tot_price
           );
       END IF;
       
   END LOOP;
    
   FOR c1 IN c_rec_part LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.rec_qty = c1.rec_qty
                    ,a.rec_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, rec_qty, rec_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.rec_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
        
   FOR c1 IN c_used_part LOOP
   
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.issue_qty = c1.iss_qty
                    ,a.issue_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, issue_qty, issue_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.iss_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   FOR c1 IN (SELECT 
                        a.comp_no
                        ,a.wcode_id
                        ,to_char(sysdate,'yyyymm') AS yyyymm
                        ,a.part_id
                        ,a.part_grade
                        ,a.stock_qty
                        ,nvl(a.unit_price,0) AS unit_price
                        ,a.stock_qty * nvl(a.unit_price,0) AS tot_price
                    FROM taptstock a
                    WHERE 1=1
                        AND a.comp_no = v_comp_no
                        AND a.stock_qty > 0) 
   LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.result_tot = c1.stock_qty
                    ,a.result_unit_price = c1.unit_price
                    ,a.result_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, result_tot, result_unit_price, result_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.stock_qty,  c1.unit_price, c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   
   
   
   
    UPDATE TABATPGM set 
         exe_by = (SELECT user_id 
                        FROM tauser 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPTMONTHLYSTOCK'
    ;
    
    
    
    
END;
/

/** 2018-12-07 09:10 동국제약 반영 */
/** 2018-12-10 평화오일씰 반영 */
/** 2018-12-11 안국약품 반영 */
/** 2018-12-12 평화오일씰 반영 */
/** 2018-12-13 평화오일씰 반영 */
/** 2018-12-14 평화오일씰 반영 */
/** 2018-12-14 동국제약 반영 */
/** 2018-12-17 평화오일씰 반영 */
/** 2018-12-18 평화오일씰 반영 */
/** 2018-12-18 안국약품 반영 */
/** 2018-12-19 평화오일씰 반영 */
/** 2018-12-20 평화오일씰 반영 */
/** 2018-12-20 동국제약 반영 */

/** 2018-12-21 이근환 */
CREATE OR REPLACE PROCEDURE SP_KPI_MAKE_TAPTMONTHLYSTOCK(
      v_comp_no     IN varchar2      
     ,v_user_no      IN varchar2
) IS
    v_count                                 number(4); 
    
    -- exec SP_KPI_MAKE_TAPTMONTHLYSTOCK('100','ADMIN');
    
    CURSOR c_base IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,to_char(sysdate,'yyyymm') AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,a.result_tot AS base_qty
                ,a.result_unit_price AS base_unit_price
                ,a.result_tot_price AS base_tot_price
            FROM TAPTMONTHLYSTOCK a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.yyyymm = to_char(add_months(sysdate,-1),'yyyymm')  -- 한달전
            ;
            
    CURSOR c_rec_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.rec_date,1,6) AS yyyymm
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
                AND a.comp_no = v_comp_no
                AND a.ptrec_type = 'PRREC'
                AND a.rec_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.rec_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.rec_date,1,6),a.part_id,a.part_grade   
            ;
     
    CURSOR c_used_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.iss_date,1,6) AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.iss_qty 
                        WHEN a.ptiss_mode = 'R' THEN a.iss_qty * -1
                        END) AS iss_qty
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.tot_price 
                        WHEN a.ptiss_mode = 'R' THEN a.tot_price * -1
                        END) AS tot_price
            FROM taptisshist a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.ptiss_type = 'WOISS'
                AND a.iss_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.iss_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.iss_date,1,6),a.part_id,a.part_grade   
            ;
            
       
    
        
BEGIN

   UPDATE taptstock a set 
        a.unit_price = (SELECT last_price FROM taparts
                              WHERE comp_no = a.comp_no
                              AND part_id = a.part_id)
   WHERE a.comp_no = v_comp_no
   ;

   UPDATE taptmonthlystock a set 
       a.rec_qty = 0
       ,a.rec_tot_price = 0
       ,a.issue_qty = 0
       ,a.issue_tot_price = 0
   WHERE 1=1
       AND a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm') 
       AND a.yyyymm <= to_char(sysdate,'yyyymm')
   ;
   
   UPDATE taptmonthlystock a set 
       a.base_qty = 0
       ,a.base_unit_price = 0
       ,a.base_tot_price = 0
       ,a.result_tot = 0
       ,a.result_unit_price = 0
       ,a.result_tot_price = 0
   WHERE 1=1
       AND a.yyyymm = to_char(sysdate,'yyyymm')
   ;


    FOR c1 IN c_base LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.base_qty = c1.base_qty
                    ,a.base_unit_price = c1.base_unit_price
                    ,a.base_tot_price = c1.base_tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, base_qty, base_unit_price, base_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.base_qty,  c1.base_unit_price, c1.base_tot_price
           );
       END IF;
       
   END LOOP;
    
   FOR c1 IN c_rec_part LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.rec_qty = c1.rec_qty
                    ,a.rec_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, rec_qty, rec_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.rec_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
        
   FOR c1 IN c_used_part LOOP
   
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.issue_qty = c1.iss_qty
                    ,a.issue_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, issue_qty, issue_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.iss_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   FOR c1 IN (SELECT 
                        a.comp_no
                        ,a.wcode_id
                        ,to_char(sysdate,'yyyymm') AS yyyymm
                        ,a.part_id
                        ,a.part_grade
                        ,a.stock_qty
                        ,nvl(a.unit_price,0) AS unit_price
                        ,a.stock_qty * nvl(a.unit_price,0) AS tot_price
                    FROM taptstock a
                    WHERE 1=1
                        AND a.comp_no = v_comp_no
                        AND a.stock_qty > 0
                        AND EXISTS(SELECT 1 FROM tawarehouse 
                                           WHERE comp_no = a.comp_no 
                                           AND wcode_id = a.wcode_id
                                           AND wh_type = 'DREAM'
                                           AND wh_categ = 'PART')
                        AND EXISTS(SELECT 1 FROM taparts
                                           WHERE comp_no = a.comp_no
                                           AND part_id = a.part_id
                                           AND part_categ = 'SPPT'
                                           AND is_stock_control = 'Y')) 
   LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.result_tot = c1.stock_qty
                    ,a.result_unit_price = c1.unit_price
                    ,a.result_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, result_tot, result_unit_price, result_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.stock_qty,  c1.unit_price, c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   
   
   
   
    UPDATE TABATPGM set 
         exe_by = (SELECT user_id 
                        FROM tauser 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPTMONTHLYSTOCK'
    ;
    
    
    
    
END;
/

/** 2018-12-21 평화오일씰 반영 */
/** 2018-12-26 평화오일씰 반영 */
/** 2018-12-27 안국약품 반영 */
/** 2018-12-27 평화오일씰 반영 */
/** 2019-01-02 본사Dream 반영 */
/** 2019-01-04 평화오일씰 반영 */
/** 2019-01-08 안국약품 반영 */
/** 2019-01-16 동국제약 반영 */

/** 2019-01-17 양소영 */
UPDATE TAWORKORDER SET labor_amt = tot_amt;
UPDATE TAWORKORDER x SET x.part_amt = (SELECT NVL(SUM(tot_price),0) FROM TAWOPARTS WHERE comp_no = x.comp_no AND wkor_id = x.wkor_id )            
						,x.tot_amt = ( (SELECT NVL(SUM(tot_price),0) FROM TAWOPARTS WHERE comp_no = x.comp_no AND wkor_id = x.wkor_id) + NVL(labor_amt,0) );
UPDATE TAWORKORDER x SET x.tot_amt = (NVL(x.labor_amt,0) + NVL(part_amt,0));						

/** 2019-01-31 평화오일씰 반영 */
/** 2019-02-11 본사Dream 반영 */
/** 2019-02-12 안국약품 반영 */
/** 2019-03-04 안국약품 반영 */
/** 2019-03-07 동국제약 반영 */
/** 2019-03-13 현대일렉트릭 반영 */
/** 2019-03-26 오뚜기 반영 */
/** 2019-03-26 14:30 오뚜기 반영 */
/** 2019-03-26 안국약품 반영 */
/** 2019-03-27 로얄캐닌 반영 */
/** 2019-03-28 평화오일씰 반영 */
/** 2019-04-08 평화오일씰 반영 */
/** 2019-04-16 오뚜기 반영 */
/** 2019-04-18 현대일렉트릭 반영 */
/** 2019-04-26 현대일렉트릭 반영 */
/** 2019-05-02 현대일렉트릭 반영 */
/** 2019-05-12 백광산업 반영 */
/** 2019-05-13 평화오일씰 반영 */
/** 2019-05-16 현대일렉트릭 반영 */
/** 2019-05-17 현대일렉트릭 반영 */
/** 2019-05-21 안국약품 반영 */


/** 2019-05-14 이지수 */
UPDATE TAEQASSLIST SET EQASSLIST_TYPE = 'LT';

/** 2019-05-22 오뚜기 본사 반영 */
/** 2019-05-22 현대일렉트릭 반영 */

/** 2019-05-21 이지수 */
insert into TAUGPGPGAU
select sqaugpgpgau_id.nextval, x.usrgrp_id, (select pgpage_id  from TAPGPAGE  where c_page_id = (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail')), x.comp_no
from TAUGPGPGAU x
where x.pgpage_id in (select pgpage_id  from TAPGPAGE  where c_page_id = (select page_id from TAPAGE where file_name = 'workPmiPointDetail'));
insert into TAUGPGPGAU
select sqaugpgpgau_id.nextval, x.usrgrp_id, (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail')), x.comp_no
from TAUGPGPGAU x
where x.pgpage_id in (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointDetail'));
insert into TAUGPGPGAU
select sqaugpgpgau_id.nextval, x.usrgrp_id, (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList')), x.comp_no
from TAUGPGPGAU x
where x.pgpage_id in (select pgpage_id  from TAPGPAGE  where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibList'));

insert into TAUGPGAU
select sqaugpgau_id.nextval, x.usrgrp_id, (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail'), x.comp_no
from TAUGPGAU x
where x.page_id in (select page_id from TAPAGE where file_name = 'workPmiPointDetail');
insert into TAUGPGAU
select sqaugpgau_id.nextval, x.usrgrp_id, (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList'), x.comp_no
from TAUGPGAU x
where x.page_id in (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibList');
insert into TAUGPGAU
select sqaugpgau_id.nextval, x.usrgrp_id, (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibDetail'), x.comp_no
from TAUGPGAU x
where x.page_id in (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibDetail');

insert into TAUGPGBTNAU
select SQAUGPGBTN_ID.nextval, x.usrgrp_id, (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail' and button_id = (select button_id from TAPGBTN where pgbtn_id = x.pgbtn_id))), x.comp_no
from TAUGPGBTNAU x
where x.pgbtn_id in (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiPointDetail'));
insert into TAUGPGBTNAU
select SQAUGPGBTN_ID.nextval, x.usrgrp_id,(select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList' and button_id = (select button_id from TAPGBTN where pgbtn_id = x.pgbtn_id))), x.comp_no
from TAUGPGBTNAU x
where x.pgbtn_id in (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibList'));
insert into TAUGPGBTNAU
select SQAUGPGBTN_ID.nextval, x.usrgrp_id,(select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibDetail' and button_id = (select button_id from TAPGBTN where pgbtn_id = x.pgbtn_id))), x.comp_no
from TAUGPGBTNAU x
where x.pgbtn_id in (select pgbtn_id from TAPGBTN where page_id = (select page_id from TAPAGE where file_name = 'workPmiRInsPointDocLibDetail')); 

/** 2019-05-23 평화오일씰 반영 */
/** 2019-05-23 현대일렉트릭 반영 */
/** 2019-05-24 오뚜기 본사 반영 */
/** 2019-05-27 표준 Dream 반영 */

/** 2019-05-27 이근환 */
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'APP10','결재대기시 대기자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'APP20','결재완료시 기안자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'REQ10','작업요청시 접수자(부)에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'RQC10','작업불가,접수시 요청자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'WRK10','작업완료시 요청자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'PRI10','구매신청시 발주담당자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'ISS10','출고처리시 수령자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'QNA20','QNA완료시 요청자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'ERR10','오류발생시 담당자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'USR10','사용자계정 등록시 사용자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'USR20','사용자 비밀번호 리셋시 사용자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'MAL01','메일링주기설정에서 전송한 내용을 사용자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'PPR10','현장구매신청 시 구매 담당자에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'WRK20','작업완료시 작업부서 전원에게 전송','Y','N','Y');
INSERT INTO TAMESSAGECATEG(messagecateg_id,message_object_type,description,mail_use,sms_use,is_use) VALUES (sqamessagecateg_id.NEXTVAL,'RQC20','접수자 변경 시 변경된 접수자에게 전송','Y','N','Y');

INSERT INTO TAMSGCOMPSET(comp_no, msgcompset_id, message_object_type,mail_use,sms_use,is_use)
SELECT b.comp_no, sqamsgcompset_id.NEXTVAL,a.message_object_type,a.mail_use,a.sms_use,a.is_use
FROM TAMESSAGECATEG a, TACOMP b;

INSERT INTO TAMSGEMPSET(comp_no,msgempset_id,message_object_type,mail_use,sms_use,is_use,emp_id)
SELECT a.comp_no, sqamsgempset_id.NEXTVAL, a.message_object_type,a.mail_use,a.sms_use,a.is_use,b.emp_id
FROM TAMSGCOMPSET a INNER JOIN TAEMP b
ON a.comp_no = b.comp_no;


/** 2019-05-27 오뚜기 본사 반영 */

/** 2019-05-29 현대일렉트릭 반영 */

/** 2019-05-30 현대일렉트릭 반영 */

/** 2019-06-03 현대일렉트릭 반영 */
/** 2019-06-04 표준Dream 반영 */

/** 2019-06-05 현대일렉트릭 반영 */

/** 2019-06-07 현대일렉트릭 반영 */
/** 2019-06-10 오뚜기 본사 반영 */

/** 2019-06-11 현대일렉트릭 반영 */
/** 2019-06-12 평화오일씰 반영 */

/** 2019-06-14 김남현 */
insert into TAINITALARMEMPSET(comp_no, initalarmempset_id, emp_id, init_alarm_list, is_notice, is_use, remark)
select b.comp_no,sqainitalarmempset_id.nextval,  b.emp_id, a.cdsysd_no, a.is_use, a.is_use, a.remark
from tacdsysd a inner join taemp b on a.list_type = 'INIT_ALARM_LIST' and b.is_join = 'Y'
where 1=1;

/** 2019-06-19 현대일렉트릭 반영 */
/** 2019-06-19 오뚜기 본사 반영 */
/** 2019-06-25 현대일렉트릭 반영 */
/** 2019-07-02 오뚜기 본사 반영 */
/** 2019-07-03 현대일렉트릭 반영 */
/** 2019-07-11 평화오일씰 반영 */
/** 2019-07-12 백광 반영 */


  /** 2019-07-23 김정우 */
/**
 * TACONFIG 의 값이 각 사이트에 맞게 아래 형식으로 변경되어야 합니다.
 * TACONFIG.ANT_APK_URL = http://ip:port/path/android_apk/ant/
 * TACONFIG.BEE_APK_URL = http://ip:port/path/android_apk/bee/
 * TACONFIG.CRICKET_APK_URL = http://ip:port/path/android_apk/cricket/
 * 
 */
/** 2019-07-24 로얄캐닌 반영 */
/** 2019-08-12 오뚜기(OEMS) 반영 */

/* 310 김남현  */
UPDATE TACDUSRM 
   SET description = (SELECT key_name FROM TALANG WHERE key_no = 'docType' AND lang = 'ko')  
 WHERE dir_type = 'DOC_CATEG';

/** 2019-08-21 연우 반영 */
 
 /** 298 양소영 */
CREATE OR REPLACE PROCEDURE update_SEQ( SEQ_NAME1 IN VARCHAR2, SEQ_NAME2 IN VARCHAR2 )
IS
    INCVAL NUMBER;
    L_VAL NUMBER;  
BEGIN
	SELECT
		(CASE WHEN (SELECT last_number FROM user_sequences WHERE sequence_name=SEQ_NAME2) > (SELECT last_number FROM user_sequences WHERE sequence_name=SEQ_NAME1)
	        THEN ( (SELECT last_number FROM user_sequences WHERE sequence_name=SEQ_NAME2) - (SELECT last_number FROM user_sequences WHERE sequence_name=SEQ_NAME1) ) 
	        ELSE (1)
	     END )  INTO INCVAL
	FROM DUAL;
	
	EXECUTE IMMEDIATE 'ALTER SEQUENCE ' || SEQ_NAME1 || ' INCREMENT BY ' || INCVAL;
	
	EXECUTE IMMEDIATE 'SELECT ' || SEQ_NAME1 || '.NEXTVAL FROM DUAL' INTO L_VAL;
	EXECUTE IMMEDIATE 'SELECT ' || SEQ_NAME1 || '.NEXTVAL FROM DUAL' INTO L_VAL;
	
	EXECUTE IMMEDIATE 'ALTER SEQUENCE ' || SEQ_NAME1 || ' INCREMENT BY 1';
END;
/

exec update_SEQ('SQAPTWHBINNO_ID', 'SQAWHUSER_ID');

DROP PROCEDURE update_SEQ;


/** 2019-08-26 동국제약 반영 */
/** 2019-08-26 연우 반영 */

/** 308 + 은아 */
update TAPLANT set plant_id = SQAPLANT_ID.NEXTVAL;

/** 2019-09-09 평화오일씰 반영 */

/** 300 + ghlee */
/**

UPDATE TAPMINSSCHED a SET a.pmsched_status=(SELECT pmsched_status FROM TAPMINSLIST WHERE comp_no=a.comp_no AND pminssched_id=a.pminssched_id)
WHERE EXISTS(SELECT 1 FROM TAPMINSLIST WHERE comp_no=a.comp_no AND pminssched_id=a.pminssched_id AND pmsched_status!=a.pmsched_status);

UPDATE TAPMINSDSCHED a SET a.pmsched_status=(SELECT pmsched_status FROM TAPMINSDLIST WHERE comp_no=a.comp_no AND pminsdsched_id=a.pminsdsched_id)
WHERE EXISTS(SELECT 1 FROM TAPMINSDLIST WHERE comp_no=a.comp_no AND pminsdsched_id=a.pminsdsched_id AND pmsched_status!=a.pmsched_status);

UPDATE TAPMSCHED a SET a.pmsched_status=(SELECT pmsched_status FROM TAWORKORDER WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id)
WHERE EXISTS(SELECT 1 FROM TAWORKORDER WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id AND pmsched_status!=a.pmsched_status);


UPDATE TAPMPOINT a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pm_id FROM TAPMLST WHERE comp_no=a.comp_no AND pm_id=a.pm_id AND is_deleted='Y')
AND is_deleted='N';

UPDATE TAPMPART a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pm_id FROM TAPMLST WHERE comp_no=a.comp_no AND pm_id=a.pm_id AND is_deleted='Y')
AND is_deleted='N';

UPDATE TAPMEQUIP a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pm_id FROM TAPMLST WHERE comp_no=a.comp_no AND pm_id=a.pm_id AND is_deleted='Y')
AND is_deleted='N';

UPDATE TAPMINSSCHED a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pmequip_id FROM TAPMEQUIP WHERE comp_no=a.comp_no AND pmequip_id=a.pmequip_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE TAPMINSLIST a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pminssched_id FROM TAPMINSSCHED WHERE comp_no=a.comp_no AND pminssched_id=a.pminssched_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE TAPMINSDSCHED a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pmequip_id FROM TAPMEQUIP WHERE comp_no=a.comp_no AND pmequip_id=a.pmequip_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE TAPMINSDLIST a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pminsdsched_id FROM TAPMINSDSCHED WHERE comp_no=a.comp_no AND pminsdsched_id=a.pminsdsched_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE TAPMSCHED a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pmequip_id FROM TAPMEQUIP WHERE comp_no=a.comp_no AND pmequip_id=a.pmequip_id AND is_deleted='Y')
AND is_deleted='N'
AND pmsched_status != 'C';

UPDATE TAWOPLAN a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pmsched_id FROM TAPMSCHED WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id AND is_deleted='Y')
AND is_deleted='N'
AND wo_status != 'C';

UPDATE TAWORKORDER a set is_deleted='Y', delete_by=(SELECT emp_id FROM TAEMP WHERE comp_no=a.comp_no AND emp_no='ADMIN'), delete_time=to_char(sysdate,'yyyymmddhh24miss')
WHERE EXISTS(SELECT pmsched_id FROM TAPMSCHED WHERE comp_no=a.comp_no AND pmsched_id=a.pmsched_id AND is_deleted='Y')
AND is_deleted='N'
AND wo_status != 'C';

UPDATE TAPTEMGISSLIST a set ptemg_task_status='W', wkor_id=NULL
WHERE EXISTS(SELECT wkor_id FROM TAWORKORDER WHERE comp_no=a.comp_no AND wkor_id=a.wkor_id AND is_deleted='Y');
*/

/** 2019-10-01 안국약품 반영 */
/** 446 소영 */
UPDATE TAWOREQ SET req_plant = plant;

/** 2019-10-16 현대일렉트릭 반영 */

/** 445 + euna0207 */

/** 작업자 : 김은아 
 * 패치시 불러주세요 ........... !!!
 * 0. copy table 생성문
 * 1. 설비부위의 수정시간, 생성시간의 특수문자 및 공백제거하는 일괄처리문 
 * 2. upd_date와 cre_date가 14자리가 아닐 경우, 뒤에 00 붙여주는 일괄처리문
 * 3. 설비부위와, 설비부위유닛 페이지에서 column_id가 UPDDATE, CREDATE인 그리드컬럼들의 TYPE을 'dhxCalendar'타입으로 변경하는 쿼리 
 * 
 *  사이트 패치 시, 기존의 설비부위 데이타가 UPD_DATE, CRE_DATE가 어떤 형식으로 들어가있는지 확인 후 작업해주시기 바랍니다. */

/**

CREATE TABLE copy_table_asmb AS SELECT * FROM TAEQASMB;


UPDATE TAEQASMB
SET upd_date = TRIM(REGEXP_REPLACE(upd_date,'[^[:digit:]]'))
WHERE upd_date IS NOT NULL;

UPDATE TAEQASMB
SET cre_date = TRIM(REGEXP_REPLACE(cre_date,'[^[:digit:]]'))
WHERE cre_date IS NOT NULL;


UPDATE TAEQASMB
SET upd_date = upd_date||'00'
WHERE LENGTH(upd_date) != 14
AND upd_date IS NOT NULL;


UPDATE TAEQASMB
SET cre_date= cre_date||'00'
WHERE LENGTH(cre_date)!= 14
AND cre_date IS NOT NULL;

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
UPDATE taptprlist a set a.wcode_id=(SELECT wcode_id FROM tadept WHERE comp_no=a.comp_no AND dept_id=a.dept_id)
WHERE a.wcode_id IS NULL;

UPDATE taptpoitem a 
set a.gr_on_qty = (SELECT sum(rec_qty) FROM taptprreclist WHERE comp_no=a.comp_no AND ptpoitem_id=a.ptpoitem_id);
UPDATE taptpritem a 
set a.gr_on_qty = (SELECT sum(gr_on_qty) FROM taptpoitem WHERE comp_no=a.comp_no AND ptpritem_id=a.ptpritem_id)
, a.po_on_qty = (SELECT sum(po_qty) FROM taptpoitem WHERE comp_no=a.comp_no AND ptpritem_id=a.ptpritem_id);
UPDATE taptpnlist a 
set a.gr_on_qty = (SELECT sum(gr_on_qty) FROM taptpritem WHERE comp_no=a.comp_no AND ptpnlist_id=a.ptpnlist_id)
, a.po_on_qty = (SELECT sum(po_on_qty) FROM taptpritem WHERE comp_no=a.comp_no AND ptpnlist_id=a.ptpnlist_id)
, a.pr_on_qty = (SELECT sum(rec_qty) FROM taptpritem WHERE comp_no=a.comp_no AND ptpnlist_id=a.ptpnlist_id);
*/

/*532 김남현*/
UPDATE TAEQHISTORY SET eqhist_gen_type = 'WORKORDER' where wkor_id is not null;
UPDATE TAEQHISTORY SET eqhist_gen_type = 'MANUAL' where wkor_id is null;

/** 2019-10-30 평화오일씰 반영 */
/** 2019-11-05 안국약품 반영 */

/** 695 이근환 */
INSERT INTO taconfig(comp_no,config_id,NAME,value$,description,is_system)
VALUES((SELECT MIN(comp_no) FROM tacomp WHERE is_use='Y'),sqaconfig_id.NEXTVAL,'SERVER_DATABASE','Oracle','서버 데이타 베이스 종류','Y');


/** 670 이근환 */
/** 위치 별 MTTR/MTBF 지표를 사용하는 사이트의 경우 해당 쿼리를 적용해야 함
 *  TALNWRKLIST의 eqloc_id는 더이상 사용하지 않는 컬럼이고 TAEQLOC의 lnwrklist_id를 사용하기 때문.
 *  쿼리에 오류가 있을 경우 셋팅이 잘 못 되어있는 경우이므로 TALNWRKLIST의 eqloc_id 데이터를 직접 보고 수정해 주어야 함.
update TAEQLOC a set a.lnwrklist_id=(select lnwrklist_id from TALNWRKLIST where comp_no=a.comp_no and eqloc_id=a.eqloc_id)
where a.lnwrklist_id is null;
*/

/** 2019-11-22 동국제약 반영 */
/** 2019-11-25 연우 반영 */
/** 2019-11-26 평화오일씰 반영 */
/** 2019-11-27 연우 반영 */
/** 2019-12-04 안국약품 반영 */

/** youjngjoo38_607 + 김영주 입력 
* TAWOPOINT 데이터 생성시 
* 최조 점검결과 설정값(pm_point_rslt_status)에 TACDSYSD.is_use = Y 인 데이터가 들어가도록 변경
* 패치 시 불러주세요 */

/**
CREATE OR REPLACE PROCEDURE DREAM_DEV.SP_PM_MAKE_TO_WO(
      v_comp_no     IN varchar2      
     ,v_pmsched_id IN number
) IS
    v_count                         number(4); 
    v_wo_res_before           number(4);  
    v_wo_id                        number(18);
    v_wo_no                      number(18);  
    v_sched_date               varchar2(8);
    v_is_work                    varchar2(20);
    v_wo_type                    varchar2(20);
    v_is_work_plan             varchar2(20);
    v_wo_status                varchar2(20);
    v_pmc_type                varchar2(20);
    v_pmcalibstdtp_id        number(18);
    v_next_sch_date          varchar2(8);
    
    CURSOR c_pm_sched_id IS
        SELECT 
             b.pm_id
            ,(SELECT aa.wcode_id FROM TADEPT aa WHERE b.comp_no = aa.comp_no AND b.dept_id = aa.dept_id) AS wcode_id
            ,b.wo_type
            ,b.pm_type
            ,A.pmsched_id
            ,A.sched_date
            ,A.pmequip_id
            ,A.equip_id
            ,c.eqasmb_id
            ,b.CYCLE
            ,b.period_type
            ,REPLACE(ee.tolerance,'N/A','') tolerance 
        FROM TAPMSCHED A INNER JOIN  TAPMLST b ON A.pm_id = b.pm_id AND  A.comp_no = b.comp_no
                 INNER JOIN TAPMEQUIP c ON  A.comp_no = c.comp_no AND A.pmequip_id = c.pmequip_id  
                 INNER JOIN TAEQUIPMENT d ON c.equip_id = d.equip_id 
                 LEFT OUTER JOIN TAEQTOOL ee ON d.equip_id = ee.equip_id 
        WHERE 1=1
            AND c.is_deleted = 'N'
            AND c.is_active = 'Y'
            AND b.is_active = 'Y'
            AND A.pmsched_status = 'P' -- not release work order 
            AND A.comp_no = v_comp_no
            AND A.pmsched_id = v_pmsched_id
        ;
     
        
BEGIN

    --  Y인경우 작업계획(TAWOPLAN)으로 발행
    --  N인경우 작업계획(TAWOPLAN), 작업실적(TAWORKORDER) 2가지 모두 발행 
    --  작업계획을 자동 확정되고 사용자는 작업실적을 사용
    SELECT COUNT(*) INTO v_count 
    FROM TACONFIG
    WHERE comp_no = v_comp_no
         AND NAME = 'IS_WORK_PLAN'
    ;
        
    IF v_count > 0 THEN
        SELECT NVL(value$,'N')
        INTO v_is_work_plan 
        FROM TACONFIG
        WHERE comp_no = v_comp_no
            AND NAME = 'IS_WORK_PLAN'
        ;
    ELSE
        v_is_work_plan := 'N';
    END IF;

    IF v_is_work_plan = 'N' THEN
      --  v_wo_status := 'PRW'; -- 작업결과작성중 (2018-09-06 노정현 : 작업결과 작성중으로 만들어져서 PM 재스캐줄에 지워지지 않음, PM 재스캐줄은 PMW는 삭제하지 않음)
         v_wo_status := 'P';
    ELSE
        v_wo_status := 'P';  -- 작업대기
    END IF;
        
    FOR c1 IN c_pm_sched_id LOOP
        
                 SELECT sqawkor_id.NEXTVAL 
                 INTO v_wo_id
                 FROM dual;
                 
                 v_wo_no := TO_CHAR(v_wo_id);
                 
                 SELECT A.wo_type
                 INTO v_wo_type
                 FROM TAPMLST A
                 WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.pm_id = c1.pm_id;
                    
                   /*
                   INSERT INTO TAWOPLAN(
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
                    SELECT 
                         v_comp_no  AS comp_no
                        ,v_wo_id      AS wkor_id
                        ,v_wo_no      AS wo_no
                        ,A.description  AS description
                        ,v_wo_status   AS wo_status
                        ,A.wo_type      AS wo_type  -- preventmaint
                        ,A.pm_type      AS pm_type
                        ,'PMPLAN'        AS wo_gen_type
                        ,c1.sched_date  AS start_date
                        ,c1.sched_date  AS end_date
                        ,c1.sched_date  AS wkor_date
                        ,A.dept_id         AS dept_id
                        ,A.emp_id        AS emp_id
                        ,A.REMARK         AS perform
                        ,c1.pm_id         AS pm_id
                        ,c1.pmsched_id  AS pmsched_id
                        ,TO_CHAR(SYSDATE,'yyyymmdd') AS upd_date
                        ,A.emp_id          AS upd_by
                        ,A.wkctr_id
                        ,a.plant
                    FROM TAPMLST A
                    WHERE 1=1
                        AND A.comp_no = v_comp_no
                        AND A.pm_id = c1.pm_id
                    ;
                    */
                     
                  INSERT INTO TAWORKORDER(
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
                SELECT 
                     v_comp_no  AS comp_no
                    ,v_wo_id      AS wkor_id
                    ,v_wo_no      AS wo_no
                    ,A.description  AS description
                    ,v_wo_status    AS wo_status -- stand by 
                    ,A.wo_type      AS wo_type  -- preventmaint
                    ,A.pm_type      AS pm_type
                    ,'PMPLAN'        AS wo_gen_type
                    ,c1.sched_date  AS start_date
                    ,c1.sched_date  AS end_date
                    ,c1.sched_date  AS wkor_date
                    ,A.dept_id         AS dept_id
                    ,A.emp_id        AS emp_id
                    ,A.REMARK         AS perform
                    ,c1.pm_id         AS pm_id
                    ,c1.pmsched_id  AS pmsched_id
                    ,TO_CHAR(SYSDATE,'yyyymmdd') AS upd_date
                    ,A.emp_id          AS upd_by
                    ,A.wkctr_id
                    ,A.plant
                    ,c1.eqasmb_id
                FROM TAPMLST A
                WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.pm_id = c1.pm_id
                ;
                
                IF v_wo_type = 'PMC' THEN
                
                    SELECT    
                            CASE 
                                 WHEN c1.period_type = 'D' THEN TO_CHAR(TO_DATE(c1.sched_date,'yyyymmdd') + (c1.CYCLE * 1 ) -1 ,'yyyymmdd')
                                 WHEN c1.period_type = 'W' THEN TO_CHAR(TO_DATE(c1.sched_date,'yyyymmdd') + (7 * c1.CYCLE * 1 ) -1 ,'yyyymmdd') 
                                 WHEN c1.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(c1.sched_date,'yyyymmdd'),c1.CYCLE * 1) -1 ,'yyyymmdd') 
                                 WHEN c1.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(c1.sched_date,'yyyymmdd'),12 * c1.CYCLE * 1) -1 ,'yyyymmdd')
                            END AS next_date
                        INTO v_next_sch_date
                        FROM dual
                        ; 
                    INSERT INTO TAWOCALIB (comp_no , wkor_id , calib_type, period_type, CYCLE, next_calib_date) VALUES (v_comp_no,v_wo_id,'R', c1.period_type, c1.CYCLE, v_next_sch_date);
                    -- insert into TAWOCALIBGNLVALUE
                    SELECT COUNT(*) INTO v_count 
                    FROM TAEQTOOL 
                    WHERE comp_no = v_comp_no 
                        AND equip_id = c1.equip_id
                    ;
                    
                    IF v_count > 0 THEN
                        SELECT NVL(MAX(pmc_type),'XXX'), NVL(MAX(pmcalibstdtp_id),0)
                        INTO v_pmc_type, v_pmcalibstdtp_id 
                        FROM TAEQTOOL 
                        WHERE comp_no = v_comp_no 
                            AND equip_id = c1.equip_id
                        ; 
                    ELSE
                        v_pmc_type := 'XXXX';
                        v_pmcalibstdtp_id := 0; 
                    END IF;
                    
                    IF v_pmcalibstdtp_id = 0 THEN  -- 동일한 계측기 타입을 찾아서 하나만 넣어줌.
                    
                        INSERT INTO TAWOCALIBGNLVALUE(
                            comp_no
                            ,wocalibgnlvalue_id
                            ,wkor_id
                            ,calib_point_type
                            ,calib_point
                            ,allow_value
                            ,asf_std_value
                            ,ord_no
                            ,REMARK
                        )SELECT comp_no
                              ,sqawocalibgnlvalue_id.NEXTVAL
                              ,v_wo_id
                              ,calib_point_type
                              ,calib_point
                              ,NVL(c1.tolerance, allow_value)
                              ,asf_std_value
                              ,ord_no
                              ,REMARK
                        FROM TAPMCALIBSTDVALUE
                        WHERE comp_no = v_comp_no
                            AND pmcalibstdtp_id = (
                                                                SELECT pmcalibstdtp_id
                                                                FROM TAPMCALIBSTDTP 
                                                                WHERE comp_no = v_comp_no 
                                                                    AND pmc_type = v_pmc_type 
                                                                    AND ROWNUM = 1
                                                              ) ;
                                                                              
                    ELSE  -- 해당 표준교정항목값을 넣어 줌
                        INSERT INTO TAWOCALIBGNLVALUE(
                            comp_no
                            ,wocalibgnlvalue_id
                            ,wkor_id
                            ,calib_point_type
                            ,calib_point
                            ,allow_value
                            ,asf_std_value
                            ,ord_no
                            ,REMARK
                        )SELECT comp_no
                              ,sqawocalibgnlvalue_id.NEXTVAL
                              ,v_wo_id
                              ,calib_point_type
                              ,calib_point
                              ,allow_value
                              ,asf_std_value
                              ,ord_no
                              ,REMARK
                        FROM TAPMCALIBSTDVALUE
                        WHERE comp_no = v_comp_no
                            AND pmcalibstdtp_id = v_pmcalibstdtp_id ;
                                                                                  
                    END IF;
                    
                END IF;
                
                -- tawoequip 
                INSERT INTO TAWOEQUIP(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description)
                SELECT comp_no, sqawoequip_id.NEXTVAL,  v_wo_id,  equip_id, eqctg_id, description 
                FROM TAEQUIPMENT
                WHERE comp_no = v_comp_no
                    AND equip_id = c1.equip_id
                ;
                    
                -- parts
                INSERT INTO TAWOPARTS(
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
                SELECT v_comp_no AS comp_no
                    ,sqawopart_id.NEXTVAL AS wopart_id
                    ,v_wo_id     AS wkor_id
                    ,c1.wcode_id AS wcode_id
                    ,A.part_id     AS part_id
                    ,'A'              AS part_grade
                    ,A.use_qty   AS use_qty
                    ,b.last_price  AS unit_price
                    ,A.use_qty * b.last_price AS tot_price
                FROM TAPMPART A
                    ,TAPARTS b
                WHERE A.comp_no = b.comp_no
                    AND A.part_id = b.part_id
                    AND A.comp_no = v_comp_no
                    AND A.pm_id =  c1.pm_id
                ;
   
                -- inspection point
                INSERT INTO TAWOPOINT(
                    comp_no
                    ,wopoint_id
                    ,wkor_id
                    ,pm_point_id
                    ,pm_point_rslt_status
                    ,pm_point_rep_status
                    ,is_saved
                )
                SELECT v_comp_no AS comp_no
                    ,sqawopoint_id.NEXTVAL AS wopoint_id
                    ,v_wo_id     AS wkor_id
                    ,A.pm_point_id AS pm_point_id
                    ,(SELECT cdsysd_no FROM 
                            (SELECT * FROM TACDSYSD
                            WHERE list_type='PM_POINT_RSLT_STATUS'
                            AND is_use = 'Y' -- (2019-12-11 김영주 : 최조 점검결과 설정값(pm_point_rslt_status)에 TACDSYSD.is_use = Y 인 데이터가 들어가도록 변경) 
                            ORDER BY ord_no)
                            WHERE ROWNUM=1)               AS pm_point_rslt_status
                    ,'GD'               AS pm_point_rep_status
                    ,'N'                  AS is_saved
                FROM TAPMPOINT A
                WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.pm_id =  c1.pm_id
                    AND A.is_active = 'Y'
                ;
                    
                    /*
                 -- stardard point insert 
                INSERT INTO TAWOSTPOINT(
                     comp_no
                    ,wostpoint_id
                    ,wkor_id
                    ,stwrk_point_id
                    ,st_point_rslt_status
                )
                SELECT v_comp_no AS comp_no
                    ,sqawostpoint_id.NEXTVAL AS wostpoint_id
                    ,v_wo_id     AS wkor_id
                    ,A.stwrk_point_id AS stwrk_point_id
                    ,'GD'               AS st_point_rslt_status
                FROM TASTWRKPOINT A, TASTWRKLST b
                WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.comp_no = b.comp_no
                    AND A.stwrk_id = b.stwrk_id
                    AND A.is_active = 'Y'
                    AND b.pm_type = c1.pm_type
                    AND b.is_active = 'Y'
                    AND b.is_force ='Y'
                ;
                */
                    
                -- update wo_no in TAPMSCHED
                UPDATE TAPMSCHED SET
                     wkor_id = v_wo_id
                    ,pmsched_status =  'S' 
                WHERE comp_no = v_comp_no
                    AND pmsched_id = c1.pmsched_id
                ;
                    
                SP_WOMAKE_4WP_BYONE(v_comp_no, v_wo_id);
            
            

        
    END LOOP;
    
    
END;
/
*/

/** 758 이근환 */
/**
 * 운영환경에 배포 시 TACDSYSD의 param1에 주소에 운영환경에 맞는 아이피, 포트를 넣어야 함
 * 
UPDATE tacdsysd set param1='http://localhost:8080/dream/report.do'
WHERE list_type='RPTFILE_TYPE'
AND cdsysd_no='RDX';
UPDATE tacdsysd set param1='http://202.133.21.180:9040/ubi4/reportViewer.jsp'
WHERE list_type='RPTFILE_TYPE'
AND cdsysd_no='UBI';
INSERT INTO tarptcplist(comp_no,rptcplist_id,rptlist_no,description,is_use,remark)
SELECT comp_no, sqarptcplist_id.NEXTVAL,'K001','설비대장','Y','설비관리 정보를 표시하는 출력물'
FROM tacomp
WHERE is_use='Y';
INSERT INTO tarptcpfile(comp_no,rptcpfile_id,rptcplist_id,rptlist_no,rptlist_name,rptfile_type,svr_addr,design_file,query_file,reversion_no,is_use)
SELECT comp_no,sqarptcpfile_id.NEXTVAL,rptcplist_id,rptlist_no,'maEqMstrDetail_v1.0','RDX',(SELECT param1 FROM tacdsysd WHERE list_type='RPTFILE_TYPE' and cdsysd_no='RDX'),'/dream/print/rd/maEqMstrDetail.mrd','/dream/print/rd/Oracle/maEqMstrDetail.sql','1.0','Y'
FROM tarptcplist;
INSERT INTO tarptcpfile(comp_no,rptcpfile_id,rptcplist_id,rptlist_no,rptlist_name,rptfile_type,svr_addr,design_file,query_file,reversion_no,is_use)
SELECT comp_no,sqarptcpfile_id.NEXTVAL,rptcplist_id,rptlist_no,'maEqMstrDetail_v1.0','UBI',(SELECT param1 FROM tacdsysd WHERE list_type='RPTFILE_TYPE' and cdsysd_no='UBI'),'/dream/print/ubiReport/maEqMstrDetail.jrf','/dream/print/ubiReport/Oracle/maEqMstrDetail.sql','1.0','Y'
FROM tarptcplist;
*/

/** 616 추가 */
UPDATE tapggridcol set column_id='ITEMNO'
WHERE pggrid_id=(SELECT pggrid_id FROM tapggrid WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='workPmiList'))
AND upper(column_id) = 'EQUIPNO';

/** 2019-12-17 표준 Dream 반영 */

/* 629 김남현 */
UPDATE TAWOEQUIP y SET 
   eqasmb_id = (SELECT x.eqasmb_id 
                  FROM TAWORKORDER x
                 WHERE x.comp_no = y.comp_no
                   AND x.wkor_id = y.wkor_id
                )
 WHERE 1=1 
;


/**
 *  TAWOEQUIP 테이블에 INSERT 시 eqasmb_id 의 값을 같이 넣어주도록 프로시져 수정
 * 	각 사이트 별 패치시 추가가 필요합니다. 
 * 	패치 전에 불러주세요.
 */

/**
CREATE OR REPLACE PROCEDURE DREAM_DEV.SP_PM_MAKE_TO_WO(
      v_comp_no     IN varchar2      
     ,v_pmsched_id IN number
) IS
    v_count                         number(4); 
    v_wo_res_before           number(4);  
    v_wo_id                        number(18);
    v_wo_no                      number(18);  
    v_sched_date               varchar2(8);
    v_is_work                    varchar2(20);
    v_wo_type                    varchar2(20);
    v_is_work_plan             varchar2(20);
    v_wo_status                varchar2(20);
    v_pmc_type                varchar2(20);
    v_pmcalibstdtp_id        number(18);
    v_next_sch_date          varchar2(8);
    
    CURSOR c_pm_sched_id IS
        SELECT 
             b.pm_id
            ,(SELECT aa.wcode_id FROM TADEPT aa WHERE b.comp_no = aa.comp_no AND b.dept_id = aa.dept_id) AS wcode_id
            ,b.wo_type
            ,b.pm_type
            ,A.pmsched_id
            ,A.sched_date
            ,A.pmequip_id
            ,A.equip_id
            ,c.eqasmb_id
            ,b.CYCLE
            ,b.period_type
            ,REPLACE(ee.tolerance,'N/A','') tolerance 
        FROM TAPMSCHED A INNER JOIN  TAPMLST b ON A.pm_id = b.pm_id AND  A.comp_no = b.comp_no
                 INNER JOIN TAPMEQUIP c ON  A.comp_no = c.comp_no AND A.pmequip_id = c.pmequip_id  
                 INNER JOIN TAEQUIPMENT d ON c.equip_id = d.equip_id 
                 LEFT OUTER JOIN TAEQTOOL ee ON d.equip_id = ee.equip_id 
        WHERE 1=1
            AND c.is_deleted = 'N'
            AND c.is_active = 'Y'
            AND b.is_active = 'Y'
            AND A.pmsched_status = 'P' -- not release work order 
            AND A.comp_no = v_comp_no
            AND A.pmsched_id = v_pmsched_id
        ;
     
        
BEGIN

    --  Y인경우 작업계획(TAWOPLAN)으로 발행
    --  N인경우 작업계획(TAWOPLAN), 작업실적(TAWORKORDER) 2가지 모두 발행 
    --  작업계획을 자동 확정되고 사용자는 작업실적을 사용
    SELECT COUNT(*) INTO v_count 
    FROM TACONFIG
    WHERE comp_no = v_comp_no
         AND NAME = 'IS_WORK_PLAN'
    ;
        
    IF v_count > 0 THEN
        SELECT NVL(value$,'N')
        INTO v_is_work_plan 
        FROM TACONFIG
        WHERE comp_no = v_comp_no
            AND NAME = 'IS_WORK_PLAN'
        ;
    ELSE
        v_is_work_plan := 'N';
    END IF;

    IF v_is_work_plan = 'N' THEN
      --  v_wo_status := 'PRW'; -- 작업결과작성중 (2018-09-06 노정현 : 작업결과 작성중으로 만들어져서 PM 재스캐줄에 지워지지 않음, PM 재스캐줄은 PMW는 삭제하지 않음)
         v_wo_status := 'P';
    ELSE
        v_wo_status := 'P';  -- 작업대기
    END IF;
        
    FOR c1 IN c_pm_sched_id LOOP
        
                 SELECT sqawkor_id.NEXTVAL 
                 INTO v_wo_id
                 FROM dual;
                 
                 v_wo_no := TO_CHAR(v_wo_id);
                 
                 SELECT A.wo_type
                 INTO v_wo_type
                 FROM TAPMLST A
                 WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.pm_id = c1.pm_id;
                    
                   /*
                   INSERT INTO TAWOPLAN(
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
                    SELECT 
                         v_comp_no  AS comp_no
                        ,v_wo_id      AS wkor_id
                        ,v_wo_no      AS wo_no
                        ,A.description  AS description
                        ,v_wo_status   AS wo_status
                        ,A.wo_type      AS wo_type  -- preventmaint
                        ,A.pm_type      AS pm_type
                        ,'PMPLAN'        AS wo_gen_type
                        ,c1.sched_date  AS start_date
                        ,c1.sched_date  AS end_date
                        ,c1.sched_date  AS wkor_date
                        ,A.dept_id         AS dept_id
                        ,A.emp_id        AS emp_id
                        ,A.REMARK         AS perform
                        ,c1.pm_id         AS pm_id
                        ,c1.pmsched_id  AS pmsched_id
                        ,TO_CHAR(SYSDATE,'yyyymmdd') AS upd_date
                        ,A.emp_id          AS upd_by
                        ,A.wkctr_id
                        ,a.plant
                    FROM TAPMLST A
                    WHERE 1=1
                        AND A.comp_no = v_comp_no
                        AND A.pm_id = c1.pm_id
                    ;
                    */
                     
                  INSERT INTO TAWORKORDER(
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
                SELECT 
                     v_comp_no  AS comp_no
                    ,v_wo_id      AS wkor_id
                    ,v_wo_no      AS wo_no
                    ,A.description  AS description
                    ,v_wo_status    AS wo_status -- stand by 
                    ,A.wo_type      AS wo_type  -- preventmaint
                    ,A.pm_type      AS pm_type
                    ,'PMPLAN'        AS wo_gen_type
                    ,c1.sched_date  AS start_date
                    ,c1.sched_date  AS end_date
                    ,c1.sched_date  AS wkor_date
                    ,A.dept_id         AS dept_id
                    ,A.emp_id        AS emp_id
                    ,A.REMARK         AS perform
                    ,c1.pm_id         AS pm_id
                    ,c1.pmsched_id  AS pmsched_id
                    ,TO_CHAR(SYSDATE,'yyyymmdd') AS upd_date
                    ,A.emp_id          AS upd_by
                    ,A.wkctr_id
                    ,A.plant
                    ,c1.eqasmb_id
                FROM TAPMLST A
                WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.pm_id = c1.pm_id
                ;
                
                IF v_wo_type = 'PMC' THEN
                
                    SELECT    
                            CASE 
                                 WHEN c1.period_type = 'D' THEN TO_CHAR(TO_DATE(c1.sched_date,'yyyymmdd') + (c1.CYCLE * 1 ) -1 ,'yyyymmdd')
                                 WHEN c1.period_type = 'W' THEN TO_CHAR(TO_DATE(c1.sched_date,'yyyymmdd') + (7 * c1.CYCLE * 1 ) -1 ,'yyyymmdd') 
                                 WHEN c1.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(c1.sched_date,'yyyymmdd'),c1.CYCLE * 1) -1 ,'yyyymmdd') 
                                 WHEN c1.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(c1.sched_date,'yyyymmdd'),12 * c1.CYCLE * 1) -1 ,'yyyymmdd')
                            END AS next_date
                        INTO v_next_sch_date
                        FROM dual
                        ; 
                    INSERT INTO TAWOCALIB (comp_no , wkor_id , calib_type, period_type, CYCLE, next_calib_date) VALUES (v_comp_no,v_wo_id,'R', c1.period_type, c1.CYCLE, v_next_sch_date);
                    -- insert into TAWOCALIBGNLVALUE
                    SELECT COUNT(*) INTO v_count 
                    FROM TAEQTOOL 
                    WHERE comp_no = v_comp_no 
                        AND equip_id = c1.equip_id
                    ;
                    
                    IF v_count > 0 THEN
                        SELECT NVL(MAX(pmc_type),'XXX'), NVL(MAX(pmcalibstdtp_id),0)
                        INTO v_pmc_type, v_pmcalibstdtp_id 
                        FROM TAEQTOOL 
                        WHERE comp_no = v_comp_no 
                            AND equip_id = c1.equip_id
                        ; 
                    ELSE
                        v_pmc_type := 'XXXX';
                        v_pmcalibstdtp_id := 0; 
                    END IF;
                    
                    IF v_pmcalibstdtp_id = 0 THEN  -- 동일한 계측기 타입을 찾아서 하나만 넣어줌.
                    
                        INSERT INTO TAWOCALIBGNLVALUE(
                            comp_no
                            ,wocalibgnlvalue_id
                            ,wkor_id
                            ,calib_point_type
                            ,calib_point
                            ,allow_value
                            ,asf_std_value
                            ,ord_no
                            ,REMARK
                        )SELECT comp_no
                              ,sqawocalibgnlvalue_id.NEXTVAL
                              ,v_wo_id
                              ,calib_point_type
                              ,calib_point
                              ,NVL(c1.tolerance, allow_value)
                              ,asf_std_value
                              ,ord_no
                              ,REMARK
                        FROM TAPMCALIBSTDVALUE
                        WHERE comp_no = v_comp_no
                            AND pmcalibstdtp_id = (
                                                                SELECT pmcalibstdtp_id
                                                                FROM TAPMCALIBSTDTP 
                                                                WHERE comp_no = v_comp_no 
                                                                    AND pmc_type = v_pmc_type 
                                                                    AND ROWNUM = 1
                                                              ) ;
                                                                              
                    ELSE  -- 해당 표준교정항목값을 넣어 줌
                        INSERT INTO TAWOCALIBGNLVALUE(
                            comp_no
                            ,wocalibgnlvalue_id
                            ,wkor_id
                            ,calib_point_type
                            ,calib_point
                            ,allow_value
                            ,asf_std_value
                            ,ord_no
                            ,REMARK
                        )SELECT comp_no
                              ,sqawocalibgnlvalue_id.NEXTVAL
                              ,v_wo_id
                              ,calib_point_type
                              ,calib_point
                              ,allow_value
                              ,asf_std_value
                              ,ord_no
                              ,REMARK
                        FROM TAPMCALIBSTDVALUE
                        WHERE comp_no = v_comp_no
                            AND pmcalibstdtp_id = v_pmcalibstdtp_id ;
                                                                                  
                    END IF;
                    
                END IF;
                
                -- tawoequip 
                INSERT INTO TAWOEQUIP(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description, eqasmb_id)
                SELECT comp_no, sqawoequip_id.NEXTVAL,  v_wo_id,  equip_id, eqctg_id, description, c1.eqasmb_id 
                FROM TAEQUIPMENT
                WHERE comp_no = v_comp_no
                    AND equip_id = c1.equip_id
                ;
                    
                -- parts
                INSERT INTO TAWOPARTS(
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
                SELECT v_comp_no AS comp_no
                    ,sqawopart_id.NEXTVAL AS wopart_id
                    ,v_wo_id     AS wkor_id
                    ,c1.wcode_id AS wcode_id
                    ,A.part_id     AS part_id
                    ,'A'              AS part_grade
                    ,A.use_qty   AS use_qty
                    ,b.last_price  AS unit_price
                    ,A.use_qty * b.last_price AS tot_price
                FROM TAPMPART A
                    ,TAPARTS b
                WHERE A.comp_no = b.comp_no
                    AND A.part_id = b.part_id
                    AND A.comp_no = v_comp_no
                    AND A.pm_id =  c1.pm_id
                ;
   
                -- inspection point
                INSERT INTO TAWOPOINT(
                    comp_no
                    ,wopoint_id
                    ,wkor_id
                    ,pm_point_id
                    ,pm_point_rslt_status
                    ,pm_point_rep_status
                    ,is_saved
                )
                SELECT v_comp_no AS comp_no
                    ,sqawopoint_id.NEXTVAL AS wopoint_id
                    ,v_wo_id     AS wkor_id
                    ,A.pm_point_id AS pm_point_id
                    ,(SELECT cdsysd_no FROM 
                            (SELECT * FROM TACDSYSD
                            WHERE list_type='PM_POINT_RSLT_STATUS'
                            AND is_use = 'Y'
                            ORDER BY ord_no)
                            WHERE ROWNUM=1)               AS pm_point_rslt_status
                    ,'GD'               AS pm_point_rep_status
                    ,'N'                  AS is_saved
                FROM TAPMPOINT A
                WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.pm_id =  c1.pm_id
                    AND A.is_active = 'Y'
                ;
                    
                    /*
                 -- stardard point insert 
                INSERT INTO TAWOSTPOINT(
                     comp_no
                    ,wostpoint_id
                    ,wkor_id
                    ,stwrk_point_id
                    ,st_point_rslt_status
                )
                SELECT v_comp_no AS comp_no
                    ,sqawostpoint_id.NEXTVAL AS wostpoint_id
                    ,v_wo_id     AS wkor_id
                    ,A.stwrk_point_id AS stwrk_point_id
                    ,'GD'               AS st_point_rslt_status
                FROM TASTWRKPOINT A, TASTWRKLST b
                WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.comp_no = b.comp_no
                    AND A.stwrk_id = b.stwrk_id
                    AND A.is_active = 'Y'
                    AND b.pm_type = c1.pm_type
                    AND b.is_active = 'Y'
                    AND b.is_force ='Y'
                ;
                */
                    
                -- update wo_no in TAPMSCHED
                UPDATE TAPMSCHED SET
                     wkor_id = v_wo_id
                    ,pmsched_status =  'S' 
                WHERE comp_no = v_comp_no
                    AND pmsched_id = c1.pmsched_id
                ;
                    
                SP_WOMAKE_4WP_BYONE(v_comp_no, v_wo_id);
            
            

        
    END LOOP;
    
    
END;
/
*/

/** 447 + 이지수 */
 
update  TADAY SET dow_key_type = 'LABEL' 
                         , dow_key_no = 
                                    (case when dow = '1' then 'sun'
                                         when dow = '2' then 'mon'
                                         when dow = '3' then 'tue'
                                         when dow = '4' then 'wed'
                                         when dow = '5' then 'thu'
                                         when dow = '6' then 'fri'
                                         when dow = '7' then 'sat'
                                   end) ;

CREATE OR REPLACE procedure SP_SETDEFAULT_DUMYDAYS(
       v_comp_no                         in varchar2 
      ,v_user_no                          in varchar2
)is
    v_count                  number(4);  
    
    -- 오늘일자를 기준으로 올해 1일 부터 미래 2년 1개월의 일자를 새로 만듬. 
    cursor c_dumy_data is
            select 
                  TO_CHAR(TRUNC(SYSDATE,'yyyy') + rn-1,'yyyymmdd') as tday
                 ,TO_CHAR(TRUNC(SYSDATE,'yyyy') + rn-1,'yyyymm') as tmonth
                 ,TO_CHAR(TRUNC(SYSDATE,'yyyy') + rn-1,'iyyy') as tyear
                 ,TO_CHAR(TRUNC(SYSDATE,'yyyy') + rn-1,'D') as dow         --요일1:일, 2:월...7:토
                 ,TO_CHAR(TRUNC(SYSDATE,'yyyy') + rn-1,'IW') as week      --ISO 표준주차(월 ~ 일)
                 ,TO_CHAR(TRUNC(SYSDATE,'yyyy') + rn-1,'Q') as tquarter  --분기..
            from (
                select  rownum as rn
                from user_tab_columns
                where rownum <= (ADD_MONTHS(SYSDATE,36) - SYSDATE)
                order by 1
            )
            order by 1
          ;
    
    
begin
    
       
       -- 미래 2년 + 1개월치 날짜를 새로게 만듬.
       for c1 in c_dumy_data loop
              
              select COUNT(*)
              into v_count
              from TADAY
              where 1=1
                  and tday = c1.tday
              ;
              
              if v_count  = 0 then
                   insert into TADAY(
                        seq, tday,tmonth, tquarter,thalf,tyyyy,week,dow,DOW_KEY_TYPE,DOW_KEY_NO
                   )values( 
                          (select MAX(seq) + 1 from TADAY), c1.tday, c1.tmonth, c1.tquarter
                               ,case when c1.tquarter < '3' then '1' else '2' end
                               , c1.tyear, c1.week, c1.dow
                               ,'LABEL'
                               , (case when c1.dow = '1' then 'sun'
                                         when c1.dow = '2' then 'mon'
                                         when c1.dow = '3' then 'tue'
                                         when c1.dow = '4' then 'wed'
                                         when c1.dow = '5' then 'thu'
                                         when c1.dow = '6' then 'fri'
                                         when c1.dow = '7' then 'sat'
                                   end)
                   );
              end if;
              
              select COUNT(*)
              into v_count
              from TAMONTH
              where 1=1
                  and tmonth = c1.tmonth
              ;
              
              if v_count  = 0 then
                   insert into TAMONTH(seq, tmonth,tquarter,thalf,tyyyy)
                   values( (select MAX(seq) + 1 from TAMONTH), c1.tmonth, c1.tquarter
                               ,case when c1.tquarter < '3' then '1' else '2' end
                               , c1.tyear
                   );
              end if;
              
              select COUNT(*)
              into v_count
              from TAYEAR
              where 1=1
                  and tyyyy = c1.tyear
              ;
              
              if v_count  = 0 then
                   insert into TAYEAR(seq, tyyyy)
                   values( (select MAX(seq) + 1 from TAYEAR) , c1.tyear
                   );
              end if;
           
       end loop;
       
       
       -- 근무카렌다 전체에 대해서 default값 셋팅..
       SP_SETDEFAULT_WRKCAL_BYALL( v_comp_no,v_user_no);
       
       -- 표준항목의 다음달 단가를 default 셋팅해줌.
       SP_SETDEFAULT_STDCHKPOINTPIRCE(v_comp_no , v_user_no);
       
       update TABATPGM SET 
         exe_by = (select user_id 
                        from TAUSER 
                        where comp_no = v_comp_no 
                            and user_no = v_user_no 
                            and rownum = 1
                       )
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    where comp_no = v_comp_no
        and batpgm_no = 'TAWRKCALENDAR'
    ;
       
       
end;
/

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


/** 2020-01-07 연우 반영 */
/** 2020-01-09 평화오일씰 phos 반영 */

/*784 김남현*/
/*
 * TAPARTS에 최신 단가 갱신 시, 화폐도 같이 업데이트
 * 사이트 패치 시 각 사이트의 프로시저 확인 후 currency 입력 
 * 확인이 안되는 부분은 불러주세요.
 */
/*
CREATE OR REPLACE PROCEDURE DREAM_DEV.SP_PT_INSTOCK(
      v_comp_no                         IN varchar2 
     ,v_ptrechist_id                      IN number
)IS
    v_count                  number(4);  
    
    CURSOR c_rec_list IS
        SELECT 
            comp_no
            ,ptrechist_id
            ,ptrec_mode
            ,ptrec_type
            ,dept_id
            ,wcode_id
            ,rec_date
            ,part_id
            ,DECODE(part_grade,'A','A','B','B','B')  AS part_grade
            ,NVL(rec_qty,0) AS rec_qty
            ,NVL(unit_price,0) AS unit_price
            ,currency --
        FROM TAPTRECHIST
        WHERE 1=1
            AND comp_no = v_comp_no
            AND ptrechist_id = v_ptrechist_id
        ;
        
    
BEGIN
    
       
       FOR c1 IN c_rec_list LOOP
           
           IF c1.ptrec_mode = 'C' THEN  -- 입고
               SELECT COUNT(*)
                INTO v_count
                FROM TAPTSTOCK
                WHERE 1=1
                    AND comp_no = c1.comp_no
                    AND wcode_id =c1.wcode_id
                    AND part_id = c1.part_id
                    AND part_grade = c1.part_grade
                ;
                   
                IF v_count > 0 THEN
                    UPDATE TAPTSTOCK SET
                         stock_qty = NVL(stock_qty,0) + c1.rec_qty
                    WHERE 1=1
                         AND comp_no = c1.comp_no
                         AND wcode_id =c1.wcode_id
                         AND part_id = c1.part_id
                         AND part_grade = c1.part_grade
                    ;
                ELSE
                    INSERT INTO TAPTSTOCK(comp_no, wcode_id, part_id, part_grade, stock_qty)
                    VALUES(c1.comp_no, c1.wcode_id, c1.part_id, c1.part_grade, c1.rec_qty)
                    ;
                END IF;
                   
                -- 최신단가 갱신
                UPDATE TAPARTS SET
                    last_price = c1.unit_price
                    ,last_gr_date = TO_CHAR(SYSDATE,'yyyymmdd')
                    ,currency = c1.currency --
                WHERE comp_no = c1.comp_no
                    AND part_id = c1.part_id
                ;
           
           ELSIF c1.ptrec_mode = 'R' THEN  -- 취소
               SELECT COUNT(*)
                INTO v_count
                FROM TAPTSTOCK
                WHERE 1=1
                    AND comp_no = c1.comp_no
                    AND wcode_id =c1.wcode_id
                    AND part_id = c1.part_id
                    AND part_grade = c1.part_grade
               ;
                   
               IF v_count > 0 THEN
                   UPDATE TAPTSTOCK SET
                        stock_qty = NVL(stock_qty,0) - c1.rec_qty
                   WHERE 1=1
                        AND comp_no = c1.comp_no
                        AND wcode_id =c1.wcode_id
                        AND part_id = c1.part_id
                        AND part_grade = c1.part_grade
                   ;
               ELSE
                   INSERT INTO TAPTSTOCK(comp_no, wcode_id, part_id, part_grade, stock_qty)
                   VALUES(c1.comp_no, c1.wcode_id, c1.part_id, c1.part_grade, c1.rec_qty * -1)
                   ;
               END IF;
           
           END IF;
           
       END LOOP;
       
       

    
END;
/
 */

/*985 김남현*/
/*
 * 페이지 / 탭 / 버튼 권한 일괄 업데이트 CURSOR
 * 각 사이트 패치시 comp_no 변경해서 패치 부탁드립니다.
 * 패치 전에 확인이 필요해서 불러주세요.
 */

/*
DECLARE
    v_comp_no     varchar2(12);
    v_usrgrp_id    number(18);

CURSOR TAUSRGRP_CURSOR IS
SELECT 
       usrgrp_id
      , comp_no
  FROM TAUSRGRP
 WHERE 1 = 1
    AND comp_no = '100';

BEGIN 
    FOR c1 IN TAUSRGRP_CURSOR LOOP
        
        -- 페이지 권한
        DELETE FROM TAUGPGAU                 
         WHERE page_id IN (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail'))
           AND usrgrp_id      = (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = c1.comp_no AND usrgrp_id = c1.usrgrp_id)                 
           AND comp_no      =  c1.comp_no;

        INSERT INTO TAUGPGAU 
        SELECT 
            SQAUGPGAU_ID.NEXTVAL
            , (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = c1.comp_no AND usrgrp_id = c1.usrgrp_id)
            , page_id
            , c1.comp_no 
          FROM TAPAGE 
         WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail');
        
        -- 탭 페이지 권한
        DELETE FROM TAUGPGPGAU                 
         WHERE pgpage_id IN (SELECT pgpage_id FROM TAPGPAGE WHERE c_page_id IN (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail')))
           AND usrgrp_id = (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = c1.comp_no AND usrgrp_id = c1.usrgrp_id)                 
           AND comp_no   = c1.comp_no;

        INSERT INTO TAUGPGPGAU
        SELECT 
            SQAUGPGPGAU_ID.NEXTVAL
            , (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = c1.comp_no AND usrgrp_id = c1.usrgrp_id)
            , pgpage_id
            , c1.comp_no 
          FROM TAPGPAGE 
         WHERE c_page_id IN (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail'));
        
        -- 페이지 버튼 권한
        DELETE FROM TAUGPGBTNAU                 
         WHERE pgbtn_id IN (SELECT pgbtn_id FROM TAPGBTN WHERE page_id IN (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail')))
           AND usrgrp_id = (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = c1.comp_no AND usrgrp_id = c1.usrgrp_id)                 
           AND comp_no      =  c1.comp_no;

        INSERT INTO TAUGPGBTNAU
        SELECT 
            SQAUGPGBTN_ID.NEXTVAL
            , (SELECT usrgrp_id FROM TAUSRGRP WHERE comp_no = c1.comp_no AND usrgrp_id = c1.usrgrp_id)
            , pgbtn_id
            , c1.comp_no
          FROM TAPGBTN 
         WHERE page_id IN (SELECT page_id FROM TAPAGE WHERE file_name IN ('maWoResultBmRplMstrDetail', 'maWoResultPmOilMstrDetail', 'maWoResultCmRplMstrDetail', 'maWoResultPmRplMstrDetail', 'maWoResultToolList', 'maWoResultToolDetail', 'maWoResultPartList', 'maWoResultPartDetail', 'maWoResultCraftList', 'maWoResultCraftDetail'));
    
    END LOOP;
END;
*/

/** 2020-01-13 현대일렉트릭 hdele 반영 */
/** 2020-01-13 평화오일씰 phos 반영 */

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

/*패치 순서.. 잘 지켜야 함..*/
1) table
2) tabutton
3) tacdsysm_d
4) TACONFIG
5) TALANG
6) TAPAGE
7) TAPGBTN
8) TAPGPAGE
9) TAPGGRID
10) TGPGGRIDCOL
11) TAMENU
12. procedure


