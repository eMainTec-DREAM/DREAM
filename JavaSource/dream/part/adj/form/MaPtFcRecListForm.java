package dream.part.adj.form;

import common.struts.BaseForm;
import dream.part.adj.dto.MaPtFcRecCommonDTO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;

/**
 * �����԰� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtFcRecListForm"
 */
public class MaPtFcRecListForm extends BaseForm
{    
    //===============================================================
    /** �����԰� ���� */
    private MaPtFcRecCommonDTO maPtFcRecCommonDTO = new MaPtFcRecCommonDTO();
    /** �����԰� �� */
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
