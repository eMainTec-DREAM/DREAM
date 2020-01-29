package dream.req.qna.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.dto.MaQnaDetailDTO;

/**
 * 질의- 상세 Form
 * @author  kim21017
 * @version $Id: MaQnaDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maQnaDetailForm"
 */
public class MaQnaDetailForm extends BaseForm
{
    //========================================================================
    /** 질의 공통 */ 
    private MaQnaCommonDTO maQnaCommonDTO = new MaQnaCommonDTO();
    //========================================================================
    /** 질의 상세 */
    private MaQnaDetailDTO maQnaDetailDTO = new MaQnaDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public MaQnaCommonDTO getMaQnaCommonDTO() {
		return maQnaCommonDTO;
	}

	public void setMaQnaCommonDTO(MaQnaCommonDTO maQnaCommonDTO) {
		this.maQnaCommonDTO = maQnaCommonDTO;
	}

	public MaQnaDetailDTO getMaQnaDetailDTO() {
		return maQnaDetailDTO;
	}

	public void setMaQnaDetailDTO(MaQnaDetailDTO maQnaDetailDTO) {
		this.maQnaDetailDTO = maQnaDetailDTO;
	}
}