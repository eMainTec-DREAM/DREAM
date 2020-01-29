package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;

/**
 * 표준항목 리스트 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPointListForm"
 */
public class MaStdPointListForm extends BaseForm
{    
    //===============================================================
    /** 표준항목 공통 */
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    /** 표준항목 리스트 */
    private MaStdPointListDTO maStdPointListDTO = new MaStdPointListDTO();
    
    public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdPointListDTO getMaStdPointListDTO() {
		return maStdPointListDTO;
	}

	public void setMaStdPointListDTO(MaStdPointListDTO maStdPointListDTO) {
		this.maStdPointListDTO = maStdPointListDTO;
	}
	
}
