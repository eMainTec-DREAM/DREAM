package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFDefListDTO;

/**
 * 기능정의 
 * @author  kim21017
 * @version $Id: RcmSysFDefDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysFDefDetailForm"
 */
public class RcmSysFDefDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    /** 기능정의 목록 DTO  */
    private RcmSysFDefListDTO rcmSysFDefListDTO = new RcmSysFDefListDTO();
	/** 기능정의 상세 DTO  */
    private RcmSysFDefDetailDTO rcmSysFDefDetailDTO = new RcmSysFDefDetailDTO();

	public RcmSysFDefDetailDTO getRcmSysFDefDetailDTO() {
		return rcmSysFDefDetailDTO;
	}
	public void setRcmSysFDefDetailDTO(RcmSysFDefDetailDTO rcmSysFDefDetailDTO) {
		this.rcmSysFDefDetailDTO = rcmSysFDefDetailDTO;
	}
	public RcmSysFDefListDTO getRcmSysFDefListDTO() {
		return rcmSysFDefListDTO;
	}
	public void setRcmSysFDefListDTO(RcmSysFDefListDTO rcmSysFDefListDTO) {
		this.rcmSysFDefListDTO = rcmSysFDefListDTO;
	}
	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}
	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}
	
}
