package dream.mgr.lang.form;

import common.struts.BaseForm;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * �����۾� - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="mgrLangPgBtnForm"
 */
public class MgrLangPgBtnForm extends BaseForm
{    
    private MaResCommonDTO maResCommonDTO = new MaResCommonDTO();
	public MaResCommonDTO getMaResCommonDTO() {
		return maResCommonDTO;
	}
	public void setMaResCommonDTO(MaResCommonDTO maResCommonDTO) {
		this.maResCommonDTO = maResCommonDTO;
	}
}
