package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;

/**
 * �޹����ϼ��� - ��� form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetListForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalDowsetListForm"
 */
public class MgrCalCompWkrcalDowsetListForm extends BaseForm
{
    //===============================================================
    /** ȸ�缳�� ���� */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();
    /** �޹����� ��� */
    private MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO = new MgrCalCompWkrcalDowsetListDTO();

	public MgrCalCompWkrcalCommonDTO getMgrCalCompWkrcalCommonDTO() {
		return mgrCalCompWkrcalCommonDTO;
	}

	public void setMgrCalCompWkrcalCommonDTO(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO) {
		this.mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalCommonDTO;
	}

	public MgrCalCompWkrcalDowsetListDTO getMgrCalCompWkrcalDowsetListDTO() {
		return mgrCalCompWkrcalDowsetListDTO;
	}

	public void setMgrCalCompWkrcalDowsetListDTO(MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO) {
		this.mgrCalCompWkrcalDowsetListDTO = mgrCalCompWkrcalDowsetListDTO;
	}

}
