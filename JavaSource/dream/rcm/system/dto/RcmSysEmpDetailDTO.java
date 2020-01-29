package dream.rcm.system.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 분석자 상세 DTO
 * @author  jung7126
 * @version $Id: RcmSysEmpDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class RcmSysEmpDetailDTO extends BaseDTO
{
	/** RCMLISTID */
	private String rcmListId		= "";
	/** 분석자id*/
	private String rcmEmpId			= "";
	/** 사원ID*/
	private String empId			= "";
	/** 사원No */
	private String empNo			= "";
	/** 사원명 */
	private String empName			= "";
	/** 부서ID */
	private String deptId			= "";
	/** 부서명 */
	private String deptDesc			= "";
	/** 비고 */
	private String remark			= "";
	
	public String getRcmListId() {
		return rcmListId;
	}
	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
	}
	public String getRcmEmpId() {
		return rcmEmpId;
	}
	public void setRcmEmpId(String rcmEmpId) {
		this.rcmEmpId = rcmEmpId;
		super.setAuditKey(rcmEmpId);
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}