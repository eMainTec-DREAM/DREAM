package dream.req.qna.form;

import common.struts.BaseForm;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * �亯- ���
 * @author  kim21017
 * @version $Id: MaQnaAnsListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maQnaAnsListForm"
 */
public class MaQnaAnsListForm extends BaseForm
{    
    //===============================================================
    /** ���� ���� */
    private MaQnaCommonDTO maQnaCommonDTO = new MaQnaCommonDTO();
    /** �亯  */
    private MaQnaAnsListDTO maQnaAnsListDTO = new MaQnaAnsListDTO();
    
	public MaQnaCommonDTO getMaQnaCommonDTO() {
		return maQnaCommonDTO;
	}

	public void setMaQnaCommonDTO(MaQnaCommonDTO maQnaCommonDTO) {
		this.maQnaCommonDTO = maQnaCommonDTO;
	}

	public MaQnaAnsListDTO getMaQnaAnsListDTO() {
		return maQnaAnsListDTO;
	}

	public void setMaQnaAnsListDTO(MaQnaAnsListDTO maQnaAnsListDTO) {
		this.maQnaAnsListDTO = maQnaAnsListDTO;
	}
}
