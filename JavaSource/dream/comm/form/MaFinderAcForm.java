package dream.comm.form;

import common.struts.BaseForm;

/**
 * AutoComplete Form
 * @author  jung7126
 * @version $Id: ValidationForm.java,v 1.1 2013/08/30 09:12:46 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="maFinderAcForm"
 */
public class MaFinderAcForm extends BaseForm
{
    /** 검색 CODE */
    private String code = "";
    
    private String keyCode  = "";
    
    private String resultCol = "";
    
    private String extCode1 = "";
    
    /** 검색 Code Type */
    private String codeType = "";
    /** Param JSON */
    private String param = "";
    /** LOV Lable */
    private String label = "";
    
    private String title = "";
    
    private String multiSelect = "";
    /** Child Obj Name */
    private String chName = "";
    
    private String[] filter = null;

    
    public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getExtCode1() {
		return extCode1;
	}

	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}

	public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String[] getFilter()
    {
        return filter;
    }

    public void setFilter(String[] filter)
    {
        this.filter = filter;
    }

    public String getResultCol()
    {
        return resultCol;
    }

    public void setResultCol(String resultCol)
    {
        this.resultCol = resultCol;
    }

    public String getKeyCode()
    {
        return keyCode;
    }

    public void setKeyCode(String keyCode)
    {
        this.keyCode = keyCode;
    }

    public String getCodeType()
    {
        return codeType;
    }

    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getParam()
    {
        return param;
    }

    public void setParam(String param)
    {
        this.param = param;
    }
}
