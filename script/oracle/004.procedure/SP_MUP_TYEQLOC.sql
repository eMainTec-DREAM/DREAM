CREATE OR REPLACE PROCEDURE "SP_MUP_TYEQLOC" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
   v_eqloc_id number(18);
   v_p_eqloc_id number(18);
   v_lvl_type  varchar2(10);
   v_lvl  number(4);
   v_eqloc_no varchar2(20);
   v_full_desc varchar2(512);
      
    
     
    cursor c_l1_data is
                select 
                     trim(l1_desc) as l1_desc
                from TYEQLOC a
                where 1=1
                group by trim(l1_desc) 
                ;
                
    cursor c_l2_data is
                select 
                     trim(l1_desc) as l1_desc
                     ,trim(l2_desc) as l2_desc
                from TYEQLOC a
                where 1=1
                group by trim(l1_desc) ,trim(l2_desc) 
                ;

    cursor c_l3_data is
                select 
                      trim(l1_desc) as l1_desc
                     ,trim(l2_desc) as l2_desc
                     ,trim(l3_desc) as l3_desc
                from TYEQLOC a
                where 1=1
                group by trim(l1_desc) ,trim(l2_desc) ,trim(l3_desc)
                ;                
                                
begin

    
    
    for c1 in c_l1_data loop
    
                   select sqaeqloc_id.nextval
                   into v_eqloc_id
                   from dual;
                   
                   select trim(to_char(nvl(to_number(max(eqloc_no)),10000) + 1,'00000'))
                   into v_eqloc_no
                   from TAEQLOC
                   where translate(eqloc_no,'x0123456789','x') IS null 
                   ;
                   
                   v_p_eqloc_id := 0;
                   v_lvl_type  := 'CP';
                    v_lvl := 1;
                    v_full_desc := c1.l1_desc;
                   
                   insert into TAEQLOC(
                                                 comp_no,eqloc_id, eqloc_no, description
                                                ,full_desc, p_eqloc_id, eqloc_lvl_type, lvl
                                                ,remark, ord_no, is_use
                   )values(
                                               v_comp_no, v_eqloc_id, v_eqloc_no, c1.l1_desc
                                               ,v_full_desc, v_p_eqloc_id, v_lvl_type, v_lvl
                                               ,'', v_eqloc_no, 'Y'
                   );
                   
                   
    end loop;
    
    
    
     for c1 in c_l2_data loop
    
                   select sqaeqloc_id.nextval
                   into v_eqloc_id
                   from dual;
                   
                   select trim(to_char(nvl(to_number(max(eqloc_no)),10000) + 1,'00000'))
                   into v_eqloc_no
                   from TAEQLOC
                   where translate(eqloc_no,'x0123456789','x') IS null 
                   ;
                   
                   select eqloc_id
                   into v_p_eqloc_id
                   from TAEQLOC
                   where comp_no = v_comp_no
                       and description = c1.l1_desc
                       and rownum = 1;
                   
                   v_lvl_type  := 'GW';
                    v_lvl := 2;
                    v_full_desc := c1.l1_desc || ' > ' || c1.l2_desc;
                   
                   insert into TAEQLOC(
                                                 comp_no,eqloc_id, eqloc_no, description
                                                ,full_desc, p_eqloc_id, eqloc_lvl_type, lvl
                                                ,remark, ord_no, is_use
                   )values(
                                               v_comp_no, v_eqloc_id, v_eqloc_no, c1.l2_desc
                                               ,v_full_desc, v_p_eqloc_id, v_lvl_type, v_lvl
                                               ,'', v_eqloc_no, 'Y'
                   );
                   
                   
    end loop;
    
    
    for c1 in c_l3_data loop
    
                   select sqaeqloc_id.nextval
                   into v_eqloc_id
                   from dual;
                   
                   select trim(to_char(nvl(to_number(max(eqloc_no)),10000) + 1,'00000'))
                   into v_eqloc_no
                   from TAEQLOC
                   where translate(eqloc_no,'x0123456789','x') IS null 
                   ;
                   
                   select eqloc_id
                   into v_p_eqloc_id
                   from TAEQLOC
                   where comp_no = v_comp_no
                       and full_desc = c1.l1_desc || ' > ' || c1.l2_desc
                       and rownum = 1;
                   
                   v_lvl_type  := 'LN';
                    v_lvl := 3;
                    v_full_desc := c1.l1_desc || ' > ' || c1.l2_desc || ' > ' || c1.l3_desc;
                   
                   insert into TAEQLOC(
                                                 comp_no,eqloc_id, eqloc_no, description
                                                ,full_desc, p_eqloc_id, eqloc_lvl_type, lvl
                                                ,remark, ord_no, is_use
                   )values(
                                               v_comp_no, v_eqloc_id, v_eqloc_no, c1.l3_desc
                                               ,v_full_desc, v_p_eqloc_id, v_lvl_type, v_lvl
                                               ,'', v_eqloc_no, 'Y'
                   );
                   
                   
    end loop;
    
    
    
end;
/
