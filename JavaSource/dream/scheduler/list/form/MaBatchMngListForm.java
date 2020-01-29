package dream.scheduler.list.form;

import common.struts.BaseForm;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;

/**
 * 버튼 - 목록 form
 * @author  kim21017
 * @version $Id: MaBatchMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBatchMngListForm"
 */
public class MaBatchMngListForm extends BaseForm
{    
    //===============================================================
    /** 버튼 공통 */
    private MaBatchMngCommonDTO maBatchMngCommonDTO = new MaBatchMngCommonDTO();

	public MaBatchMngCommonDTO getMaBatchMngCommonDTO() {
		return maBatchMngCommonDTO;
	}

	public void setMaBatchMngCommonDTO(MaBatchMngCommonDTO maBatchMngCommonDTO) {
		this.maBatchMngCommonDTO = maBatchMngCommonDTO;
	}

}
