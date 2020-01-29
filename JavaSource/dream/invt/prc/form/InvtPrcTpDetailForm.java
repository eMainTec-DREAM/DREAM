package dream.invt.prc.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpDetailDTO;

/**
 * 구매절차 상세 Form
 * @author  kim21017
 * @version $Id: InvtPrcTpDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="invtPrcTpDetailForm"
 */
public class InvtPrcTpDetailForm extends BaseForm
{
    //========================================================================
    /** 구매절차 공통 */ 
    private InvtPrcTpCommonDTO invtPrcTpCommonDTO = new InvtPrcTpCommonDTO();
    //========================================================================
    /** 구매절차 상세 */
    private InvtPrcTpDetailDTO invtPrcTpDetailDTO = new InvtPrcTpDetailDTO();
    /** 구매절차 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public InvtPrcTpCommonDTO getInvtPrcTpCommonDTO() {
		return invtPrcTpCommonDTO;
	}

	public void setInvtPrcTpCommonDTO(InvtPrcTpCommonDTO invtPrcTpCommonDTO) {
		this.invtPrcTpCommonDTO = invtPrcTpCommonDTO;
	}

	public InvtPrcTpDetailDTO getInvtPrcTpDetailDTO() {
		return invtPrcTpDetailDTO;
	}

	public void setInvtPrcTpDetailDTO(InvtPrcTpDetailDTO invtPrcTpDetailDTO) {
		this.invtPrcTpDetailDTO = invtPrcTpDetailDTO;
	}
}