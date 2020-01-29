package dream.mgr.audtrail.form;

import common.struts.BaseForm;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;

/**
 * Audit Trail
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrAudTrailListForm"
 */
public class MgrAudTrailListForm extends BaseForm
{    
    //===============================================================
	/** Audit Trail Common */
    private MgrAudTrailCommonDTO mgrAudTrailCommonDTO = new MgrAudTrailCommonDTO();

	public MgrAudTrailCommonDTO getMgrAudTrailCommonDTO() {
		return mgrAudTrailCommonDTO;
	}
	public void setMgrAudTrailCommonDTO(MgrAudTrailCommonDTO mgrAudTrailCommonDTO) {
		this.mgrAudTrailCommonDTO = mgrAudTrailCommonDTO;
	}

}
