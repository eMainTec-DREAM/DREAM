package dream.fail.library.form;

import common.struts.BaseForm;
import dream.fail.library.dto.FailLibraryCommonDTO;

/**
 * ����Library - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="failLibraryListForm"
 */
public class FailLibraryListForm extends BaseForm
{    
    //===============================================================
    /** ���� ���� */
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
