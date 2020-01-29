package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartDetailDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * 표준항목 리스트- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPartDetailForm"
 */
public class MaStdPartDetailForm extends BaseForm
{
    //========================================================================
    /** 표준항목 공통 */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** 표준항목리슽 목록 */
    private MaStdPartListDTO maStdPartListDTO = new MaStdPartListDTO();
    /** 표준항목리슽 상세 */
    private MaStdPartDetailDTO maStdPartDetailDTO = new MaStdPartDetailDTO();

	public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdPartDetailDTO getMaStdPartDetailDTO() 
	{
		return maStdPartDetailDTO;
	}

	public void setMaStdPartDetailDTO(MaStdPartDetailDTO maStdPartDetailDTO) 
	{
		this.maStdPartDetailDTO = maStdPartDetailDTO;
	}

	public MaStdPartListDTO getMaStdPartListDTO() {
		return maStdPartListDTO;
	}

	public void setMaStdPartListDTO(MaStdPartListDTO maStdPartListDTO) {
		this.maStdPartListDTO = maStdPartListDTO;
	}

}
