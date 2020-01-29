package dream.asset.loc.run.form;

import common.struts.BaseForm;
import dream.asset.loc.run.dto.MaLineRunPlanDTO;

/**
 * 라인가동계획 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maLineRunPlanForm"
 */
public class MaLineRunPlanForm extends BaseForm
{    
    //===============================================================
    /** 라인가동계획 공통 */
    private MaLineRunPlanDTO maLineRunPlanDTO = new MaLineRunPlanDTO();;

	public MaLineRunPlanDTO getMaLineRunPlanDTO() 
	{
		return maLineRunPlanDTO;
	}

	public void setMaLineRunPlanDTO(MaLineRunPlanDTO maLineRunPlanDTO) 
	{
		this.maLineRunPlanDTO = maLineRunPlanDTO;
	}
}
