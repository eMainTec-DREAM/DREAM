package dream.tool.rpt.renthist.form;

import common.struts.BaseForm;
import dream.tool.rpt.renthist.dto.MaPttRentCommonDTO;

/**
 * ���ⱸ�뿩���� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttRentListForm"
 */
public class MaPttRentListForm extends BaseForm
{    
    //===============================================================
    /** ���ⱸ�뿩���� ���� */
    private MaPttRentCommonDTO maPttRentCommonDTO = new MaPttRentCommonDTO();
    
	public MaPttRentCommonDTO getMaPttRentCommonDTO() {
		return maPttRentCommonDTO;
	}

	public void setMaPttRentCommonDTO(MaPttRentCommonDTO maPttRentCommonDTO) {
		this.maPttRentCommonDTO = maPttRentCommonDTO;
	}
}
