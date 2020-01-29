CREATE OR REPLACE PROCEDURE "SP_MUP_TYCATEG" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
   v_eqctg_id number(18);
   v_p_eqctg_id number(18);
   v_lvl  number(4);
   v_eqctg_no varchar2(20);
   v_full_desc varchar2(512);
   v_asmb_desc varchar2(512);
   v_ord_no number(4);
   
   -- exec SP_MUP_TYCATEG
      
    
     
    cursor c_e1_data is
                select 
                     trim(e1_desc) as e1_desc
                from TYCATEG a
                where 1=1
                    and trim(e1_desc) is not null
                group by trim(e1_desc) 
                order by trim(e1_desc) 
                ;
                
    cursor c_e2_data is
                select 
                     trim(e1_desc) as e1_desc
                     ,trim(e2_desc) as e2_desc
                from TYCATEG a
                where 1=1
                    and trim(e2_desc) is not null
                group by trim(e1_desc) ,trim(e2_desc) 
                order by trim(e1_desc)  ,trim(e2_desc) 
                ;

    cursor c_e3_data is
                select 
                      trim(e1_desc) as e1_desc
                     ,trim(e2_desc) as e2_desc
                     ,trim(e3_desc) as e3_desc
                from TYCATEG a
                where 1=1
                   and trim(e3_desc) is not null
                group by trim(e1_desc) ,trim(e2_desc) ,trim(e3_desc)
                order by trim(e1_desc)  ,trim(e2_desc)  ,trim(e3_desc)
                ; 
                
      cursor c_e4_data is
                select 
                      trim(e1_desc) as e1_desc
                     ,trim(e2_desc) as e2_desc
                     ,trim(e3_desc) as e3_desc
                     ,trim(e4_desc) as e4_desc
                from TYCATEG a
                where 1=1
                   and trim(e3_desc) is not null
                group by trim(e1_desc) ,trim(e2_desc) ,trim(e3_desc) ,trim(e4_desc)
                order by trim(e1_desc)  ,trim(e2_desc)  ,trim(e3_desc),trim(e4_desc)
                ; 
                
                
                
       cursor c_asmb_data(p_e4_desc varchar2) is
            WITH DATA AS ( 
                  SELECT p_e4_desc str FROM dual
               )
               SELECT trim(regexp_substr(str, '[^,]+', 1, LEVEL)) str
               FROM DATA
               CONNECT BY instr(str, ',', 1, LEVEL - 1) > 0
               ;               
                                
begin

    
    
    for c1 in c_e1_data loop
    
                   select sqaeqctg_id.nextval
                   into v_eqctg_id
                   from dual;
                   
                   select trim(to_char(nvl(to_number(max(eqctg_no)),10000) + 1,'00000'))
                   into v_eqctg_no
                   from TAEQCTG
                   where translate(eqctg_no,'x0123456789','x') IS null 
                   ;
                   
                   v_p_eqctg_id := 0;
                    v_lvl := 1;
                    v_full_desc := c1.e1_desc;
                   
                   insert into TAEQCTG(
                                                 comp_no,eqctg_id, eqctg_no, description
                                                ,full_desc, p_eqctg_id, lvl
                                                ,remark, ord_no, is_use
                   )values(
                                               v_comp_no, v_eqctg_id, v_eqctg_no, c1.e1_desc
                                               ,v_full_desc, v_p_eqctg_id, v_lvl
                                               ,'', v_eqctg_no, 'Y'
                   );
                   
                   
    end loop;
    
    
    
     for c1 in c_e2_data loop
    
                   select sqaeqctg_id.nextval
                   into v_eqctg_id
                   from dual;
                   
                   select trim(to_char(nvl(to_number(max(eqctg_no)),10000) + 1,'00000'))
                   into v_eqctg_no
                   from TAEQCTG
                   where translate(eqctg_no,'x0123456789','x') IS null 
                   ;
                   
                   select eqctg_id
                   into v_p_eqctg_id
                   from TAEQCTG
                   where comp_no = v_comp_no
                       and description = c1.e1_desc
                       and rownum = 1;
                   
                    v_lvl := 2;
                    v_full_desc := c1.e1_desc || ' > ' || c1.e2_desc;
                   
                   insert into TAEQCTG(
                                                 comp_no,eqctg_id, eqctg_no, description
                                                ,full_desc, p_eqctg_id, lvl
                                                ,remark, ord_no, is_use
                   )values(
                                               v_comp_no, v_eqctg_id, v_eqctg_no, c1.e2_desc
                                               ,v_full_desc, v_p_eqctg_id, v_lvl
                                               ,'', v_eqctg_no, 'Y'
                   );
                   
                   
    end loop;
    
    
    for c1 in c_e3_data loop
    
                   select sqaeqctg_id.nextval
                   into v_eqctg_id
                   from dual;
                   
                   select trim(to_char(nvl(to_number(max(eqctg_no)),10000) + 1,'00000'))
                   into v_eqctg_no
                   from TAEQCTG
                   where translate(eqctg_no,'x0123456789','x') IS null 
                   ;
                   
                   select eqctg_id
                   into v_p_eqctg_id
                   from TAEQCTG
                   where comp_no = v_comp_no
                       and full_desc = c1.e1_desc || ' > ' || c1.e2_desc
                       and rownum = 1;
                   
                    v_lvl := 3;
                    v_full_desc := c1.e1_desc || ' > ' || c1.e2_desc || ' > ' || c1.e3_desc;
                   
                   insert into TAEQCTG(
                                                 comp_no,eqctg_id, eqctg_no, description
                                                ,full_desc, p_eqctg_id, lvl
                                                ,remark, ord_no, is_use
                   )values(
                                               v_comp_no, v_eqctg_id, v_eqctg_no, c1.e3_desc
                                               ,v_full_desc, v_p_eqctg_id, v_lvl
                                               ,'', v_eqctg_no, 'Y'
                   );
                   
                   
                   select trim(e4_desc)
                   into v_asmb_desc
                   from TYCATEG
                   where e1_desc = c1.e1_desc
                       and e2_desc = c1.e2_desc
                       and e3_desc = c1.e3_desc
                       and rownum = 1;
                       
                   if v_asmb_desc is not null then
                       v_ord_no := 0;
                       for c2 in c_asmb_data(v_asmb_desc) loop
                           v_ord_no := v_ord_no + 1;
                           
                           insert into TAEQCTGASMB(comp_no, eq_ctg_asmb_id, eqctg_id, description, ord_no, is_use,remark)
                           values(v_comp_no, sqaeq_ctg_asmb_id.nextval, v_eqctg_id, c2.str, v_ord_no, 'Y', c2.str);

                       end loop;
                   
                   end if;
                   
                   
                   
                   
                   
                   
                   
                   
                   
    end loop;
    
    
    
    
    
end;
/
