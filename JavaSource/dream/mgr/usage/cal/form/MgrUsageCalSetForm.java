package dream.mgr.usage.cal.form;

import common.struts.BaseForm;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;

/**
 * 사용달력설정 - 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrUsageCalSetForm"
 */
public class MgrUsageCalSetForm extends BaseForm
{    
    //===============================================================
	/** 가동시간설정 공통 */ 
    private MgrUsageCalSetDTO mgrUsageCalSetDTO = new MgrUsageCalSetDTO();

	public MgrUsageCalSetDTO getMgrUsageCalSetDTO() {
		return mgrUsageCalSetDTO;
	}

	public void setMgrUsageCalSetDTO(MgrUsageCalSetDTO mgrUsageCalSetDTO) {
		this.mgrUsageCalSetDTO = mgrUsageCalSetDTO;
	}

}
