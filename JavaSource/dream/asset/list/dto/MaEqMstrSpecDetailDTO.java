package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비제원(스펙) 상세 dto
 * @author  kim21017
 * @version $Id: MaEqMstrSpecDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrSpecDetailDTO extends BaseDTO
{
	/** 설비제원 id */
	private String eqSpecId			= "";
	/** 분류*/
	private String categ			= "";
	/** 항목 */
	private String prompt			= "";
	/** 값 */
	private String response			= "";
	/** 단위 */
	private String uom				= "";
	/** 순서 */
	private String ordNo			= "";
	/** 설비부위ID */
	private String eqAsmbId			= "";
	/** 설비부위DESC */
	private String eqAsmbDesc		= "";
	/** OLD 설비제원 id */
	private String oldEqSpecId		= "";
	/** 설비종류별제원 id */
	private String eqCtgSpecId		= "";
	/** 공통제원여부 */
	private String isEqTypeSpec		= "";
	/** 비고 */
	private String remark			= "";
	
	public String getIsEqTypeSpec()
    {
        return isEqTypeSpec;
    }
    public void setIsEqTypeSpec(String isEqTypeSpec)
    {
        this.isEqTypeSpec = isEqTypeSpec;
    }
    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public String getEqAsmbId() {
		return eqAsmbId;
	}
	public String getOldEqSpecId() {
		return oldEqSpecId;
	}
	public void setOldEqSpecId(String oldEqSpecId) {
		this.oldEqSpecId = oldEqSpecId;
	}
	public String getEqCtgSpecId() {
		return eqCtgSpecId;
	}
	public void setEqCtgSpecId(String eqCtgSpecId) {
		this.eqCtgSpecId = eqCtgSpecId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}
	public String getEqSpecId() {
		return eqSpecId;
	}
	public void setEqSpecId(String eqSpecId) {
		this.eqSpecId = eqSpecId;
		super.setAuditKey(eqSpecId);
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
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
}