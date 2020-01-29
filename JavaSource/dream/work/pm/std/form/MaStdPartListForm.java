package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * 표준항목 리스트 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPartListForm"
 */
public class MaStdPartListForm extends BaseForm
{    
    //===============================================================
    /** 표준항목 공통 */
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    /** 표준항목 리스트 */
    private MaStdPartListDTO maStdPartListDTO = new MaStdPartListDTO();
    
    public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdPartListDTO getMaStdPartListDTO() {
		return maStdPartListDTO;
	}

	public void setMaStdPartListDTO(MaStdPartListDTO maStdPartListDTO) {
		this.maStdPartListDTO = maStdPartListDTO;
	}
	
}
