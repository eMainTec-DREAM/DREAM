package dream.note.daily.form;

import common.struts.BaseForm;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * �����۾� - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDailyListForm"
 */
public class MaWoDailyListForm extends BaseForm
{    
    //===============================================================
    /** �����۾� ���� */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    
	public MaWoDailyCommonDTO getMaWoDailyCommonDTO() {
		return maWoDailyCommonDTO;
	}

	public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO) {
		this.maWoDailyCommonDTO = maWoDailyCommonDTO;
	}
}
