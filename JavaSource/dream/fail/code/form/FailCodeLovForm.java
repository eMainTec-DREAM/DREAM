package dream.fail.code.form;

import dream.comm.form.MaFinderAcForm;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * 고장코드 LOV- 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="failCodeLovForm"
 */
public class FailCodeLovForm extends MaFinderAcForm
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
