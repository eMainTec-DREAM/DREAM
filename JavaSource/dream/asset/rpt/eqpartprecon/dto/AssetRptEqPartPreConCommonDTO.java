package dream.asset.rpt.eqpartprecon.dto;

import common.bean.BaseDTO;

/**
 * AssetRptEqPartPreConList Page - ���� DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptEqPartPreConCommonDTO extends BaseDTO
{
	/** Key ���� ID */ 
	private String equipId                   = "";
	/** ���� DESC */ 
	private String filterEquipDesc           = "";
    /**Filter ���� ID */ 
    private String filterPlantId             = "";
    /**Filter ���� DESC */ 
    private String filterPlantDesc           = "";
    /**Filter ��ġ ID */ 
    private String filterEqLocId             = "";
    /**Filter ��ġ DESC */ 
    private String filterEqLocDesc           = "";
    /**Filter ���� ID */ 
    private String filterEqCtgId             = "";
    /**Filter ���� DESC */ 
    private String filterEqCtgDesc           = "";
    
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
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
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
}
