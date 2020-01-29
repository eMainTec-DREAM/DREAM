package intf.dream.ant.inspection.form;

import common.struts.BaseForm;
import intf.dream.ant.inspection.dto.AntInspectionCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antInspectionListForm"
 */
public class AntInspectionListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntInspectionCommonDTO antInspectionCommonDTO = new AntInspectionCommonDTO();

	public AntInspectionCommonDTO getAntInspectionCommonDTO() {
		return antInspectionCommonDTO;
	}

	public void setAntInspectionCommonDTO(AntInspectionCommonDTO antInspectionCommonDTO) {
		this.antInspectionCommonDTO = antInspectionCommonDTO;
	}

    
}
