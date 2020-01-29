package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드 - 분류 
 * @author  kim2107
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysCodeDetailForm"
 */
public class MgrCdSysCodeDetailForm extends BaseForm
{    
    //===============================================================
    /** 시스템 코드 - 공통 DTO */
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
	/** 시스템 코드 - 분류 DTO  */
    private MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
	/** 시스템 코드 - 분류Detail DTO  */
    private MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO = new MgrCdSysCodeDetailDTO();
    
	public MgrCdSysCodeListDTO getMgrCdSysCodeListDTO() {
		return mgrCdSysCodeListDTO;
	}
	public void setMgrCdSysCodeListDTO(MgrCdSysCodeListDTO mgrCdSysCodeListDTO) {
		this.mgrCdSysCodeListDTO = mgrCdSysCodeListDTO;
	}
	public MgrCdSysCodeDetailDTO getMgrCdSysCodeDetailDTO() {
		return mgrCdSysCodeDetailDTO;
	}
	public void setMgrCdSysCodeDetailDTO(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO) {
		this.mgrCdSysCodeDetailDTO = mgrCdSysCodeDetailDTO;
	}
	public MgrCdSysCommonDTO getMgrCdSysCommonDTO() {
		return mgrCdSysCommonDTO;
	}
	public void setMgrCdSysCommonDTO(MgrCdSysCommonDTO mgrCdSysCommonDTO) {
		this.mgrCdSysCommonDTO = mgrCdSysCommonDTO;
	}
	
}
