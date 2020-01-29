package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartDetailDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * ǥ���׸� ����Ʈ- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPartDetailForm"
 */
public class MaStdPartDetailForm extends BaseForm
{
    //========================================================================
    /** ǥ���׸� ���� */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** ǥ���׸񸮚� ��� */
    private MaStdPartListDTO maStdPartListDTO = new MaStdPartListDTO();
    /** ǥ���׸񸮚� �� */
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
