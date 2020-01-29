package dream.org.rpt.craft.work.time.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾��� ���� �۾��ð�
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class OrgRptCraftWorkTimeMonthlyDTO extends BaseDTO
{
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    	= "";
    /** ��� id */
    private String empId		= "";
    /** ����� */
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