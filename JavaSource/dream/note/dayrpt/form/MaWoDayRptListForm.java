package dream.note.dayrpt.form;

import common.struts.BaseForm;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;

/**
 * �������� - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDayRptListForm"
 */
public class MaWoDayRptListForm extends BaseForm
{    
    //===============================================================
    /** �������� ���� */
    private MaWoDayRptCommonDTO maWoDayRptCommonDTO = new MaWoDayRptCommonDTO();
    
	public MaWoDayRptCommonDTO getMaWoDayRptCommonDTO() {
		return maWoDayRptCommonDTO;
	}

	public void setMaWoDayRptCommonDTO(MaWoDayRptCommonDTO maWoDayRptCommonDTO) {
		this.maWoDayRptCommonDTO = maWoDayRptCommonDTO;
	}
}
