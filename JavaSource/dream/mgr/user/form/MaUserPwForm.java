package dream.mgr.user.form;

import common.struts.BaseForm;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserPwDTO;

/**
 * ��й�ȣ����
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maUserPwForm"
 */
public class MaUserPwForm extends BaseForm
{    
	//========================================================================
    /** ����� ���� */ 
    private MaUserCommonDTO maUserCommonDTO = new MaUserCommonDTO();
    //===============================================================
    /** ��й�ȣ���� DTO  */
    private MaUserPwDTO maUserPwDTO = new MaUserPwDTO();

    
    public MaUserCommonDTO getMaUserCommonDTO() 
    {
		return maUserCommonDTO;
	}
	public void setMaUserCommonDTO(MaUserCommonDTO maUserCommonDTO) 
	{
		this.maUserCommonDTO = maUserCommonDTO;
	}
	public MaUserPwDTO getMaUserPwDTO() 
    {
		return maUserPwDTO;
	}
	public void setMaUserPwDTO(MaUserPwDTO maUserPwDTO) 
	{
		this.maUserPwDTO = maUserPwDTO;
	}
}
