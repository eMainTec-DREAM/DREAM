package dream.work.rpt.energyuseprecon.month.dto;

import common.bean.BaseDTO;

/**
 * EnergyUsePreConMonth Page - detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class EnergyUsePreConMonthDetailDTO extends BaseDTO
{
    /**공장 ID */ 
    private String plantId                    = "";
    /**항목 ID */ 
    private String checkPointType          = "";
    /**공장 DESC */ 
    private String plantDesc                    = "";
    /**항목 DESC */ 
    private String checkPointDesc          = "";
    /**시작일자 */ 
    private String startDate                    = "";
    /**종료일자 */ 
    private String endDate                     = "";
    
    public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getCheckPointType()
    {
        return checkPointType;
    }
    public void setCheckPointType(String checkPointType)
    {
        this.checkPointType = checkPointType;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getCheckPointDesc()
    {
        return checkPointDesc;
    }
    public void setCheckPointDesc(String checkPointDesc)
    {
        this.checkPointDesc = checkPointDesc;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
}
