package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * �������������� �˾� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovEqCtgSpecAcListDTO extends BaseDTO
{
	/**   �������������� ID */
    private String eqCtgSpecId       = "";
    /**   �з� */
    private String prompt            = "";
    /**   �׸� */
    private String categ   	         = "";
    /**   ���� */
    private String uom      		 = "";
    /**   �������� ID */
    private String eqCtgId           = "";
    /**   ������� ID */
    private String eqCtgAsmbId       = "";
    /**   ��������� */
    private String eqCtgAsmbDesc     = "";
    /**   ��뿩�� */
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
