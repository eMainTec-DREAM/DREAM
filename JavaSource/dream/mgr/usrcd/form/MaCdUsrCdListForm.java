package dream.mgr.usrcd.form;

import common.struts.BaseForm;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
/**
 * ������ڵ���� �� List action form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maCdUsrCdListForm"
 */
public class MaCdUsrCdListForm extends BaseForm
{ 
    /** ������ڵ���� DTO */
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
