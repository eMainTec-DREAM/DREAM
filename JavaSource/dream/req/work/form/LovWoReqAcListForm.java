package dream.req.work.form;

import dream.comm.form.MaFinderAcForm;
import dream.req.work.dto.LovWoReqAcListDTO;

/**
 * �۾���û Form
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="lovWoReqAcListForm"
 */
public class LovWoReqAcListForm extends MaFinderAcForm
{
    /** ����˾� DTO */
    private LovWoReqAcListDTO lovWoReqAcListDTO = new LovWoReqAcListDTO();

	public LovWoReqAcListDTO getLovWoReqAcListDTO() {
		return lovWoReqAcListDTO;
	}

	public void setLovWoReqAcListDTO(LovWoReqAcListDTO lovWoReqAcListDTO) {
		this.lovWoReqAcListDTO = lovWoReqAcListDTO;
	}
}
