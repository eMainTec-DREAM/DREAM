CREATE PROCEDURE [dbo].[SP_PT_OUTSTOCK] (
       @v_comp_no      varchar(6)
      ,@v_ptisshist_id bigint
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint

    DECLARE c_iss_list CURSOR FOR
        select 
             ptisshist_id
            ,ptiss_mode
            ,ptiss_type
            ,dept_id
            ,wcode_id
            ,iss_date
            ,part_id
            ,case when part_grade = 'A' then 'A'
                  when part_grade = 'B' then 'B'
                  else 'B' end as part_grade
            ,isnull(iss_qty,0) as iss_qty
            ,isnull(unit_price,0) as unit_price
        from TAPTISSHIST
        where 1=1
            and comp_no = @v_comp_no
            and ptisshist_id = @v_ptisshist_id
        ;
    
    -- c_rec_list cursor parameter
    DECLARE  @c_ptisshist_id bigint
            ,@c_ptiss_mode   varchar(20)
            ,@c_ptiss_type   varchar(20) 
            ,@c_dept_id      bigint
            ,@c_wcode_id     bigint
            ,@c_iss_date     varchar(8)
            ,@c_part_id      bigint
            ,@c_part_grade   varchar(20)
            ,@c_iss_qty      numeric(18,3)
            ,@c_unit_price   numeric(18,3)
            
    OPEN c_pm
    FETCH NEXT FROM c_iss_list INTO @c_ptisshist_id,@c_ptiss_mode,@c_ptiss_type,@c_dept_id,@c_wcode_id,@c_iss_date,@c_part_id,@c_part_grade,@c_iss_qty,@c_unit_price
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            
            IF @c_ptiss_mode = 'C'  -- 출고
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
                             stock_qty = isnull(stock_qty,0) - @c_iss_qty
                        where 1=1
                             and comp_no = @v_comp_no
                             and wcode_id =@c_wcode_id
                             and part_id = @c_part_id
                             and part_grade = @c_part_grade
                        ;
                    else
                        insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                        values(@v_comp_no, @c_wcode_id, @c_part_id, @c_part_grade, @c_iss_qty * -1)
                        ;

               END
               
            IF @c_ptiss_mode = 'R'  -- 취소
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
                            stock_qty = isnull(stock_qty,0) + @c_iss_qty
                       where 1=1
                            and comp_no = @v_comp_no
                            and wcode_id =@c_wcode_id
                            and part_id = @c_part_id
                            and part_grade = @c_part_grade
                       ;
                   else
                       insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                       values(@v_comp_no, @c_wcode_id, @c_part_id, @c_part_grade, @c_iss_qty )
                       ;
               
               END
        
            FETCH NEXT FROM c_iss_list INTO @c_ptisshist_id,@c_ptiss_mode,@c_ptiss_type,@c_dept_id,@c_wcode_id,@c_iss_date,@c_part_id,@c_part_grade,@c_iss_qty,@c_unit_price
        END
    CLOSE c_iss_list
    DEALLOCATE c_iss_list
    
    
    