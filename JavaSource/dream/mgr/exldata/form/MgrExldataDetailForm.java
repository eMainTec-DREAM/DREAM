package dream.mgr.exldata.form;

import common.struts.BaseForm;
import dream.mgr.exldata.dto.MgrExldataCommonDTO;
import dream.mgr.exldata.dto.MgrExldataDetailDTO;

/**
 * Excel Data Upload - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrExldataDetailForm"
 */
public class MgrExldataDetailForm extends BaseForm
{
	private MgrExldataCommonDTO mgrExldataCommonDTO = new MgrExldataCommonDTO();
	private MgrExldataDetailDTO mgrExldataDetailDTO = new MgrExldataDetailDTO();
    
	public MgrExldataCommonDTO getMgrExldataCommonDTO() {
		return mgrExldataCommonDTO;
	}
	public void setMgrExldataCommonDTO(MgrExldataCommonDTO mgrExldataCommonDTO) {
		this.mgrExldataCommonDTO = mgrExldataCommonDTO;
	}
	public MgrExldataDetailDTO getMgrExldataDetailDTO() {
		return mgrExldataDetailDTO;
	}
	public void setMgrExldataDetailDTO(MgrExldataDetailDTO mgrExldataDetailDTO) {
		this.mgrExldataDetailDTO = mgrExldataDetailDTO;
	}
}
