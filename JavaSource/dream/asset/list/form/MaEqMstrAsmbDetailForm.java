package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비부위
 * @author  kim2107
 * @version $Id: MaEqMstrAsmbDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrAsmbDetailForm"
 */
public class MaEqMstrAsmbDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 설비부위 목록 DTO  */
    private MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = new MaEqMstrAsmbListDTO();
	/** 설비부위 상세 DTO  */
    private MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = new MaEqMstrAsmbDetailDTO();
    
	public MaEqMstrAsmbListDTO getMaEqMstrAsmbListDTO() {
		return maEqMstrAsmbListDTO;
	}
	public void setMaEqMstrAsmbListDTO(MaEqMstrAsmbListDTO maEqMstrAsmbListDTO) {
		this.maEqMstrAsmbListDTO = maEqMstrAsmbListDTO;
	}
	public MaEqMstrAsmbDetailDTO getMaEqMstrAsmbDetailDTO() {
		return maEqMstrAsmbDetailDTO;
	}
	public void setMaEqMstrAsmbDetailDTO(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO) {
		this.maEqMstrAsmbDetailDTO = maEqMstrAsmbDetailDTO;
	}
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
}
