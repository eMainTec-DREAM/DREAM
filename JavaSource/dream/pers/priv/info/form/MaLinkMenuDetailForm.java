package dream.pers.priv.info.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaLinkMenuDetailDTO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * 상세 
 * @author  jung7126
 * @version $Id: MaLinkMenuDetailForm.java,v 1.0 2015/12/04 09:09:54 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maLinkMenuDetailForm"
 */
public class MaLinkMenuDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();

	/** 상세 DTO  */
    private MaLinkMenuDetailDTO maLinkMenuDetailDTO = new MaLinkMenuDetailDTO();

	public MaLinkMenuDetailDTO getMaLinkMenuDetailDTO() {
		return maLinkMenuDetailDTO;
	}
	public void setMaLinkMenuDetailDTO(MaLinkMenuDetailDTO maLinkMenuDetailDTO) {
		this.maLinkMenuDetailDTO = maLinkMenuDetailDTO;
	}
	public MaMyInfoCommonDTO getMaMyInfoCommonDTO() {
		return maMyInfoCommonDTO;
	}
	public void setMaMyInfoCommonDTO(MaMyInfoCommonDTO maMyInfoCommonDTO) {
		this.maMyInfoCommonDTO = maMyInfoCommonDTO;
	}
}
