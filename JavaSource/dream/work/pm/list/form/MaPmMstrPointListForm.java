package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;

/**
 * 검사항목 목록
 * @author  jung7126
 * @version $Id: MaPmMstrPointListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmMstrPointListForm"
 */
public class MaPmMstrPointListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPmMstrCommonDTO maPmMstrMstrCommonDTO = new MaPmMstrCommonDTO();
    private MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = new MaPmMstrPointDetailDTO();

	public MaPmMstrCommonDTO getMaPmMstrMstrCommonDTO() {
		return maPmMstrMstrCommonDTO;
	}

	public void setMaPmMstrMstrCommonDTO(MaPmMstrCommonDTO maPmMstrMstrCommonDTO) {
		this.maPmMstrMstrCommonDTO = maPmMstrMstrCommonDTO;
	}

	public MaPmMstrPointDetailDTO getMaPmMstrPointDetailDTO() {
		return maPmMstrPointDetailDTO;
	}

	public void setMaPmMstrPointDetailDTO(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO) {
		this.maPmMstrPointDetailDTO = maPmMstrPointDetailDTO;
	}

	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrMstrCommonDTO;
	}

	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrMstrCommonDTO) {
		this.maPmMstrMstrCommonDTO = maPmMstrMstrCommonDTO;
	}
}
