package dream.consult.program.wrkimp.form;

import common.struts.BaseForm;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;

/**
 * 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWrkimpListForm"
 */
public class MaWrkimpListForm extends BaseForm
{    
    //============================================================
    /** 공통 */
    private MaWrkimpCommonDTO maWrkimpCommonDTO = new MaWrkimpCommonDTO();


    public MaWrkimpCommonDTO getMaWrkimpCommonDTO() 
	{
		return maWrkimpCommonDTO;
	}

	public void setMaWrkimpCommonDTO(MaWrkimpCommonDTO maWrkimpCommonDTO) 
	{
		this.maWrkimpCommonDTO = maWrkimpCommonDTO;
	}
}
