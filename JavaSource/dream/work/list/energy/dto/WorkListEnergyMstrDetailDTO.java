package dream.work.list.energy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 에너지관리 - 에너지값 등록상세 DTO
 * @author  sy.yang
 * @version $Id: WorkListEnergyMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 sy.yang Exp $
 * @since   1.0
 *
 */
public class WorkListEnergyMstrDetailDTO extends BaseDTO
{
	/** 점검작업Id */
	private String pminslistId 			= "";
    /** SCHED ID */
    private String pmInsSchedId         = "";
	/** 상태Id */
	private String pmschedStatusId 		= "";
	/** 상태명 */
	private String pmschedStatusDesc	= "";
	/** 설비id */
	private String equipId 				= "";
	/** 설비명 */                      	
	private String equipDesc 			= "";
	/** 작업종류Id */                   	
	private String woTypeId 			= "";
	/** 작업종류명 */                    	
	private String woTypeDesc 			= "";
	/** 작업형태Id */                   	
	private String pmTypeId 			= "";
	/** 작업형태명 */                    	
	private String pmTypeDesc 			= "";
	/** 주기 ID */                       	
	private String pmId     			= "";
	/** 주기# */                       	
	private String pmNo     			= "";
	/** 작업명 */                      	
	private String pmiDesc 				= "";
	/** 작업일자 */                     	
	private String wkorDate         	= "";
	/** 시간대 */                     	
	private String measureTime         	= "";
	/** 담당부서Id */                   	
	private String deptId 				= "";
	/** 담당부서명 */                    	
	private String deptDesc 			= "";
	/** 작업그룹Id */                   	
	private String wkCtrId				= "";
	/** 작업그룹명 */                    	
	private String wkCtrDesc			= "";	
	/** 담당자Id */                    	
	private String empId 				= "";
	/** 담당자명 */                     	
	private String empDesc 				= "";
	/** 비고 */                       	
	private String remark				= "";
	                                	
	private String pmInscchedId     	= "";
	                                	
	                                	
	private String param     			= "";
	
	
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getPmInsSchedId() {
		return pmInsSchedId;
	}
	public void setPmInsSchedId(String pmInsSchedId) {
		this.pmInsSchedId = pmInsSchedId;
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
	public String getWkorDate() {
		return wkorDate;
	}
	public void setWkorDate(String wkorDate) {
		this.wkorDate = CommonUtil.convertDate(wkorDate);
	}
	public String getMeasureTime() {
		return measureTime;
	}
	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
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
}
