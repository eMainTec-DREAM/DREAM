package dream.mgr.mail.form;

import common.struts.BaseForm;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailDetailDTO;

/**
 * 메일수신자설정- 상세 Form
 * @author  kim21017
 * @version $Id: MaMailDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maMailDetailForm"
 */
public class MaMailDetailForm extends BaseForm
{
    //========================================================================
    /** 메일수신자설정 공통 */ 
    private MaMailCommonDTO maMailCommonDTO = new MaMailCommonDTO();
    //========================================================================
    /** 메일수신자설정 상세 */
    private MaMailDetailDTO maMailDetailDTO = new MaMailDetailDTO();
    
	public MaMailCommonDTO getMaMailCommonDTO() {
		return maMailCommonDTO;
	}

	public void setMaMailCommonDTO(MaMailCommonDTO maMailCommonDTO) {
		this.maMailCommonDTO = maMailCommonDTO;
	}

	public MaMailDetailDTO getMaMailDetailDTO() {
		return maMailDetailDTO;
	}

	public void setMaMailDetailDTO(MaMailDetailDTO maMailDetailDTO) {
		this.maMailDetailDTO = maMailDetailDTO;
	}
}