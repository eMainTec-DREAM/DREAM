package dream.mgr.message.form;

import common.struts.BaseForm;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
/**
 * Message Transfer Page - List Form
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrMessageTransListForm"
 * */

public class MgrMessageTransListForm extends BaseForm{
	
	private MgrMessageTransCommonDTO mgrMessageTransCommonDTO = new MgrMessageTransCommonDTO();


	public MgrMessageTransCommonDTO getMgrMessageTransCommonDTO() {
		return mgrMessageTransCommonDTO;
	}
	public void setMgrMessageTransCommonDTO(MgrMessageTransCommonDTO mgrMessageTransCommonDTO) {
		this.mgrMessageTransCommonDTO = mgrMessageTransCommonDTO;
	}

}
