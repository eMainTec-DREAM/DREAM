package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * ���
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptOrdListForm"
 */
public class MaUserRptOrdListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
    
    public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}
	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}
    
}
