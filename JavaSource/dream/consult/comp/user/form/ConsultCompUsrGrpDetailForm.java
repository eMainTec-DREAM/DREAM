package dream.consult.comp.user.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpDetailDTO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpMenuDTO;


/**
 * ���ѱ׷�- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompUsrGrpDetailForm"
 */
public class ConsultCompUsrGrpDetailForm extends BaseForm
{
    //========================================================================
    /** ���ѱ׷� ���� */ 
    private ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = new ConsultCompUsrGrpCommonDTO();
    //========================================================================
    /** ���ѱ׷� �� */
    private ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO = new ConsultCompUsrGrpDetailDTO();
    /** ���ѱ׷� �� */
    private ConsultCompUsrGrpMenuDTO consultCompUsrGrpMenuDTO = new ConsultCompUsrGrpMenuDTO();
    
    /** �޴� id/desc */
    private List consultCompUsrGrpMenuDTOList =  ListUtils.lazyList(new ArrayList(), getDtoFactory(ConsultCompUsrGrpMenuDTO.class));

	public ConsultCompUsrGrpMenuDTO getConsultCompUsrGrpMenuDTO()
    {
        return consultCompUsrGrpMenuDTO;
    }

    public void setConsultCompUsrGrpMenuDTO(ConsultCompUsrGrpMenuDTO consultCompUsrGrpMenuDTO)
    {
        this.consultCompUsrGrpMenuDTO = consultCompUsrGrpMenuDTO;
    }

    public ConsultCompUsrGrpCommonDTO getConsultCompUsrGrpCommonDTO() 
	{
		return consultCompUsrGrpCommonDTO;
	}

	public void setConsultCompUsrGrpCommonDTO(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) 
	{
		this.consultCompUsrGrpCommonDTO = consultCompUsrGrpCommonDTO;
	}

	public ConsultCompUsrGrpDetailDTO getConsultCompUsrGrpDetailDTO() 
	{
		return consultCompUsrGrpDetailDTO;
	}

	public void setConsultCompUsrGrpDetailDTO(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO) 
	{
		this.consultCompUsrGrpDetailDTO = consultCompUsrGrpDetailDTO;
	}

    public List getConsultCompUsrGrpMenuDTOList()
    {
        return consultCompUsrGrpMenuDTOList;
    }

    public void setConsultCompUsrGrpMenuDTOList(List consultCompUsrGrpMenuDTOList)
    {
        this.consultCompUsrGrpMenuDTOList = consultCompUsrGrpMenuDTOList;
    }

}
