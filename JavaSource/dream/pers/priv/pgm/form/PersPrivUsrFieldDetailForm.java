package dream.pers.priv.pgm.form;

import common.struts.BaseForm;

import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;

/**
 * ȭ�麰 �ʵ� �� 
 * @author  kim2107
 * @version $Id: MaPgUsrFieldDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="persPrivUsrFieldDetailForm"
 */
public class PersPrivUsrFieldDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO = new PersPrivUsrFieldCommonDTO();
	/** ȭ�麰 �ʵ� �� DTO  */
    private PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO = new PersPrivUsrFieldDetailDTO();
    
    
    public PersPrivUsrFieldDetailDTO getPersPrivUsrFieldDetailDTO() {
		return persPrivUsrFieldDetailDTO;
	}
	public void setPersPrivUsrFieldDetailDTO(
			PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO) {
		this.persPrivUsrFieldDetailDTO = persPrivUsrFieldDetailDTO;
	}
	public PersPrivUsrFieldCommonDTO getPersPrivUsrFieldCommonDTO() {
		return persPrivUsrFieldCommonDTO;
	}
	public void setPersPrivUsrFieldCommonDTO(
			PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO) {
		this.persPrivUsrFieldCommonDTO = persPrivUsrFieldCommonDTO;
	}
	
}
