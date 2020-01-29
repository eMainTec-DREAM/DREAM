package dream.mgr.usage.cal.form;

import common.struts.BaseForm;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDayDTO;

/**
 * 사용달력일별횟수설정 - 목록 form
 * @author  youngjoo38
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrUsageCalSetDayForm"
 */
public class MgrUsageCalSetDayForm extends BaseForm
{    
    //===============================================================
    /** 사용달력일별횟수설정 공통 */
    private MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = new MgrUsageCalSetDayDTO();;

	public MgrUsageCalSetDayDTO getMgrUsageCalSetDayDTO() 
	{
		return mgrUsageCalSetDayDTO;
	}

	public void setMgrUsageCalSetDayDTO(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO) 
	{
		this.mgrUsageCalSetDayDTO = mgrUsageCalSetDayDTO;
	}
}
