package mobile.dream.org.dept.form;

import common.struts.BaseForm;
import mobile.dream.org.dept.dto.OrgDeptLovListDTO;

/**
 * ºÎ¼­ÆË¾÷ Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgDeptLovListForm"
 */
public class OrgDeptLovListForm extends BaseForm
{
    /** ºÎ¼­ÆË¾÷ DTO */
    private OrgDeptLovListDTO orgDeptLovListDTO = new OrgDeptLovListDTO();

	public OrgDeptLovListDTO getOrgDeptLovListDTO() 
	{
		return orgDeptLovListDTO;
	}

	public void setOrgDeptLovListDTO(OrgDeptLovListDTO orgDeptLovListDTO) 
	{
		this.orgDeptLovListDTO = orgDeptLovListDTO;
	}
}
