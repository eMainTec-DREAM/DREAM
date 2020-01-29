package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeDetailDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * 표준항목 리스트- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdWoTypeDetailForm"
 */
public class MaStdWoTypeDetailForm extends BaseForm
{
    //========================================================================
    /** 표준항목 공통 */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** 표준항목리슽 목록 */
    private MaStdWoTypeListDTO maStdWoTypeListDTO = new MaStdWoTypeListDTO();
    /** 표준항목리슽 상세 */
    private MaStdWoTypeDetailDTO maStdWoTypeDetailDTO = new MaStdWoTypeDetailDTO();

	public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdWoTypeDetailDTO getMaStdWoTypeDetailDTO() 
	{
		return maStdWoTypeDetailDTO;
	}

	public void setMaStdWoTypeDetailDTO(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO) 
	{
		this.maStdWoTypeDetailDTO = maStdWoTypeDetailDTO;
	}

	public MaStdWoTypeListDTO getMaStdWoTypeListDTO() {
		return maStdWoTypeListDTO;
	}

	public void setMaStdWoTypeListDTO(MaStdWoTypeListDTO maStdWoTypeListDTO) {
		this.maStdWoTypeListDTO = maStdWoTypeListDTO;
	}

}
