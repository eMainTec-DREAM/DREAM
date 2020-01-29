package dream.rcm.crity.form;

import common.struts.BaseForm;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowDetailDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;

/**
 * Criticality Matrix Row Page - Detail Form
 * @author kim21017
 * @version $Id: RcmCrityRowDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityRowDetailForm"
 */
public class RcmCrityRowDetailForm extends BaseForm
{
	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();
	private RcmCrityRowListDTO rcmCrityRowListDTO = new RcmCrityRowListDTO();
	private RcmCrityRowDetailDTO rcmCrityRowDetailDTO = new RcmCrityRowDetailDTO();
	
	public RcmCrityCommonDTO getRcmCrityCommonDTO() {
		return rcmCrityCommonDTO;
	}
	public void setRcmCrityCommonDTO(RcmCrityCommonDTO rcmCrityCommonDTO) {
		this.rcmCrityCommonDTO = rcmCrityCommonDTO;
	}
	public RcmCrityRowListDTO getRcmCrityRowListDTO() {
		return rcmCrityRowListDTO;
	}
	public void setRcmCrityRowListDTO(RcmCrityRowListDTO rcmCrityRowListDTO) {
		this.rcmCrityRowListDTO = rcmCrityRowListDTO;
	}
	public RcmCrityRowDetailDTO getRcmCrityRowDetailDTO() {
		return rcmCrityRowDetailDTO;
	}
	public void setRcmCrityRowDetailDTO(RcmCrityRowDetailDTO rcmCrityRowDetailDTO) {
		this.rcmCrityRowDetailDTO = rcmCrityRowDetailDTO;
	}
}
