package dream.consult.program.btn.form;

import common.struts.BaseForm;
import dream.consult.program.btn.dto.MaBtnMngCommonDTO;
import dream.consult.program.btn.dto.MaBtnMngDetailDTO;

/**
 * 버튼- 상세 Form
 * @author  kim21017
 * @version $Id: MaBtnMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBtnMngDetailForm"
 */
public class MaBtnMngDetailForm extends BaseForm
{
    //========================================================================
    /** 버튼 공통 */ 
    private MaBtnMngCommonDTO maBtnMngCommonDTO = new MaBtnMngCommonDTO();
    //========================================================================
    /** 버튼 상세 */
    private MaBtnMngDetailDTO maBtnMngDetailDTO = new MaBtnMngDetailDTO();
    

	public MaBtnMngCommonDTO getMaBtnMngCommonDTO() {
		return maBtnMngCommonDTO;
	}

	public void setMaBtnMngCommonDTO(MaBtnMngCommonDTO maBtnMngCommonDTO) {
		this.maBtnMngCommonDTO = maBtnMngCommonDTO;
	}

	public MaBtnMngDetailDTO getMaBtnMngDetailDTO() {
		return maBtnMngDetailDTO;
	}

	public void setMaBtnMngDetailDTO(MaBtnMngDetailDTO maBtnMngDetailDTO) {
		this.maBtnMngDetailDTO = maBtnMngDetailDTO;
	}

}
