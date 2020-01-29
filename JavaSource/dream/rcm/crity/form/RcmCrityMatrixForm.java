package dream.rcm.crity.form;

import common.struts.BaseForm;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityMatrixDTO;
/**
 * Criticality Matrix Page - Matrix Form
 * @author kim21017
 * @version $Id: RcmCrityMatrixForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityMatrixForm"
 * */

public class RcmCrityMatrixForm extends BaseForm{

	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();
	private RcmCrityMatrixDTO rcmCrityMatrixDTO = new RcmCrityMatrixDTO();

	
	public RcmCrityCommonDTO getRcmCrityCommonDTO() {
		return rcmCrityCommonDTO;
	}

	public void setRcmCrityCommonDTO(RcmCrityCommonDTO rcmCrityCommonDTO) {
		this.rcmCrityCommonDTO = rcmCrityCommonDTO;
	}

	public RcmCrityMatrixDTO getRcmCrityMatrixDTO() {
		return rcmCrityMatrixDTO;
	}

	public void setRcmCrityMatrixDTO(RcmCrityMatrixDTO rcmCrityMatrixDTO) {
		this.rcmCrityMatrixDTO = rcmCrityMatrixDTO;
	}
	
}
