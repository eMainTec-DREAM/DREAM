package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovPtIssListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * ����ǰ Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPtIssListForm"
 */
public class LovPtIssListForm extends MaFinderAcForm
{
    /** ����˾� DTO */
    private LovPtIssListDTO lovPtIssListDTO = new LovPtIssListDTO();

	public LovPtIssListDTO getLovPtIssListDTO() {
		return lovPtIssListDTO;
	}

	public void setLovPtIssListDTO(LovPtIssListDTO lovPtIssListDTO) {
		this.lovPtIssListDTO = lovPtIssListDTO;
	}
}
