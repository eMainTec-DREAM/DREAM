package dream.mgr.contract.form;

import common.struts.BaseForm;
import dream.mgr.contract.dto.MgrContractDTO;
import dream.mgr.contract.dto.MgrContractItemDTO;

/**
 * 단가계약 설정 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrContractItemForm"
 */
public class MgrContractItemForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MgrContractDTO mgrContractDTO = new MgrContractDTO();
    /** 단가계약 설정 목록  */
    private MgrContractItemDTO mgrContractItemDTO = new MgrContractItemDTO();
	

	public MgrContractDTO getMgrContractDTO() {
		return mgrContractDTO;
	}

	public void setMgrContractDTO(MgrContractDTO mgrContractDTO) {
		this.mgrContractDTO = mgrContractDTO;
	}

	public MgrContractItemDTO getMgrContractItemDTO() {
		return mgrContractItemDTO;
	}

	public void setMgrContractItemDTO(MgrContractItemDTO mgrContractItemDTO) {
		this.mgrContractItemDTO = mgrContractItemDTO;
	}
}
