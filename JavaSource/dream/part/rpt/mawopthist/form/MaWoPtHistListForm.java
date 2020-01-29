package dream.part.rpt.mawopthist.form;

import common.struts.BaseForm;
import dream.part.rpt.mawopthist.dto.MaWoPtHistCommonDTO;

/**
 * 부품사용이력 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWoPtHistListForm"
 */
public class MaWoPtHistListForm extends BaseForm
{    
    //===============================================================
    /** 부품사용이력 공통 */
    private MaWoPtHistCommonDTO maWoPtHistCommonDTO = new MaWoPtHistCommonDTO();;

	public MaWoPtHistCommonDTO getMaWoPtHistCommonDTO() 
	{
		return maWoPtHistCommonDTO;
	}

	public void setMaWoPtHistCommonDTO(MaWoPtHistCommonDTO maWoPtHistCommonDTO) 
	{
		this.maWoPtHistCommonDTO = maWoPtHistCommonDTO;
	}

}
