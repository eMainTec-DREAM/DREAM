package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;

/**
 * 휴무요일설정 - 목록 form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetListForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalDowsetListForm"
 */
public class MgrCalCompWkrcalDowsetListForm extends BaseForm
{
    //===============================================================
    /** 회사설정 공통 */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();
    /** 휴무요일 목록 */
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
