package dream.part.adj.form;

import common.struts.BaseForm;
import dream.part.adj.dto.MaPtFcRecCommonDTO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;

/**
 * 무상입고 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtFcRecListForm"
 */
public class MaPtFcRecListForm extends BaseForm
{    
    //===============================================================
    /** 무상입고 공통 */
    private MaPtFcRecCommonDTO maPtFcRecCommonDTO = new MaPtFcRecCommonDTO();
    /** 무상입고 상세 */
    private MaPtFcRecDetailDTO maPtFcRecDetailDTO = new MaPtFcRecDetailDTO();

	public MaPtFcRecDetailDTO getMaPtFcRecDetailDTO()
    {
        return maPtFcRecDetailDTO;
    }

    public void setMaPtFcRecDetailDTO(MaPtFcRecDetailDTO maPtFcRecDetailDTO)
    {
        this.maPtFcRecDetailDTO = maPtFcRecDetailDTO;
    }

    public MaPtFcRecCommonDTO getMaPtFcRecCommonDTO() {
		return maPtFcRecCommonDTO;
	}

	public void setMaPtFcRecCommonDTO(MaPtFcRecCommonDTO maPtFcRecCommonDTO) {
		this.maPtFcRecCommonDTO = maPtFcRecCommonDTO;
	}
}
