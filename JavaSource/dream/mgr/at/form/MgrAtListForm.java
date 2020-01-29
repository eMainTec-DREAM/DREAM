package dream.mgr.at.form;

import common.struts.BaseForm;
import dream.mgr.at.dto.MgrAtCommonDTO;

/**
 * Audit Trail
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrAtListForm"
 */
public class MgrAtListForm extends BaseForm
{    
    //===============================================================
	/** Audit Trail Common */
    private MgrAtCommonDTO mgrAtCommonDTO = new MgrAtCommonDTO();

	public MgrAtCommonDTO getMgrAtCommonDTO() {
		return mgrAtCommonDTO;
	}
	public void setMgrAtCommonDTO(MgrAtCommonDTO mgrAtCommonDTO) {
		this.mgrAtCommonDTO = mgrAtCommonDTO;
	}
}
