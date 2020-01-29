package dream.org.rpt.craft.work.time.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업자 작업시간 Top
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class OrgRptCraftWorkTimeTopCommonDTO extends BaseDTO
{
    /** 일자(시작) */
    private String filterStartDate    		= "";
    /** 일자(끝) */
    private String filterEndDate    		= "";
    /** 부서 ID */
    private String filterDeptId    			= "";
    /** 부서 desc */
    private String filterDeptDesc    		= "";
    /**	공장 ID */
    private String filterPlantId            = "";
    /** 공장 DESC */
    private String filterPlantDesc          = "";
    /** 작업그룹 */
    private String filterWkCtrId          	= "";
    /** 작업그룹 DESC */
    private String filterWkCtrDesc      	= "";
    
    
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
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
	
}