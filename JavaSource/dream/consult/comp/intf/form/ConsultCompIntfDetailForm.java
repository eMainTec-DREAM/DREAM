package dream.consult.comp.intf.form;

import common.struts.BaseForm;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfDetailDTO;

/**
 * Interface Page - Detail Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="consultCompIntfDetailForm"
 */
public class ConsultCompIntfDetailForm extends BaseForm
{
	private ConsultCompIntfCommonDTO consultCompIntfCommonDTO = new ConsultCompIntfCommonDTO();
	private ConsultCompIntfDetailDTO consultCompIntfDetailDTO = new ConsultCompIntfDetailDTO();
    
	public ConsultCompIntfCommonDTO getConsultCompIntfCommonDTO() {
		return consultCompIntfCommonDTO;
	}
	public void setConsultCompIntfCommonDTO(ConsultCompIntfCommonDTO consultCompIntfCommonDTO) {
		this.consultCompIntfCommonDTO = consultCompIntfCommonDTO;
	}
	public ConsultCompIntfDetailDTO getConsultCompIntfDetailDTO() {
		return consultCompIntfDetailDTO;
	}
	public void setConsultCompIntfDetailDTO(ConsultCompIntfDetailDTO consultCompIntfDetailDTO) {
		this.consultCompIntfDetailDTO = consultCompIntfDetailDTO;
	}
}
