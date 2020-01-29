package dream.req.work.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResListDTO;
import dream.req.work.dto.ReqWorkResListDTO;
import dream.req.work.dto.ReqWorkReswoDetailDTO;

/**
 * 작업요청-처리사항
 * @author  kim2107
 * @version $Id: ReqWorkReswoDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="reqWorkReswoDetailForm"
 */
public class ReqWorkReswoDetailForm extends BaseForm
{    
    //===============================================================
    /**  공통 */ 
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
	/** 작업요청-처리사항 목록 DTO  */
    private MaWoReqResListDTO maWoReqResListDTO = new MaWoReqResListDTO();
    private ReqWorkResListDTO reqWorkResListDTO = new ReqWorkResListDTO();
	
    /** 작업요청-처리사항 상세 DTO  */
    private ReqWorkReswoDetailDTO reqWorkReswoDetailDTO = new ReqWorkReswoDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    public ReqWorkResListDTO getReqWorkResListDTO() {
		return reqWorkResListDTO;
	}

	public void setReqWorkResListDTO(ReqWorkResListDTO reqWorkResListDTO) {
		this.reqWorkResListDTO = reqWorkResListDTO;
	}

	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
	
	public MaWoReqResListDTO getMaWoReqResListDTO() {
		return maWoReqResListDTO;
	}

	public void setMaWoReqResListDTO(MaWoReqResListDTO maWoReqResListDTO) {
		this.maWoReqResListDTO = maWoReqResListDTO;
	}

	public ReqWorkReswoDetailDTO getReqWorkReswoDetailDTO() {
		return reqWorkReswoDetailDTO;
	}
	public void setReqWorkReswoDetailDTO(ReqWorkReswoDetailDTO reqWorkReswoDetailDTO) {
		this.reqWorkReswoDetailDTO = reqWorkReswoDetailDTO;
	}
}
