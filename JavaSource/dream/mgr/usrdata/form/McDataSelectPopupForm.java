package dream.mgr.usrdata.form;

import common.struts.BaseForm;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;

/**
 * 메뉴 - 목록 form
 * @author  kim21017
 * @version $Id: McDataSelectListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="mcDataSelectPopupForm"
 */
public class McDataSelectPopupForm extends BaseForm
{    
    //===============================================================
    /** 메뉴 공통 */
    private McDataSelectCommonDTO mcDataSelectCommonDTO = new McDataSelectCommonDTO();

	public McDataSelectCommonDTO getMcDataSelectCommonDTO() {
		return mcDataSelectCommonDTO;
	}

	public void setMcDataSelectCommonDTO(McDataSelectCommonDTO mcDataSelectCommonDTO) {
		this.mcDataSelectCommonDTO = mcDataSelectCommonDTO;
	}

    
}
