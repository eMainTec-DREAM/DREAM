CREATE OR REPLACE PROCEDURE SP_WOMAKE_4WP_BYALL(
     v_comp_no        IN varchar2          
) IS
    v_count                 number(4);
    v_start_date          varchar2(8);
    v_to_date              varchar2(8);   
    
    cursor c_workorder is
                select a.comp_no
                           , a.wkor_id
                from TAWORKORDER a
                where 1=1
                    and a.wkor_date >= v_start_date
                    and a.wkor_date <= v_to_date
                    and a.wo_status = 'P'
            ;
  
BEGIN

   v_start_date := to_char(sysdate - 31, 'yyyymmdd');
   v_to_date  := to_char(sysdate + 31, 'yyyymmdd');
   

    for c1 in c_workorder loop
    
        SP_WOMAKE_4WP_BYONE(c1.comp_no, c1.wkor_id);
        
    end loop;
    

    
    
    
    
    
END;
/
