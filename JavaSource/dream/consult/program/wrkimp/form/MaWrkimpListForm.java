package dream.consult.program.wrkimp.form;

import common.struts.BaseForm;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;

/**
 * ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWrkimpListForm"
 */
public class MaWrkimpListForm extends BaseForm
{    
    //============================================================
    /** ���� */
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
