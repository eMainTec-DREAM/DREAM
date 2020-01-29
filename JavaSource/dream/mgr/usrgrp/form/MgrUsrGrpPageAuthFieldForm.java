package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthFieldDTO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts.form name="mgrUsrGrpPageAuthFieldForm"
 */
public class MgrUsrGrpPageAuthFieldForm extends BaseForm{
	
    private MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = new MgrUsrGrpPageAuthDTO();
	private MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO = new MgrUsrGrpPageAuthFieldDTO();

    public MgrUsrGrpPageAuthDTO getMgrUsrGrpPageAuthDTO()
    {
        return mgrUsrGrpPageAuthDTO;
    }

    public void setMgrUsrGrpPageAuthDTO(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO)
    {
        this.mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthDTO;
    }

    public MgrUsrGrpPageAuthFieldDTO getMgrUsrGrpPageAuthFieldDTO()
    {
        return mgrUsrGrpPageAuthFieldDTO;
    }

    public void setMgrUsrGrpPageAuthFieldDTO(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO)
    {
        this.mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldDTO;
    }

}
