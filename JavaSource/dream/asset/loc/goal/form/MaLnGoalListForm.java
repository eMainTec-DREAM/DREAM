package dream.asset.loc.goal.form;

import common.struts.BaseForm;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;

/**
 * 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maLnGoalListForm"
 */
public class MaLnGoalListForm extends BaseForm
{    
    /** 공통 */
    private MaLnGoalCommonDTO maLnGoalCommonDTO = new MaLnGoalCommonDTO();;

	public MaLnGoalCommonDTO getMaLnGoalCommonDTO() 
	{
		return maLnGoalCommonDTO;
	}

	public void setMaLnGoalCommonDTO(MaLnGoalCommonDTO maLnGoalCommonDTO) 
	{
		this.maLnGoalCommonDTO = maLnGoalCommonDTO;
	}

}
