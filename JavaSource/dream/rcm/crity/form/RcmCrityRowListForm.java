package dream.rcm.crity.form;

import common.struts.BaseForm;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;
/**
 * Criticality Matrix Row Page - List Form
 * @author kim21017
 * @version $Id: RcmCrityRowListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmCrityRowListForm"
 * */

public class RcmCrityRowListForm extends BaseForm{
	
	private RcmCrityCommonDTO rcmCrityCommonDTO = new RcmCrityCommonDTO();
	private RcmCrityRowListDTO rcmCrityRowListDTO = new RcmCrityRowListDTO();
	
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
	
}
