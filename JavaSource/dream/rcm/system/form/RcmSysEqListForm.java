package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;

/**
 * 설비설정 
 * @author  kim21017
 * @version $Id: RcmSysEqListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysEqListForm"
 */
public class RcmSysEqListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    
    private RcmSysEqListDTO rcmSysEqListDTO = new RcmSysEqListDTO();

	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}

	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}

	public RcmSysEqListDTO getRcmSysEqListDTO() {
		return rcmSysEqListDTO;
	}

	public void setRcmSysEqListDTO(RcmSysEqListDTO rcmSysEqListDTO) {
		this.rcmSysEqListDTO = rcmSysEqListDTO;
	}
	
	
}
