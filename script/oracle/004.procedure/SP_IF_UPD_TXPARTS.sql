CREATE OR REPLACE PROCEDURE SP_IF_UPD_TXPARTS  (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
   v_dept_no  varchar2(10);
   v_dept_name varchar2(300);
      
    cursor c_data is
        select 
             a.part_id
            ,a.part_no
            ,a.description||','||a.pt_size as full_desc
            ,a.last_price
            ,to_char(sysdate,'yyyymmdd') as ifdate
        from taparts a
        where 1=1
            and upd_date >= (select last_exe_date 
                                     from TABATPGM 
                                     where comp_no = v_comp_no 
                                               and batpgm_no = 'TXPARTS' 
                                     )
        ;
                                
begin

        
    for c1 in c_data loop
    
        -- create txparts table
        
        
        select count(*)
        into v_count
        from TAPTUSEDDEPT a,tadept b
        where a.comp_no = b.comp_no
            and a.dept_id = b.dept_id
            and a.part_id = c1.part_id
            and a.comp_no = v_comp_no
        ;
        
        if v_count > 0 then
            select b.dept_no,b.description
            into v_dept_no, v_dept_name
            from TAPTUSEDDEPT a,tadept b
            where a.comp_no = b.comp_no
                and a.dept_id = b.dept_id
                 and a.part_id = c1.part_id
                and a.comp_no = v_comp_no
                and rownum = 1
            ;
        else
            v_dept_no := '';
            v_dept_name := '';
        end if;
        
        
    
        
        select count(*)
        into v_count
        from TXPARTS
        where part_id = c1.part_id
        ;
        
        if v_count > 0 then

            update TXPARTS set
                 full_desc = c1.full_desc
                 ,last_price = c1.last_price
                 ,ifdate = c1.ifdate
                 ,dept_no = v_dept_no
                 ,dept_name = v_dept_name
            where part_id = c1.part_id
            ;
            
        else
            
            
            insert into TXPARTS(part_id, part_no, full_desc, last_price, ifdate,dept_no, dept_name)
            values(c1.part_id, c1.part_no, c1.full_desc, c1.last_price, c1.ifdate,v_dept_no, v_dept_name)
            ;
            
        
        end if;
               
    end loop;
    
    update TABATPGM set 
         exe_by = (select user_id 
                        from tauser 
                        where comp_no = v_comp_no 
                            and user_no = v_user_no 
                            and rownum = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    where comp_no = v_comp_no
        and batpgm_no = 'TXPARTS'
    ;
    
    
    
end;
/
