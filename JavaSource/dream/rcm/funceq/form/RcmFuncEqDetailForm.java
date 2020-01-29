package dream.rcm.funceq.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqDetailDTO;

/**
 * ����- �� Form
 * @author  kim21017
 * @version $Id: RcmFuncEqDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFuncEqDetailForm"
 */
public class RcmFuncEqDetailForm extends BaseForm
{
    //========================================================================
    /** ���� ���� */ 
    private RcmFuncEqCommonDTO rcmFuncEqCommonDTO = new RcmFuncEqCommonDTO();
    //========================================================================
    /** ���� �� */
    private RcmFuncEqDetailDTO rcmFuncEqDetailDTO = new RcmFuncEqDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public RcmFuncEqCommonDTO getRcmFuncEqCommonDTO() {
		return rcmFuncEqCommonDTO;
	}

	public void setRcmFuncEqCommonDTO(RcmFuncEqCommonDTO rcmFuncEqCommonDTO) {
		this.rcmFuncEqCommonDTO = rcmFuncEqCommonDTO;
	}

	public RcmFuncEqDetailDTO getRcmFuncEqDetailDTO() {
		return rcmFuncEqDetailDTO;
	}

	public void setRcmFuncEqDetailDTO(RcmFuncEqDetailDTO rcmFuncEqDetailDTO) {
		this.rcmFuncEqDetailDTO = rcmFuncEqDetailDTO;
	}
}