package dream.part.iss.wo.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;

/**
 * �������Ȯ��- �� Form
 * @author  ssong
 * @version $Id: MaPtIssDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtIssDetailForm"
 */
public class MaPtIssDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private MaPtIssCommonDTO maPtIssCommonDTO = new MaPtIssCommonDTO();
    //========================================================================
    /** �� */
    private MaPtIssDetailDTO maPtIssDetailDTO = new MaPtIssDetailDTO();
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

    public MaPtIssCommonDTO getMaPtIssCommonDTO() {
		return maPtIssCommonDTO;
	}

    public void setMaPtIssCommonDTO(MaPtIssCommonDTO maPtIssCommonDTO) {
		this.maPtIssCommonDTO = maPtIssCommonDTO;
	}

	public MaPtIssDetailDTO getMaPtIssDetailDTO() {
		return maPtIssDetailDTO;
	}

	public void setMaPtIssDetailDTO(MaPtIssDetailDTO maPtIssDetailDTO) {
		this.maPtIssDetailDTO = maPtIssDetailDTO;
	}
}
