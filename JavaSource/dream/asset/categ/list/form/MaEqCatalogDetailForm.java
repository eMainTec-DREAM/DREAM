package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;

/**
 * ��������- �� Form
 * @author  kim21017
 * @version $Id: MaEqCatalogDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCatalogDetailForm"
 */
public class MaEqCatalogDetailForm extends BaseForm
{
    //========================================================================
    /** �������� ���� */ 
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
    //========================================================================
    /** �������� �� */
    private MaEqCatalogDetailDTO maEqCatalogDetailDTO = new MaEqCatalogDetailDTO();
    

	public MaEqCatalogCommonDTO getMaEqCatalogCommonDTO() {
		return maEqCatalogCommonDTO;
	}

    public void setMaEqCatalogCommonDTO(MaEqCatalogCommonDTO maEqCatalogCommonDTO) {
		this.maEqCatalogCommonDTO = maEqCatalogCommonDTO;
	}

	public MaEqCatalogDetailDTO getMaEqCatalogDetailDTO() {
		return maEqCatalogDetailDTO;
	}

	public void setMaEqCatalogDetailDTO(MaEqCatalogDetailDTO maEqCatalogDetailDTO) {
		this.maEqCatalogDetailDTO = maEqCatalogDetailDTO;
	}
}
