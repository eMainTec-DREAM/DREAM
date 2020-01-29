CREATE OR REPLACE PROCEDURE "SP_MUP_TYWAREHOUSE" (
    v_comp_no varchar2 default '100'
) is


-- truncate table TAWAREHOUSE;
-- exec SP_MUP_TYWAREHOUSE

   v_count number(4);
      
    cursor c_excel_data is
                select 
                      sn
                      ,code
                      ,code_desc
                      ,plant
                     ,plant_DESC
                from TYWAREHOUSE a
                where 1=1
                order by sn
                ;
                  
        
    
    
begin

    
    
    for c1 in c_excel_data loop
    
               select count(*)
               into v_count
               from TAWAREHOUSE
               where comp_no = v_comp_no
                   and wcode = c1.code
               ;
               
               if v_count > 0 then 
                   update TAWAREHOUSE set
                       wname = c1.code_desc
                       ,plant = c1.plant
                       ,is_use = 'Y'
                       ,wh_type = 'MWARE'
                   where comp_no = v_comp_no
                       and wcode = c1.code
                   ;
               else
                   
                   insert into TAWAREHOUSE(comp_no, wcode_id, wcode, wname, wh_type, is_use, plant)
                   values(v_comp_no, sqawcode_id.nextval, c1.code, c1.code_desc,  'MWARE','Y',c1.plant)
                   ;
                   
                   
               end if;
               
               
                 
                 null;
               
    end loop;
    
    
    
end;
/
