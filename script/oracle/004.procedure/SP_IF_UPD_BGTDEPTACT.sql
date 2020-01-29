CREATE OR REPLACE PROCEDURE SP_IF_UPD_BGTDEPTACT (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is
   v_count number(4);
   v_bgt_plan_id number(18);
   v_act_amt number(18,3);
   
    cursor c_data  is
        select
             a.rprapp_id
             ,a.app_doc_no
             ,(select aa.equip_id from taequipment aa where aa.comp_no = v_comp_no and aa.item_no = a.item_no and rownum=1) as equip_id
             ,(select aa.part_id from taparts aa where aa.comp_no = v_comp_no and aa.part_no = a.part_no and rownum=1) as part_id
             ,a.req_qty
             ,a.unit_price
             ,a.tot_amt
             ,a.accnt_no
             ,(select aa.dept_id from taemp aa where aa.comp_no = v_comp_no and aa.emp_no = a.reg_by and rownum=1) as dept_id
             ,(select aa.vendor_id from tavendor aa where aa.comp_no = v_comp_no and aa.vendor_no = a.vendor_no and rownum=1) as vendor_id
             ,(select aa.emp_id from taemp aa where aa.comp_no = v_comp_no and aa.emp_no = a.reg_by and rownum=1) as reg_by
             ,a.app_date
            ,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as received_date
        from TXPTREPAIRLIST a
        where 1=1
            and a.ifresult = 'N'
        ;

begin

    -- BGTDEPTACT : 노츠에서 수선비 품의서 결재완료된 금액을 가져오는 프로그램.
    -- EXEC SP_IF_UPD_BGTDEPTACT('100','ADMIN')


    for c1 in c_data loop

        select count(*)
        into v_count
        from TABGTDEPTACT
        where comp_no = v_comp_no
            and rprapp_id = c1.rprapp_id
        ;

        if v_count > 0 then
            null; -- if v_count > 0 then this case is error
        else

            insert into TABGTDEPTACT(
                 comp_no
                ,bgtdeptact_id
                ,accnt_no
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
                ,c1.accnt_no
                ,c1.vendor_id
                ,c1.app_date
                ,c1.equip_id
                ,c1.part_id
                ,c1.req_qty
                ,c1.unit_price
                ,c1.tot_amt
                ,c1.reg_by
                ,c1.rprapp_id
                ,c1.app_doc_no
            );

        end if;
        
        
        -- 예산계획 확인하고 실적누계값 갱신처리..
        select count(*)
        into v_count
        from tabgtplan
        where comp_no = v_comp_no
            and yyyymm = substr(c1.app_date,1,6)
            and accnt_no = c1.accnt_no
       ;
       
       if v_count > 0 then
           select bgt_plan_id
            into v_bgt_plan_id
            from tabgtplan
            where comp_no = v_comp_no
                and yyyymm = substr(c1.app_date,1,6)
                and accnt_no = c1.accnt_no
           ;
       else
           select sqabgt_plan_id.nextval 
           into v_bgt_plan_id
           from dual;
           
           insert into tabgtplan(comp_no, bgt_plan_id, yyyymm,accnt_no, plan_amt, upd_date, upd_by)
           values(v_comp_no, v_bgt_plan_id, substr(c1.app_date,1,6), c1.accnt_no, 0 , to_char(sysdate,'yyyymmdd'), c1.reg_by )
           ;
       end if;
       
       
       select nvl(sum(aa.tot_amt),0)
       into v_act_amt
       from tabgtdeptact aa
       where comp_no = v_comp_no
           and accnt_no = c1.accnt_no
           and dept_id = c1.dept_id
           and  app_date >=  substr(c1.app_date,1,6) || '01' 
           and app_date >= substr(c1.app_date,1,6) || '31' 
       group by comp_no, accnt_no, dept_id, substr(c1.app_date,1,6)
       ;
       
       
       select count(*)
        into v_count
        from tabgtdeptplan
        where comp_no = v_comp_no
            and bgt_plan_id = v_bgt_plan_id
            and dept_id = c1.dept_id
       ;
       
       if v_count > 0 then
           update tabgtdeptplan set
                     act_amt =   v_act_amt
                     ,upd_date = to_char(sysdate,'yyyymmdd')
                     ,upd_by = c1.reg_by
           where comp_no = v_comp_no
                and bgt_plan_id = v_bgt_plan_id
                and dept_id = c1.dept_id
            ;
       else
           insert into tabgtdeptplan(comp_no, bgt_dept_plan_id, bgt_plan_id, dept_id, plan_amt, act_amt, gl_amt, upd_date, upd_by)
           values(
               v_comp_no, sqabgt_dept_plan_id.nextval, v_bgt_plan_id, c1.dept_id, 0, v_act_amt, 0, to_char(sysdate,'yyyymmdd'), c1.reg_by
           );
           
       end if;
       
        update TXPTREPAIRLIST set
            ifresult = 'Y'
            ,received_date = c1.received_date
        where rprapp_id = c1.rprapp_id
        ;
        
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
        and batpgm_no = 'BGTDEPTACT'
    ;

end;
/
