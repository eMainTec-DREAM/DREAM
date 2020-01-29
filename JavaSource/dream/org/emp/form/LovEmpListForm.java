package dream.org.emp.form;

import common.struts.BaseForm;
import dream.comm.form.MaFinderAcForm;
import dream.org.emp.dto.LovEmpListDTO;

/**
 * ����˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEmpListForm"
 */
public class LovEmpListForm extends MaFinderAcForm
{
    /** ����˾� DTO */
    private LovEmpListDTO lovEmpListDTO = new LovEmpListDTO();

	public LovEmpListDTO getLovEmpListDTO() 
	{
		return lovEmpListDTO;
	}

	public void setLovEmpListDTO(LovEmpListDTO lovEmpListDTO) 
	{
		this.lovEmpListDTO = lovEmpListDTO;
	}
}
