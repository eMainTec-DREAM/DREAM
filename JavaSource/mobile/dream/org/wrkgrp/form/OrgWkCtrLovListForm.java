package mobile.dream.org.wrkgrp.form;

import common.struts.BaseForm;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;

/**
 * �۾��׷��˾�  Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgWkCtrLovListForm"
 */
public class OrgWkCtrLovListForm extends BaseForm
{
    /** �۾��׷� �˾� DTO */
    private OrgWkCtrLovListDTO orgWkCtrLovListDTO = new OrgWkCtrLovListDTO();

	public OrgWkCtrLovListDTO getOrgWkCtrLovListDTO() 
	{
		return orgWkCtrLovListDTO;
	}

	public void setOrgWkCtrLovListDTO(OrgWkCtrLovListDTO orgWkCtrLovListDTO) 
	{
		this.orgWkCtrLovListDTO = orgWkCtrLovListDTO;
	}
}
