package dream.consult.comp.config.form;

import common.struts.BaseForm;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.dto.ConsultCompConfigDetailDTO;

/**
 * 시스템 환경변수- 상세 Form
 * @author  syyang
 * @version $Id: ConsultCompConfigDetailForm.java,v 1.0 2015/12/02 09:13:09 syyang Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompConfigDetailForm"
 */
public class ConsultCompConfigDetailForm extends BaseForm
{
    //========================================================================
    /** 시스템환경변수 공통 */ 
    private ConsultCompConfigCommonDTO consultCompConfigCommonDTO = new ConsultCompConfigCommonDTO();
    //========================================================================
    /** 시스템환경변수 상세 */
    private ConsultCompConfigDetailDTO consultCompConfigDetailDTO = new ConsultCompConfigDetailDTO();
    
	public ConsultCompConfigCommonDTO getConsultCompConfigCommonDTO() {
		return consultCompConfigCommonDTO;
	}

	public void setConsultCompConfigCommonDTO(ConsultCompConfigCommonDTO consultCompConfigCommonDTO) {
		this.consultCompConfigCommonDTO = consultCompConfigCommonDTO;
	}

	public ConsultCompConfigDetailDTO getConsultCompConfigDetailDTO() {
		return consultCompConfigDetailDTO;
	}

	public void setConsultCompConfigDetailDTO(ConsultCompConfigDetailDTO consultCompConfigDetailDTO) {
		this.consultCompConfigDetailDTO = consultCompConfigDetailDTO;
	}
}
