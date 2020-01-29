package dream.work.rpt.mapmtrend.form;

import common.struts.BaseForm;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendDetailDTO;

/**
 * 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPmTrendDetailForm"
 */
public class MaPmTrendDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPmTrendCommonDTO maPmTrendCommonDTO = new MaPmTrendCommonDTO();
    
    private MaPmTrendDetailDTO maPmTrendDetailDTO = new MaPmTrendDetailDTO();
    
    public MaPmTrendCommonDTO getMaPmTrendCommonDTO()
    {
        return maPmTrendCommonDTO;
    }

    public void setMaPmTrendCommonDTO(
            MaPmTrendCommonDTO maPmTrendCommonDTO)
    {
        this.maPmTrendCommonDTO = maPmTrendCommonDTO;
    }
    
    public MaPmTrendDetailDTO getMaPmTrendDetailDTO()
    {
        return maPmTrendDetailDTO;
    }

    public void setMaPmTrendDetailDTO(
            MaPmTrendDetailDTO maPmTrendDetailDTO)
    {
        this.maPmTrendDetailDTO = maPmTrendDetailDTO;
    }
	
}
