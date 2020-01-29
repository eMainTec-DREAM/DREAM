package dream.rcm.pmtask.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;

/**
 * 
 * @author  kim2107
 * @version $Id: RcmPmtaskMapDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmPmtaskMapDetailForm"
 */
public class RcmPmtaskMapDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private RcmPmtaskCommonDTO rcmPmtaskCommonDTO = new RcmPmtaskCommonDTO();
	/** 답변  DTO  */
    private RcmPmtaskMapListDTO rcmPmtaskMapListDTO = new RcmPmtaskMapListDTO();
	/** 답변  Detail DTO  */
    private RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO = new RcmPmtaskMapDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmPmtaskMapListDTO getRcmPmtaskMapListDTO() {
		return rcmPmtaskMapListDTO;
	}
	public void setRcmPmtaskMapListDTO(RcmPmtaskMapListDTO rcmPmtaskMapListDTO) {
		this.rcmPmtaskMapListDTO = rcmPmtaskMapListDTO;
	}
	public RcmPmtaskMapDetailDTO getRcmPmtaskMapDetailDTO() {
		return rcmPmtaskMapDetailDTO;
	}
	public void setRcmPmtaskMapDetailDTO(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO) {
		this.rcmPmtaskMapDetailDTO = rcmPmtaskMapDetailDTO;
	}
	public RcmPmtaskCommonDTO getRcmPmtaskCommonDTO() {
		return rcmPmtaskCommonDTO;
	}
	public void setRcmPmtaskCommonDTO(RcmPmtaskCommonDTO rcmPmtaskCommonDTO) {
		this.rcmPmtaskCommonDTO = rcmPmtaskCommonDTO;
	}
	
}
