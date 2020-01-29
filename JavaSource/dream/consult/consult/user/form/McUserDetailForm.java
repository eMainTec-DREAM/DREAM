package dream.consult.consult.user.form;

import common.struts.BaseForm;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserDetailDTO;

/**
 * �����- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mcUserDetailForm"
 */
public class McUserDetailForm extends BaseForm
{
    //========================================================================
    /** ����� ���� */ 
    private McUserCommonDTO mcUserCommonDTO = new McUserCommonDTO();
    //========================================================================
    /** ����� �� */
    private McUserDetailDTO mcUserDetailDTO = new McUserDetailDTO();

	public McUserCommonDTO getMcUserCommonDTO() 
	{
		return mcUserCommonDTO;
	}

	public void setMcUserCommonDTO(McUserCommonDTO mcUserCommonDTO) 
	{
		this.mcUserCommonDTO = mcUserCommonDTO;
	}

	public McUserDetailDTO getMcUserDetailDTO() 
	{
		return mcUserDetailDTO;
	}

	public void setMcUserDetailDTO(McUserDetailDTO mcUserDetailDTO) 
	{
		this.mcUserDetailDTO = mcUserDetailDTO;
	}

}
