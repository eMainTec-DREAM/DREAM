package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * ��ǰ���� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepListForm"
 */
public class MaPtRepListForm extends BaseForm
{    
    //===============================================================
    /** ��ǰ���� ���� */
    private MaPtRepCommonDTO maPtRepCommonDTO = new MaPtRepCommonDTO();
    /** ��ǰ���� �� */
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
