package dream.tool.iss.rtn.form;

import common.struts.BaseForm;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;

/**
 * ���ⱸ�ݳ� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttRtnListForm"
 */
public class MaPttRtnListForm extends BaseForm
{    
    //===============================================================
    /** ���ⱸ�ݳ� ���� */
    private MaPttRtnCommonDTO maPttRtnCommonDTO = new MaPttRtnCommonDTO();
    
	public MaPttRtnCommonDTO getMaPttRtnCommonDTO() {
		return maPttRtnCommonDTO;
	}

	public void setMaPttRtnCommonDTO(MaPttRtnCommonDTO maPttRtnCommonDTO) {
		this.maPttRtnCommonDTO = maPttRtnCommonDTO;
	}
}
