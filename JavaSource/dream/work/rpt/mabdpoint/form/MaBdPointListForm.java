package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;

/**
 * 이상점검조치 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointListForm"
 */
public class MaBdPointListForm extends BaseForm
{    
    //===============================================================
    /** 이상점검조치 공통 */
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    
	public MaBdPointCommonDTO getMaBdPointCommonDTO() {
		return maBdPointCommonDTO;
	}

	public void setMaBdPointCommonDTO(MaBdPointCommonDTO maBdPointCommonDTO) {
		this.maBdPointCommonDTO = maBdPointCommonDTO;
	}
}
