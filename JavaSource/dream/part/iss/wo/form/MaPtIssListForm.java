package dream.part.iss.wo.form;

import common.struts.BaseForm;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;

/**
 * �������Ȯ�� - ��� form
 * @author  ssong
 * @version $Id: MaPtIssListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtIssListForm"
 */
public class MaPtIssListForm extends BaseForm
{    
    //===============================================================
    /** �������Ȯ�� ���� */
    private MaPtIssCommonDTO maPtIssCommonDTO = new MaPtIssCommonDTO();
    /** �� */
    private MaPtIssDetailDTO maPtIssDetailDTO = new MaPtIssDetailDTO();
    
	public MaPtIssDetailDTO getMaPtIssDetailDTO()
    {
        return maPtIssDetailDTO;
    }

    public void setMaPtIssDetailDTO(MaPtIssDetailDTO maPtIssDetailDTO)
    {
        this.maPtIssDetailDTO = maPtIssDetailDTO;
    }

    public MaPtIssCommonDTO getMaPtIssCommonDTO() {
		return maPtIssCommonDTO;
	}

	public void setMaPtIssCommonDTO(MaPtIssCommonDTO maPtIssCommonDTO) {
		this.maPtIssCommonDTO = maPtIssCommonDTO;
	}
}
