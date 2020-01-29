package dream.rcm.crity.form;

import common.struts.BaseForm;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
/**
 * Criticality Matrix Col Page - List Form
 * @author kim21017
 * @version $Id: RcmCrityColListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityColListForm"
 * */

public class RcmCrityColListForm extends BaseForm{
	
	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();
	private RcmCrityColListDTO rcmCrityColListDTO = new RcmCrityColListDTO();
	
	public RcmCrityCommonDTO getRcmCrityCommonDTO() {
		return rcmCrityCommonDTO;
	}
	public void setRcmCrityCommonDTO(RcmCrityCommonDTO rcmCrityCommonDTO) {
		this.rcmCrityCommonDTO = rcmCrityCommonDTO;
	}
	public RcmCrityColListDTO getRcmCrityColListDTO() {
		return rcmCrityColListDTO;
	}
	public void setRcmCrityColListDTO(RcmCrityColListDTO rcmCrityColListDTO) {
		this.rcmCrityColListDTO = rcmCrityColListDTO;
	}
	
}
