CREATE PROCEDURE [dbo].[SP_PT_INSTOCK] (
       @v_comp_no      varchar(6)
      ,@v_ptrechist_id      bigint
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    
    DECLARE c_rec_list CURSOR FOR
        select 
             ptrechist_id
            ,ptrec_mode
            ,ptrec_type
            ,dept_id
            ,wcode_id
            ,rec_date
            ,part_id
            ,case when part_grade = 'A' then 'A'
                  when part_grade = 'B' then 'B'
                  else 'B' end as part_grade
            ,isnull(rec_qty,0) as rec_qty
            ,isnull(unit_price,0) as unit_price
        from TAPTRECHIST
        where 1=1
            and comp_no = @v_comp_no
            and ptrechist_id = @v_ptrechist_id
        ;
        
    -- c_rec_list cursor parameter
    DECLARE  @c_ptrechist_id bigint
            ,@c_ptrec_mode   varchar(20)
            ,@c_ptrec_type   varchar(20) 
            ,@c_dept_id      bigint
            ,@c_wcode_id     bigint
            ,@c_rec_date     varchar(8)
            ,@c_part_id      bigint
            ,@c_part_grade   varchar(20)
            ,@c_rec_qty      numeric(18,3)
            ,@c_unit_price   numeric(18,3)
            
    OPEN c_pm
    FETCH NEXT FROM c_rec_list INTO @c_ptrechist_id,@c_ptrec_mode,@c_ptrec_type,@c_dept_id,@c_wcode_id,@c_rec_date,@c_part_id,@c_part_grade,@c_rec_qty,@c_unit_price
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            
            IF @c_ptrec_mode = 'C' -- 입고
               BEGIN
                    select @v_count = count(*)
                    from taptstock
                    where 1=1
                        and comp_no = @v_comp_no
                        and wcode_id =@c_wcode_id
                        and part_id = @c_part_id
                        and part_grade = @c_part_grade
                    ;
                       
                    if @v_count > 0
                        update taptstock set
                             stock_qty = isnull(stock_qty,0) + @c_rec_qty
                        where 1=1
                             and comp_no = @v_comp_no
                             and wcode_id =@c_wcode_id
                             and part_id = @c_part_id
                             and part_grade = @c_part_grade
                        ;
                    else
                        insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                        values(@v_comp_no, @c_wcode_id, @c_part_id, @c_part_grade, @c_rec_qty)
                        ;

                       
                    -- 최신단가 갱신
                    update taparts set
                        last_price = @c_unit_price
                    where comp_no = @v_comp_no
                        and part_id = @c_part_id
                    ;
               END
               
            IF @c_ptrec_mode = 'R' -- 취소
               BEGIN
               
                   select @v_count = count(*)
                   from taptstock
                   where 1=1
                        and comp_no = @v_comp_no
                        and wcode_id =@c_wcode_id
                        and part_id = @c_part_id
                        and part_grade = @c_part_grade
                   ;
                       
                   if @v_count > 0
                       update taptstock set
                            stock_qty = isnull(stock_qty,0) - @c_rec_qty
                       where 1=1
                            and comp_no = @v_comp_no
                            and wcode_id =@c_wcode_id
                            and part_id = @c_part_id
                            and part_grade = @c_part_grade
                       ;
                   else
                       insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                       values(@v_comp_no, @c_wcode_id, @c_part_id, @c_part_grade, @c_rec_qty * -1)
                       ;
               
               
               END
        
            FETCH NEXT FROM c_rec_list INTO @c_ptrechist_id,@c_ptrec_mode,@c_ptrec_type,@c_dept_id,@c_wcode_id,@c_rec_date,@c_part_id,@c_part_grade,@c_rec_qty,@c_unit_price
        END
    CLOSE c_rec_list
    DEALLOCATE c_rec_list
            
            

