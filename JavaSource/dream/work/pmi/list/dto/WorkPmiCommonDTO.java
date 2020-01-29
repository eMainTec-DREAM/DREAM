package dream.work.pmi.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾� ���� DTO
 * @author  kim21017
 * @version $Id: WorkPmiCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class WorkPmiCommonDTO extends BaseDTO
{
	/** �۾����ID */
	private String pminslistId			= "";
	/** ������ID */
	private String pminsschedId			= "";
	/** �۾���û ID */
	private String woReqId				= "";
	/** ����Id */
    private String pmschedStatusId      = "";
	/** �۾���ȣ - ���� */
	private String filterWoNo			= "";
	/** �۾����� ������ - ���� */
	private String filterStartDate		= "";
	/** �۾����� ������ - ���� */
	private String filterEndDate		= "";
	/** �μ�id - ���� */
	private String filterDeptId			= "";
	/** �μ��� - ���� */
	private String filterDeptDesc		= "";
	/** ����id - ���� */
	private String filterEquipId		= "";
	/** ����id - ���� */
	private String filterEquipNo		= "";
	/** ����� - ���� */
	private String filterEquipDesc		= "";
	/** ��ġid - ���� */
	private String filterEqLocId		= "";
	/** ��ġ�� - ���� */
	private String filterEqLocDesc		= "";
	/** ����id - ���� */
	private String filterEqCtgId		= "";
	/** ������ - ���� */
	private String filterEqCtgDesc		= "";
	/** ����-�۾����� */
	private String filterWoTypeId		= "";
	/** ����-�۾����� �� */
	private String filterWoTypeDesc		= "";
	/** ����-�۾����� */
	private String filterPmTypeId		= "";
	/** ����-�۾����� �� */
	private String filterPmTypeDesc		= "";
	/** ����-�������񿩺� */
	private String filterIsLawEq		= "";
	/** ����-������(��)id */
	private String filterMainMngId		= "";
	/** ����-������(��)�� */
	private String filterMainMngName	= "";
	/** ����-������(��)id */
	private String filterSubMngId		= "";
	/** ����-������(��)�� */
	private String filterSubMngName		= "";
	/** ���� - �۾��׷�Id */
	private String filterWkCtrId		= "";
	/** ���� - �۾��׷�� */
	private String filterWkCtrDesc		= "";
	/** ���� - �����۾�# */
	private String filterPmNo			= "";
	/** ���õ� wkorId */
	private String selectedWkorId		= "";
	/** ���õ� �۾����� */
	private String selectedPmType          = "";
	/** ���õ� �۾����� */
	private String selectedWoType          = "";
	/** Filter-��������Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-�������� */
	private String filterEqCtgTypeDesc 	   = "";
	/** Filter-����� Id */
	private String filterManagerId 	   = "";
	/** Filter-����� */
	private String filterManagerDesc 	   = "";
	/** not���� Id */
    private String notPmschedStatusId       = "";

    /** Filter-���� Id */
    private String filterPlantId 	   = "";
    /** Filter-����� */
    private String filterPlantDesc 	   = "";
    
    /** Filter-�����۾����� ID */
    private String filterPmSchedStatusId 	   = "";
    /** Filter-�����۾����� DESC */
    private String filterPmSchedStatusDesc 	   = "";
    
    /** ���� ���μ� */
    private String filterUsageDeptId        = "";
    /** ���� ���μ��� */
    private String filterUsageDeptDesc      = "";  
    
    private String pmId                     = "";
    
    public String getPminsschedId()
    {
        return pminsschedId;
    }
    public void setPminsschedId(String pminsschedId)
    {
        this.pminsschedId = pminsschedId;
    }
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
    }
    public String getFilterUsageDeptId()
    {
        return filterUsageDeptId;
    }
    public void setFilterUsageDeptId(String filterUsageDeptId)
    {
        this.filterUsageDeptId = filterUsageDeptId;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
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
	public String getNotPmschedStatusId()
    {
        return notPmschedStatusId;
    }
    public void setNotPmschedStatusId(String notPmschedStatusId)
    {
        this.notPmschedStatusId = notPmschedStatusId;
    }
    public String getFilterManagerId()
    {
        return filterManagerId;
    }
    public void setFilterManagerId(String filterManagerId)
    {
        this.filterManagerId = filterManagerId;
    }
    public String getFilterManagerDesc()
    {
        return filterManagerDesc;
    }
    public void setFilterManagerDesc(String filterManagerDesc)
    {
        this.filterManagerDesc = filterManagerDesc;
    }
    public String getPminslistId() {
		return pminslistId;
	}
	public void setPminslistId(String pminslistId) {
		this.pminslistId = pminslistId;
		super.setAuditKey(pminslistId);
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterEqCtgTypeId() {
		return filterEqCtgTypeId;
	}
	public void setFilterEqCtgTypeId(String filterEqCtgTypeId) {
		this.filterEqCtgTypeId = filterEqCtgTypeId;
	}
	public String getFilterEqCtgTypeDesc() {
		return filterEqCtgTypeDesc;
	}
	public void setFilterEqCtgTypeDesc(String filterEqCtgTypeDesc) {
		this.filterEqCtgTypeDesc = filterEqCtgTypeDesc;
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
	public String getFilterIsLawEq() {
		return filterIsLawEq;
	}
	public void setFilterIsLawEq(String filterIsLawEq) {
		this.filterIsLawEq = filterIsLawEq;
	}
	public String getFilterMainMngId() {
		return filterMainMngId;
	}
	public void setFilterMainMngId(String filterMainMngId) {
		this.filterMainMngId = filterMainMngId;
	}
	public String getFilterMainMngName() {
		return filterMainMngName;
	}
	public void setFilterMainMngName(String filterMainMngName) {
		this.filterMainMngName = filterMainMngName;
	}
	public String getFilterSubMngId() {
		return filterSubMngId;
	}
	public void setFilterSubMngId(String filterSubMngId) {
		this.filterSubMngId = filterSubMngId;
	}
	public String getFilterSubMngName() {
		return filterSubMngName;
	}
	public void setFilterSubMngName(String filterSubMngName) {
		this.filterSubMngName = filterSubMngName;
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
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	
}
