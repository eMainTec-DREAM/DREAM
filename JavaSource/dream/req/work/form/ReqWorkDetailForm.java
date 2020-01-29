package dream.req.work.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;

/**
 * 작업요청 - 상세 Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWorkDetailForm"
 */
public class ReqWorkDetailForm extends BaseForm
{
    //========================================================================
    /**  공통 */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
    //========================================================================
    /**  상세 */
    private ReqWorkDetailDTO reqWorkDetailDTO = new ReqWorkDetailDTO();
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

    public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
    public ReqWorkCommonDTO getReqWorkCommonDTO() {
		return reqWorkCommonDTO;
	}

    public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO) {
		this.reqWorkCommonDTO = reqWorkCommonDTO;
	}

	public ReqWorkDetailDTO getReqWorkDetailDTO() {
		return reqWorkDetailDTO;
	}

	public void setReqWorkDetailDTO(ReqWorkDetailDTO reqWorkDetailDTO) {
		this.reqWorkDetailDTO = reqWorkDetailDTO;
	}
}
