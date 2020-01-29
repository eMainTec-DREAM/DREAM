package dream.consult.program.btn.form;

import common.struts.BaseForm;
import dream.consult.program.btn.dto.MaBtnMngCommonDTO;

/**
 * ��ư - ��� form
 * @author  kim21017
 * @version $Id: MaBtnMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBtnMngListForm"
 */
public class MaBtnMngListForm extends BaseForm
{    
    //===============================================================
    /** ��ư ���� */
    private MaBtnMngCommonDTO maBtnMngCommonDTO = new MaBtnMngCommonDTO();

	public MaBtnMngCommonDTO getMaBtnMngCommonDTO() {
		return maBtnMngCommonDTO;
	}

	public void setMaBtnMngCommonDTO(MaBtnMngCommonDTO maBtnMngCommonDTO) {
		this.maBtnMngCommonDTO = maBtnMngCommonDTO;
	}

}
