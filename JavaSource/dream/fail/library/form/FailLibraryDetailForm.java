package dream.fail.library.form;

import common.struts.BaseForm;
import dream.fail.library.dto.FailLibraryCommonDTO;
import dream.fail.library.dto.FailLibraryDetailDTO;

/**
 * ����library- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="failLibraryDetailForm"
 */
public class FailLibraryDetailForm extends BaseForm
{
    //========================================================================
    /** ���� ���� */ 
    private FailLibraryCommonDTO failLibraryCommonDTO = new FailLibraryCommonDTO();
    //========================================================================
    /** ���� �� */
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
