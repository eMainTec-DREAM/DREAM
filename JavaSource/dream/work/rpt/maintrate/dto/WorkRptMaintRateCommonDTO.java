package dream.work.rpt.maintrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * WorkRptMaintRateList Page - 공통 DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptMaintRateCommonDTO extends BaseDTO
{
    /**Filter 시작일자 */ 
    private String filterStartDate           = "";
    /**Filter 종료일자 */ 
    private String filterEndDate             = "";
    /**Filter 공장 ID */ 
    private String filterPlantId             = "";
    /**Filter 공장 DESC */ 
    private String filterPlantDesc           = "";
    /** 부서 ID */ 
    private String deptId                    = "";
    
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
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
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = CommonUtil.convertDate(filterPlantId);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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
