package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;

/**
 * 가동달력설정 - 목록
 * @author  euna0207
 * @version $Id: MgrCalLineTimeSetForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalLineTimeSetForm"
 */
public class MgrCalLineTimeSetForm extends BaseForm
{    
    //===============================================================
	/** 가동시간설정 공통 */ 
    private MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = new MgrCalLineTimeSetDTO();

	public MgrCalLineTimeSetDTO getMgrCalLineTimeSetDTO() {
		return mgrCalLineTimeSetDTO;
	}

	public void setMgrCalLineTimeSetDTO(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO) {
		this.mgrCalLineTimeSetDTO = mgrCalLineTimeSetDTO;
	}

}
