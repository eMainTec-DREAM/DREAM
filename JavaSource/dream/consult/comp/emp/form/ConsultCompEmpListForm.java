package dream.consult.comp.emp.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpGridDTO;

/**
 * ��� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompEmpListForm"
 */
public class ConsultCompEmpListForm extends BaseForm
{    
    //===============================================================
    /** ��� ���� */
    private ConsultCompEmpCommonDTO consultCompEmpCommonDTO = new ConsultCompEmpCommonDTO();
    
    /** �׸��� DTO */
    private List consultCompEmpGridDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(ConsultCompEmpGridDTO.class));

	public List getConsultCompEmpGridDTOList()
    {
        return consultCompEmpGridDTOList;
    }

    public void setConsultCompEmpGridDTOList(List consultCompEmpGridDTOList)
    {
        this.consultCompEmpGridDTOList = consultCompEmpGridDTOList;
    }

    public ConsultCompEmpCommonDTO getConsultCompEmpCommonDTO() 
	{
		return consultCompEmpCommonDTO;
	}

	public void setConsultCompEmpCommonDTO(ConsultCompEmpCommonDTO consultCompEmpCommonDTO) 
	{
		this.consultCompEmpCommonDTO = consultCompEmpCommonDTO;
	}
}
