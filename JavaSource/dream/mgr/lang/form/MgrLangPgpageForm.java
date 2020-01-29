package dream.mgr.lang.form;

import common.struts.BaseForm;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * 일일작업 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="mgrLangPgpageForm"
 */
public class MgrLangPgpageForm extends BaseForm
{    
    private MaResCommonDTO maResCommonDTO = new MaResCommonDTO();
	public MaResCommonDTO getMaResCommonDTO() {
		return maResCommonDTO;
	}
	public void setMaResCommonDTO(MaResCommonDTO maResCommonDTO) {
		this.maResCommonDTO = maResCommonDTO;
	}
}
