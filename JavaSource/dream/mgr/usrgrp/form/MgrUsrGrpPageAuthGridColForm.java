package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthGridColDTO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts.form name="mgrUsrGrpPageAuthGridColForm"
 */
public class MgrUsrGrpPageAuthGridColForm extends BaseForm{
	
    private MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = new MgrUsrGrpPageAuthDTO();
	private MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO = new MgrUsrGrpPageAuthGridColDTO();

    public MgrUsrGrpPageAuthDTO getMgrUsrGrpPageAuthDTO()
    {
        return mgrUsrGrpPageAuthDTO;
    }

    public void setMgrUsrGrpPageAuthDTO(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO)
    {
        this.mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthDTO;
    }

    public MgrUsrGrpPageAuthGridColDTO getMgrUsrGrpPageAuthGridColDTO()
    {
        return mgrUsrGrpPageAuthGridColDTO;
    }

    public void setMgrUsrGrpPageAuthGridColDTO(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO)
    {
        this.mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColDTO;
    }

}
