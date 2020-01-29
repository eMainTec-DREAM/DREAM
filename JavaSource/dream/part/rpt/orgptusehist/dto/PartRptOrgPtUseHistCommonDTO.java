package dream.part.rpt.orgptusehist.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * PartRptOrgPtUseHistList Page - ���� DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartRptOrgPtUseHistCommonDTO extends BaseDTO
{
    /**Filter �������� */ 
    private String filterStartYyyymm    = "";
    /**Filter �������� */ 
    private String filterEndYyyymm      = "";
    /**Filter �μ� ID */ 
    private String filterDeptId         = "";
    /**Filter �μ� DESC */ 
    private String filterDeptDesc		= "";
    /**Filter ���� ID */ 
    private String filterPlantId        = "";
    /**Filter ���� DESC */ 
    private String filterPlantDesc      = "";
    /**Filter �������� ID */ 
    private String filterDeptCategId    = "";
    /**Filter �������� DESC */ 
    private String filterDeptCategDesc	= "";
    
    /** �μ� ID */ 
    private String deptId         		= "";
    
    
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getFilterDeptCategId() {
		return filterDeptCategId;
	}
	public void setFilterDeptCategId(String filterDeptCategId) {
		this.filterDeptCategId = filterDeptCategId;
	}
	public String getFilterDeptCategDesc() {
		return filterDeptCategDesc;
	}
	public void setFilterDeptCategDesc(String filterDeptCategDesc) {
		this.filterDeptCategDesc = filterDeptCategDesc;
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
	public String getFilterStartYyyymm()
    {
        return filterStartYyyymm;
    }
    public void setFilterStartYyyymm(String filterStartYyyymm)
    {
        this.filterStartYyyymm = filterStartYyyymm;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterEndYyyymm()
    {
        return filterEndYyyymm;
    }
    public void setFilterEndYyyymm(String filterEndYyyymm)
    {
        this.filterEndYyyymm = CommonUtil.convertDate(filterEndYyyymm);
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
}
