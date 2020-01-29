package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비부위 팝업 DTO
 * @author  hyosun
 * @version $Id: LovEqAsmbListDTO.java,v 1.1 2016/01/18 09:12:12 hyosun Exp $
 * @since   1.0
 */
public class LovEqAsmbListDTO extends BaseDTO
{
    /** Search Description */
    private String eqAsmbDesc 	= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    private String multiSelect	= "";
    
    private String equipId		= "";
    
    private String eqasmbId     = "";
    
    private String isUse		= "";
    
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
    public String getEqasmbId() {
        return eqasmbId;
    }
    public void setEqasmbId(String eqasmbId) {
        this.eqasmbId = eqasmbId;
    }
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}
	public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	public String getExtCode2() {
		return extCode2;
	}
	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
    
}
