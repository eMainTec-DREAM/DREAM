package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * �ý����ڵ� detail - code ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysCodeListForm"
 */
public class MgrCdSysCodeListForm extends BaseForm
{    
    //===============================================================
    /** �ý����ڵ� ���� */
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
    /** �ý����ڵ� detail-code  */
    private MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
    
	public MgrCdSysCommonDTO getMgrCdSysCommonDTO() {
		return mgrCdSysCommonDTO;
	}

	public void setMgrCdSysCommonDTO(MgrCdSysCommonDTO mgrCdSysCommonDTO) {
		this.mgrCdSysCommonDTO = mgrCdSysCommonDTO;
	}

	public MgrCdSysCodeListDTO getMgrCdSysCodeListDTO() {
		return mgrCdSysCodeListDTO;
	}

	public void setMgrCdSysCodeListDTO(MgrCdSysCodeListDTO mgrCdSysCodeListDTO) {
		this.mgrCdSysCodeListDTO = mgrCdSysCodeListDTO;
	}
}
