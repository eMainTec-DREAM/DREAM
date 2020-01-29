package dream.consult.consult.menu.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.consult.consult.menu.dto.McMenuCommonDTO;
import dream.consult.consult.menu.dto.McMenuDetailDTO;


/**
 * �޴�- �� Form
 * @author  kim21017
 * @version $Id: McMenuDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="mcMenuDetailForm"
 */
public class McMenuDetailForm extends BaseForm
{
    //========================================================================
    /** �޴� ���� */ 
    private McMenuCommonDTO mcMenuCommonDTO = new McMenuCommonDTO();
    //========================================================================
    /** �޴� �� */
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
