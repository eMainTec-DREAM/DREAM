package dream.work.rpt.pmins.ach.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방점검 이행율(담당자) 상세 dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsAchDetailDTO extends BaseDTO
{
    /** 사원 id */
    private String empId    = "";
    /** 사원 desc */
    private String empDesc    = "";
    /** 부서 id */
    private String deptId    = "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    = "";
    
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
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