package mobile.dream.org.wrkgrp.form;

import common.struts.BaseForm;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;

/**
 * 작업그룹팝업  Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgWkCtrLovListForm"
 */
public class OrgWkCtrLovListForm extends BaseForm
{
    /** 작업그룹 팝업 DTO */
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
