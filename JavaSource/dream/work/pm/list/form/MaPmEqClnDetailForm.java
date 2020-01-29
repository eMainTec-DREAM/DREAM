package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmEqClnDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방설비 
 * @author  kim21017
 * @version $Id: MaPmEqClnDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmEqClnDetailForm"
 */
public class MaPmEqClnDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
	/** 사용자재 투입자재 상세 DTO  */
    private MaPmEqClnDetailDTO maPmEqClnDetailDTO = new MaPmEqClnDetailDTO();

	public MaPmEqClnDetailDTO getMaPmEqClnDetailDTO() {
		return maPmEqClnDetailDTO;
	}
	public void setMaPmEqClnDetailDTO(MaPmEqClnDetailDTO maPmEqClnDetailDTO) {
		this.maPmEqClnDetailDTO = maPmEqClnDetailDTO;
	}
	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}
	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
}
