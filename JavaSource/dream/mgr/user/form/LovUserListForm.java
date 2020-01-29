package dream.mgr.user.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.user.dto.LovUserListDTO;

/**
 * ����� �˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovUserListForm"
 */
public class LovUserListForm extends MaFinderAcForm
{
    /** �˾� DTO */
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
