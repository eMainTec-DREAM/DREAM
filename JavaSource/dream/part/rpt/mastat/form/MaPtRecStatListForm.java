package dream.part.rpt.mastat.form;

import common.struts.BaseForm;
import dream.part.rpt.mastat.dto.MaPtRecStatCommonDTO;

/**
 * �����԰��� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRecStatListForm"
 */
public class MaPtRecStatListForm extends BaseForm
{    
    //===============================================================
    /** �����԰��� ���� */
    private MaPtRecStatCommonDTO maPtRecStatCommonDTO = new MaPtRecStatCommonDTO();
    
	public MaPtRecStatCommonDTO getMaPtRecStatCommonDTO() {
		return maPtRecStatCommonDTO;
	}

	public void setMaPtRecStatCommonDTO(MaPtRecStatCommonDTO maPtRecStatCommonDTO) {
		this.maPtRecStatCommonDTO = maPtRecStatCommonDTO;
	}
}
