package dream.tool.iss.rent.form;

import common.struts.BaseForm;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;
import dream.tool.iss.rent.dto.MaPttIssDetailDTO;

/**
 * �����԰�- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttIssDetailForm"
 */
public class MaPttIssDetailForm extends BaseForm
{
    //========================================================================
    /** �����԰� ���� */ 
    private MaPttIssCommonDTO maPttIssCommonDTO = new MaPttIssCommonDTO();
    //========================================================================
    /** �����԰� �� */
    private MaPttIssDetailDTO maPttIssDetailDTO = new MaPttIssDetailDTO();

    public MaPttIssCommonDTO getMaPttIssCommonDTO() {
		return maPttIssCommonDTO;
	}

    public void setMaPttIssCommonDTO(MaPttIssCommonDTO maPttIssCommonDTO) {
		this.maPttIssCommonDTO = maPttIssCommonDTO;
	}

	public MaPttIssDetailDTO getMaPttIssDetailDTO() {
		return maPttIssDetailDTO;
	}

	public void setMaPttIssDetailDTO(MaPttIssDetailDTO maPttIssDetailDTO) {
		this.maPttIssDetailDTO = maPttIssDetailDTO;
	}
}
