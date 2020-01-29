package dream.invt.rpt.invtprecon.dto;

import common.bean.BaseDTO;

/**
 * InvtRptInvtPreCon Page - 공통 DTO
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConCommonDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class InvtRptInvtPreConCommonDTO extends BaseDTO
{
    /**Key 부서 ID */ 
    private String deptId                 = "";
    /**Key 년도 */ 
    private String year                        = "";
    
    /**Filter 부서 ID */
    private String filterDeptId                 = "";
    /**Filter 부서 DESC */ 
    private String filterDeptDesc               = "";

    /**Filter 시작년도 */ 
    private String filterStartYear              = "";
    /**Filter 종료년도 */ 
    private String filterEndYear                = "";
    
    /** Filter 투자구분 */
    private String filterInvtCateg				= "";
    private String filterInvtCategDesc			= "";
    
    /** Filter 공장 ID */
    private String filterPlantId				= "";
    /** Filter 공장 명 */
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
