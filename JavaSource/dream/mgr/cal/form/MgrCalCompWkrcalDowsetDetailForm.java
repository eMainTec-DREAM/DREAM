package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetDetailDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;

/**
 * �޹�����- �� Form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetDetailForm.java,v 1.0 2015/12/02 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalDowsetDetailForm"
 */
public class MgrCalCompWkrcalDowsetDetailForm extends BaseForm
{
    //========================================================================
    /** �ٹ��ϴ޷� ���� */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();
    //========================================================================
    /** �޹����� ��� */
    private MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO = new MgrCalCompWkrcalDowsetListDTO();
    /** �޹����� �� */
    private MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO = new MgrCalCompWkrcalDowsetDetailDTO();


	public MgrCalCompWkrcalCommonDTO getMgrCalCompWkrcalCommonDTO() {
		return mgrCalCompWkrcalCommonDTO;
	}

	public void setMgrCalCompWkrcalCommonDTO(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO) {
		this.mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalCommonDTO;
	}

	public MgrCalCompWkrcalDowsetDetailDTO getMgrCalCompWkrcalDowsetDetailDTO() {
		return mgrCalCompWkrcalDowsetDetailDTO;
	}

	public void setMgrCalCompWkrcalDowsetDetailDTO(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO) {
		this.mgrCalCompWkrcalDowsetDetailDTO = mgrCalCompWkrcalDowsetDetailDTO;
	}

	public MgrCalCompWkrcalDowsetListDTO getMgrCalCompWkrcalDowsetListDTO() {
		return mgrCalCompWkrcalDowsetListDTO;
	}

	public void setMgrCalCompWkrcalDowsetListDTO(MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO) {
		this.mgrCalCompWkrcalDowsetListDTO = mgrCalCompWkrcalDowsetListDTO;
	}

}
