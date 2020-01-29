package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmListDTO;

/**
 * 설비 점검 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmListForm"
 */
public class MaEqMstrPmListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  설비 점검 목록  */
    private MaEqMstrPmListDTO maEqMstrPmListDTO = new MaEqMstrPmListDTO();
	

	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public MaEqMstrPmListDTO getMaEqMstrPmListDTO() {
		return maEqMstrPmListDTO;
	}

	public void setMaEqMstrPmListDTO(MaEqMstrPmListDTO maEqMstrPmListDTO) {
		this.maEqMstrPmListDTO = maEqMstrPmListDTO;
	}
}
