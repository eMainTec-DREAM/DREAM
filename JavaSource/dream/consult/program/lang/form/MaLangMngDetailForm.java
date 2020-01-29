package dream.consult.program.lang.form;

import common.struts.BaseForm;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;
import dream.consult.program.lang.dto.MaLangMngDetailDTO;

/**
 * �ٱ���- �� Form
 * @author  kim21017
 * @version $Id: MaLangMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maLangMngDetailForm"
 */
public class MaLangMngDetailForm extends BaseForm
{
    //========================================================================
    /** �ٱ��� ���� */ 
    private MaLangMngCommonDTO maLangMngCommonDTO = new MaLangMngCommonDTO();
    //========================================================================
    /** �ٱ��� �� */
    private MaLangMngDetailDTO maLangMngDetailDTO = new MaLangMngDetailDTO();
    
	public MaLangMngCommonDTO getMaLangMngCommonDTO() {
		return maLangMngCommonDTO;
	}

	public void setMaLangMngCommonDTO(MaLangMngCommonDTO maLangMngCommonDTO) {
		this.maLangMngCommonDTO = maLangMngCommonDTO;
	}

	public MaLangMngDetailDTO getMaLangMngDetailDTO() {
		return maLangMngDetailDTO;
	}

	public void setMaLangMngDetailDTO(MaLangMngDetailDTO maLangMngDetailDTO) {
		this.maLangMngDetailDTO = maLangMngDetailDTO;
	}
}
