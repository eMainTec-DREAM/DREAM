package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;

/**
 * ȭ�麰 �ʵ� ���
 * @author  kim21017
 * @version $Id: MaPgMngFieldListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngFieldListForm"
 */
public class MaPgMngFieldListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    /** page-field */
    private MaPgMngFieldListDTO maPgMngFieldListDTO = new MaPgMngFieldListDTO();
	

	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}

	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}

	public MaPgMngFieldListDTO getMaPgMngFieldListDTO() {
		return maPgMngFieldListDTO;
	}

	public void setMaPgMngFieldListDTO(MaPgMngFieldListDTO maPgMngFieldListDTO) {
		this.maPgMngFieldListDTO = maPgMngFieldListDTO;
	}
}
