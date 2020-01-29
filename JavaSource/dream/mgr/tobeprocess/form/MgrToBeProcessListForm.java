package dream.mgr.tobeprocess.form;

import common.struts.BaseForm;
import dream.mgr.tobeprocess.dto.MgrToBeProcessCommonDTO;
/**
 * ToBeProcess Page - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrToBeProcessListForm"
 * */

public class MgrToBeProcessListForm extends BaseForm{
	
	private MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO = new MgrToBeProcessCommonDTO();

	public MgrToBeProcessCommonDTO getMgrToBeProcessCommonDTO() {
		return mgrToBeProcessCommonDTO;
	}

	public void setMgrToBeProcessCommonDTO(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO) {
		this.mgrToBeProcessCommonDTO = mgrToBeProcessCommonDTO;
	}
	
}
