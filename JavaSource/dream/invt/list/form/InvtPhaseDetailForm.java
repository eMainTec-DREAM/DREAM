package dream.invt.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.invt.list.dto.InvtPhaseDetailDTO;



/**
 * 
 * @author  kim2107
 * @version $Id: InvtPhaseDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="invtPhaseDetailForm"
 */
public class InvtPhaseDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    
    /** 투자결과 Detail DTO*/
    private InvtDetailDTO invtDetailDTO = new InvtDetailDTO();
	
    /**  Detail DTO  */
    private InvtPhaseDetailDTO invtCrityDetailDTO = new InvtPhaseDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    
	public InvtDetailDTO getInvtDetailDTO()
    {
        return invtDetailDTO;
    }
    public void setInvtDetailDTO(InvtDetailDTO invtDetailDTO)
    {
        this.invtDetailDTO = invtDetailDTO;
    }
    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public InvtPhaseDetailDTO getInvtPhaseDetailDTO() {
		return invtCrityDetailDTO;
	}
	public void setInvtPhaseDetailDTO(InvtPhaseDetailDTO invtCrityDetailDTO) {
		this.invtCrityDetailDTO = invtCrityDetailDTO;
	}
	public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}
	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
	
}
