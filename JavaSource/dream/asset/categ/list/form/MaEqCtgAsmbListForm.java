package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * ���������� �۾����� ���
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCtgAsmbListForm"
 */
public class MaEqCtgAsmbListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
    /**  ���������� �۾����� ���  */
    private MaEqCtgAsmbListDTO maEqCtgAsmbListDTO = new MaEqCtgAsmbListDTO();
	

	public MaEqCatalogCommonDTO getMaEqCatalogCommonDTO() {
		return maEqCatalogCommonDTO;
	}

	public void setMaEqCatalogCommonDTO(MaEqCatalogCommonDTO maEqCatalogCommonDTO) {
		this.maEqCatalogCommonDTO = maEqCatalogCommonDTO;
	}

	public MaEqCtgAsmbListDTO getMaEqCtgAsmbListDTO() {
		return maEqCtgAsmbListDTO;
	}

	public void setMaEqCtgAsmbListDTO(MaEqCtgAsmbListDTO maEqCtgAsmbListDTO) {
		this.maEqCtgAsmbListDTO = maEqCtgAsmbListDTO;
	}
}
