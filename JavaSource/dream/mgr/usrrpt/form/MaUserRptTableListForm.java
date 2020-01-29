package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptTableListDTO;

/**
 * 목록
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptTableListForm"
 */
public class MaUserRptTableListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
    /** 목록  */
    private MaUserRptTableListDTO maUserRptTableListDTO = new MaUserRptTableListDTO();
	
    public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}
	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}
	public MaUserRptTableListDTO getMaUserRptTableListDTO() {
		return maUserRptTableListDTO;
	}
	public void setMaUserRptTableListDTO(MaUserRptTableListDTO maUserRptTableListDTO) {
		this.maUserRptTableListDTO = maUserRptTableListDTO;
	}
    
}
