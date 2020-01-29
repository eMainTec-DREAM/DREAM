package dream.ass.asset.dto;

import common.bean.BaseDTO;

/**
 * 평가결과복사 LOV DTO
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public class AssAssetScoreCopyLovDTO extends BaseDTO
{
	/** 공장 ID */
	private String filterPlantId		= "";
	/** 공장명 */
	private String filterPlantDesc		= "";
	
	/** 설비 ID */
	private String filterEquipId		= "";
	/** 설비명 */
	private String filterEquipDesc		= "";
	/** 설비번호 */
	private String filterEquipNo		= "";
	
	/** 평가일자 */
	private String filterAssStartDate	= "";
	private String filterAssEndDate		= "";
	
	/** 설비등급평가 ID */
	private String eqasslistId          = "";
	
	/** 평가구분 ID */
	private String filterAssTypeId		= "";
	
	/** 평가구분명 */
	private String filterAssTypeDesc	= "";
	
	public String getFilterAssTypeId() {
		return filterAssTypeId;
	}
	public void setFilterAssTypeId(String filterAssTypeId) {
		this.filterAssTypeId = filterAssTypeId;
	}
	public String getFilterAssTypeDesc() {
		return filterAssTypeDesc;
	}
	public void setFilterAssTypeDesc(String filterAssTypeDesc) {
		this.filterAssTypeDesc = filterAssTypeDesc;
	}
	public String getEqasslistId() {
		return eqasslistId;
	}
	public void setEqasslistId(String eqasslistId) {
		this.eqasslistId = eqasslistId;
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
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterAssStartDate() {
		return filterAssStartDate;
	}
	public void setFilterAssStartDate(String filterAssStartDate) {
		this.filterAssStartDate = filterAssStartDate;
	}
	public String getFilterAssEndDate() {
		return filterAssEndDate;
	}
	public void setFilterAssEndDate(String filterAssEndDate) {
		this.filterAssEndDate = filterAssEndDate;
	}
	
	
	
}
