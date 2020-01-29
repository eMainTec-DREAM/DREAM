package dream.mgr.intf.form;

import common.struts.BaseForm;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfDetailDTO;

/**
 * Interface Page - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrIntfDetailForm"
 */
public class MgrIntfDetailForm extends BaseForm
{
	private MgrIntfCommonDTO mgrIntfCommonDTO = new MgrIntfCommonDTO();
	private MgrIntfDetailDTO mgrIntfDetailDTO = new MgrIntfDetailDTO();
    
	public MgrIntfCommonDTO getMgrIntfCommonDTO() {
		return mgrIntfCommonDTO;
	}
	public void setMgrIntfCommonDTO(MgrIntfCommonDTO mgrIntfCommonDTO) {
		this.mgrIntfCommonDTO = mgrIntfCommonDTO;
	}
	public MgrIntfDetailDTO getMgrIntfDetailDTO() {
		return mgrIntfDetailDTO;
	}
	public void setMgrIntfDetailDTO(MgrIntfDetailDTO mgrIntfDetailDTO) {
		this.mgrIntfDetailDTO = mgrIntfDetailDTO;
	}
}
