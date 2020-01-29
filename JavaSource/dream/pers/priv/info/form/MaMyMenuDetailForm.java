package dream.pers.priv.info.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.MaMyMenuDetailDTO;

/**
 * �� 
 * @author  jung7126
 * @version $Id: MaMyMenuDetailForm.java,v 1.0 2015/12/04 09:09:54 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maMyMenuDetailForm"
 */
public class MaMyMenuDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();

	/** �� DTO  */
    private MaMyMenuDetailDTO maMyMenuDetailDTO = new MaMyMenuDetailDTO();

	public MaMyMenuDetailDTO getMaMyMenuDetailDTO() {
		return maMyMenuDetailDTO;
	}
	public void setMaMyMenuDetailDTO(MaMyMenuDetailDTO maMyMenuDetailDTO) {
		this.maMyMenuDetailDTO = maMyMenuDetailDTO;
	}
	public MaMyInfoCommonDTO getMaMyInfoCommonDTO() {
		return maMyInfoCommonDTO;
	}
	public void setMaMyInfoCommonDTO(MaMyInfoCommonDTO maMyInfoCommonDTO) {
		this.maMyInfoCommonDTO = maMyInfoCommonDTO;
	}
}
