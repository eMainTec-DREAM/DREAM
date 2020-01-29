package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdListDTO;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPartListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldProdListForm"
 */
public class MaEqMstrMoldProdListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  금형생산품 목록  */
    private MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO = new MaEqMstrMoldProdListDTO();
	

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

	
}
