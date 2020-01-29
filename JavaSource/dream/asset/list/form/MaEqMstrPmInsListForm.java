package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * 설비의 정기점검 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmInsListForm"
 */
public class MaEqMstrPmInsListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  설비 예방작업 목록  */
    private MaEqMstrPmInsListDTO maEqMstrPmInsListDTO = new MaEqMstrPmInsListDTO();
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmInsListDTO getMaEqMstrPmInsListDTO() {
		return maEqMstrPmInsListDTO;
	}
	public void setMaEqMstrPmInsListDTO(MaEqMstrPmInsListDTO maEqMstrPmInsListDTO) {
		this.maEqMstrPmInsListDTO = maEqMstrPmInsListDTO;
	}

}
