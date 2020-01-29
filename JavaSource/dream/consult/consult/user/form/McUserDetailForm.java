package dream.consult.consult.user.form;

import common.struts.BaseForm;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserDetailDTO;

/**
 * 사용자- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mcUserDetailForm"
 */
public class McUserDetailForm extends BaseForm
{
    //========================================================================
    /** 사용자 공통 */ 
    private McUserCommonDTO mcUserCommonDTO = new McUserCommonDTO();
    //========================================================================
    /** 사용자 상세 */
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
