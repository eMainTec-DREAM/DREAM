package dream.work.rpt.pmins.ach.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방점검 이행율(담당자) dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsAchCommonDTO extends BaseDTO
{
    /** 일자(시작) */
    private String filterStartDate    = "";
    /** 일자(끝) */
    private String filterEndDate    = "";
    /** 부서 */
    private String filterDeptId    = "";
    /** 부서 desc */
    private String filterDeptDesc    = "";
    
    /** 담당자id - 필터 */
    private String filterEmpId          = "";
    /** 담당자명 - 필터 */
    private String filterEmpDesc        = "";
    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc          = "";
    
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
    public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpDesc()
    {
        return filterEmpDesc;
    }
    public void setFilterEmpDesc(String filterEmpDesc)
    {
        this.filterEmpDesc = filterEmpDesc;
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = filterStartDate;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
	
}