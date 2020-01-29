package dream.mapt.mastat.form;

import common.struts.BaseForm;
import dream.mapt.mastat.dto.MaPtExpStatCommonDTO;

/**
 * 자재비용분석 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtExpStatListForm"
 */
public class MaPtExpStatListForm extends BaseForm
{    
    //===============================================================
    /** 자재비용분석 공통 */
    private MaPtExpStatCommonDTO maPtExpStatCommonDTO = new MaPtExpStatCommonDTO();
    
	public MaPtExpStatCommonDTO getMaPtExpStatCommonDTO() {
		return maPtExpStatCommonDTO;
	}

	public void setMaPtExpStatCommonDTO(MaPtExpStatCommonDTO maPtExpStatCommonDTO) {
		this.maPtExpStatCommonDTO = maPtExpStatCommonDTO;
	}
}
