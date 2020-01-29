package dream.part.iss.emg.list.dto;

import common.bean.BaseDTO;

/**
 * 긴급출고 팝업 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovEmgPartListDTO extends BaseDTO
{
	/** 출고부서 */
    private String issueDept			= "";
    /** 출고부서명 */
    private String issueDeptDesc		= "";
    /** 출고일자 From */
    private String issueDateFrom		= "";
    /** 출고일자 To */
    private String issueDateTo			= "";
    /** 품명/규격 */
    private String partDesc				= "";
    /** 수령자 */
    private String recBy				= "";
    /** 수량자명 */
    private String recByName			= "";
    /** 출고상태 */
    private String ptemgissStatus		= "";
    
    private String ptemgissStatusDesc	= "";
    /** 작업연결상태 */
    private String ptemgTaskStatus		= "";
    
    private String ptemgTaskStatusDesc	= "";
    /** 출고번호 */
    private String ptemgisslistId		= "";
    /** 출고번호 Filter */
    private String filterPtemgisslistId	= "";
    /** 작업 ID */
    private String wkorId				= "";
    
    /** Filter plant ID*/
    private String filterPlantId		= "";
    /** Filter plant DESC*/
    private String filterPlantDesc		= "";
    
    /** Multy Select Y */
    private String multiSelect    		= "";
    
    /** 창고명 ID */
    private String filterWCodeId		= "";
    /** 창고명 DESC */
    private String filterWName			= "";
    

    /** 현재고 Filter */
    private String filterStockQty		= "";
    /** 비고 Filter */
    private String filterRemark		    = "";
    
	public String getFilterRemark()
    {
        return filterRemark;
    }
    public void setFilterRemark(String filterRemark)
    {
        this.filterRemark = filterRemark;
    }
    public String getFilterWCodeId() {
		return filterWCodeId;
	}
	public String getFilterStockQty() {
		return filterStockQty;
	}
	public void setFilterStockQty(String filterStockQty) {
		this.filterStockQty = filterStockQty;
	}
	public void setFilterWCodeId(String filterWCodeId) {
		this.filterWCodeId = filterWCodeId;
	}
	public String getFilterWName() {
		return filterWName;
	}
	public void setFilterWName(String filterWName) {
		this.filterWName = filterWName;
	}
	public String getMultiSelect()
    {
        return multiSelect;
    }
    public void setMultiSelect(String multiSelect)
    {
        this.multiSelect = multiSelect;
    }
    public String getWkorId() {
		return wkorId;
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
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getIssueDept() {
		return issueDept;
	}
	public void setIssueDept(String issueDept) {
		this.issueDept = issueDept;
	}
	public String getIssueDeptDesc() {
		return issueDeptDesc;
	}
	public void setIssueDeptDesc(String issueDeptDesc) {
		this.issueDeptDesc = issueDeptDesc;
	}
	public String getIssueDateFrom() {
		return issueDateFrom;
	}
	public void setIssueDateFrom(String issueDateFrom) {
		this.issueDateFrom = issueDateFrom;
	}
	public String getIssueDateTo() {
		return issueDateTo;
	}
	public void setIssueDateTo(String issueDateTo) {
		this.issueDateTo = issueDateTo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getRecBy() {
		return recBy;
	}
	public void setRecBy(String recBy) {
		this.recBy = recBy;
	}
	public String getRecByName() {
		return recByName;
	}
	public void setRecByName(String recByName) {
		this.recByName = recByName;
	}
	public String getPtemgissStatus() {
		return ptemgissStatus;
	}
	public void setPtemgissStatus(String ptemgissStatus) {
		this.ptemgissStatus = ptemgissStatus;
	}
	public String getPtemgissStatusDesc() {
		return ptemgissStatusDesc;
	}
	public void setPtemgissStatusDesc(String ptemgissStatusDesc) {
		this.ptemgissStatusDesc = ptemgissStatusDesc;
	}
	public String getPtemgTaskStatus() {
		return ptemgTaskStatus;
	}
	public void setPtemgTaskStatus(String ptemgTaskStatus) {
		this.ptemgTaskStatus = ptemgTaskStatus;
	}
	public String getPtemgTaskStatusDesc() {
		return ptemgTaskStatusDesc;
	}
	public void setPtemgTaskStatusDesc(String ptemgTaskStatusDesc) {
		this.ptemgTaskStatusDesc = ptemgTaskStatusDesc;
	}
	public String getPtemgisslistId() {
		return ptemgisslistId;
	}
	public void setPtemgisslistId(String ptemgisslistId) {
		this.ptemgisslistId = ptemgisslistId;
	}
	public String getFilterPtemgisslistId() {
		return filterPtemgisslistId;
	}
	public void setFilterPtemgisslistId(String filterPtemgisslistId) {
		this.filterPtemgisslistId = filterPtemgisslistId;
	}
}
