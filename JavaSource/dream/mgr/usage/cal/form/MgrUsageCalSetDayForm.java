package dream.mgr.usage.cal.form;

import common.struts.BaseForm;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDayDTO;

/**
 * ���޷��Ϻ�Ƚ������ - ��� form
 * @author  youngjoo38
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrUsageCalSetDayForm"
 */
public class MgrUsageCalSetDayForm extends BaseForm
{    
    //===============================================================
    /** ���޷��Ϻ�Ƚ������ ���� */
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
