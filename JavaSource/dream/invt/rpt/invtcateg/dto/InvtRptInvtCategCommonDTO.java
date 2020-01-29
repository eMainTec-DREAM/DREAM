package dream.invt.rpt.invtcateg.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
import common.util.DateUtil;

/**
 * 투자구분분석 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class InvtRptInvtCategCommonDTO extends BaseDTO
{
    /** 투자완료 일자 FROM */
    private String filterFromDate    		= "";
    /** 투자완료 일자 TO */
    private String filterToDate    			= "";
    
    /** 공장명 ID */
    private String filterPlantId    		= "";
    /** 공장명 DESC */
    private String filterPlantDesc    		= "";
    /** 사용부서 ID */
    private String filterUsageDeptId    	= "";
    /** 사용부서 DESC */
    private String filterUsageDeptDesc    	= "";
    
	public String getFilterFromDate() {
		return filterFromDate;
	}
	public void setFilterFromDate(String filterFromDate) {
		this.filterFromDate = CommonUtil.convertDate(filterFromDate);
	}
	public String getFilterToDate() {
		return filterToDate;
	}
	public void setFilterToDate(String filterToDate) {
		this.filterToDate = CommonUtil.convertDate(filterToDate);
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
	public String getFilterUsageDeptId() {
		return filterUsageDeptId;
	}
	public void setFilterUsageDeptId(String filterUsageDeptId) {
		this.filterUsageDeptId = filterUsageDeptId;
	}
	public String getFilterUsageDeptDesc() {
		return filterUsageDeptDesc;
	}
	public void setFilterUsageDeptDesc(String filterUsageDeptDesc) {
		this.filterUsageDeptDesc = filterUsageDeptDesc;
	}
}