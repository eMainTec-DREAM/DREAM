package dream.consult.comp.emp.form;

import common.struts.BaseForm;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpDetailDTO;

/**
 * 사원- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompEmpDetailForm"
 */
public class ConsultCompEmpDetailForm extends BaseForm
{
    //========================================================================
    /** 사원 공통 */ 
    private ConsultCompEmpCommonDTO consultCompEmpCommonDTO = new ConsultCompEmpCommonDTO();
    //========================================================================
    /** 사원 상세 */
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
