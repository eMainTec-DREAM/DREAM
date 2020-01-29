package dream.part.stk.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.stk.dto.LovPtStckListDTO;

/**
 * 營堅で機 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPtStckListForm"
 */
public class LovPtStckListForm extends MaFinderAcForm
{
    /** 濠營で機 DTO */
    private LovPtStckListDTO lovPtStckListDTO = new LovPtStckListDTO();

	public LovPtStckListDTO getLovPtStckListDTO() {
		return lovPtStckListDTO;
	}

	public void setLovPtStckListDTO(LovPtStckListDTO lovPtStckListDTO) {
		this.lovPtStckListDTO = lovPtStckListDTO;
	}
}
