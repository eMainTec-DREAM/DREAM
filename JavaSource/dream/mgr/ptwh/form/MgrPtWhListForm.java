package dream.mgr.ptwh.form;

import common.struts.BaseForm;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
/**
 * 부품창고 - List Form
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts.form name="mgrPtWhListForm"
 * */

public class MgrPtWhListForm extends BaseForm{
	
	private MgrPtWhCommonDTO mgrPtWhCommonDTO = new MgrPtWhCommonDTO();

	public MgrPtWhCommonDTO getMgrPtWhCommonDTO() {
		return mgrPtWhCommonDTO;
	}

	public void setMgrPtWhCommonDTO(MgrPtWhCommonDTO mgrPtWhCommonDTO) {
		this.mgrPtWhCommonDTO = mgrPtWhCommonDTO;
	}
	
}
