package dream.note.daily.form;

import common.struts.BaseForm;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyImageListDTO;

/**
 * 일일작업승인 사진 FORM
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="maWoDailyImageListForm"
 */
public class MaWoDailyImageListForm extends BaseForm
{    
    //===============================================================
    /** 일일작업승인 공통 */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    /**  일일작업승인 사진 목록  */
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
