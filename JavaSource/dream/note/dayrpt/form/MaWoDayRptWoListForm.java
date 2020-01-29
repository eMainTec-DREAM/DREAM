package dream.note.dayrpt.form;

import common.struts.BaseForm;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 * �������� - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDayRptWoListForm"
 */
public class MaWoDayRptWoListForm extends BaseForm
{    
    //===============================================================
    /** �������� ���� */
    private MaWoDayRptCommonDTO maWoDayRptCommonDTO = new MaWoDayRptCommonDTO();
    private MaWoDayRptDetailDTO maWoDayRptDetailDTO = new MaWoDayRptDetailDTO();
    
	public MaWoDayRptCommonDTO getMaWoDayRptCommonDTO() {
		return maWoDayRptCommonDTO;
	}

	public void setMaWoDayRptCommonDTO(MaWoDayRptCommonDTO maWoDayRptCommonDTO) {
		this.maWoDayRptCommonDTO = maWoDayRptCommonDTO;
	}

	public MaWoDayRptDetailDTO getMaWoDayRptDetailDTO() {
		return maWoDayRptDetailDTO;
	}

	public void setMaWoDayRptDetailDTO(MaWoDayRptDetailDTO maWoDayRptDetailDTO) {
		this.maWoDayRptDetailDTO = maWoDayRptDetailDTO;
	}
	
}
