package dream.work.rpt.pmins.deptach.dto;

import common.bean.BaseDTO;

/**
 * �������� ������(�μ�) �� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsDeptAchDetailDTO extends BaseDTO
{
    /** �μ� id */
    private String deptId    	= "";
    /** �μ� desc */
    private String deptDesc    	= "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    	= "";
    
    public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
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