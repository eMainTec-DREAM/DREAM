package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * ���������� �۾����� �� dto
 * @author  syyang
 * @version $Id: MaEqCtgAsmbDetailDTO.java,v 1.1 2015/12/04 09:10:45 syyang Exp $
 * @since   1.0
 */
public class MaEqCtgSpecDetailDTO extends BaseDTO
{
	/** ���������� ����ǥ������ id */
	private String eqCtgSpecId 	= "";
	/** �з�*/
	private String categ			= "";
	/** �׸� */
	private String prompt			= "";
	/** ���� */
	private String uom				= "";
	/** ���� */
	private String ordNo			= "";
	/** ��뿩�� */
	private String isUse			= "";

	/** �۾�����id*/
	private String eqCtgAsmbId		= "";
	/** �۾�������*/
	private String eqCtgAsmbDesc	= "";

	/** (������)������������ */
    private String isEqTypeSpec = "";
    /** �������� id */
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