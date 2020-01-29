package dream.work.close.check.dto;

import common.bean.BaseDTO;

/**
 * MgrWorkCloseCheck Page - ���� DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrWorkCloseCheckCommonDTO extends BaseDTO
{
    /**Key �۾��Ϸ� �����׸� ���� ID */ 
    private String stwrkId                	= "";
    
    /**Filter ����# */
    private String filterStwrkNo           	= "";    
    /**Filter ���� */
    private String filterStwrkDesc          = "";    
    /**Filter �۾�����ID*/
    private String filterWoTypeId           = "";    
    /**Filter �۾�������*/
    private String filterWoTypeDesc         = "";    
    /**Filter ����ID*/
	private String filterPlantId 			= "";
	/**Filter ����� */
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
