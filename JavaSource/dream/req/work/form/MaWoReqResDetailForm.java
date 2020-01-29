package dream.req.work.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.MaWoReqResListDTO;

/**
 * 작업요청-처리사항
 * @author  kim2107
 * @version $Id: MaWoReqResDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoReqResDetailForm"
 */
public class MaWoReqResDetailForm extends BaseForm
{    
    //===============================================================
    /**  공통 */ 
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
	/** 작업요청-처리사항 목록 DTO  */
    private MaWoReqResListDTO maWoReqResListDTO = new MaWoReqResListDTO();
	/** 작업요청-처리사항 상세 DTO  */
    private MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
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
	public MaWoReqResDetailDTO getMaWoReqResDetailDTO() {
		return maWoReqResDetailDTO;
	}
	public void setMaWoReqResDetailDTO(MaWoReqResDetailDTO maWoReqResDetailDTO) {
		this.maWoReqResDetailDTO = maWoReqResDetailDTO;
	}
}
