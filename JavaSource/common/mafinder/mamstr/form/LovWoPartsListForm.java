package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovWoPartsListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * �۾���ǰ Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWoPartsListForm"
 */
public class LovWoPartsListForm extends MaFinderAcForm
{
    /** ����˾� DTO */
    private LovWoPartsListDTO lovWoPartsListDTO = new LovWoPartsListDTO();

	public LovWoPartsListDTO getLovWoPartsListDTO() {
		return lovWoPartsListDTO;
	}

	public void setLovWoPartsListDTO(LovWoPartsListDTO lovWoPartsListDTO) {
		this.lovWoPartsListDTO = lovWoPartsListDTO;
	}
}
