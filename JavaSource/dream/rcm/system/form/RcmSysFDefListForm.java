package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFDefListDTO;

/**
 * 기능정의 
 * @author  kim21017
 * @version $Id: RcmSysFDefListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysFDefListForm"
 */
public class RcmSysFDefListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    
    private RcmSysFDefListDTO rcmSysFDefListDTO = new RcmSysFDefListDTO();

	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}

	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}

	public RcmSysFDefListDTO getRcmSysFDefListDTO() {
		return rcmSysFDefListDTO;
	}

	public void setRcmSysFDefListDTO(RcmSysFDefListDTO rcmSysFDefListDTO) {
		this.rcmSysFDefListDTO = rcmSysFDefListDTO;
	}
	
	
}
