package dream.pers.mamymenu.form;

import common.struts.BaseForm;
import dream.pers.mamymenu.dto.MaMyMenuDTO;

/**
 * 사용자메뉴 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maMyMenuForm"
 */
public class MaMyMenuForm extends BaseForm
{    
    //===============================================================
    /** 권한그룹 공통 */
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
