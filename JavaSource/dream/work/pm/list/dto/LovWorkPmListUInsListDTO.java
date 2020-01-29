package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 에너지 측정기준주기 Lov DTO
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public class LovWorkPmListUInsListDTO extends BaseDTO
{
	/** PM ID */
	private String pmId			= "";
	/** PM 번호 */
	private String pmNo			= "";

	/** 작업명 */
	private String pmiDesc		= "";
	/** 작업형태 */
	private String pmTypeId		= "";
	/** 작업형태 명 */
	private String pmTypeDesc	= "";
	/** 설비id */
	private String equipId		= "";
	/** 설비명 */
	private String equipDesc	= "";		
	/** 부서id  */
	private String deptId		= "";
	/** 부서명  */
	private String deptDesc		= "";
	/** 작업그룹Id  */
	private String wkCtrId		= "";
	/** 작업그룹명  */
	private String wkCtrDesc	= "";
	/** 담당자id  */
	private String empId		= "";
	/** 담당자명  */
	private String empDesc		= "";
    /** 공장 Id  */
    private String plantId 	   	= "";
    /** 공장명  */
    private String plantDesc   	= "";
    /** 시행여부  */
    private String isActive   	= "";
    
    /** Multy Select Y */
    private String multiSelect	= "";
    
    private String extCode1 	= "";
	
	
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getPmiDesc() {
		return pmiDesc;
	}
	public void setPmiDesc(String pmiDesc) {
		this.pmiDesc = pmiDesc;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
	public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	
}
