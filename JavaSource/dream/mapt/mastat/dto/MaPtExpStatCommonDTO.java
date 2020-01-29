package dream.mapt.mastat.dto;

import common.bean.BaseDTO;

/**
 * ������м� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtExpStatCommonDTO extends BaseDTO
{	
    
	/** ����-�⵵ */
	private String filterYear          = "";
	/** ����-�μ�Id */
	private String filterDeptId        = "";
	private String filterDeptDesc      = "";
	
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
    public String getFilterYear()
    {
        return filterYear;
    }
    public void setFilterYear(String filterYear)
    {
        this.filterYear = filterYear;
    }
}
