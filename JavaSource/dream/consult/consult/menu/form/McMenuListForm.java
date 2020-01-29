package dream.consult.consult.menu.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.consult.consult.menu.dto.McMenuCommonDTO;

/**
 * 메뉴 - 목록 form
 * @author  kim21017
 * @version $Id: McMenuListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="mcMenuListForm"
 */
public class McMenuListForm extends BaseForm
{    
    //===============================================================
    /** 메뉴 공통 */
    private McMenuCommonDTO mcMenuCommonDTO = new McMenuCommonDTO();
    
    /** 상위메뉴 Options */
    private Collection pMenuOptions = null;

    
	public Collection getpMenuOptions() {
		return pMenuOptions;
	}

	public void setpMenuOptions(Collection pMenuOptions) {
		this.pMenuOptions = pMenuOptions;
	}

	public McMenuCommonDTO getMcMenuCommonDTO() {
		return mcMenuCommonDTO;
	}

	public void setMcMenuCommonDTO(McMenuCommonDTO mcMenuCommonDTO) {
		this.mcMenuCommonDTO = mcMenuCommonDTO;
	}

}
