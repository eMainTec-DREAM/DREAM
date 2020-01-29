CREATE OR REPLACE PROCEDURE SP_IF_UPD_BGTPLAN (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);

    cursor c_data is
        select
            c.accnt_no
            ,b.dept_no
            ,c.yyyymm
            ,a.plan_amt
            ,b.description
            ,to_char(sysdate,'yyyymmdd') as ifdate
        from TABGTDEPTPLAN a
               ,TADEPT b
               ,TABGTPLAN c
        where 1=1
            and a.comp_no = b.comp_no
            and a.dept_id = b.dept_id
            and a.comp_no = c.comp_no
            and a.bgt_plan_id = c.bgt_plan_id
            and c.upd_date >=  (select last_exe_date 
                                         from TABATPGM 
                                         where comp_no = v_comp_no 
                                                   and batpgm_no = 'TXBGTPLAN' 
                                        )
        ;

begin


    for c1 in c_data loop

        select count(*)
        into v_count
        from TXBGTPLAN
        where accnt_no = c1.accnt_no
            and dept_no = c1.dept_no
            and yyyymm = c1.yyyymm
        ;



        if v_count > 0 then

            update TXBGTPLAN set
                 plan_amt = c1.plan_amt
                 ,dept_name = c1.description
                 ,ifdate = c1.ifdate
            where accnt_no = c1.accnt_no
                and dept_no = c1.dept_no
                and yyyymm = c1.yyyymm
            ;

        else

            insert into TXBGTPLAN(bgtplan_id, yyyymm, accnt_no, dept_no, dept_name, plan_amt, ifdate)
            values(sqabgtplan_id.nextval,c1.yyyymm, c1.accnt_no, c1.dept_no, c1.description, c1.plan_amt, c1.ifdate)
            ;

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
