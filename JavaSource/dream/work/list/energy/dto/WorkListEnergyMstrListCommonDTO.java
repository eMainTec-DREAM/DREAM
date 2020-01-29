package dream.work.list.energy.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���������� - �������� ��� ���� DTO
 * @author  kim21017
 * @version $Id: WorkListEnergyMstrListCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class WorkListEnergyMstrListCommonDTO extends BaseDTO
{
	/** �۾����ID */
	private String pminslistId			= "";
	/** �����ֱ� ID */
	private String pmId					= "";
	/** ����Id */
    private String pmschedStatusId      = "";
    
	/** �۾���ȣ - ���� */
	private String filterWoNo			= "";
	/** �۾����� ������ - ���� */
	private String filterStartDate		= "";
	/** �۾����� ������ - ���� */
	private String filterEndDate		= "";
    /** ���� Id - ���� */
    private String filterPlantId 	   	= "";
    /** ����� - ���� */
    private String filterPlantDesc 	   	= "";
	/** ����id - ���� */
	private String filterEquipId		= "";
	/** ����id - ���� */
	private String filterEquipNo		= "";
	/** ����� - ���� */
	private String filterEquipDesc		= "";
	/** �۾����� - ���� */
	private String filterPmTypeId		= "";
	/** �۾����� �� - ���� */
	private String filterPmTypeDesc		= "";	
	/** �μ�id - ���� */
	private String filterDeptId			= "";
	/** �μ��� - ���� */
	private String filterDeptDesc		= "";
	/** �۾��׷�Id - ���� */
	private String filterWkCtrId		= "";
	/** �۾��׷�� - ���� */
	private String filterWkCtrDesc		= "";
	/** �����id - ���� */
	private String filterEmpId			= "";
	/** ����ڸ� - ���� */
	private String filterEmpDesc		= "";
	/** �۾��� - ���� */
	private String filterPmiDesc		= "";
    /** �����۾����� ID - ���� */
    private String filterPmSchedStatusId	= "";
    /** �����۾����� DESC - ���� */
    private String filterPmSchedStatusDesc	= "";
	/** �����۾�# - ���� */
	private String filterPmNo			= "";
	
	/** �۾����� - ���� */
	private String filterWoTypeId		= "";
	/** �۾����� �� - ���� */
	private String filterWoTypeDesc		= "";
	
	/** ���õ� wkorId */
	private String selectedWkorId		= "";
	/** ���õ� �۾����� */
	private String selectedPmType          = "";
	/** ���õ� �۾����� */
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
