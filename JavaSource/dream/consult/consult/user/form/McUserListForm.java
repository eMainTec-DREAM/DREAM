package dream.consult.consult.user.form;

import common.struts.BaseForm;
import dream.consult.consult.user.dto.McUserCommonDTO;

/**
 * ����� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mcUserListForm"
 */
public class McUserListForm extends BaseForm
{    
    //===============================================================
    /** ����� ���� */
    private McUserCommonDTO mcUserCommonDTO = new McUserCommonDTO();
    
    public McUserCommonDTO getMcUserCommonDTO() 
	{
		return mcUserCommonDTO;
	}

	public void setMcUserCommonDTO(McUserCommonDTO mcUserCommonDTO) 
	{
		this.mcUserCommonDTO = mcUserCommonDTO;
	}
}
