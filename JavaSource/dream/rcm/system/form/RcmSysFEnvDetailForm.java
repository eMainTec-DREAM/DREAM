package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.system.dto.RcmSysFEnvDetailDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 운전환경
 * @author  kim2107
 * @version $Id: RcmSysFEnvDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysFEnvDetailForm"
 */
public class RcmSysFEnvDetailForm extends BaseForm
{    
    //===============================================================
    /** 질의 - 공통 DTO */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
	/** 답변  DTO  */
    private RcmSysFEnvListDTO rcmSysFEnvListDTO = new RcmSysFEnvListDTO();
	/** 답변  Detail DTO  */
    private RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO = new RcmSysFEnvDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmSysFEnvListDTO getRcmSysFEnvListDTO() {
		return rcmSysFEnvListDTO;
	}
	public void setRcmSysFEnvListDTO(RcmSysFEnvListDTO rcmSysFEnvListDTO) {
		this.rcmSysFEnvListDTO = rcmSysFEnvListDTO;
	}
	public RcmSysFEnvDetailDTO getRcmSysFEnvDetailDTO() {
		return rcmSysFEnvDetailDTO;
	}
	public void setRcmSysFEnvDetailDTO(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO) {
		this.rcmSysFEnvDetailDTO = rcmSysFEnvDetailDTO;
	}
	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}
	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}
	
}
