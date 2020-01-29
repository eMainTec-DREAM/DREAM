package dream.req.qna.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.req.qna.dto.MaQnaAnsDetailDTO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 답변
 * @author  kim2107
 * @version $Id: MaQnaAnsDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maQnaAnsDetailForm"
 */
public class MaQnaAnsDetailForm extends BaseForm
{    
    //===============================================================
    /** 질의 - 공통 DTO */
    private MaQnaCommonDTO maQnaCommonDTO = new MaQnaCommonDTO();
	/** 답변  DTO  */
    private MaQnaAnsListDTO maQnaAnsListDTO = new MaQnaAnsListDTO();
	/** 답변  Detail DTO  */
    private MaQnaAnsDetailDTO maQnaAnsDetailDTO = new MaQnaAnsDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public MaQnaAnsListDTO getMaQnaAnsListDTO() {
		return maQnaAnsListDTO;
	}
	public void setMaQnaAnsListDTO(MaQnaAnsListDTO maQnaAnsListDTO) {
		this.maQnaAnsListDTO = maQnaAnsListDTO;
	}
	public MaQnaAnsDetailDTO getMaQnaAnsDetailDTO() {
		return maQnaAnsDetailDTO;
	}
	public void setMaQnaAnsDetailDTO(MaQnaAnsDetailDTO maQnaAnsDetailDTO) {
		this.maQnaAnsDetailDTO = maQnaAnsDetailDTO;
	}
	public MaQnaCommonDTO getMaQnaCommonDTO() {
		return maQnaCommonDTO;
	}
	public void setMaQnaCommonDTO(MaQnaCommonDTO maQnaCommonDTO) {
		this.maQnaCommonDTO = maQnaCommonDTO;
	}
	
}
