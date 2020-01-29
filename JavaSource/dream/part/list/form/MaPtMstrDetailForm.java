package dream.part.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;

/**
 * ���縶���� - �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtMstrDetailForm"
 */
public class MaPtMstrDetailForm extends BaseForm
{
    //========================================================================
    /** ���縶���� ���� */ 
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    //========================================================================
    /** ���縶���� �� */
    private MaPtMstrDetailDTO maPtMstrDetailDTO = new MaPtMstrDetailDTO();
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

    public MaPtMstrCommonDTO getMaPtMstrCommonDTO() {
		return maPtMstrCommonDTO;
	}

	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) 
	{
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}

	public MaPtMstrDetailDTO getMaPtMstrDetailDTO() {
		return maPtMstrDetailDTO;
	}

	public void setMaPtMstrDetailDTO(MaPtMstrDetailDTO maPtMstrDetailDTO) 
	{
		this.maPtMstrDetailDTO = maPtMstrDetailDTO;
	}
}
