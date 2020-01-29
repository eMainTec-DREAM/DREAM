package dream.consult.comp.eqmgr.form;

import common.struts.BaseForm;
import dream.consult.comp.eqmgr.dto.MaEqMngDetailDTO;

/**
 * 설비담당자변경- 상세 Form
 * @author  kim21017
 * @version $Id: MaEqMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMngDetailForm"
 */
public class MaEqMngDetailForm extends BaseForm
{
    /** 설비마스터 상세 */
    private MaEqMngDetailDTO maEqMngDetailDTO = new MaEqMngDetailDTO();

	public MaEqMngDetailDTO getMaEqMngDetailDTO() {
		return maEqMngDetailDTO;
	}

	public void setMaEqMngDetailDTO(MaEqMngDetailDTO maEqMngDetailDTO) {
		this.maEqMngDetailDTO = maEqMngDetailDTO;
	}
}
