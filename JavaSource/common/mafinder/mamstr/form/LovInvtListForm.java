package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovInvtListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * ���� ��� Form
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="lovInvtListForm"
 */
public class LovInvtListForm extends MaFinderAcForm
{
    /** ���� ��� �˾� DTO */
    private LovInvtListDTO lovInvtListDTO = new LovInvtListDTO();

	public LovInvtListDTO getLovInvtListDTO() {
		return lovInvtListDTO;
	}

	public void setLovInvtListDTO(LovInvtListDTO lovInvtListDTO) {
		this.lovInvtListDTO = lovInvtListDTO;
	}
}
