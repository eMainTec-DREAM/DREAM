package dream.fail.code.form;

import common.struts.BaseForm;
import dream.fail.code.dto.MaFailureCommonDTO;
import dream.fail.code.dto.MaFailureDetailDTO;

/**
 * 고장코드- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maFailureDetailForm"
 */
public class MaFailureDetailForm extends BaseForm
{
    //========================================================================
    /** 고장코드 공통 */ 
    private MaFailureCommonDTO maFailureCommonDTO = new MaFailureCommonDTO();
    //========================================================================
    /** 고장코드 상세 */
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
