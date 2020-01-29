package dream.rcm.pmtask.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;

/**
 * 
 * @author  kim2107
 * @version $Id: RcmPmtaskCndtDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmPmtaskCndtDetailForm"
 */
public class RcmPmtaskCndtDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private RcmPmtaskCommonDTO rcmPmtaskCommonDTO = new RcmPmtaskCommonDTO();
	/** 답변  DTO  */
    private RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO = new RcmPmtaskCndtListDTO();
	/** 답변  Detail DTO  */
    private RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO = new RcmPmtaskCndtDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmPmtaskCndtListDTO getRcmPmtaskCndtListDTO() {
		return rcmPmtaskCndtListDTO;
	}
	public void setRcmPmtaskCndtListDTO(RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO) {
		this.rcmPmtaskCndtListDTO = rcmPmtaskCndtListDTO;
	}
	public RcmPmtaskCndtDetailDTO getRcmPmtaskCndtDetailDTO() {
		return rcmPmtaskCndtDetailDTO;
	}
	public void setRcmPmtaskCndtDetailDTO(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO) {
		this.rcmPmtaskCndtDetailDTO = rcmPmtaskCndtDetailDTO;
	}
	public RcmPmtaskCommonDTO getRcmPmtaskCommonDTO() {
		return rcmPmtaskCommonDTO;
	}
	public void setRcmPmtaskCommonDTO(RcmPmtaskCommonDTO rcmPmtaskCommonDTO) {
		this.rcmPmtaskCommonDTO = rcmPmtaskCommonDTO;
	}
	
}
