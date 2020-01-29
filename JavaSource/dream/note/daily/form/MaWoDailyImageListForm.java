package dream.note.daily.form;

import common.struts.BaseForm;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyImageListDTO;

/**
 * �����۾����� ���� FORM
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="maWoDailyImageListForm"
 */
public class MaWoDailyImageListForm extends BaseForm
{    
    //===============================================================
    /** �����۾����� ���� */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    /**  �����۾����� ���� ���  */
    private MaWoDailyImageListDTO maWoDailyImageListDTO = new MaWoDailyImageListDTO();
    
	public MaWoDailyCommonDTO getMaWoDailyCommonDTO() {
		return maWoDailyCommonDTO;
	}
	public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO) {
		this.maWoDailyCommonDTO = maWoDailyCommonDTO;
	}
	public MaWoDailyImageListDTO getMaWoDailyImageListDTO() {
		return maWoDailyImageListDTO;
	}
	public void setMaWoDailyImageListDTO(MaWoDailyImageListDTO maWoDailyImageListDTO) {
		this.maWoDailyImageListDTO = maWoDailyImageListDTO;
	}

}
