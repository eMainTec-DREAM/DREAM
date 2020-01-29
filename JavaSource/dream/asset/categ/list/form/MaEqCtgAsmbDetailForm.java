package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * ���������� �۾�����
 * @author  kim2107
 * @version $Id: MaEqCtgAsmbDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCtgAsmbDetailForm"
 */
public class MaEqCtgAsmbDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
	/** ���������� �۾����� ��� DTO  */
    private MaEqCtgAsmbListDTO maEqCtgAsmbListDTO = new MaEqCtgAsmbListDTO();
	/** ���������� �۾����� �� DTO  */
    private MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = new MaEqCtgAsmbDetailDTO();
    
	public MaEqCtgAsmbListDTO getMaEqCtgAsmbListDTO() {
		return maEqCtgAsmbListDTO;
	}
	public void setMaEqCtgAsmbListDTO(MaEqCtgAsmbListDTO maEqCtgAsmbListDTO) {
		this.maEqCtgAsmbListDTO = maEqCtgAsmbListDTO;
	}
	public MaEqCtgAsmbDetailDTO getMaEqCtgAsmbDetailDTO() {
		return maEqCtgAsmbDetailDTO;
	}
	public void setMaEqCtgAsmbDetailDTO(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO) {
		this.maEqCtgAsmbDetailDTO = maEqCtgAsmbDetailDTO;
	}
	public MaEqCatalogCommonDTO getMaEqCatalogCommonDTO() {
		return maEqCatalogCommonDTO;
	}
	public void setMaEqCatalogCommonDTO(MaEqCatalogCommonDTO maEqCatalogCommonDTO) {
		this.maEqCatalogCommonDTO = maEqCatalogCommonDTO;
	}
}
