package dream.mgr.exldata.form;

import common.struts.BaseForm;
import dream.mgr.exldata.dto.MgrExldataCommonDTO;
/**
 * Excel Data Upload - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrExldataListForm"
 * */

public class MgrExldataListForm extends BaseForm{
	
	private MgrExldataCommonDTO mgrExldataCommonDTO = new MgrExldataCommonDTO();

	public MgrExldataCommonDTO getMgrExldataCommonDTO() {
		return mgrExldataCommonDTO;
	}

	public void setMgrExldataCommonDTO(MgrExldataCommonDTO mgrExldataCommonDTO) {
		this.mgrExldataCommonDTO = mgrExldataCommonDTO;
	}
	
}
