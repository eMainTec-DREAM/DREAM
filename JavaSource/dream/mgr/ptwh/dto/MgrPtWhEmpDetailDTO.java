package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * ��ǰâ�� ����� - Detail DTO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrPtWhEmpDetailDTO extends BaseDTO
{
	/** â�� ID */
	private String wcodeId			= "";
    /** â�� ����� ID */
    private String whUserId			= "";
    
    /** ����� ���Id */
    private String empId 			= "";
    /** ����� �����ȣ */
    private String empNo 			= "";
    /** ����ڸ� */
    private String empDesc 			= "";
    /** �μ� */
    private String deptId     		= "";
    /** �μ��� */
    private String deptDesc     	= "";
    /** ��� */
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
