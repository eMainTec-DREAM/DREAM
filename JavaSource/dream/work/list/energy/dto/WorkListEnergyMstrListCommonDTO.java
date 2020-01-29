package dream.work.list.energy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 에너지관리 - 에너지값 등록 공통 DTO
 * @author  kim21017
 * @version $Id: WorkListEnergyMstrListCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class WorkListEnergyMstrListCommonDTO extends BaseDTO
{
	/** 작업결과ID */
	private String pminslistId			= "";
	/** 기준주기 ID */
	private String pmId					= "";
	/** 상태Id */
    private String pmschedStatusId      = "";
    
	/** 작업번호 - 필터 */
	private String filterWoNo			= "";
	/** 작업일자 시작일 - 필터 */
	private String filterStartDate		= "";
	/** 작업일자 종료일 - 필터 */
	private String filterEndDate		= "";
    /** 공장 Id - 필터 */
    private String filterPlantId 	   	= "";
    /** 공장명 - 필터 */
    private String filterPlantDesc 	   	= "";
	/** 설비id - 필터 */
	private String filterEquipId		= "";
	/** 설비id - 필터 */
	private String filterEquipNo		= "";
	/** 설비명 - 필터 */
	private String filterEquipDesc		= "";
	/** 작업형태 - 필터 */
	private String filterPmTypeId		= "";
	/** 작업형태 명 - 필터 */
	private String filterPmTypeDesc		= "";	
	/** 부서id - 필터 */
	private String filterDeptId			= "";
	/** 부서명 - 필터 */
	private String filterDeptDesc		= "";
	/** 작업그룹Id - 필터 */
	private String filterWkCtrId		= "";
	/** 작업그룹명 - 필터 */
	private String filterWkCtrDesc		= "";
	/** 담당자id - 필터 */
	private String filterEmpId			= "";
	/** 담당자명 - 필터 */
	private String filterEmpDesc		= "";
	/** 작업명 - 필터 */
	private String filterPmiDesc		= "";
    /** 점검작업상태 ID - 필터 */
    private String filterPmSchedStatusId	= "";
    /** 점검작업상태 DESC - 필터 */
    private String filterPmSchedStatusDesc	= "";
	/** 예방작업# - 필터 */
	private String filterPmNo			= "";
	
	/** 작업종류 - 필터 */
	private String filterWoTypeId		= "";
	/** 작업종류 명 - 필터 */
	private String filterWoTypeDesc		= "";
	
	/** 선택된 wkorId */
	private String selectedWkorId		= "";
	/** 선택된 작업형태 */
	private String selectedPmType          = "";
	/** 선택된 작업종류 */
	private String selectedWoType          = "";
	
    
    public String getFilterPmSchedStatusId() {
		return filterPmSchedStatusId;
	}
	public void setFilterPmSchedStatusId(String filterPmSchedStatusId) {
		this.filterPmSchedStatusId = filterPmSchedStatusId;
	}
	public String getFilterPmSchedStatusDesc() {
		return filterPmSchedStatusDesc;
	}
	public void setFilterPmSchedStatusDesc(String filterPmSchedStatusDesc) {
		this.filterPmSchedStatusDesc = filterPmSchedStatusDesc;
	}
	public String getPmschedStatusId()
    {
        return pmschedStatusId;
    }
    public void setPmschedStatusId(String pmschedStatusId)
    {
        this.pmschedStatusId = pmschedStatusId;
    }
    public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
    public String getPminslistId() {
		return pminslistId;
	}
	public void setPminslistId(String pminslistId) {
		this.pminslistId = pminslistId;
		super.setAuditKey(pminslistId);
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
    public String getSelectedPmType() {
		return selectedPmType;
	}
	public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}
	public String getSelectedWoType() {
		return selectedWoType;
	}
	public void setSelectedWoType(String selectedWoType) {
		this.selectedWoType = selectedWoType;
	}
	public String getFilterPmNo() {
		return filterPmNo;
	}
	public void setFilterPmNo(String filterPmNo) {
		this.filterPmNo = filterPmNo;
	}
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
	public String getFilterWoNo() {
		return filterWoNo;
	}
	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
	}
	public String getSelectedWkorId() {
		return selectedWkorId;
	}
	public void setSelectedWkorId(String selectedWkorId) {
		this.selectedWkorId = selectedWkorId;
	}
	public String getFilterPmTypeId() {
		return filterPmTypeId;
	}
	public void setFilterPmTypeId(String filterPmTypeId) {
		this.filterPmTypeId = filterPmTypeId;
	}
	public String getFilterPmTypeDesc() {
		return filterPmTypeDesc;
	}
	public void setFilterPmTypeDesc(String filterPmTypeDesc) {
		this.filterPmTypeDesc = filterPmTypeDesc;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}
	public String getFilterPmiDesc() {
		return filterPmiDesc;
	}
	public void setFilterPmiDesc(String filterPmiDesc) {
		this.filterPmiDesc = filterPmiDesc;
	}
	public String getFilterWoTypeId() {
		return filterWoTypeId;
	}
	public void setFilterWoTypeId(String filterWoTypeId) {
		this.filterWoTypeId = filterWoTypeId;
	}
	public String getFilterWoTypeDesc() {
		return filterWoTypeDesc;
	}
	public void setFilterWoTypeDesc(String filterWoTypeDesc) {
		this.filterWoTypeDesc = filterWoTypeDesc;
	}
	
}
