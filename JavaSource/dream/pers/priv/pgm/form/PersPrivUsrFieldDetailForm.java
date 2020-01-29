package dream.pers.priv.pgm.form;

import common.struts.BaseForm;

import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;

/**
 * 화면별 필드 상세 
 * @author  kim2107
 * @version $Id: MaPgUsrFieldDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="persPrivUsrFieldDetailForm"
 */
public class PersPrivUsrFieldDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO = new PersPrivUsrFieldCommonDTO();
	/** 화면별 필드 상세 DTO  */
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
