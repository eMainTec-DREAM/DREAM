package dream.work.let.categ.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 안전작업유형 Detail DTO
 * @author euna0207
 * @version $Id: WorkLetCategDetailDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategDetailDTO extends BaseDTO {
	
	/** 안전작업유형id (key값) */
	private String woLetCtgId =				"";
	
	/** 안전작업 유형번호 */
	private String woLetCtgNo =				"";
	
	/** 안전작업 유형 Id */
	private String woLetCtgTypeId =			"";
	
	/** 안전작업 유형 */
	private String woLetCtgTypeDesc =		"";
	
	/** 제목 */
	private String description =			"";
	
	/** 작성부서 Id */
	private String deptId =					"";
	
	/** 작성부서 */
	private String deptIdDesc =				"";
	
	/** 사용여부 Id */	
	private String isUseId =				"";

	/** 사용여부 */
	private String isUseDesc =				"";

	/** 작성자 Id */
	private String empId =					"";
	
	/** 작성자 */
	private String empIdDesc =				"";

	/** 작성일자 CommonUtil.convert~() */
	private String regDate =				"";
	
	/** 비고 */
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
		/** 날짜나, 금액을 입력하는 경우 (, -) 설정하는 메소드 CommonUtil.conver~() */
		this.regDate = CommonUtil.convertDate(regDate);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
