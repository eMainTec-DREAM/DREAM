package dream.tool.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrDetailDTO;

/**
 * ���縶���� - �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPttMstrDetailForm"
 */
public class MaPttMstrDetailForm extends BaseForm
{
    //========================================================================
    /** ���縶���� ���� */ 
    private MaPttMstrCommonDTO maPttMstrCommonDTO = new MaPttMstrCommonDTO();
    //========================================================================
    /** ���縶���� �� */
    private MaPttMstrDetailDTO maPttMstrDetailDTO = new MaPttMstrDetailDTO();
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

    public MaPttMstrCommonDTO getMaPttMstrCommonDTO() {
		return maPttMstrCommonDTO;
	}

	public void setMaPttMstrCommonDTO(MaPttMstrCommonDTO maPttMstrCommonDTO) 
	{
		this.maPttMstrCommonDTO = maPttMstrCommonDTO;
	}

	public MaPttMstrDetailDTO getMaPttMstrDetailDTO() {
		return maPttMstrDetailDTO;
	}

	public void setMaPttMstrDetailDTO(MaPttMstrDetailDTO maPttMstrDetailDTO) 
	{
		this.maPttMstrDetailDTO = maPttMstrDetailDTO;
	}
}
