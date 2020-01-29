package dream.pers.mamylink.form;

import common.struts.BaseForm;
import dream.pers.mamylink.dto.MaMyLinkDTO;

/**
 * 권한그룹 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maMyLinkForm"
 */
public class MaMyLinkForm extends BaseForm
{    
    //===============================================================
    /** 권한그룹 공통 */
    private MaMyLinkDTO maMyLinkDTO = new MaMyLinkDTO();
    
    public MaMyLinkDTO getMaMyLinkDTO() 
	{
		return maMyLinkDTO;
	}

	public void setMaMyLinkDTO(MaMyLinkDTO maMyLinkDTO) 
	{
		this.maMyLinkDTO = maMyLinkDTO;
	}
}
