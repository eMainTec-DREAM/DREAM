package dream.fail.code.form;

import common.struts.BaseForm;
import dream.fail.code.dto.MaFailureCommonDTO;
import dream.fail.code.dto.MaFailureDetailDTO;

/**
 * �����ڵ�- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maFailureDetailForm"
 */
public class MaFailureDetailForm extends BaseForm
{
    //========================================================================
    /** �����ڵ� ���� */ 
    private MaFailureCommonDTO maFailureCommonDTO = new MaFailureCommonDTO();
    //========================================================================
    /** �����ڵ� �� */
    private MaFailureDetailDTO maFailureDetailDTO = new MaFailureDetailDTO();
    
	public MaFailureCommonDTO getMaFailureCommonDTO() 
	{
		return maFailureCommonDTO;
	}

	public void setMaFailureCommonDTO(MaFailureCommonDTO maFailureCommonDTO) 
	{
		this.maFailureCommonDTO = maFailureCommonDTO;
	}

	public MaFailureDetailDTO getMaFailureDetailDTO() 
	{
		return maFailureDetailDTO;
	}

	public void setMaFailureDetailDTO(MaFailureDetailDTO maFailureDetailDTO) 
	{
		this.maFailureDetailDTO = maFailureDetailDTO;
	}

}
