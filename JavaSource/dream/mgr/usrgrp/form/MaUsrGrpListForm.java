package dream.mgr.usrgrp.form;

import common.struts.BaseForm;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * ���ѱ׷� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maUsrGrpListForm"
 */
public class MaUsrGrpListForm extends BaseForm
{    
    //===============================================================
    /** ���ѱ׷� ���� */
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
