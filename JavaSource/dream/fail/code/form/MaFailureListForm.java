package dream.fail.code.form;

import common.struts.BaseForm;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * 고장코드 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maFailureListForm"
 */
public class MaFailureListForm extends BaseForm
{    
    //===============================================================
    /** 고장코드 공통 */
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
