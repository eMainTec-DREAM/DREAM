package dream.part.rpt.ptusehist.dto;

import common.bean.BaseDTO;

/**
 * 부품사용분석 - 공통 DTO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public class PartRptPtUseHistCommonDTO extends BaseDTO
{
	/** 자재번호 ID */
	private String partId 				= "";
	
	/** 일자(시작) */
    private String filterStartDate    	= "";
    /** 일자(끝) */
    private String filterEndDate    	= "";
	/**Filter 부서 ID*/
	private String filterDeptId 		= "";
	/**Filter 부서 */
	private String filterDeptDesc 		= "";
	/**Filter 공장 ID*/
	private String filterPlantId 		= "";
	/**Filter 공장 */
	private String filterPlantDesc	 	= "";
	/**Filter 자재 ID*/
	private String filterPartId 		= "";
	/**Filter 자재 */
	private String filterPartDesc	 	= "";
	
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
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
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	
}
