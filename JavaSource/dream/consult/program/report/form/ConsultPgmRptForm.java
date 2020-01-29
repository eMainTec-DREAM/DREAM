package dream.consult.program.report.form;

import common.struts.BaseForm;
import dream.consult.program.report.dto.ConsultPgmRptDTO;

/**
 * Report List - ��� form 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultPgmRptForm"
 */
public class ConsultPgmRptForm extends BaseForm
{    
    //=======================================================================
    /** Report List ���� */
    private ConsultPgmRptDTO consultPgmRptDTO = new ConsultPgmRptDTO();
    //=======================================================================

	public ConsultPgmRptDTO getConsultPgmRptDTO() {
		return consultPgmRptDTO;
	}

	public void setConsultPgmRptDTO(ConsultPgmRptDTO consultPgmRptDTO) {
		this.consultPgmRptDTO = consultPgmRptDTO;
	}
}
