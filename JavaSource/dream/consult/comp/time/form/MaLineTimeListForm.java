package dream.consult.comp.time.form;

import common.struts.BaseForm;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;

/**
 * �����ð����� - ���
 * @author  kim21017
 * @version $Id: MaLineTimeListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maLineTimeListForm"
 */
public class MaLineTimeListForm extends BaseForm
{    
    //===============================================================
    /** ������ġ ���� */
    private MaLineTimeCommonDTO maLineTimeCommonDTO = new MaLineTimeCommonDTO();

	public MaLineTimeCommonDTO getMaLineTimeCommonDTO() {
		return maLineTimeCommonDTO;
	}

	public void setMaLineTimeCommonDTO(MaLineTimeCommonDTO maLineTimeCommonDTO) {
		this.maLineTimeCommonDTO = maLineTimeCommonDTO;
	}

}
