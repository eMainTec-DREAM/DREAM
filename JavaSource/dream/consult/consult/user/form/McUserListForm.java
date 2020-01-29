package dream.consult.consult.user.form;

import common.struts.BaseForm;
import dream.consult.consult.user.dto.McUserCommonDTO;

/**
 * 사용자 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mcUserListForm"
 */
public class McUserListForm extends BaseForm
{    
    //===============================================================
    /** 사용자 공통 */
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
