package dream.consult.comp.dept.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.dto.ConsultCompDeptDetailDTO;

/**
 * �����μ�- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompDeptDetailForm"
 */
public class ConsultCompDeptDetailForm extends BaseForm
{
    //========================================================================
    /** �����μ� ���� */ 
    private ConsultCompDeptCommonDTO consultCompDeptCommonDTO = new ConsultCompDeptCommonDTO();
    //========================================================================
    /** �����μ� �� */
    private ConsultCompDeptDetailDTO consultCompDeptDetailDTO = new ConsultCompDeptDetailDTO();
    
    /** ��뿩�� Options */
    private Collection isUseOptions = null;

	public ConsultCompDeptCommonDTO getConsultCompDeptCommonDTO() {
		return consultCompDeptCommonDTO;
	}

	public void setConsultCompDeptCommonDTO(ConsultCompDeptCommonDTO consultCompDeptCommonDTO) {
		this.consultCompDeptCommonDTO = consultCompDeptCommonDTO;
	}

	public ConsultCompDeptDetailDTO getConsultCompDeptDetailDTO() {
		return consultCompDeptDetailDTO;
	}

	public void setConsultCompDeptDetailDTO(ConsultCompDeptDetailDTO consultCompDeptDetailDTO) {
		this.consultCompDeptDetailDTO = consultCompDeptDetailDTO;
	}

	public Collection getIsUseOptions() {
		return isUseOptions;
	}

	public void setIsUseOptions(Collection isUseOptions) {
		this.isUseOptions = isUseOptions;
	}
}
