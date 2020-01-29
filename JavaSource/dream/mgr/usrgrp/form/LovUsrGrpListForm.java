package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.LovUsrGrpListDTO;

/**
 * 권한 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovUsrGrpListForm"
 */
public class LovUsrGrpListForm extends BaseForm
{
    /** 권한 DTO */
    private LovUsrGrpListDTO lovUsrGrpListDTO = new LovUsrGrpListDTO();

	public LovUsrGrpListDTO getLovUsrGrpListDTO() 
	{
		return lovUsrGrpListDTO;
	}

	public void setLovUsrGrpListDTO(LovUsrGrpListDTO lovUsrGrpListDTO) 
	{
		this.lovUsrGrpListDTO = lovUsrGrpListDTO;
	}
}
