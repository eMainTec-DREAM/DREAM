package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * ��ǰ������ �԰��̷� - ��� form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrRecStatListForm"
 */
public class MaPtMstrRecStatListForm extends BaseForm
{    
    //===============================================================
    /** ��ǰ������ ���� */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();

    public MaPtMstrCommonDTO getMaPtMstrCommonDTO()
    {
        return maPtMstrCommonDTO;
    }

    public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO)
    {
        this.maPtMstrCommonDTO = maPtMstrCommonDTO;
    }
    
}
