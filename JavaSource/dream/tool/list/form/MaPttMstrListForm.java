package dream.tool.list.form;

import common.struts.BaseForm;
import dream.tool.list.dto.MaPttMstrCommonDTO;

/**
 * ��������з�(������) - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPttMstrListForm"
 */
public class MaPttMstrListForm extends BaseForm
{    
    //===============================================================
    /** ��������з�(������) ���� */
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
