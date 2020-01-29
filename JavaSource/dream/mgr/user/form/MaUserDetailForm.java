package dream.mgr.user.form;

import common.struts.BaseForm;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserDetailDTO;

/**
 * �����- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maUserDetailForm"
 */
public class MaUserDetailForm extends BaseForm
{
    //========================================================================
    /** ����� ���� */ 
    private MaUserCommonDTO maUserCommonDTO = new MaUserCommonDTO();
    //========================================================================
    /** ����� �� */
    private MaUserDetailDTO maUserDetailDTO = new MaUserDetailDTO();

	public MaUserCommonDTO getMaUserCommonDTO() 
	{
		return maUserCommonDTO;
	}

	public void setMaUserCommonDTO(MaUserCommonDTO maUserCommonDTO) 
	{
		this.maUserCommonDTO = maUserCommonDTO;
	}

	public MaUserDetailDTO getMaUserDetailDTO() 
	{
		return maUserDetailDTO;
	}

	public void setMaUserDetailDTO(MaUserDetailDTO maUserDetailDTO) 
	{
		this.maUserDetailDTO = maUserDetailDTO;
	}

}
