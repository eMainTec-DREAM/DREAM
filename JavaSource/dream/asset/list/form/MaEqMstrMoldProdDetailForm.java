package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldProdListDTO;

/**
 * 구성자재
 * @author  kim2107
 * @version $Id: MaEqMstrPartDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldProdDetailForm"
 */
public class MaEqMstrMoldProdDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 금형생산품 목록 DTO  */
    private MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO = new MaEqMstrMoldProdListDTO();
	/** 금형생산품 상세 DTO  */
    private MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO = new MaEqMstrMoldProdDetailDTO();
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrMoldProdListDTO getMaEqMstrMoldProdListDTO() {
		return maEqMstrMoldProdListDTO;
	}
	public void setMaEqMstrMoldProdListDTO(
			MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO) {
		this.maEqMstrMoldProdListDTO = maEqMstrMoldProdListDTO;
	}
	public MaEqMstrMoldProdDetailDTO getMaEqMstrMoldProdDetailDTO() {
		return maEqMstrMoldProdDetailDTO;
	}
	public void setMaEqMstrMoldProdDetailDTO(
			MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO) {
		this.maEqMstrMoldProdDetailDTO = maEqMstrMoldProdDetailDTO;
	}
    
	
}
