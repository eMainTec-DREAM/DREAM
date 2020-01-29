package dream.mgr.usrcd.form;

import common.struts.BaseForm;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;
/**
 * 사용자코드관리 action form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maCdUsrDetailForm"
 */
public class MaCdUsrDetailForm extends BaseForm
{
    /** 공통 DTO */
    private MaCdUsrCommonDTO maCdUsrCommonDTO = new MaCdUsrCommonDTO();
    
    /** 사용자코드 Master DTO */
    private MaCdUsrDetailDTO maCdUsrDetailDTO = new MaCdUsrDetailDTO();
    
    public MaCdUsrCommonDTO getMaCdUsrCommonDTO() {
		return maCdUsrCommonDTO;
	}

	public void setMaCdUsrCommonDTO(MaCdUsrCommonDTO maCdUsrCommonDTO) {
		this.maCdUsrCommonDTO = maCdUsrCommonDTO;
	}

	public MaCdUsrDetailDTO getMaCdUsrDetailDTO()
    {
        return maCdUsrDetailDTO;
    }

    public void setMaCdUsrDetailDTO(MaCdUsrDetailDTO maCdUsrDetailDTO)
    {
        this.maCdUsrDetailDTO = maCdUsrDetailDTO;
    }   
}
