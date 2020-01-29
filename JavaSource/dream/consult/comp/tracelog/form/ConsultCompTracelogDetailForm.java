package dream.consult.comp.tracelog.form;

import common.struts.BaseForm;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogDetailDTO;

/**
 * Screen Trace - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="consultCompTracelogDetailForm"
 */
public class ConsultCompTracelogDetailForm extends BaseForm
{
	private ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO = new ConsultCompTracelogCommonDTO();
	private ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO = new ConsultCompTracelogDetailDTO();
    
	public ConsultCompTracelogCommonDTO getConsultCompTracelogCommonDTO() {
		return consultCompTracelogCommonDTO;
	}
	public void setConsultCompTracelogCommonDTO(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO) {
		this.consultCompTracelogCommonDTO = consultCompTracelogCommonDTO;
	}
	public ConsultCompTracelogDetailDTO getConsultCompTracelogDetailDTO() {
		return consultCompTracelogDetailDTO;
	}
	public void setConsultCompTracelogDetailDTO(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO) {
		this.consultCompTracelogDetailDTO = consultCompTracelogDetailDTO;
	}
}
