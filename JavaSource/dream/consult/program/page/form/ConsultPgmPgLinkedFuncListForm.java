package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultPgmPgLinkedFuncListForm"
 */
public class ConsultPgmPgLinkedFuncListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    /** page-field */
    private ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO = new ConsultPgmPgLinkedFuncListDTO();
	

	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}

	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}

	public ConsultPgmPgLinkedFuncListDTO getConsultPgmPgLinkedFuncListDTO() {
		return consultPgmPgLinkedFuncListDTO;
	}

	public void setConsultPgmPgLinkedFuncListDTO(ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO) {
		this.consultPgmPgLinkedFuncListDTO = consultPgmPgLinkedFuncListDTO;
	}
}
