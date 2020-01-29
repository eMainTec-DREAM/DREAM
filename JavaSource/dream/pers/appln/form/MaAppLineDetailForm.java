package dream.pers.appln.form;

import common.struts.BaseForm;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineDetailDTO;

/**
 * 상세 Form
 * @author  kim21017
 * @version $Id: MaAppLineDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maAppLineDetailForm"
 */
public class MaAppLineDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private MaAppLineCommonDTO maAppLineCommonDTO = new MaAppLineCommonDTO();
    //========================================================================
    /** 상세 */
    private MaAppLineDetailDTO maAppLineDetailDTO = new MaAppLineDetailDTO();


	public MaAppLineCommonDTO getMaAppLineCommonDTO() {
		return maAppLineCommonDTO;
	}

	public void setMaAppLineCommonDTO(MaAppLineCommonDTO maAppLineCommonDTO) {
		this.maAppLineCommonDTO = maAppLineCommonDTO;
	}

	public MaAppLineDetailDTO getMaAppLineDetailDTO() {
		return maAppLineDetailDTO;
	}

	public void setMaAppLineDetailDTO(MaAppLineDetailDTO maAppLineDetailDTO) {
		this.maAppLineDetailDTO = maAppLineDetailDTO;
	}
}