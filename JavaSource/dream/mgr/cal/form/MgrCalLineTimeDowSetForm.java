package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalLineTimeDowSetDTO;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;

/**
 * ���Ϻ� ���� ���
 * @author  euna0207
 * @version $Id: MgrCalLineTimeDowSetForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalLineTimeDowSetForm"
 */
public class MgrCalLineTimeDowSetForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = new MgrCalLineTimeSetDTO();
    /** ���Ϻ� ���� ���  */
    private MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = new MgrCalLineTimeDowSetDTO();
	

	public MgrCalLineTimeSetDTO getMgrCalLineTimeSetDTO() {
		return mgrCalLineTimeSetDTO;
	}

	public void setMgrCalLineTimeSetDTO(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO) {
		this.mgrCalLineTimeSetDTO = mgrCalLineTimeSetDTO;
	}

	public MgrCalLineTimeDowSetDTO getMgrCalLineTimeDowSetDTO() {
		return mgrCalLineTimeDowSetDTO;
	}

	public void setMgrCalLineTimeDowSetDTO(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO) {
		this.mgrCalLineTimeDowSetDTO = mgrCalLineTimeDowSetDTO;
	}
}
