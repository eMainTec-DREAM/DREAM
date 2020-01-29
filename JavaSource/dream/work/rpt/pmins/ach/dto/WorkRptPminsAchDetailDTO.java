package dream.work.rpt.pmins.ach.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������� ������(�����) �� dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsAchDetailDTO extends BaseDTO
{
    /** ��� id */
    private String empId    = "";
    /** ��� desc */
    private String empDesc    = "";
    /** �μ� id */
    private String deptId    = "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
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