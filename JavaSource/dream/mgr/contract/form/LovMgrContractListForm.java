package dream.mgr.contract.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.contract.dto.LovMgrContractListDTO;
/**
 * 단가계약 LOV - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="lovMgrContractListForm"
 * */

public class LovMgrContractListForm extends MaFinderAcForm{
	
	private LovMgrContractListDTO lovMgrContractListDTO = new LovMgrContractListDTO();

	public LovMgrContractListDTO getLovMgrContractListDTO() {
		return lovMgrContractListDTO;
	}

	public void setLovMgrContractListDTO(LovMgrContractListDTO lovMgrContractListDTO) {
		this.lovMgrContractListDTO = lovMgrContractListDTO;
	}

	
}
