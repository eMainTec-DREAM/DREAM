package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * ��� form
 * @author  kim21017
 * @version $Id: MaUserRptListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maUserRptPopupForm"
 */
public class MaUserRptPopupForm extends BaseForm
{    
    //===============================================================
    /** �޴� ���� */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();

	public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}

	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}

    
}
