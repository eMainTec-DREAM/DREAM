package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;


/**
 * �� Form
 * @author  kim21017
 * @version $Id: MaUserRptDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maUserRptDetailForm"
 */
public class MaUserRptDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
    //========================================================================
    /** �� */
    private MaUserRptDetailDTO maUserRptDetailDTO = new MaUserRptDetailDTO();
    
	public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}

	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}

	public MaUserRptDetailDTO getMaUserRptDetailDTO() {
		return maUserRptDetailDTO;
	}

	public void setMaUserRptDetailDTO(MaUserRptDetailDTO maUserRptDetailDTO) {
		this.maUserRptDetailDTO = maUserRptDetailDTO;
	}

}
