package dream.doc.ctg.dto;

import common.bean.BaseDTO;

/**
 * 문서분류 팝업 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class DocCtgLovDTO extends BaseDTO
{
	/** 문서분류체계명 */
	private String description = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    private String multiSelect	= "";
    
    private String docctgId		= "";
    
    private String pdocctgId	= "";
    
    private String isUse		= "";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPdocctgId() {
		return pdocctgId;
	}

	public void setPdocctgId(String pdocctgId) {
		this.pdocctgId = pdocctgId;
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

	public String getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getDocctgId() {
		return docctgId;
	}

	public void setDocctgId(String docctgId) {
		this.docctgId = docctgId;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
    
}
