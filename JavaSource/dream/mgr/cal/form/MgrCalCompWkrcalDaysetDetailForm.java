package dream.mgr.cal.form;

import common.struts.BaseForm;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetDetailDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetListDTO;

/**
 * 휴무일 설정- 상세 Form
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDaysetDetailForm.java,v 1.0 2015/12/02 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrCalCompWkrcalDaysetDetailForm"
 */
public class MgrCalCompWkrcalDaysetDetailForm extends BaseForm
{
    //========================================================================
    /** 근무일달력 공통 */
    private MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = new MgrCalCompWkrcalCommonDTO();
    //========================================================================
    /** 휴무요일 목록 */
    private MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO = new MgrCalCompWkrcalDaysetListDTO();
    /** 휴무요일 상세 */
    private MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO = new MgrCalCompWkrcalDaysetDetailDTO();


	public MgrCalCompWkrcalCommonDTO getMgrCalCompWkrcalCommonDTO() {
		return mgrCalCompWkrcalCommonDTO;
	}

	public void setMgrCalCompWkrcalCommonDTO(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO) {
		this.mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalCommonDTO;
	}

	public MgrCalCompWkrcalDaysetDetailDTO getMgrCalCompWkrcalDaysetDetailDTO() {
		return mgrCalCompWkrcalDaysetDetailDTO;
	}

	public void setMgrCalCompWkrcalDaysetDetailDTO(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO) {
		this.mgrCalCompWkrcalDaysetDetailDTO = mgrCalCompWkrcalDaysetDetailDTO;
	}

	public MgrCalCompWkrcalDaysetListDTO getMgrCalCompWkrcalDaysetListDTO() {
		return mgrCalCompWkrcalDaysetListDTO;
	}

	public void setMgrCalCompWkrcalDaysetListDTO(MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO) {
		this.mgrCalCompWkrcalDaysetListDTO = mgrCalCompWkrcalDaysetListDTO;
	}

}
