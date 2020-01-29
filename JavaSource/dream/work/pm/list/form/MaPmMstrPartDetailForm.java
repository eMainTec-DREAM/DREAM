package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;

/**
 * 사용자재
 * @author  jung7126
 * @version $Id: MaPmMstrPartDetailForm.java,v 1.0 2015/12/04 09:09:54 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmMstrPartDetailForm"
 */
public class MaPmMstrPartDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPmMstrCommonDTO maPmMstrMstrCommonDTO = new MaPmMstrCommonDTO();
	/** 사용자재 투입자재 상세 DTO  */
    private MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = new MaPmMstrPartDetailDTO();

	public MaPmMstrPartDetailDTO getMaPmMstrPartDetailDTO() {
		return maPmMstrPartDetailDTO;
	}
	public void setMaPmMstrPartDetailDTO(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO) {
		this.maPmMstrPartDetailDTO = maPmMstrPartDetailDTO;
	}
	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrMstrCommonDTO;
	}
	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrMstrCommonDTO) {
		this.maPmMstrMstrCommonDTO = maPmMstrMstrCommonDTO;
	}
}
