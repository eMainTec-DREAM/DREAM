package dream.pers.priv.info.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;

/**
 * 상세 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="persPrivInfoMsgEmpDetailForm"
 */
public class PersPrivInfoMsgEmpDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();

	/** 상세 DTO  */
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
