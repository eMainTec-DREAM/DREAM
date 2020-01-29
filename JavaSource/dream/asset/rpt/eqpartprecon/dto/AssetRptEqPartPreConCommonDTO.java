package dream.asset.rpt.eqpartprecon.dto;

import common.bean.BaseDTO;

/**
 * AssetRptEqPartPreConList Page - 공통 DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptEqPartPreConCommonDTO extends BaseDTO
{
	/** Key 설비 ID */ 
	private String equipId                   = "";
	/** 설비 DESC */ 
	private String filterEquipDesc           = "";
    /**Filter 공장 ID */ 
    private String filterPlantId             = "";
    /**Filter 공장 DESC */ 
    private String filterPlantDesc           = "";
    /**Filter 위치 ID */ 
    private String filterEqLocId             = "";
    /**Filter 위치 DESC */ 
    private String filterEqLocDesc           = "";
    /**Filter 종류 ID */ 
    private String filterEqCtgId             = "";
    /**Filter 종류 DESC */ 
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
