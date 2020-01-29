package dream.pers.appln.form;

import common.struts.BaseForm;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrDetailDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 * 
 * @author  kim2107
 * @version $Id: MaAppLineUsrDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maAppLineUsrDetailForm"
 */
public class MaAppLineUsrDetailForm extends BaseForm
{    
    //===============================================================
    /**  - °øÅë DTO */
    private MaAppLineCommonDTO maAppLineCommonDTO = new MaAppLineCommonDTO();
	/**   DTO  */
    private MaAppLineUsrListDTO maAppLineUsrListDTO = new MaAppLineUsrListDTO();
	/**   Detail DTO  */
    private MaAppLineUsrDetailDTO maAppLineUsrDetailDTO = new MaAppLineUsrDetailDTO();

	public MaAppLineUsrListDTO getMaAppLineUsrListDTO() {
		return maAppLineUsrListDTO;
	}
	public void setMaAppLineUsrListDTO(MaAppLineUsrListDTO maAppLineUsrListDTO) {
		this.maAppLineUsrListDTO = maAppLineUsrListDTO;
	}
	public MaAppLineUsrDetailDTO getMaAppLineUsrDetailDTO() {
		return maAppLineUsrDetailDTO;
	}
	public void setMaAppLineUsrDetailDTO(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO) {
		this.maAppLineUsrDetailDTO = maAppLineUsrDetailDTO;
	}
	public MaAppLineCommonDTO getMaAppLineCommonDTO() {
		return maAppLineCommonDTO;
	}
	public void setMaAppLineCommonDTO(MaAppLineCommonDTO maAppLineCommonDTO) {
		this.maAppLineCommonDTO = maAppLineCommonDTO;
	}
	
}
