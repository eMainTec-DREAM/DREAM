package dream.tool.iss.rtn.form;

import common.struts.BaseForm;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;
import dream.tool.iss.rtn.dto.MaPttRtnDetailDTO;

/**
 * 공기구반납- 상세 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttRtnDetailForm"
 */
public class MaPttRtnDetailForm extends BaseForm
{
    //========================================================================
    /** 공기구반납 공통 */ 
    private MaPttRtnCommonDTO maPttRtnCommonDTO = new MaPttRtnCommonDTO();
    //========================================================================
    /** 공기구반납 상세 */
    private MaPttRtnDetailDTO maPttRtnDetailDTO = new MaPttRtnDetailDTO();

    public MaPttRtnCommonDTO getMaPttRtnCommonDTO() {
		return maPttRtnCommonDTO;
	}

    public void setMaPttRtnCommonDTO(MaPttRtnCommonDTO maPttRtnCommonDTO) {
		this.maPttRtnCommonDTO = maPttRtnCommonDTO;
	}

	public MaPttRtnDetailDTO getMaPttRtnDetailDTO() {
		return maPttRtnDetailDTO;
	}

	public void setMaPttRtnDetailDTO(MaPttRtnDetailDTO maPttRtnDetailDTO) {
		this.maPttRtnDetailDTO = maPttRtnDetailDTO;
	}
}
