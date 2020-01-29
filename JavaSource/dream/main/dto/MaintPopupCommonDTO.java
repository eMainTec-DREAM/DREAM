package dream.main.dto;

import common.bean.BaseDTO;

/**
 * maintenance popup dto
 * @author  freeze
 * @version $Id: MaintPopupCommonDTO.java,v 1.1 2013/08/30 09:08:50 javaworker Exp $
 * @since   1.0
 * 
 */
public class MaintPopupCommonDTO  extends BaseDTO
{
    /** pm No */
    private String pmNo        = "";
    /** pmDesc*/
    private String pmDesc      = "";
    /** w/o No */
    private String woNo        = "";
    /** 점검시작일 */
    private String startDateFrom   = "";
    /** 점검시작일 */
    private String startDateTo   = "";
    
    public String getPmDesc()
    {
        return pmDesc;
    }
    public void setPmDesc(String pmDesc)
    {
        this.pmDesc = pmDesc;
    }
    public String getPmNo()
    {
        return pmNo;
    }
    public void setPmNo(String pmNo)
    {
        this.pmNo = pmNo;
    }
    public String getWoNo()
    {
        return woNo;
    }
    public void setWoNo(String woNo)
    {
        this.woNo = woNo;
    }
    public String getStartDateFrom()
    {
        return startDateFrom;
    }
    public void setStartDateFrom(String startDateFrom)
    {
        this.startDateFrom = startDateFrom;
    }
    public String getStartDateTo()
    {
        return startDateTo;
    }
    public void setStartDateTo(String startDateTo)
    {
        this.startDateTo = startDateTo;
    }
}
