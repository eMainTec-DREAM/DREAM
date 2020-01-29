package dream.consult.comp.warehouse.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtWhCommonDTO extends BaseDTO
{
	/** Filter Wcode Id */
	private String filterWcodeId	= "";
	/** Filter Wcode Desc */
	private String filterWname	= "";
	
	/** 비고 */
	private String remark = "";
	/** 사용여부 */
	private String isUse = "";
	/** 창고분류(ERP, MMS) */
	private String whType = "";
	/** 창고명 */
	private String wname = "";
	/** 창고코드 */
	private String wcode = "";
	/** 창고ID */
	private String wcodeId = "";
	/** 회사코드 */
	private String compNo = "";
	/**  */
	private String plant = "";

	private String filterCompNo = "";
	private String filterCompDesc = "";
	
	

	
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
	public String getFilterCompDesc() {
		return filterCompDesc;
	}
	public void setFilterCompDesc(String filterCompDesc) {
		this.filterCompDesc = filterCompDesc;
	}
	public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterWname() {
		return filterWname;
	}
	public void setFilterWname(String filterWname) {
		this.filterWname = filterWname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getWhType() {
		return whType;
	}
	public void setWhType(String whType) {
		this.whType = whType;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWcode() {
		return wcode;
	}
	public void setWcode(String wcode) {
		this.wcode = wcode;
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
}
