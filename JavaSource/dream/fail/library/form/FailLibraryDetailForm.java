package dream.fail.library.form;

import common.struts.BaseForm;
import dream.fail.library.dto.FailLibraryCommonDTO;
import dream.fail.library.dto.FailLibraryDetailDTO;

/**
 * 고장library- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="failLibraryDetailForm"
 */
public class FailLibraryDetailForm extends BaseForm
{
    //========================================================================
    /** 고장 공통 */ 
    private FailLibraryCommonDTO failLibraryCommonDTO = new FailLibraryCommonDTO();
    //========================================================================
    /** 고장 상세 */
    private FailLibraryDetailDTO failLibraryDetailDTO = new FailLibraryDetailDTO();
    
	public FailLibraryCommonDTO getFailLibraryCommonDTO() 
	{
		return failLibraryCommonDTO;
	}

	public void setFailLibraryCommonDTO(FailLibraryCommonDTO failLibraryCommonDTO) 
	{
		this.failLibraryCommonDTO = failLibraryCommonDTO;
	}

	public FailLibraryDetailDTO getFailLibraryDetailDTO() 
	{
		return failLibraryDetailDTO;
	}

	public void setFailLibraryDetailDTO(FailLibraryDetailDTO failLibraryDetailDTO) 
	{
		this.failLibraryDetailDTO = failLibraryDetailDTO;
	}

}
