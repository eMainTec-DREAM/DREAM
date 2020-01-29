package dream.consult.comp.tracelog.form;

import common.struts.BaseForm;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
/**
 * Screen Trace - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="consultCompTracelogListForm"
 * */

public class ConsultCompTracelogListForm extends BaseForm{
	
	private ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO = new ConsultCompTracelogCommonDTO();

	public ConsultCompTracelogCommonDTO getConsultCompTracelogCommonDTO() {
		return consultCompTracelogCommonDTO;
	}

	public void setConsultCompTracelogCommonDTO(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO) {
		this.consultCompTracelogCommonDTO = consultCompTracelogCommonDTO;
	}
	
}
