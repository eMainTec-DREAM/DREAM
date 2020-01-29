package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkDetailDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * 표준항목 리스트- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdWorkDetailForm"
 */
public class MaStdWorkDetailForm extends BaseForm
{
    //========================================================================
    /** 표준항목 공통 */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** 표준항목리슽 목록 */
    private MaStdWorkListDTO maStdWorkListDTO = new MaStdWorkListDTO();
    /** 표준항목리슽 상세 */
    private MaStdWorkDetailDTO maStdWorkDetailDTO = new MaStdWorkDetailDTO();

	public MaStdPointCommonDTO getMaStdPointCommonDTO() 
	{
		return maStdPointCommonDTO;
	}

	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) 
	{
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}

	public MaStdWorkDetailDTO getMaStdWorkDetailDTO() 
	{
		return maStdWorkDetailDTO;
	}

	public void setMaStdWorkDetailDTO(MaStdWorkDetailDTO maStdWorkDetailDTO) 
	{
		this.maStdWorkDetailDTO = maStdWorkDetailDTO;
	}

	public MaStdWorkListDTO getMaStdWorkListDTO() {
		return maStdWorkListDTO;
	}

	public void setMaStdWorkListDTO(MaStdWorkListDTO maStdWorkListDTO) {
		this.maStdWorkListDTO = maStdWorkListDTO;
	}

}
