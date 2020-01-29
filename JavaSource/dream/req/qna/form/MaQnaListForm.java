package dream.req.qna.form;

import common.struts.BaseForm;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 질의 - 목록 form
 * @author  kim21017
 * @version $Id: MaQnaListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maQnaListForm"
 */
public class MaQnaListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private MaQnaCommonDTO maQnaCommonDTO = new MaQnaCommonDTO();
    
	public MaQnaCommonDTO getMaQnaCommonDTO() {
		return maQnaCommonDTO;
	}

	public void setMaQnaCommonDTO(MaQnaCommonDTO maQnaCommonDTO) {
		this.maQnaCommonDTO = maQnaCommonDTO;
	}
}
