package dream.invt.rpt.leadtime.dto;

import common.bean.BaseDTO;

/**
 * InvtRptLeadTime Page - ���� DTO
 * @author cjscjs9
 * @version $Id: InvtRptLeadTimeCommonDTO.java,v 1.0 2017/08/22 15:19:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public class InvtRptLeadTimeCommonDTO extends BaseDTO
{

    /**Filter ���μ� ID */
    private String filterDeptId                 = "";
    /**Filter ���μ� DESC */ 
    private String filterDeptDesc               = "";

    /**Filter ���۳⵵ */ 
    private String filterStartYear              = "";
    /**Filter ����⵵ */ 
    private String filterEndYear                = "";
    
    /** Filter ���ڱ��� */
    private String filterInvtCateg				= "";
    private String filterInvtCategDesc			= "";
    
    /** Filter �з� */
    private String filterInvtType				= "";
    private String filterInvtTypeDesc			= "";
    
	/** Filter ���� ID */
    private String filterPlantId				= "";
    /** Filter ���� �� */
    private String filterPlantDesc				= "";
    /** ��з� */
    private String filterLType					= "";
    /** ��з� */
    private String filterLTypeId				= "";
    /** �Һз� */
    private String filterSType					= "";
    /** �Һз� */
    private String filterSTypeId				= "";
    
    
    
	public String getFilterLType() {
		return filterLType;
	}
	public void setFilterLType(String filterLType) {
		this.filterLType = filterLType;
	}
	public String getFilterLTypeId() {
		return filterLTypeId;
	}
	public void setFilterLTypeId(String filterLTypeId) {
		this.filterLTypeId = filterLTypeId;
	}
	public String getFilterSType() {
		return filterSType;
	}
	public void setFilterSType(String filterSType) {
		this.filterSType = filterSType;
	}
	public String getFilterSTypeId() {
		return filterSTypeId;
	}
	public void setFilterSTypeId(String filterSTypeId) {
		this.filterSTypeId = filterSTypeId;
	}
	public String getFilterInvtCateg() {
		return filterInvtCateg;
	}
	public void setFilterInvtCateg(String filterInvtCateg) {
		this.filterInvtCateg = filterInvtCateg;
	}
	public String getFilterInvtCategDesc() {
		return filterInvtCategDesc;
	}
	public void setFilterInvtCategDesc(String filterInvtCategDesc) {
		this.filterInvtCategDesc = filterInvtCategDesc;
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
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterStartYear()
    {
        return filterStartYear;
    }
    public void setFilterStartYear(String filterStartYear)
    {
        this.filterStartYear = filterStartYear;
    }
    public String getFilterEndYear()
    {
        return filterEndYear;
    }
    public void setFilterEndYear(String filterEndYear)
    {
        this.filterEndYear = filterEndYear;
    }
    
    public String getFilterInvtType() {
		return filterInvtType;
	}
	public void setFilterInvtType(String filterInvtType) {
		this.filterInvtType = filterInvtType;
	}
	public String getFilterInvtTypeDesc() {
		return filterInvtTypeDesc;
	}
	public void setFilterInvtTypeDesc(String filterInvtTypeDesc) {
		this.filterInvtTypeDesc = filterInvtTypeDesc;
	}
    
}
