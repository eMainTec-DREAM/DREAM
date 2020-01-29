package dream.work.close.check.dto;

import common.bean.BaseDTO;

/**
 * MgrWorkCloseCheck Page - 공통 DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrWorkCloseCheckCommonDTO extends BaseDTO
{
    /**Key 작업완료 점검항목 설정 ID */ 
    private String stwrkId                	= "";
    
    /**Filter 점검# */
    private String filterStwrkNo           	= "";    
    /**Filter 제목 */
    private String filterStwrkDesc          = "";    
    /**Filter 작업종류ID*/
    private String filterWoTypeId           = "";    
    /**Filter 작업종류명*/
    private String filterWoTypeDesc         = "";    
    /**Filter 공장ID*/
	private String filterPlantId 			= "";
	/**Filter 공장명 */
	private String filterPlantDesc 			= "";
	
	public String getStwrkId() {
		return stwrkId;
	}
	public void setStwrkId(String stwrkId) {
		this.stwrkId = stwrkId;
	}
	public String getFilterStwrkNo() {
		return filterStwrkNo;
	}
	public void setFilterStwrkNo(String filterStwrkNo) {
		this.filterStwrkNo = filterStwrkNo;
	}
	public String getFilterStwrkDesc() {
		return filterStwrkDesc;
	}
	public void setFilterStwrkDesc(String filterStwrkDesc) {
		this.filterStwrkDesc = filterStwrkDesc;
	}
	public String getFilterWoTypeId() {
		return filterWoTypeId;
	}
	public void setFilterWoTypeId(String filterWoTypeId) {
		this.filterWoTypeId = filterWoTypeId;
	}
	public String getFilterWoTypeDesc() {
		return filterWoTypeDesc;
	}
	public void setFilterWoTypeDesc(String filterWoTypeDesc) {
		this.filterWoTypeDesc = filterWoTypeDesc;
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
}
