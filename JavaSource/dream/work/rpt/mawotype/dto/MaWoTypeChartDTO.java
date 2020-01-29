package dream.work.rpt.mawotype.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업유형별현황 DTO
 * @author  kim21017
 * @version $Id: MaWoTypeChartDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaWoTypeChartDTO extends BaseDTO
{
	/** 필터-공장아이디 */
	private String filterPlantDesc 		= "";
	/** 필터-공장명 */
	private String filterPlantId 		= "";
	/** 필터-위치아이디 */
	private String filterEqLocDesc 		= "";
	/** 필터-위치명 */
	private String filterEqLocId 		= "";
	/** 필터-종류아이디 */
	private String filterEqCtgDesc 		= "";
	/** 필터-종류명 */
	private String filterEqCtgId 		= "";
	/** 필터-일자 */
	private String filterStartDate 		= "";
	/** 필터-일자 */
	private String filterEndDate 		= "";
	
	
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
	}
}
