package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthTabDTO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
/**
 * 화면권한설정상세탭탭권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts.form name="mgrUsrGrpPageAuthTabForm"
 */
public class MgrUsrGrpPageAuthTabForm extends BaseForm{
	
    private MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = new MgrUsrGrpPageAuthDTO();
	private MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO = new MgrUsrGrpPageAuthTabDTO();

    public MgrUsrGrpPageAuthDTO getMgrUsrGrpPageAuthDTO()
    {
        return mgrUsrGrpPageAuthDTO;
    }

    public void setMgrUsrGrpPageAuthDTO(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO)
    {
        this.mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthDTO;
    }

    public MgrUsrGrpPageAuthTabDTO getMgrUsrGrpPageAuthTabDTO()
    {
        return mgrUsrGrpPageAuthTabDTO;
    }

    public void setMgrUsrGrpPageAuthTabDTO(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO)
    {
        this.mgrUsrGrpPageAuthTabDTO = mgrUsrGrpPageAuthTabDTO;
    }

}
