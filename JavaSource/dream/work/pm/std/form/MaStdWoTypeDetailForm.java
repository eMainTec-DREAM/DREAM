package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeDetailDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * ǥ���׸� ����Ʈ- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdWoTypeDetailForm"
 */
public class MaStdWoTypeDetailForm extends BaseForm
{
    //========================================================================
    /** ǥ���׸� ���� */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** ǥ���׸񸮚� ��� */
    private MaStdWoTypeListDTO maStdWoTypeListDTO = new MaStdWoTypeListDTO();
    /** ǥ���׸񸮚� �� */
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
