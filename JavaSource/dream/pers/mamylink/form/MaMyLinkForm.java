package dream.pers.mamylink.form;

import common.struts.BaseForm;
import dream.pers.mamylink.dto.MaMyLinkDTO;

/**
 * ���ѱ׷� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maMyLinkForm"
 */
public class MaMyLinkForm extends BaseForm
{    
    //===============================================================
    /** ���ѱ׷� ���� */
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
