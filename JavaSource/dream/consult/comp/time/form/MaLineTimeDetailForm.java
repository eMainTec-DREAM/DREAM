package dream.consult.comp.time.form;

import common.struts.BaseForm;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDetailDTO;

/**
 * �����ð�����- �� Form
 * @author  kim21017
 * @version $Id: MaLineTimeDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maLineTimeDetailForm"
 */
public class MaLineTimeDetailForm extends BaseForm
{
    //========================================================================
    /** �����ð����� ���� */ 
    private MaLineTimeCommonDTO maLineTimeCommonDTO = new MaLineTimeCommonDTO();
    //========================================================================
    /** �����ð����� �� */
    private MaLineTimeDetailDTO maLineTimeDetailDTO = new MaLineTimeDetailDTO();
    

	public MaLineTimeCommonDTO getMaLineTimeCommonDTO() {
		return maLineTimeCommonDTO;
	}

	public void setMaLineTimeCommonDTO(MaLineTimeCommonDTO maLineTimeCommonDTO) {
		this.maLineTimeCommonDTO = maLineTimeCommonDTO;
	}

	public MaLineTimeDetailDTO getMaLineTimeDetailDTO() {
		return maLineTimeDetailDTO;
	}

	public void setMaLineTimeDetailDTO(MaLineTimeDetailDTO maLineTimeDetailDTO) {
		this.maLineTimeDetailDTO = maLineTimeDetailDTO;
	}

}
