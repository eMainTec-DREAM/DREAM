package dream.consult.comp.list.dto;

import common.bean.BaseDTO;

/**
 * 회사코드 팝업 DTO
 * @author  hyosung
 * @version $Id: ConsultCompListLovDTO.java,v 1.1 2016/01/18 09:12:12 hyosung Exp $
 * @since   1.0
 */
public class ConsultCompListLovDTO extends BaseDTO
{
    /** Search Code */
    private String compNo 		= "";
    /** Search Description */
    private String description 	= "";
    
    private String code	= "";
    
    private String codeType	= "";

    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
