package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncDetailDTO;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 연결기능 상세 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultPgmPgLinkedFuncDetailForm"
 */
public class ConsultPgmPgLinkedFuncDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
	/** 화면별 연결기능 목록 DTO  */
    private ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO = new ConsultPgmPgLinkedFuncListDTO();
	/** 화면별 연결기능 상세 DTO  */
    private ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO = new ConsultPgmPgLinkedFuncDetailDTO();
    
	public ConsultPgmPgLinkedFuncListDTO getConsultPgmPgLinkedFuncListDTO() {
		return consultPgmPgLinkedFuncListDTO;
	}
	public void setConsultPgmPgLinkedFuncListDTO(ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO) {
		this.consultPgmPgLinkedFuncListDTO = consultPgmPgLinkedFuncListDTO;
	}
	public ConsultPgmPgLinkedFuncDetailDTO getConsultPgmPgLinkedFuncDetailDTO() {
		return consultPgmPgLinkedFuncDetailDTO;
	}
	public void setConsultPgmPgLinkedFuncDetailDTO(ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO) {
		this.consultPgmPgLinkedFuncDetailDTO = consultPgmPgLinkedFuncDetailDTO;
	}
	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}
	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}
}
