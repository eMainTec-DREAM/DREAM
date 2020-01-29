package dream.ass.asset.dto;

import common.bean.BaseDTO;

/**
 * �򰡰������ LOV DTO
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public class AssAssetScoreCopyLovDTO extends BaseDTO
{
	/** ���� ID */
	private String filterPlantId		= "";
	/** ����� */
	private String filterPlantDesc		= "";
	
	/** ���� ID */
	private String filterEquipId		= "";
	/** ����� */
	private String filterEquipDesc		= "";
	/** �����ȣ */
	private String filterEquipNo		= "";
	
	/** ������ */
	private String filterAssStartDate	= "";
	private String filterAssEndDate		= "";
	
	/** �������� ID */
	private String eqasslistId          = "";
	
	/** �򰡱��� ID */
	private String filterAssTypeId		= "";
	
	/** �򰡱��и� */
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
