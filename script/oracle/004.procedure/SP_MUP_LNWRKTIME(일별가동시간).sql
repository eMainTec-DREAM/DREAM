CREATE OR REPLACE PROCEDURE SP_MUP_LNWRKTIME (
     v_comp_no   varchar2 default '100'
    ,v_user_no   varchar2 default 'admin'
    ,v_key_id    number default 0
) is

   v_count          number(4);
   v_value          number(18,3);
   
   v_lnwrklist_id    number(18);
   v_lnwrklist_no   varchar2(20);
   v_equip_id     number(18);
   v_eqloc_id     number(18);
   v_wrkcallist_id number(18);
   
   v_upload_emp_id number(18);
   
    cursor c_data is
        select *
        from (
                select 
                    sunbeon
                    ,trim(wrkcal_no)     as wrkcal_no
                    ,trim(wrkcal_desc)  as wrkcal_desc
                    ,trim(yyyymm)        as yyyymm
                    ,trim(value_01)      as value_01
                    ,trim(value_02)      as value_02
                    ,trim(value_03)      as value_03
                    ,trim(value_04)      as value_04
                    ,trim(value_05)      as value_05
                    ,trim(value_06)      as value_06
                    ,trim(value_07)      as value_07
                    ,trim(value_08)      as value_08
                    ,trim(value_09)      as value_09
                    ,trim(value_10)      as value_10
                    ,trim(value_11)      as value_11
                    ,trim(value_12)      as value_12
                    ,trim(value_13)      as value_13
                    ,trim(value_14)      as value_14
                    ,trim(value_15)      as value_15
                    ,trim(value_16)      as value_16
                    ,trim(value_17)      as value_17
                    ,trim(value_18)      as value_18
                    ,trim(value_19)      as value_19
                    ,trim(value_20)      as value_20
                    ,trim(value_21)      as value_21
                    ,trim(value_22)      as value_22
                    ,trim(value_23)      as value_23
                    ,trim(value_24)      as value_24
                    ,trim(value_25)      as value_25
                    ,trim(value_26)      as value_26
                    ,trim(value_27)      as value_27
                    ,trim(value_28)      as value_28
                    ,trim(value_29)      as value_29
                    ,trim(value_30)      as value_30
                    ,trim(value_31)      as value_31
                    ,is_success
                    ,SKEY_ID 
                from TYLNWRKTIME_EXCEL a
                where 1=1
                    and comp_no = v_comp_no
                    and user_no = v_user_no
                    and 0 = v_key_id
                union all
                select 
                    sunbeon
                    ,trim(wrkcal_no)     as wrkcal_no
                    ,trim(wrkcal_desc)  as wrkcal_desc
                    ,trim(yyyymm)        as yyyymm
                    ,trim(value_01)      as value_01
                    ,trim(value_02)      as value_02
                    ,trim(value_03)      as value_03
                    ,trim(value_04)      as value_04
                    ,trim(value_05)      as value_05
                    ,trim(value_06)      as value_06
                    ,trim(value_07)      as value_07
                    ,trim(value_08)      as value_08
                    ,trim(value_09)      as value_09
                    ,trim(value_10)      as value_10
                    ,trim(value_11)      as value_11
                    ,trim(value_12)      as value_12
                    ,trim(value_13)      as value_13
                    ,trim(value_14)      as value_14
                    ,trim(value_15)      as value_15
                    ,trim(value_16)      as value_16
                    ,trim(value_17)      as value_17
                    ,trim(value_18)      as value_18
                    ,trim(value_19)      as value_19
                    ,trim(value_20)      as value_20
                    ,trim(value_21)      as value_21
                    ,trim(value_22)      as value_22
                    ,trim(value_23)      as value_23
                    ,trim(value_24)      as value_24
                    ,trim(value_25)      as value_25
                    ,trim(value_26)      as value_26
                    ,trim(value_27)      as value_27
                    ,trim(value_28)      as value_28
                    ,trim(value_29)      as value_29
                    ,trim(value_30)      as value_30
                    ,trim(value_31)      as value_31
                    ,is_success
                    ,SKEY_ID 
                from TYLNWRKTIME_EXCEL a
                where 1=1
                    and comp_no = v_comp_no
                    and user_no = v_user_no
                    and SKEY_ID = v_key_id
            ) a
            where 1=1
            order by  to_number(a.sunbeon) asc           
                ;
                                
begin



   
   select count(*) into v_count
   from taemp 
   where comp_no = v_comp_no
       and emp_no = 'ADMIN'
   ;
   if v_count > 0 then
           select emp_id
           into v_upload_emp_id
           from taemp 
           where comp_no = v_comp_no
               and emp_no = 'ADMIN'
               and rownum = 1
           ;
   end if;
   
    
    for c1 in c_data loop
              
        UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = '' ,message=''  WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
        
        select count(*) into v_count
        from TALNWRKLIST 
        where comp_no = v_comp_no 
            and lnwrklist_no = c1.wrkcal_no
        ;  
        
        if v_count = 0 then --기존 가동 카렌다에 존재하는 지 확인.
            UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '가동카렌다코드 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
        end if;
        
        select count(*) into v_count
        from TAMONTH 
        where tmonth =  replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','')
        ;  
        
        if v_count = 0 then --월이 잘못되었는지 확인
            UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '월데이타 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
        end if;
        
        
        if c1.value_01 is not null then 
            begin
                select to_number(replace(replace(c1.value_01,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '01일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_02 is not null then 
            begin
                select to_number(replace(replace(c1.value_02,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '02일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_03 is not null then 
            begin
                select to_number(replace(replace(c1.value_03,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '03일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_04 is not null then 
            begin
                select to_number(replace(replace(c1.value_04,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '04일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_05 is not null then 
            begin
                select to_number(replace(replace(c1.value_05,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '05일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_06 is not null then 
            begin
                select to_number(replace(replace(c1.value_06,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '06일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_07 is not null then 
            begin
                select to_number(replace(replace(c1.value_07,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '07일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_08 is not null then 
            begin
                select to_number(replace(replace(c1.value_08,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '08일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_09 is not null then 
            begin
                select to_number(replace(replace(c1.value_09,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '09일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_10 is not null then 
            begin
                select to_number(replace(replace(c1.value_10,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '10일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_11 is not null then 
            begin
                select to_number(replace(replace(c1.value_11,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '11일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_12 is not null then 
            begin
                select to_number(replace(replace(c1.value_12,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '12일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
        if c1.value_13 is not null then 
            begin
                select to_number(replace(replace(c1.value_13,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '13일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_14 is not null then 
            begin
                select to_number(replace(replace(c1.value_14,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '14일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_15 is not null then 
            begin
                select to_number(replace(replace(c1.value_15,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '15일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_16 is not null then 
            begin
                select to_number(replace(replace(c1.value_16,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '16일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_17 is not null then 
            begin
                select to_number(replace(replace(c1.value_17,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '17일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_18 is not null then 
            begin
                select to_number(replace(replace(c1.value_18,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '18일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_19 is not null then 
            begin
                select to_number(replace(replace(c1.value_19,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '19일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_20 is not null then 
            begin
                select to_number(replace(replace(c1.value_20,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '20일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_21 is not null then 
            begin
                select to_number(replace(replace(c1.value_21,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '21일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_22 is not null then 
            begin
                select to_number(replace(replace(c1.value_22,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '22일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_23 is not null then 
            begin
                select to_number(replace(replace(c1.value_23,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '23일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_24 is not null then 
            begin
                select to_number(replace(replace(c1.value_24,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '24일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_25 is not null then 
            begin
                select to_number(replace(replace(c1.value_25,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '25일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_26 is not null then 
            begin
                select to_number(replace(replace(c1.value_26,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '26일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_27 is not null then 
            begin
                select to_number(replace(replace(c1.value_27,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '27일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_28 is not null then 
            begin
                select to_number(replace(replace(c1.value_28,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '28일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_29 is not null then 
            begin
                select to_number(replace(replace(c1.value_29,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '29일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_30 is not null then 
            begin
                select to_number(replace(replace(c1.value_30,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '30일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
         if c1.value_31 is not null then 
            begin
                select to_number(replace(replace(c1.value_31,',',''),' ','')) into v_value from dual;
            exception when others then
                 UPDATE TYLNWRKTIME_EXCEL  SET  IS_SUCCESS = 'N' ,message = '31일 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end;
        end if;
    end loop;
    
    
    
    for c1 in c_data loop
        if c1.is_success = 'Y' then  -- 성공한 데이타만 등록함.
            
            select lnwrklist_id into v_lnwrklist_id
            from TALNWRKLIST 
            where comp_no = v_comp_no 
                and lnwrklist_no = c1.wrkcal_no
                and rownum = 1
            ;  
            
            -- 기존 데이타 삭제(월 전체)
            delete from talnwrktime
            where comp_no = v_comp_no
                and lnwrklist_id = v_lnwrklist_id
                and wrk_date like replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '%'
            ;
            
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_01,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '01'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_02,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '02'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_03,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '03'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_04,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '04'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_05,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '05'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_06,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '06'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_07,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '07'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_08,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '08'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_09,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '09'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_10,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '10'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_11,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '11'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_12,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '12'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_13,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '13'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_14,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '14'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_15,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '15'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_16,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '16'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_17,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '17'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_18,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '18'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_19,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '19'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_20,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '20'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_21,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '21'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_22,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '22'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_23,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '23'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_24,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '24'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_25,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '25'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_26,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '26'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_27,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '27'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_28,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '28'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_29,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '29'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_30,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '30'
            ;
            insert into talnwrktime(comp_no, lnwrktime_id, wrk_date, dtime, lnwrklist_id)
            select v_comp_no, sqalnwrktime_id.nextval, tday, to_number(replace(replace(c1.value_31,',',''),' ','')) , v_lnwrklist_id 
            from taday 
            where tday = replace(replace(replace(trim(c1.yyyymm),'.',''),'-',''),' ','') || '31'
            ;

        end if;
        
    end loop;
    
    
    
end;
/
