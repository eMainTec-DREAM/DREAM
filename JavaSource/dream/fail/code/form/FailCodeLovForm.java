package dream.fail.code.form;

import dream.comm.form.MaFinderAcForm;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * �����ڵ� LOV- ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="failCodeLovForm"
 */
public class FailCodeLovForm extends MaFinderAcForm
{    
    //===============================================================
    /** �����ڵ� ���� */
    private MaFailureCommonDTO maFailureCommonDTO = new MaFailureCommonDTO();;

	public MaFailureCommonDTO getMaFailureCommonDTO() 
	{
		return maFailureCommonDTO;
	}

	public void setMaFailureCommonDTO(MaFailureCommonDTO maFailureCommonDTO) 
	{
		this.maFailureCommonDTO = maFailureCommonDTO;
	}

}
