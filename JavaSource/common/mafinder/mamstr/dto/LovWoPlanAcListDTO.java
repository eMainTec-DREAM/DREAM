package common.mafinder.mamstr.dto;

import common.bean.BaseDTO;

/**
 * �۾� �˾� DTO
 * @author  kim21017
 * @version $Id: LovWoPlanAcListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovWoPlanAcListDTO extends BaseDTO
{
    /** �ڵ� */
    private String woNo 		= "";
    /** �� */
    private String woDesc 		= "";
    /** ���� */
    private String woStatus     = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String pmType 	    = "";
    
    private String woType       = "";
    
    /** �۾��� - ���� */
	private String filterWkOrDesc		= "";
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
	/** ����� - ���� */
	private String filterEquipDesc		= "";
	/** �����id - ���� */
	private String filterEmpId			= "";
	/** ����ڸ� - ���� */
	private String filterEmpDesc		= "";
	/** ��ġid - ���� */
	private String filterEqLocId		= "";
	/** ��ġ�� - ���� */
	private String filterEqLocDesc		= "";
	/** ����id - ���� */
	private String filterEqCtgId		= "";
	/** ������ - ���� */
	private String filterEqCtgDesc		= "";
	/** ����-��/���� */
	private String filterPlfTypeId		= "";
	/** ����-��/���� �� */
	private String filterPlfTypeDesc	= "";
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
	/** ���� - �����ڵ� */
	private String filterWoStatus		= "";
	/** ���� - �����ڵ�� */
	private String filterWoStatusDesc	= "";
	
	/** �ڰ�/���ֱ��� �ڵ� */
	private String selfVendorType       = "";
	/** �ڰ�/���ֱ��и� */
	private String selfVendorTypeDesc  	= "";
	/** �ŷ�óID */
	private String vendorId            	= "";
	/** �ŷ�ó */
	private String vendorDesc          	= "";
    /** Multy Select Y */
    private String multiSelect    		= "";
	
    
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
	public String getSelfVendorType() {
		return selfVendorType;
	}
	public void setSelfVendorType(String selfVendorType) {
		this.selfVendorType = selfVendorType;
	}
	public String getSelfVendorTypeDesc() {
		return selfVendorTypeDesc;
	}
	public void setSelfVendorTypeDesc(String selfVendorTypeDesc) {
		this.selfVendorTypeDesc = selfVendorTypeDesc;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	public String getFilterWkOrDesc() {
		return filterWkOrDesc;
	}
	public void setFilterWkOrDesc(String filterWkOrDesc) {
		this.filterWkOrDesc = filterWkOrDesc;
	}
	public String getFilterWoNo() {
		return filterWoNo;
	}
	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
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
	public String getFilterPlfTypeId() {
		return filterPlfTypeId;
	}
	public void setFilterPlfTypeId(String filterPlfTypeId) {
		this.filterPlfTypeId = filterPlfTypeId;
	}
	public String getFilterPlfTypeDesc() {
		return filterPlfTypeDesc;
	}
	public void setFilterPlfTypeDesc(String filterPlfTypeDesc) {
		this.filterPlfTypeDesc = filterPlfTypeDesc;
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
	public String getFilterWoStatus() {
		return filterWoStatus;
	}
	public void setFilterWoStatus(String filterWoStatus) {
		this.filterWoStatus = filterWoStatus;
	}
	public String getFilterWoStatusDesc() {
		return filterWoStatusDesc;
	}
	public void setFilterWoStatusDesc(String filterWoStatusDesc) {
		this.filterWoStatusDesc = filterWoStatusDesc;
	}
	public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
    }
    public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
    }
    public String getWoStatus()
    {
        return woStatus;
    }
    public void setWoStatus(String woStatus)
    {
        this.woStatus = woStatus;
    }
    public String getWoNo()
    {
        return woNo;
    }
    public void setWoNo(String woNo)
    {
        this.woNo = woNo;
    }
    public String getWoDesc()
    {
        return woDesc;
    }
    public void setWoDesc(String woDesc)
    {
        this.woDesc = woDesc;
    }
    public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	public String getExtCode2() {
		return extCode2;
	}
	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}
    
}
