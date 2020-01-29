package dream.org.dept.form;

import common.struts.BaseForm;
import dream.comm.form.MaFinderAcForm;
import dream.org.dept.dto.LovDeptListDTO;

/**
 * �μ��˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovDeptListForm"
 */
public class LovDeptListForm extends MaFinderAcForm
{
    /** �μ��˾� DTO */
    private LovDeptListDTO lovDeptListDTO = new LovDeptListDTO();

	public LovDeptListDTO getLovDeptListDTO() 
	{
		return lovDeptListDTO;
	}

	public void setLovDeptListDTO(LovDeptListDTO lovDeptListDTO) 
	{
		this.lovDeptListDTO = lovDeptListDTO;
	}
}
