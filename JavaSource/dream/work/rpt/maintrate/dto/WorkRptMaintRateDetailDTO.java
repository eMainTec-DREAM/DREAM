package dream.work.rpt.maintrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * WorkRptMaintRate Page - detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptMaintRateDetailDTO extends BaseDTO
{
    /**���� ID */ 
    private String plantId                    = "";
    /**���� DESC */ 
    private String plantDesc                    = "";
    /**�μ� ID */ 
    private String deptId                    = "";
    /**�μ� DESC */ 
    private String deptDesc                    = "";
    /**�������� */ 
    private String startDate                    = "";
    /**�������� */ 
    private String endDate                     = "";
    
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getStartDate()
    {
        return startDate;
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
