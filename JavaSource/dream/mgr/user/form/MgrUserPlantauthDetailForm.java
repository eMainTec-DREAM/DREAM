package dream.mgr.user.form;

import common.struts.BaseForm;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserDetailDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;

/**
 * �����- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrUserPlantauthDetailForm"
 */
public class MgrUserPlantauthDetailForm extends BaseForm
{
    //========================================================================
    /** ����� ���� */ 
    private MaUserCommonDTO maUserCommonDTO = new MaUserCommonDTO();
    //========================================================================
    /** ����� �� */
    private MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = new MgrUserPlantauthDetailDTO();

	public MaUserCommonDTO getMaUserCommonDTO() 
	{
		return maUserCommonDTO;
	}

	public void setMaUserCommonDTO(MaUserCommonDTO maUserCommonDTO) 
	{
		this.maUserCommonDTO = maUserCommonDTO;
	}

    public MgrUserPlantauthDetailDTO getMgrUserPlantauthDetailDTO()
    {
        return mgrUserPlantauthDetailDTO;
    }

    public void setMgrUserPlantauthDetailDTO(
            MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO)
    {
        this.mgrUserPlantauthDetailDTO = mgrUserPlantauthDetailDTO;
    }
}
