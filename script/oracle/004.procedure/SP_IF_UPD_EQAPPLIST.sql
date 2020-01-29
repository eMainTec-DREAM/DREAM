CREATE OR REPLACE PROCEDURE SP_IF_UPD_EQAPPLIST     (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);

    cursor c_data is
        select
             a.eqapp_id
            ,a.eqapp_no
            ,a.categ
            ,a.req_date
            ,a.app_date
            ,a.comments
            ,a.act_date
            ,a.receiver
            ,a.referrer
            ,a.title
            ,a.contents
            ,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as received_date
        from TXEQAPPLIST a
        where 1=1
            and a.ifresult = 'N'
        ;

begin

    for c1 in c_data loop

        select count(*)
        into v_count
        from TEEQAPPLIST
        where comp_no = v_comp_no
            and eqapp_id = c1.eqapp_id
        ;

        if v_count > 0 then

            update TEEQAPPLIST set
                  categ = c1.categ
                  ,req_date = c1.req_date
                  ,app_date = c1.app_date
                  ,comments = c1.comments
                  ,act_date = c1.act_date
                  ,receiver = c1.receiver
                  ,referrer = c1.referrer
                  ,title = c1.title
                  ,contents = c1.contents
                  ,received_date = c1.received_date
            where comp_no = v_comp_no
                 and eqapp_id = c1.eqapp_id
            ;

        else

            insert into TEEQAPPLIST(
                 comp_no
                ,eqapp_id
                ,eqapp_no
                ,categ
                ,req_date
                ,app_date
                ,comments
                ,act_date
                ,receiver
                ,referrer
                ,title
                ,contents
                ,received_date
            )values(
                v_comp_no
                ,c1.eqapp_id
                ,c1.eqapp_no
                ,c1.categ
                ,c1.req_date
                ,c1.app_date
                ,c1.comments
                ,c1.act_date
                ,c1.receiver
                ,c1.referrer
                ,c1.title
                ,c1.contents
                ,c1.received_date
            );

        end if;
        
        
        update TXEQAPPLIST set
            ifresult = 'Y'
            ,received_date = c1.received_date
        where eqapp_id = c1.eqapp_id
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
        and batpgm_no = 'EQAPPLIST'
    ;

end;
/
