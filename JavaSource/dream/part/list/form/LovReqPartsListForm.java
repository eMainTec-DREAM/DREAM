package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.LovReqPartsListDTO;

/**
 * �����˾� Form
 * @author  kim21017
 * @version $Id: LovReqPartsListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovReqPartsListForm"
 */
public class LovReqPartsListForm extends BaseForm
{
    /** �����˾� DTO */
    private LovReqPartsListDTO lovReqPartsListDTO = new LovReqPartsListDTO();

	public LovReqPartsListDTO getLovReqPartsListDTO() {
		return lovReqPartsListDTO;
	}

	public void setLovReqPartsListDTO(LovReqPartsListDTO lovReqPartsListDTO) {
		this.lovReqPartsListDTO = lovReqPartsListDTO;
	}
}
