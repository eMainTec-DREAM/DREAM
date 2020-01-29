package dream.pers.priv.info.dto;

import common.bean.BaseDTO;

/**
 * 공장권한 팝업 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class LovUsrPlantAuthListDTO extends BaseDTO
{
    /** Search Code */
    private String compNo 		= "";
    /** Search Description */
    private String description 	= "";
    
    private String plant		= "";
    
    private String codeType		= "";

    
    public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
