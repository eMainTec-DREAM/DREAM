package dream.consult.program.lang.form;

import common.struts.BaseForm;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;

/**
 * �ٱ��� - ��� form
 * @author  kim21017
 * @version $Id: MaLangMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maLangMngListForm"
 */
public class MaLangMngListForm extends BaseForm
{    
    //===============================================================
    /** �ٱ��� ���� */
    private MaLangMngCommonDTO maLangMngCommonDTO = new MaLangMngCommonDTO();

	public MaLangMngCommonDTO getMaLangMngCommonDTO() {
		return maLangMngCommonDTO;
	}

	public void setMaLangMngCommonDTO(MaLangMngCommonDTO maLangMngCommonDTO) {
		this.maLangMngCommonDTO = maLangMngCommonDTO;
	}
}
