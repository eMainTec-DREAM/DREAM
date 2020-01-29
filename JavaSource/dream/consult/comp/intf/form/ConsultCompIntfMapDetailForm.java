package dream.consult.comp.intf.form;

import common.struts.BaseForm;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapDetailDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;

/**
 * Interface Log Page - Detail Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="consultCompIntfMapDetailForm"
 */
public class ConsultCompIntfMapDetailForm extends BaseForm
{
	private ConsultCompIntfCommonDTO consultCompIntfCommonDTO = new ConsultCompIntfCommonDTO();
	private ConsultCompIntfMapListDTO consultCompIntfMapListDTO = new ConsultCompIntfMapListDTO();
	private ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO = new ConsultCompIntfMapDetailDTO();
    
	public ConsultCompIntfCommonDTO getConsultCompIntfCommonDTO() {
		return consultCompIntfCommonDTO;
	}
	public void setConsultCompIntfCommonDTO(ConsultCompIntfCommonDTO consultCompIntfCommonDTO) {
		this.consultCompIntfCommonDTO = consultCompIntfCommonDTO;
	}
	public ConsultCompIntfMapListDTO getConsultCompIntfMapListDTO() {
		return consultCompIntfMapListDTO;
	}
	public void setConsultCompIntfMapListDTO(ConsultCompIntfMapListDTO consultCompIntfMapListDTO) {
		this.consultCompIntfMapListDTO = consultCompIntfMapListDTO;
	}
	public ConsultCompIntfMapDetailDTO getConsultCompIntfMapDetailDTO() {
		return consultCompIntfMapDetailDTO;
	}
	public void setConsultCompIntfMapDetailDTO(ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO) {
		this.consultCompIntfMapDetailDTO = consultCompIntfMapDetailDTO;
	}
}
