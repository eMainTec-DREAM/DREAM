package dream.consult.comp.emp.form;

import common.struts.BaseForm;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpDetailDTO;

/**
 * ���- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompEmpDetailForm"
 */
public class ConsultCompEmpDetailForm extends BaseForm
{
    //========================================================================
    /** ��� ���� */ 
    private ConsultCompEmpCommonDTO consultCompEmpCommonDTO = new ConsultCompEmpCommonDTO();
    //========================================================================
    /** ��� �� */
    private ConsultCompEmpDetailDTO consultCompEmpDetailDTO = new ConsultCompEmpDetailDTO();

	public ConsultCompEmpCommonDTO getConsultCompEmpCommonDTO() 
	{
		return consultCompEmpCommonDTO;
	}

	public void setConsultCompEmpCommonDTO(ConsultCompEmpCommonDTO consultCompEmpCommonDTO) 
	{
		this.consultCompEmpCommonDTO = consultCompEmpCommonDTO;
	}

	public ConsultCompEmpDetailDTO getConsultCompEmpDetailDTO() 
	{
		return consultCompEmpDetailDTO;
	}

	public void setConsultCompEmpDetailDTO(ConsultCompEmpDetailDTO consultCompEmpDetailDTO) 
	{
		this.consultCompEmpDetailDTO = consultCompEmpDetailDTO;
	}

}
