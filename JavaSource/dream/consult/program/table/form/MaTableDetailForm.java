package dream.consult.program.table.form;

import common.struts.BaseForm;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.dto.MaTableDetailDTO;

/**
 * 데이터 테이블- 상세 Form
 * @author  kim21017
 * @version $Id: MaTableDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maTableDetailForm"
 */
public class MaTableDetailForm extends BaseForm
{
    //========================================================================
    /** 데이터 테이블 공통 */ 
    private MaTableCommonDTO maTableCommonDTO = new MaTableCommonDTO();
    //========================================================================
    /** 데이터 테이블 상세 */
    private MaTableDetailDTO maTableDetailDTO = new MaTableDetailDTO();
    
	public MaTableCommonDTO getMaTableCommonDTO() {
		return maTableCommonDTO;
	}

	public void setMaTableCommonDTO(MaTableCommonDTO maTableCommonDTO) {
		this.maTableCommonDTO = maTableCommonDTO;
	}

	public MaTableDetailDTO getMaTableDetailDTO() {
		return maTableDetailDTO;
	}

	public void setMaTableDetailDTO(MaTableDetailDTO maTableDetailDTO) {
		this.maTableDetailDTO = maTableDetailDTO;
	}
}