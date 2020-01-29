package dream.asset.categ.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * 설비종류의 점검항목 탭의 list form
 * @author  euna0207
 * @version $Id: MaEqCatalogPointListForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqCatalogPointListForm"
 */
public class MaEqCatalogPointListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
    private MaEqCatalogDetailDTO maEqCatalogDetailDTO = new MaEqCatalogDetailDTO();
    /**  설비 예방작업 목록  */
    private MaEqCatalogPointListDTO maEqCatalogPointListDTO = new MaEqCatalogPointListDTO();
	
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
}
