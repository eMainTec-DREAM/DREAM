package dream.mgr.user.form;

import common.struts.BaseForm;
import dream.mgr.user.dto.MaUserCommonDTO;

/**
 * ����� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maUserListForm"
 */
public class MaUserListForm extends BaseForm
{    
    //===============================================================
    /** ����� ���� */
    private MaUserCommonDTO maUserCommonDTO = new MaUserCommonDTO();
    
    public MaUserCommonDTO getMaUserCommonDTO() 
	{
		return maUserCommonDTO;
	}

	public void setMaUserCommonDTO(MaUserCommonDTO maUserCommonDTO) 
	{
		this.maUserCommonDTO = maUserCommonDTO;
	}
}
