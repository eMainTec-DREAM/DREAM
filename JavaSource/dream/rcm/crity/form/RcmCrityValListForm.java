package dream.rcm.crity.form;

import common.struts.BaseForm;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;

/**
 * Criticality Matrix Val Page - List Form
 * @author kim21017
 * @version $Id: RcmCrityValListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityValListForm"
 * */

public class RcmCrityValListForm extends BaseForm{
	
	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();
	private RcmCrityValListDTO rcmCrityValListDTO = new RcmCrityValListDTO();
	
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
	
}
