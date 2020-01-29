package dream.rcm.crity.form;

import dream.comm.form.MaFinderAcForm;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
/**
 * Criticality Matrix Page - List Form
 * @author kim21017
 * @version $Id: RcmCrityValLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityValLovForm"
 * */

public class RcmCrityValLovForm extends MaFinderAcForm{
	
	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();

	public RcmCrityCommonDTO getRcmCrityCommonDTO() {
		return rcmCrityCommonDTO;
	}

	public void setRcmCrityCommonDTO(RcmCrityCommonDTO rcmCrityCommonDTO) {
		this.rcmCrityCommonDTO = rcmCrityCommonDTO;
	}
	
}
