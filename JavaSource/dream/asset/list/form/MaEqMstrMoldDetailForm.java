package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * ���񸶽���- �� Form
 * @author  kim21017
 * @version $Id: MaEqMstrDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldDetailForm"
 */
public class MaEqMstrMoldDetailForm extends BaseForm
{
    //========================================================================
    /** ���񸶽��� ���� */ 
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    //========================================================================
    /** ���񸶽��� �� */
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
    /** ������� �� */
    private MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO = new MaEqMstrMoldDetailDTO();
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

    public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

    public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public MaEqMstrDetailDTO getMaEqMstrDetailDTO() {
		return maEqMstrDetailDTO;
	}

	public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		this.maEqMstrDetailDTO = maEqMstrDetailDTO;
	}
	
	public MaEqMstrMoldDetailDTO getMaEqMstrMoldDetailDTO() {
		return maEqMstrMoldDetailDTO;
	}

	public void setMaEqMstrMoldDetailDTO(MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO) {
		this.maEqMstrMoldDetailDTO = maEqMstrMoldDetailDTO;
	}
	
}
