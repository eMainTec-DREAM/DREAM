CREATE OR REPLACE PROCEDURE SP_IF_UPD_EQRUNLST  (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
   v_ctctr_id number(18);
   v_eqrunlst_id number(18);
      
    cursor c_data is
        select 
             to_char(PLAN_DATE,'yyyymmdd') as plan_date
            ,ORG_CODE
            ,DEPT_CODE as dept_no
            ,CP_CODE   as ctctr_no
            ,CP_NAME   as description
            ,USE_HOUR  as use_hour
            ,USE_MINUTE as use_minute
            ,OUT_QTY    as out_qty
        from OEMS_WORK_ORDER_V@OEMS_CRP_DBLINK a
        WHERE 1=1
            and PLAN_DATE >= (select last_exe_date 
                                         from TABATPGM 
                                         where comp_no = v_comp_no 
                                                   and batpgm_no = 'TAEQRUNLST' 
                                         ) 
                                                                                 
           
        ;
        
    cursor m_data( p_ctctr_id number) is    
        select 
            comp_no, eqloc_id, eqloc_no, description, ctctr_id 
        from taeqloc
        where comp_no = v_comp_no
            and ctctr_id = p_ctctr_id
        ;
        
                                        
begin

           
    for c1 in c_data loop
    
        select count(*)
        into v_count
        from tactctr
        where comp_no = v_comp_no
            and ctctr_no = c1.ctctr_no
        ;
        
        if v_count > 0 then
            
            
            select ctctr_id
            into v_ctctr_id
            from tactctr
            where comp_no = v_comp_no
                and ctctr_no = c1.ctctr_no
            ;
            
            for m1 in m_data( v_ctctr_id) loop            
                select count(*)
                into v_count
                from taeqrunlst
                where comp_no = v_comp_no
                    and eqloc_id = m1.eqloc_id
                    and plan_date = c1.plan_date
                ;
                
                if v_count > 0 then
                    update taeqrunlst set
                        use_hour = c1.use_hour
                        ,use_minute = c1.use_minute
                        ,out_qty = c1.out_qty
                    where comp_no = v_comp_no
                        and eqloc_id = m1.eqloc_id
                        and plan_date = c1.plan_date
                    ;
                else
                    select sqaeqrunlst_id.nextval
                    into v_eqrunlst_id
                    from dual; 
            
                    insert into taeqrunlst(comp_no, eqrunlst_id, eqloc_id, plan_date, use_hour,  use_minute, out_qty)
                    values(v_comp_no, v_eqrunlst_id, m1.eqloc_id, c1.plan_date, c1.use_hour, c1.use_minute, c1.out_qty)
                    ;
                end if;
                
            
            end loop;
            
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
        and batpgm_no = 'TAEQRUNLST'
    ;
    
    
    
end;
/
