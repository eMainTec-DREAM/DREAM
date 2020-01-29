package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.RcmSysEmpDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpListDTO;

/**
 * 분석자 
 * @author  kim21017
 * @version $Id: RcmSysEmpDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysEmpDetailForm"
 */
public class RcmSysEmpDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    /** 분석자 목록 DTO  */
    private RcmSysEmpListDTO rcmSysEmpListDTO = new RcmSysEmpListDTO();
	/** 분석자 상세 DTO  */
    private RcmSysEmpDetailDTO rcmSysEmpDetailDTO = new RcmSysEmpDetailDTO();

	public RcmSysEmpDetailDTO getRcmSysEmpDetailDTO() {
		return rcmSysEmpDetailDTO;
	}
	public void setRcmSysEmpDetailDTO(RcmSysEmpDetailDTO rcmSysEmpDetailDTO) {
		this.rcmSysEmpDetailDTO = rcmSysEmpDetailDTO;
	}
	public RcmSysEmpListDTO getRcmSysEmpListDTO() {
		return rcmSysEmpListDTO;
	}
	public void setRcmSysEmpListDTO(RcmSysEmpListDTO rcmSysEmpListDTO) {
		this.rcmSysEmpListDTO = rcmSysEmpListDTO;
	}
	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}
	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}
	
}
