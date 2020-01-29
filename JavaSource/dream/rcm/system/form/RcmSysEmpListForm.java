package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpListDTO;

/**
 * 분석자 
 * @author  kim21017
 * @version $Id: RcmSysEmpListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysEmpListForm"
 */
public class RcmSysEmpListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    
    private RcmSysEmpListDTO rcmSysEmpListDTO = new RcmSysEmpListDTO();

	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}

	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}

	public RcmSysEmpListDTO getRcmSysEmpListDTO() {
		return rcmSysEmpListDTO;
	}

	public void setRcmSysEmpListDTO(RcmSysEmpListDTO rcmSysEmpListDTO) {
		this.rcmSysEmpListDTO = rcmSysEmpListDTO;
	}
	
	
}
