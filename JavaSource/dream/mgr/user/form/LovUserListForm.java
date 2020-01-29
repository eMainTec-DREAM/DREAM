package dream.mgr.user.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.user.dto.LovUserListDTO;

/**
 * »ç¿ëÀÚ ÆË¾÷ Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovUserListForm"
 */
public class LovUserListForm extends MaFinderAcForm
{
    /** ÆË¾÷ DTO */
    private LovUserListDTO lovUserListDTO = new LovUserListDTO();

	public LovUserListDTO getLovUserListDTO() 
	{
		return lovUserListDTO;
	}

	public void setLovUserListDTO(LovUserListDTO lovUserListDTO) 
	{
		this.lovUserListDTO = lovUserListDTO;
	}
}
