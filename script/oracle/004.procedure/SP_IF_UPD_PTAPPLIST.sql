CREATE OR REPLACE PROCEDURE SP_IF_UPD_PTAPPLIST  (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);

    cursor c_data is
        select
             a.ptapp_id
            ,a.ptapp_no
            ,a.referrer
            ,a.rec_date
            ,a.title
            ,a.eq_desc
            ,a.item_no
            ,a.eq_place
            ,a.req_date
            ,a.occur_date
            ,a.tbc_date
            ,a.tot_amt
            ,a.contents
            ,a.fail_desc
            ,a.comments
            ,a.req_by
            ,a.app1_by
            ,a.app2_by
            ,a.app3_by
            ,a.app4_by
            ,a.app5_by
            ,a.app6_by
            ,a.app7_by
            ,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as received_date
        from TXPTAPPLIST a
        where 1=1
            and a.ifresult = 'N'
        ;

begin


    for c1 in c_data loop

        select count(*)
        into v_count
        from TEPTAPPLIST
        where comp_no = v_comp_no
            and ptapp_id = c1.ptapp_id
        ;

        if v_count > 0 then

            update TEPTAPPLIST set
                 referrer = c1.referrer
                ,rec_date = c1.rec_date
                ,title = c1.title
                ,eq_desc = c1.eq_desc
                ,item_no = c1.item_no
                ,eq_place = c1.eq_place
                ,req_date = c1.req_date
                ,occur_date = c1.occur_date
                ,tbc_date = c1.tbc_date
                ,tot_amt = c1.tot_amt
                ,contents = c1.contents
                ,fail_desc = c1.fail_desc
                ,comments = c1.comments
                ,req_by = c1.req_by
                ,app1_by = c1.app1_by
                ,app2_by = c1.app2_by
                ,app3_by = c1.app3_by
                ,app4_by = c1.app4_by
                ,app5_by = c1.app5_by
                ,app6_by = c1.app6_by
                ,app7_by = c1.app7_by
                ,received_date = c1.received_date
            where comp_no = v_comp_no
                 and ptapp_id = c1.ptapp_id
            ;

        else

            insert into TEPTAPPLIST(
                 comp_no
                ,ptapp_id
                ,ptapp_no
                ,referrer
                ,rec_date
                ,title
                ,eq_desc
                ,item_no
                ,eq_place
                ,req_date
                ,occur_date
                ,tbc_date
                ,tot_amt
                ,contents
                ,fail_desc
                ,comments
                ,req_by
                ,app1_by
                ,app2_by
                ,app3_by
                ,app4_by
                ,app5_by
                ,app6_by
                ,app7_by
                ,received_date
            )values(
                v_comp_no
                ,c1.ptapp_id
                ,c1.ptapp_no
                ,c1.referrer
                ,c1.rec_date
                ,c1.title
                ,c1.eq_desc
                ,c1.item_no
                ,c1.eq_place
                ,c1.req_date
                ,c1.occur_date
                ,c1.tbc_date
                ,c1.tot_amt
                ,c1.contents
                ,c1.fail_desc
                ,c1.comments
                ,c1.req_by
                ,c1.app1_by
                ,c1.app2_by
                ,c1.app3_by
                ,c1.app4_by
                ,c1.app5_by
                ,c1.app6_by
                ,c1.app7_by
                ,c1.received_date
            );

        end if;
        
        
        update TXPTAPPLIST set
            ifresult = 'Y'
            ,received_date = c1.received_date
        where ptapp_id = c1.ptapp_id
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
        and batpgm_no = 'PTAPPLIST'
    ;

end;
/
