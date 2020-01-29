package dream.work.rpt.energyuseprecon.year.dto;

import common.bean.BaseDTO;

/**
 * EnergyUsePreConYear Page - 공통 DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class EnergyUsePreConYearCommonDTO extends BaseDTO
{
    /**Filter 시작일자 */ 
    private String filterStartDate           = "";
    /**Filter 종료일자 */ 
    private String filterEndDate             = "";
    /**Filter 항목 ID */ 
    private String filterCheckPointType     = "";
    /**Filter 항목 DESC */ 
    private String filterCheckPointDesc   = "";
    /**Filter 공장 ID */ 
    private String filterPlantId             = "";
    /**Filter 공장 DESC */ 
    private String filterPlantDesc           = "";
    
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = filterStartDate;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public String getFilterCheckPointType()
    {
        return filterCheckPointType;
    }
    public void setFilterCheckPointType(String filterCheckPointType)
    {
        this.filterCheckPointType = filterCheckPointType;
    }
    public String getFilterCheckPointDesc()
    {
        return filterCheckPointDesc;
    }
    public void setFilterCheckPointDesc(String filterCheckPointDesc)
    {
        this.filterCheckPointDesc = filterCheckPointDesc;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
}
