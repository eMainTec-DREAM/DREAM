package dream.org.rpt.craft.work.time.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업자 월별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class OrgRptCraftWorkTimeMonthlyDTO extends BaseDTO
{
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    	= "";
    /** 사원 id */
    private String empId		= "";
    /** 사원명 */
    private String empDesc		= "";
    
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
    
}