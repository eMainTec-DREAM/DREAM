package dream.work.pm.check.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 월별단가 - Detail DTO
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceDetailDTO.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkPmCheckMonthlyUnitPriceDetailDTO extends BaseDTO
{ 
    /**Key 표준점검항목 ID */ 
    private String checkPointId         = "";
    
    /** 점검항목 월단가 ID*/
    private String stdChkPointPriceId   = "";    
    /** 월 */
    private String yyyymm               = "";    
    /** 단가 */
    private String unitPrice            = "";    
    /** 비고 */
    private String remark               = "";
    
    public String getCheckPointId()
    {
        return checkPointId;
    }
    public void setCheckPointId(String checkPointId)
    {
        this.checkPointId = checkPointId;
    }
    public String getStdChkPointPriceId()
    {
        return stdChkPointPriceId;
    }
    public void setStdChkPointPriceId(String stdChkPointPriceId)
    {
        this.stdChkPointPriceId = stdChkPointPriceId;
        super.setAuditKey(stdChkPointPriceId);
    }
    public String getYyyymm()
    {
        return yyyymm;
    }
    public void setYyyymm(String yyyymm)
    {
        this.yyyymm = CommonUtil.convertDate(yyyymm);
    }
    public String getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = CommonUtil.convertMoney(unitPrice);
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }    
}
