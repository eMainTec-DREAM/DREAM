CREATE OR REPLACE PROCEDURE "SP_MUP_TYASSET" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
      
    cursor c_excel_data is
                select 
                      SN
                    ,asset_no
                    ,description
                    ,replace(replace(acq_date,'-',''),'.','') as acq_date
                    ,to_number(buyer_amt) as buyer_amt
                    ,to_number(dep_amt) as dep_amt
                    ,to_number(res_amt) as res_amt
                from TYASSET a
                where 1=1
                order by sn
                ;
                                
begin

    
    
    for c1 in c_excel_data loop
    
               select count(*)
               into v_count
               from TAASSET
               where comp_no = v_comp_no
                   and asset_no = c1.asset_no
               ;
               
               if v_count > 0 then 
                   update TAASSET set
                       description = c1.description
                       ,acq_date =c1.acq_date
                       ,buyer_amt = c1.buyer_amt
                       ,dep_amt = c1.dep_amt
                       ,res_amt = c1.res_amt
                   where  comp_no = v_comp_no
                       and asset_no = c1.asset_no
                   ;
               else
                   
                   insert into TAASSET(comp_no, asset_id, asset_no, description, acq_date, buyer_amt, dep_amt,res_amt)
                   values(v_comp_no, sqaasset_id.nextval, c1.asset_no, c1.description,c1.acq_date, c1.buyer_amt, c1.dep_amt, c1.res_amt)
                   ;
                   
                   
               end if;
               
               
                 
                 null;
               
    end loop;
    
    
    
end;
/
