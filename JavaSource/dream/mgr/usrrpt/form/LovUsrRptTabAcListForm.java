package dream.mgr.usrrpt.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.usrrpt.dto.LovUsrRptTabAcListDTO;
/**
 * LOV - List Form
 * @author youngjoo38
 * @version $Id: LovUsrRptTabAcListForm.java,v 1.0 2017/09/12 16:10:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="lovUsrRptTabAcListForm"
 * */

public class LovUsrRptTabAcListForm extends MaFinderAcForm{
	
	private LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO = new LovUsrRptTabAcListDTO();

	public LovUsrRptTabAcListDTO getLovUsrRptTabAcListDTO() {
		return lovUsrRptTabAcListDTO;
	}

	public void setLovUsrRptTabAcListDTO(LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO) {
		this.lovUsrRptTabAcListDTO = lovUsrRptTabAcListDTO;
	}

	
}
