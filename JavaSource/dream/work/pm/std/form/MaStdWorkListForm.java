package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * 표준항목 리스트 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdWorkListForm"
 */
public class MaStdWorkListForm extends BaseForm
{    
    //===============================================================
    /** 표준항목 공통 */
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    /** 표준항목 리스트 */
    private MaStdWorkListDTO maStdWorkListDTO = new MaStdWorkListDTO();
    
    public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdWorkListDTO getMaStdWorkListDTO() {
		return maStdWorkListDTO;
	}

	public void setMaStdWorkListDTO(MaStdWorkListDTO maStdWorkListDTO) {
		this.maStdWorkListDTO = maStdWorkListDTO;
	}
	
}
