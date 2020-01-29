package dream.part.rep.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * ��ǰ����- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepDetailForm"
 */
public class MaPtRepDetailForm extends BaseForm
{
    //========================================================================
    /** ��ǰ���� ���� */ 
    private MaPtRepCommonDTO maPtRepCommonDTO = new MaPtRepCommonDTO();
    //========================================================================
    /** ��ǰ���� �� */
    private MaPtRepDetailDTO maPtRepDetailDTO = new MaPtRepDetailDTO();
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

    public MaPtRepCommonDTO getMaPtRepCommonDTO() 
    {
		return maPtRepCommonDTO;
	}

    public void setMaPtRepCommonDTO(MaPtRepCommonDTO maPtRepCommonDTO) 
    {
		this.maPtRepCommonDTO = maPtRepCommonDTO;
	}

	public MaPtRepDetailDTO getMaPtRepDetailDTO() 
	{
		return maPtRepDetailDTO;
	}

	public void setMaPtRepDetailDTO(MaPtRepDetailDTO maPtRepDetailDTO) 
	{
		this.maPtRepDetailDTO = maPtRepDetailDTO;
	}
}
