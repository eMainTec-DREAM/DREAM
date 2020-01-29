package dream.consult.program.menu.dto;

import common.bean.BaseDTO;

/**
 * 컨설트 메뉴 팝업 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovConsultMenuAcListDTO extends BaseDTO
{
    /** menu Desc */
    private String menuDesc     = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
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
