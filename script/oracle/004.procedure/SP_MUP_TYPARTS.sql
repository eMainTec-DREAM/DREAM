CREATE OR REPLACE PROCEDURE "SP_MUP_TYPARTS" (
    v_comp_no varchar2 default '100'
) is

-- truncate table TYPARTS


-- truncate table TAPARTS
-- truncate table TAPTUSEDDEPT
-- truncate table taptstock
-- truncate table taptsaftystock
-- exec SP_MUP_TYPARTS('120')

-- is_inpart is sap interface check, then 'Y' or 'N' update


   v_count number(4);
   v_part_id number(18);
   v_wcode_id number(18);
   v_dept_id number(18);
   v_plf_type varchar2(20);
   v_pco_type varchar2(20);
   v_full_desc varchar2(1024);
    v_remark varchar2(512);
    v_cdsysm_id number(21);
    v_ord_no number(21);
   
      
    cursor c_group_code is
        select 
               trim(part_group_code) as part_group_code
              ,trim(part_group_name) as part_group_name
        from TYPARTS a
        where 1=1
             and trim(part_group_code) is not null
             and trim(part_group_name) is not null  
        group by  trim(part_group_code), trim(part_group_name)
        ;
                
                
    cursor c_excel_data is
                select 
                       sn
                      ,trim(part_no) as part_no
                      ,trim(description) as description
                      ,trim(pt_size) as pt_size
                      ,trim(pt_kind) as pt_kind
                      ,trim(pt_type) as pt_type
                      ,trim(uom) as uom
                      ,trim(part_group_code) as part_group_code
                      ,trim(part_group_name) as part_group_name
                      ,trim(var_class_code) as var_class_code
                      ,trim(var_class_name) as var_class_name
                      ,nvl(last_price,0) as last_price
                      ,nvl(min_stock_qty,0) as min_stock_qty
                      ,nvl(cur_stock_qty,0) as cur_stock_qty 
                      ,nvl(under_min_qty,0) as under_min_qty 
                      ,nvl(total_amount,0) as total_amount 
                      ,trim(localization) as localization
                      ,trim(remark) as remark
                      ,trim(supplier) as supplier
                      ,trim(producer) as producer
                      ,to_char(sysdate,'yyyymmdd') as UPD_DATE
                from TYPARTS a
                where 1=1
                order by sn
                ;
    
begin

    delete from tacdsysm
    where list_type = 'PART_GROUP';
    
    select sqacdsysm_id.nextval 
    into v_cdsysm_id 
    from dual;
    
    insert into tacdsysm(cdsysm_id, list_type, description, remark, is_use)
    values(v_cdsysm_id, 'PART_GROUP', 'Part Group','' ,'Y');
    
    delete from tacdsysd
    where list_type = 'PART_GROUP';

   v_ord_no := 0;
    for c1 in c_group_code loop
        v_ord_no := v_ord_no + 1;
    
        insert into tacdsysd(cdsysd_id, cdsysm_id, cdsysd_no, description, ord_no, is_use, list_type)
        values(sqacdsysd_id.nextval, v_cdsysm_id, c1.part_group_code, c1.part_group_name, to_char(v_ord_no,'000'), 'Y','PART_GROUP')
        ;
    
    end loop;
    
    
    for c1 in c_excel_data loop
    
               select count(*)
               into v_count
               from TAPARTS
               where comp_no = v_comp_no
                   and part_no = c1.part_no
               ;
               
               v_full_desc := c1.description || (case when c1.pt_size is not null then ',' || c1.pt_size else '' end);
               
               
               
               if v_count > 0 then 
               
                  select part_id
                  into v_part_id
                  from TAPARTS 
                  where comp_no = v_comp_no
                   and part_no = c1.part_no
                   ;
                   
                   update TAPARTS set
                        description = c1.description
                       ,pt_size = c1.pt_size
                       ,uom = c1.uom
                       ,full_desc = v_full_desc
                       ,maker = c1.producer
                       ,model = c1.pt_kind
                       ,last_price = c1.last_price
                       ,seller = c1.supplier
                       ,is_use = 'Y'
                       ,remark = c1.remark
                       ,upd_date = c1.upd_date
                       ,kind = c1.pt_type
                       ,var_class = c1.var_class_name
                       ,part_categ = 'SPPT'
                       ,is_serial_part = 'N'
                       ,part_group = c1.part_group_code
                   where comp_no = v_comp_no
                       and part_no = c1.part_no
                   ;
               else
                   select sqapart_id.nextval
                   into v_part_id
                   from dual;
                   
                   
                   insert into TAPARTS(
                      comp_no, part_id, part_no
                      ,description,pt_size,uom
                       ,full_desc,maker,model,last_price
                       ,seller,is_use,remark,upd_date
                       ,kind,var_class,part_categ,is_serial_part
                       ,part_group
                       
                  )values(
                      v_comp_no, v_part_id, c1.part_no
                      ,c1.description,c1.pt_size,c1.uom
                      ,v_full_desc,c1.producer,c1.pt_kind,c1.last_price
                       ,c1.supplier,'Y',c1.remark,c1.upd_date
                       ,c1.pt_type,c1.var_class_name,'SPPT','N'
                       ,c1.part_group_code
                  )
                  ;
                  
               end if;
               
               select count(*)
               into v_count
               from tawarehouse
               where wh_categ = 'PART'
               ;
               
               if v_count > 0 then

                   select wcode_id
                   into v_wcode_id
                   from tawarehouse
                   where wh_categ = 'PART'
                       and rownum = 1
                   ;
                   
                   select count(*)
                   into v_count 
                   from taptstock
                   where comp_no = v_comp_no
                       and wcode_id = v_wcode_id
                       and part_id = v_part_id
                       and part_grade = 'B'
                  ;
                  
                  if v_count > 0 then
                       update taptstock set
                            stock_qty = c1.cur_stock_qty
                           ,bin_no = c1.localization
                           , unit_price = c1.last_price                
                       where comp_no = v_comp_no
                           and wcode_id = v_wcode_id
                           and part_id = v_part_id
                           and part_grade = 'B'
                      ;
                  else
                       insert into taptstock(
                           comp_no, wcode_id, part_id, part_grade, stock_qty, bin_no, unit_price
                       )values(
                           v_comp_no, v_wcode_id, v_part_id, 'B', c1.cur_stock_qty, c1.localization, c1.last_price
                       );
                   
                  end if;
                   
                  
                  select count(*)
                   into v_count 
                   from taptsaftystock
                   where comp_no = v_comp_no
                       and wcode_id = v_wcode_id
                       and part_id = v_part_id
                  ;
                  
                  if v_count > 0 then
                      update taptsaftystock set
                          safty_qty = c1.min_stock_qty
                          , max_safty_qty = c1.min_stock_qty
                       where comp_no = v_comp_no
                           and wcode_id = v_wcode_id
                           and part_id = v_part_id
                      ;
                  else
                       insert into taptsaftystock(comp_no, wcode_id, part_id, safty_qty, max_safty_qty
                       )values(
                           v_comp_no, v_wcode_id, v_part_id, c1.min_stock_qty, c1.min_stock_qty
                       );
                  end if;
                  
               end if;
               
    end loop;
    
    
    
end;
/
