package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * 목록
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptParamListForm"
 */
public class MaUserRptParamListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
    
    public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}
	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}
    
}
