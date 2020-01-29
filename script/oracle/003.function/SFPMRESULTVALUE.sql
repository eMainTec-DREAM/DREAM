CREATE OR REPLACE FUNCTION SFPMRESULTVALUE( pCompNo VARCHAR2, pPmPointId number, pType VARCHAR2, pCnt number)
    RETURN VARCHAR2
IS
      v_result_status VARCHAR2(20);
      v_result_value  number(18,3);
      v_close_date    varchar2(8);
      
      
BEGIN

   select 
          pm_point_rslt_status
         ,to_char(result_value) as result_value
         ,close_date
    into v_result_status, v_result_value, v_close_date
    from (         
                select 
                      row_number() over (partition by aa.pm_point_id order by bb.comp_no, bb.wkor_date desc) as rn
                     ,aa.pm_point_id
                     ,aa.pm_point_rslt_status
                     ,aa.result_value
                     ,bb.close_date
                from tawopoint aa, taworkorder bb
                where 1=1
                    and aa.comp_no = bb.comp_no
                    and aa.wkor_id  = bb.wkor_id
                    and aa.comp_no = pCompNo
                    and bb.wo_status = 'C'
                    and aa.pm_point_id = pPmPointId
              )
    where rn = pCnt
   ;
   
   if pType = 'STATUS' then
       return v_result_status;
   elsif pType = 'VALUE' then
       return v_result_value;
   else
       return v_close_date;
   end if;
   
   
   

END;
/
