package dream.consult.comp.intf.form;

import common.struts.BaseForm;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
/**
 * Interface Page - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="consultCompIntfListForm"
 * */

public class ConsultCompIntfListForm extends BaseForm{
	
	private ConsultCompIntfCommonDTO consultCompIntfCommonDTO = new ConsultCompIntfCommonDTO();

	public ConsultCompIntfCommonDTO getConsultCompIntfCommonDTO() {
		return consultCompIntfCommonDTO;
	}

	public void setConsultCompIntfCommonDTO(ConsultCompIntfCommonDTO consultCompIntfCommonDTO) {
		this.consultCompIntfCommonDTO = consultCompIntfCommonDTO;
	}
	
}
