package dream.part.iss.emg.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;

/**
 * ������- �� Form
 * @author  ssong
 * @version $Id: MaPtIssEmgDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtIssEmgDetailForm"
 */
public class MaPtIssEmgDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private MaPtIssEmgCommonDTO maPtIssEmgCommonDTO = new MaPtIssEmgCommonDTO();
    //========================================================================
    /** �� */
    private MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = new MaPtIssEmgDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

    public MaPtIssEmgCommonDTO getMaPtIssEmgCommonDTO() {
		return maPtIssEmgCommonDTO;
	}

    public void setMaPtIssEmgCommonDTO(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO) {
		this.maPtIssEmgCommonDTO = maPtIssEmgCommonDTO;
	}

	public MaPtIssEmgDetailDTO getMaPtIssEmgDetailDTO() {
		return maPtIssEmgDetailDTO;
	}

	public void setMaPtIssEmgDetailDTO(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO) {
		this.maPtIssEmgDetailDTO = maPtIssEmgDetailDTO;
	}
}
