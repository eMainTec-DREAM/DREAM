package dream.pers.appln.form;

import common.struts.BaseForm;
import dream.pers.appln.dto.MaAppLineCommonDTO;

/**
 * 목록 form
 * @author  kim21017
 * @version $Id: MaAppLineListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maAppLineListForm"
 */
public class MaAppLineListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private MaAppLineCommonDTO maAppLineCommonDTO = new MaAppLineCommonDTO();
    
	public MaAppLineCommonDTO getMaAppLineCommonDTO() {
		return maAppLineCommonDTO;
	}

	public void setMaAppLineCommonDTO(MaAppLineCommonDTO maAppLineCommonDTO) {
		this.maAppLineCommonDTO = maAppLineCommonDTO;
	}
}
