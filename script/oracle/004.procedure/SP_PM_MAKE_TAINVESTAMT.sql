CREATE OR REPLACE procedure SP_PM_MAKE_TAINVESTAMT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                               number(4); 
    v_mtlnpoint_id                     number(18);
    
    v_bd_monthly_tvalue_eq             number(18,3);  
    v_bdh_monthly_tvalue_eq             number(18,3);  
    v_mttr_monthly_tvalue_eq          number(15);
    v_mtbf_monthly_tvalue_eq         number(15);
    
    v_bd_monthly_tvalue             number(18,3);  
    v_bdh_monthly_tvalue             number(18,3);  
    v_mttr_monthly_tvalue          number(15);
    v_mtbf_monthly_tvalue         number(15);
    v_stock_monthly_tvalue        number(15);
    v_pvalue                           number(18,3);   
 
    -- delete from TAMTLNPOINT
    -- exec SP_PM_MAKE_TAINVESTAMT('120','ADMIN'); 

    cursor c_data is
        select 
           comp_no, eqloc_id, plant
        from taeqloc 
        where 1=1
            and comp_no = v_comp_no
            and is_use = 'Y'
            and is_kpi = 'Y'
        order by plant, eqloc_no
    ;
    
    cursor c_month is
        select  tmonth
        from tamonth 
        where 1=1
            --and  tmonth >= '201608'
            and  tmonth >= to_char(sysdate,'yyyymm')
            and tmonth <= to_char(add_months(sysdate,1),'yyyymm')
        order by 1
        ;      
        
     cursor c_point is
        select cdsysd_no
        from tacdsysd
        where list_type = 'MTLNPOINT'
           and is_use = 'Y'
       ;   
    
       cursor c_warehouse is
        select wcode_id
        from tawarehouse 
        where comp_no = v_comp_no
            and is_use = 'Y' 
            and wh_categ = 'PART'
       ;
        
begin

   begin
         v_bd_monthly_tvalue := 0.3;
         select value$ into v_bd_monthly_tvalue
         from taconfig
         where name='BD_MONTHLY_TVALUE' 
         ;
    exception
        when others then
           v_bd_monthly_tvalue := 0.3; 
    end;
    
   begin
         v_bd_monthly_tvalue_eq := 0.3;
         select value$ into v_bd_monthly_tvalue_eq
         from taconfig
         where name='BD_MONTHLY_TVALUE_EQ' 
         ;
    exception
        when others then
           v_bd_monthly_tvalue_eq := 0.3; 
    end;
    begin
         v_bdh_monthly_tvalue := 1.0;
         select value$ into v_bdh_monthly_tvalue
         from taconfig
         where name='BDH_MONTHLY_TVALUE' 
         ;
    exception
        when others then
           v_bdh_monthly_tvalue := 1.0; 
    end;
    
   begin
         v_bdh_monthly_tvalue_eq :=2.0;
         select value$ into v_bdh_monthly_tvalue_eq
         from taconfig
         where name='BDH_MONTHLY_TVALUE_EQ' 
         ;
    exception
        when others then
           v_bd_monthly_tvalue_eq := 2.0; 
    end;
    begin
         v_mttr_monthly_tvalue := 20;
         select value$ into v_mttr_monthly_tvalue
         from taconfig
         where name='MTTR_MONTHLY_TVALUE' 
         ;
    exception
        when others then
           v_mttr_monthly_tvalue := 20; 
    end;
    
    begin
         v_mttr_monthly_tvalue_eq := 20;
         select value$ into v_mttr_monthly_tvalue_eq
         from taconfig
         where name='MTTR_MONTHLY_TVALUE_EQ' 
         ;
    exception
        when others then
           v_mttr_monthly_tvalue_eq := 20; 
    end;
   
   begin
         v_mtbf_monthly_tvalue := 20000;
         select value$ into v_mtbf_monthly_tvalue
         from taconfig
         where name='MTBF_MONTHLY_TVALUE' 
         ;
    exception
        when others then
           v_mtbf_monthly_tvalue := 20000; 
    end;   
   
   begin
         v_mtbf_monthly_tvalue_eq := 20000;
         select value$ into v_mtbf_monthly_tvalue_eq
         from taconfig
         where name='MTBF_MONTHLY_TVALUE_EQ' 
         ;
    exception
        when others then
           v_mtbf_monthly_tvalue_eq := 20000; 
    end;      
    
    begin
         v_stock_monthly_tvalue := 6000000;
         select value$ into v_stock_monthly_tvalue
         from taconfig
         where name='STOCK_MONTHLY_TVALUE' 
         ;
    exception
        when others then
           v_stock_monthly_tvalue := 6000000; 
    end;        
    
    for c1 in c_data loop
        
        for c2 in c_month loop
        
             for c3 in c_point loop
             
                 select count(*)
                 into v_count
                 from TAMTLNPOINT
                 where comp_no = v_comp_no
                     and plant =  c1.plant
                     and yyyymm = c2.tmonth
                     and eqloc_id =  c1.eqloc_id
                     and mtlnpoint = c3.cdsysd_no
                 ;
                 
                 if v_count = 0 then
                     select sqamtlnpoint_id.nextval 
                     into v_mtlnpoint_id
                     from dual;
                
                      if c3.cdsysd_no = 'BD' then
                         v_pvalue := v_bd_monthly_tvalue;
                     elsif c3.cdsysd_no = 'BD_HOURS' then
                         v_pvalue := v_bdh_monthly_tvalue;
                     elsif c3.cdsysd_no = 'BD_HOURS_EQ' then
                         v_pvalue := v_bdh_monthly_tvalue_eq;
                     elsif c3.cdsysd_no = 'MTTR' then
                         v_pvalue := v_mttr_monthly_tvalue;
                     elsif c3.cdsysd_no = 'MTBF' then
                         v_pvalue := v_mtbf_monthly_tvalue;
                     elsif c3.cdsysd_no = 'BD_EQ' then
                     v_pvalue := v_bd_monthly_tvalue_eq;
                     elsif c3.cdsysd_no = 'MTTR_EQ' then
                     v_pvalue := v_mttr_monthly_tvalue_eq;
                     elsif c3.cdsysd_no = 'MTBF_EQ' then
                     v_pvalue := v_mtbf_monthly_tvalue_eq;
                     else
                         v_pvalue := 0;
                     end if;
                     
                    insert into TAMTLNPOINT (comp_no, mtlnpoint_id, plant, yyyymm, eqloc_id, mtlnpoint, pvalue, avalue)
                    values(
                               v_comp_no, v_mtlnpoint_id, c1.plant, c2.tmonth, c1.eqloc_id, c3.cdsysd_no, v_pvalue, 0
                    );
                 end if;
                 
             
             end loop;
             

            /*재고목표금액은 창고별로 테이블이 다름.*/
             for c4 in c_warehouse loop
                 
                 select count(*)
                 into v_count 
                 from TAPTMSTOCKPLAN
                 where comp_no = v_comp_no
                     and wcode_id = c4.wcode_id
                     and yyyymm = c2.tmonth
                 ;
                 
                 if v_count = 0 then
                     insert into TAPTMSTOCKPLAN (comp_no, wcode_id, yyyymm, stock_plan_amt, plant )
                     values(v_comp_no, c4.wcode_id, c2.tmonth,v_stock_monthly_tvalue, c1.plant);
                 end if;
                 
                 
                 
             end loop;
            
        end loop; 
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
        and batpgm_no = 'TAINVESTAMT'
    ;
    
    
    
end;
/
