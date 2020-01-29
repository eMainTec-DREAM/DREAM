package dream.consult.program.dashboard.form;

import common.struts.BaseForm;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardDetailDTO;

/**
 * ��ú��� Contents- �� Form
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultPgmDashboardDetailForm"
 */
public class ConsultPgmDashboardDetailForm extends BaseForm
{
    //========================================================================
    /** ��ú��� Contents ���� */
    private ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO = new ConsultPgmDashboardCommonDTO();
    //========================================================================
    /** ��ú��� Contents �� */
    private ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO = new ConsultPgmDashboardDetailDTO();


	public ConsultPgmDashboardCommonDTO getConsultPgmDashboardCommonDTO() {
		return consultPgmDashboardCommonDTO;
	}

	public void setConsultPgmDashboardCommonDTO(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO) {
		this.consultPgmDashboardCommonDTO = consultPgmDashboardCommonDTO;
	}

	public ConsultPgmDashboardDetailDTO getConsultPgmDashboardDetailDTO() {
		return consultPgmDashboardDetailDTO;
	}

	public void setConsultPgmDashboardDetailDTO(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO) {
		this.consultPgmDashboardDetailDTO = consultPgmDashboardDetailDTO;
	}

}
