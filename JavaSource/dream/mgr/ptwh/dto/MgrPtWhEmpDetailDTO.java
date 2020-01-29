package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 담당자 - Detail DTO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrPtWhEmpDetailDTO extends BaseDTO
{
	/** 창고 ID */
	private String wcodeId			= "";
    /** 창고 담당자 ID */
    private String whUserId			= "";
    
    /** 담당자 사원Id */
    private String empId 			= "";
    /** 담당자 사원번호 */
    private String empNo 			= "";
    /** 담당자명 */
    private String empDesc 			= "";
    /** 부서 */
    private String deptId     		= "";
    /** 부서명 */
    private String deptDesc     	= "";
    /** 비고 */
    private String remark 			= "";
    
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}	
	public String getWhUserId() {
		return whUserId;
	}
	public void setWhUserId(String whUserId) {
		this.whUserId = whUserId;
		super.setAuditKey(whUserId);
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
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
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
