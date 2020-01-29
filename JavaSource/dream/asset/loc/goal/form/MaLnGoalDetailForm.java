package dream.asset.loc.goal.form;

import common.struts.BaseForm;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;
import dream.asset.loc.goal.dto.MaLnGoalDetailDTO;

/**
 * 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maLnGoalDetailForm"
 */
public class MaLnGoalDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private MaLnGoalCommonDTO maLnGoalCommonDTO = new MaLnGoalCommonDTO();
    //========================================================================
    /** 상세 */
    private MaLnGoalDetailDTO maLnGoalDetailDTO = new MaLnGoalDetailDTO();


    public MaLnGoalCommonDTO getMaLnGoalCommonDTO() {
		return maLnGoalCommonDTO;
	}

	public void setMaLnGoalCommonDTO(MaLnGoalCommonDTO maLnGoalCommonDTO) 
	{
		this.maLnGoalCommonDTO = maLnGoalCommonDTO;
	}

	public MaLnGoalDetailDTO getMaLnGoalDetailDTO() {
		return maLnGoalDetailDTO;
	}

	public void setMaLnGoalDetailDTO(MaLnGoalDetailDTO maLnGoalDetailDTO) 
	{
		this.maLnGoalDetailDTO = maLnGoalDetailDTO;
	}
}
