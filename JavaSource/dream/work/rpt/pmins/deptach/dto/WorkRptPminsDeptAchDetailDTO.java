package dream.work.rpt.pmins.deptach.dto;

import common.bean.BaseDTO;

/**
 * 예방점검 이행율(부서) 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsDeptAchDetailDTO extends BaseDTO
{
    /** 부서 id */
    private String deptId    	= "";
    /** 부서 desc */
    private String deptDesc    	= "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
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