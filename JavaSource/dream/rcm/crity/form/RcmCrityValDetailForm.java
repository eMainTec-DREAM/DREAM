package dream.rcm.crity.form;

import common.struts.BaseForm;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValDetailDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;

/**
 * Criticality Matrix Val Page - Detail Form
 * @author kim21017
 * @version $Id: RcmCrityValDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityValDetailForm"
 */
public class RcmCrityValDetailForm extends BaseForm
{
	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();
	private RcmCrityValListDTO rcmCrityValListDTO = new RcmCrityValListDTO();
	private RcmCrityValDetailDTO rcmCrityValDetailDTO = new RcmCrityValDetailDTO();
	
	public RcmCrityCommonDTO getRcmCrityCommonDTO() {
		return rcmCrityCommonDTO;
	}
	public void setRcmCrityCommonDTO(RcmCrityCommonDTO rcmCrityCommonDTO) {
		this.rcmCrityCommonDTO = rcmCrityCommonDTO;
	}
	public RcmCrityValListDTO getRcmCrityValListDTO() {
		return rcmCrityValListDTO;
	}
	public void setRcmCrityValListDTO(RcmCrityValListDTO rcmCrityValListDTO) {
		this.rcmCrityValListDTO = rcmCrityValListDTO;
	}
	public RcmCrityValDetailDTO getRcmCrityValDetailDTO() {
		return rcmCrityValDetailDTO;
	}
	public void setRcmCrityValDetailDTO(RcmCrityValDetailDTO rcmCrityValDetailDTO) {
		this.rcmCrityValDetailDTO = rcmCrityValDetailDTO;
	}
}
