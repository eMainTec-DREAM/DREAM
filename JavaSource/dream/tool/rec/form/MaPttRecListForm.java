package dream.tool.rec.form;

import common.struts.BaseForm;
import dream.tool.rec.dto.MaPttRecCommonDTO;

/**
 * �����԰� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttRecListForm"
 */
public class MaPttRecListForm extends BaseForm
{    
    //===============================================================
    /** �����԰� ���� */
    private MaPttRecCommonDTO maPttRecCommonDTO = new MaPttRecCommonDTO();
    
	public MaPttRecCommonDTO getMaPttRecCommonDTO() {
		return maPttRecCommonDTO;
	}

	public void setMaPttRecCommonDTO(MaPttRecCommonDTO maPttRecCommonDTO) {
		this.maPttRecCommonDTO = maPttRecCommonDTO;
	}
}
