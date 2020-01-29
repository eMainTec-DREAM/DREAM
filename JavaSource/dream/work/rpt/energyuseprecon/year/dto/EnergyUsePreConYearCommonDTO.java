package dream.work.rpt.energyuseprecon.year.dto;

import common.bean.BaseDTO;

/**
 * EnergyUsePreConYear Page - ���� DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class EnergyUsePreConYearCommonDTO extends BaseDTO
{
    /**Filter �������� */ 
    private String filterStartDate           = "";
    /**Filter �������� */ 
    private String filterEndDate             = "";
    /**Filter �׸� ID */ 
    private String filterCheckPointType     = "";
    /**Filter �׸� DESC */ 
    private String filterCheckPointDesc   = "";
    /**Filter ���� ID */ 
    private String filterPlantId             = "";
    /**Filter ���� DESC */ 
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
