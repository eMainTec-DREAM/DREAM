CREATE   FUNCTION [dbo].[SFPMRESULTVALUE]( 
      @pCompNo VARCHAR(6)
    , @pPmPointId bigint
    , @pType NVARCHAR(20)
    , @pCnt int
)

RETURNS NVARCHAR(256)

BEGIN
      DECLARE @v_result_status AS NVARCHAR(20);
      DECLARE @v_result_value AS NVARCHAR(20);
      DECLARE @v_close_date AS NVARCHAR(8);
      DECLARE @description AS NVARCHAR(8);
      
   select 
          @v_result_status = pm_point_rslt_status
         ,@v_result_value = CONVERT(nvarchar(20),result_value)
         ,@v_result_value = close_date
    from (         
                select 
                      row_number() over (partition by aa.pm_point_id order by bb.comp_no, bb.wkor_date desc) as rn
                     ,aa.pm_point_id
                     ,aa.pm_point_rslt_status
                     ,aa.result_value
                     ,bb.close_date
                from tawopoint aa, taworkorder bb
                where 1=1
                    and aa.comp_no = bb.comp_no
                    and aa.wkor_id  = bb.wkor_id
                    and aa.comp_no = @pCompNo
                    and bb.wo_status = 'C'
                    and aa.pm_point_id = @pPmPointId
              ) a
    where rn = @pCnt
   ;
   
   if @pType = 'STATUS'
       SET @description =  @v_result_status;
   else if @pType = 'VALUE'
       SET @description =  @v_result_value;
   else
       SET @description =  @v_close_date;
       
   RETURN @description

   
   
END