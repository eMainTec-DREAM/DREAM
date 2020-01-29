package dream.main.form;

import common.struts.BaseForm;
import dream.main.dto.MaDbListDTO;

/**
 *  Dashboard List form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maDbListForm"
 */
public class MaDbListForm extends BaseForm
{    
    //===============================================================
    /**  °øÅë */
    private MaDbListDTO maDbListDTO = new MaDbListDTO();
    
    public MaDbListDTO getMaDbListDTO() 
	{
		return maDbListDTO;
	}

	public void setMaDbListDTO(MaDbListDTO maDbListDTO) 
	{
		this.maDbListDTO = maDbListDTO;
	}
}
