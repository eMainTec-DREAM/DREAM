CREATE OR REPLACE PROCEDURE SP_MUP_LNWRKLIST (
     v_comp_no   varchar2 default '100'
    ,v_user_no   varchar2 default 'admin'
    ,v_key_id    number default 0
) is

   v_count          number(4);
   v_plant           varchar2(20);
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
                    ,trim(wrkcal_no)   as wrkcal_no
                    ,trim(wrkcal_desc) as wrkcal_desc
                    ,trim(plant)       as plant
                    ,trim(eqloc_no)    as eqloc_no
                    ,trim(eqloc_desc)  as eqloc_desc
                    ,trim(item_no)     as item_no
                    ,trim(item_desc)   as item_desc
                    ,trim(remark)      as remark
                    ,is_success
                    ,SKEY_ID 
                from TYLNWRKLIST_EXCEL a
                where 1=1
                    and comp_no = v_comp_no
                    and user_no = v_user_no
                    and 0 = v_key_id
                union all
                select 
                    sunbeon
                    ,trim(wrkcal_no)   as wrkcal_no
                    ,trim(wrkcal_desc) as wrkcal_desc
                    ,trim(plant)       as plant
                    ,trim(eqloc_no)    as eqloc_no
                    ,trim(eqloc_desc)  as eqloc_desc
                    ,trim(item_no)     as item_no
                    ,trim(item_desc)   as item_desc
                    ,trim(remark)      as remark
                    ,is_success
                    ,SKEY_ID 
                from TYLNWRKLIST_EXCEL a
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
              
        UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = '' ,message=''  WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
        
        if trim(c1.wrkcal_no) is not null then
            select count(*) into v_count
            from TALNWRKLIST 
            where comp_no = v_comp_no 
                and lnwrklist_no = c1.wrkcal_no
            ;  
            if v_count > 0 then --기존 가동 카렌다에 중복된 코드가 존재하는 지 확인.
                UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = 'N' ,message = '가동카렌다코드 중복됨. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            else
                select count(*) into v_count
                from TYLNWRKLIST_EXCEL 
                where comp_no = v_comp_no 
                    and trim(wrkcal_no) = c1.wrkcal_no
                ;
                if v_count > 1 then --엑셀 자료에 가동 카렌다에 중복된 코드가 존재하는 지 확인.
                    UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = 'N' ,message = '가동카렌다코드 중복됨. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
                end if;
            end if;
        end if;
        
        if trim(c1.wrkcal_desc) is null then
          UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = 'N' ,message = message || '가동카렌다 명 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
        end if;
        
        if trim(c1.plant) is not null then
            select count(*) into v_count
            from taplant 
            where comp_no = v_comp_no 
                and plant = c1.plant
            ;  
            if v_count = 0 then --Plant 코드가 존재하는지 확인.
                UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = 'N' ,message = 'Plant코드 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end if;
        else
            UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = 'N' ,message = 'Plant코드 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
        end if;
        
        if trim(c1.eqloc_no) is not null then
            select count(*) into v_count
            from taeqloc 
            where comp_no = v_comp_no 
                and plant = c1.eqloc_no
            ;  
            if v_count = 0 then --위치코드가 존재하는지 확인.
                UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = 'N' ,message = '위치코드 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end if;
        end if;
        
        if trim(c1.item_no) is not null then
            select count(*) into v_count
            from taequipment 
            where comp_no = v_comp_no 
                and item_no = c1.item_no
                and is_last_version = 'Y'
            ;  
            if v_count = 0 then --설비코드가 존재하는지 확인.
                UPDATE TYLNWRKLIST_EXCEL  SET  IS_SUCCESS = 'N' ,message = '설비코드 확인. ' WHERE COMP_NO = v_comp_no   AND USER_NO = v_user_no  AND SKEY_ID = c1.skey_id;
            end if;
        end if;
              
    end loop;
    
    
    
    for c1 in c_data loop
        if c1.is_success = 'Y' then  -- 성공한 데이타만 등록함.
        
            if trim(c1.wrkcal_no) is null then
                
                select sqalnwrklist_id.nextval into v_lnwrklist_id
                from dual;
                
                v_lnwrklist_no := v_lnwrklist_id;
            end if;
                
            if trim(c1.eqloc_no) is not null then
                select eqloc_id into v_eqloc_id
                from taeqloc 
                where comp_no = v_comp_no 
                    and plant = c1.eqloc_no
                    and rownum = 1
                ;  
            else
                v_eqloc_id := null;
            end if;
                
            if trim(c1.item_no) is not null then
                select equip_id into v_equip_id
                from taequipment 
                where comp_no = v_comp_no 
                    and plant = c1.item_no
                    and is_last_version = 'Y'
                    and rownum = 1
                ;  
            else 
                v_equip_id := null;
            end if;
                
            select count(*) into v_count
            from TAWRKCALLIST
            where comp_no = v_comp_no
                and plant = c1.plant
                and rownum = 1
            ;
                
            if v_count > 0 then
                select wrkcallist_id into v_wrkcallist_id
                from TAWRKCALLIST
                where comp_no = v_comp_no
                    and plant = c1.plant
                    and is_use = 'Y'
                    and rownum = 1
                ;
            else
                select max(wrkcallist_id) into v_wrkcallist_id
                from TAWRKCALLIST
                where comp_no = v_comp_no
                    and is_use = 'Y'
                ;
            end if;
                    
            insert into TALNWRKLIST(comp_no, lnwrklist_id, lnwrklist_no, description, plant, is_use, remark, wrkcallist_id)
            values(v_comp_no, v_lnwrklist_id, v_lnwrklist_no, c1.wrkcal_desc, c1.plant, 'Y',c1.remark, v_wrkcallist_id)
            ;
            
             FOR i IN 1..7 LOOP
                   if i not in (1,7) then
                       INSERT INTO TAEQLOCDOWRUN(comp_no, lnwrklist_id, eqlocdowrun_id, dow, dtime, ntime, etime, is_use)
                       VALUES( v_comp_no, v_lnwrklist_id, sqaeqlocdowrun_id.NEXTVAL, i, 480, 480, 480,'Y');
                   end if;
             END LOOP;
             
             
             if v_eqloc_id is not null then
                 update taeqloc set 
                     lnwrklist_id = v_lnwrklist_id
                 where comp_no = v_comp_no
                     and eqloc_id in (select eqloc_id
                                             from taeqloc
                                             where comp_no = v_comp_no
                                             start with eqloc_id = 2173
                                             connect by prior  eqloc_id = p_eqloc_id
                                             )
                 ;
                 
                 update taequipment set
                      lnwrklist_id = v_lnwrklist_id
                 where comp_no = v_comp_no
                     and eqloc_id in (select eqloc_id
                                             from taeqloc
                                             where comp_no = v_comp_no
                                             start with eqloc_id = 2173
                                             connect by prior  eqloc_id = p_eqloc_id
                                             )
                 ;
             end if;
             
             
              if v_equip_id is not null then
                 update taequipment set
                      lnwrklist_id = v_lnwrklist_id
                 where comp_no = v_comp_no
                     and equip_id = v_equip_id
                 ;
             end if;

        end if;
        
    end loop;
    
    
    
end;
/
