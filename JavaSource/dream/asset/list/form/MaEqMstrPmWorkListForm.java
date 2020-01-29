package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * 설비의 정기작업 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmWorkListForm"
 */
public class MaEqMstrPmWorkListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  설비 예방작업 목록  */
    private MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO = new MaEqMstrPmWorkListDTO();
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmWorkListDTO getMaEqMstrPmWorkListDTO() {
		return maEqMstrPmWorkListDTO;
	}
	public void setMaEqMstrPmWorkListDTO(MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO) {
		this.maEqMstrPmWorkListDTO = maEqMstrPmWorkListDTO;
	}

}
