package dream.work.rpt.maeqbm.form;

import common.struts.BaseForm;
import dream.work.rpt.maeqbm.dto.MaEqBmListDTO;

/**
 * 설비고장내역
 * @author  kim21017
 * @version $Id: MaEqBmListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqBmListForm"
 */
public class MaEqBmListForm extends BaseForm
{    
    //===============================================================
    /** 설비별고장분석 */
    private MaEqBmListDTO maEqBmListDTO = new MaEqBmListDTO();

	public MaEqBmListDTO getMaEqBmListDTO() {
		return maEqBmListDTO;
	}

	public void setMaEqBmListDTO(MaEqBmListDTO maEqBmListDTO) {
		this.maEqBmListDTO = maEqBmListDTO;
	}

}
