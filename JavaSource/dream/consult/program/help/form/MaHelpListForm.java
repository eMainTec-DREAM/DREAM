package dream.consult.program.help.form;

import common.struts.BaseForm;
import dream.consult.program.help.dto.MaHelpCommonDTO;

/**
 * ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maHelpListForm"
 */
public class MaHelpListForm extends BaseForm
{    
    //============================================================
    /** ���� */
    private MaHelpCommonDTO maHelpCommonDTO = new MaHelpCommonDTO();


    public MaHelpCommonDTO getMaHelpCommonDTO() 
	{
		return maHelpCommonDTO;
	}

	public void setMaHelpCommonDTO(MaHelpCommonDTO maHelpCommonDTO) 
	{
		this.maHelpCommonDTO = maHelpCommonDTO;
	}
}
