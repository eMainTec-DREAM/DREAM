package dream.mgr.intf.form;

import common.struts.BaseForm;
import dream.mgr.intf.dto.MgrIntfCommonDTO;

/**
 * 인터페이스 파라미터
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrIntfParamsForm"
 */
public class MgrIntfParamsForm extends BaseForm
{    
	//========================================================================
    /** 공통 */ 
    private MgrIntfCommonDTO mgrIntfCommonDTO = new MgrIntfCommonDTO();
    
    public MgrIntfCommonDTO getMgrIntfCommonDTO()
    {
        return mgrIntfCommonDTO;
    }
    public void setMgrIntfCommonDTO(MgrIntfCommonDTO mgrIntfCommonDTO)
    {
        this.mgrIntfCommonDTO = mgrIntfCommonDTO;
    }
}
