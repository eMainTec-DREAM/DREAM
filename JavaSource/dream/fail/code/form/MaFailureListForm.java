package dream.fail.code.form;

import common.struts.BaseForm;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * �����ڵ� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maFailureListForm"
 */
public class MaFailureListForm extends BaseForm
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
