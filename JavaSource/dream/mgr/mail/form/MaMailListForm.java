package dream.mgr.mail.form;

import common.struts.BaseForm;
import dream.mgr.mail.dto.MaMailCommonDTO;

/**
 * ���ϼ����ڼ��� - ��� form
 * @author  kim21017
 * @version $Id: MaMailListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maMailListForm"
 */
public class MaMailListForm extends BaseForm
{    
    //===============================================================
    /** ���ϼ����ڼ��� ���� */
    private MaMailCommonDTO maMailCommonDTO = new MaMailCommonDTO();
    
	public MaMailCommonDTO getMaMailCommonDTO() {
		return maMailCommonDTO;
	}

	public void setMaMailCommonDTO(MaMailCommonDTO maMailCommonDTO) {
		this.maMailCommonDTO = maMailCommonDTO;
	}
}
