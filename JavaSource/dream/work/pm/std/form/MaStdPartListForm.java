package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * ǥ���׸� ����Ʈ - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPartListForm"
 */
public class MaStdPartListForm extends BaseForm
{    
    //===============================================================
    /** ǥ���׸� ���� */
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    /** ǥ���׸� ����Ʈ */
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
