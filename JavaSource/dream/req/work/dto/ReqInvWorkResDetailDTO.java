package dream.req.work.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 처리사항(투자) 상세 DTO
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ReqInvWorkResDetailDTO extends BaseDTO
{
	/** 설비투자 리스트 id */
	private String invtlistId			= "";
	
	/** 투자계획# */
	private String invtlistNo			= "";
	
	/** 상태 */
	private String invtlistStatus		= "";
	private String invtlistStatusDesc	= "";
	
	/** 투자명 */
	private String description			= "";
	
	/** 투자구분 */
	private String invtCateg			= "";
	private String invtCategDesc		= "";
	
	/** 구매절차 */
	private String invtDesc				= "";
	
	/** 설비 */
	private String equipId				= "";
	private String equipDesc			= "";
	
	/** 설비 위치 */
	private String eqLocDesc			= "";
	
	/** 설비 종류 */
	private String eqctgDesc			= "";
	
	/** 투자부서 */
	private String deptId				= "";
	private String deptDesc				= "";
	
	/** 담당자 */
	private String empId				= "";
	private String empDesc				= "";
	
	/** 투자시기 */
	private String planSdate			= "";
	
	/** 투자금액 */
	private String invtAmt				= "";
	
	/** 비고 */
	private String remark				= "";
	
	/** 공장 */
	private String plantId				= "";
	private String plantDesc			= "";
	
	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getPlantDesc() {
		return plantDesc;
	}

	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}

	public String getEqLocDesc() {
		return eqLocDesc;
	}

	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}

	public String getEqctgDesc() {
		return eqctgDesc;
	}

	public void setEqctgDesc(String eqctgDesc) {
		this.eqctgDesc = eqctgDesc;
	}

	public String getInvtAmt() {
		return invtAmt;
	}

	public void setInvtAmt(String invtAmt) {
		this.invtAmt = invtAmt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvtlistId() {
		return invtlistId;
	}

	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
	}

	public String getInvtlistNo() {
		return invtlistNo;
	}

	public void setInvtlistNo(String invtlistNo) {
		this.invtlistNo = invtlistNo;
	}

	public String getInvtlistStatus() {
		return invtlistStatus;
	}

	public void setInvtlistStatus(String invtlistStatus) {
		this.invtlistStatus = invtlistStatus;
	}

	public String getInvtlistStatusDesc() {
		return invtlistStatusDesc;
	}

	public void setInvtlistStatusDesc(String invtlistStatusDesc) {
		this.invtlistStatusDesc = invtlistStatusDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInvtCateg() {
		return invtCateg;
	}

	public void setInvtCateg(String invtCateg) {
		this.invtCateg = invtCateg;
	}

	public String getInvtCategDesc() {
		return invtCategDesc;
	}

	public void setInvtCategDesc(String invtCategDesc) {
		this.invtCategDesc = invtCategDesc;
	}

	public String getInvtDesc() {
		return invtDesc;
	}

	public void setInvtDesc(String invtDesc) {
		this.invtDesc = invtDesc;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getEquipDesc() {
		return equipDesc;
	}

	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpDesc() {
		return empDesc;
	}

	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}

	public String getPlanSdate() {
		return planSdate;
	}

	public void setPlanSdate(String planSdate) {
		this.planSdate = planSdate;
	}
	
	
	
	
}
