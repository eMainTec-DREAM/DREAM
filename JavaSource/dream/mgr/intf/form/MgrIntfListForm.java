package dream.mgr.intf.form;

import common.struts.BaseForm;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
/**
 * Interface Page - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrIntfListForm"
 * */

public class MgrIntfListForm extends BaseForm{
	
	private MgrIntfCommonDTO mgrIntfCommonDTO = new MgrIntfCommonDTO();

	public MgrIntfCommonDTO getMgrIntfCommonDTO() {
		return mgrIntfCommonDTO;
	}

	public void setMgrIntfCommonDTO(MgrIntfCommonDTO mgrIntfCommonDTO) {
		this.mgrIntfCommonDTO = mgrIntfCommonDTO;
	}
	
}
