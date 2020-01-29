package dream.mgr.mail.form;

import common.struts.BaseForm;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailDetailDTO;

/**
 * ���ϼ����ڼ���- �� Form
 * @author  kim21017
 * @version $Id: MaMailDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maMailDetailForm"
 */
public class MaMailDetailForm extends BaseForm
{
    //========================================================================
    /** ���ϼ����ڼ��� ���� */ 
    private MaMailCommonDTO maMailCommonDTO = new MaMailCommonDTO();
    //========================================================================
    /** ���ϼ����ڼ��� �� */
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