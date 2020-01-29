package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
/**
 * 화면권한설정
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts.form name="mgrUsrGrpPageAuthForm"
 */
public class MgrUsrGrpPageAuthForm extends BaseForm{
	
	private MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = new MgrUsrGrpPageAuthDTO();

    public MgrUsrGrpPageAuthDTO getMgrUsrGrpPageAuthDTO()
    {
        return mgrUsrGrpPageAuthDTO;
    }

    public void setMgrUsrGrpPageAuthDTO(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO)
    {
        this.mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthDTO;
    }

}
