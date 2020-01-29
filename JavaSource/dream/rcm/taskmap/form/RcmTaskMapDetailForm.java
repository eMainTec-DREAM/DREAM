package dream.rcm.taskmap.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapDetailDTO;

/**
 * ����- �� Form
 * @author  kim21017
 * @version $Id: RcmTaskMapDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmTaskMapDetailForm"
 */
public class RcmTaskMapDetailForm extends BaseForm
{
    //========================================================================
    /** ���� ���� */ 
    private RcmTaskMapCommonDTO rcmTaskMapCommonDTO = new RcmTaskMapCommonDTO();
    //========================================================================
    /** ���� �� */
    private RcmTaskMapDetailDTO rcmTaskMapDetailDTO = new RcmTaskMapDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public RcmTaskMapCommonDTO getRcmTaskMapCommonDTO() {
		return rcmTaskMapCommonDTO;
	}

	public void setRcmTaskMapCommonDTO(RcmTaskMapCommonDTO rcmTaskMapCommonDTO) {
		this.rcmTaskMapCommonDTO = rcmTaskMapCommonDTO;
	}

	public RcmTaskMapDetailDTO getRcmTaskMapDetailDTO() {
		return rcmTaskMapDetailDTO;
	}

	public void setRcmTaskMapDetailDTO(RcmTaskMapDetailDTO rcmTaskMapDetailDTO) {
		this.rcmTaskMapDetailDTO = rcmTaskMapDetailDTO;
	}
}