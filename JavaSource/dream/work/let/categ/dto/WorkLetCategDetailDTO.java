package dream.work.let.categ.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾����� Detail DTO
 * @author euna0207
 * @version $Id: WorkLetCategDetailDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategDetailDTO extends BaseDTO {
	
	/** �����۾�����id (key��) */
	private String woLetCtgId =				"";
	
	/** �����۾� ������ȣ */
	private String woLetCtgNo =				"";
	
	/** �����۾� ���� Id */
	private String woLetCtgTypeId =			"";
	
	/** �����۾� ���� */
	private String woLetCtgTypeDesc =		"";
	
	/** ���� */
	private String description =			"";
	
	/** �ۼ��μ� Id */
	private String deptId =					"";
	
	/** �ۼ��μ� */
	private String deptIdDesc =				"";
	
	/** ��뿩�� Id */	
	private String isUseId =				"";

	/** ��뿩�� */
	private String isUseDesc =				"";

	/** �ۼ��� Id */
	private String empId =					"";
	
	/** �ۼ��� */
	private String empIdDesc =				"";

	/** �ۼ����� CommonUtil.convert~() */
	private String regDate =				"";
	
	/** ��� */
	private String remark =					"";
	
	
	public String getWoLetCtgId() {
		return woLetCtgId;
	}

	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
		super.setAuditKey(woLetCtgId);
	}

	public String getWoLetCtgNo() {
		return woLetCtgNo;
	}

	public void setWoLetCtgNo(String woLetCtgNo) {
		this.woLetCtgNo = woLetCtgNo;
	}

	public String getWoLetCtgTypeId() {
		return woLetCtgTypeId;
	}

	public void setWoLetCtgTypeId(String woLetCtgTypeId) {
		this.woLetCtgTypeId = woLetCtgTypeId;
	}

	public String getWoLetCtgTypeDesc() {
		return woLetCtgTypeDesc;
	}

	public void setWoLetCtgTypeDesc(String woLetCtgTypeDesc) {
		this.woLetCtgTypeDesc = woLetCtgTypeDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptIdDesc() {
		return deptIdDesc;
	}

	public void setDeptIdDesc(String deptIdDesc) {
		this.deptIdDesc = deptIdDesc;
	}

	public String getIsUseId() {
		return isUseId;
	}

	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}

	public String getIsUseDesc() {
		return isUseDesc;
	}

	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpIdDesc() {
		return empIdDesc;
	}

	public void setEmpIdDesc(String empIdDesc) {
		this.empIdDesc = empIdDesc;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		/** ��¥��, �ݾ��� �Է��ϴ� ��� (, -) �����ϴ� �޼ҵ� CommonUtil.conver~() */
		this.regDate = CommonUtil.convertDate(regDate);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
