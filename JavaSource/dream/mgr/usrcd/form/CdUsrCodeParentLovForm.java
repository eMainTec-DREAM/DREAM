package dream.mgr.usrcd.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.usrcd.dto.CdUsrCodeParentLovDTO;
/**
 * 사용자코드 부모 LOV - List Form
 * @author kim21017
 * @version $Id: CdUsrCodeParentLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="cdUsrCodeParentLovForm"
 * */

public class CdUsrCodeParentLovForm extends MaFinderAcForm{
	
	private CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO = new CdUsrCodeParentLovDTO();

	public CdUsrCodeParentLovDTO getCdUsrCodeParentLovDTO() {
		return cdUsrCodeParentLovDTO;
	}

	public void setCdUsrCodeParentLovDTO(CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO) {
		this.cdUsrCodeParentLovDTO = cdUsrCodeParentLovDTO;
	}
	
}
