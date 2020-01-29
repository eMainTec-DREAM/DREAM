package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovWoPlanAcListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * �۾� Form
 * @author  kim21017
 * @version $Id: LovWoPlanAcListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovWoPlanAcListForm"
 */
public class LovWoPlanAcListForm extends MaFinderAcForm
{
    /** ����˾� DTO */
    private LovWoPlanAcListDTO lovWoPlanAcListDTO = new LovWoPlanAcListDTO();

	public LovWoPlanAcListDTO getLovWoPlanAcListDTO() {
		return lovWoPlanAcListDTO;
	}

	public void setLovWoPlanAcListDTO(LovWoPlanAcListDTO lovWoPlanAcListDTO) {
		this.lovWoPlanAcListDTO = lovWoPlanAcListDTO;
	}
}
