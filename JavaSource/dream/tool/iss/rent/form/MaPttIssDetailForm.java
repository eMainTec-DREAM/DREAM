package dream.tool.iss.rent.form;

import common.struts.BaseForm;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;
import dream.tool.iss.rent.dto.MaPttIssDetailDTO;

/**
 * 구매입고- 상세 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttIssDetailForm"
 */
public class MaPttIssDetailForm extends BaseForm
{
    //========================================================================
    /** 구매입고 공통 */ 
    private MaPttIssCommonDTO maPttIssCommonDTO = new MaPttIssCommonDTO();
    //========================================================================
    /** 구매입고 상세 */
    private MaPttIssDetailDTO maPttIssDetailDTO = new MaPttIssDetailDTO();

    public MaPttIssCommonDTO getMaPttIssCommonDTO() {
		return maPttIssCommonDTO;
	}

    public void setMaPttIssCommonDTO(MaPttIssCommonDTO maPttIssCommonDTO) {
		this.maPttIssCommonDTO = maPttIssCommonDTO;
	}

	public MaPttIssDetailDTO getMaPttIssDetailDTO() {
		return maPttIssDetailDTO;
	}

	public void setMaPttIssDetailDTO(MaPttIssDetailDTO maPttIssDetailDTO) {
		this.maPttIssDetailDTO = maPttIssDetailDTO;
	}
}
