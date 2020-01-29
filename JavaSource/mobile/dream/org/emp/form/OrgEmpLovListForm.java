package mobile.dream.org.emp.form;

import common.struts.BaseForm;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;

/**
 * ����˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="orgEmpLovListForm"
 */
public class OrgEmpLovListForm extends BaseForm
{
    /** ����˾� DTO */
    private OrgEmpLovListDTO orgEmpLovListDTO = new OrgEmpLovListDTO();

	public OrgEmpLovListDTO getOrgEmpLovListDTO() 
	{
		return orgEmpLovListDTO;
	}

	public void setOrgEmpLovListDTO(OrgEmpLovListDTO orgEmpLovListDTO) 
	{
		this.orgEmpLovListDTO = orgEmpLovListDTO;
	}
}
