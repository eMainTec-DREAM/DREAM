package dream.org.wrkgrp.form;

import dream.comm.form.MaFinderAcForm;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;

/**
 * 작업그룹팝업  Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWkCtrListForm"
 */
public class LovWkCtrListForm extends MaFinderAcForm
{
    /** 작업그룹 팝업 DTO */
    private LovWkCtrListDTO lovWkCtrListDTO = new LovWkCtrListDTO();

	public LovWkCtrListDTO getLovWkCtrListDTO() 
	{
		return lovWkCtrListDTO;
	}

	public void setLovWkCtrListDTO(LovWkCtrListDTO lovWkCtrListDTO) 
	{
		this.lovWkCtrListDTO = lovWkCtrListDTO;
	}
}
