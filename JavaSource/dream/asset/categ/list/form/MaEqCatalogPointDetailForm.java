package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * 설비종류의 점검항목 탭의 detail form
 * @author  euna0207
 * @version $Id: MaEqCatalogPointDetailForm.java,v 1.0 2015/12/04 09:09:54 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCatalogPointDetailForm"
 */
public class MaEqCatalogPointDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
    private MaEqCatalogDetailDTO maEqCatalogDetailDTO = new MaEqCatalogDetailDTO();
	/** 설비 점검항목 목록 DTO  */
    private MaEqCatalogPointListDTO maEqCatalogPointListDTO = new MaEqCatalogPointListDTO();
	/** 설비 점검항목 상세 DTO  */
    private MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO = new MaEqCatalogPointDetailDTO();
    
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
	public MaEqCatalogPointListDTO getMaEqCatalogPointListDTO() {
		return maEqCatalogPointListDTO;
	}
	public void setMaEqCatalogPointListDTO(MaEqCatalogPointListDTO maEqCatalogPointListDTO) {
		this.maEqCatalogPointListDTO = maEqCatalogPointListDTO;
	}
	public MaEqCatalogPointDetailDTO getMaEqCatalogPointDetailDTO() {
		return maEqCatalogPointDetailDTO;
	}
	public void setMaEqCatalogPointDetailDTO(MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO) {
		this.maEqCatalogPointDetailDTO = maEqCatalogPointDetailDTO;
	}
}
