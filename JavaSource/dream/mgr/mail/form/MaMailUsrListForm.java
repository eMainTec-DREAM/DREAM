package dream.mgr.mail.form;

import common.struts.BaseForm;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * 메일수신자설정-수신자 목록
 * @author  kim21017
 * @version $Id: MaMailUsrListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maMailUsrListForm"
 */
public class MaMailUsrListForm extends BaseForm
{    
    //===============================================================
    /** 메일수신자설정 공통 */
    private MaMailCommonDTO maMailCommonDTO = new MaMailCommonDTO();
    /** 메일수신자설정-수신자  */
    private MaMailUsrListDTO maMailUsrListDTO = new MaMailUsrListDTO();
    
	public MaMailCommonDTO getMaMailCommonDTO() {
		return maMailCommonDTO;
	}

	public void setMaMailCommonDTO(MaMailCommonDTO maMailCommonDTO) {
		this.maMailCommonDTO = maMailCommonDTO;
	}

	public MaMailUsrListDTO getMaMailUsrListDTO() {
		return maMailUsrListDTO;
	}

	public void setMaMailUsrListDTO(MaMailUsrListDTO maMailUsrListDTO) {
		this.maMailUsrListDTO = maMailUsrListDTO;
	}
}
