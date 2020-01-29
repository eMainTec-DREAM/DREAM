package dream.mgr.ptwh.form;

import common.struts.BaseForm;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhDetailDTO;

/**
 * 부품창고 - Detail Form
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts.form name="mgrPtWhDetailForm"
 */
public class MgrPtWhDetailForm extends BaseForm
{
	private MgrPtWhCommonDTO mgrPtWhCommonDTO = new MgrPtWhCommonDTO();
	private MgrPtWhDetailDTO mgrPtWhDetailDTO = new MgrPtWhDetailDTO();
    
	public MgrPtWhCommonDTO getMgrPtWhCommonDTO() {
		return mgrPtWhCommonDTO;
	}
	public void setMgrPtWhCommonDTO(MgrPtWhCommonDTO mgrPtWhCommonDTO) {
		this.mgrPtWhCommonDTO = mgrPtWhCommonDTO;
	}
	public MgrPtWhDetailDTO getMgrPtWhDetailDTO() {
		return mgrPtWhDetailDTO;
	}
	public void setMgrPtWhDetailDTO(MgrPtWhDetailDTO mgrPtWhDetailDTO) {
		this.mgrPtWhDetailDTO = mgrPtWhDetailDTO;
	}
}
