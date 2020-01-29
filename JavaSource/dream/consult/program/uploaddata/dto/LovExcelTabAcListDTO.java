package dream.consult.program.uploaddata.dto;

import common.bean.BaseDTO;

/**
 * Excel Tab ÆË¾÷ DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovExcelTabAcListDTO extends BaseDTO
{
    /** Desc */
    private String description     = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
	
	public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
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
