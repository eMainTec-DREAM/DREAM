package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;

/**
 * 사용자재 목록
 * @author  jung7126
 * @version $Id: MaPmMstrPartListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmMstrPartListForm"
 */
public class MaPmMstrPartListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPmMstrCommonDTO maPmMstrMstrCommonDTO = new MaPmMstrCommonDTO();
    /** 사용자재 투입자재 상세 DTO  */
    private MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = new MaPmMstrPartDetailDTO();


	public MaPmMstrCommonDTO getMaPmMstrMstrCommonDTO()
    {
        return maPmMstrMstrCommonDTO;
    }

    public void setMaPmMstrMstrCommonDTO(MaPmMstrCommonDTO maPmMstrMstrCommonDTO)
    {
        this.maPmMstrMstrCommonDTO = maPmMstrMstrCommonDTO;
    }

    public MaPmMstrPartDetailDTO getMaPmMstrPartDetailDTO()
    {
        return maPmMstrPartDetailDTO;
    }

    public void setMaPmMstrPartDetailDTO(
            MaPmMstrPartDetailDTO maPmMstrPartDetailDTO)
    {
        this.maPmMstrPartDetailDTO = maPmMstrPartDetailDTO;
    }

    public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrMstrCommonDTO;
	}

	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrMstrCommonDTO) {
		this.maPmMstrMstrCommonDTO = maPmMstrMstrCommonDTO;
	}
}
