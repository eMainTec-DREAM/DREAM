package dream.scheduler.list.form;

import common.struts.BaseForm;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.dto.MaBatchMngDetailDTO;

/**
 * 버튼- 상세 Form
 * @author  kim21017
 * @version $Id: MaBatchMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBatchMngDetailForm"
 */
public class MaBatchMngDetailForm extends BaseForm
{
    //========================================================================
    /** 버튼 공통 */ 
    private MaBatchMngCommonDTO maBatchMngCommonDTO = new MaBatchMngCommonDTO();
    //========================================================================
    /** 버튼 상세 */
    private MaBatchMngDetailDTO maBatchMngDetailDTO = new MaBatchMngDetailDTO();
    

	public MaBatchMngCommonDTO getMaBatchMngCommonDTO() {
		return maBatchMngCommonDTO;
	}

	public void setMaBatchMngCommonDTO(MaBatchMngCommonDTO maBatchMngCommonDTO) {
		this.maBatchMngCommonDTO = maBatchMngCommonDTO;
	}

	public MaBatchMngDetailDTO getMaBatchMngDetailDTO() {
		return maBatchMngDetailDTO;
	}

	public void setMaBatchMngDetailDTO(MaBatchMngDetailDTO maBatchMngDetailDTO) {
		this.maBatchMngDetailDTO = maBatchMngDetailDTO;
	}

}
