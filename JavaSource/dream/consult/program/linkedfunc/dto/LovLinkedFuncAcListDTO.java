package dream.consult.program.linkedfunc.dto;

import common.bean.BaseDTO;

/**
 * Linked Function 팝업 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovLinkedFuncAcListDTO extends BaseDTO
{
    /** linked Function 명 */
    private String linkedFuncDesc     = "";
    /** 사용여부 Id */
    private String isUseId    		  = "";
    /** 사용여부 Desc */
    private String isUseDesc  		  = "";
    /** extCode1 */
    private String extCode1 		  = "";
    /** extCode2 */
    private String extCode2 		  = "";
    /** code type */
    private String codeType 		  = "";
	public String getLinkedFuncDesc() {
		return linkedFuncDesc;
	}
	public void setLinkedFuncDesc(String linkedFuncDesc) {
		this.linkedFuncDesc = linkedFuncDesc;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
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
