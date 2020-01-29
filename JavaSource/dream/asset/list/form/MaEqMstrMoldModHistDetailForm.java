package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;

/**
 * 구성자재
 * @author  kim2107
 * @version $Id: MaEqMstrMoldModHistDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldModHistDetailForm"
 */
public class MaEqMstrMoldModHistDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 금형생산품 목록 DTO  */
    private MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO = new MaEqMstrMoldModHistListDTO();
	/** 금형생산품 상세 DTO  */
    private MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO = new MaEqMstrMoldModHistDetailDTO();
    
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrMoldModHistListDTO getMaEqMstrMoldModHistListDTO() {
		return maEqMstrMoldModHistListDTO;
	}
	public void setMaEqMstrMoldModHistListDTO(
			MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO) {
		this.maEqMstrMoldModHistListDTO = maEqMstrMoldModHistListDTO;
	}
	public MaEqMstrMoldModHistDetailDTO getMaEqMstrMoldModHistDetailDTO() {
		return maEqMstrMoldModHistDetailDTO;
	}
	public void setMaEqMstrMoldModHistDetailDTO(
			MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO) {
		this.maEqMstrMoldModHistDetailDTO = maEqMstrMoldModHistDetailDTO;
	}
    
	
	
}
