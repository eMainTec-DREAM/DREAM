package dream.org.rpt.craft.work.time.form;

import common.struts.BaseForm;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeMonthlyDTO;

/**
 * 작업자 월별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgRptCraftWorkTimeMonthlyForm"
 */
public class OrgRptCraftWorkTimeMonthlyForm extends BaseForm
{    
    //===============================================================
    private OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO = new OrgRptCraftWorkTimeMonthlyDTO();
    

	public OrgRptCraftWorkTimeMonthlyDTO getOrgRptCraftWorkTimeMonthlyDTO()
    {
        return orgRptCraftWorkTimeMonthlyDTO;
    }

    public void setOrgRptCraftWorkTimeMonthlyDTO(
            OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO)
    {
        this.orgRptCraftWorkTimeMonthlyDTO = orgRptCraftWorkTimeMonthlyDTO;
    }
	
}
