package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDetailDTO;

/**
 * 근무일달력설정- 상세 Form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDetailForm.java,v 1.0 2015/12/02 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalDetailForm"
 */
public class MgrCalCompWkrcalDetailForm extends BaseForm
{
    //========================================================================
    /** 회사설정 공통 */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();
    //========================================================================
    /** 회사설정 상세 */
    private MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO = new MgrCalCompWkrcalDetailDTO();


	public MgrCalCompWkrcalCommonDTO getMgrCalCompWkrcalCommonDTO() {
		return mgrCalCompWkrcalCommonDTO;
	}

	public void setMgrCalCompWkrcalCommonDTO(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO) {
		this.mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalCommonDTO;
	}

	public MgrCalCompWkrcalDetailDTO getMgrCalCompWkrcalDetailDTO() {
		return mgrCalCompWkrcalDetailDTO;
	}

	public void setMgrCalCompWkrcalDetailDTO(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO) {
		this.mgrCalCompWkrcalDetailDTO = mgrCalCompWkrcalDetailDTO;
	}

}
