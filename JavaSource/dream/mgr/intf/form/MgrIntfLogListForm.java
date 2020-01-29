package dream.mgr.intf.form;

import common.struts.BaseForm;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;
/**
 * Interface Log Page - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrIntfLogListForm"
 * */

public class MgrIntfLogListForm extends BaseForm{
    private MgrIntfCommonDTO mgrIntfCommonDTO = new MgrIntfCommonDTO();
	private MgrIntfLogListDTO mgrIntfLogListDTO = new MgrIntfLogListDTO();

	public MgrIntfCommonDTO getMgrIntfCommonDTO()
    {
        return mgrIntfCommonDTO;
    }

    public void setMgrIntfCommonDTO(MgrIntfCommonDTO mgrIntfCommonDTO)
    {
        this.mgrIntfCommonDTO = mgrIntfCommonDTO;
    }

    public MgrIntfLogListDTO getMgrIntfLogListDTO() {
		return mgrIntfLogListDTO;
	}

	public void setMgrIntfLogListDTO(MgrIntfLogListDTO mgrIntfLogListDTO) {
		this.mgrIntfLogListDTO = mgrIntfLogListDTO;
	}
	
}
