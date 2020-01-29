package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 보전자재분류(마스터) - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtMstrListForm"
 */
public class MaPtMstrListForm extends BaseForm
{    
    //===============================================================
    /** 보전자재분류(마스터) 공통 */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();;

	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() 
	{
		return maPtMstrCommonDTO;
	}

	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) 
	{
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}

}
