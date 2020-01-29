package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;

/**
 * 표준항목 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPointHdrListForm"
 */
public class MaStdPointHdrListForm extends BaseForm
{    
    //===============================================================
    /** 표준항목 공통 */
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    
    public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}
}
