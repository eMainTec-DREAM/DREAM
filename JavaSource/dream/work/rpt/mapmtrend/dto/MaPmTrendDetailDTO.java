package dream.work.rpt.mapmtrend.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class MaPmTrendDetailDTO extends BaseDTO
{
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate      = "";
    
    /** pmId */
    private String pmId         = "";
    /** pmPointId */
    private String pmPointId    = "";
    /** equipId */
    private String equipId      = "";
    /** pmType */
    private String pmType        = "";
    /** selectedWoType */
    private String selectedWoType        = "";
    /** selectedPmType */
    private String selectedPmType        = "";
    /** 점검부위 */
    private String checkPoint        = "";
    
    
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
    }
    public String getCheckPoint()
    {
        return checkPoint;
    }
    public void setCheckPoint(String checkPoint)
    {
        this.checkPoint = checkPoint;
    }
    public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
    }
    public String getSelectedWoType()
    {
        return selectedWoType;
    }
    public void setSelectedWoType(String selectedWoType)
    {
        this.selectedWoType = selectedWoType;
    }
    public String getSelectedPmType()
    {
        return selectedPmType;
    }
    public void setSelectedPmType(String selectedPmType)
    {
        this.selectedPmType = selectedPmType;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public String getPmPointId()
    {
        return pmPointId;
    }
    public void setPmPointId(String pmPointId)
    {
        this.pmPointId = pmPointId;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = CommonUtil.convertDate(startDate);
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = CommonUtil.convertDate(endDate);
    }
    
}