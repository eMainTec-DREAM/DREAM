package dream.mgr.user.form;

import common.struts.BaseForm;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;

/**
 * 사용자 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrUserPlantauthListForm"
 */
public class MgrUserPlantauthListForm extends BaseForm
{    
    //===============================================================
    /** 사용자 공통 */
    private MaUserCommonDTO maUserCommonDTO = new MaUserCommonDTO();
    /** 사용자 상세 */
    private MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = new MgrUserPlantauthDetailDTO();

    public MgrUserPlantauthDetailDTO getMgrUserPlantauthDetailDTO() {
		return mgrUserPlantauthDetailDTO;
	}

	public void setMgrUserPlantauthDetailDTO(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO) {
		this.mgrUserPlantauthDetailDTO = mgrUserPlantauthDetailDTO;
	}

	public MaUserCommonDTO getMaUserCommonDTO() 
	{
		return maUserCommonDTO;
	}

	public void setMaUserCommonDTO(MaUserCommonDTO maUserCommonDTO) 
	{
		this.maUserCommonDTO = maUserCommonDTO;
	}
}