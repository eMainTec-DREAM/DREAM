package dream.pers.priv.info.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;

/**
 * �� 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="persPrivInfoMsgEmpDetailForm"
 */
public class PersPrivInfoMsgEmpDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();

	/** �� DTO  */
    private PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO = new PersPrivInfoMsgEmpDetailDTO();

	public PersPrivInfoMsgEmpDetailDTO getPersPrivInfoMsgEmpDetailDTO() {
		return persPrivInfoMsgEmpDetailDTO;
	}
	public void setPersPrivInfoMsgEmpDetailDTO(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO) {
		this.persPrivInfoMsgEmpDetailDTO = persPrivInfoMsgEmpDetailDTO;
	}
	public MaMyInfoCommonDTO getMaMyInfoCommonDTO() {
		return maMyInfoCommonDTO;
	}
	public void setMaMyInfoCommonDTO(MaMyInfoCommonDTO maMyInfoCommonDTO) {
		this.maMyInfoCommonDTO = maMyInfoCommonDTO;
	}
}
