package dream.mgr.lang.form;

import common.struts.BaseForm;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.dto.MaResDetailDTO;

/**
 * 언어- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maResDetailForm"
 */
public class MaResDetailForm extends BaseForm
{
    //========================================================================
    /** 사원 공통 */ 
    private MaResCommonDTO maResCommonDTO = new MaResCommonDTO();
    //========================================================================
    /** 사원 상세 */
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
