package dream.part.rec.form;

import common.struts.BaseForm;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecDetailDTO;

/**
 * 구매입고 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRecListForm"
 */
public class MaPtRecListForm extends BaseForm
{    
    //===============================================================
    /** 구매입고 공통 */
    private MaPtRecCommonDTO maPtRecCommonDTO = new MaPtRecCommonDTO();
    /** 구매입고 상세 */
    private MaPtRecDetailDTO maPtRecDetailDTO = new MaPtRecDetailDTO();
    
	public MaPtRecDetailDTO getMaPtRecDetailDTO()
    {
        return maPtRecDetailDTO;
    }

    public void setMaPtRecDetailDTO(MaPtRecDetailDTO maPtRecDetailDTO)
    {
        this.maPtRecDetailDTO = maPtRecDetailDTO;
    }

    public MaPtRecCommonDTO getMaPtRecCommonDTO() {
		return maPtRecCommonDTO;
	}

	public void setMaPtRecCommonDTO(MaPtRecCommonDTO maPtRecCommonDTO) {
		this.maPtRecCommonDTO = maPtRecCommonDTO;
	}
}
