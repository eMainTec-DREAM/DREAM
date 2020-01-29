package dream.part.rpt.mayearptsched.dto;

import common.bean.BaseDTO;

/**
 * 연간부품사용일정 DTO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPmYearPtSchedCommonDTO extends BaseDTO
{

	/** 필터-부서아이디 */
	private String filterDeptDesc  = "";
	/** 필터-부서명 */
	private String filterDeptId    = "";
	/** 필터-일자 */
	private String filterYear      = "";
	/** Filter-설비유형Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-설비유형 */
	private String filterEqCtgTypeDesc 	   = "";

	/** 선택된 부서명 */
	private String deptId          = "";
	/** 선택된 년월 */
	private String yearMon         = "";

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
	public String getYearMon()
    {
        return yearMon;
    }
    public void setYearMon(String yearMon)
    {
        this.yearMon = yearMon;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterYear()
    {
        return filterYear;
    }
    public void setFilterYear(String filterYear)
    {
        this.filterYear = filterYear;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
}
