package dream.mgr.lang.form;

import common.struts.BaseForm;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * 언어 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maResListForm"
 */
public class MaResListForm extends BaseForm
{    
    //===============================================================
    /** 언어 공통 */
    private MaResCommonDTO maResCommonDTO = new MaResCommonDTO();

	public MaResCommonDTO getMaResCommonDTO() {
		return maResCommonDTO;
	}

	public void setMaResCommonDTO(MaResCommonDTO maResCommonDTO) {
		this.maResCommonDTO = maResCommonDTO;
	}
}
