package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * �������- ���
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysEqAsmbListForm"
 */
public class RcmSysEqAsmbListForm extends BaseForm
{    
    //===============================================================
    /** ���� ���� */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    /** �亯  */
    private RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO = new RcmSysEqAsmbListDTO();
    
    private RcmSysEqDetailDTO rcmSysEqDetailDTO = new RcmSysEqDetailDTO();
    
	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}

	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}

	public RcmSysEqAsmbListDTO getRcmSysEqAsmbListDTO() {
		return rcmSysEqAsmbListDTO;
	}

	public void setRcmSysEqAsmbListDTO(RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO) {
		this.rcmSysEqAsmbListDTO = rcmSysEqAsmbListDTO;
	}

	public RcmSysEqDetailDTO getRcmSysEqDetailDTO() {
		return rcmSysEqDetailDTO;
	}

	public void setRcmSysEqDetailDTO(RcmSysEqDetailDTO rcmSysEqDetailDTO) {
		this.rcmSysEqDetailDTO = rcmSysEqDetailDTO;
	}
	
}
