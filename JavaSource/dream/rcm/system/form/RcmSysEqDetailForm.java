package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;

/**
 * 설비설정 
 * @author  kim21017
 * @version $Id: RcmSysEqDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysEqDetailForm"
 */
public class RcmSysEqDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    /** 설비설정 목록 DTO  */
    private RcmSysEqListDTO rcmSysEqListDTO = new RcmSysEqListDTO();
	/** 설비설정 상세 DTO  */
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
