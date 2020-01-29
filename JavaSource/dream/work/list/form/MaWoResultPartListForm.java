package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.MaWoResultPartListDTO;

/**
 * �۾���� �������� ���
 * @author  kim21017
 * @version $Id: MaWoResultPartListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultPartListForm"
 */
public class MaWoResultPartListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** �� */
    private MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
    /**  �۾���� �������� ���  */
    private MaWoResultPartListDTO maWoResultPartListDTO = new MaWoResultPartListDTO();
    /**  �۾���� �������� ��  */
    private MaWoResultPartDetailDTO maWoResultPartDetailDTO = new MaWoResultPartDetailDTO();
	

	public MaWoResultMstrDetailDTO getMaWoResultMstrDetailDTO()
    {
        return maWoResultMstrDetailDTO;
    }

    public void setMaWoResultMstrDetailDTO(
            MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        this.maWoResultMstrDetailDTO = maWoResultMstrDetailDTO;
    }

    public MaWoResultPartDetailDTO getMaWoResultPartDetailDTO()
    {
        return maWoResultPartDetailDTO;
    }

    public void setMaWoResultPartDetailDTO(
            MaWoResultPartDetailDTO maWoResultPartDetailDTO)
    {
        this.maWoResultPartDetailDTO = maWoResultPartDetailDTO;
    }

    public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultPartListDTO getMaWoResultPartListDTO() {
		return maWoResultPartListDTO;
	}

	public void setMaWoResultPartListDTO(MaWoResultPartListDTO maWoResultPartListDTO) {
		this.maWoResultPartListDTO = maWoResultPartListDTO;
	}
}
