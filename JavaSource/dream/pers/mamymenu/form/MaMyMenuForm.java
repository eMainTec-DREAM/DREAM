package dream.pers.mamymenu.form;

import common.struts.BaseForm;
import dream.pers.mamymenu.dto.MaMyMenuDTO;

/**
 * ����ڸ޴� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maMyMenuForm"
 */
public class MaMyMenuForm extends BaseForm
{    
    //===============================================================
    /** ���ѱ׷� ���� */
    private MaMyMenuDTO maMyMenuDTO = new MaMyMenuDTO();
    
    public MaMyMenuDTO getMaMyMenuDTO() 
	{
		return maMyMenuDTO;
	}

	public void setMaMyMenuDTO(MaMyMenuDTO maMyMenuDTO) 
	{
		this.maMyMenuDTO = maMyMenuDTO;
	}
}
