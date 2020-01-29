package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovInvtListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 투자 목록 Form
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="lovInvtListForm"
 */
public class LovInvtListForm extends MaFinderAcForm
{
    /** 투자 목록 팝업 DTO */
    private LovInvtListDTO lovInvtListDTO = new LovInvtListDTO();

	public LovInvtListDTO getLovInvtListDTO() {
		return lovInvtListDTO;
	}

	public void setLovInvtListDTO(LovInvtListDTO lovInvtListDTO) {
		this.lovInvtListDTO = lovInvtListDTO;
	}
}
