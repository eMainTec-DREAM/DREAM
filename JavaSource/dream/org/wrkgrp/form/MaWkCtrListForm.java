package dream.org.wrkgrp.form;

import common.struts.BaseForm;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;

/**
 * �۾��׷� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWkCtrListForm"
 */
public class MaWkCtrListForm extends BaseForm
{    
    //===============================================================
    /** �۾��׷� ���� */
    private MaWkCtrCommonDTO maWkCtrCommonDTO = new MaWkCtrCommonDTO();;
    
	public MaWkCtrCommonDTO getMaWkCtrCommonDTO() 
	{
		return maWkCtrCommonDTO;
	}

	public void setMaWkCtrCommonDTO(MaWkCtrCommonDTO maWkCtrCommonDTO) 
	{
		this.maWkCtrCommonDTO = maWkCtrCommonDTO;
	}
	
}
