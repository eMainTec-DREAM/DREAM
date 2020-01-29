package dream.tool.stk.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;

/**
 * �������- �� Form
 * @author  ssong
 * @version $Id: MaPttStckDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPttStckDetailForm"
 */
public class MaPttStckDetailForm extends BaseForm
{
    //========================================================================
    /** ������� ���� */ 
    private MaPttStckCommonDTO maPttStckCommonDTO = new MaPttStckCommonDTO();
    //========================================================================
    /** ������� �� */
    private MaPttStckDetailDTO maPttStckDetailDTO = new MaPttStckDetailDTO();
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

    public MaPttStckCommonDTO getMaPttStckCommonDTO() {
		return maPttStckCommonDTO;
	}

    public void setMaPttStckCommonDTO(MaPttStckCommonDTO maPttStckCommonDTO) {
		this.maPttStckCommonDTO = maPttStckCommonDTO;
	}

	public MaPttStckDetailDTO getMaPttStckDetailDTO() {
		return maPttStckDetailDTO;
	}

	public void setMaPttStckDetailDTO(MaPttStckDetailDTO maPttStckDetailDTO) {
		this.maPttStckDetailDTO = maPttStckDetailDTO;
	}
}
