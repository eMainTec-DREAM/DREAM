package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;

/**
 * ���������� ��ǰ ���
 * @author  kim21017
 * @version $Id: MaEqCtgPartListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCtgPartListForm"
 */
public class MaEqCtgPartListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
    /**  ���������� ��ǰ ���  */
    private MaEqCtgPartListDTO maEqCtgPartListDTO = new MaEqCtgPartListDTO();
    /** ���������� ��ǰ �� DTO  */
    private MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = new MaEqCtgPartDetailDTO();
	

	public MaEqCtgPartDetailDTO getMaEqCtgPartDetailDTO()
    {
        return maEqCtgPartDetailDTO;
    }

    public void setMaEqCtgPartDetailDTO(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO)
    {
        this.maEqCtgPartDetailDTO = maEqCtgPartDetailDTO;
    }

    public MaEqCatalogCommonDTO getMaEqCatalogCommonDTO() {
		return maEqCatalogCommonDTO;
	}

	public void setMaEqCatalogCommonDTO(MaEqCatalogCommonDTO maEqCatalogCommonDTO) {
		this.maEqCatalogCommonDTO = maEqCatalogCommonDTO;
	}

	public MaEqCtgPartListDTO getMaEqCtgPartListDTO() {
		return maEqCtgPartListDTO;
	}

	public void setMaEqCtgPartListDTO(MaEqCtgPartListDTO maEqCtgPartListDTO) {
		this.maEqCtgPartListDTO = maEqCtgPartListDTO;
	}
}
