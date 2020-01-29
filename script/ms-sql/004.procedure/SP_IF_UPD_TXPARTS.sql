CREATE PROCEDURE [dbo].[SP_IF_UPD_TXPARTS] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(30)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    declare @v_dept_no        as varchar(10)
    declare @v_dept_name      as nvarchar(300)
    
    DECLARE c_data CURSOR FOR  
        select 
             a.part_id
            ,a.part_no
            ,a.description + ',' + a.pt_size as full_desc
            ,a.last_price
            ,CONVERT(varchar(8), getdate(), 112) as ifdate
        from taparts a
        where 1=1
            and upd_date >= (select last_exe_date 
                             from TABATPGM 
                             where comp_no = @v_comp_no 
                                   and batpgm_no = 'TXPARTS' 
                             )
        ;


    -- c_data cursor parameter
    DECLARE  @c_part_id      bigint
            ,@c_part_no      varchar(40)
            ,@c_full_desc    nvarchar(2048) 
            ,@c_last_price   numeric(18,3) 
            ,@c_ifdate       varchar(8)
            
    OPEN c_data
    FETCH NEXT FROM c_data INTO @c_part_id,@c_part_no,@c_full_desc,@c_last_price,@c_ifdate
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            select @v_count =  count(*)
            from TAPTUSEDDEPT a,tadept b
            where a.comp_no = b.comp_no
                and a.dept_id = b.dept_id
                and a.part_id = @c_part_id
                and a.comp_no = @v_comp_no
            ;
            
            if @v_count > 0
                select top 1 @v_dept_no = b.dept_no, @v_dept_name = b.description
                from TAPTUSEDDEPT a,tadept b
                where a.comp_no = b.comp_no
                    and a.dept_id = b.dept_id
                     and a.part_id = @c_part_id
                    and a.comp_no = @v_comp_no
                ;
            else
                select @v_dept_no = '',@v_dept_name = ''
                ;
                
        
            select @v_count =  count(*)
            from TXPARTS
            where part_id= @c_part_id
            ;
            
            if @v_count > 0
                update TXPARTS set
                     full_desc = @c_full_desc
                     ,last_price = @c_last_price
                     ,ifdate = @c_ifdate
                     ,dept_no = @v_dept_no
                     ,dept_name = @v_dept_name
                where part_id = @c_part_id
                ;
                
            else
                insert into TXPARTS(part_id, part_no, full_desc, last_price, ifdate,dept_no, dept_name)
                values(@c_part_id, @c_part_no, @c_full_desc, @c_last_price, @c_ifdate, @v_dept_no, @v_dept_name)
                ;
                
            
            FETCH NEXT FROM c_data INTO @c_part_id,@c_part_no,@c_full_desc,@c_last_price,@c_ifdate
        END
    CLOSE c_data
    DEALLOCATE c_data        
    
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TXPARTS'
    ;
    
         