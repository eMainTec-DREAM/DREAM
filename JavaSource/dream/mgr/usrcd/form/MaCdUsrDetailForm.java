package dream.mgr.usrcd.form;

import common.struts.BaseForm;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;
/**
 * ������ڵ���� action form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maCdUsrDetailForm"
 */
public class MaCdUsrDetailForm extends BaseForm
{
    /** ���� DTO */
    private MaCdUsrCommonDTO maCdUsrCommonDTO = new MaCdUsrCommonDTO();
    
    /** ������ڵ� Master DTO */
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
