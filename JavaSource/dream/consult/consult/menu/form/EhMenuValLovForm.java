package dream.consult.consult.menu.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.consult.menu.dto.EhMenuValLovDTO;
/**
 * 컨설트 메뉴 LOV - List Form
 * @author kim21017
 * @version $Id: EhMenuValLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="ehMenuValLovForm"
 * */

public class EhMenuValLovForm extends MaFinderAcForm{
	
	private EhMenuValLovDTO ehMenuValLovDTO = new EhMenuValLovDTO();

	public EhMenuValLovDTO getEhMenuValLovDTO() {
		return ehMenuValLovDTO;
	}

	public void setEhMenuValLovDTO(EhMenuValLovDTO ehMenuValLovDTO) {
		this.ehMenuValLovDTO = ehMenuValLovDTO;
	}

	
}
