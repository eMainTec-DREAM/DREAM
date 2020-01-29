package dream.asset.rpt.unit.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� dto
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public class AssetRptEqUnitsDTO extends BaseDTO
{
	/** ���� ID */
	private String equipId 					= "";
	/** LIST */
	/** �����ȣ */
	private String filterItemNo				= "";
	/** ����� */
	private String filterEquipDesc			= "";
	/** ������ġ */
	private String filterEqLocDesc			= "";
	private String filterEqLocId			= "";
	/** ���� */
	private String filterPlantDesc			= "";
	private String filterPlantId			= "";
	/** �������� */
	private String filterEqCtgDesc			= "";
	private String filterEqCtgId			= "";
	/** �����μ� */
	private String filterDeptDesc			= "";
	private String filterDeptId			= "";
	/** ������ */
	private String filterEqCtgAsmbDesc		= "";
	/** �������� */
	private String filterUpDate				= "";
	private String filterEndUpDate			= "";
	
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getFilterItemNo() {
		return filterItemNo;
	}
	public void setFilterItemNo(String filterItemNo) {
		this.filterItemNo = filterItemNo;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterEqCtgAsmbDesc() {
		return filterEqCtgAsmbDesc;
	}
	public void setFilterEqCtgAsmbDesc(String filterEqCtgAsmbDesc) {
		this.filterEqCtgAsmbDesc = filterEqCtgAsmbDesc;
	}
	public String getFilterUpDate() {
		return filterUpDate;
	}
	public void setFilterUpDate(String filterUpDate) {
		this.filterUpDate = CommonUtil.convertDate(filterUpDate);
	}
	public String getFilterEndUpDate() {
		return filterEndUpDate;
	}
	public void setFilterEndUpDate(String filterEndUpDate) {
		this.filterEndUpDate = CommonUtil.convertDate(filterEndUpDate);
	}
}