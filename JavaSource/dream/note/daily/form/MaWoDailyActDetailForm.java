package dream.note.daily.form;

import common.struts.BaseForm;
import dream.note.daily.dto.MaWoDailyActDetailDTO;
import dream.note.daily.dto.MaWoDailyActListDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업-main activities
 * @author  kim2107
 * @version $Id: MaWoDailyActDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoDailyActDetailForm"
 */
public class MaWoDailyActDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
	/** 작업결과 작업자 목록 DTO  */
    private MaWoDailyActListDTO maWoDailyActListDTO = new MaWoDailyActListDTO();
	/** 작업결과 작업자 상세 DTO  */
    private MaWoDailyActDetailDTO maWoDailyActDetailDTO = new MaWoDailyActDetailDTO();
    
    
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
	public MaWoDailyActDetailDTO getMaWoDailyActDetailDTO() {
		return maWoDailyActDetailDTO;
	}
	public void setMaWoDailyActDetailDTO(MaWoDailyActDetailDTO maWoDailyActDetailDTO) {
		this.maWoDailyActDetailDTO = maWoDailyActDetailDTO;
	}
}
