package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재
 * @author  kim2107
 * @version $Id: MaEqMstrPartDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPartDetailForm"
 */
public class MaEqMstrPartDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 구성자재 목록 DTO  */
    private MaEqMstrPartListDTO maEqMstrPartListDTO = new MaEqMstrPartListDTO();
	/** 구성자재 상세 DTO  */
    private MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = new MaEqMstrPartDetailDTO();
    
    /** 상세 DTO  */
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
    
    
    public MaEqMstrDetailDTO getMaEqMstrDetailDTO()
    {
        return maEqMstrDetailDTO;
    }
    public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
        this.maEqMstrDetailDTO = maEqMstrDetailDTO;
    }
    public MaEqMstrPartListDTO getMaEqMstrPartListDTO() {
		return maEqMstrPartListDTO;
	}
	public void setMaEqMstrPartListDTO(MaEqMstrPartListDTO maEqMstrPartListDTO) {
		this.maEqMstrPartListDTO = maEqMstrPartListDTO;
	}
	public MaEqMstrPartDetailDTO getMaEqMstrPartDetailDTO() {
		return maEqMstrPartDetailDTO;
	}
	public void setMaEqMstrPartDetailDTO(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO) {
		this.maEqMstrPartDetailDTO = maEqMstrPartDetailDTO;
	}
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
}
