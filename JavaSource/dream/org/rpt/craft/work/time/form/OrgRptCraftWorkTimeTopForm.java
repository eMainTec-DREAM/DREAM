package dream.org.rpt.craft.work.time.form;

import common.struts.BaseForm;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeMonthlyDTO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;

/**
 * 작업자 작업시간 Top
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgRptCraftWorkTimeTopForm"
 */
public class OrgRptCraftWorkTimeTopForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO = new OrgRptCraftWorkTimeTopCommonDTO();

    private OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO = new OrgRptCraftWorkTimeMonthlyDTO();
    private OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO = new OrgRptCraftWorkTimeDailyDTO();
    
	public OrgRptCraftWorkTimeDailyDTO getOrgRptCraftWorkTimeDailyDTO() {
		return orgRptCraftWorkTimeDailyDTO;
	}

	public void setOrgRptCraftWorkTimeDailyDTO(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO) {
		this.orgRptCraftWorkTimeDailyDTO = orgRptCraftWorkTimeDailyDTO;
	}

	public OrgRptCraftWorkTimeMonthlyDTO getOrgRptCraftWorkTimeMonthlyDTO() {
		return orgRptCraftWorkTimeMonthlyDTO;
	}

	public void setOrgRptCraftWorkTimeMonthlyDTO(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO) {
		this.orgRptCraftWorkTimeMonthlyDTO = orgRptCraftWorkTimeMonthlyDTO;
	}

	public OrgRptCraftWorkTimeTopCommonDTO getOrgRptCraftWorkTimeTopCommonDTO() {
		return orgRptCraftWorkTimeTopCommonDTO;
	}

	public void setOrgRptCraftWorkTimeTopCommonDTO(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO) {
		this.orgRptCraftWorkTimeTopCommonDTO = orgRptCraftWorkTimeTopCommonDTO;
	}
	
}
