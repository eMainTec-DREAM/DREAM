package dream.consult.comp.eqmgr.form;

import common.struts.BaseForm;
import dream.consult.comp.eqmgr.dto.MaEqMngCommonDTO;

/**
 * �������ں���- ��� form
 * @author  jung7126
 * @version $Id: MaEqMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMngListForm"
 */
public class MaEqMngListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqMngCommonDTO maEqMngCommonDTO = new MaEqMngCommonDTO();
    
	public MaEqMngCommonDTO getMaEqMngCommonDTO() {
		return maEqMngCommonDTO;
	}

	public void setMaEqMngCommonDTO(MaEqMngCommonDTO maEqMngCommonDTO) {
		this.maEqMngCommonDTO = maEqMngCommonDTO;
	}
}
