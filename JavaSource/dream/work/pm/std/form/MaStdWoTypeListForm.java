package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * ǥ���׸� ����Ʈ - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdWoTypeListForm"
 */
public class MaStdWoTypeListForm extends BaseForm
{    
    //===============================================================
    /** ǥ���׸� ���� */
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    /** ǥ���׸� ����Ʈ */
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
