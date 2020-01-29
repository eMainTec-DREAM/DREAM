package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngDetailDTO;

/**
 * 화면- 상세 Form
 * @author  kim21017
 * @version $Id: MaPgMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngDetailForm"
 */
public class MaPgMngDetailForm extends BaseForm
{
    //========================================================================
    /** 화면 공통 */ 
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    //========================================================================
    /** 화면 상세 */
    private MaPgMngDetailDTO maPgMngDetailDTO = new MaPgMngDetailDTO();
    

	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}

    public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}

	public MaPgMngDetailDTO getMaPgMngDetailDTO() {
		return maPgMngDetailDTO;
	}

	public void setMaPgMngDetailDTO(MaPgMngDetailDTO maPgMngDetailDTO) {
		this.maPgMngDetailDTO = maPgMngDetailDTO;
	}
}
