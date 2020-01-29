package dream.part.rpt.orgptusehist.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * PartRptOrgPtUseHistList Page - 공통 DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartRptOrgPtUseHistCommonDTO extends BaseDTO
{
    /**Filter 시작일자 */ 
    private String filterStartYyyymm    = "";
    /**Filter 종료일자 */ 
    private String filterEndYyyymm      = "";
    /**Filter 부서 ID */ 
    private String filterDeptId         = "";
    /**Filter 부서 DESC */ 
    private String filterDeptDesc		= "";
    /**Filter 공장 ID */ 
    private String filterPlantId        = "";
    /**Filter 공장 DESC */ 
    private String filterPlantDesc      = "";
    /**Filter 조직구분 ID */ 
    private String filterDeptCategId    = "";
    /**Filter 조직구분 DESC */ 
    private String filterDeptCategDesc	= "";
    
    /** 부서 ID */ 
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
