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
    /**공장 ID */ 
    private String plantId                    = "";
    /**공장 DESC */ 
    private String plantDesc                    = "";
    /**부서 ID */ 
    private String deptId                    = "";
    /**부서 DESC */ 
    private String deptDesc                    = "";
    /**시작일자 */ 
    private String startDate                    = "";
    /**종료일자 */ 
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
