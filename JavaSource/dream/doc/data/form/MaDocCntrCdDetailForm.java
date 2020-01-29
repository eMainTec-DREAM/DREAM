package dream.doc.data.form;

import common.struts.BaseForm;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * �Ϲ��ڷ�� - �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maDocCntrCdDetailForm"
 */
public class MaDocCntrCdDetailForm extends BaseForm
{
    //========================================================================
    /** ���縶���� ���� */ 
    private MaDocCntrCdCommonDTO maDocCntrCdCommonDTO = new MaDocCntrCdCommonDTO();
    //========================================================================
    /** ���縶���� �� */
    private MaDocCntrCdDetailDTO maDocCntrCdDetailDTO = new MaDocCntrCdDetailDTO();
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

    public MaDocCntrCdCommonDTO getMaDocCntrCdCommonDTO() {
		return maDocCntrCdCommonDTO;
	}

	public void setMaDocCntrCdCommonDTO(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO) 
	{
		this.maDocCntrCdCommonDTO = maDocCntrCdCommonDTO;
	}

	public MaDocCntrCdDetailDTO getMaDocCntrCdDetailDTO() {
		return maDocCntrCdDetailDTO;
	}

	public void setMaDocCntrCdDetailDTO(MaDocCntrCdDetailDTO maDocCntrCdDetailDTO) 
	{
		this.maDocCntrCdDetailDTO = maDocCntrCdDetailDTO;
	}
}
