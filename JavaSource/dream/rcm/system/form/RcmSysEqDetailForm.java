package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;

/**
 * ������ 
 * @author  kim21017
 * @version $Id: RcmSysEqDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysEqDetailForm"
 */
public class RcmSysEqDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    /** ������ ��� DTO  */
    private RcmSysEqListDTO rcmSysEqListDTO = new RcmSysEqListDTO();
	/** ������ �� DTO  */
    private RcmSysEqDetailDTO rcmSysEqDetailDTO = new RcmSysEqDetailDTO();

	public RcmSysEqDetailDTO getRcmSysEqDetailDTO() {
		return rcmSysEqDetailDTO;
	}
	public void setRcmSysEqDetailDTO(RcmSysEqDetailDTO rcmSysEqDetailDTO) {
		this.rcmSysEqDetailDTO = rcmSysEqDetailDTO;
	}
	public RcmSysEqListDTO getRcmSysEqListDTO() {
		return rcmSysEqListDTO;
	}
	public void setRcmSysEqListDTO(RcmSysEqListDTO rcmSysEqListDTO) {
		this.rcmSysEqListDTO = rcmSysEqListDTO;
	}
	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}
	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}
	
}
