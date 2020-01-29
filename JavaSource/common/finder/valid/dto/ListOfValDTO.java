package common.finder.valid.dto;

import common.bean.BaseDTO;

/**
 * List Of Val - DTO
 * @author  javaworker
 * @version $Id: ListOfValDTO.java,v 1.1 2013/08/30 09:12:12 javaworker Exp $
 * @since   1.0
 */
public class ListOfValDTO extends BaseDTO
{
    /** Search Code */
    private String code = "";
    /** Search Description */
    private String description = "";
    /** Code Type */
    private String codeType = "";
    /** extra code 1 */
    private String extCode1 = "";
    /** extra code 2 */
    private String extCode2 = "";
    
    private String codeKind = "";
    /** 유저 권한 그룹 */
    private String usrgrpId    = "";
    /** Multy Select Y */
    private String multiSelect    = "";

	
    public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
	public String getUsrgrpId()
    {
        return usrgrpId;
    }
    public void setUsrgrpId(String usrgrpId)
    {
        this.usrgrpId = usrgrpId;
    }
    public String getCodeKind()
    {
        return codeKind;
    }
    public void setCodeKind(String codeKind)
    {
        this.codeKind = codeKind;
    }
    public String getExtCode2()
    {
        return extCode2;
    }
    public void setExtCode2(String extCode2)
    {
        this.extCode2 = extCode2;
    }
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getCodeType()
    {
        return codeType;
    }
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }
    public String getExtCode1()
    {
        return extCode1;
    }
    public void setExtCode1(String extCode1)
    {
        this.extCode1 = extCode1;
    }
}
