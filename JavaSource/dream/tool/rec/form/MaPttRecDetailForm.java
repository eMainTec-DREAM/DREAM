package dream.tool.rec.form;

import common.struts.BaseForm;
import dream.tool.rec.dto.MaPttRecCommonDTO;
import dream.tool.rec.dto.MaPttRecDetailDTO;

/**
 * �����԰�- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttRecDetailForm"
 */
public class MaPttRecDetailForm extends BaseForm
{
    //========================================================================
    /** �����԰� ���� */ 
    private MaPttRecCommonDTO maPttRecCommonDTO = new MaPttRecCommonDTO();
    //========================================================================
    /** �����԰� �� */
    private MaPttRecDetailDTO maPttRecDetailDTO = new MaPttRecDetailDTO();

    public MaPttRecCommonDTO getMaPttRecCommonDTO() {
		return maPttRecCommonDTO;
	}

    public void setMaPttRecCommonDTO(MaPttRecCommonDTO maPttRecCommonDTO) {
		this.maPttRecCommonDTO = maPttRecCommonDTO;
	}

	public MaPttRecDetailDTO getMaPttRecDetailDTO() {
		return maPttRecDetailDTO;
	}

	public void setMaPttRecDetailDTO(MaPttRecDetailDTO maPttRecDetailDTO) {
		this.maPttRecDetailDTO = maPttRecDetailDTO;
	}
}
