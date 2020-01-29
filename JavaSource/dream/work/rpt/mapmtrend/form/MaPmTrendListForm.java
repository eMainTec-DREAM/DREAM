package dream.work.rpt.mapmtrend.form;

import common.struts.BaseForm;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendDetailDTO;

/**
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPmTrendListForm"
 */
public class MaPmTrendListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
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
