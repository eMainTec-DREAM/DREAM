package dream.mgr.usrdata.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 상세 Form
 * @author  kim21017
 * @version $Id: McDataSelectDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="mcDataSelectDetailForm"
 */
public class McDataSelectDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private McDataSelectCommonDTO mcDataSelectCommonDTO = new McDataSelectCommonDTO();
    //========================================================================
    /** 상세 */
    private McDataSelectDetailDTO mcDataSelectDetailDTO = new McDataSelectDetailDTO();
    
	public McDataSelectCommonDTO getMcDataSelectCommonDTO() {
		return mcDataSelectCommonDTO;
	}

	public void setMcDataSelectCommonDTO(McDataSelectCommonDTO mcDataSelectCommonDTO) {
		this.mcDataSelectCommonDTO = mcDataSelectCommonDTO;
	}

	public McDataSelectDetailDTO getMcDataSelectDetailDTO() {
		return mcDataSelectDetailDTO;
	}

	public void setMcDataSelectDetailDTO(McDataSelectDetailDTO mcDataSelectDetailDTO) {
		this.mcDataSelectDetailDTO = mcDataSelectDetailDTO;
	}

}
