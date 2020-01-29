package dream.mgr.at.form;

import common.struts.BaseForm;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtHistListDTO;

/**
 * Audit Trail Hist Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="mgrAtHistListForm"
 * */
public class MgrAtHistListForm extends BaseForm {
    
    private MgrAtCommonDTO mgrAtCommonDTO = new MgrAtCommonDTO();
    private MgrAtHistListDTO mgrAtHistListDTO = new MgrAtHistListDTO();

    
    public MgrAtCommonDTO getMgrAtCommonDTO()
    {
        return mgrAtCommonDTO;
    }

    public void setMgrAtCommonDTO(MgrAtCommonDTO mgrAtCommonDTO)
    {
        this.mgrAtCommonDTO = mgrAtCommonDTO;
    }

    public MgrAtHistListDTO getMgrAtHistListDTO() {
        return mgrAtHistListDTO;
    }

    public void setMgrAtHistListDTO(MgrAtHistListDTO mgrAtHistListDTO) {
        this.mgrAtHistListDTO = mgrAtHistListDTO;
    }
}