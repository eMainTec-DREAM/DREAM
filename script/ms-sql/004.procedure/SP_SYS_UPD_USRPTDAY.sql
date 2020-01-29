CREATE PROCEDURE [dbo].[SP_SYS_UPD_USRPTDAY] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    declare @v_login_date     as varchar(8)
    
    set @v_login_date = CONVERT(VARCHAR(8), DATEADD(day,-1,GETDATE()),112)
    
    DECLARE c_data CURSOR FOR
        SELECT 
              a.dept_id
        FROM TADEPT a
        WHERE 1=1
            AND comp_no = @v_comp_no
        ;
    
    --c_pm cursor parameter
    DECLARE @c_dept_id       bigint
            
    --하루에 여러번 돌릴 경우가 있으므로 돌리기전에 그날 데이터를 삭제한다.
    DELETE FROM TAUSRPTDAY WHERE comp_no = @v_comp_no AND login_date = @v_login_date;
    
    OPEN c_data
    FETCH NEXT FROM c_data INTO @c_dept_id
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            
            -- 접속 대상자 수 
            SELECT @v_count = count(*)
            FROM TAUSER
            WHERE comp_no = @v_comp_no
                AND emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no = @v_comp_no AND dept_id= @c_dept_id)
                AND is_monitor='Y'
            ;
            
            if @v_count > 0 
                INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
                SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType, isnull(cnt,0)
                FROM(
                       SELECT @v_comp_no compNo, @v_login_date loginDate,@c_dept_id deptId, 'USR_MCNT' dataType, count(1) cnt
                       FROM TAUSER
                       WHERE comp_no = @v_comp_no
                           AND emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no = @v_comp_no AND dept_id= @c_dept_id)
                           AND is_monitor='Y'
                    ) A
                where 1=1
                ;
            else
                INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
                SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'USR_MCNT',0
                ;
                    
                    
            -- 접속자 수
            SELECT @v_count = count(*)
            FROM TAUSER a, ( SELECT * FROM TADAY x, TALOGINCCLOG y
                             WHERE x.tday = y.login_date
                                 AND x.tday = @v_login_date
                                 AND y.comp_no = @v_comp_no
                            ) b
            WHERE a.comp_no = b.comp_no
                AND a.user_id = b.user_id
                AND a.comp_no = @v_comp_no
                AND a.is_monitor='Y'
                AND b.user_id IN (SELECT user_id FROM TAUSER 
                                  WHERE comp_no = @v_comp_no AND emp_id IN (SELECT emp_id FROM TAEMP WHERE comp_no=@v_comp_no AND dept_id=@c_dept_id))
            ;
            
            
            IF @v_count > 0
                INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
                SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
                FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate ,@c_dept_id deptId, 'USR_ACNT' dataType
                              , sum(case when b.login_date = @v_login_date then 1 else 0 end) cnt
                       FROM TAUSER a, ( SELECT * FROM TADAY x, TALOGINCCLOG y
                                        WHERE x.tday = y.login_date
                                            AND x.tday = @v_login_date
                                            AND y.comp_no = @v_comp_no
                                       ) b
                       WHERE a.comp_no = b.comp_no
                           AND a.user_id = b.user_id
                           AND a.comp_no = @v_comp_no
                           AND a.is_monitor='Y'
                           AND b.user_id IN (SELECT user_id FROM TAUSER 
                                             WHERE comp_no = @v_comp_no AND emp_id IN (SELECT emp_id FROM TAEMP 
                                                                                      WHERE comp_no=@v_comp_no AND dept_id=@c_dept_id)
                                             )
                       GROUP BY b.comp_no , b.login_date, b.user_id
                      ) A
                ;
            ELSE
                INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
                SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'USR_ACNT',0
                ;
                
                
            -- 접속횟수
           SELECT @v_count = count(*)
           FROM TAUSER a,( SELECT * FROM TADAY x, TALOGINCCLOG y
                           WHERE x.tday = y.login_date
                               AND x.tday = @v_login_date
                               AND y.comp_no = @v_comp_no
                          ) b
           WHERE a.comp_no = b.comp_no
                AND a.user_id = b.user_id
                AND a.comp_no = @v_comp_no
                AND a.is_monitor='Y'
                AND b.user_id IN (SELECT user_id FROM TAUSER 
                                  WHERE comp_no = @v_comp_no AND emp_id IN (SELECT emp_id FROM TAEMP 
                                                                           WHERE comp_no=@v_comp_no AND dept_id=@c_dept_id)
                                 )
           ;
           
           IF @v_count > 0
             INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
             SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
             FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'USR_LCNT' dataType
                            , sum(case when b.login_date = @v_login_date then 1 else 0 end) cnt
                    FROM TAUSER a, ( SELECT * FROM TADAY x, TALOGINCCLOG y
                                     WHERE x.tday = y.login_date
                                         AND x.tday = @v_login_date
                                         AND y.comp_no = @v_comp_no
                                   ) b
                    WHERE a.comp_no = b.comp_no
                        AND a.user_id = b.user_id
                        AND a.comp_no = @v_comp_no
                        AND a.is_monitor='Y'
                        AND b.user_id IN (SELECT user_id FROM TAUSER 
                                          WHERE comp_no = @v_comp_no AND emp_id IN (SELECT emp_id FROM TAEMP 
                                                                                   WHERE comp_no=@v_comp_no AND dept_id=@c_dept_id)
                                         )
                    GROUP BY b.comp_no , b.login_date, b.user_id
                ) a
             ;
          ELSE
              INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
              SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'USR_LCNT',0
              
              
              
          -- 작업대상건수
          SELECT @v_count = count(*)
          FROM tAWORKORDER x, TAEQUIPMENT y 
          WHERE x.comp_no = y.comp_no 
                AND x.equip_id = y.equip_id 
                AND x.comp_no = @v_comp_no
                AND x.start_date = @v_login_date
                AND x.dept_id = @c_dept_id  
          ;
          
          IF @v_count > 0
              INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
              SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
              FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'WO_PCNT' dataType
                            , sum(case when x.start_date = @v_login_date then 1 else 0 end) cnt
                      FROM tAWORKORDER x, TAEQUIPMENT y 
                      WHERE x.comp_no = y.comp_no 
                         AND x.equip_id = y.equip_id 
                         AND x.comp_no = @v_comp_no
                         AND x.start_date = @v_login_date
                         AND x.dept_id = @c_dept_id   
                      GROUP BY x.start_date 
                    ) A
              ;
            ELSE
               INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
               SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'WO_PCNT',0
                ;
                
         -- 작업완료건수
         SELECT @v_count = count(*)
         FROM tAWORKORDER x, TAEQUIPMENT y 
         WHERE x.comp_no = y.comp_no 
             AND x.equip_id = y.equip_id 
             AND x.comp_no =@v_comp_no
             AND x.start_date = @v_login_date
             AND x.wo_status = 'C' 
             AND x.dept_id = @c_dept_id
         ;
         
         IF @v_count > 0
             INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
             SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
             FROM (SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'WO_ACNT' dataType
                          , sum(case when x.start_date = @v_login_date then 1 else 0 end) cnt
                   FROM tAWORKORDER x, TAEQUIPMENT y 
                   WHERE x.comp_no = y.comp_no 
                        AND x.equip_id = y.equip_id 
                        AND x.comp_no =@v_comp_no
                        AND x.start_date = @v_login_date
                        AND x.wo_status = 'C' 
                        AND x.dept_id = @c_dept_id 
                   GROUP BY x.start_date 
                ) A
         ELSE
             INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
             SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'WO_ACNT',0
             
             
             
         -- 작업실행율
         SELECT @v_count = count(*)
         FROM tAWORKORDER x, TAEQUIPMENT y 
         WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.start_date = @v_login_date
            AND x.comp_no =@v_comp_no
            AND x.dept_id = @c_dept_id 
         ;
         
         
         IF @v_count > 0
             INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
             SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
             FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'WO_RATE' dataType
                            ,  round(((sum(case when x.wo_status='C' then (case when x.start_date = @v_login_date then 1 else 0 end) else 0 end))
                             / (case when sum(case when x.start_date = @v_login_date then 1 else 0 end) = 0 then null 
                               else sum(case when x.start_date = @v_login_date then 1 else 0 end) 
                               end))*100, 2) as cnt
                    FROM tAWORKORDER x, TAEQUIPMENT y 
                    WHERE x.comp_no = y.comp_no 
                        AND x.equip_id = y.equip_id 
                        AND x.start_date = @v_login_date
                        AND x.comp_no =@v_comp_no
                        AND x.dept_id = @c_dept_id 
                  ) A
             ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
           SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'WO_RATE',0
           
       
       
        -- 예방작업 기준건수
        SELECT @v_count = count(*)
        FROM TAPMLST x, TAEQUIPMENT y 
        WHERE x.comp_no = y.comp_no 
            AND x.equip_id = y.equip_id 
            AND x.comp_no = @v_comp_no
            AND x.dept_id = @c_dept_id 
        ;
        
        IF @v_count > 0
            INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
            SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
            FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'PM_MCNT' dataType, count(1) cnt
                   FROM TAPMLST x, TAEQUIPMENT y 
                   WHERE x.comp_no = y.comp_no 
                       AND x.equip_id = y.equip_id 
                       AND x.comp_no = @v_comp_no
                      AND x.dept_id = @c_dept_id 
                 ) A
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
           SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'PM_MCNT',0
           
           
       -- 예방작업 계획건수
       SELECT @v_count = count(*)
       FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
       WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.comp_no = @v_comp_no
            AND y.dept_id = @c_dept_id 
            AND d.start_date = @v_login_date
       ;
       
       IF @v_count > 0
          INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
          SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
          FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'PM_PCNT' dataType
                        , sum(case when d.start_date = @v_login_date then 1 else 0 end ) cnt
                 FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
                 WHERE x.comp_no = y.comp_no 
                    AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
                    AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
                    AND z.equip_id = y.equip_id 
                    AND x.comp_no = @v_comp_no
                    AND y.dept_id = @c_dept_id 
                    AND d.start_date = @v_login_date
               ) A
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
           SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'PM_PCNT',0
           
           
           
       -- 예방작업 완료건수
       SELECT @v_count = count(*)
       FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d  
       WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.pmsched_status = 'C' 
            AND x.comp_no =   @v_comp_no
            AND y.dept_id = @c_dept_id   
            AND d.start_date = @v_login_date
       ;
       
       
       IF @v_count > 0
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
           SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
           FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'PM_ACNT' dataType
                        , sum(case when d.start_date = @v_login_date then 1 else 0 end) cnt
                  FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d  
                  WHERE x.comp_no = y.comp_no 
                        AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
                        AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
                        AND z.equip_id = y.equip_id 
                        AND x.pmsched_status = 'C' 
                        AND x.comp_no =   @v_comp_no
                        AND y.dept_id = @c_dept_id   
                        AND d.start_date = @v_login_date
                ) A
            ;
       ELSE
         INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
         SELECT @v_comp_no,NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'PM_ACNT',0
        
         
        -- 예방작업 실행율
        SELECT @v_count = count(*)
        FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
        WHERE x.comp_no = y.comp_no 
            AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
            AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
            AND z.equip_id = y.equip_id 
            AND x.comp_no = @v_comp_no
            AND y.dept_id = @c_dept_id   
            AND d.start_date = @v_login_date
        ;
        
        IF @v_count > 0
            INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
            SELECT compNo, NEXT VALUE FOR SQAUSRPTDAY_ID, loginDate, deptId, dataType,isnull(cnt,0)
            FROM ( SELECT @v_comp_no compNo, @v_login_date loginDate, @c_dept_id deptId, 'PM_RATE' dataType
                   
                   ,round(((sum(case when x.pmsched_status = 'C' then case when d.start_date = @v_login_date then 1 else 0 end else 0 end ) ) / 
                    (case when (sum(case when d.start_date = @v_login_date then 1 else 0 end)) = 0 then null else   
                        (sum(case when d.start_date = @v_login_date then 1 else 0 end)) end )) * 100, 2) cnt
            
                               
                               
                               
                   FROM TAPMSCHED x, TAPMLST y, TAEQUIPMENT z, TAWORKORDER d   
                   WHERE x.comp_no = y.comp_no 
                        AND y.comp_no = z.comp_no  AND x.comp_no = d.comp_no 
                        AND x.pm_id = y.pm_id AND x.wkor_id = d.wkor_id 
                        AND z.equip_id = y.equip_id 
                        AND x.comp_no = @v_comp_no
                        AND y.dept_id = @c_dept_id   
                        AND d.start_date = @v_login_date
                  ) A
            ;
        ELSE
           INSERT INTO TAUSRPTDAY(comp_no, usrptday_id, login_date, dept_id, usingrpt_type,d_value)
           SELECT @v_comp_no, NEXT VALUE FOR SQAUSRPTDAY_ID, @v_login_date, @c_dept_id, 'PM_RATE',0
        
        
        
        
        
        
        
            FETCH NEXT FROM c_data INTO @c_dept_id
        END
    CLOSE c_data
    DEALLOCATE c_data
    
    
    UPDATE TABATPGM set 
         exe_by = (SELECT top 1 user_id 
                        FROM tauser 
                        WHERE comp_no = @v_comp_no 
                            AND user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    WHERE comp_no = @v_comp_no
        AND batpgm_no = 'TAUSRPTDAY'
    ;
    
  