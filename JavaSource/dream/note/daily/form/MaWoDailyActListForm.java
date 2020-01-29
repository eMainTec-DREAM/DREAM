package dream.note.daily.form;

import common.struts.BaseForm;
import dream.note.daily.dto.MaWoDailyActListDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * �����۾� Main Activities - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDailyActListForm"
 */
public class MaWoDailyActListForm extends BaseForm
{    
    //===============================================================
    /** �����۾� ���� */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    /** Main Act */
    private MaWoDailyActListDTO maWoDailyActListDTO = new MaWoDailyActListDTO();
    
	public MaWoDailyCommonDTO getMaWoDailyCommonDTO() {
		return maWoDailyCommonDTO;
	}

	public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO) {
		this.maWoDailyCommonDTO = maWoDailyCommonDTO;
	}

	public MaWoDailyActListDTO getMaWoDailyActListDTO() {
		return maWoDailyActListDTO;
	}

	public void setMaWoDailyActListDTO(MaWoDailyActListDTO maWoDailyActListDTO) {
		this.maWoDailyActListDTO = maWoDailyActListDTO;
	}
	
}
