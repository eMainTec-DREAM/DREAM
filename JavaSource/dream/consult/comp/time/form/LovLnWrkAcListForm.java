package dream.consult.comp.time.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.comp.time.dto.LovLnWrkAcListDTO;
/**
 * 가동달력 LOV - List Form
 * @author youngjoo38
 * @version $Id: LovLnWrkAcListForm.java,v 1.0 2017/12/14 11:09:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="lovLnWrkAcListForm"
 * */

public class LovLnWrkAcListForm extends MaFinderAcForm{
	
	private LovLnWrkAcListDTO lovLnWrkAcListDTO = new LovLnWrkAcListDTO();

	public LovLnWrkAcListDTO getLovLnWrkAcListDTO() {
		return lovLnWrkAcListDTO;
	}

	public void setLovLnWrkAcListDTO(LovLnWrkAcListDTO lovLnWrkAcListDTO) {
		this.lovLnWrkAcListDTO = lovLnWrkAcListDTO;
	}

	
}
