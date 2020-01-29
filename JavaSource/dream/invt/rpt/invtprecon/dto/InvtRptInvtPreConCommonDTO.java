package dream.invt.rpt.invtprecon.dto;

import common.bean.BaseDTO;

/**
 * InvtRptInvtPreCon Page - ���� DTO
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class InvtRptInvtPreConCommonDTO extends BaseDTO
{
    /**Key �μ� ID */ 
    private String deptId                 = "";
    /**Key �⵵ */ 
    private String year                        = "";
    
    /**Filter �μ� ID */
    private String filterDeptId                 = "";
    /**Filter �μ� DESC */ 
    private String filterDeptDesc               = "";

    /**Filter ���۳⵵ */ 
    private String filterStartYear              = "";
    /**Filter ����⵵ */ 
    private String filterEndYear                = "";
    
    /** Filter ���ڱ��� */
    private String filterInvtCateg				= "";
    private String filterInvtCategDesc			= "";
    
    /** Filter ���� ID */
    private String filterPlantId				= "";
    /** Filter ���� �� */
    private String filterPlantDesc				= "";
    
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
	public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getYear()
    {
        return year;
    }
    public void setYear(String year)
    {
        this.year = year;
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
    
}
