package dream.consult.comp.config.form;

import common.struts.BaseForm;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;

/**
 * 시스템환경변수 - 목록 form
 * @author  syyang
 * @version $Id: ConsultCompConfigListForm.java,v 1.0 2015/12/01 09:13:09 syyang Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompConfigListForm"
 */
public class ConsultCompConfigListForm extends BaseForm
{    
    //===============================================================
    /** 시스템환경변수 공통 */
    private ConsultCompConfigCommonDTO consultCompConfigCommonDTO = new ConsultCompConfigCommonDTO();
    
	public ConsultCompConfigCommonDTO getConsultCompConfigCommonDTO() {
		return consultCompConfigCommonDTO;
	}

	public void setConsultCompConfigCommonDTO(ConsultCompConfigCommonDTO consultCompConfigCommonDTO) {
		this.consultCompConfigCommonDTO = consultCompConfigCommonDTO;
	}
	
}
