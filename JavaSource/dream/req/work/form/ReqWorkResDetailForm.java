package dream.req.work.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 작업요청-처리사항
 * @author  kim2107
 * @version $Id: ReqWorkResDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="reqWorkResDetailForm"
 */
public class ReqWorkResDetailForm extends BaseForm
{
    //===============================================================
    /**  공통 */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
	/** 작업요청-처리사항 목록 DTO  */
    private ReqWorkResListDTO reqWorkResListDTO = new ReqWorkResListDTO();
	/** 작업요청-처리사항 상세 DTO  */
    private ReqWorkResDetailDTO reqWorkResDetailDTO = new ReqWorkResDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();


    public ReqWorkCommonDTO getReqWorkCommonDTO() {
		return reqWorkCommonDTO;
	}

	public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO) {
		this.reqWorkCommonDTO = reqWorkCommonDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
	public ReqWorkResListDTO getReqWorkResListDTO() {
		return reqWorkResListDTO;
	}
	public void setReqWorkResListDTO(ReqWorkResListDTO reqWorkResListDTO) {
		this.reqWorkResListDTO = reqWorkResListDTO;
	}
	public ReqWorkResDetailDTO getReqWorkResDetailDTO() {
		return reqWorkResDetailDTO;
	}
	public void setReqWorkResDetailDTO(ReqWorkResDetailDTO reqWorkResDetailDTO) {
		this.reqWorkResDetailDTO = reqWorkResDetailDTO;
	}
}
