package dream.mgr.usrcd.form;

import common.struts.BaseForm;
import dream.mgr.usrcd.dto.MaCdUsrCdDetailDTO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * ������ �����ڵ� 
 * @author  
 * @version $Id:  $
 * @since   1.0
 *
 * @struts.form name="maCdUsrCdDetailForm"
 */
public class MaCdUsrCdDetailForm extends BaseForm
{    
    /** ���� DTO */
    private MaCdUsrCommonDTO maCdUsrCommonDTO = new MaCdUsrCommonDTO();
   
    /** ������ �����ڵ�  */
    private MaCdUsrCdDetailDTO maCdUsrCdDetailDTO = new MaCdUsrCdDetailDTO(); 
    
	public MaCdUsrCommonDTO getMaCdUsrCommonDTO() 
	{
		return maCdUsrCommonDTO;
	}
	public void setMaCdUsrCommonDTO(MaCdUsrCommonDTO maCdUsrCommonDTO) 
	{
		this.maCdUsrCommonDTO = maCdUsrCommonDTO;
	}
	public MaCdUsrCdDetailDTO getMaCdUsrCdDetailDTO() 
	{
		return maCdUsrCdDetailDTO;
	}
	public void setMaCdUsrCdDetailDTO(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO) 
	{
		this.maCdUsrCdDetailDTO = maCdUsrCdDetailDTO;
	}

}
