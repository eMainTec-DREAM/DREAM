package dream.rcm.ffail.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailDetailDTO;

/**
 * 질의- 상세 Form
 * @author  kim21017
 * @version $Id: RcmFfailDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFfailDetailForm"
 */
public class RcmFfailDetailForm extends BaseForm
{
    //========================================================================
    /** 질의 공통 */ 
    private RcmFfailCommonDTO rcmFfailCommonDTO = new RcmFfailCommonDTO();
    //========================================================================
    /** 질의 상세 */
    private RcmFfailDetailDTO rcmFfailDetailDTO = new RcmFfailDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public RcmFfailCommonDTO getRcmFfailCommonDTO() {
		return rcmFfailCommonDTO;
	}

	public void setRcmFfailCommonDTO(RcmFfailCommonDTO rcmFfailCommonDTO) {
		this.rcmFfailCommonDTO = rcmFfailCommonDTO;
	}

	public RcmFfailDetailDTO getRcmFfailDetailDTO() {
		return rcmFfailDetailDTO;
	}

	public void setRcmFfailDetailDTO(RcmFfailDetailDTO rcmFfailDetailDTO) {
		this.rcmFfailDetailDTO = rcmFfailDetailDTO;
	}
}