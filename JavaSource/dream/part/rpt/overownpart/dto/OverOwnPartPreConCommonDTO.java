package dream.part.rpt.overownpart.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * OverOwnPartPreCon Page - 공통 DTO
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class OverOwnPartPreConCommonDTO extends BaseDTO
{
    /**Key 부품 ID */ 
    private String partId                    = "";
    
    /**Filter 부품코드 */
    private String filterPartCode            = "";
    /**Filter 창고 ID */ 
    private String filterWCode               = "";
    /**Filter 창고 DESC */ 
    private String filterWDesc               = "";
    /**Filter 기준일자 */ 
    private String filterStandardDate        = "";
    
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getFilterPartCode()
    {
        return filterPartCode;
    }
    public void setFilterPartCode(String filterPartCode)
    {
        this.filterPartCode = filterPartCode;
    }
    public String getFilterWCode()
    {
        return filterWCode;
    }
    public void setFilterWCode(String filterWCode)
    {
        this.filterWCode = filterWCode;
    }
    public String getFilterWDesc()
    {
        return filterWDesc;
    }
    public void setFilterWDesc(String filterWDesc)
    {
        this.filterWDesc = filterWDesc;
    }
    public String getFilterStandardDate()
    {
        return filterStandardDate;
    }
    public void setFilterStandardDate(String filterStandardDate)
    {
        this.filterStandardDate = CommonUtil.convertDate(filterStandardDate);
    }
}
