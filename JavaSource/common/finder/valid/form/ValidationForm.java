package common.finder.valid.form;

import common.struts.BaseForm;

/**
 * Validation Form
 * @author  javaworker
 * @version $Id: ValidationForm.java,v 1.1 2013/08/30 09:12:46 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="validationForm"
 */
public class ValidationForm extends BaseForm
{
    /** 검색 CODE */
    private String code = "";
    /** Code Type */
    private String codeType = "";
    /** Code Kind */
    private String codeKind = "";
    /** 확장 검색 CODE */
    private String expCode = "";
    /** 확장 검색 CODE 2 */
    private String expCode2 = "";
    /** 확장 검색 CODE 3 */
    private String expCode3 = "";
    /** 확장검색 Code Array */
    private String[] expCodeArr = null;
 
    public String[] getExpCodeArr()
    {
        return expCodeArr;
    }

    public void setExpCodeArr(String[] expCodeArr)
    {
        this.expCodeArr = expCodeArr;
    }

    public String getCodeKind() {
		return codeKind;
	}

	public void setCodeKind(String codeKind) {
		this.codeKind = codeKind;
	}

	public String getExpCode3()
    {
        return expCode3;
    }

    public void setExpCode3(String expCode3)
    {
        this.expCode3 = expCode3;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCodeType()
    {
        return codeType;
    }

    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }

    public String getExpCode()
    {
        return expCode;
    }

    public void setExpCode(String expCode)
    {
        this.expCode = expCode;
    }

    public String getExpCode2()
    {
        return expCode2;
    }

    public void setExpCode2(String expCode2)
    {
        this.expCode2 = expCode2;
    }
}
