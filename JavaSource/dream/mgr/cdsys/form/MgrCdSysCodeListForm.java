package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysCodeListForm"
 */
public class MgrCdSysCodeListForm extends BaseForm
{    
    //===============================================================
    /** 시스템코드 공통 */
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
    /** 시스템코드 detail-code  */
    private MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
    
	public MgrCdSysCommonDTO getMgrCdSysCommonDTO() {
		return mgrCdSysCommonDTO;
	}

	public void setMgrCdSysCommonDTO(MgrCdSysCommonDTO mgrCdSysCommonDTO) {
		this.mgrCdSysCommonDTO = mgrCdSysCommonDTO;
	}

	public MgrCdSysCodeListDTO getMgrCdSysCodeListDTO() {
		return mgrCdSysCodeListDTO;
	}

	public void setMgrCdSysCodeListDTO(MgrCdSysCodeListDTO mgrCdSysCodeListDTO) {
		this.mgrCdSysCodeListDTO = mgrCdSysCodeListDTO;
	}
}
