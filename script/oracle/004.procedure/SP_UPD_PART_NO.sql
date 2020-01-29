CREATE OR REPLACE PROCEDURE "SP_UPD_PART_NO" (
    v_comp_no varchar2 default '100'
) is



   v_count number(4);
   v_part_id number(18);
   v_part_no varchar2(40);
   
      
    cursor c_data is
                select 
                    comp_no
                    ,part_id
                    ,part_no
                    ,description
                    ,pt_size
                    ,model
                    ,maker
                from taparts a
                where 1=1
                order by comp_no, part_id
                ;
    
begin

   -- exec SP_UPD_PART_NO('100')

    update taparts set mro_type = 'R';
    
    for c1 in c_data loop
    
               SELECT                                                                                 
                        TRIM(TO_CHAR(NVL(MAX(TO_NUMBER(PART_NO)),10000) +1, '00000'))
               into v_part_no
               FROM TAPARTS                                                                            
               WHERE TRANSLATE(PART_NO,'x0123456789','x') IS NULL                                    
                  AND  comp_no =  c1.comp_no
               ;
               
               update taparts set 
                   part_no = v_part_no
                   ,full_desc = v_part_no || '|' || upper(c1.description) || '|' || upper(c1.pt_size)|| '|' || c1.model|| '|' || c1.maker
               where comp_no = c1.comp_no
                   and part_id = c1.part_id
               ;
  
               
    end loop;
    
    
    
end;
/
