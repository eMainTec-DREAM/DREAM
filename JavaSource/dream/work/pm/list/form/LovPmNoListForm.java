package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovPmNoListDTO;

/**
 * ���������˾� Form
 * @author  kim21017
 * @version $Id: LovPmNoListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovPmNoListForm"
 */
public class LovPmNoListForm extends MaFinderAcForm
{
    /** ���������˾� DTO */
    private LovPmNoListDTO lovPmNoListDTO = new LovPmNoListDTO();

	public LovPmNoListDTO getLovPmNoListDTO() {
		return lovPmNoListDTO;
	}

	public void setLovPmNoListDTO(LovPmNoListDTO lovPmNoListDTO) {
		this.lovPmNoListDTO = lovPmNoListDTO;
	}
}
