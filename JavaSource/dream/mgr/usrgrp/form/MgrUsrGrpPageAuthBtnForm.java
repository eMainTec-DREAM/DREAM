package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthBtnDTO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts.form name="mgrUsrGrpPageAuthBtnForm"
 */
public class MgrUsrGrpPageAuthBtnForm extends BaseForm{
	
    private MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = new MgrUsrGrpPageAuthDTO();
	private MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO = new MgrUsrGrpPageAuthBtnDTO();

    public MgrUsrGrpPageAuthDTO getMgrUsrGrpPageAuthDTO()
    {
        return mgrUsrGrpPageAuthDTO;
    }

    public void setMgrUsrGrpPageAuthDTO(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO)
    {
        this.mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthDTO;
    }

    public MgrUsrGrpPageAuthBtnDTO getMgrUsrGrpPageAuthBtnDTO()
    {
        return mgrUsrGrpPageAuthBtnDTO;
    }

    public void setMgrUsrGrpPageAuthBtnDTO(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO)
    {
        this.mgrUsrGrpPageAuthBtnDTO = mgrUsrGrpPageAuthBtnDTO;
    }

}
