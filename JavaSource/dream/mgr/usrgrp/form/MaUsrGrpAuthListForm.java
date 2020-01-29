package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maUsrGrpAuthListForm"
 */
public class MaUsrGrpAuthListForm extends BaseForm
{    
    //===============================================================
    /** 권한그룹 공통 */
    private MaUsrGrpCommonDTO maUsrGrpCommonDTO = new MaUsrGrpCommonDTO();
    
    public MaUsrGrpCommonDTO getMaUsrGrpCommonDTO() 
	{
		return maUsrGrpCommonDTO;
	}

	public void setMaUsrGrpCommonDTO(MaUsrGrpCommonDTO maUsrGrpCommonDTO) 
	{
		this.maUsrGrpCommonDTO = maUsrGrpCommonDTO;
	}
}
