package dream.part.iss.emg.list.dto;

import common.bean.BaseDTO;

/**
 * ������ �˾� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovEmgPartListDTO extends BaseDTO
{
	/** ���μ� */
    private String issueDept			= "";
    /** ���μ��� */
    private String issueDeptDesc		= "";
    /** ������� From */
    private String issueDateFrom		= "";
    /** ������� To */
    private String issueDateTo			= "";
    /** ǰ��/�԰� */
    private String partDesc				= "";
    /** ������ */
    private String recBy				= "";
    /** �����ڸ� */
    private String recByName			= "";
    /** ������ */
    private String ptemgissStatus		= "";
    
    private String ptemgissStatusDesc	= "";
    /** �۾�������� */
    private String ptemgTaskStatus		= "";
    
    private String ptemgTaskStatusDesc	= "";
    /** ����ȣ */
    private String ptemgisslistId		= "";
    /** ����ȣ Filter */
    private String filterPtemgisslistId	= "";
    /** �۾� ID */
    private String wkorId				= "";
    
    /** Filter plant ID*/
    private String filterPlantId		= "";
    /** Filter plant DESC*/
    private String filterPlantDesc		= "";
    
    /** Multy Select Y */
    private String multiSelect    		= "";
    
    /** â��� ID */
    private String filterWCodeId		= "";
    /** â��� DESC */
    private String filterWName			= "";
    

    /** ����� Filter */
    private String filterStockQty		= "";
    /** ��� Filter */
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
