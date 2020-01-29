package dream.mgr.usage.cal.form;

import common.struts.BaseForm;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDowSetDTO;

/**
 * 요일별 설정 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrUsageCalSetDowSetForm"
 */
public class MgrUsageCalSetDowSetForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MgrUsageCalSetDTO mgrUsageCalSetDTO = new MgrUsageCalSetDTO();
    /** 요일별 설정 목록  */
    private MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = new MgrUsageCalSetDowSetDTO();
	

	public MgrUsageCalSetDTO getMgrUsageCalSetDTO() {
		return mgrUsageCalSetDTO;
	}

	public void setMgrUsageCalSetDTO(MgrUsageCalSetDTO mgrUsageCalSetDTO) {
		this.mgrUsageCalSetDTO = mgrUsageCalSetDTO;
	}

	public MgrUsageCalSetDowSetDTO getMgrUsageCalSetDowSetDTO() {
		return mgrUsageCalSetDowSetDTO;
	}

	public void setMgrUsageCalSetDowSetDTO(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO) {
		this.mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetDTO;
	}
}
