CREATE OR REPLACE PROCEDURE "SP_MUP_TYEQCTG" (
    v_comp_no varchar2 default '100'
) is

-- truncate table TAEQASMB
-- exec SP_MUP_TYEQCTG

   v_count number(4);
   v_equip_id number(18);
   
   v_eqctg_id number(18);
   v_p_eqctg_id number(18);
   v_lvl  number(4);
   v_eqctg_no varchar2(20);
   v_full_desc varchar2(512);
   v_asmb_desc varchar2(512);
   v_ord_no number(4);
      
    
     
                
      cursor c_e1_data is
                select 
                     sn
                     ,excel_no
                     ,plant
                     ,gw
                     ,item_name
                     ,trim(e1_desc) as e1_desc
                     ,trim(e2_desc) as e2_desc
                     ,trim(e3_desc) as e3_desc
                     ,trim(e4_desc) as e4_desc
                from TYEQCATEG a
                where 1=1
                order by sn
                ; 
                
                                
begin


            update taequipment set eqctg_id = null
            where comp_no = v_comp_no
            ;
    
    
    
    for c1 in c_e1_data loop
    
                   select count(*)
                   into v_count
                   from TAEQCTG
                   where comp_no = v_comp_no
                       and full_desc = c1.e1_desc || ' > ' || c1.e2_desc || ' > ' || c1.e3_desc
                       and rownum = 1;
                       
                   if v_count > 0 then
                   
                       select eqctg_id
                       into v_eqctg_id
                       from TAEQCTG
                       where comp_no = v_comp_no
                           and full_desc = c1.e1_desc || ' > ' || c1.e2_desc || ' > ' || c1.e3_desc
                           and rownum = 1; 
                           
                           
                   
                       select count(*)
                       into v_count
                       from taequipment
                       where comp_no = v_comp_no
                           and excel_no = c1.excel_no
                           and rownum = 1;
                           
                       
                       if v_count > 0 then
                       
                               select equip_id
                               into v_equip_id
                               from taequipment
                               where comp_no = v_comp_no
                                   and excel_no = c1.excel_no
                                   and rownum = 1;
                       
                           -- 설비종류 갱신
                           update taequipment set 
                               eqctg_id = v_eqctg_id
                           where comp_no = v_comp_no
                               and equip_id = v_equip_id
                            ;
                            
                            
                            -- 설비별 작업부위 생성
                            
                            insert into TAEQASMB(comp_no, eqasmb_id, equip_id, eq_ctg_asmb_id, eqctg_id, description, is_eqtype_asmb, ord_no, is_use)
                            select  a.comp_no, sqaeqasmb_id.nextval, v_equip_id, b.eq_ctg_asmb_id,b.eqctg_id, b.description, 'Y' , b.ord_no, b.is_use
                            from TAEQCTG a
                                 ,TAEQCTGASMB b
                            where a.comp_no = b.comp_no
                                and a.eqctg_id = b.eqctg_id
                                and a.eqctg_id = v_eqctg_id
                            ;
                           
                            
                            
                   end if;
               end if;
                            
    end loop;
    
    
    
    
    
end;
/
