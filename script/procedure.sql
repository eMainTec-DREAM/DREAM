/*2016-05-19 박철완. 작업결과에서 점검항목 만들때 표준번호 가지고 있기..*/
procedure recompile SP_PM_MAKE_WORKORDER





/** 2016-12-15 김정우 금형 프로시저 수정 */
CREATE OR REPLACE PROCEDURE SP_PM_MAKE_MOLDS(
     v_comp_no     IN varchar2          
    ,v_user_no     IN varchar2  
) IS
    v_count             number(4);   
    v_user_id             number(18);
    
    -- exec SP_PM_MAKE_MOLDS('100','ADMIN')
    
    --삭제할 wkor_id 목록
    CURSOR c_wkor_id IS
                SELECT wkor_id, comp_no
                FROM TAWOEQUIP x
                WHERE 1=1 
                AND x.wkor_id IN (SELECT wkor_id
                                             FROM TAWORKORDER 
                                             WHERE comp_no  = v_comp_no
                                             AND wo_status != 'C'
                                             AND wo_gen_type='PMPLAN'
                                             AND wkor_date > TO_CHAR(SYSDATE-1,'YYYYMMDD') )
                AND x.equip_id in (SELECT equip_id
                                            FROM taequipment
                                            WHERE comp_no = v_comp_no
                                            AND eqctg_type='MD'
                                            AND eq_status='R')
                AND x.comp_no = v_comp_no
            ;

    --삭제할 pmsched_id 목록 
    CURSOR c_pmsched_id IS
                SELECT pmsched_id, comp_no
                FROM TAPMSCHED
                WHERE comp_no = v_comp_no
                AND pmsched_status !='C'
                AND plan_date > TO_CHAR(SYSDATE-1,'YYYYMMDD')
                AND pm_id IN (SELECT pm_id 
                                        FROM TAPMEQUIP
                                        WHERE comp_no =v_comp_no
                                        AND equip_id IN  (select equip_id
                                                                FROM taequipment
                                                                where comp_no = v_comp_no
                                                                and eqctg_type='MD'
                                                                and eq_status='R')
                                        )
                 ;
                 
    --현재 Line에 올라와 있는 금형 설비의 PM List
    CURSOR c_pmlist IS
                SELECT pm_id, comp_no
                FROM TAPMLST
                WHERE comp_no = v_comp_no
                AND pm_id IN (SELECT pm_id 
                                        FROM TAPMEQUIP
                                        WHERE comp_no = v_comp_no
                                        AND equip_id IN  (SELECT equip_id
                                                                FROM taequipment
                                                                WHERE comp_no = v_comp_no
                                                                AND eqctg_type='MD'
                                                                AND eq_status='R'
                                                                AND eqstrloc_no IN (SELECT cdusrd_no
                                                                                            FROM TACDUSRD
                                                                                            WHERE cdusrm_id = (SELECT cdusrm_id
                                                                                                                            FROM TACDUSRM
                                                                                                                            WHERE 1=1
                                                                                                                            AND comp_no = v_comp_no
                                                                                                                            AND dir_type='EQSTRLOC_NO'
                                                                                                                            )
                                                                                              AND  cdusrd_no not in ('STOCK','IR','ER')
                                                                                              AND IS_USE = 'Y'
                                                                                              )
                                                                   )
                                        )
                 ;
  
BEGIN
    --W/O 삭제 
    FOR c1 IN c_wkor_id LOOP
    
            -- W/O 삭제
            DELETE FROM TAWORKORDER
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O FAIL 삭제
            DELETE FROM TAWOFAIL
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O CRAFT 삭제
            DELETE FROM TAWOCRAFT
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O PARTS 삭제
            DELETE FROM TAWOPARTS
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O POINT 삭제
            DELETE FROM TAWOPOINT
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O WOEQUIP 삭제
            DELETE FROM TAWOEQUIP
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O 긴급출고 상태 변경
            UPDATE TAPTEMGISSLIST SET
                        ptemg_task_status = 'W',
                        wkor_id = ''
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            
    END LOOP;
    
    --PM SCHED 삭제 
    FOR c2 IN c_pmsched_id LOOP
    
            DELETE FROM TAPMSCHED
            WHERE 1=1
            AND comp_no = c2.comp_no
            AND pmsched_id = c2.pmsched_id
            ;
    
    END LOOP;
    
    -- 금형설비의 PM 마스터의  is_active값 모두 N , Last sch Date 값 변경
    UPDATE TAPMLST
    SET is_active = 'N'
          ,last_sch_date = TO_CHAR(SYSDATE-1,'YYYYMMDD')
    WHERE comp_no = v_comp_no
    AND pm_id IN (SELECT pm_id 
                            FROM TAPMEQUIP
                            WHERE comp_no = v_comp_no
                            AND equip_id IN  (SELECT equip_id
                                                    FROM taequipment
                                                    WHERE comp_no = v_comp_no
                                                    AND eqctg_type='MD'
                                                    AND eq_status='R')
                            )
    ;
    
    -- 금형설비의 PM 마스터의 next sch Date 값 변경
    UPDATE TAPMLST
    SET next_sch_date =  (CASE 
                                 WHEN period_type = 'D' THEN TO_CHAR(TO_DATE(last_sch_date,'yyyymmdd') + (cycle * 1 ),'yyyymmdd')
                                 WHEN period_type = 'W' THEN TO_CHAR(TO_DATE(last_sch_date,'yyyymmdd') + (7 * cycle * 1 ),'yyyymmdd') 
                                 WHEN period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(last_sch_date,'yyyymmdd'),cycle * 1),'yyyymmdd') 
                                 WHEN period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(last_sch_date,'yyyymmdd'),12 * cycle * 1),'yyyymmdd')
                            END)
    WHERE comp_no = v_comp_no
    AND pm_id IN (SELECT pm_id 
                            FROM TAPMEQUIP
                            WHERE comp_no = v_comp_no
                            AND equip_id IN  (SELECT equip_id
                                                    FROM taequipment
                                                    WHERE comp_no = v_comp_no
                                                    AND eqctg_type='MD'
                                                    AND eq_status='R')
                            )
    ;
    
    --Line에 올라와있는 금형설비의 PM 마스터의 is_active값 Y로 변경 
    FOR c3 IN c_pmlist LOOP
    
            UPDATE TAPMLST
            SET is_active = 'Y'
            WHERE 1=1
            AND comp_no = c3.comp_no
            AND pm_id = c3.pm_id
            ;
    
    END LOOP;
    
    --SP_PM_MAKE_SCHEDULE_BYALL 프로시저 실행
    SP_PM_MAKE_SCHEDULE_BYALL(v_comp_no,v_user_no);
    --SP_PM_MAKE_WORKORDER 프로시저 실행
    SP_PM_MAKE_WORKORDER(v_comp_no,v_user_no);
    
END;
/




/** 2017-01-06 김정우 요일별 설정 프로시저 수정 */
CREATE OR REPLACE procedure SP_PM_MAKE_TALNWRKTIME(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                               number(4); 
    v_dow_count                       number(4); 
    v_dow_dtime                       number(18); 
    v_dow_ntime                       number(18); 
    v_dow_etime                       number(18); 
    v_lnwrktime_id                     number(18);
 
    
    -- exec SP_PM_MAKE_TALNWRKTIME('100','ADMIN'); 

    cursor c_data is
        select 
           comp_no, eqloc_id, plant
           ,nvl(dtime,0) as dtime
           ,nvl(ntime,0) as ntime
           ,nvl(etime,0) as etime
        from taeqloc 
        where 1=1
            and comp_no = v_comp_no
            and is_operation = 'Y'
            and is_use = 'Y'
        order by plant, eqloc_no
    ;
    
    cursor c_day(p_plant varchar2) is
        select cal_date as tday
        from TAWRKCALENDAR
        where comp_no = v_comp_no
            and plant = p_plant
            and is_work = 'Y'
            and cal_date >= to_char(sysdate,'yyyymmdd')
            --and cal_date >='20160829'
            and cal_date <= to_char(add_months(sysdate,1),'yyyymmdd')
       ;
    
        
begin

  
        
        

    for c1 in c_data loop
        
        for c2 in c_day(c1.plant) loop

            select count(*)
            into v_count
            from TALNWRKTIME
            where comp_no = v_comp_no
                and plant = c1.plant
                and eqloc_id = c1.eqloc_id
                and wrk_date = c2.tday
            ;
            
            select count(*)
            into v_dow_count
            from TAEQLOCDOWRUN
            where 1=1
            and comp_no = v_comp_no
            and eqloc_id = c1.eqloc_id
            and is_use='Y'
            and dow = (   select dow
                                 from taday
                                 where tday=c2.tday    )
            ;
            
            if v_count = 0 then
                 select sqalnwrktime_id.nextval 
                 into v_lnwrktime_id
                 from dual;
                if v_dow_count > 0 then 
                        select nvl(dtime,0), nvl(ntime,0), nvl(etime,0)
                        into v_dow_dtime,v_dow_ntime,v_dow_etime
                        from TAEQLOCDOWRUN
                        where 1=1
                        and comp_no = v_comp_no
                        and eqloc_id = c1.eqloc_id
                        and is_use='Y'
                        and dow = (   select dow
                                             from taday
                                             where tday=c2.tday    )
                        ;
                
                    insert into TALNWRKTIME (comp_no, lnwrktime_id, plant, wrk_date, eqloc_id, dtime, ntime, etime )
                    values(
                           v_comp_no, v_lnwrktime_id, c1.plant, c2.tday, c1.eqloc_id, v_dow_dtime,v_dow_ntime,v_dow_etime
                        );
                        
                else
                
                    insert into TALNWRKTIME (comp_no, lnwrktime_id, plant, wrk_date, eqloc_id, dtime, ntime, etime )
                    values(
                           v_comp_no, v_lnwrktime_id, c1.plant, c2.tday, c1.eqloc_id, c1.dtime, c1.ntime, c1.etime
                        );
                        
                end if;
                
                
            end if;
            
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
        and batpgm_no = 'TALNWRKTIME'
        ;
end;
/


/** 2017-01-11 보전목표 설정 프로시저 수정 */
CREATE OR REPLACE procedure SP_PM_MAKE_TAINVESTAMT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                               number(4); 
    v_mtlnpoint_id                     number(18);
    
    v_bd_monthly_tvalue_eq             number(18,3);  
    v_mttr_monthly_tvalue_eq          number(15);
    v_mtbf_monthly_tvalue_eq         number(15);
    
    v_bd_monthly_tvalue             number(18,3);  
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



/** 2017-01-11 KPI 집계 값 변경 */

CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIDLOCDN(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_d_stoptime                        number(18);
    v_d_repairtime                      number(18);
    v_d_stopfreq                         number(18);
    v_d_repairfreq                      number(18);
    v_m_stoptime                        number(18);
    v_m_repairtime                      number(18);
    v_m_stopfreq                         number(18);
    v_m_repairfreq                      number(18);
    
    
    -- exec SP_KPI_MAKE_TAKPIDLOCDN('100','ADMIN'); 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    cursor c_lnwrktime is
        select 
            a.comp_no    
            ,a.plant
            ,a.wrk_date
            ,a.eqloc_id
            ,a.dtime  as d_worktime_ds
            ,a.ntime  as d_worktime_es -- TALNWRKTIME에서 ntime이 2Shift 
            ,a.etime  as d_worktime_ns -- TALNWRKTIME에서 etime이 3Shift 
            ,nvl(a.dtime,0) + nvl(a.etime,0)+ nvl(a.ntime,0) as d_worktime
            ,(select nvl(sum(aa.dtime),0) +nvl(sum(aa.etime),0) + nvl(sum(aa.ntime),0) 
              from TALNWRKTIME aa
              where a.comp_no = aa.comp_no
                  and a.plant = aa.plant
                  and a.eqloc_id = aa.eqloc_id
                  and aa.wrk_date >= to_char(trunc(to_date(a.wrk_date,'yyyymmdd'),'mm'),'yyyymmdd')
                  and aa.wrk_date <= a.wrk_date
              ) as m_work_time
        from TALNWRKTIME a
        where 1=1
            and a.comp_no = v_comp_no
            and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= to_char(sysdate,'yyyymmdd')
       ;
       
     /*고장시간 집계대상일자 및 라인*/
     cursor  c_eqloc_list is
         select 
                 comp_no
                 ,plant
                 ,wrk_date
                 ,eqloc_id
            from TALNWRKTIME a
            where 1=1
                and a.comp_no = v_comp_no
                and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= to_char(sysdate,'yyyymmdd')
            group by comp_no, plant, wrk_date, eqloc_id
            order by 1,2,3,4
           ;
        
begin

    
        
   for c1 in c_lnwrktime loop
   
       --  가동시간 Setting 
       select count(*)
       into v_count
       from takpidlocdn
       where comp_no = c1.comp_no
           and plant = c1.plant
           and wrk_date = c1.wrk_date
           and eqloc_id = c1.eqloc_id
       ;
       
       if v_count > 0 then
           update takpidlocdn set
                    d_worktime_ds = c1.d_worktime_ds
                    ,d_worktime_es = c1.d_worktime_es
                    ,d_worktime_ns = c1.d_worktime_ns
                    ,d_worktime = c1.d_worktime
                    ,m_worktime = c1.m_work_time
            where comp_no = c1.comp_no
                and plant = c1.plant
                and wrk_date = c1.wrk_date
                and eqloc_id = c1.eqloc_id
            ;
       else
           insert into takpidlocdn( comp_no, plant, wrk_date, eqloc_id, d_worktime_ds, d_worktime_es, d_worktime_ns, d_worktime, m_worktime
           ) values (
               c1.comp_no, c1.plant, c1.wrk_date,  c1.eqloc_id, c1.d_worktime_ds,c1.d_worktime_es, c1.d_worktime_ns, c1.d_worktime,c1.m_work_time
           );
       end if;
       
   end loop;
   
   for c1 in c_eqloc_list loop
   
        -- 일일 데이타 추출..
        select 
                 NVL(sum(d_stoptime),0)
                ,NVL(sum(d_repairtime),0)
                ,NVL(sum(d_stopfreq),0)
                ,NVL(sum(d_repairfreq),0)
        into v_d_stoptime, v_d_repairtime, v_d_stopfreq, v_d_repairfreq
        from (SELECT
                     a.comp_no
                    ,e.plant
                    ,a.wkor_date as wrk_date
                    ,d.eqloc_id as eqloc_id
                    ,SUM (b.lndn_work_time) as d_stoptime
                    ,SUM (b.eqdn_work_time) as d_repairtime
                    ,SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) AS d_stopfreq
                    ,SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) AS d_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE     1 = 1
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date = c1.wrk_date
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id
            ) a
        where 1=1
            and comp_no = c1.comp_no
            and plant = c1.plant
            and wrk_date = c1.wrk_date
            and eqloc_id in (select eqloc_id
                                    from taeqloc
                                    where 1=1
                                    start with eqloc_id = c1.eqloc_id
                                    connect by prior  eqloc_id = p_eqloc_id
                                   )
         ;
         
         --//1일 ~ 현재일까지 누적 데이타 추출...
          select NVL(sum(a.d_stoptime),0)
                  ,NVL(sum(a.d_repairtime),0)
                  ,NVL(sum(a.d_stopfreq),0)
                  ,NVL(sum(a.d_repairfreq),0)
          into v_m_stoptime, v_m_repairtime, v_m_stopfreq, v_m_repairfreq
          from (SELECT
                     a.comp_no
                    ,e.plant
                    ,a.wkor_date as wrk_date
                    ,d.eqloc_id as eqloc_id
                    ,SUM (b.lndn_work_time) as d_stoptime
                    ,SUM (b.eqdn_work_time) as d_repairtime
                    ,SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) AS d_stopfreq
                    ,SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) AS d_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE     1 = 1
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= to_char(trunc(to_date(c1.wrk_date,'yyyymmdd'),'mm'),'yyyymmdd')
                    AND a.wkor_date <= c1.wrk_date
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, a.wkor_date, d.eqloc_id
            ) a
          where comp_no = c1.comp_no
              and plant = c1.plant
              and a.eqloc_id in (select eqloc_id
                                        from taeqloc
                                        start with eqloc_id =c1.eqloc_id
                                        connect by prior  eqloc_id = p_eqloc_id
                                       )
          ;
          
          update takpidlocdn set
                     d_stoptime = v_d_stoptime
                    ,d_repairtime = v_d_repairtime
                    ,d_stopfreq = v_d_stopfreq
                    ,d_repairfreq = v_d_repairfreq
                    ,m_stoptime = v_m_stoptime
                    ,m_repairtime = v_m_repairtime
                    ,m_stopfreq = v_m_stopfreq
                    ,m_repairfreq = v_m_repairfreq
                    ,mtbf = d_worktime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mtbf_eq = d_worktime/decode(v_d_repairfreq,0,1,v_d_repairfreq)
                    ,mttr = v_m_stoptime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mttr_eq = v_m_repairtime/decode(v_d_repairfreq,0,1,v_d_repairfreq)
                    ,mtba = d_worktime/decode(v_d_repairfreq,0,1,v_d_repairfreq)
                    ,ratio =trunc( (v_m_stoptime/decode(d_worktime,0,1,d_worktime)) * 100,2) 
            where comp_no = c1.comp_no
                and plant = c1.plant
                and wrk_date = c1.wrk_date
                and eqloc_id = c1.eqloc_id
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
        and batpgm_no = 'TAKPIDLOCDN'
    ;
    
    
    
end;
/


CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMLOCCTGDN(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_m_stoptime                       number(18);
    v_m_repairtime                     number(18);
    v_m_stopfreq                        number(18);
    v_m_repairfreq                      number(18);
    
    -- exec SP_KPI_MAKE_TAKPIMLOCCTGDN('100','ADMIN'); 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    cursor c_lnctgwrktime is
        select 
            a.comp_no    
            ,a.plant
            ,substr(a.wrk_date,1,6) as yyyymm
            ,a.eqloc_id
            ,b.eqctg_id
            ,sum(a.dtime) + sum(a.etime) + sum(a.ntime) as m_worktime
        from TALNWRKTIME a
               ,taeqctg b
        where 1=1
            and a.comp_no = b.comp_no
            and b.lvl = 1
            and b.is_use = 'Y'
            and a.comp_no = v_comp_no
            and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= to_char(sysdate,'yyyymmdd')
        group by a.comp_no, a.plant, substr(a.wrk_date,1,6), a.eqloc_id, b.eqctg_id
       ;
       
     /*고장시간 집계대상일자 및 라인*/
     cursor  c_eqlocctg_list is
         select 
                 a.comp_no
                 ,a.plant
                 ,substr(a.wrk_date,1,6) as yyyymm
                 ,a.eqloc_id
                 ,b.eqctg_id
            from TALNWRKTIME a
                  ,taeqctg b
            where 1=1
                 and a.comp_no = b.comp_no
                 and b.lvl = 1
                 and b.is_use = 'Y'
                and a.comp_no = v_comp_no
                and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= to_char(sysdate,'yyyymmdd')
            group by a.comp_no, a.plant, substr(a.wrk_date,1,6) , a.eqloc_id,b.eqctg_id
            order by 1,2,3,4,5
           ;
        
begin

    
        
   for c1 in c_lnctgwrktime loop
   
       --  가동시간 Setting 
       select count(*)
       into v_count
       from takpimlocctgdn
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and eqloc_id = c1.eqloc_id
           and eqctg_id = c1.eqctg_id
       ;
       
       if v_count > 0 then
           update takpimlocctgdn set
                     m_worktime = c1.m_worktime
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and eqloc_id = c1.eqloc_id
                and eqctg_id = c1.eqctg_id
            ;
       else
           insert into takpimlocctgdn( comp_no, plant, yyyymm, eqloc_id, eqctg_id, m_worktime
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm,  c1.eqloc_id, c1.eqctg_id, c1.m_worktime
           );
       end if;
       
   end loop;
   
   
   for c1 in c_eqlocctg_list loop
   
         --//월  데이타 추출...
          select sum(a.m_stoptime)
                  ,sum(a.m_repairtime)
                  ,sum(a.m_stopfreq)
                  ,sum(a.m_repairfreq)
          into v_m_stoptime, v_m_repairtime, v_m_stopfreq, v_m_repairfreq
          from (SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
                    ,d.eqloc_id as eqloc_id
                    ,f.eqctg_id
                    ,SUM (b.lndn_work_time) as m_stoptime
                    ,SUM (b.eqdn_work_time) as m_repairtime
                    ,SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) AS m_stopfreq
                    ,SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) AS m_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e,
                    taeqctg f
               WHERE     1 = 1
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    and d.comp_no = f.comp_no
                    and d.eqctg_id = f.eqctg_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= to_char(trunc(to_date(c1.yyyymm,'yyyymm'),'mm'),'yyyymmdd')
                    AND a.wkor_date <= to_char(last_day(to_date(c1.yyyymm,'yyyymm')),'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6), d.eqloc_id, f.eqctg_id
            ) a
          where comp_no = c1.comp_no
              and plant = c1.plant
              and a.eqloc_id in (select eqloc_id
                                        from taeqloc
                                        start with eqloc_id =c1.eqloc_id
                                        connect by prior  eqloc_id = p_eqloc_id
                                       )
              and a.eqctg_id in (select eqctg_id
                                        from taeqctg
                                        start with eqctg_id =c1.eqctg_id
                                        connect by prior  eqctg_id = p_eqctg_id
                                       )
          ;

          
          update takpimlocctgdn set
                     m_stoptime = v_m_stoptime
                     ,m_repairtime = v_m_repairtime
                     ,m_stopfreq = v_m_stopfreq
                     ,m_repairfreq = v_m_repairfreq
                    
                    ,mtbf = m_worktime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mtbf_eq = m_worktime/decode(v_m_repairfreq,0,1,v_m_repairfreq)
                    ,mttr = v_m_stoptime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mttr_eq = v_m_repairtime/decode(v_m_stopfreq,0,1,v_m_stopfreq)
                    ,mtba = m_worktime/decode(v_m_repairfreq,0,1,v_m_repairfreq)
                    ,ratio =trunc( (m_stoptime/decode(m_worktime,0,1,m_worktime)) * 100,2) 
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and eqloc_id = c1.eqloc_id
                and eqctg_id = c1.eqctg_id
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
        and batpgm_no = 'TAKPIMLOCCTGDN'
    ;
    
    
    
end;
/

CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMLOCDN(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_ma_stoptime                        number(18);
    v_ma_repairtime                      number(18);
    v_ma_stopfreq                         number(18);
    v_ma_repairfreq                      number(18);
    
    
    -- exec SP_KPI_MAKE_TAKPIMLOCDN('100','ADMIN'); 
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    cursor c_lnwrktime is
        select 
            a.comp_no    
            ,a.plant
            ,substr(a.wrk_date,1,6) as yyyymm
            ,a.eqloc_id
            ,sum(a.dtime) + sum(a.etime)+ sum(a.ntime) as ma_worktime
        from TALNWRKTIME a
        where 1=1
            and a.comp_no = v_comp_no
            and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= to_char(sysdate,'yyyymmdd')
        group by a.comp_no, a.plant, substr(a.wrk_date,1,6), a.eqloc_id
       ;
       
     /*고장시간 집계대상일자 및 라인*/
     cursor  c_eqloc_list is
         select 
                 comp_no
                 ,plant
                 ,substr(wrk_date,1,6) as yyyymm
                 ,eqloc_id
            from TALNWRKTIME a
            where 1=1
                and a.comp_no = v_comp_no
                and a.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= to_char(sysdate,'yyyymmdd')
            group by comp_no, plant, substr(wrk_date,1,6), eqloc_id
            order by 1,2,3,4
           ;
        
begin

    
        
   for c1 in c_lnwrktime loop
   
       --  가동시간 Setting 
       select count(*)
       into v_count
       from takpimlocdn
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and eqloc_id = c1.eqloc_id
       ;
       
       if v_count > 0 then
           update takpimlocdn set
                    ma_worktime = c1.ma_worktime
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and eqloc_id = c1.eqloc_id
            ;
       else
           insert into takpimlocdn( comp_no, plant, yyyymm, eqloc_id, ma_worktime
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm,  c1.eqloc_id, c1.ma_worktime
           );
       end if;
       
   end loop;
   
   for c1 in c_eqloc_list loop
   
         --//1일 ~ 현재일까지 누적 데이타 추출...
          select sum(a.d_stoptime)
                  ,sum(a.d_repairtime)
                  ,sum(a.d_stopfreq)
                  ,sum(a.d_repairfreq)
          into v_ma_stoptime, v_ma_repairtime, v_ma_stopfreq, v_ma_repairfreq
          from (SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
                    ,d.eqloc_id as eqloc_id
                    ,SUM (b.lndn_work_time) as d_stoptime
                    ,SUM (b.eqdn_work_time) as d_repairtime
                    ,SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END) AS d_stopfreq
                    ,SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END) AS d_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE     1 = 1
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= to_char(trunc(to_date(c1.yyyymm,'yyyymm'),'mm'),'yyyymmdd')
                    AND a.wkor_date <= to_char(last_day(to_date(c1.yyyymm,'yyyymm')),'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6), d.eqloc_id
            ) a
          where comp_no = c1.comp_no
              and plant = c1.plant
              and a.eqloc_id in (select eqloc_id
                                        from taeqloc
                                        start with eqloc_id =c1.eqloc_id
                                        connect by prior  eqloc_id = p_eqloc_id
                                       )
          ;
          
          update takpimlocdn set
                     ma_stoptime = v_ma_stoptime
                    ,ma_bd_rate = trunc( (v_ma_stoptime/decode(ma_worktime,0,1,ma_worktime)) * 100,2) 
                    ,ma_bd_eq_rate = trunc( (v_ma_repairtime/decode(ma_worktime,0,1,ma_worktime)) * 100,2) 
                    ,ma_repairtime = v_ma_repairtime
                    ,ma_stopfreq = v_ma_stopfreq
                    ,ma_repairfreq = v_ma_repairfreq
                    ,mtbf = ma_worktime/decode(v_ma_stopfreq,0,1,v_ma_stopfreq)
                    ,mtbf_eq = ma_worktime/decode(v_ma_repairfreq,0,1,v_ma_repairfreq)
                    ,mttr = v_ma_stoptime/decode(v_ma_stopfreq,0,1,v_ma_stopfreq)
                    ,mttr_eq = v_ma_repairtime/decode(v_ma_repairfreq,0,1,v_ma_repairfreq)
                    ,mtba = ma_worktime/decode(v_ma_repairfreq,0,1,v_ma_repairfreq)
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and eqloc_id = c1.eqloc_id
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
        and batpgm_no = 'TAKPIMLOCDN'
    ;
    
    
    
end;
/

CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMMPOINT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_bd_actual                           number(18,3);
    v_btbf_actual                         number(18,3);
    v_mttr_actual                        number(18,3);
    v_pm_actual                         number(18);
    v_tr_actual                           number(18,3);
    
    -- exec SP_KPI_MAKE_TAKPIMMPOINT('100','ADMIN'); 
    
    
--BD,MTBF,MTTR,PM
    cursor c_bd_plan is
        select 
            a.comp_no    
            ,a.plant
            ,a.yyyymm
            ,a.mtlnpoint mtpoint
            ,avg(nvl(a.pvalue,0)) pvalue
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= a.yyyymm
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 
                      (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                  ,2) as acm_plan
             ,(select avg(nvl(aa.pvalue,0))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
             ) as next_plan
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 12
                  ,2) as y_plan
        from TAMTLNPOINT a
        where 1=1
            and a.comp_no = v_comp_no
            and a.mtlnpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
            group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
       ;
       --체크
     cursor c_bd_actual is
                SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
                    ,(select nvl(sum(aa.dtime),0) + nvl(sum(aa.etime),0) + nvl(sum(aa.ntime),0) 
                      from TALNWRKTIME aa
                      where a.comp_no = aa.comp_no
                          and  aa.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                          AND aa.wrk_date <= to_char(sysdate,'yyyymmdd') 
                          and e.plant = aa.plant
                     ) as m_worktime
                    ,nvl(SUM (b.lndn_work_time),0) as m_stoptime
                    ,nvl(SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_stopfreq
                    ,nvl(SUM (b.eqdn_work_time),0) as m_repairtime
                    ,nvl(SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE  a.comp_no = v_comp_no
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                    AND a.wkor_date <= to_char(sysdate,'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6)
        ;
--체크
    cursor c_pm_plan_actual is       
       select 
            a.comp_no
            ,a.plant
            ,a.yyyymm
            ,a.mtpoint
            ,a.pvalue
            ,a.avalue
            ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_plan
              ,(select nvl(sum(aa.avalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_actual
            ,(select aa.pvalue
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
              ) as next_plan
              ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as y_plan
        from vwkpimpmrslt a
        where 1=1
            and a.comp_no = v_comp_no
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
    ;

--체크
   
       
        
begin

    
        
   for c1 in c_bd_plan loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                     m_plan = c1.pvalue
                     ,acm_plan = c1.acm_plan
                     ,next_plan = c1.next_plan
                     ,y_plan = c1.y_plan
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, acm_plan, next_plan,y_plan,m_actual,acm_actual,y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, c1.pvalue, c1.acm_plan, c1.next_plan, c1.y_plan,0,0,0
           );
       end if;
       
   end loop;
   --체크
   for c1 in c_bd_actual loop

     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_stoptime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD'
            ;
            
     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_repairtime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD_EQ'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF_EQ'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_stoptime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_repairtime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR_EQ'
            ;
            --체크
       --누적실적 넣기
       update takpimmpoint a set a.acm_actual = ( select   nvl(sum(aa.m_actual),0) 
                                                                                / (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                                                                     from takpimmpoint aa
                                                                     where 1=1
                                                                         and aa.comp_no = a.comp_no
                                                                         and aa.plant = a.plant
                                                                         and aa.yyyymm <=  a.yyyymm
                                                                         and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                                                                         and aa.kpi_mpoint = a.kpi_mpoint 
                                                                  )
      where a.comp_no = c1.comp_no
          and a.plant = c1.plant
          and a.yyyymm = c1.yyyymm
          and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
      ;           
       
       
   end loop;
   
   --체크 
   
   for c1 in c_pm_plan_actual loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                      m_plan = nvl(c1.pvalue,0)
                     ,m_actual = nvl(c1.avalue,0)
                     ,acm_plan = nvl(c1.acm_plan,0)
                     ,acm_actual = nvl(c1.acm_actual,0)
                     ,next_plan = nvl(c1.next_plan,0)
                     ,y_plan = nvl(c1.y_plan,0)
                     ,y_actual = nvl(c1.acm_actual,0)
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, m_actual, acm_plan, acm_actual, next_plan, y_plan, y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, nvl(c1.pvalue,0), nvl(c1.avalue,0), nvl(c1.acm_plan,0), nvl(c1.acm_actual,0), nvl(c1.next_plan,0),nvl(c1.y_plan,0),  nvl(c1.acm_actual,0)
           );
       end if;
       
   end loop;
   
   
   update takpimmpoint a set
       a.m_diff = nvl(a.m_plan,0) - nvl(a.m_actual,0)
       ,a.m_ach = trunc( nvl(a.m_actual,0)/decode(nvl(a.m_plan,0),0,1,nvl(a.m_plan,0))* 100,2)
       ,a.acm_diff = nvl(a.acm_plan,0) - nvl(a.acm_actual,0)
       ,a.acm_ach = trunc(nvl(a.acm_actual,0)/decode(nvl(a.acm_plan,0),0,1,nvl(a.acm_plan,0)) * 100 , 2)
   where comp_no = v_comp_no
       and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
        and a.yyyymm <= to_char(sysdate,'yyyymm')
        and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')   
    ;
   
   
   
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
        and batpgm_no = 'TAKPIMMPOINT'
    ;
    
    
    
    
end;
/


/** 20170116 김정우 프로시저 수정 */
CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMMPOINT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_bd_actual                           number(18,3);
    v_btbf_actual                         number(18,3);
    v_mttr_actual                        number(18,3);
    v_pm_actual                         number(18);
    v_tr_actual                           number(18,3);
    
    -- exec SP_KPI_MAKE_TAKPIMMPOINT('100','ADMIN'); 
    
    
--BD,MTBF,MTTR,PM
    cursor c_bd_plan is
        select 
            a.comp_no    
            ,a.plant
            ,a.yyyymm
            ,a.mtlnpoint mtpoint
            ,avg(nvl(a.pvalue,0)) pvalue
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= a.yyyymm
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 
                      (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                  ,2) as acm_plan
             ,(select avg(nvl(aa.pvalue,0))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
             ) as next_plan
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 12
                  ,2) as y_plan
        from TAMTLNPOINT a
        where 1=1
            and a.comp_no = v_comp_no
            and a.mtlnpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
            group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
       ;
 
     cursor c_bd_actual is
                SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
                    ,(select nvl(sum(aa.dtime),0) + nvl(sum(aa.etime),0) + nvl(sum(aa.ntime),0) 
                      from TALNWRKTIME aa
                      where a.comp_no = aa.comp_no
                          and  aa.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                          AND aa.wrk_date <= to_char(sysdate,'yyyymmdd') 
                          and e.plant = aa.plant
                     ) as m_worktime
                    ,nvl(SUM (b.lndn_work_time),0) as m_stoptime
                    ,nvl(SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_stopfreq
                    ,nvl(SUM (b.eqdn_work_time),0) as m_repairtime
                    ,nvl(SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE  a.comp_no = v_comp_no
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                    AND a.wkor_date <= to_char(sysdate,'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6)
        ;

    cursor c_pm_plan_actual is       
       select 
            a.comp_no
            ,a.plant
            ,a.yyyymm
            ,a.mtpoint
            ,a.pvalue
            ,a.avalue
            ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_plan
              ,(select nvl(sum(aa.avalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_actual
            ,(select aa.pvalue
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
              ) as next_plan
              ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as y_plan
        from vwkpimpmrslt a
        where 1=1
            and a.comp_no = v_comp_no
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
    ;

        
begin

    
        
   for c1 in c_bd_plan loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                     m_plan = c1.pvalue
                     ,acm_plan = c1.acm_plan
                     ,next_plan = c1.next_plan
                     ,y_plan = c1.y_plan
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, acm_plan, next_plan,y_plan,m_actual,acm_actual,y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, c1.pvalue, c1.acm_plan, c1.next_plan, c1.y_plan,0,0,0
           );
       end if;
       
   end loop;

   for c1 in c_bd_actual loop

     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_stoptime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD'
            ;
            
     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_repairtime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD_EQ'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF_EQ'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_stoptime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_repairtime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR_EQ'
            ;

       --누적실적 넣기
       update takpimmpoint a set a.acm_actual = ( select   nvl(sum(aa.m_actual),0) 
                                                                                / (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                                                                     from takpimmpoint aa
                                                                     where 1=1
                                                                         and aa.comp_no = a.comp_no
                                                                         and aa.plant = a.plant
                                                                         and aa.yyyymm <=  a.yyyymm
                                                                         and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                                                                         and aa.kpi_mpoint = a.kpi_mpoint 
                                                                  )
      where a.comp_no = c1.comp_no
          and a.plant = c1.plant
          and a.yyyymm = c1.yyyymm
          and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
      ;           
       
       
   end loop;
   

   
   for c1 in c_pm_plan_actual loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                      m_plan = nvl(c1.pvalue,0)
                     ,m_actual = nvl(c1.avalue,0)
                     ,acm_plan = nvl(c1.acm_plan,0)
                     ,acm_actual = nvl(c1.acm_actual,0)
                     ,next_plan = nvl(c1.next_plan,0)
                     ,y_plan = nvl(c1.y_plan,0)
                     ,y_actual = nvl(c1.acm_actual,0)
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, m_actual, acm_plan, acm_actual, next_plan, y_plan, y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, nvl(c1.pvalue,0), nvl(c1.avalue,0), nvl(c1.acm_plan,0), nvl(c1.acm_actual,0), nvl(c1.next_plan,0),nvl(c1.y_plan,0),  nvl(c1.acm_actual,0)
           );
       end if;
       
   end loop;
   
   
   update takpimmpoint a set
       a.m_diff = nvl(a.m_plan,0) - nvl(a.m_actual,0)
       ,a.m_ach = trunc( nvl(a.m_actual,0)/decode(nvl(a.m_plan,0),0,1,nvl(a.m_plan,0))* 100,2)
       ,a.acm_diff = nvl(a.acm_plan,0) - nvl(a.acm_actual,0)
       ,a.acm_ach = trunc(nvl(a.acm_actual,0)/decode(nvl(a.acm_plan,0),0,1,nvl(a.acm_plan,0)) * 100 , 2)
   where comp_no = v_comp_no
       and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
        and a.yyyymm <= to_char(sysdate,'yyyymm')
        --and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')   
    ;
   
   
   
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
        and batpgm_no = 'TAKPIMMPOINT'
    ;
    
    
    
    
end;
/




/** 2017-01-25 김정우-SLP까지적용 */
CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMMPOINT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 
    v_bd_actual                           number(18,3);
    v_btbf_actual                         number(18,3);
    v_mttr_actual                        number(18,3);
    v_pm_actual                         number(18);
    v_tr_actual                           number(18,3);
    
    -- exec SP_KPI_MAKE_TAKPIMMPOINT('100','ADMIN'); 
    
    
--BD,MTBF,MTTR,PM
    cursor c_bd_plan is
        select 
            a.comp_no    
            ,a.plant
            ,a.yyyymm
            ,a.mtlnpoint mtpoint
            ,avg(nvl(a.pvalue,0)) pvalue
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= a.yyyymm
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 
                      (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                  ,2) as acm_plan
             ,(select avg(nvl(aa.pvalue,0))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
             ) as next_plan
            ,trunc((select sum(avg(nvl(aa.pvalue,0)))
                       from tamtlnpoint aa 
                       where aa.comp_no = a.comp_no
                           and aa.plant = a.plant
                           and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                           and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                           and aa.mtlnpoint = a.mtlnpoint
                      group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
                      ) / 12
                  ,2) as y_plan
        from TAMTLNPOINT a
        where 1=1
            and a.comp_no = v_comp_no
            and a.mtlnpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
            group by a.comp_no , a.plant, a.yyyymm, a.mtlnpoint
       ;
 
     cursor c_bd_actual is
                SELECT
                     a.comp_no
                    ,e.plant
                    ,substr(a.wkor_date,1,6)  as yyyymm
                    ,(select nvl(sum(aa.dtime),0) + nvl(sum(aa.etime),0) + nvl(sum(aa.ntime),0) 
                      from TALNWRKTIME aa
                      where a.comp_no = aa.comp_no
                          and  aa.wrk_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                          AND aa.wrk_date <= to_char(sysdate,'yyyymmdd') 
                          and e.plant = aa.plant
                     ) as m_worktime
                    ,nvl(SUM (b.lndn_work_time),0) as m_stoptime
                    ,nvl(SUM (CASE WHEN b.lndn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_stopfreq
                    ,nvl(SUM (b.eqdn_work_time),0) as m_repairtime
                    ,nvl(SUM (CASE WHEN b.eqdn_work_time > 0 THEN 1 ELSE 0 END),0) AS m_repairfreq
                FROM taworkorder a,
                    tawofail b,
                    tawoequip c,
                    taequipment d,
                    taeqloc e
               WHERE  a.comp_no = v_comp_no
                    AND a.comp_no = b.comp_no
                    AND a.wkor_id = b.wkor_id
                    AND a.comp_no = c.comp_no
                    AND a.wkor_id = c.wkor_id
                    AND c.comp_no = d.comp_no
                    AND c.equip_id = d.equip_id
                    and d.comp_no = e.comp_no
                    and d.eqloc_id = e.eqloc_id
                    AND a.wo_status = 'C'                                      -- 작업완료
                    AND a.wkor_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                    AND a.wkor_date <= to_char(sysdate,'yyyymmdd')
                    AND a.wo_type = 'BM'                                     --고장작업만..
            GROUP BY a.comp_no, e.plant, substr(a.wkor_date,1,6)
        ;

    cursor c_pm_plan_actual is       
       select 
            a.comp_no
            ,a.plant
            ,a.yyyymm
            ,a.mtpoint
            ,a.pvalue
            ,a.avalue
            ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_plan
              ,(select nvl(sum(aa.avalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= a.yyyymm
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as acm_actual
            ,(select aa.pvalue
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm = to_char(add_months(to_date(a.yyyymm,'yyyymm'),1),'yyyymm')
              ) as next_plan
              ,(select nvl(sum(aa.pvalue),0)
              from vwkpimpmrslt aa
              where aa.comp_no = a.comp_no
                  and aa.plant = a.plant
                  and aa.mtpoint =a.mtpoint
                  and aa.yyyymm <= substr(a.yyyymm,1,4) || '12'
                  and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
              ) as y_plan
        from vwkpimpmrslt a
        where 1=1
            and a.comp_no = v_comp_no
            and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
            and a.yyyymm <= to_char(sysdate,'yyyymm')
    ;

        
begin

    
        
   for c1 in c_bd_plan loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                     m_plan = c1.pvalue
                     ,acm_plan = c1.acm_plan
                     ,next_plan = c1.next_plan
                     ,y_plan = c1.y_plan
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, acm_plan, next_plan,y_plan,m_actual,acm_actual,y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, c1.pvalue, c1.acm_plan, c1.next_plan, c1.y_plan,0,0,0
           );
       end if;
       
   end loop;

   for c1 in c_bd_actual loop

     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_stoptime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD'
            ;
     update takpimmpoint set
          m_actual = nvl(c1.m_stopfreq,0)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BDN'
            ;
            
     update takpimmpoint set
          m_actual = trunc( (nvl(c1.m_repairtime,0)/decode(nvl(c1.m_worktime,0),0,1,c1.m_worktime)) * 100,2)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BD_EQ'
            ;
     update takpimmpoint set
          m_actual = nvl(c1.m_repairfreq,0)
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'BDN_EQ'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF'
            ;
      update takpimmpoint set
          m_actual = trunc((nvl(c1.m_worktime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTBF_EQ'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_stoptime,0)/decode(nvl(c1.m_stopfreq,0),0,1,c1.m_stopfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR'
            ;
       update takpimmpoint set
          m_actual = trunc((nvl(c1.m_repairtime,0)/decode(nvl(c1.m_repairfreq,0),0,1,c1.m_repairfreq)),0) 
     where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = 'MTTR_EQ'
            ;

       --누적실적 넣기
       update takpimmpoint a set a.acm_actual = ( select   nvl(sum(aa.m_actual),0) 
                                                                                / (months_between(to_date(a.yyyymm,'yyyymm') , to_date(substr(a.yyyymm,1,4) || '01','yyyymm'))+1) 
                                                                     from takpimmpoint aa
                                                                     where 1=1
                                                                         and aa.comp_no = a.comp_no
                                                                         and aa.plant = a.plant
                                                                         and aa.yyyymm <=  a.yyyymm
                                                                         and aa.yyyymm >= substr(a.yyyymm,1,4) || '01'
                                                                         and aa.kpi_mpoint = a.kpi_mpoint 
                                                                  )
      where a.comp_no = c1.comp_no
          and a.plant = c1.plant
          and a.yyyymm = c1.yyyymm
          and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')
      ;           
       
       
   end loop;
   

   
   for c1 in c_pm_plan_actual loop
       select count(*)
       into v_count
       from takpimmpoint
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and kpi_mpoint = c1.mtpoint
       ;
       
       if v_count > 0 then
           update takpimmpoint set
                      m_plan = nvl(c1.pvalue,0)
                     ,m_actual = nvl(c1.avalue,0)
                     ,acm_plan = nvl(c1.acm_plan,0)
                     ,acm_actual = nvl(c1.acm_actual,0)
                     ,next_plan = nvl(c1.next_plan,0)
                     ,y_plan = nvl(c1.y_plan,0)
                     ,y_actual = nvl(c1.acm_actual,0)
            where comp_no = c1.comp_no
                and plant = c1.plant
                and yyyymm = c1.yyyymm
                and kpi_mpoint = c1.mtpoint
            ;
       else
           insert into takpimmpoint( comp_no, plant, yyyymm, kpi_mpoint, m_plan, m_actual, acm_plan, acm_actual, next_plan, y_plan, y_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.mtpoint, nvl(c1.pvalue,0), nvl(c1.avalue,0), nvl(c1.acm_plan,0), nvl(c1.acm_actual,0), nvl(c1.next_plan,0),nvl(c1.y_plan,0),  nvl(c1.acm_actual,0)
           );
       end if;
       
   end loop;
   
   
   update takpimmpoint a set
       a.m_diff = nvl(a.m_plan,0) - nvl(a.m_actual,0)
       ,a.m_ach = trunc( nvl(a.m_actual,0)/decode(nvl(a.m_plan,0),0,1,nvl(a.m_plan,0))* 100,2)
       ,a.acm_diff = nvl(a.acm_plan,0) - nvl(a.acm_actual,0)
       ,a.acm_ach = trunc(nvl(a.acm_actual,0)/decode(nvl(a.acm_plan,0),0,1,nvl(a.acm_plan,0)) * 100 , 2)
   where comp_no = v_comp_no
       and a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm')  -- 한달전 1일 부터 재 계산 처리...
        and a.yyyymm <= to_char(sysdate,'yyyymm')
        --and a.kpi_mpoint in (select cdsysd_no from tacdsysd where list_Type='MTLNPOINT' and is_use='Y')   
    ;
   
   
   
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
        and batpgm_no = 'TAKPIMMPOINT'
    ;
    
    
    
    
end;
/


CREATE OR REPLACE procedure SP_PM_MAKE_TAINVESTAMT(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                               number(4); 
    v_mtlnpoint_id                     number(18);
    
    v_bd_monthly_tvalue_eq             number(18,3);  
    v_bdn_monthly_tvalue_eq             number(18,3);  
    v_mttr_monthly_tvalue_eq          number(15);
    v_mtbf_monthly_tvalue_eq         number(15);
    
    v_bd_monthly_tvalue             number(18,3);  
    v_bdn_monthly_tvalue             number(18,3);  
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
         v_bdn_monthly_tvalue := 2;
         select value$ into v_bdn_monthly_tvalue
         from taconfig
         where name='BDN_MONTHLY_TVALUE' 
         ;
    exception
        when others then
           v_bdn_monthly_tvalue := 2; 
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
         v_bdn_monthly_tvalue_eq := 20;
         select value$ into v_bdn_monthly_tvalue_eq
         from taconfig
         where name='BDN_MONTHLY_TVALUE_EQ' 
         ;
    exception
        when others then
           v_bdn_monthly_tvalue_eq := 20; 
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
                     elsif c3.cdsysd_no = 'BDN' then
                     v_pvalue := v_bdn_monthly_tvalue;
                     elsif c3.cdsysd_no = 'BDN_EQ' then
                     v_pvalue := v_bdn_monthly_tvalue_eq;
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



/** 2017-03-02 김정우 추가 PM workorder 생성할 떄 젖검결과값을 코드 첫번째 값을 넣어줌*/
CREATE OR REPLACE procedure SP_PM_MAKE_WORKORDER(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                         number(4); 
    v_wo_res_before           number(4);  
    v_wo_id                        number(18);
    v_wo_no                      number(18);  
    
    -- exec SP_PM_MAKE_WORKORDER('100','ADMIN');

    cursor c_pm is
        select 
             a.pm_id
            ,a.wo_res_bef 
            ,(select aa.wcode_id from tadept aa where a.comp_no = aa.comp_no and a.dept_id = aa.dept_id) as wcode_id
            ,a.pm_type
        from TAPMLST a
        where 1=1
            and a.comp_no = v_comp_no
            and a.is_active = 'Y'
           -- and a.pm_id = 5212
    ;
    
    cursor c_pm_sche_no(p_pm_id number, p_wo_res_bef number) is
        select 
            a.pmsched_id
            ,a.sched_date
        from TAPMSCHED a
        where 1=1
            and a.comp_no = v_comp_no
            and a.pm_id = p_pm_id
            and a.pmsched_status = 'P' -- not release work order 
            and a.wkor_id is null
            and ( to_date(a.sched_date,'yyyymmdd') - trunc(sysdate) ) <= p_wo_res_bef
        order by sched_date
    ;
     
        
begin

    for c1 in c_pm loop
        
        begin
            v_wo_res_before := to_number(c1.wo_res_bef);
        exception
            when others then
               v_wo_res_before := 7; -- default set 7 day before
        end;
        
        for c2 in c_pm_sche_no(c1.pm_id,v_wo_res_before) loop

            select sqawkor_id.nextval 
            into v_wo_id
            from dual;
            
            insert into taworkorder(
                 comp_no
                ,wkor_id
                ,wo_no
                ,equip_id
                ,description
                ,wo_status 
                ,wo_type
                ,pm_type
                ,wo_gen_type
                ,start_date
                ,end_date
                ,wkor_date
                ,dept_id
                ,emp_id
                ,perform
                ,pm_id
                ,pmsched_id
                ,upd_date
                ,upd_by
                ,wkctr_id
            )
            select 
                 v_comp_no  as comp_no
                ,v_wo_id      as wkor_id
                ,v_wo_id      as wo_no
                ,a.equip_id   as equip_id
                ,a.description  as description
                ,'P'                 as wo_status -- stand by 
                ,a.wo_type      as wo_type  -- preventmaint
                ,a.pm_type      as pm_type
                ,'PMPLAN'        as wo_gen_type
                ,c2.sched_date  as start_date
                ,c2.sched_date  as end_date
                ,c2.sched_date  as wkor_date
                ,a.dept_id         as dept_id
                ,a.emp_id        as emp_id
                ,a.remark         as perform
                ,c1.pm_id         as pm_id
                ,c2.pmsched_id  as pmsched_id
                ,to_char(sysdate,'yyyymmdd') as upd_date
                ,(select user_id from tauser where comp_no = v_comp_no 
                            and user_no = v_user_no and rownum = 1
                 ) as upd_by
                ,a.wkctr_id
            from tapmlst a
            where 1=1
                and a.comp_no = v_comp_no
                and a.pm_id = c1.pm_id
            ;
            
            -- tawoequip 
            insert into tawoequip(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description)
            select comp_no, sqawoequip_id.nextval,  v_wo_id,  equip_id, eqctg_id, description 
            from tapmequip
            where comp_no = v_comp_no
                and pm_id = c1.pm_id
            ;
            
            -- parts
            insert into tawoparts(
                comp_no
                ,wopart_id
                ,wkor_id
                ,wcode_id
                ,part_id
                ,part_grade
                ,use_qty
                ,unit_price
                ,tot_price
            )
            select v_comp_no as comp_no
                ,sqawopart_id.nextval as wopart_id
                ,v_wo_id     as wkor_id
                ,c1.wcode_id as wcode_id
                ,a.part_id     as part_id
                ,'A'              as part_grade
                ,a.use_qty   as use_qty
                ,b.last_price  as unit_price
                ,a.use_qty * b.last_price as tot_price
            from tapmpart a
                ,taparts b
            where a.comp_no = b.comp_no
                and a.part_id = b.part_id
                and a.comp_no = v_comp_no
                and a.pm_id =  c1.pm_id
            ;
            
            -- inspection point
            insert into tawopoint(
                comp_no
                ,wopoint_id
                ,wkor_id
                ,pm_point_id
                ,pm_point_rslt_status
                ,pm_point_rep_status
            )
            select v_comp_no as comp_no
                ,sqawopoint_id.nextval as wopoint_id
                ,v_wo_id     as wkor_id
                ,a.pm_point_id as pm_point_id
                ,(select cdsysd_no from 
                        (select * from tacdsysd
                        where list_type='PM_POINT_RSLT_STATUS'
                        order by ord_no)
                        where rownum=1)               as pm_point_rslt_status
                ,'GD'               as pm_point_rep_status
            from tapmpoint a
            where 1=1
                and a.comp_no = v_comp_no
                and a.pm_id =  c1.pm_id
                and a.is_active = 'Y'
            ;
            
            
             -- stardard point insert 
            insert into TAWOSTPOINT(
                 comp_no
                ,wostpoint_id
                ,wkor_id
                ,stwrk_point_id
                ,st_point_rslt_status
            )
            select v_comp_no as comp_no
                ,sqawostpoint_id.nextval as wostpoint_id
                ,v_wo_id     as wkor_id
                ,a.stwrk_point_id as stwrk_point_id
                ,'GD'               as st_point_rslt_status
            from TASTWRKPOINT a, TASTWRKLST b
            where 1=1
                and a.comp_no = v_comp_no
                and a.comp_no = b.comp_no
                and a.stwrk_id = b.stwrk_id
                and a.is_active = 'Y'
                and b.pm_type = c1.pm_type
                and b.is_active = 'Y'
                and b.is_force ='Y'
            ;
            
            -- update wo_no in TAPMSCHED
            update TAPMSCHED set
                 wkor_id = v_wo_id
                ,pmsched_status =  'S' 
            where comp_no = v_comp_no
                and pmsched_id = c2.pmsched_id
            ;
        
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
        and batpgm_no = 'TAPMWORK'
    ;
    
    
    
end;
/


CREATE OR REPLACE procedure SP_PM_MAKE_WO_BY_SCHEDID(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
     ,v_pmsched_id in number
) is
    v_count                         number(4); 
    
    -- exec SP_PM_MAKE_WO_BY_SCHEDID('120','ADMIN',0000);

     
        
begin

   --W/O CREATE 
     insert into taworkorder(
                 comp_no
                ,wkor_id
                ,wo_no
                ,description
                ,wo_status
                ,wo_type 
                ,pm_type
                ,pm_id
                ,pmsched_id
                ,start_date
                ,end_date
                ,dept_id
                ,emp_id
                ,wo_gen_type
                ,wkor_date
                ,wkctr_id
                ,upd_date
                ,upd_by
            )
            select 
                 a.comp_no
                ,SQAWKOR_ID.nextval
                ,SQAWKOR_ID.currval
                ,'['||a.wo_type||']'||a.description
                ,'P'
                ,a.wo_type
                ,a.pm_type
                ,a.pm_id
                ,a.pmsched_id
                ,a.sched_date
                ,a.sched_date
                ,a.dept_id
                ,a.main_mng_id
                ,'PMPLAN'
                ,a.wkor_date
                ,a.wkctr_id
                ,to_char(sysdate,'yyyymmdd')
                ,(SELECT user_id FROM TAUSER where comp_no = a.comp_no AND user_no = v_user_no AND rownum=1)
            FROM ( SELECT x.comp_no comp_no
                                    ,y.description description
                                    ,y.pm_type pm_type
                                    ,y.pm_id pm_id
                                    ,y.wo_type
                                    ,x.pmsched_id pmsched_id
                                    ,y.dept_id dept_id
                                    ,x.sched_date sched_date
                                    ,x.sched_date wkor_date
                                    ,(SELECT MIN(b.main_mng_id)
                                          FROM TAPMEQUIP a, TAEQUIPMENT b
                                          WHERE a.comp_no = b.comp_no
                                          AND a.equip_id = b.equip_id
                                          AND a.pm_id = y.pm_id
                                          GROUP By a.comp_no, a.pm_id    ) AS main_mng_id
                                    ,x.sched_date plan_date
                                    ,y.wkctr_id wkctr_id
                           FROM TAPMSCHED x, TAPMLST y
                           WHERE x.comp_no  = y.comp_no
                           AND x.pm_id    = y.pm_id
                           AND x.wkor_id is null
                           AND y.is_active = 'Y'
                           AND x.pmsched_status = 'P'
                           AND x.comp_no = v_comp_no
                           AND x.pmsched_id = v_pmsched_id
             ) a
            ;
            --W/O Equipment CREATE
           INSERT INTO TAWOEQUIP(comp_no,woequip_id,wkor_id,equip_id)
           SELECT a.comp_no,SQAWOEQUIP_ID.nextval,a.wkor_id,b.equip_id
           FROM TAWORKORDER a, TAPMEQUIP b
           WHERE a.comp_no = b.comp_no
           AND a.pm_id = b.pm_id
           AND a.wo_status = 'P'
           AND a.wo_type IN ('PMI','PMW')
           AND a.comp_no = v_comp_no
           AND a.pmsched_id IN
                ( SELECT x.pmsched_id
                  FROM TAPMSCHED x, TAPMLST y  
                  WHERE x.comp_no  = y.comp_no
                  AND x.pm_id    = y.pm_id
                  AND x.comp_no = v_comp_no
                  AND y.is_active = 'Y'
                  AND x.pmsched_id = v_pmsched_id
                  )
           ;
           --UPDATE PM SCHED STATUS
           UPDATE TAPMSCHED x SET x.pmsched_status = 'S'
                                                    ,x.wkor_id = (SELECT wkor_id
                                                                        FROM TAWORKORDER
                                                                        WHERE comp_no = x.comp_no
                                                                        AND pmsched_id = x.pmsched_id
                                                                        AND comp_no = v_comp_no)
           WHERE 1=1
           AND x.pmsched_id = v_pmsched_id
           AND x.comp_no = v_comp_no
           AND (SELECT is_active
                    FROM TAPMLST
                    WHERE comp_no = x.comp_no
                    AND pm_id = x.pm_id
                    AND comp_no = v_comp_no) = 'Y'
          ;
          --W/O  PARTS CREATE
            INSERT INTO TAWOPARTS(
                comp_no
                ,wopart_id
                ,wkor_id
                ,wcode_id
                ,part_id
                ,part_grade
                ,use_qty
                ,unit_price
                ,tot_price
            )
            SELECT x.comp_no as comp_no
                ,sqawopart_id.nextval as wopart_id
                ,x.wkor_id     as wkor_id
                ,(SELECT wcode_id 
                     FROM TADEPT 
                   WHERE dept_id = 
                                            (SELECT dept_id FROM TAPMLST WHERE comp_no = x.comp_no AND pm_id = x.pm_id)
                        AND comp_no = x.comp_no) as wcode_id
                ,y.part_id     as part_id
                ,'A'              as part_grade
                ,y.use_qty   as use_qty
                ,z.last_price  as unit_price
                ,y.use_qty * z.last_price as tot_price
            FROM TAPMSCHED x, TAPMPART y, TAPARTS z
            WHERE x.comp_no = y.comp_no
            AND y.comp_no = z.comp_no
            AND x.pm_id = y.pm_id
            AND y.part_id = z.part_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            ;
            
            -- W/O  CRAFT CREATE
            INSERT INTO TAWOCRAFT(
                    comp_no,        wocraft_id,        wkor_id,
                    emp_id,            start_date,        end_date
                )
            SELECT x.comp_no,        SQAWOCRAFT_ID.NEXTVAL, x.wkor_id,
                        (SELECT MIN(b.main_mng_id)
                        FROM TAPMEQUIP a, TAEQUIPMENT b
                        WHERE a.comp_no = b.comp_no
                        AND a.equip_id = b.equip_id
                        AND a.pm_id = y.pm_id
                        AND a.comp_no = y.comp_no
                        GROUP By a.comp_no, a.pm_id    ) AS main_mng_id,
                        x.sched_date,x.sched_date
            FROM TAPMSCHED x, TAPMLST y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            AND   (SELECT MIN(b.main_mng_id)
                      FROM TAPMEQUIP a, TAEQUIPMENT b
                      WHERE a.comp_no = b.comp_no
                      AND a.equip_id = b.equip_id
                      AND a.pm_id = y.pm_id
                      AND a.comp_no = y.comp_no
                      GROUP BY a.comp_no, a.pm_id    ) is not null
            ;
            
            --W/O  POINT CREATE
            INSERT INTO TAWOPOINT(
                    comp_no,        wopoint_id,             wkor_id,
                    pm_point_id,    pm_point_rslt_status,   pm_point_rep_status
                    )
            SELECT x.comp_no,     SQAWOPOINT_ID.NEXTVAL,  x.wkor_id,
                        y.pm_point_id, (select cdsysd_no from 
                        (select * from tacdsysd
                        where list_type='PM_POINT_RSLT_STATUS'
                        order by ord_no)
                        where rownum=1),                   'GD'
            FROM TAPMSCHED x, TAPMPOINT y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            ;
            
            -- UPDATE LAST SCHED DATE
            UPDATE TAPMLST a SET a.last_sch_date =
                    DECODE(a.period_type,
                                'Y',to_char(add_months(to_date(a.last_sch_date,'yyyymmdd'), 1*a.cycle*12), 'yyyymmdd'),
                                'M',to_char(add_months(to_date(a.last_sch_date,'yyyymmdd'), a.cycle*1), 'yyyymmdd'),
                                'W',to_char(to_date(a.last_sch_date,'yyyymmdd')+1*a.cycle*7, 'yyyymmdd'),
                                'D',to_char(to_date(a.last_sch_date,'yyyymmdd')+a.cycle*1, 'yyyymmdd'))
            WHERE 1=1
            AND a.comp_no = v_comp_no
            AND a.is_active = 'Y'
            AND a.pm_id IN (SELECT pm_id
                                    FROM TAPMSCHED
                                    WHERE comp_no = a.comp_no
                                    AND pmsched_id = v_pmsched_id
                                    )
            ;
            
end;
/


/** 2017-03-17 고장시간 목표 값 추가 */
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

/** 2017-03-20 김정우수정 - MWARE_EAI에서 자재 마스터 받아올 때 재고 추가 */
CREATE OR REPLACE PROCEDURE SP_IF_UPD_TAPARTS     (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS
   v_partId number(18);   
   v_count number(4);

    CURSOR c_data IS
     SELECT
            TRIM(bukrs) AS bukrs , --회사코드
            TRIM(werks) AS werks, --플랜트
            TRIM(matnr) AS matnr, -- 품번
            TRIM(maktx) AS maktx, --품명
            TRIM(groes) AS groes, --규격
            TRIM(mtart) AS mtart, --자재유형코드
            TRIM(mtbez) AS mtbez, -- 자재유형명
            TRIM(matkl) AS matkl, -- 자재그룹코드
            TRIM(wgbez) AS wgbez, --자재그룹명
            TRIM(lgfsb) AS lgfsb, --저장위치
            TRIM(meins) AS meins, --단위
            TRIM(bklas) AS bklas, --평가 Calss 코드
            TRIM(erdat) AS erdat, --생성 또는 수정일
            TRIM(lvorm) AS lvorm, --삭제 지시자 
            TRIM(wlvorm) AS wlvorm, --플랜트 레벨 삭제 지시자
            TRIM(ifresult) AS ifresult, --IF전송 FLAG
            TRIM(ifdate) AS ifdate, --IF생성일자
            TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi:ss') received_date
        FROM MWARE_EAI.TXERPPARTS
        WHERE 1=1
            --AND ifresult IS NULL
        ;

BEGIN

    FOR c1 IN c_data LOOP

       SELECT COUNT(*)
       INTO v_count
       FROM TAPARTS 
       WHERE 1=1
           AND comp_no = v_comp_no
           AND TRIM(part_no) = c1.matnr
       ;
       
       IF v_count = 0 THEN
          
       SELECT sqapart_id.nextval INTO v_partId
        FROM DUAL;
  
          INSERT INTO TAPARTS(
              comp_no
              ,part_id
              ,part_no
              ,description
              ,is_inpart
              ,part_categ
              ,is_use
              ,part_group
              ,full_desc
              ,vendor_code
             /* ,pt_size
              ,uom
              ,full_desc
              ,MODEL
              ,maker
              ,USAGE
              ,last_price
              ,plf_type
              ,pco_type
              ,seller
              ,lead_time
              ,is_use
              ,REMARK
              ,upd_date
              ,upd_by
              ,mro_type
              ,kind
              ,var_class
              ,vendor_desc*/
          )VALUES(
              v_comp_no
              ,v_partId
              ,c1.matnr --품번
              ,c1.maktx  --품명 
              ,'N'
              ,'SPPT' --자재와 공구 구분 (자재)
              ,'Y'
              ,c1.wgbez
              ,c1.maktx  --품명 
              ,v_partId
          /*    ,c1.groes  --규격 
              ,c1.meins  --단위 
              ,c1.maktx||'|'||c1.groes||'|'||c1.meins
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,'Y' --isUse
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,'' */
          );
          
          --INSERT STOCK 
          INSERT INTO TAPTSTOCK                            
        (comp_no , wcode_id, part_id, part_grade,        
        stock_qty, bin_no, unit_price)                
        select x.comp_no,y.wcode_id, x.part_id, z.cdsysd_no,0,'',x.last_price
        from taparts x, tawarehouse y, (select * from tacdsysd where list_type='PART_GRADE') z
        where x.comp_no  = y.comp_no        
        and x.comp_no =    v_comp_no                
        and x.part_id =   v_partId            
        and z.is_use=        'Y'                
        and y.wh_categ=    'PART'            
        and y.is_use=        'Y';  

           UPDATE MWARE_EAI.TXERPPARTS SET
                ifresult = 'Y'
                ,received_date = c1.received_date
            WHERE TRIM(bukrs) = c1.bukrs
                 AND TRIM(werks) = c1.werks
                 AND TRIM(matnr) = c1.matnr
            ;
            
       ELSE
          
             UPDATE  TAPARTS
                  SET    part_no = c1.matnr
                            ,part_group = c1.wgbez
                          /* ,description = c1.maktx
                            ,pt_size = c1.groes
                            ,uom = c1.meins
                            ,full_desc = c1.maktx||'|'||c1.groes||'|'||c1.meins
                            ,is_use = 'Y'
                            ,part_group = c1.matkl */
                            ,is_inpart = 'N'
                            ,part_categ='SPPT'
            WHERE 1=1
                AND comp_no = v_comp_no
                AND TRIM(part_no) = c1.matnr
           ;
           
            UPDATE MWARE_EAI.TXERPPARTS SET
                ifresult = 'Y'
                ,received_date = c1.received_date
            WHERE TRIM(bukrs) = c1.bukrs
                 AND TRIM(werks) = c1.werks
                 AND TRIM(matnr) = c1.matnr
            ;
            
       END IF;
       


    END LOOP;
    
MERGE INTO TXERPPARTS a
    USING( SELECT
            TRIM(bukrs) AS bukrs , --회사코드
            TRIM(werks) AS werks, --플랜트
            TRIM(matnr) AS matnr, -- 품번
            TRIM(maktx) AS maktx, --품명
            TRIM(groes) AS groes, --규격
            TRIM(mtart) AS mtart, --자재유형코드
            TRIM(mtbez) AS mtbez, -- 자재유형명
            TRIM(matkl) AS matkl, -- 자재그룹코드
            TRIM(wgbez) AS wgbez, --자재그룹명
            TRIM(lgfsb) AS lgfsb, --저장위치
            TRIM(meins) AS meins, --단위
            TRIM(bklas) AS bklas, --평가 Calss 코드
            TRIM(erdat) AS erdat, --생성 또는 수정일
            TRIM(lvorm) AS lvorm, --삭제 지시자 
            TRIM(wlvorm) AS wlvorm, --플랜트 레벨 삭제 지시자
            TRIM(ifresult) AS ifresult, --IF전송 FLAG
            TRIM(ifdate) AS ifdate, --IF생성일자
            TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi:ss') received_date
        FROM MWARE_EAI.TXERPPARTS
        WHERE 1=1
        ) b
     ON(TRIM(a.bukrs) = b.bukrs
           AND TRIM(a.werks) = b.werks
           AND TRIM(a.matnr) = b.matnr)
     WHEN MATCHED THEN
           UPDATE SET 
                  --  a.bukrs= b.bukrs , --회사코드
                 --   a.werks= b.werks, --플랜트
                 --   a.matnr= b.matnr, -- 품번
                    a.maktx= b.maktx, --품명
                    a.groes= b.groes, --규격
                    a.mtart= b.mtart, --자재유형코드
                    a.mtbez= b.mtbez, -- 자재유형명
                    a.matkl= b.matkl, -- 자재그룹코드
                    a.wgbez= b.wgbez, --자재그룹명
                    a.lgfsb= b.lgfsb, --저장위치
                    a.meins= b.meins, --단위
                    a.bklas= b.bklas, --평가 Calss 코드
                    a.erdat= b.erdat, --생성 또는 수정일
                    a.lvorm= b.lvorm, --삭제 지시자 
                    a.wlvorm= b.wlvorm, --플랜트 레벨 삭제 지시자
                    a.ifresult= b.ifresult, --IF전송 FLAG
                    a.ifdate= b.ifdate --IF생성일자
               --     a.received_date = b.received_date
        WHEN NOT MATCHED THEN                                         
              INSERT ( a.bukrs , --회사코드
                        a.werks, --플랜트
                        a.matnr, -- 품번
                        a.maktx, --품명
                        a.groes, --규격
                        a.mtart, --자재유형코드
                        a.mtbez, -- 자재유형명
                        a.matkl, -- 자재그룹코드
                        a.wgbez, --자재그룹명
                        a.lgfsb, --저장위치
                        a.meins, --단위
                        a.bklas, --평가 Calss 코드
                        a.erdat, --생성 또는 수정일
                        a.lvorm, --삭제 지시자 
                        a.wlvorm, --플랜트 레벨 삭제 지시자
                        a.ifresult, --IF전송 FLAG
                        a.ifdate, --IF생성일자
                        a.received_date
                     )                                                    
              VALUES (b.bukrs , --회사코드
                        b.werks, --플랜트
                        b.matnr, -- 품번
                        b.maktx, --품명
                        b.groes, --규격
                        b.mtart, --자재유형코드
                        b.mtbez, -- 자재유형명
                        b.matkl, -- 자재그룹코드
                        b.wgbez, --자재그룹명
                        b.lgfsb, --저장위치
                        b.meins, --단위
                        b.bklas, --평가 Calss 코드
                        b.erdat, --생성 또는 수정일
                        b.lvorm, --삭제 지시자 
                        b.wlvorm, --플랜트 레벨 삭제 지시자
                        b.ifresult, --IF전송 FLAG
                        b.ifdate, --IF생성일자
                        b.received_date
                     )       
      ;

    UPDATE TABATPGM SET 
         exe_by = (SELECT user_id 
                        FROM TAUSER 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPARTS'
    ;

END;
/


/** 2017-03-22 김정우 추가 */
CREATE OR REPLACE PROCEDURE SP_PM_MAKE_SCHEDULE_BYONE(
     v_comp_no     IN varchar2          
    ,v_pm_id       IN number
    ,v_user_id     IN number
    ,v_target_date IN number  
) IS
    v_count                 number(4);   
    v_last_sch_date      varchar2(8);
    v_next_sch_date     varchar2(8);
    v_to_day                 varchar2(8);
    v_is_work_calendar  varchar2(20);
    
    CURSOR c_pm IS
                SELECT
                     a.comp_no 
                    ,a.pm_id
                    ,a.equip_id
                    ,a.schedule_type -- T:Time, U:Usage
                    ,a.cycle
                    ,a.period_type 
                    ,case when nvl(a.USAGE, 99999999999999) <=0 then 99999999999999 else nvl(a.USAGE, 99999999999999)*60 end as usage  --분으로 보정작업함. 
                    ,a.last_sch_date    -- 다음일정 예정일[주말보정전]
                    ,NVL(b.plant,(select plant FROM TAPLANT where comp_no= v_comp_no and rownum=1)) plant
                    ,b.eqloc_id
                FROM TAPMLST a, taeqloc b
                WHERE 1=1
                    and a.comp_no = b.comp_no(+)
                    and a.eqloc_id = b.eqloc_id(+)
                    AND a.comp_no = v_comp_no
                    AND a.pm_id     = v_pm_id
                    AND a.is_active = 'Y'
                    AND a.last_sch_date <= v_target_date
            ;
  
BEGIN

    v_to_day := TO_CHAR(SYSDATE,'yyyymmdd');
    
    
    -- use work calendar
    select count(*)
    into v_count 
    from taconfig
    where comp_no = v_comp_no
        and name = 'IS_USE_WORK_CALENDAR'
    ;
    
    if v_count > 0 then
        select nvl(value$,'N')
        into v_is_work_calendar 
        from taconfig
        where comp_no = v_comp_no
            and name = 'IS_USE_WORK_CALENDAR'
        ;
    else
        v_is_work_calendar := 'N';
    end if;

    
    FOR c1 IN c_pm LOOP
    
        IF c1.schedule_type = 'T' THEN    -- Period Time Based Scheduling
                
                SELECT COUNT(*)
                INTO v_count
                FROM TAPMSCHED
                WHERE comp_no = c1.comp_no
                    AND pm_id = c1.pm_id
                    AND pmsched_status IN ('C') -- Schedlued, Completed.
                    AND plan_date >= c1.last_sch_date
                ;
                
                IF v_count > 0 THEN
                        SELECT NVL(MAX(plan_date),c1.last_sch_date)
                        INTO v_last_sch_date
                        FROM TAPMSCHED
                        WHERE comp_no = c1.comp_no
                            AND pm_id = c1.pm_id
                            AND pmsched_status IN ('C') -- Schedlued, Completed.
                            AND plan_date >= c1.last_sch_date
                        ;
                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = c1.comp_no
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                            WHERE comp_no = c1.comp_no
                                                               AND pm_id = c1.pm_id
                                                               AND pmsched_status IN ('P','S') -- Planned.
                                                               AND plan_date >= v_last_sch_date )
                       ;
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = c1.comp_no
                           AND pm_id = c1.pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= v_last_sch_date
                       ;
                       
                       SELECT    
                            CASE 
                                 WHEN c1.period_type = 'D' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (c1.cycle * 1 ),'yyyymmdd')
                                 WHEN c1.period_type = 'W' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (7 * c1.cycle * 1 ),'yyyymmdd') 
                                 WHEN c1.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),c1.cycle * 1),'yyyymmdd') 
                                 WHEN c1.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),12 * c1.cycle * 1),'yyyymmdd')
                            END AS next_date
                        INTO v_last_sch_date
                        FROM dual
                        ;

                ELSE 
                        v_last_sch_date := c1.last_sch_date;
                        
                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = c1.comp_no
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                           WHERE comp_no = c1.comp_no
                                                               AND pm_id = c1.pm_id
                                                               AND pmsched_status IN ('P','S') -- Planned.
                                                               AND plan_date >= v_last_sch_date )
                       ;
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = c1.comp_no
                           AND pm_id = c1.pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= v_last_sch_date
                       ;
                       
                END IF;
                
                UPDATE TAPMLST SET
                       last_sch_date = v_last_sch_date
                      ,next_sch_date = v_last_sch_date
                WHERE comp_no = c1.comp_no
                      AND pm_id = c1.pm_id
                ; 
                
                
                WHILE v_last_sch_date <= v_target_date  LOOP
                        
                        -- 생성할 일자가 주말이면 주말이 아닌 다음날짜를 찾기...
                        if v_is_work_calendar = 'Y' then
                            select  min(cal_date)
                            INTO v_next_sch_date
                            from TAWRKCALENDAR a
                            where a.comp_no = v_comp_no
                                and a.plant = c1.plant
                                and a.cal_date >=v_last_sch_date
                                and a.is_work = 'Y'
                            ;
                        else
                            SELECT  MIN(tday)
                            INTO v_next_sch_date
                            FROM TADAY
                            WHERE tday >= v_last_sch_date
                                AND dow NOT IN ('1','7')
                            ;
                        end if;
                        
                        
                        
                        -- 월간 작업일 경우 달을 넘기지 않도록 한다.
                        IF v_next_sch_date IS NOT NULL then 
                        
                                IF c1.period_type = 'M' AND SUBSTR(v_last_sch_date,1,6) != SUBSTR(v_next_sch_date,1,6) THEN
                                    if v_is_work_calendar = 'Y' then
                                        select  max(cal_date)
                                        INTO v_next_sch_date
                                        from TAWRKCALENDAR a
                                        where a.comp_no = v_comp_no
                                            and a.plant = c1.plant
                                            and a.cal_date <=v_last_sch_date
                                            and a.is_work = 'Y'
                                        ;
                                    else
                                        SELECT  MAX(tday)
                                        INTO v_next_sch_date
                                        FROM TADAY
                                        WHERE tday <= v_last_sch_date
                                            AND dow NOT IN ('1','7')
                                        ;
                                    end if;
                                    
                                END IF;  
                                
                                IF v_next_sch_date > v_to_day THEN
                                     INSERT INTO TAPMSCHED(comp_no, pmsched_id, pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
                                     VALUES(c1.comp_no,sqapmsched_id.NEXTVAL, c1.pm_id, v_last_sch_date, v_next_sch_date, v_next_sch_date,'P' )
                                     ;
                               END IF;  
                               
                               UPDATE TAPMLST SET
                                       last_sch_date = v_last_sch_date
                                      ,next_sch_date = v_next_sch_date
                                WHERE comp_no = c1.comp_no
                                      AND pm_id = c1.pm_id
                                ; 
                                
                                -- 주기가 일일일 경우 휴일이면 스케쥴이 건너뜀..
                                -- 1D, 2D, 3D, 4D도 모두 포함...
                                IF c1.period_type = 'D' THEN
                                    v_last_sch_date := v_next_sch_date;
                                END IF;
                        end if;    

                        
                         SELECT    
                            CASE 
                                 WHEN c1.period_type = 'D' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (c1.cycle * 1 ),'yyyymmdd')
                                 WHEN c1.period_type = 'W' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (7 * c1.cycle * 1 ),'yyyymmdd') 
                                 WHEN c1.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),c1.cycle * 1),'yyyymmdd') 
                                 WHEN c1.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),12 * c1.cycle * 1),'yyyymmdd')
                            END AS next_date
                        INTO v_last_sch_date
                        FROM dual
                        ;
                        
                END LOOP;
                
        
        ELSIF c1.schedule_type = 'U' THEN    -- Run Time Based Scheduling
               --.해당 pm번호에 해당하는 가동라인 알아내기
               -- 가동라인에 설정한 일자 이후에 가동시간을 합계하고 가동시간을 초과하는 일자가 며칠인지 알아내기
               -- 이 일자로 일정을 생성하기.
                SELECT COUNT(*)
                INTO v_count
                FROM TAPMSCHED
                WHERE comp_no = c1.comp_no
                    AND pm_id = c1.pm_id
                    AND pmsched_status IN ('C') -- Schedlued, Completed.
                    AND plan_date >= c1.last_sch_date
                ;
                
                IF v_count > 0 THEN
                        SELECT NVL(MAX(plan_date),c1.last_sch_date)
                        INTO v_last_sch_date
                        FROM TAPMSCHED
                        WHERE comp_no = c1.comp_no
                            AND pm_id = c1.pm_id
                            AND pmsched_status IN ('C') -- Schedlued, Completed.
                            AND plan_date >= c1.last_sch_date
                        ;
                        
                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = c1.comp_no
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                            WHERE comp_no = c1.comp_no
                                                               AND pm_id = c1.pm_id
                                                               AND pmsched_status IN ('P','S') -- Planned.
                                                               AND plan_date >= v_last_sch_date )
                       ;
                        
                        
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = c1.comp_no
                           AND pm_id = c1.pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= v_last_sch_date
                       ;
                ELSE 
                        v_last_sch_date := c1.last_sch_date;
                        
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = c1.comp_no
                              AND pmsched_id in ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                            WHERE comp_no = c1.comp_no
                                                                   AND pm_id = c1.pm_id
                                                                   AND pmsched_status IN ('P','S') -- Planned.
                                                                   AND plan_date >= v_last_sch_date )
                       ;
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = c1.comp_no
                           AND pm_id = c1.pm_id
                           AND pmsched_status IN ('P','S') -- Planned.
                           AND plan_date >= v_last_sch_date
                       ;
                       
                END IF;
                
                WHILE v_last_sch_date < v_target_date  LOOP
                
                       
                       IF v_last_sch_date > v_to_day THEN
                             INSERT INTO TAPMSCHED(comp_no, pmsched_id, pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
                             VALUES(c1.comp_no,sqapmsched_id.NEXTVAL, c1.pm_id, v_last_sch_date, v_last_sch_date, v_last_sch_date,'P' )
                             ;
                       END IF;  
                       
                       UPDATE TAPMLST SET
                               last_sch_date = v_last_sch_date
                        WHERE comp_no = c1.comp_no
                              AND pm_id = c1.pm_id
                        ; 
                       
                       select min(wrk_date) as wrk_date
                       into v_next_sch_date
                       from (
                               select 
                                    a.wrk_date
                                   ,sum(nvl(sum(a.dtime),0) + nvl(sum(a.ntime),0) + nvl(sum(a.etime),0)) over (order by a.wrk_date) run_time
                               from TALNWRKTIME a
                               where a.comp_no = c1.comp_no
                                   and a.plant = c1.plant
                                   and a.wrk_date > v_last_sch_date
                                   and a.eqloc_id in (SELECT eqloc_id                         
                                                                       FROM TAEQLOC                            
                                                                       WHERE comp_no =c1.comp_no
                                                                       START wITH eqloc_id = c1.eqloc_id     
                                                                       CONNECT BY PRIOR p_eqloc_id = eqloc_id  
                                                                     )
                               group by a.wrk_date 
                           ) a
                       where a.run_time >= c1.usage  
                       ;
                       
                        IF v_next_sch_date IS NOT NULL then 

                               UPDATE TAPMLST SET
                                       next_sch_date = v_next_sch_date
                                WHERE comp_no = c1.comp_no
                                      AND pm_id = c1.pm_id
                                ; 
                                
                       end if;
                       
                       v_last_sch_date := nvl(v_next_sch_date,'99991231');

                      
                end loop;
                
        
        END IF;
        
        
    END LOOP;
    
    
     UPDATE TABATPGM SET 
         exe_by = v_user_id
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPMSCHED_ONE'
    ;
    
    
END;
/



/**  2017-03-24 노정현 자재마스터 Interface Procedure !!!운영!!!! */

CREATE OR REPLACE PROCEDURE SP_IF_UPD_TAPARTS     (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS
   v_partId number(18);   
   v_count number(4);

    CURSOR c_data IS
     SELECT
            TRIM(bukrs) AS bukrs , --회사코드
            TRIM(werks) AS werks, --플랜트
            TRIM(matnr) AS matnr, -- 품번
            TRIM(maktx) AS maktx, --품명
            TRIM(groes) AS groes, --규격
            TRIM(mtart) AS mtart, --자재유형코드
            TRIM(mtbez) AS mtbez, -- 자재유형명
            TRIM(matkl) AS matkl, -- 자재그룹코드
            TRIM(wgbez) AS wgbez, --자재그룹명
            TRIM(lgfsb) AS lgfsb, --저장위치
            TRIM(meins) AS meins, --단위
            TRIM(bklas) AS bklas, --평가 Calss 코드
            TRIM(erdat) AS erdat, --생성 또는 수정일
            TRIM(lvorm) AS lvorm, --삭제 지시자 
            TRIM(wlvorm) AS wlvorm, --플랜트 레벨 삭제 지시자
            TRIM(ifresult) AS ifresult, --IF전송 FLAG
            TRIM(ifdate) AS ifdate, --IF생성일자
            TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi:ss') received_date
        FROM MWARE_EAI.TXERPPARTS
        WHERE 1=1
            --AND ifresult IS NULL
        ;

BEGIN

    FOR c1 IN c_data LOOP

       SELECT COUNT(*)
       INTO v_count
       FROM TAPARTS 
       WHERE 1=1
           AND comp_no = v_comp_no
           AND TRIM(part_no) = c1.matnr
       ;
       
       IF v_count = 0 THEN
          
       SELECT sqapart_id.NEXTVAL INTO v_partId
        FROM DUAL;
  
          INSERT INTO TAPARTS(
              comp_no
              ,part_id
              ,part_no
              ,description
              ,is_inpart
              ,part_categ
              ,is_use
              ,part_group
              ,full_desc
              ,vendor_code
             /* ,pt_size
              ,uom
              ,full_desc
              ,MODEL
              ,maker
              ,USAGE
              ,last_price
              ,plf_type
              ,pco_type
              ,seller
              ,lead_time
              ,is_use
              ,REMARK
              ,upd_date
              ,upd_by
              ,mro_type
              ,kind
              ,var_class
              ,vendor_desc*/
          )VALUES(
              v_comp_no
              ,v_partId
              ,c1.matnr --품번
              ,c1.maktx  --품명 
              ,'N'
              ,'SPPT' --자재와 공구 구분 (자재)
              ,'Y'
              ,c1.wgbez
              ,c1.matnr||','||c1.maktx||','||c1.groes  --품명 
              ,v_partId
          /*    ,c1.groes  --규격 
              ,c1.meins  --단위 
              ,c1.maktx||'|'||c1.groes||'|'||c1.meins
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,'Y' --isUse
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,'' */
          );
          
          --INSERT STOCK 
          INSERT INTO TAPTSTOCK                            
        (comp_no , wcode_id, part_id, part_grade,        
        stock_qty, bin_no, unit_price)                
        SELECT x.comp_no,y.wcode_id, x.part_id, z.cdsysd_no,0,'',x.last_price
        FROM TAPARTS x, TAWAREHOUSE y, (SELECT * FROM TACDSYSD WHERE list_type='PART_GRADE') z
        WHERE x.comp_no  = y.comp_no        
        AND x.comp_no =    v_comp_no                
        AND x.part_id =   v_partId            
        AND z.is_use=        'Y'                
        AND y.wh_categ=    'PART'            
        AND y.is_use=        'Y';  

           UPDATE MWARE_EAI.TXERPPARTS SET
                ifresult = 'Y'
                ,received_date = c1.received_date
            WHERE TRIM(bukrs) = c1.bukrs
                 AND TRIM(werks) = c1.werks
                 AND TRIM(matnr) = c1.matnr
            ;
            
       ELSE
          
             UPDATE  TAPARTS
                  SET    part_no = c1.matnr
                            ,part_group = c1.wgbez
                          /* ,description = c1.maktx
                            ,pt_size = c1.groes
                            ,uom = c1.meins
                            ,full_desc = c1.maktx||'|'||c1.groes||'|'||c1.meins
                            ,is_use = 'Y'
                            ,part_group = c1.matkl */
                            ,is_inpart = 'N'
                            ,part_categ='SPPT'
            WHERE 1=1
                AND comp_no = v_comp_no
                AND TRIM(part_no) = c1.matnr
           ;
           
            UPDATE MWARE_EAI.TXERPPARTS SET
                ifresult = 'Y'
                ,received_date = c1.received_date
            WHERE TRIM(bukrs) = c1.bukrs
                 AND TRIM(werks) = c1.werks
                 AND TRIM(matnr) = c1.matnr
            ;
            
       END IF;
       


    END LOOP;
    
MERGE INTO TXERPPARTS a
    USING( SELECT
            TRIM(bukrs) AS bukrs , --회사코드
            TRIM(werks) AS werks, --플랜트
            TRIM(matnr) AS matnr, -- 품번
            TRIM(maktx) AS maktx, --품명
            TRIM(groes) AS groes, --규격
            TRIM(mtart) AS mtart, --자재유형코드
            TRIM(mtbez) AS mtbez, -- 자재유형명
            TRIM(matkl) AS matkl, -- 자재그룹코드
            TRIM(wgbez) AS wgbez, --자재그룹명
            TRIM(lgfsb) AS lgfsb, --저장위치
            TRIM(meins) AS meins, --단위
            TRIM(bklas) AS bklas, --평가 Calss 코드
            TRIM(erdat) AS erdat, --생성 또는 수정일
            TRIM(lvorm) AS lvorm, --삭제 지시자 
            TRIM(wlvorm) AS wlvorm, --플랜트 레벨 삭제 지시자
            TRIM(ifresult) AS ifresult, --IF전송 FLAG
            TRIM(ifdate) AS ifdate, --IF생성일자
            TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi:ss') received_date
        FROM MWARE_EAI.TXERPPARTS
        WHERE 1=1
        ) b
     ON(TRIM(a.bukrs) = b.bukrs
           AND TRIM(a.werks) = b.werks
           AND TRIM(a.matnr) = b.matnr)
     WHEN MATCHED THEN
           UPDATE SET 
                  --  a.bukrs= b.bukrs , --회사코드
                 --   a.werks= b.werks, --플랜트
                 --   a.matnr= b.matnr, -- 품번
                    a.maktx= b.maktx, --품명
                    a.groes= b.groes, --규격
                    a.mtart= b.mtart, --자재유형코드
                    a.mtbez= b.mtbez, -- 자재유형명
                    a.matkl= b.matkl, -- 자재그룹코드
                    a.wgbez= b.wgbez, --자재그룹명
                    a.lgfsb= b.lgfsb, --저장위치
                    a.meins= b.meins, --단위
                    a.bklas= b.bklas, --평가 Calss 코드
                    a.erdat= b.erdat, --생성 또는 수정일
                    a.lvorm= b.lvorm, --삭제 지시자 
                    a.wlvorm= b.wlvorm, --플랜트 레벨 삭제 지시자
                    a.ifresult= b.ifresult, --IF전송 FLAG
                    a.ifdate= b.ifdate --IF생성일자
               --     a.received_date = b.received_date
        WHEN NOT MATCHED THEN                                         
              INSERT ( a.bukrs , --회사코드
                        a.werks, --플랜트
                        a.matnr, -- 품번
                        a.maktx, --품명
                        a.groes, --규격
                        a.mtart, --자재유형코드
                        a.mtbez, -- 자재유형명
                        a.matkl, -- 자재그룹코드
                        a.wgbez, --자재그룹명
                        a.lgfsb, --저장위치
                        a.meins, --단위
                        a.bklas, --평가 Calss 코드
                        a.erdat, --생성 또는 수정일
                        a.lvorm, --삭제 지시자 
                        a.wlvorm, --플랜트 레벨 삭제 지시자
                        a.ifresult, --IF전송 FLAG
                        a.ifdate, --IF생성일자
                        a.received_date
                     )                                                    
              VALUES (b.bukrs , --회사코드
                        b.werks, --플랜트
                        b.matnr, -- 품번
                        b.maktx, --품명
                        b.groes, --규격
                        b.mtart, --자재유형코드
                        b.mtbez, -- 자재유형명
                        b.matkl, -- 자재그룹코드
                        b.wgbez, --자재그룹명
                        b.lgfsb, --저장위치
                        b.meins, --단위
                        b.bklas, --평가 Calss 코드
                        b.erdat, --생성 또는 수정일
                        b.lvorm, --삭제 지시자 
                        b.wlvorm, --플랜트 레벨 삭제 지시자
                        b.ifresult, --IF전송 FLAG
                        b.ifdate, --IF생성일자
                        b.received_date
                     )       
      ;

    UPDATE TABATPGM SET 
         exe_by = (SELECT user_id 
                        FROM TAUSER 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPARTS'
    ;

END;
/


/**
 * 2017-05-12 김정우 추가 
 */
CREATE OR REPLACE PROCEDURE SP_PM_MAKE_WO_BY_SCHEDID(
      v_comp_no     IN varchar2      
     ,v_user_no      IN varchar2
     ,v_pmsched_id IN number
) IS
    v_count                         number(4); 
    v_wkor_id                     number(18);
    
    -- exec SP_PM_MAKE_WO_BY_SCHEDID('120','ADMIN',0000);

     
        
BEGIN

  SELECT  SQAWKOR_ID.NEXTVAL
  INTO v_wkor_id
  FROM dual;

   --W/O CREATE 
     INSERT INTO taworkorder(
                 comp_no
                ,wkor_id
                ,wo_no
                ,description
                ,wo_status
                ,wo_type 
                ,pm_type
                ,pm_id
                ,pmsched_id
                ,start_date
                ,end_date
                ,dept_id
                ,emp_id
                ,wo_gen_type
                ,wkor_date
                ,wkctr_id
                ,upd_date
                ,upd_by
            )
            SELECT 
                 a.comp_no
                ,v_wkor_id
                ,v_wkor_id
                ,'['||a.wo_type||']'||a.description
                ,'P'
                ,a.wo_type
                ,a.pm_type
                ,a.pm_id
                ,a.pmsched_id
                ,a.sched_date
                ,a.sched_date
                ,a.dept_id
                ,a.main_mng_id
                ,'PMPLAN'
                ,a.wkor_date
                ,a.wkctr_id
                ,to_char(sysdate,'yyyymmdd')
                ,(SELECT user_id FROM TAUSER WHERE comp_no = a.comp_no AND user_no = v_user_no AND ROWNUM=1)
            FROM ( SELECT x.comp_no comp_no
                                    ,y.description description
                                    ,y.pm_type pm_type
                                    ,y.pm_id pm_id
                                    ,y.wo_type
                                    ,x.pmsched_id pmsched_id
                                    ,y.dept_id dept_id
                                    ,x.sched_date sched_date
                                    ,x.sched_date wkor_date
                                    ,(SELECT MIN(b.main_mng_id)
                                          FROM TAPMEQUIP a, TAEQUIPMENT b
                                          WHERE a.comp_no = b.comp_no
                                          AND a.equip_id = b.equip_id
                                          AND a.pm_id = y.pm_id
                                          GROUP BY a.comp_no, a.pm_id    ) AS main_mng_id
                                    ,x.sched_date plan_date
                                    ,y.wkctr_id wkctr_id
                           FROM TAPMSCHED x, TAPMLST y
                           WHERE x.comp_no  = y.comp_no
                           AND x.pm_id    = y.pm_id
                           AND x.wkor_id IS NULL
                           AND y.is_active = 'Y'
                           AND x.pmsched_status = 'P'
                           AND x.comp_no = v_comp_no
                           AND x.pmsched_id = v_pmsched_id
             ) a
            ;
            --W/O Equipment CREATE
           INSERT INTO TAWOEQUIP(comp_no,woequip_id,wkor_id,equip_id)
           SELECT a.comp_no,SQAWOEQUIP_ID.NEXTVAL,a.wkor_id,b.equip_id
           FROM TAWORKORDER a, TAPMEQUIP b
           WHERE a.comp_no = b.comp_no
           AND a.pm_id = b.pm_id
           AND a.wo_status = 'P'
           AND a.wo_type IN ('PMI','PMW')
           AND a.comp_no = v_comp_no
           AND a.pmsched_id IN
                ( SELECT x.pmsched_id
                  FROM TAPMSCHED x, TAPMLST y  
                  WHERE x.comp_no  = y.comp_no
                  AND x.pm_id    = y.pm_id
                  AND x.comp_no = v_comp_no
                  AND y.is_active = 'Y'
                  AND x.pmsched_id = v_pmsched_id
                  )
           ;
           --UPDATE PM SCHED STATUS
           UPDATE TAPMSCHED x SET x.pmsched_status = 'S'
                                                    ,x.wkor_id = (SELECT wkor_id
                                                                        FROM TAWORKORDER
                                                                        WHERE comp_no = x.comp_no
                                                                        AND pmsched_id = x.pmsched_id
                                                                        AND comp_no = v_comp_no)
           WHERE 1=1
           AND x.pmsched_id = v_pmsched_id
           AND x.comp_no = v_comp_no
           AND (SELECT is_active
                    FROM TAPMLST
                    WHERE comp_no = x.comp_no
                    AND pm_id = x.pm_id
                    AND comp_no = v_comp_no) = 'Y'
          ;
          --W/O  PARTS CREATE
            INSERT INTO TAWOPARTS(
                comp_no
                ,wopart_id
                ,wkor_id
                ,wcode_id
                ,part_id
                ,part_grade
                ,use_qty
                ,unit_price
                ,tot_price
            )
            SELECT x.comp_no AS comp_no
                ,sqawopart_id.NEXTVAL AS wopart_id
                ,x.wkor_id     AS wkor_id
                ,(SELECT wcode_id 
                     FROM TADEPT 
                   WHERE dept_id = 
                                            (SELECT dept_id FROM TAPMLST WHERE comp_no = x.comp_no AND pm_id = x.pm_id)
                        AND comp_no = x.comp_no) AS wcode_id
                ,y.part_id     AS part_id
                ,'A'              AS part_grade
                ,y.use_qty   AS use_qty
                ,z.last_price  AS unit_price
                ,y.use_qty * z.last_price AS tot_price
            FROM TAPMSCHED x, TAPMPART y, TAPARTS z
            WHERE x.comp_no = y.comp_no
            AND y.comp_no = z.comp_no
            AND x.pm_id = y.pm_id
            AND y.part_id = z.part_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            ;
            
            -- W/O  CRAFT CREATE
            INSERT INTO TAWOCRAFT(
                    comp_no,        wocraft_id,        wkor_id,
                    emp_id,            start_date,        end_date
                )
            SELECT x.comp_no,        SQAWOCRAFT_ID.NEXTVAL, x.wkor_id,
                        (SELECT MIN(b.main_mng_id)
                        FROM TAPMEQUIP a, TAEQUIPMENT b
                        WHERE a.comp_no = b.comp_no
                        AND a.equip_id = b.equip_id
                        AND a.pm_id = y.pm_id
                        AND a.comp_no = y.comp_no
                        GROUP BY a.comp_no, a.pm_id    ) AS main_mng_id,
                        x.sched_date,x.sched_date
            FROM TAPMSCHED x, TAPMLST y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            AND   (SELECT MIN(b.main_mng_id)
                      FROM TAPMEQUIP a, TAEQUIPMENT b
                      WHERE a.comp_no = b.comp_no
                      AND a.equip_id = b.equip_id
                      AND a.pm_id = y.pm_id
                      AND a.comp_no = y.comp_no
                      GROUP BY a.comp_no, a.pm_id    ) IS NOT NULL
            ;
            
            --W/O  POINT CREATE
            INSERT INTO TAWOPOINT(
                    comp_no,        wopoint_id,             wkor_id,
                    pm_point_id,    pm_point_rslt_status,   pm_point_rep_status
                    )
            SELECT x.comp_no,     SQAWOPOINT_ID.NEXTVAL,  x.wkor_id,
                        y.pm_point_id, (SELECT cdsysd_no FROM 
                        (SELECT * FROM tacdsysd
                        WHERE list_type='PM_POINT_RSLT_STATUS'
                        ORDER BY ord_no)
                        WHERE ROWNUM=1),                   'GD'
            FROM TAPMSCHED x, TAPMPOINT y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            ;
            
            -- UPDATE LAST SCHED DATE
            UPDATE TAPMLST a SET a.last_sch_date =
                    DECODE(a.period_type,
                                'Y',to_char(add_months(to_date(a.last_sch_date,'yyyymmdd'), 1*a.cycle*12), 'yyyymmdd'),
                                'M',to_char(add_months(to_date(a.last_sch_date,'yyyymmdd'), a.cycle*1), 'yyyymmdd'),
                                'W',to_char(to_date(a.last_sch_date,'yyyymmdd')+1*a.cycle*7, 'yyyymmdd'),
                                'D',to_char(to_date(a.last_sch_date,'yyyymmdd')+a.cycle*1, 'yyyymmdd'))
            WHERE 1=1
            AND a.comp_no = v_comp_no
            AND a.is_active = 'Y'
            AND a.pm_id IN (SELECT pm_id
                                    FROM TAPMSCHED
                                    WHERE comp_no = a.comp_no
                                    AND pmsched_id = v_pmsched_id
                                    )
            ;
            
            
            SP_WOMAKE_4WP_BYALL(v_comp_no);
            
END;
/

/**
 * 2017-05-12 김정우 추가 
 */
CREATE OR REPLACE PROCEDURE SP_SET_WORKCALENDAR(
       v_comp_no                         IN varchar2 
      ,v_user_no                          IN varchar2
)IS
    v_count                  number(4);  
    v_is_work               char(1);

/*set default value in calendar and work calendar*/
      
    CURSOR c_plant IS
        SELECT plant 
        FROM TAPLANT
        WHERE comp_no = v_comp_no
       ;
    
    CURSOR c_calendar_data IS
      SELECT 
                  to_char(add_months(add_months(sysdate,-2),120) + rn-1,'yyyymmdd') AS tday
                 ,to_char(add_months(add_months(sysdate,-2),120) + rn-1,'yyyymm') AS tmonth
                 ,to_char(add_months(add_months (sysdate,-2),120) + rn-1,'iyyy') AS tyear
                 ,to_char(add_months(add_months (sysdate,-2),120) + rn-1,'D') AS dow         --요일1:일, 2:월...7:토
                 ,to_char(add_months(add_months (sysdate,-2),120) + rn-1,'IW') AS week      --ISO 표준주차(월 ~ 일)
                 ,to_char(add_months(add_months (sysdate,-2),120) + rn-1,'Q') AS tquarter  --분기..
            FROM (
                SELECT  ROWNUM AS rn
                FROM user_tab_columns
                WHERE ROWNUM <= (add_months(sysdate,120) - sysdate) - (add_months(sysdate,108) - sysdate) + 1
                ORDER BY 1
            )
            ORDER BY 1
          ;
    
    CURSOR c_wrk_calendar_data IS
        SELECT 
             tday
            ,dow
        FROM taday
        WHERE     1=1
            AND tday >= to_char(add_months(sysdate,-2),'yyyymmdd')
            --and tday >= add_months(to_char(sysdate,'yyyymmdd'),22)    -- to_char(sysdate,'yyyymmdd')  , add_months(to_char(sysdate,'yyyymmdd'),22)  
            AND tday <= to_char(add_months(add_months(sysdate,-2),48), 'yyyymmdd')
        ORDER BY tday    
        ;
        
    
BEGIN
    
       /*1 year period data create after 10 years .*/
       FOR c1 IN c_calendar_data LOOP
              
              SELECT count(*)
              INTO v_count
              FROM taday
              WHERE 1=1
                  AND tday = c1.tday
              ;
              
              IF v_count  = 0 THEN
                   INSERT INTO taday(seq, tday,tmonth, tquarter,thalf,tyyyy,week,dow)
                   VALUES( (SELECT max(seq) + 1 FROM taday), c1.tday, c1.tmonth, c1.tquarter
                               ,CASE WHEN c1.tquarter < '3' THEN '1' ELSE '2' END
                               , c1.tyear, c1.week, c1.dow
                   );
              END IF;
              
              SELECT count(*)
              INTO v_count
              FROM tamonth
              WHERE 1=1
                  AND tmonth = c1.tmonth
              ;
              
              IF v_count  = 0 THEN
                   INSERT INTO tamonth(seq, tmonth,tquarter,thalf,tyyyy)
                   VALUES( (SELECT max(seq) + 1 FROM tamonth), c1.tmonth, c1.tquarter
                               ,CASE WHEN c1.tquarter < '3' THEN '1' ELSE '2' END
                               , c1.tyear
                   );
              END IF;
              
              SELECT count(*)
              INTO v_count
              FROM tayear
              WHERE 1=1
                  AND tyyyy = c1.tyear
              ;
              
              IF v_count  = 0 THEN
                   INSERT INTO tayear(seq, tyyyy)
                   VALUES( (SELECT max(seq) + 1 FROM tayear) , c1.tyear
                   );
              END IF;
           
       END LOOP;
       
       
        /*2 months period data create after 2 years .*/
       FOR c2 IN c_wrk_calendar_data LOOP
          FOR c3 IN c_plant LOOP
              SELECT count(*)
              INTO v_count
              FROM TAWRKCALENDAR
              WHERE 1=1
                  AND plant = c3.plant
                  AND cal_date = c2.tday
              ;
              
              IF v_count = 0 THEN
                  IF c2.dow IN ('1','7') THEN 
                      v_is_work := 'N'; 
                  ELSE 
                      -- 국각별 국경일 추가하여 적용필요...
                      IF  substr(c2.tday, 5,4) IN ('0101','0501','0508','0705','0706','0928','1028','1117','1224','1225','1226') THEN
                          v_is_work :='N'; 
                      ELSE
                          v_is_work :='Y'; 
                      END IF;
                      
                  END IF;
                  
                  INSERT INTO TAWRKCALENDAR(comp_no, wrkcalendar_id, plant, cal_date, is_work)
                  VALUES(v_comp_no, sqawrkcalendar_id.NEXTVAL, c3.plant,c2.tday,v_is_work );
              END IF;
              
          END LOOP;
       END LOOP;
       
       
       UPDATE TABATPGM set 
         exe_by = (SELECT user_id 
                        FROM tauser 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAWRKCALENDAR'
    ;
       
       
       
       
       
END;
/


 -----------------------------------------------------------------------------
--------------------------------Mseat patch완료 2017.05.15일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 체코 patch완료 2017.05.15일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 슬로박 patch완료 2017.05.15일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 몬테레이 patch완료 2017.05.15일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 slp patch완료 2017.05.15일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 조지아 patch완료 2017.05.15일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 브라질 patch완료 2017.05.15일-------------------
-----------------------------------------------------------------------------