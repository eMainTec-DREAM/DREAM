package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * �ý����ڵ� - ��� form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysListForm"
 */
public class MgrCdSysListForm extends BaseForm
{    
    //===============================================================
    /** �ý����ڵ� ���� */
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
    
	public MgrCdSysCommonDTO getMgrCdSysCommonDTO() {
		return mgrCdSysCommonDTO;
	}

	public void setMgrCdSysCommonDTO(MgrCdSysCommonDTO mgrCdSysCommonDTO) {
		this.mgrCdSysCommonDTO = mgrCdSysCommonDTO;
	}
}
