package dream.consult.program.lang.form;

import common.struts.BaseForm;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;

/**
 * 다국어 - 목록 form
 * @author  kim21017
 * @version $Id: MaLangMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maLangMngListForm"
 */
public class MaLangMngListForm extends BaseForm
{    
    //===============================================================
    /** 다국어 공통 */
    private MaLangMngCommonDTO maLangMngCommonDTO = new MaLangMngCommonDTO();

	public MaLangMngCommonDTO getMaLangMngCommonDTO() {
		return maLangMngCommonDTO;
	}

	public void setMaLangMngCommonDTO(MaLangMngCommonDTO maLangMngCommonDTO) {
		this.maLangMngCommonDTO = maLangMngCommonDTO;
	}
}
