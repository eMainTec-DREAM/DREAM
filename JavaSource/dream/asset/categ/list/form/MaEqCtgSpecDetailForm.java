package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * ���������� �۾�����
 * @author  kim2107
 * @version $Id: MaEqCtgSpecDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCtgSpecDetailForm"
 */
public class MaEqCtgSpecDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
	/** ���������� ǥ������ ��� DTO  */
    private MaEqCtgSpecListDTO maEqCtgSpecListDTO = new MaEqCtgSpecListDTO();
	/** ���������� �۾����� �� DTO  */
    private MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO = new MaEqCtgSpecDetailDTO();
    
	public MaEqCtgSpecListDTO getMaEqCtgSpecListDTO() {
		return maEqCtgSpecListDTO;
	}
	public void setMaEqCtgSpecListDTO(MaEqCtgSpecListDTO maEqCtgSpecListDTO) {
		this.maEqCtgSpecListDTO = maEqCtgSpecListDTO;
	}
	public MaEqCtgSpecDetailDTO getMaEqCtgSpecDetailDTO() {
		return maEqCtgSpecDetailDTO;
	}
	public void setMaEqCtgSpecDetailDTO(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO) {
		this.maEqCtgSpecDetailDTO = maEqCtgSpecDetailDTO;
	}
	public MaEqCatalogCommonDTO getMaEqCatalogCommonDTO() {
		return maEqCatalogCommonDTO;
	}
	public void setMaEqCatalogCommonDTO(MaEqCatalogCommonDTO maEqCatalogCommonDTO) {
		this.maEqCatalogCommonDTO = maEqCatalogCommonDTO;
	}
}
