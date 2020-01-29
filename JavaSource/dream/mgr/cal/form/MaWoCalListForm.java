package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MaWoCalCommonDTO;

/**
 * Working Calendar - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWoCalListForm"
 */
public class MaWoCalListForm extends BaseForm
{    
    //===============================================================
    /** Working Calendar 공통 */
    private MaWoCalCommonDTO maWoCalCommonDTO = new MaWoCalCommonDTO();;

	public MaWoCalCommonDTO getMaWoCalCommonDTO() 
	{
		return maWoCalCommonDTO;
	}

	public void setMaWoCalCommonDTO(MaWoCalCommonDTO maWoCalCommonDTO) 
	{
		this.maWoCalCommonDTO = maWoCalCommonDTO;
	}
}
