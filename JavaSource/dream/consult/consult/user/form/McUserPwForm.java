package dream.consult.consult.user.form;

import common.struts.BaseForm;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserPwDTO;


/**
 * ��й�ȣ����
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mcUserPwForm"
 */
public class McUserPwForm extends BaseForm
{    
	//========================================================================
    /** ����� ���� */ 
    private McUserCommonDTO mcUserCommonDTO = new McUserCommonDTO();
    //===============================================================
    /** ��й�ȣ���� DTO  */
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
