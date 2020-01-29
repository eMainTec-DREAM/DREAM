package dream.invt.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtItemsDetailDTO;

/**
 * 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="invtItemsDetailForm"
 */
public class InvtItemsDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
	/**  Detail DTO  */
    private InvtItemsDetailDTO invtCrityDetailDTO = new InvtItemsDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public InvtItemsDetailDTO getInvtItemsDetailDTO() {
		return invtCrityDetailDTO;
	}
	public void setInvtItemsDetailDTO(InvtItemsDetailDTO invtCrityDetailDTO) {
		this.invtCrityDetailDTO = invtCrityDetailDTO;
	}
	public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}
	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
	
}
