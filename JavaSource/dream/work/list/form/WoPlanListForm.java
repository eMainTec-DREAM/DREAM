package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;

/**
 * 작업계획목록 - 목록 form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanListForm"
 */
public class WoPlanListForm extends BaseForm
{    
    //===============================================================
    /** 작업결과 공통 */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}

	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}
}
