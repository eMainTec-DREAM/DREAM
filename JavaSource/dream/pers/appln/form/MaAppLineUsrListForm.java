package dream.pers.appln.form;

import common.struts.BaseForm;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 * 목록
 * @author  kim21017
 * @version $Id: MaAppLineUsrListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maAppLineUsrListForm"
 */
public class MaAppLineUsrListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaAppLineCommonDTO maAppLineCommonDTO = new MaAppLineCommonDTO();
    /**   */
    private MaAppLineUsrListDTO maAppLineUsrListDTO = new MaAppLineUsrListDTO();
    
	public MaAppLineCommonDTO getMaAppLineCommonDTO() {
		return maAppLineCommonDTO;
	}

	public void setMaAppLineCommonDTO(MaAppLineCommonDTO maAppLineCommonDTO) {
		this.maAppLineCommonDTO = maAppLineCommonDTO;
	}

	public MaAppLineUsrListDTO getMaAppLineUsrListDTO() {
		return maAppLineUsrListDTO;
	}

	public void setMaAppLineUsrListDTO(MaAppLineUsrListDTO maAppLineUsrListDTO) {
		this.maAppLineUsrListDTO = maAppLineUsrListDTO;
	}
}
