package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;

/**
 * ȸ�缳�� - ��� form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalListForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalListForm"
 */
public class MgrCalCompWkrcalListForm extends BaseForm
{
    //===============================================================
    /** ȸ�缳�� ���� */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();

	public MgrCalCompWkrcalCommonDTO getMgrCalCompWkrcalCommonDTO() {
		return mgrCalCompWkrcalCommonDTO;
	}

	public void setMgrCalCompWkrcalCommonDTO(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO) {
		this.mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalCommonDTO;
	}

}
