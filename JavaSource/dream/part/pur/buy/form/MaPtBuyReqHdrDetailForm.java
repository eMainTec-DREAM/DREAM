package dream.part.pur.buy.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrDetailDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * 구매신청- 상세 Form
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPtBuyReqHdrDetailForm"
 */
public class MaPtBuyReqHdrDetailForm extends BaseForm
{
    //========================================================================
    /** 구매신청 공통 */ 
    private MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
    //========================================================================
    /** 구매신청 상세 */
    private MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = new MaPtBuyReqHdrDetailDTO();
    
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** 결재 진행 이력 DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();

    
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

	public MaPtBuyReqHdrCommonDTO getMaPtBuyReqHdrCommonDTO() {
		return maPtBuyReqHdrCommonDTO;
	}

	public void setMaPtBuyReqHdrCommonDTO(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO) {
		this.maPtBuyReqHdrCommonDTO = maPtBuyReqHdrCommonDTO;
	}

	public MaPtBuyReqHdrDetailDTO getMaPtBuyReqHdrDetailDTO() {
		return maPtBuyReqHdrDetailDTO;
	}

	public void setMaPtBuyReqHdrDetailDTO(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO) {
		this.maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailDTO;
	}
}