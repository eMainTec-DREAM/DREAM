package dream.org.wrkgrp.form;

import common.struts.BaseForm;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;

/**
 * 작업그룹 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maWkCtrListForm"
 */
public class MaWkCtrListForm extends BaseForm
{    
    //===============================================================
    /** 작업그룹 공통 */
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
