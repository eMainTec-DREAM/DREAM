package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * 표준항목 리스트 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdWoTypeListForm"
 */
public class MaStdWoTypeListForm extends BaseForm
{    
    //===============================================================
    /** 표준항목 공통 */
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    /** 표준항목 리스트 */
    private MaStdWoTypeListDTO maStdWoTypeListDTO = new MaStdWoTypeListDTO();
    
    public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdWoTypeListDTO getMaStdWoTypeListDTO() {
		return maStdWoTypeListDTO;
	}

	public void setMaStdWoTypeListDTO(MaStdWoTypeListDTO maStdWoTypeListDTO) {
		this.maStdWoTypeListDTO = maStdWoTypeListDTO;
	}
	
}
