CREATE PROCEDURE [dbo].[SP_IF_UPD_TXEQUIPMENT] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(30)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          as bigint
    
    DECLARE c_if_list CURSOR FOR
        select 
             a.equip_id
            ,a.item_no
            ,a.description
            ,(select aa.full_desc 
               from taeqloc aa 
               where a.comp_no = aa.comp_no 
                   and a.eqloc_id = aa.eqloc_id
             ) as full_desc
            ,CONVERT(varchar(8), getdate(), 112) as ifdate
        from taequipment a
        where 1=1
            and equip_id in (select equip_id
                               from TAEQALTHIST 
                               where comp_no = @v_comp_no
                                   and upd_date >= (select last_exe_date
                                                    from TABATPGM 
                                                    where comp_no = @v_comp_no
                                                          and batpgm_no = 'TXEQUIPMENT' 
                                                    )
                            )
        ;
        
      
    -- c_if_list cursor parameter
    DECLARE  @c_equip_id      bigint
            ,@c_item_no       varchar(40)
            ,@c_description   nvarchar(200) 
            ,@c_full_desc     nvarchar(200) 
            ,@c_ifdate     varchar(8)
            
    OPEN c_if_list
    FETCH NEXT FROM c_if_list INTO @c_equip_id,@c_item_no,@c_description,@c_full_desc,@c_ifdate
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            select @v_count = count(*)
            from TXEQUIPMENT
            where equip_id = @c_equip_id
            ;
            
            if @v_count > 0
                update TXEQUIPMENT set
                     description = @c_description
                    ,full_desc = @c_full_desc
                where equip_id = @c_equip_id
                ;
            
            else
                insert into TXEQUIPMENT(equip_id, item_no, description, full_desc, ifdate)
                values(@c_equip_id, @c_item_no, @c_description, @c_full_desc, @c_ifdate)
                ;
            
            FETCH NEXT FROM c_if_list INTO @c_equip_id,@c_item_no,@c_description,@c_full_desc,@c_ifdate
        END
    CLOSE c_if_list
    DEALLOCATE c_if_list
    
    
    update TABATPGM set 
         exe_by = (select top 1 user_id 
                        from tauser 
                        where comp_no = @v_comp_no 
                            and user_no = @v_user_no 
                       )
        ,last_exe_date = convert(varchar(8), getdate(), 112)
        ,last_exe_time = GETDATE()
    where comp_no = @v_comp_no
        and batpgm_no = 'TXEQUIPMENT'
    ;
    
        
   