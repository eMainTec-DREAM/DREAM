package dream.mgr.intf.form;

import common.struts.BaseForm;
import dream.mgr.intf.dto.MgrIntfCommonDTO;

/**
 * �������̽� �Ķ����
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="mgrIntfParamsForm"
 */
public class MgrIntfParamsForm extends BaseForm
{    
	//========================================================================
    /** ���� */ 
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
