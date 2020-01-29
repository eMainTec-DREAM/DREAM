CREATE PROCEDURE [dbo].[SP_KPI_MAKE_TAKPIMLOCDN] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    declare @v_ma_stoptime                        numeric(18)
            ,@v_ma_repairtime                      numeric(18)
            ,@v_ma_stopfreq                        numeric(18)
            ,@v_ma_repairfreq                      numeric(18)
    
    /*일일 가동시간을 계산. 전일 1일 부터 재 계산하여 입력..*/
    DECLARE c_lnwrktime CURSOR FOR
        select 
             a.plant
            ,left(a.wrk_date,6) as yyyymm
            ,a.eqloc_id
            ,sum(a.dtime) + sum(a.etime)+ sum(a.ntime) as ma_worktime
        from TALNWRKTIME a
        where 1=1
            and a.comp_no = @v_comp_no
            and a.wrk_date >=  left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
            and a.wrk_date <= CONVERT(varchar(8), getdate(), 112)
        group by a.plant, left(a.wrk_date,6), a.eqloc_id
       ;
       
       -- c_lnwrktime cursor parameter
     DECLARE @c_plant     varchar(20)
            ,@c_yyyymm    varchar(6)
            ,@c_eqloc_id   bigint
            ,@c_ma_worktime   numeric(18)

       
     /*고장시간 집계대상일자 및 라인*/
     DECLARE c_eqloc_list CURSOR FOR
            select 
                  plant
                 ,left(wrk_date,6) as yyyymm
                 ,eqloc_id
            from TALNWRKTIME a
            where 1=1
                and a.comp_no = @v_comp_no
                and a.wrk_date >= left(convert(varchar(8), DATEADD(month,-1,GETDATE()), 112),6)+ '01'  -- 한달전 1일 부터 재 계산 처리...
                and a.wrk_date <= CONVERT(varchar(8), getdate(), 112)
            group by comp_no, plant, left(wrk_date,6), eqloc_id
            order by 1,2,3
           ;
           

    OPEN c_lnwrktime
    FETCH NEXT FROM c_lnwrktime INTO @c_plant,@c_yyyymm,@c_eqloc_id,@c_ma_worktime
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            --  가동시간 Setting 
            select @v_count = count(*)
            from takpimlocdn
            where comp_no = @v_comp_no
                and plant = @c_plant
                and yyyymm = @c_yyyymm
                and eqloc_id = @c_eqloc_id
            ;
           
            if @v_count > 0
               update takpimlocdn set
                        ma_worktime = @c_ma_worktime
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and yyyymm = @c_yyyymm
                    and eqloc_id = @c_eqloc_id
                ;
            else
               insert into takpimlocdn( comp_no, plant, yyyymm, eqloc_id, ma_worktime
               ) values (
                   @v_comp_no, @c_plant, @c_yyyymm,  @c_eqloc_id, @c_ma_worktime
               );

            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_lnwrktime INTO @c_plant,@c_yyyymm,@c_eqloc_id,@c_ma_worktime
        END
    CLOSE c_lnwrktime
    DEALLOCATE c_lnwrktime
    
    
    
    OPEN c_eqloc_list
    FETCH NEXT FROM c_eqloc_list INTO @c_plant,@c_yyyymm,@c_eqloc_id
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -------------------------------------------------------------------------------------------
            --//1일 ~ 현재일까지 누적 데이타 추출...
              select @v_ma_stoptime = sum(a.d_stoptime)
                      ,@v_ma_repairtime = sum(a.d_repairtime)
                      ,@v_ma_stopfreq = sum(a.d_stopfreq)
                      ,@v_ma_repairfreq = sum(a.d_repairfreq)
              from (SELECT
                         a.comp_no
                        ,e.plant
                        ,left(a.wkor_date,6)  as yyyymm
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
                        AND a.wkor_date >= @c_yyyymm + '01'
                        AND a.wkor_date <= @c_yyyymm + '31'
                        AND a.wo_type = 'BM'                                     --고장작업만..
                GROUP BY a.comp_no, e.plant, left(a.wkor_date,6), d.eqloc_id
                ) a
              where comp_no = @v_comp_no
                  and plant = @c_plant
                  and a.eqloc_id in (select eqloc_id from dbo.SFAEQLOC_CALL(@v_comp_no, @c_eqloc_id, '') )
              ;
              
              update takpimlocdn set
                         ma_stoptime = @v_ma_stoptime
                        ,ma_bd_rate = round( (@v_ma_stoptime/(case when ma_worktime=0 then 1 else ma_worktime end)) * 100,2) 
                        ,ma_bd_eq_rate = round( (@v_ma_repairtime/(case when ma_worktime =0 then 1 else ma_worktime end)) * 100,2) 
                        ,ma_repairtime = @v_ma_repairtime
                        ,ma_stopfreq = @v_ma_stopfreq
                        ,ma_repairfreq = @v_ma_repairfreq
                        ,mtbf = ma_worktime/(case when @v_ma_stopfreq=0 then 1 else @v_ma_stopfreq end)
                        ,mtbf_eq = ma_worktime/(case when @v_ma_repairfreq=0 then 1 else @v_ma_repairfreq end)
                        ,mttr = @v_ma_stoptime/(case when @v_ma_stopfreq=0 then 1 else @v_ma_stopfreq end)
                        ,mttr_eq = @v_ma_repairtime/(case when @v_ma_repairfreq=0 then 1 else @v_ma_repairfreq end)
                        ,mtba = ma_worktime/(case when @v_ma_repairfreq=0 then 1 else @v_ma_repairfreq end)
                where comp_no = @v_comp_no
                    and plant = @c_plant
                    and yyyymm = @c_yyyymm
                    and eqloc_id = @c_eqloc_id
                ;
            
            -------------------------------------------------------------------------------------------
            FETCH NEXT FROM c_eqloc_list INTO @c_plant,@c_yyyymm,@c_eqloc_id
        END
    CLOSE c_eqloc_list
    DEALLOCATE c_eqloc_list
               
  
    
   
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TAKPIMLOCDN'
    ;
    
    