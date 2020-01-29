package dream.asset.list.form;

import dream.asset.list.dto.LovEqToolAcListDTO;
import dream.comm.form.MaFinderAcForm;
/**
 * LOV - List Form
 * @author youngjoo38
 * @version $Id: LovEqToolAcListForm.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="lovEqToolAcListForm"
 * */

public class LovEqToolAcListForm extends MaFinderAcForm{
	
	private LovEqToolAcListDTO lovEqToolAcListDTO = new LovEqToolAcListDTO();

	public LovEqToolAcListDTO getLovEqToolAcListDTO() {
		return lovEqToolAcListDTO;
	}

	public void setLovEqToolAcListDTO(LovEqToolAcListDTO lovEqToolAcListDTO) {
		this.lovEqToolAcListDTO = lovEqToolAcListDTO;
	}

	
}
