CREATE OR REPLACE PROCEDURE SP_IF_UPD_PTREPAIRLIST     (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
   v_equip_id number(18);
   v_part_id  number(18);
   v_accnt_no varchar2(20);
   v_vendor_id number(18);
   v_dept_id number(18);
   v_emp_id number(18);
   v_wcode_id number(18);
   
   v_prreclist_id number(18);
   v_ptrechist_id number(18);
   v_ptrepairlist_id number(18);
   
   
            
    cursor c_data is
        select
             a.rprapp_id
             ,a.app_doc_no
             ,a.pt_pr_rpr_type
             ,a.item_no
             ,a.part_no
             ,nvl(a.req_qty,0) req_qty
             ,nvl(a.unit_price,0) unit_price
             ,nvl(a.tot_amt,0) tot_amt
             ,a.accnt_no
             ,a.vendor_no
             ,a.app_date
             ,a.reg_by
             ,a.ifresult
            ,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as received_date
        from txptrepairlist a
        where 1=1
            and a.ifresult = 'N'
        ;

begin

    for c1 in c_data loop
    
       -- 설비 오류검증처리
       select count(*)
       into v_count
       from taequipment 
       where 1=1
           and comp_no = v_comp_no
           and item_no = c1.item_no
       ;
       
       if v_count > 0 then
           select equip_id
           into v_equip_id
           from taequipment 
           where 1=1
               and comp_no = v_comp_no
               and item_no = c1.item_no
           ;
       else
           v_equip_id := null; 
       end if;
       
       -- 부품 오류검증처리
       select count(*)
       into v_count
       from taparts 
       where 1=1
           and comp_no = v_comp_no
           and part_no = c1.part_no
       ;
       if v_count > 0 then
           select part_id
           into v_part_id
           from taparts 
           where 1=1
               and comp_no = v_comp_no
               and part_no = c1.part_no
           ;
       else
           v_part_id := null; 
       end if;
       
       -- 계정코드 오류검증처리
       select count(*)
       into v_count
       from tacdsysd 
       where 1=1
           and list_type = 'ACCNT_NO'
           and cdsysd_no = c1.accnt_no
       ;
       if v_count > 0 then
           select cdsysd_no
           into v_accnt_no
           from tacdsysd 
           where 1=1
               and list_type = 'ACCNT_NO'
               and cdsysd_no = c1.accnt_no
           ;
       else
           v_accnt_no := null; 
       end if;
       
       -- 거래처코드 오류검증처리
       select count(*)
       into v_count
       from tavendor 
       where 1=1
           and comp_no = v_comp_no
           and vendor_no = c1.vendor_no
       ;
       if v_count > 0 then
           select vendor_id
           into v_vendor_id
           from tavendor 
           where 1=1
               and comp_no = v_comp_no
               and vendor_no = c1.vendor_no
           ;
       else
           v_vendor_id := null; 
       end if;
       
       -- 사원번호로 부서,창고 오류검증
       select count(*)
       into v_count
       from taemp a, tadept b
       where a.comp_no = b.comp_no
           and a.dept_id = b.dept_id
           and a.comp_no = v_comp_no
           and a.emp_no = c1.reg_by
       ;
       if v_count > 0 then
           select a.dept_id,a.emp_id,b.wcode_id
           into v_dept_id, v_emp_id, v_wcode_id
           from taemp a, tadept b
           where a.comp_no = b.comp_no
                and a.dept_id = b.dept_id
                and a.comp_no = v_comp_no
                and a.emp_no = c1.reg_by
           ;
       else
           v_dept_id := null; 
           v_emp_id := null; 
           v_wcode_id := null; 
       end if;
       
       
       
       if v_equip_id is not null 
           and v_part_id is not null 
           and v_accnt_no is not null 
           and v_vendor_id is not null 
           and v_dept_id is not null 
           and v_emp_id is not null 
           and v_wcode_id is not null 
           and c1.pt_pr_rpr_type in ('P','R') then
          -- 정상적인 데이타일 경우에만 처리.
          
          insert into tabgtdeptact(
              comp_no
              ,bgtdeptact_id
              ,accnt_no
              ,dept_id
              ,vendor_id
              ,app_date
              ,equip_id
              ,part_id
              ,req_qty
              ,unit_price
              ,tot_amt
              ,reg_by
              ,rprapp_id
              ,app_doc_no
          )values(
              v_comp_no
              ,sqabgtdeptact_id.nextval
              ,v_accnt_no
              ,v_dept_id
              ,v_vendor_id
              ,c1.app_date
              ,v_equip_id
              ,v_part_id
              ,c1.req_qty
              ,c1.unit_price
              ,c1.req_qty * c1.unit_price
              ,v_emp_id
              ,c1.rprapp_id
              ,c1.app_doc_no
          );
          
          
          if c1.pt_pr_rpr_type = 'P' then  -- 구매처리
              -- insert taptprreclist
              -- insert taptrechist
              -- exec sp_pt_instock
          
              select sqaprreclist_id.nextval, sqaptrechist_id.nextval
              into v_prreclist_id, v_ptrechist_id
              from dual;
             
              insert into taptprreclist(
                  comp_no
                  ,prreclist_id
                  ,prreclist_no
                  ,prreclist_status
                  ,dept_id
                  ,wcode_id
                  ,vendor_id
                  ,rec_date
                  ,part_id
                  ,rec_qty
                  ,unit_price
                  ,tot_price
                  ,inspector
                  ,remark
              )values(
                  v_comp_no
                  ,v_prreclist_id
                  ,v_prreclist_id
                  ,'C'
                  ,v_dept_id
                  ,v_wcode_id
                  ,v_vendor_id
                  ,c1.app_date
                  ,v_part_id
                  ,c1.req_qty
                  ,c1.unit_price
                  ,c1.req_qty * c1.unit_price
                  ,v_emp_id
                  ,'Notes Approval'
                  
              );
              
              insert into taptrechist(
                  comp_no
                  ,ptrechist_id
                  ,ptrec_mode
                  ,ptrec_type
                  ,reclist_id
                  ,dept_id
                  ,wcode_id
                  ,vendor_id
                  ,rec_date
                  ,part_id
                  ,part_grade
                  ,rec_qty
                  ,unit_price
                  ,tot_price
              )values(
                  v_comp_no
                  ,v_ptrechist_id
                  ,'C'
                  ,'REC'
                  ,v_prreclist_id
                  ,v_dept_id
                  ,v_wcode_id
                  ,v_vendor_id
                  ,c1.app_date
                  ,v_part_id
                  ,'A'
                  ,c1.req_qty
                  ,c1.unit_price
                  ,c1.req_qty * c1.unit_price
              );
              
              -- 재고조정 pricedure 호출
              SP_PT_INSTOCK(v_comp_no, v_ptrechist_id);
              
              
          
          elsif c1.pt_pr_rpr_type = 'R' then  -- 수리처리
              
              select sqaptrepairlist_id.nextval, sqaptrechist_id.nextval
              into v_ptrepairlist_id, v_ptrechist_id
              from dual;
              
              insert into taptrepairlist(
                  comp_no
                  ,ptrepairlist_id
                  ,ptrepairlist_no
                  ,ptrepairlist_status
                  ,reg_date
                  ,dept_id
                  ,wcode_id
                  ,vendor_id
                  ,repair_date
                  ,part_id
                  ,repair_qty
                  ,unit_price
                  ,tot_price
                  ,inspector
                  ,remark
              )values(
                  v_comp_no
                  ,v_ptrepairlist_id
                  ,v_ptrepairlist_id
                  ,'V'
                  ,c1.app_date
                  ,v_dept_id
                  ,v_wcode_id
                  ,v_vendor_id
                  ,c1.app_date
                  ,v_part_id
                  ,c1.req_qty
                  ,c1.unit_price
                  ,c1.req_qty * c1.unit_price
                  ,v_emp_id
                  ,'Notes Approval'
                  
              );
              
              insert into taptrechist(
                  comp_no
                  ,ptrechist_id
                  ,ptrec_mode
                  ,ptrec_type
                  ,reclist_id
                  ,dept_id
                  ,wcode_id
                  ,vendor_id
                  ,rec_date
                  ,part_id
                  ,part_grade
                  ,rec_qty
                  ,unit_price
                  ,tot_price
              )values(
                  v_comp_no
                  ,v_ptrechist_id
                  ,'C'
                  ,'REPAIR'
                  ,v_prreclist_id
                  ,v_dept_id
                  ,v_wcode_id
                  ,v_vendor_id
                  ,c1.app_date
                  ,v_part_id
                  ,'A'
                  ,c1.req_qty
                  ,c1.unit_price
                  ,c1.req_qty * c1.unit_price
              );
              
              -- 재고조정 pricedure 호출
              SP_PT_INSTOCK(v_comp_no, v_ptrechist_id);
              
              
              -- insert taptrepairlist
              -- insert taptrechist
              -- exec sp_pt_instock
           
          end if;
      
       
           update txptrepairlist set
                ifresult = 'Y'
                ,received_date = c1.received_date
            where rprapp_id = c1.rprapp_id
            ;
            
       else
           null;
           -- 이런경우는 에러임..
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
        and batpgm_no = 'PTREPAIRLIST'
    ;

end;
/
