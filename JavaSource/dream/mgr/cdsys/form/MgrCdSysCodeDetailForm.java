package dream.mgr.cdsys.form;

import common.struts.BaseForm;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * �ý����ڵ� - �з� 
 * @author  kim2107
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrCdSysCodeDetailForm"
 */
public class MgrCdSysCodeDetailForm extends BaseForm
{    
    //===============================================================
    /** �ý��� �ڵ� - ���� DTO */
    private MgrCdSysCommonDTO mgrCdSysCommonDTO = new MgrCdSysCommonDTO();
	/** �ý��� �ڵ� - �з� DTO  */
    private MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
	/** �ý��� �ڵ� - �з�Detail DTO  */
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
