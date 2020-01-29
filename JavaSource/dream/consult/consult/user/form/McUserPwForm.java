package dream.consult.consult.user.form;

import common.struts.BaseForm;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserPwDTO;


/**
 * 비밀번호설정
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mcUserPwForm"
 */
public class McUserPwForm extends BaseForm
{    
	//========================================================================
    /** 사용자 공통 */ 
    private McUserCommonDTO mcUserCommonDTO = new McUserCommonDTO();
    //===============================================================
    /** 비밀번호변경 DTO  */
    private McUserPwDTO mcUserPwDTO = new McUserPwDTO();

    
    public McUserCommonDTO getMcUserCommonDTO() 
    {
		return mcUserCommonDTO;
	}
	public void setMcUserCommonDTO(McUserCommonDTO mcUserCommonDTO) 
	{
		this.mcUserCommonDTO = mcUserCommonDTO;
	}
	public McUserPwDTO getMcUserPwDTO() 
    {
		return mcUserPwDTO;
	}
	public void setMcUserPwDTO(McUserPwDTO mcUserPwDTO) 
	{
		this.mcUserPwDTO = mcUserPwDTO;
	}
}
