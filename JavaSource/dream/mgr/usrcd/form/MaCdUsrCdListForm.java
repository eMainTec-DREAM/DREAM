package dream.mgr.usrcd.form;

import common.struts.BaseForm;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
/**
 * 사용자코드관리 상세 List action form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maCdUsrCdListForm"
 */
public class MaCdUsrCdListForm extends BaseForm
{ 
    /** 사용자코드관리 DTO */
    private MaCdUsrCommonDTO maCdUsrCommonDTO = new MaCdUsrCommonDTO();

	public MaCdUsrCommonDTO getMaCdUsrCommonDTO()
	{
		return maCdUsrCommonDTO;
	}

	public void setMaCdUsrCommonDTO(MaCdUsrCommonDTO maCdUsrCommonDTO) 
	{
		this.maCdUsrCommonDTO = maCdUsrCommonDTO;
	}

}
