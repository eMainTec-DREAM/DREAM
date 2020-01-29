package dream.tool.iss.rtn.form;

import common.struts.BaseForm;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;

/**
 * 공기구반납 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttRtnListForm"
 */
public class MaPttRtnListForm extends BaseForm
{    
    //===============================================================
    /** 공기구반납 공통 */
    private MaPttRtnCommonDTO maPttRtnCommonDTO = new MaPttRtnCommonDTO();
    
	public MaPttRtnCommonDTO getMaPttRtnCommonDTO() {
		return maPttRtnCommonDTO;
	}

	public void setMaPttRtnCommonDTO(MaPttRtnCommonDTO maPttRtnCommonDTO) {
		this.maPttRtnCommonDTO = maPttRtnCommonDTO;
	}
}
