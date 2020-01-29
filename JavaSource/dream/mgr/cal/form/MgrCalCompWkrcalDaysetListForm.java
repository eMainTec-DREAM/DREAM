package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetListDTO;

/**
 * 휴무일 설정 - 목록 form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDaysetListForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalDaysetListForm"
 */
public class MgrCalCompWkrcalDaysetListForm extends BaseForm
{
    //===============================================================
    /** 회사설정 공통 */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();
    /** 휴무요일 목록 */
    private MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO = new MgrCalCompWkrcalDaysetListDTO();

	public MgrCalCompWkrcalCommonDTO getMgrCalCompWkrcalCommonDTO() {
		return mgrCalCompWkrcalCommonDTO;
	}

	public void setMgrCalCompWkrcalCommonDTO(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO) {
		this.mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalCommonDTO;
	}

	public MgrCalCompWkrcalDaysetListDTO getMgrCalCompWkrcalDaysetListDTO() {
		return mgrCalCompWkrcalDaysetListDTO;
	}

	public void setMgrCalCompWkrcalDaysetListDTO(MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO) {
		this.mgrCalCompWkrcalDaysetListDTO = mgrCalCompWkrcalDaysetListDTO;
	}

}
