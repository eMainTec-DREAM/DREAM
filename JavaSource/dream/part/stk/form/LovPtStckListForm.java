package dream.part.stk.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.stk.dto.LovPtStckListDTO;

/**
 * ����˾� Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPtStckListForm"
 */
public class LovPtStckListForm extends MaFinderAcForm
{
    /** �����˾� DTO */
    private LovPtStckListDTO lovPtStckListDTO = new LovPtStckListDTO();

	public LovPtStckListDTO getLovPtStckListDTO() {
		return lovPtStckListDTO;
	}

	public void setLovPtStckListDTO(LovPtStckListDTO lovPtStckListDTO) {
		this.lovPtStckListDTO = lovPtStckListDTO;
	}
}
