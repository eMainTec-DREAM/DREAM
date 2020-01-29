package dream.consult.program.dashboard.form;

import common.struts.BaseForm;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;

/**
 * 대시보드 Contents - 목록 form
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultPgmDashboardListForm"
 */
public class ConsultPgmDashboardListForm extends BaseForm
{
    //===============================================================
    /** 접근터미널  공통 */
    private ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO = new ConsultPgmDashboardCommonDTO();

	public ConsultPgmDashboardCommonDTO getConsultPgmDashboardCommonDTO() {
		return consultPgmDashboardCommonDTO;
	}

	public void setConsultPgmDashboardCommonDTO(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO) {
		this.consultPgmDashboardCommonDTO = consultPgmDashboardCommonDTO;
	}

}
