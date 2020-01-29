package dream.fail.library.form;

import common.struts.BaseForm;
import dream.fail.library.dto.FailLibraryCommonDTO;

/**
 * 고장Library - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="failLibraryListForm"
 */
public class FailLibraryListForm extends BaseForm
{    
    //===============================================================
    /** 고장 공통 */
    private FailLibraryCommonDTO failLibraryCommonDTO = new FailLibraryCommonDTO();;

	public FailLibraryCommonDTO getFailLibraryCommonDTO() 
	{
		return failLibraryCommonDTO;
	}

	public void setFailLibraryCommonDTO(FailLibraryCommonDTO failLibraryCommonDTO) 
	{
		this.failLibraryCommonDTO = failLibraryCommonDTO;
	}

}
