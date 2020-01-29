CREATE OR REPLACE PROCEDURE SP_SYS_UPD_USRPTDAY  (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS

     v_count number(4);
     
    CURSOR c_data IS
        SELECT 
             a.dept_id,
             a.comp_no,
           TO_CHAR(SYSDATE-1,'yyyymmdd') loginDate
        FROM TADEPT a
        WHERE 1=1
            AND comp_no = v_comp_no
        ;
                                
BEGIN
    --하루에 여러번 돌릴 경우가 있으므로 돌리기전에 그날 데이터를 삭제한다.
    DELETE FROM TAUSRPTDAY WHERE comp_no = v_comp_no AND login_date = TO_CHAR(SYSDATE-1,'yyyymmdd');
        
    FOR c1 IN c_data LOOP
    
    -- 접속 대상자 수 
    SELECT count(*)
         INTO v_count
            FROM TAUSER
            WHERE comp_no = v_comp_no
            AND emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no = v_comp_no AND dept_id= c1.dept_id)
            AND is_monitor='Y'
        ;
        IF v_count > 0 THEN
             INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate,c1.dept_id deptId, 'USR_MCNT' dataType,count(1) cnt
            FROM TAUSER
            WHERE comp_no = v_comp_no
            AND emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no = v_comp_no AND dept_id= c1.dept_id)
            AND is_monitor='Y'
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'USR_MCNT',0
         FROM dual
            ;
        END IF;
        
     -- 접속자 수
     
     SELECT count(*)
         INTO v_count
           FROM TAUSER a, 
            (
            SELECT * FROM TADAY x, TALOGINCCLOG y
            WHERE x.tday = y.login_date
            AND x.tday = c1.loginDate
            AND y.comp_no = v_comp_no
            ) b
            WHERE a.comp_no = b.comp_no
            AND a.user_id = b.user_id
            AND a.comp_no = v_comp_no
            AND a.is_monitor='Y'
            AND b.user_id IN (SELECT user_id FROM TAUSER WHERE comp_no = v_comp_no AND 
            emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no=v_comp_no AND dept_id=c1.dept_id))
        ;
        IF v_count > 0 THEN
             INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate ,c1.dept_id deptId, 'USR_ACNT' dataType, sum(decode(b.login_date,c1.loginDate, count(DISTINCT b.user_id),0)) cnt
            FROM TAUSER a, 
            (
            SELECT * FROM TADAY x, TALOGINCCLOG y
            WHERE x.tday = y.login_date
            AND x.tday = c1.loginDate
            AND y.comp_no = v_comp_no
            ) b
            WHERE a.comp_no = b.comp_no
            AND a.user_id = b.user_id
            AND a.comp_no = v_comp_no
            AND a.is_monitor='Y'
            AND b.user_id IN (SELECT user_id FROM TAUSER WHERE comp_no = v_comp_no AND 
            emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no=v_comp_no AND dept_id=c1.dept_id))
            GROUP BY b.comp_no , b.login_date, b.user_id
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'USR_ACNT',0
         FROM dual
            ;
        END IF;
        
     -- 접속횟수
     SELECT count(*)
         INTO v_count
          FROM TAUSER a, 
            (
            SELECT * FROM TADAY x, TALOGINCCLOG y
            WHERE x.tday = y.login_date
            AND x.tday = c1.loginDate
            AND y.comp_no = v_comp_no
            ) b
            WHERE a.comp_no = b.comp_no
            AND a.user_id = b.user_id
            AND a.comp_no = v_comp_no
            AND a.is_monitor='Y'
            AND b.user_id IN (SELECT user_id FROM TAUSER WHERE comp_no = v_comp_no AND 
            emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no=v_comp_no AND dept_id=c1.dept_id))
        ;
        IF v_count > 0 THEN
         INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'USR_LCNT' dataType, sum(decode(b.login_date,c1.loginDate,count(b.user_id),0)) cnt
            FROM TAUSER a, 
            (
            SELECT * FROM TADAY x, TALOGINCCLOG y
            WHERE x.tday = y.login_date
            AND x.tday = c1.loginDate
            AND y.comp_no = v_comp_no
            ) b
            WHERE a.comp_no = b.comp_no
            AND a.user_id = b.user_id
            AND a.comp_no = v_comp_no
            AND a.is_monitor='Y'
            AND b.user_id IN (SELECT user_id FROM TAUSER WHERE comp_no = v_comp_no AND 
            emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no=v_comp_no AND dept_id=c1.dept_id))
            GROUP BY b.comp_no , b.login_date, b.user_id
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'USR_LCNT',0
         FROM dual
            ;
        END IF;
        
         
     -- 작업대상건수
     SELECT count(*)
         INTO v_count
          FROM tAWORKORDER x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND x.start_date = c1.loginDate
            AND x.dept_id = c1.dept_id   
        ;
        IF v_count > 0 THEN
          INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'WO_PCNT' dataType, sum(decode(x.start_date,c1.loginDate,count(x.start_date),0)) cnt
            FROM tAWORKORDER x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND x.start_date = c1.loginDate
            AND x.dept_id = c1.dept_id   
            GROUP BY x.start_date 
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'WO_PCNT',0
         FROM dual
            ;
        END IF;
        
        
     -- 작업완료건수
    SELECT count(*)
         INTO v_count
          FROM tAWORKORDER x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.comp_no =v_comp_no
            AND x.start_date = c1.loginDate
            AND x.wo_status = 'C' 
            AND x.dept_id = c1.dept_id 
        ;
        IF v_count > 0 THEN
          INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'WO_ACNT' dataType, sum(decode(x.start_date,c1.loginDate,count(x.start_date),0)) cnt
            FROM tAWORKORDER x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.comp_no =v_comp_no
            AND x.start_date = c1.loginDate
            AND x.wo_status = 'C' 
            AND x.dept_id = c1.dept_id 
            GROUP BY x.start_date 
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'WO_ACNT',0
         FROM dual
            ;
        END IF;
     -- 작업실행율
     SELECT count(*)
         INTO v_count
          FROM tAWORKORDER x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.start_date = c1.loginDate
            AND x.comp_no =v_comp_no
            AND x.dept_id = c1.dept_id 
        ;
        IF v_count > 0 THEN
          INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'WO_RATE' dataType, ROUND(sum(decode(x.wo_status,'C',decode(x.start_date,c1.loginDate,1,0),0))/ 
                        DECODE(sum(decode(x.start_date,c1.loginDate,1,0)),0,NULL,sum(decode(x.start_date,c1.loginDate,1,0)))*100,2) cnt
            FROM tAWORKORDER x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.start_date = c1.loginDate
            AND x.comp_no =v_comp_no
            AND x.dept_id = c1.dept_id 
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'WO_RATE',0
         FROM dual
            ;
        END IF;
         
     -- 예방작업 기준건수
      SELECT count(*)
         INTO v_count
         FROM TAPMLST x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND x.dept_id = c1.dept_id 
        ;
        IF v_count > 0 THEN
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'PM_MCNT' dataType, count(1) cnt
            FROM TAPMLST x, TAEQUIPMENT y 
            WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND x.dept_id = c1.dept_id 
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'PM_MCNT',0
         FROM dual
            ;
        END IF;
        
       
     -- 예방작업 계획건수
     SELECT count(*)
         INTO v_count
         FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
            WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND y.dept_id = c1.dept_id 
            AND d.start_date = c1.loginDate
        ;
        IF v_count > 0 THEN
          INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'PM_PCNT' dataType, sum(decode(d.start_date,c1.loginDate,1,0)) cnt
             FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
            WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND y.dept_id = c1.dept_id 
            AND d.start_date = c1.loginDate
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'PM_PCNT',0
         FROM dual
            ;
        END IF;
        
        
     -- 예방작업 완료건수
      SELECT count(*)
         INTO v_count
          FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d  
            WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.pmsched_status = 'C' 
            AND x.comp_no =   v_comp_no
            AND y.dept_id = c1.dept_id   
            AND d.start_date = c1.loginDate
        ;
        IF v_count > 0 THEN
         INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'PM_ACNT' dataType, sum(decode(d.start_date,c1.loginDate,1,0)) cnt
            FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d  
            WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.pmsched_status = 'C' 
            AND x.comp_no =   v_comp_no
            AND y.dept_id = c1.dept_id   
            AND d.start_date = c1.loginDate
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'PM_ACNT',0
         FROM dual
            ;
        END IF;
        
         
     -- 예방작업 실행율
     SELECT count(*)
         INTO v_count
          FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
            WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND y.dept_id = c1.dept_id   
            AND d.start_date = c1.loginDate
        ;
        IF v_count > 0 THEN
        INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT compNo, SQAUSRPTDAY_ID.NEXTVAL, loginDate, deptId, dataType,NVL(cnt,0)
         FROM (
            SELECT c1.comp_no compNo, c1.loginDate loginDate, c1.dept_id deptId, 'PM_RATE' dataType, ROUND(sum(decode(x.pmsched_status,'C',decode(d.start_date,c1.loginDate,1,0),0))/ 
                        DECODE(sum(decode(d.start_date,c1.loginDate,1,0)),0,NULL,sum(decode(d.start_date,c1.loginDate,1,0)))*100,2) cnt
            FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
            WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.comp_no = v_comp_no
            AND y.dept_id = c1.dept_id   
            AND d.start_date = c1.loginDate
            )
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT c1.comp_no, SQAUSRPTDAY_ID.NEXTVAL, c1.loginDate, c1.dept_id, 'PM_RATE',0
         FROM dual
            ;
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
        AND batpgm_no = 'TAUSRPTDAY'
    ;
    
    
    
END;
/
