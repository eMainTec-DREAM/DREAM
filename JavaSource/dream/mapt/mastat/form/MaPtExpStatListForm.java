package dream.mapt.mastat.form;

import common.struts.BaseForm;
import dream.mapt.mastat.dto.MaPtExpStatCommonDTO;

/**
 * ������м� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtExpStatListForm"
 */
public class MaPtExpStatListForm extends BaseForm
{    
    //===============================================================
    /** ������м� ���� */
    private MaPtExpStatCommonDTO maPtExpStatCommonDTO = new MaPtExpStatCommonDTO();
    
	public MaPtExpStatCommonDTO getMaPtExpStatCommonDTO() {
		return maPtExpStatCommonDTO;
	}

	public void setMaPtExpStatCommonDTO(MaPtExpStatCommonDTO maPtExpStatCommonDTO) {
		this.maPtExpStatCommonDTO = maPtExpStatCommonDTO;
	}
}
