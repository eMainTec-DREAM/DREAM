package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * 설비종류별 작업부위 상세 dto
 * @author  syyang
 * @version $Id: MaEqCtgAsmbDetailDTO.java,v 1.1 2015/12/04 09:10:45 syyang Exp $
 * @since   1.0
 */
public class MaEqCtgSpecDetailDTO extends BaseDTO
{
	/** 설비종류별 설비표준제원 id */
	private String eqCtgSpecId 	= "";
	/** 분류*/
	private String categ			= "";
	/** 항목 */
	private String prompt			= "";
	/** 단위 */
	private String uom				= "";
	/** 순서 */
	private String ordNo			= "";
	/** 사용여부 */
	private String isUse			= "";

	/** 작업부위id*/
	private String eqCtgAsmbId		= "";
	/** 작업부위명*/
	private String eqCtgAsmbDesc	= "";

	/** (종류별)공통제원여부 */
    private String isEqTypeSpec = "";
    /** 설비종류 id */
    private String eqCtgId          = "";
    
	
	public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getEqCtgSpecId() {
		return eqCtgSpecId;
	}
	public void setEqCtgSpecId(String eqCtgSpecId) {
		this.eqCtgSpecId = eqCtgSpecId;
		super.setAuditKey(eqCtgSpecId);
	}
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
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
	public String getIsEqTypeSpec() {
		return isEqTypeSpec;
	}
	public void setIsEqTypeSpec(String isEqTypeSpec) {
		this.isEqTypeSpec = isEqTypeSpec;
	}
	
	
	
}