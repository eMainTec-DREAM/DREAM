
CREATE PROCEDURE [dbo].[SP_MUP_TYVENDOR] (
      @v_comp_no     varchar(6)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count as bigint;
    
    DECLARE  
             @c_sn            varchar(10)
            ,@c_vendor_no     nvarchar(30)
            ,@c_description   nvarchar(240)
            ,@c_rep_name      nvarchar(100)
            ,@c_address       nvarchar(500)
            
          
    
    DECLARE c_excel_data CURSOR FOR
                select 
                      sn
                     ,vendor_no
                     ,description
                     ,rep_name
                     ,address
                from TYVENDOR a
                where 1=1
                order by sn                
        ;         
        
     
    OPEN c_excel_data
    FETCH NEXT FROM c_excel_data INTO 
                 @c_sn            
                ,@c_vendor_no     
                ,@c_description   
                ,@c_rep_name      
                ,@c_address        
    
      WHILE (@@FETCH_STATUS=0)
          BEGIN
          
          select 
                 @v_count = count(*)
            from TAVENDOR
           where 1=1
             and comp_no    = @v_comp_no
             and vendor_no  = @c_vendor_no
          ;
          
        if @v_count > 0 
                      
                 update TAVENDOR set
                        description = @c_description
                       ,repname     = @c_rep_name
                       ,address     = @c_address
                   where 1=1
                     and comp_no   = @v_comp_no
                     and vendor_no = @c_vendor_no                       
                    ;
          
          else
                   insert into TAVENDOR(
                                        comp_no
                                       ,vendor_id
                                       ,vendor_no
                                       ,description
                                       ,repname
                                       ,address
                                       )
                                values(
                                       @v_comp_no
                                      ,NEXT VALUE FOR sqavendor_id
                                      ,@c_vendor_no
                                      ,@c_description
                                      ,@c_rep_name
                                      ,@c_address
                                      )
                                  ;
                   
             
              

          end
          
      FETCH NEXT FROM c_excel_data INTO 
                             @c_sn            
                            ,@c_vendor_no     
                            ,@c_description   
                            ,@c_rep_name      
                            ,@c_address   
      CLOSE c_excel_data
      
      DEALLOCATE c_excel_data

     
