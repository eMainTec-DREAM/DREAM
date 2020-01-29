package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkDetailDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * ǥ���׸� ����Ʈ- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdWorkDetailForm"
 */
public class MaStdWorkDetailForm extends BaseForm
{
    //========================================================================
    /** ǥ���׸� ���� */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** ǥ���׸񸮚� ��� */
    private MaStdWorkListDTO maStdWorkListDTO = new MaStdWorkListDTO();
    /** ǥ���׸񸮚� �� */
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
