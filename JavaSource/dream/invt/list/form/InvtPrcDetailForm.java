package dream.invt.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPrcDetailDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * �� Form
 * @author  kim21017
 * @version $Id: InvtDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="invtPrcDetailForm"
 */
public class InvtPrcDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    //========================================================================
    /** �� */
    private InvtPrcDetailDTO invtPrcDetailDTO = new InvtPrcDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** ���� ���� �̷� DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}

	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}

	public InvtPrcDetailDTO getInvtPrcDetailDTO() {
		return invtPrcDetailDTO;
	}

	public void setInvtPrcDetailDTO(InvtPrcDetailDTO invtPrcDetailDTO) {
		this.invtPrcDetailDTO = invtPrcDetailDTO;
	}

    public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }

}