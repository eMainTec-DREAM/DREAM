package dream.consult.comp.cdsys.form;

import common.struts.BaseForm;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.dto.MaCdSysDetailDTO;

/**
 * 시스템코드- 상세 Form
 * @author  kim21017
 * @version $Id: MaCdSysDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maCdSysDetailForm"
 */
public class MaCdSysDetailForm extends BaseForm
{
    //========================================================================
    /** 시스템코드 공통 */ 
    private MaCdSysCommonDTO maCdSysCommonDTO = new MaCdSysCommonDTO();
    //========================================================================
    /** 시스템코드 상세 */
    private MaCdSysDetailDTO maCdSysDetailDTO = new MaCdSysDetailDTO();
    
	public MaCdSysCommonDTO getMaCdSysCommonDTO() {
		return maCdSysCommonDTO;
	}

	public void setMaCdSysCommonDTO(MaCdSysCommonDTO maCdSysCommonDTO) {
		this.maCdSysCommonDTO = maCdSysCommonDTO;
	}

	public MaCdSysDetailDTO getMaCdSysDetailDTO() {
		return maCdSysDetailDTO;
	}

	public void setMaCdSysDetailDTO(MaCdSysDetailDTO maCdSysDetailDTO) {
		this.maCdSysDetailDTO = maCdSysDetailDTO;
	}
}