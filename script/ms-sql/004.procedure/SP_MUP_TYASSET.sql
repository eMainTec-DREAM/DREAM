CREATE PROCEDURE [dbo].[SP_MUP_TYASSET] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    DECLARE @v_count as bigint
    DECLARE  @c_sn  varchar(10)
            ,@c_asset_no nvarchar(60)
            ,@c_description nvarchar(400)
            ,@c_acq_date varchar(20)
            ,@c_buyer_amt numeric(18)
            ,@c_dep_amt numeric(18)
            ,@c_res_amt numeric(18)
    
    DECLARE c_data CURSOR FOR
        select 
             SN
            ,asset_no
            ,description
            ,replace(replace(acq_date,'-',''),'.','') as acq_date
            ,convert(numeric, buyer_amt) as buyer_amt
            ,convert(numeric,dep_amt) as dep_amt
            ,convert(numeric,res_amt) as res_amt
        from TYASSET a
        where 1=1
        order by sn
        ;         
        
     
    OPEN c_excel_data
    FETCH NEXT FROM c_excel_data INTO @c_sn
                                     ,@c_asset_no
                                     ,@c_description
                                     ,@c_acq_date
                                     ,@c_buyer_amt
                                     ,@c_dep_amt
                                     ,@c_res_amt
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
          -----------------------------------------------------------------------------------------------------------------------------------------
          -----------------------------------------------------------------------------------------------------------------------------------------
          
          select @v_count = count(*)
          from TAASSET
          where comp_no = @v_comp_no
              and asset_no = @c_asset_no
          ;
          
          if @v_count > 0 
              update TAASSET set
                   description = @c_description
                   ,acq_date =@c_acq_date
                   ,buyer_amt = @c_buyer_amt
                   ,dep_amt = @c_dep_amt
                   ,res_amt = @c_res_amt
               where  comp_no = @v_comp_no
                   and asset_no = @c_asset_no
               ;
          
          else
               insert into TAASSET(comp_no, asset_id, asset_no, description, acq_date, buyer_amt, dep_amt,res_amt)
               values(@v_comp_no, NEXT VALUE FOR sqaasset_id, @c_asset_no, @c_description, @c_acq_date, @c_buyer_amt, @c_dep_amt, @c_res_amt)
               ;
             
              
          -----------------------------------------------------------------------------------------------------------------------------------------
          -----------------------------------------------------------------------------------------------------------------------------------------
          FETCH NEXT FROM c_excel_data INTO @c_sn
                                     ,@c_asset_no
                                     ,@c_description
                                     ,@c_acq_date
                                     ,@c_buyer_amt
                                     ,@c_dep_amt
                                     ,@c_res_amt
          END
      CLOSE c_excel_data
      DEALLOCATE c_excel_data
     
