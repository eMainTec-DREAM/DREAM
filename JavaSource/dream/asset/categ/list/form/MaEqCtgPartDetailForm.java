package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;

/**
 * 설비종류별 부품
 * @author  kim2107
 * @version $Id: MaEqCtgPartDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCtgPartDetailForm"
 */
public class MaEqCtgPartDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
	/** 설비종류별 부품 목록 DTO  */
    private MaEqCtgPartListDTO maEqCtgPartListDTO = new MaEqCtgPartListDTO();
	/** 설비종류별 부품 상세 DTO  */
    private MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = new MaEqCtgPartDetailDTO();
    
	public MaEqCtgPartListDTO getMaEqCtgPartListDTO() {
		return maEqCtgPartListDTO;
	}
	public void setMaEqCtgPartListDTO(MaEqCtgPartListDTO maEqCtgPartListDTO) {
		this.maEqCtgPartListDTO = maEqCtgPartListDTO;
	}
	public MaEqCtgPartDetailDTO getMaEqCtgPartDetailDTO() {
		return maEqCtgPartDetailDTO;
	}
	public void setMaEqCtgPartDetailDTO(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO) {
		this.maEqCtgPartDetailDTO = maEqCtgPartDetailDTO;
	}
	public MaEqCatalogCommonDTO getMaEqCatalogCommonDTO() {
		return maEqCatalogCommonDTO;
	}
	public void setMaEqCatalogCommonDTO(MaEqCatalogCommonDTO maEqCatalogCommonDTO) {
		this.maEqCatalogCommonDTO = maEqCatalogCommonDTO;
	}
}
