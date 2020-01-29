package dream.consult.program.btn.dto;

import common.bean.BaseDTO;

/**
 * ¹öÆ° ÆË¾÷ DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovButtonListDTO extends BaseDTO
{
    /** Search No */
    private String buttonNo     = "";
    /** Search Desc */
    private String buttonDesc   = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    
	public String getButtonNo() {
		return buttonNo;
	}
	public void setButtonNo(String buttonNo) {
		this.buttonNo = buttonNo;
	}
	public String getButtonDesc() {
		return buttonDesc;
	}
	public void setButtonDesc(String buttonDesc) {
		this.buttonDesc = buttonDesc;
	}
	public String getExtCode1()
    {
        return extCode1;
    }
    public void setExtCode1(String extCode1)
    {
        this.extCode1 = extCode1;
    }
    public String getExtCode2()
    {
        return extCode2;
    }
    public void setExtCode2(String extCode2)
    {
        this.extCode2 = extCode2;
    }
    public String getCodeType()
    {
        return codeType;
    }
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }
    

}
