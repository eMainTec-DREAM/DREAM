package dream.consult.comp.wrkcal.dto;

import common.bean.BaseDTO;

/**
 * 근무달력 팝업 DTO
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class ConsultCompWrkcalLovDTO extends BaseDTO
{
    /** Search Code */
    private String compNo 			= "";
    /** Search Description */
    private String wrkcalListDesc	= "";
    
    private String wrkcalListNo	= "";
    
    private String codeType		= "";
    
    private String plantNo			= "";
    
    private String plantDesc		= "";

    
    public String getPlantNo() {
		return plantNo;
	}
	public void setPlantNo(String plantNo) {
		this.plantNo = plantNo;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getWrkcalListDesc() {
		return wrkcalListDesc;
	}
	public void setWrkcalListDesc(String wrkcalListDesc) {
		this.wrkcalListDesc = wrkcalListDesc;
	}
	public String getWrkcalListNo() {
		return wrkcalListNo;
	}
	public void setWrkcalListNo(String wrkcalListNo) {
		this.wrkcalListNo = wrkcalListNo;
	}
	
}
