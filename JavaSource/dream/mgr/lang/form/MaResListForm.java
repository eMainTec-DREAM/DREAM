package dream.mgr.lang.form;

import common.struts.BaseForm;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * ��� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maResListForm"
 */
public class MaResListForm extends BaseForm
{    
    //===============================================================
    /** ��� ���� */
    private MaResCommonDTO maResCommonDTO = new MaResCommonDTO();

	public MaResCommonDTO getMaResCommonDTO() {
		return maResCommonDTO;
	}

	public void setMaResCommonDTO(MaResCommonDTO maResCommonDTO) {
		this.maResCommonDTO = maResCommonDTO;
	}
}
