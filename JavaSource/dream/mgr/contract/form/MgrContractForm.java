package dream.mgr.contract.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.mgr.contract.dto.MgrContractDTO;

/**
 * �ܰ���༳�� - ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrContractForm"
 */
public class MgrContractForm extends BaseForm
{    
    //===============================================================
	/** �ܰ���༳�� ���� */ 
    private MgrContractDTO mgrContractDTO = new MgrContractDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

	public MgrContractDTO getMgrContractDTO() {
		return mgrContractDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public void setMgrContractDTO(MgrContractDTO mgrContractDTO) {
		this.mgrContractDTO = mgrContractDTO;
	}

}
