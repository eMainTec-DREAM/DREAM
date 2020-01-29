package dream.work.pmi.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 점검작업 - 상세 DTO
 * @author  kim21017
 * @version $Id: WorkPmiDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class WorkPmiDetailDTO extends BaseDTO
{
	/** 점검작업Id */
	private String pminslistId 		= "";
	/** 상태Id */
	private String pmschedStatusId 	= "";
	/** 상태명 */
	private String pmschedStatusDesc	= "";
	/** 설비id */
	private String equipId 			= "";
	/** 설비번호 */
	private String itemNo 			= "";
	/** 설비명 */
	private String equipDesc 		= "";
	/** 위치id */
	private String eqLocId          = "";
	/** 위치 */
	private String eqLocDesc 		= "";
	/** 작업명 */
	private String pmiDesc 			= "";
	/** 작업종류Id */
	private String woTypeId 		= "";
	/** 작업종류명 */
	private String woTypeDesc 		= "";
	/** 담당부서Id */
	private String deptId 			= "";
	/** 담당부서명 */
	private String deptDesc 		= "";
	/** 작업형태Id */
	private String pmTypeId 		= "";
	/** 작업형태명 */
	private String pmTypeDesc 		= "";
	/** 담당자Id */
	private String empId 			= "";
	/** 담당자명 */
	private String empDesc 			= "";
	/** 작업일자 시작일 */
	private String startDate 		= "";
	/** 작업시간 시작시간 */
	private String endDate 			= "";
	/** 작업일자 종료일 */
	private String startTime 		= "";
	/** 작업시간 종료시간 */
	private String endTime 			= "";
	/** 작업시간(분) */
	private String workTime 		= "";
	/** 작업일자 */
	private String wkorDate         = "";
	/** 교대조ID */
	private String shiftTypeId      = "";
	/** 교대조ID명 */
	private String shiftTypeDesc    = "";
	/** 작업그룹Id */
	private String wkCtrId			= "";
	/** 작업그룹명 */
	private String wkCtrDesc		= "";
	/** 비고 */
	private String remark			= "";
	
	private String pmInscchedId     = "";
	
	private String pmId     		= "";
	
	private String param     		= "";
	/** 측정시간 */
	private String measureTime      = "";
	/** 수정시간 */
	private String updDate      = "";
	/** 수정자 */
	private String updById      = "";
	private String updBy      = "";
	/** 생성시간 */
	private String creDate      = "";
	/** 생성자 */
	private String creById      = "";
	private String creBy      = "";
	
	
	public String getUpdById() {
		return updById;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public void setUpdById(String updById) {
		this.updById = updById;
	}
	public String getCreById() {
		return creById;
	}
	public void setCreById(String creById) {
		this.creById = creById;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = CommonUtil.convertDateTime(updDate);
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDateTime(creDate);
	}
	public String getCreBy() {
		return creBy;
	}
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}
	public String getMeasureTime()
    {
        return measureTime;
    }
    public void setMeasureTime(String measureTime)
    {
        this.measureTime = measureTime;
    }
    public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmInscchedId() {
		return pmInscchedId;
	}
	public void setPmInscchedId(String pmInscchedId) {
		this.pmInscchedId = pmInscchedId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPminslistId() {
		return pminslistId;
	}
	public void setPminslistId(String pminslistId) {
		this.pminslistId = pminslistId;
		super.setAuditKey(pminslistId);
	}
	public String getPmschedStatusId() {
		return pmschedStatusId;
	}
	public void setPmschedStatusId(String pmschedStatusId) {
		this.pmschedStatusId = pmschedStatusId;
	}
	public String getPmschedStatusDesc() {
		return pmschedStatusDesc;
	}
	public void setPmschedStatusDesc(String pmschedStatusDesc) {
		this.pmschedStatusDesc = pmschedStatusDesc;
	}
	public String getPmiDesc() {
		return pmiDesc;
	}
	public void setPmiDesc(String pmiDesc) {
		this.pmiDesc = pmiDesc;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getWkorDate() {
		return wkorDate;
	}
	public void setWkorDate(String wkorDate) {
		this.wkorDate = CommonUtil.convertDate(wkorDate);
	}
	public String getShiftTypeId() {
		return shiftTypeId;
	}
	public void setShiftTypeId(String shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}
	public String getShiftTypeDesc() {
		return shiftTypeDesc;
	}
	public void setShiftTypeDesc(String shiftTypeDesc) {
		this.shiftTypeDesc = shiftTypeDesc;
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
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getWoTypeId() {
		return woTypeId;
	}
	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
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
	public String getPmTypeId() {
		return pmTypeId;
	}
	public void setPmTypeId(String pmTypeId) {
		this.pmTypeId = pmTypeId;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = CommonUtil.convertTime(startTime);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = CommonUtil.convertTime(endTime);
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = CommonUtil.convertMoney(workTime);
	}
}
