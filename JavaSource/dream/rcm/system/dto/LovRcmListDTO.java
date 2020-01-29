package dream.rcm.system.dto;

import common.bean.BaseDTO;

/**
 * 자산검색 팝업 DTO
 * @author  kim21017
 * @version $Id: LovRcmListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovRcmListDTO extends BaseDTO
{
    /** Search Code */
    private String certNo 		= "";
    /** Search Description */
    private String description 	= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    /** Search Code */
    private String isUse 		= "";
    
	
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getRcmNo() {
		return certNo;
	}
	public void setRcmNo(String certNo) {
		this.certNo = certNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
