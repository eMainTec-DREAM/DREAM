package dream.consult.comp.dept.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.dto.ConsultCompDeptGridDTO;

/**
 * �����μ� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompDeptListForm"
 */
public class ConsultCompDeptListForm extends BaseForm
{    
    //===============================================================
    /** �����μ� ���� */
    private ConsultCompDeptCommonDTO consultCompDeptCommonDTO = new ConsultCompDeptCommonDTO();
    /** �׸��� DTO */
    private List consultCompDeptGridDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(ConsultCompDeptGridDTO.class));
    
    
	public ConsultCompDeptCommonDTO getConsultCompDeptCommonDTO() 
	{
		return consultCompDeptCommonDTO;
	}

	public void setConsultCompDeptCommonDTO(ConsultCompDeptCommonDTO consultCompDeptCommonDTO) 
	{
		this.consultCompDeptCommonDTO = consultCompDeptCommonDTO;
	}

	public List getConsultCompDeptGridDTOList() 
	{
		return consultCompDeptGridDTOList;
	}

	public void setConsultCompDeptGridDTOList(List consultCompDeptGridDTOList) 
	{
		this.consultCompDeptGridDTOList = consultCompDeptGridDTOList;
	}
	
}
