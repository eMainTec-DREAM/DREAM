package dream.part.rpt.mastat.form;

import common.struts.BaseForm;
import dream.part.rpt.mastat.dto.MaPtRepStatCommonDTO;

/**
 * ����������� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepStatListForm"
 */
public class MaPtRepStatListForm extends BaseForm
{    
    //===============================================================
    /** ����������� ���� */
    private MaPtRepStatCommonDTO maPtRepStatCommonDTO = new MaPtRepStatCommonDTO();
    
	public MaPtRepStatCommonDTO getMaPtRepStatCommonDTO() {
		return maPtRepStatCommonDTO;
	}

	public void setMaPtRepStatCommonDTO(MaPtRepStatCommonDTO maPtRepStatCommonDTO) {
		this.maPtRepStatCommonDTO = maPtRepStatCommonDTO;
	}
}
