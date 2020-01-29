package dream.mgr.lang.form;

import common.struts.BaseForm;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.dto.MaResDetailDTO;

/**
 * ���- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maResDetailForm"
 */
public class MaResDetailForm extends BaseForm
{
    //========================================================================
    /** ��� ���� */ 
    private MaResCommonDTO maResCommonDTO = new MaResCommonDTO();
    //========================================================================
    /** ��� �� */
    private MaResDetailDTO maResDetailDTO = new MaResDetailDTO();

	public MaResCommonDTO getMaResCommonDTO() 
	{
		return maResCommonDTO;
	}

	public void setMaResCommonDTO(MaResCommonDTO maResCommonDTO) 
	{
		this.maResCommonDTO = maResCommonDTO;
	}

	public MaResDetailDTO getMaResDetailDTO() 
	{
		return maResDetailDTO;
	}

	public void setMaResDetailDTO(MaResDetailDTO maResDetailDTO) 
	{
		this.maResDetailDTO = maResDetailDTO;
	}
}
