package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncDetailDTO;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * ȭ�麰 ������ �� 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultPgmPgLinkedFuncDetailForm"
 */
public class ConsultPgmPgLinkedFuncDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
	/** ȭ�麰 ������ ��� DTO  */
    private ConsultPgmPgLinkedFuncListDTO consultPgmPgLinkedFuncListDTO = new ConsultPgmPgLinkedFuncListDTO();
	/** ȭ�麰 ������ �� DTO  */
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
