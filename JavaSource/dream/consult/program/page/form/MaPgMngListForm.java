package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * ȭ�� - ��� form
 * @author  kim21017
 * @version $Id: MaPgMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngListForm"
 */
public class MaPgMngListForm extends BaseForm
{    
    //===============================================================
    /** ȭ�� ���� */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    
	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}

	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}
}
