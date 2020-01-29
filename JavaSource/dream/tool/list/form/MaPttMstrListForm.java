package dream.tool.list.form;

import common.struts.BaseForm;
import dream.tool.list.dto.MaPttMstrCommonDTO;

/**
 * 보전자재분류(마스터) - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPttMstrListForm"
 */
public class MaPttMstrListForm extends BaseForm
{    
    //===============================================================
    /** 보전자재분류(마스터) 공통 */
    private MaPttMstrCommonDTO maPttMstrCommonDTO = new MaPttMstrCommonDTO();;

	public MaPttMstrCommonDTO getMaPttMstrCommonDTO() 
	{
		return maPttMstrCommonDTO;
	}

	public void setMaPttMstrCommonDTO(MaPttMstrCommonDTO maPttMstrCommonDTO) 
	{
		this.maPttMstrCommonDTO = maPttMstrCommonDTO;
	}

}
