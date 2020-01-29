package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * ���������� ǥ������ ���
 * @author  syyang
 * @version $Id: MaEqCtgSpecListForm.java,v 1.0 2015/12/01 09:13:09 syyang Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCtgSpecListForm"
 */
public class MaEqCtgSpecListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
    /**  ���������� ǥ������ ���  */
    private MaEqCtgSpecListDTO maEqCtgSpecListDTO = new MaEqCtgSpecListDTO();
	

	public MaEqCatalogCommonDTO getMaEqCatalogCommonDTO() {
		return maEqCatalogCommonDTO;
	}

	public void setMaEqCatalogCommonDTO(MaEqCatalogCommonDTO maEqCatalogCommonDTO) {
		this.maEqCatalogCommonDTO = maEqCatalogCommonDTO;
	}

	public MaEqCtgSpecListDTO getMaEqCtgSpecListDTO() {
		return maEqCtgSpecListDTO;
	}

	public void setMaEqCtgSpecListDTO(MaEqCtgSpecListDTO maEqCtgSpecListDTO) {
		this.maEqCtgSpecListDTO = maEqCtgSpecListDTO;
	}
}
