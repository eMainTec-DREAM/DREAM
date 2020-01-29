package dream.work.service.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.service.dto.LovWorkServiceListDTO;
/**
 * ¼­ºñ½º LOV - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="lovWorkServiceListForm"
 * */

public class LovWorkServiceListForm extends MaFinderAcForm{
	
	private LovWorkServiceListDTO lovWorkServiceListDTO = new LovWorkServiceListDTO();

	public LovWorkServiceListDTO getLovWorkServiceListDTO() {
		return lovWorkServiceListDTO;
	}

	public void setLovWorkServiceListDTO(LovWorkServiceListDTO lovWorkServiceListDTO) {
		this.lovWorkServiceListDTO = lovWorkServiceListDTO;
	}

	
}
