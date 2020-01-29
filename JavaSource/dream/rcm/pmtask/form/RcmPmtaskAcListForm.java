package dream.rcm.pmtask.form;

import dream.comm.form.MaFinderAcForm;
import dream.rcm.pmtask.dto.RcmPmtaskAcListDTO;
/**
 * LOV - List Form
 * @author kim21017
 * @version $Id: RcmPmtaskAcListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="rcmPmtaskAcListForm"
 * */

public class RcmPmtaskAcListForm extends MaFinderAcForm{
	
	private RcmPmtaskAcListDTO rcmPmtaskAcListDTO = new RcmPmtaskAcListDTO();

	public RcmPmtaskAcListDTO getRcmPmtaskAcListDTO() {
		return rcmPmtaskAcListDTO;
	}

	public void setRcmPmtaskAcListDTO(RcmPmtaskAcListDTO rcmPmtaskAcListDTO) {
		this.rcmPmtaskAcListDTO = rcmPmtaskAcListDTO;
	}

	
}
