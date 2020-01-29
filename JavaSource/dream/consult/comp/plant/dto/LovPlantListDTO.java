package dream.consult.comp.plant.dto;

import common.bean.BaseDTO;

/**
 * ÇÃ·£Æ® ÆË¾÷ DTO
 * @author  kim21017
 * @version $Id: LovPlantListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovPlantListDTO extends BaseDTO
{
    /** Search Code */
    private String compNo 		= "";
    /** Search Description */
    private String description 	= "";
    
    private String plant	= "";
    
    private String codeType	= "";

    
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
