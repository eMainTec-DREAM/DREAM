package dream.pers.priv.info.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * ������ form
 * @author  kim21017
 * @version $Id: MaMyInfoForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maMyInfoForm"
 */
public class MaMyInfoForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();

	public MaMyInfoCommonDTO getMaMyInfoCommonDTO() {
		return maMyInfoCommonDTO;
	}

	public void setMaMyInfoCommonDTO(MaMyInfoCommonDTO maMyInfoCommonDTO) {
		this.maMyInfoCommonDTO = maMyInfoCommonDTO;
	}
}
