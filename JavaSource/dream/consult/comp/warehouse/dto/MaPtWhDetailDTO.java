package dream.consult.comp.warehouse.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtWhDetailDTO extends BaseDTO
{
	/** 비고 */
	private String remark = "";
	/** 사용여부 */
	private String isUse = "";
	/** 창고분류(ERP, MMS) */
	private String whType = "";
	private String whTypeDesc = "";
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
	private String plantDesc = "";
	private String outPlant = "";
	private String outWcode = "";
	private String whCateg = "";
	private String whCategDesc = "";
	private String compDesc = "";
	
	
	
	public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
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
	public String getOutPlant() {
		return outPlant;
	}
	public void setOutPlant(String outPlant) {
		this.outPlant = outPlant;
	}
	public String getOutWcode() {
		return outWcode;
	}
	public void setOutWcode(String outWcode) {
		this.outWcode = outWcode;
	}
	public String getWhTypeDesc() {
		return whTypeDesc;
	}
	public void setWhTypeDesc(String whTypeDesc) {
		this.whTypeDesc = whTypeDesc;
	}
	public String getWhCateg() {
		return whCateg;
	}
	public void setWhCateg(String whCateg) {
		this.whCateg = whCateg;
	}
	public String getWhCategDesc() {
		return whCategDesc;
	}
	public void setWhCategDesc(String whCategDesc) {
		this.whCategDesc = whCategDesc;
	}
	
	
	
}
