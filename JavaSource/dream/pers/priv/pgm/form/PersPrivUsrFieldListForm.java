package dream.pers.priv.pgm.form;

import common.struts.BaseForm;

import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;

/**
 * ȭ�麰 ���� �ʵ� ���
 * @author  kim21017
 * @version $Id: MaPgUsrFieldListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="persPrivUsrFieldListForm"
 */
public class PersPrivUsrFieldListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
	private PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO = new PersPrivUsrFieldCommonDTO();

	public PersPrivUsrFieldCommonDTO getPersPrivUsrFieldCommonDTO() {
		return persPrivUsrFieldCommonDTO;
	}

	public void setPersPrivUsrFieldCommonDTO(
			PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO) {
		this.persPrivUsrFieldCommonDTO = persPrivUsrFieldCommonDTO;
	}
	

	
}
