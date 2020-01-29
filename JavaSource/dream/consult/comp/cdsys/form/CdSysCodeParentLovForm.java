package dream.consult.comp.cdsys.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.comp.cdsys.dto.CdSysCodeParentLovDTO;
/**
 * 시스템코드 부모 LOV - List Form
 * @author kim21017
 * @version $Id: CdSysCodeParentLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="cdSysCodeParentLovForm"
 * */

public class CdSysCodeParentLovForm extends MaFinderAcForm{
	
	private CdSysCodeParentLovDTO cdSysCodeParentLovDTO = new CdSysCodeParentLovDTO();

	public CdSysCodeParentLovDTO getCdSysCodeParentLovDTO() {
		return cdSysCodeParentLovDTO;
	}

	public void setCdSysCodeParentLovDTO(CdSysCodeParentLovDTO cdSysCodeParentLovDTO) {
		this.cdSysCodeParentLovDTO = cdSysCodeParentLovDTO;
	}
	
}
