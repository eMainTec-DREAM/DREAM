package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * 何前荐府 - 格废 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepListForm"
 */
public class MaPtRepListForm extends BaseForm
{    
    //===============================================================
    /** 何前荐府 傍烹 */
    private MaPtRepCommonDTO maPtRepCommonDTO = new MaPtRepCommonDTO();
    /** 何前荐府 惑技 */
    private MaPtRepDetailDTO maPtRepDetailDTO = new MaPtRepDetailDTO();
    
	public MaPtRepDetailDTO getMaPtRepDetailDTO()
    {
        return maPtRepDetailDTO;
    }

    public void setMaPtRepDetailDTO(MaPtRepDetailDTO maPtRepDetailDTO)
    {
        this.maPtRepDetailDTO = maPtRepDetailDTO;
    }

    public MaPtRepCommonDTO getMaPtRepCommonDTO() {
		return maPtRepCommonDTO;
	}

	public void setMaPtRepCommonDTO(MaPtRepCommonDTO maPtRepCommonDTO) {
		this.maPtRepCommonDTO = maPtRepCommonDTO;
	}
}
