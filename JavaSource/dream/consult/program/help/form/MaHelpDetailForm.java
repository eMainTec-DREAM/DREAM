package dream.consult.program.help.form;

import common.struts.BaseForm;
import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.dto.MaHelpDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maHelpDetailForm"
 */
public class MaHelpDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private MaHelpCommonDTO maHelpCommonDTO = new MaHelpCommonDTO();
    //========================================================================
    /** �� */
    private MaHelpDetailDTO maHelpDetailDTO = new MaHelpDetailDTO();
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

    public MaHelpCommonDTO getMaHelpCommonDTO() 
	{
		return maHelpCommonDTO;
	}

	public void setMaHelpCommonDTO(MaHelpCommonDTO maHelpCommonDTO) 
	{
		this.maHelpCommonDTO = maHelpCommonDTO;
	}

	public MaHelpDetailDTO getMaHelpDetailDTO() 
	{
		return maHelpDetailDTO;
	}

	public void setMaHelpDetailDTO(MaHelpDetailDTO maHelpDetailDTO) 
	{
		this.maHelpDetailDTO = maHelpDetailDTO;
	}

}
