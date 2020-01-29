package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysFieldListDTO;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysFieldListForm"
 */
public class MgrCdSysFieldListForm extends BaseForm
{    
    //===============================================================
    /** 시스템코드 공통 */
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
    /** 시스템코드 detail-code  */
    private MgrCdSysFieldListDTO mgrCdSysFieldListDTO = new MgrCdSysFieldListDTO();


	public MgrCdSysCommonDTO getMgrCdSysCommonDTO() {
		return mgrCdSysCommonDTO;
	}

	public void setMgrCdSysCommonDTO(MgrCdSysCommonDTO mgrCdSysCommonDTO) {
		this.mgrCdSysCommonDTO = mgrCdSysCommonDTO;
	}

	public MgrCdSysFieldListDTO getMgrCdSysFieldListDTO() {
		return mgrCdSysFieldListDTO;
	}

	public void setMgrCdSysFieldListDTO(MgrCdSysFieldListDTO mgrCdSysFieldListDTO) {
		this.mgrCdSysFieldListDTO = mgrCdSysFieldListDTO;
	}
}
