package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysFieldListDTO;

/**
 * �ý����ڵ� detail - code ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysFieldListForm"
 */
public class MgrCdSysFieldListForm extends BaseForm
{    
    //===============================================================
    /** �ý����ڵ� ���� */
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
    /** �ý����ڵ� detail-code  */
    private MgrCdSysFieldListDTO mgrCdSysFieldListDTO = new MgrCdSysFieldListDTO();


	public MgrCdSysCommonDTO getMgrCdSysCommonDTO() {
		return mgrCdSysCommonDTO;
	}

	public void setMgrCdSysCommonDTO(MgrCdSysCommonDTO mgrCdSysCommonDTO) {
		this.mgrCdSysCommonDTO = mgrCdSysCommonDTO;
	}

	public MgrCdSysFieldListDTO getMgrCdSysFieldListDTO() {
		return mgrCdSysFieldListDTO;
	}

	public void setMgrCdSysFieldListDTO(MgrCdSysFieldListDTO mgrCdSysFieldListDTO) {
		this.mgrCdSysFieldListDTO = mgrCdSysFieldListDTO;
	}
}
