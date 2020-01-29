CREATE OR REPLACE PROCEDURE SP_IF_UPD_BGTACT(
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
   v_dept_id number(18);
   v_user_id number(18);
   
    cursor c_data is
        select 
             replace(gl_period,'-','') as gl_yyyymm
            ,amount    as gl_amt
            ,dept_code  as dept_no
            ,account_code as accnt_no
            ,vendor_code  as vendor_no
            ,to_char(creation_date,'yyyymmdd') as cre_date
        from oems_gl_exp_v@OEMS_CRP_DBLINK
        where 1=1
             and to_char(creation_date,'yyyymmdd') >= (select last_exe_date 
                                                                             from TABATPGM 
                                                                             where comp_no = v_comp_no 
                                                                                       and batpgm_no = 'TXBGTAMT' 
                                                                            )
        ;
                                
begin

    -- TXBGTAMT : ERP에서 수선비 실적(GL금액)을 가져오는 프로그램
    -- EXEC SP_IF_UPD_BGTACT('100','ADMIN')

   
    for c1 in c_data loop
    
        select count(*)
        into v_count
        from TXBGTACT
        where accnt_no = c1.accnt_no
            and dept_no = c1.dept_no
            and vendor_no = c1.vendor_no
            and gl_yyyymm = c1.gl_yyyymm
        ;
        
        
                
        if v_count > 0 then
        
            update TXBGTACT set
                 gl_amt = c1.gl_amt
            where accnt_no = c1.accnt_no
                and dept_no = c1.dept_no
                and vendor_no = c1.vendor_no
                and gl_yyyymm = c1.gl_yyyymm
            ;
       
        else
        
            insert into TXBGTACT(accnt_no, dept_no, vendor_no, gl_yyyymm, gl_amt)
            values(c1.accnt_no, c1.dept_no, c1.vendor_no, c1.gl_yyyymm, c1.gl_amt)
            ;
            
        end if;
        
        
        -- 예산계획 확인하고 실적누계값 갱신처리..
        select count(*)
        into v_count
        from tabgtplan
        where comp_no = v_comp_no
            and yyyymm = c1.gl_yyyymm
            and accnt_no = c1.accnt_no
       ;
       
       if v_count > 0 then
           select bgt_plan_id
            into v_bgt_plan_id
            from tabgtplan
            where comp_no = v_comp_no
                and yyyymm = c1.gl_yyyymm
                and accnt_no = c1.accnt_no
           ;
       else
           select sqabgt_plan_id.nextval 
           into v_bgt_plan_id
           from dual;
           
           insert into tabgtplan(comp_no, bgt_plan_id, yyyymm,accnt_no, plan_amt, upd_date, upd_by)
           values(v_comp_no, v_bgt_plan_id, c1.gl_yyyymm, c1.accnt_no, 0 , to_char(sysdate,'yyyymmdd'), c1.reg_by )
           ;
       end if;
       
        begin
            select dept_id
            into v_dept_id
            from tadept
            where comp_no = v_comp_no
                and dept_no = c1.dept_no
                and rownum = 1
            ;
        exception
            when others then
               v_dept_id := null;
        end;
        
        begin
            select user_id 
           into v_user_id
           from tauser 
           where comp_no = v_comp_no 
               and user_no = v_user_no 
               and rownum = 1
          ; 
        exception
            when others then
               v_user_id := null;
        end;
       
       
       
       
       
       select nvl(sum(aa.gl_amt),0)
       into v_gl_amt
       from TXBGTACT aa
       where accnt_no = c1.accnt_no
           and dept_no = c1.dept_no
           and gl_yyyymm = c1.gl_yyyymm  
       group by accnt_no, dept_no, gl_yyyymm
       ;
       
       
       select count(*)
        into v_count
        from tabgtdeptplan
        where comp_no = v_comp_no
            and bgt_plan_id = v_bgt_plan_id
            and dept_id = v_dept_id
       ;
       
       if v_count > 0 then
           update tabgtdeptplan set
                      gl_amt =   v_gl_amt
                     ,upd_date = to_char(sysdate,'yyyymmdd')
                     ,upd_by = v_user_id
           where comp_no = v_comp_no
                and bgt_plan_id = v_bgt_plan_id
                and dept_id = v_dept_id
            ;
       else
           insert into tabgtdeptplan(comp_no, bgt_dept_plan_id, bgt_plan_id, dept_id, plan_amt, act_amt, gl_amt, upd_date, upd_by)
           values(
               v_comp_no, sqabgt_dept_plan_id.nextval, v_bgt_plan_id, v_dept_id, 0, 0, v_gl_amt, to_char(sysdate,'yyyymmdd'), v_user_id
           );
           
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
        and batpgm_no = 'TXBGTAMT'
    ;
    
    
    
end;
/
