package dream.part.rpt.mayearptsched.dto;

import common.bean.BaseDTO;

/**
 * ������ǰ������� DTO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPmYearPtSchedCommonDTO extends BaseDTO
{

	/** ����-�μ����̵� */
	private String filterDeptDesc  = "";
	/** ����-�μ��� */
	private String filterDeptId    = "";
	/** ����-���� */
	private String filterYear      = "";
	/** Filter-��������Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-�������� */
	private String filterEqCtgTypeDesc 	   = "";

	/** ���õ� �μ��� */
	private String deptId          = "";
	/** ���õ� ��� */
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
