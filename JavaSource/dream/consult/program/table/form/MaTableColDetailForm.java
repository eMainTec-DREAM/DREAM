package dream.consult.program.table.form;

import common.struts.BaseForm;
import dream.consult.program.table.dto.MaTableColDetailDTO;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * 데이터 테이블 - 분류 
 * @author  kim2107
 * @version $Id: MaTableColDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maTableColDetailForm"
 */
public class MaTableColDetailForm extends BaseForm
{    
    //===============================================================
    /** 데이터 테이블 - 공통 DTO */
    private MaTableCommonDTO maTableCommonDTO = new MaTableCommonDTO();
	/** 데이터 테이블 - 분류 DTO  */
    private MaTableColListDTO maTableColListDTO = new MaTableColListDTO();
	/** 데이터 테이블 - 분류Detail DTO  */
    private MaTableColDetailDTO maTableColDetailDTO = new MaTableColDetailDTO();
    
	public MaTableColListDTO getMaTableColListDTO() {
		return maTableColListDTO;
	}
	public void setMaTableColListDTO(MaTableColListDTO maTableColListDTO) {
		this.maTableColListDTO = maTableColListDTO;
	}
	public MaTableColDetailDTO getMaTableColDetailDTO() {
		return maTableColDetailDTO;
	}
	public void setMaTableColDetailDTO(MaTableColDetailDTO maTableColDetailDTO) {
		this.maTableColDetailDTO = maTableColDetailDTO;
	}
	public MaTableCommonDTO getMaTableCommonDTO() {
		return maTableCommonDTO;
	}
	public void setMaTableCommonDTO(MaTableCommonDTO maTableCommonDTO) {
		this.maTableCommonDTO = maTableCommonDTO;
	}
	
}
