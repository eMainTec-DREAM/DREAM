package dream.invt.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.req.work.dto.MaWoReqCommonDTO;

/**
 * 상세 Form
 * @author  kim21017
 * @version $Id: InvtDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="invtDetailForm"
 */
public class InvtDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    //========================================================================
    /** 상세 */
    private InvtDetailDTO invtDetailDTO = new InvtDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** 결재 진행 이력 DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    /** 작업요청 공통 */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    
    
	public MaWoReqCommonDTO getMaWoReqCommonDTO()
    {
        return maWoReqCommonDTO;
    }

    public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO)
    {
        this.maWoReqCommonDTO = maWoReqCommonDTO;
    }

    public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }

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

	public InvtDetailDTO getInvtDetailDTO() {
		return invtDetailDTO;
	}

	public void setInvtDetailDTO(InvtDetailDTO invtDetailDTO) {
		this.invtDetailDTO = invtDetailDTO;
	}
}