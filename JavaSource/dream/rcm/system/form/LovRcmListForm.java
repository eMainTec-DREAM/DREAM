package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.rcm.system.dto.LovRcmListDTO;

/**
 * �ڻ��˾� Form
 * @author  kim21017
 * @version $Id: LovRcmListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovRcmListForm"
 */
public class LovRcmListForm extends BaseForm
{
    /** �ڻ��˾� DTO */
    private LovRcmListDTO lovRcmListDTO = new LovRcmListDTO();

	public LovRcmListDTO getLovRcmListDTO() {
		return lovRcmListDTO;
	}

	public void setLovRcmListDTO(LovRcmListDTO lovRcmListDTO) {
		this.lovRcmListDTO = lovRcmListDTO;
	}
}
