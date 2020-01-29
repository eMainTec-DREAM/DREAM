package dream.part.rpt.mastat.form;

import common.struts.BaseForm;
import dream.part.rpt.mastat.dto.MaPtRepStatCommonDTO;

/**
 * 자재수리내역 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepStatListForm"
 */
public class MaPtRepStatListForm extends BaseForm
{    
    //===============================================================
    /** 자재수리내역 공통 */
    private MaPtRepStatCommonDTO maPtRepStatCommonDTO = new MaPtRepStatCommonDTO();
    
	public MaPtRepStatCommonDTO getMaPtRepStatCommonDTO() {
		return maPtRepStatCommonDTO;
	}

	public void setMaPtRepStatCommonDTO(MaPtRepStatCommonDTO maPtRepStatCommonDTO) {
		this.maPtRepStatCommonDTO = maPtRepStatCommonDTO;
	}
}
