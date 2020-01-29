package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldProdListDTO;

/**
 * ��������
 * @author  kim2107
 * @version $Id: MaEqMstrPartDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldProdDetailForm"
 */
public class MaEqMstrMoldProdDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** ��������ǰ ��� DTO  */
    private MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO = new MaEqMstrMoldProdListDTO();
	/** ��������ǰ �� DTO  */
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
