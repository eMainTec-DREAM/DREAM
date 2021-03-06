package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 부품마스터 출고이력 - 목록 form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrIssStatListForm"
 */
public class MaPtMstrIssStatListForm extends BaseForm
{    
    //===============================================================
    /** 부품마스터 공통 */
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
