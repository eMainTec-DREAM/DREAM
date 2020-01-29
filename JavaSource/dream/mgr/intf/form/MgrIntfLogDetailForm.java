package dream.mgr.intf.form;

import common.struts.BaseForm;
import dream.mgr.intf.dto.MgrIntfLogDetailDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;

/**
 * Interface Log Page - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrIntfLogDetailForm"
 */
public class MgrIntfLogDetailForm extends BaseForm
{
	private MgrIntfLogListDTO mgrIntfLogListDTO = new MgrIntfLogListDTO();
	private MgrIntfLogDetailDTO mgrIntfLogDetailDTO = new MgrIntfLogDetailDTO();
    
	public MgrIntfLogListDTO getMgrIntfLogListDTO() {
		return mgrIntfLogListDTO;
	}
	public void setMgrIntfLogListDTO(MgrIntfLogListDTO mgrIntfLogListDTO) {
		this.mgrIntfLogListDTO = mgrIntfLogListDTO;
	}
	public MgrIntfLogDetailDTO getMgrIntfLogDetailDTO() {
		return mgrIntfLogDetailDTO;
	}
	public void setMgrIntfLogDetailDTO(MgrIntfLogDetailDTO mgrIntfLogDetailDTO) {
		this.mgrIntfLogDetailDTO = mgrIntfLogDetailDTO;
	}
}
