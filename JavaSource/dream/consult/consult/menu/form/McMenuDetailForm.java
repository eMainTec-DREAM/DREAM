package dream.consult.consult.menu.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.consult.consult.menu.dto.McMenuCommonDTO;
import dream.consult.consult.menu.dto.McMenuDetailDTO;


/**
 * 메뉴- 상세 Form
 * @author  kim21017
 * @version $Id: McMenuDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="mcMenuDetailForm"
 */
public class McMenuDetailForm extends BaseForm
{
    //========================================================================
    /** 메뉴 공통 */ 
    private McMenuCommonDTO mcMenuCommonDTO = new McMenuCommonDTO();
    //========================================================================
    /** 메뉴 상세 */
    private McMenuDetailDTO mcMenuDetailDTO = new McMenuDetailDTO();
    
	public McMenuCommonDTO getMcMenuCommonDTO() {
		return mcMenuCommonDTO;
	}

	public void setMcMenuCommonDTO(McMenuCommonDTO mcMenuCommonDTO) {
		this.mcMenuCommonDTO = mcMenuCommonDTO;
	}

	public McMenuDetailDTO getMcMenuDetailDTO() {
		return mcMenuDetailDTO;
	}

	public void setMcMenuDetailDTO(McMenuDetailDTO mcMenuDetailDTO) {
		this.mcMenuDetailDTO = mcMenuDetailDTO;
	}

}
