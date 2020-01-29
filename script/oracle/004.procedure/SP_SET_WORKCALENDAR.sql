/* 1. taday, tamonth, tayear는 시스템에서 사용하는 dumy테이블인데,이곳에 데이타를 매일 매일 만들어줌.
 * 2. 카렌다 마스터(TAWRKCALLIST)를 확인하여  근무카런다(TAWRKCALENDAR)에 default 값을 향후 1년 1개월까지 자동으로 만들어줌.
 * 3. 야간에 scheduler를 이용하여 근무카렌다(TAWRKCALENDAR)에 데이타가 오늘 시점이후 미래 1년 1개월 데이타를 default로 만들어줌. 
 * TAWRKCALLIST(카런다마스터), TAWRKCALDOWSET(요일별 휴무일 셋팅), TAWRKCALDAYSET(반족적 또는 일회성 휴일값 셋팅) 
 * 2017.07.20. 박철완 수정
 *    수정내용: 카렌다 마스터(TAWRKCALLIST)가 신규로 만들어져서 procedure를 전반적으로 수정함.
 * */
CREATE OR REPLACE procedure DREAM.SP_SET_WORKCALENDAR(
       v_comp_no                         in varchar2 
      ,v_user_no                          in varchar2
)is
    v_count                  number(4);  
    v_is_work                 char(1);
    
    -- 오늘일자를 기준으로 올해 1일 부터 미래 2년 1개월의 일자를 새로 만듬. 
    cursor c_dumy_data is
            select 
                  to_char(trunc(sysdate,'yyyy') + rn-1,'yyyymmdd') as tday
                 ,to_char(trunc(sysdate,'yyyy') + rn-1,'yyyymm') as tmonth
                 ,to_char(trunc(sysdate,'yyyy') + rn-1,'iyyy') as tyear
                 ,to_char(trunc(sysdate,'yyyy') + rn-1,'D') as dow         --요일1:일, 2:월...7:토
                 ,to_char(trunc(sysdate,'yyyy') + rn-1,'IW') as week      --ISO 표준주차(월 ~ 일)
                 ,to_char(trunc(sysdate,'yyyy') + rn-1,'Q') as tquarter  --분기..
            from (
                select  rownum as rn
                from user_tab_columns
                where rownum <= (add_months(sysdate,13) - sysdate)
                order by 1
            )
            order by 1
          ;

    -- 오늘일자를 기준으로 미래 1년 1개월의 일자를 근무달력을 default로 만들어 둠. 
    cursor c_day_data is
       select 
             tday
            ,dow
        from taday
        where     1=1
            and tday >=to_char(trunc(sysdate,'yyyy'),'yyyymmdd')
            and tday <= to_char(add_months(trunc(sysdate,'yyyy'),24)-1,'yyyymmdd')
        order by tday    
        ;     
        
        
        --- 카런다 마스터
        cursor c_wrk_calendar_data is
             select  wrkcallist_id, plant
             from TAWRKCALLIST
             where comp_no = v_comp_no
                 and is_use = 'Y'   
             ;
begin
       -- 미래 2년 + 1개월치 날짜를 새로게 만듬.
       for c1 in c_dumy_data loop
              
              select count(*)
              into v_count
              from taday
              where 1=1
                  and tday = c1.tday
              ;
              
              if v_count  = 0 then
                   insert into taday(seq, tday,tmonth, tquarter,thalf,tyyyy,week,dow)
                   values( (select max(seq) + 1 from taday), c1.tday, c1.tmonth, c1.tquarter
                               ,case when c1.tquarter < '3' then '1' else '2' end
                               , c1.tyear, c1.week, c1.dow
                   );
              end if;
              
              select count(*)
              into v_count
              from tamonth
              where 1=1
                  and tmonth = c1.tmonth
              ;
              
              if v_count  = 0 then
                   insert into tamonth(seq, tmonth,tquarter,thalf,tyyyy)
                   values( (select max(seq) + 1 from tamonth), c1.tmonth, c1.tquarter
                               ,case when c1.tquarter < '3' then '1' else '2' end
                               , c1.tyear
                   );
              end if;
              
              select count(*)
              into v_count
              from tayear
              where 1=1
                  and tyyyy = c1.tyear
              ;
              
              if v_count  = 0 then
                   insert into tayear(seq, tyyyy)
                   values( (select max(seq) + 1 from tayear) , c1.tyear
                   );
              end if;
           
       end loop;
       
       
       
       
       for c2 in c_day_data loop
       
          for c3 in c_wrk_calendar_data loop
             -- 해당일자가 휴일인지, 평일인지 찾는다.
             -- is_off가 Y이면 휴일이므로 값을 바꿔준다.
             select case when nvl(max(is_off),'N') = 'Y' then 'N' else 'Y' end 
             into v_is_work
             from TAWRKCALDOWSET
             where 1=1
                and comp_no = v_comp_no
                and wrkcallist_id =c3.wrkcallist_id
                and dow = c2.dow
             ;
             
             -- 평일이라고 하더라도 TAWRKCALDAYSET에 임시 휴일로 셋팅해 두면 휴일로 적용한다.
             select count(*) 
             into v_count
             from TAWRKCALDAYSET
             where comp_no = v_comp_no
                 and wrkcallist_id = c3.wrkcallist_id
                 and (case when is_repeat = 'Y' then substr(cal_date,5,4) 
                                else cal_date end ) = (case when is_repeat = 'Y' then substr(c2.tday,5,4) 
                                                                 else c2.tday end )
                 and is_off = 'Y'
             ; 
           
              if v_count > 0 then
                  v_is_work := 'Y';
              end if;
             
              select count(*)
              into v_count
              from TAWRKCALENDAR
              where 1=1
                  and comp_no = v_comp_no
                  and wrkcallist_id = c3.wrkcallist_id
                  and cal_date = c2.tday
              ;
              
              if v_count > 0 then
                  update TAWRKCALENDAR set is_work = v_is_work
                  where 1=1
                      and comp_no = v_comp_no
                      and wrkcallist_id = c3.wrkcallist_id
                      and cal_date = c2.tday
                  ;
              else
                  insert into TAWRKCALENDAR(comp_no, wrkcalendar_id, plant, cal_date, is_work, wrkcallist_id)
                  values(v_comp_no, sqawrkcalendar_id.nextval, c3.plant,c2.tday,v_is_work ,c3.wrkcallist_id);
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
        and batpgm_no = 'TAWRKCALENDAR'
    ;
       
       
       
       
       
end;
/
