package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysDetailDTO;

/**
 * �ý����ڵ�- �� Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysDetailForm"
 */
public class MgrCdSysDetailForm extends BaseForm
{
    //========================================================================
    /** �ý����ڵ� ���� */ 
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
    //========================================================================
    /** �ý����ڵ� �� */
    private MgrCdSysDetailDTO mgrCdSysDetailDTO = new MgrCdSysDetailDTO();
    
	public MgrCdSysCommonDTO getMgrCdSysCommonDTO() {
		return mgrCdSysCommonDTO;
	}

	public void setMgrCdSysCommonDTO(MgrCdSysCommonDTO mgrCdSysCommonDTO) {
		this.mgrCdSysCommonDTO = mgrCdSysCommonDTO;
	}

	public MgrCdSysDetailDTO getMgrCdSysDetailDTO() {
		return mgrCdSysDetailDTO;
	}

	public void setMgrCdSysDetailDTO(MgrCdSysDetailDTO mgrCdSysDetailDTO) {
		this.mgrCdSysDetailDTO = mgrCdSysDetailDTO;
	}
}