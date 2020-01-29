package dream.org.rpt.craft.work.time.form;

import common.struts.BaseForm;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;

/**
 * 작업자 일별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgRptCraftWorkTimeDailyForm"
 */
public class OrgRptCraftWorkTimeDailyForm extends BaseForm
{    
    //===============================================================
    private OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO = new OrgRptCraftWorkTimeDailyDTO();
    
    public OrgRptCraftWorkTimeDailyDTO getOrgRptCraftWorkTimeDailyDTO()
    {
        return orgRptCraftWorkTimeDailyDTO;
    }

    public void setOrgRptCraftWorkTimeDailyDTO(
            OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO)
    {
        this.orgRptCraftWorkTimeDailyDTO = orgRptCraftWorkTimeDailyDTO;
    }
	
}
