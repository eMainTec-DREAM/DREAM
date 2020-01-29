package dream.consult.comp.cdsys.form;

import common.struts.BaseForm;
import dream.consult.comp.cdsys.dto.MaCdSysCodeDetailDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 - 분류 
 * @author  kim2107
 * @version $Id: MaCdSysCodeDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maCdSysCodeDetailForm"
 */
public class MaCdSysCodeDetailForm extends BaseForm
{    
    //===============================================================
    /** 시스템 코드 - 공통 DTO */
    private MaCdSysCommonDTO maCdSysCommonDTO = new MaCdSysCommonDTO();
	/** 시스템 코드 - 분류 DTO  */
    private MaCdSysCodeListDTO maCdSysCodeListDTO = new MaCdSysCodeListDTO();
	/** 시스템 코드 - 분류Detail DTO  */
    private MaCdSysCodeDetailDTO maCdSysCodeDetailDTO = new MaCdSysCodeDetailDTO();
    
	public MaCdSysCodeListDTO getMaCdSysCodeListDTO() {
		return maCdSysCodeListDTO;
	}
	public void setMaCdSysCodeListDTO(MaCdSysCodeListDTO maCdSysCodeListDTO) {
		this.maCdSysCodeListDTO = maCdSysCodeListDTO;
	}
	public MaCdSysCodeDetailDTO getMaCdSysCodeDetailDTO() {
		return maCdSysCodeDetailDTO;
	}
	public void setMaCdSysCodeDetailDTO(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO) {
		this.maCdSysCodeDetailDTO = maCdSysCodeDetailDTO;
	}
	public MaCdSysCommonDTO getMaCdSysCommonDTO() {
		return maCdSysCommonDTO;
	}
	public void setMaCdSysCommonDTO(MaCdSysCommonDTO maCdSysCommonDTO) {
		this.maCdSysCommonDTO = maCdSysCommonDTO;
	}
	
}
