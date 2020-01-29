package dream.consult.comp.intf.form;

import common.struts.BaseForm;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;
/**
 * Interface Log Page - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="consultCompIntfMapListForm"
 * */

public class ConsultCompIntfMapListForm extends BaseForm{
    private ConsultCompIntfCommonDTO consultCompIntfCommonDTO = new ConsultCompIntfCommonDTO();
	private ConsultCompIntfMapListDTO consultCompIntfMapListDTO = new ConsultCompIntfMapListDTO();

	public ConsultCompIntfCommonDTO getConsultCompIntfCommonDTO()
    {
        return consultCompIntfCommonDTO;
    }

    public void setConsultCompIntfCommonDTO(ConsultCompIntfCommonDTO consultCompIntfCommonDTO)
    {
        this.consultCompIntfCommonDTO = consultCompIntfCommonDTO;
    }

    public ConsultCompIntfMapListDTO getConsultCompIntfMapListDTO() {
		return consultCompIntfMapListDTO;
	}

	public void setConsultCompIntfMapListDTO(ConsultCompIntfMapListDTO consultCompIntfMapListDTO) {
		this.consultCompIntfMapListDTO = consultCompIntfMapListDTO;
	}
	
}
