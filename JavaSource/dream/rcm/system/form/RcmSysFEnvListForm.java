package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 운전환경- 목록
 * @author  kim21017
 * @version $Id: RcmSysFEnvListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysFEnvListForm"
 */
public class RcmSysFEnvListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    /** 답변  */
    private RcmSysFEnvListDTO rcmSysFEnvListDTO = new RcmSysFEnvListDTO();
    
    private RcmSysFDefDetailDTO rcmSysFDefDetailDTO = new RcmSysFDefDetailDTO();
    
	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}

	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}

	public RcmSysFEnvListDTO getRcmSysFEnvListDTO() {
		return rcmSysFEnvListDTO;
	}

	public void setRcmSysFEnvListDTO(RcmSysFEnvListDTO rcmSysFEnvListDTO) {
		this.rcmSysFEnvListDTO = rcmSysFEnvListDTO;
	}

	public RcmSysFDefDetailDTO getRcmSysFDefDetailDTO() {
		return rcmSysFDefDetailDTO;
	}

	public void setRcmSysFDefDetailDTO(RcmSysFDefDetailDTO rcmSysFDefDetailDTO) {
		this.rcmSysFDefDetailDTO = rcmSysFDefDetailDTO;
	}

	
}
