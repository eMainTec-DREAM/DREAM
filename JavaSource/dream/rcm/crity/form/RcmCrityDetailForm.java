package dream.rcm.crity.form;

import common.struts.BaseForm;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityDetailDTO;

/**
 * Criticality Matrix Page - Detail Form
 * @author kim21017
 * @version $Id: RcmCrityDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityDetailForm"
 */
public class RcmCrityDetailForm extends BaseForm
{
	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();
	private RcmCrityDetailDTO rcmCrityDetailDTO = new RcmCrityDetailDTO();
    
	public RcmCrityCommonDTO getRcmCrityCommonDTO() {
		return rcmCrityCommonDTO;
	}
	public void setRcmCrityCommonDTO(RcmCrityCommonDTO rcmCrityCommonDTO) {
		this.rcmCrityCommonDTO = rcmCrityCommonDTO;
	}
	public RcmCrityDetailDTO getRcmCrityDetailDTO() {
		return rcmCrityDetailDTO;
	}
	public void setRcmCrityDetailDTO(RcmCrityDetailDTO rcmCrityDetailDTO) {
		this.rcmCrityDetailDTO = rcmCrityDetailDTO;
	}
}
