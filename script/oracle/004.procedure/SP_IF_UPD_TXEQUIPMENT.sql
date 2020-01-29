CREATE OR REPLACE PROCEDURE SP_IF_UPD_TXEQUIPMENT  (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
      
    cursor c_data is
        select 
             a.equip_id
            ,a.item_no
            ,a.description
            ,(select aa.full_desc 
               from taeqloc aa 
               where a.comp_no = aa.comp_no 
                   and a.eqloc_id = aa.eqloc_id
             ) as full_desc
            ,to_char(sysdate,'yyyymmdd') as ifdate
        from taequipment a
        where 1=1
            and (comp_no, equip_id) in (select comp_no, equip_id
                                                      from TAEQALTHIST 
                                                      where upd_date >= (select last_exe_date
                                                                                     from TABATPGM 
                                                                                     where comp_no = v_comp_no
                                                                                          and batpgm_no = 'TXEQUIPMENT' 
                                                                                    )
                                                       )

        ;
       
                                
begin

    
        
    for c1 in c_data loop
    
        -- create vendor table
        
        select count(*)
        into v_count
        from TXEQUIPMENT
        where equip_id = c1.equip_id
        ;
        
        if v_count > 0 then

            update TXEQUIPMENT set
                 description = c1.description
                ,full_desc = c1.full_desc
            where equip_id = c1.equip_id
            ;
            
        else
            
            
            insert into TXEQUIPMENT(equip_id, item_no, description, full_desc, ifdate)
            values(c1.equip_id, c1.item_no, c1.description, c1.full_desc, c1.ifdate)
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
        and batpgm_no = 'TXEQUIPMENT'
    ;
    
    
    
end;
/
