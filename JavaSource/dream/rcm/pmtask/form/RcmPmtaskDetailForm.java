package dream.rcm.pmtask.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskDetailDTO;

/**
 * 상세 Form
 * @author  kim21017
 * @version $Id: RcmPmtaskDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmPmtaskDetailForm"
 */
public class RcmPmtaskDetailForm extends BaseForm
{
	
	private RcmFmeaCommonDTO rcmFmeaCommonDTO = new RcmFmeaCommonDTO();
    //========================================================================
    /** 공통 */ 
    private RcmPmtaskCommonDTO rcmPmtaskCommonDTO = new RcmPmtaskCommonDTO();
    //========================================================================
    /** 상세 */
    private RcmPmtaskDetailDTO rcmPmtaskDetailDTO = new RcmPmtaskDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public RcmFmeaCommonDTO getRcmFmeaCommonDTO() {
		return rcmFmeaCommonDTO;
	}

	public void setRcmFmeaCommonDTO(RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		this.rcmFmeaCommonDTO = rcmFmeaCommonDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public RcmPmtaskCommonDTO getRcmPmtaskCommonDTO() {
		return rcmPmtaskCommonDTO;
	}

	public void setRcmPmtaskCommonDTO(RcmPmtaskCommonDTO rcmPmtaskCommonDTO) {
		this.rcmPmtaskCommonDTO = rcmPmtaskCommonDTO;
	}

	public RcmPmtaskDetailDTO getRcmPmtaskDetailDTO() {
		return rcmPmtaskDetailDTO;
	}

	public void setRcmPmtaskDetailDTO(RcmPmtaskDetailDTO rcmPmtaskDetailDTO) {
		this.rcmPmtaskDetailDTO = rcmPmtaskDetailDTO;
	}
}