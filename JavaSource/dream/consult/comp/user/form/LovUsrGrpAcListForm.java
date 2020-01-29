package dream.consult.comp.user.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.comp.user.dto.LovUsrGrpAcListDTO;
/**
 * LOV - List Form
 * @author youngjoo38
 * @version $Id: LovUsrGrpAcListForm.java,v 1.0 2017/09/12 16:10:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="lovUsrGrpAcListForm"
 * */

public class LovUsrGrpAcListForm extends MaFinderAcForm{
	
	private LovUsrGrpAcListDTO lovUsrGrpAcListDTO = new LovUsrGrpAcListDTO();

	public LovUsrGrpAcListDTO getLovUsrGrpAcListDTO() {
		return lovUsrGrpAcListDTO;
	}

	public void setLovUsrGrpAcListDTO(LovUsrGrpAcListDTO lovUsrGrpAcListDTO) {
		this.lovUsrGrpAcListDTO = lovUsrGrpAcListDTO;
	}

	
}
