package dream.rcm.fmea.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaDetailDTO;

/**
 * �� Form
 * @author  kim21017
 * @version $Id: RcmFmeaDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFmeaDetailForm"
 */
public class RcmFmeaDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private RcmFmeaCommonDTO rcmFmeaCommonDTO = new RcmFmeaCommonDTO();
    //========================================================================
    /** �� */
    private RcmFmeaDetailDTO rcmFmeaDetailDTO = new RcmFmeaDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public RcmFmeaCommonDTO getRcmFmeaCommonDTO() {
		return rcmFmeaCommonDTO;
	}

	public void setRcmFmeaCommonDTO(RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		this.rcmFmeaCommonDTO = rcmFmeaCommonDTO;
	}

	public RcmFmeaDetailDTO getRcmFmeaDetailDTO() {
		return rcmFmeaDetailDTO;
	}

	public void setRcmFmeaDetailDTO(RcmFmeaDetailDTO rcmFmeaDetailDTO) {
		this.rcmFmeaDetailDTO = rcmFmeaDetailDTO;
	}
}