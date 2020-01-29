package dream.part.rpt.mayearptsched.form;

import common.struts.BaseForm;
import dream.part.rpt.mayearptsched.dto.MaPmYearPtSchedCommonDTO;

/**
 * 연간부품사용일정 FORM
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPmYearPtSchedListForm"
 */
public class MaPmYearPtSchedListForm extends BaseForm
{    
    private MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO = new MaPmYearPtSchedCommonDTO();

    public MaPmYearPtSchedCommonDTO getMaPmYearPtSchedCommonDTO()
    {
        return maPmYearPtSchedCommonDTO;
    }

    public void setMaPmYearPtSchedCommonDTO(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO)
    {
        this.maPmYearPtSchedCommonDTO = maPmYearPtSchedCommonDTO;
    }

}
