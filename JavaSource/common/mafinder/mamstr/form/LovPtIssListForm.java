package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovPtIssListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 출고부품 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPtIssListForm"
 */
public class LovPtIssListForm extends MaFinderAcForm
{
    /** 사용팝업 DTO */
    private LovPtIssListDTO lovPtIssListDTO = new LovPtIssListDTO();

	public LovPtIssListDTO getLovPtIssListDTO() {
		return lovPtIssListDTO;
	}

	public void setLovPtIssListDTO(LovPtIssListDTO lovPtIssListDTO) {
		this.lovPtIssListDTO = lovPtIssListDTO;
	}
}
