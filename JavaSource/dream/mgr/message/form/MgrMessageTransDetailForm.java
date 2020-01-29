package dream.mgr.message.form;

import common.struts.BaseForm;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;

/**
 * Message Transfer Page - Detail Form
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrMessageTransDetailForm"
 */
public class MgrMessageTransDetailForm extends BaseForm
{
	private MgrMessageTransCommonDTO mgrMessageTransCommonDTO = new MgrMessageTransCommonDTO();
	private MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
	
	public MgrMessageTransCommonDTO getMgrMessageTransCommonDTO() {
		return mgrMessageTransCommonDTO;
	}
	public void setMgrMessageTransCommonDTO(MgrMessageTransCommonDTO mgrMessageTransCommonDTO) {
		this.mgrMessageTransCommonDTO = mgrMessageTransCommonDTO;
	}
	public MgrMessageTransDetailDTO getMgrMessageTransDetailDTO() {
		return mgrMessageTransDetailDTO;
	}
	public void setMgrMessageTransDetailDTO(MgrMessageTransDetailDTO mgrMessageTransDetailDTO) {
		this.mgrMessageTransDetailDTO = mgrMessageTransDetailDTO;
	}

}
