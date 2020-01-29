package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * 설비종류별제원 팝업 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovEqCtgSpecAcListDTO extends BaseDTO
{
	/**   설비종류별제원 ID */
    private String eqCtgSpecId       = "";
    /**   분류 */
    private String prompt            = "";
    /**   항목 */
    private String categ   	         = "";
    /**   단위 */
    private String uom      		 = "";
    /**   설비종류 ID */
    private String eqCtgId           = "";
    /**   설비부위 ID */
    private String eqCtgAsmbId       = "";
    /**   설비부위명 */
    private String eqCtgAsmbDesc     = "";
    /**   사용여부 */
    private String isUse             = "";
	public String getEqCtgSpecId() {
		return eqCtgSpecId;
	}
	public void setEqCtgSpecId(String eqCtgSpecId) {
		this.eqCtgSpecId = eqCtgSpecId;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgAsmbId() {
		return eqCtgAsmbId;
	}
	public void setEqCtgAsmbId(String eqCtgAsmbId) {
		this.eqCtgAsmbId = eqCtgAsmbId;
	}
	public String getEqCtgAsmbDesc() {
		return eqCtgAsmbDesc;
	}
	public void setEqCtgAsmbDesc(String eqCtgAsmbDesc) {
		this.eqCtgAsmbDesc = eqCtgAsmbDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
}
