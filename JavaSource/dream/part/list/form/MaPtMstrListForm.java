package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * ��������з�(������) - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtMstrListForm"
 */
public class MaPtMstrListForm extends BaseForm
{    
    //===============================================================
    /** ��������з�(������) ���� */
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
