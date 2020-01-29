package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetDetailDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;

/**
 * 휴무요일- 상세 Form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetDetailForm.java,v 1.0 2015/12/02 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalDowsetDetailForm"
 */
public class MgrCalCompWkrcalDowsetDetailForm extends BaseForm
{
    //========================================================================
    /** 근무일달력 공통 */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();
    //========================================================================
    /** 휴무요일 목록 */
    private MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO = new MgrCalCompWkrcalDowsetListDTO();
    /** 휴무요일 상세 */
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
