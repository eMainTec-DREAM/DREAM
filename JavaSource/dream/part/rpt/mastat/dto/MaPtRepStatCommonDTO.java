package dream.part.rpt.mastat.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재수리내역 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtRepStatCommonDTO extends BaseDTO
{	
	/** 필터-부서Id */
	private String filterDeptId        = "";
	private String filterDeptDesc      = "";
	/** 필터-입고일자 */
	private String filterStartDate  = "";
	private String filterEndDate    = "";
	
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
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = CommonUtil.convertDate(filterStartDate);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
    }
}
