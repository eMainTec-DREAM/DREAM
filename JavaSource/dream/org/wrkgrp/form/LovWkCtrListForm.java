package dream.org.wrkgrp.form;

import dream.comm.form.MaFinderAcForm;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;

/**
 * �۾��׷��˾�  Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWkCtrListForm"
 */
public class LovWkCtrListForm extends MaFinderAcForm
{
    /** �۾��׷� �˾� DTO */
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
