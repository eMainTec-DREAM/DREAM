package dream.work.rpt.basicunitanalysis.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������м� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class BasicUnitAnalysisCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate  = "";
    /** ����(��) */
    private String filterEndDate    = "";
    /**Filter ���� ID */ 
    private String filterPlantId    = "";
    /**Filter ���� DESC */ 
    private String filterPlantDesc  = "";
    
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = CommonUtil.convertDate(filterStartDate);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
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