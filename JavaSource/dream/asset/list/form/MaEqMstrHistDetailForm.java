package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrHistDetailDTO;
import dream.asset.list.dto.MaEqMstrHistListDTO;

/**
 * 설비변경이력- 상세 Form
 * @author  kim21017
 * @version $Id: MaEqMstrHistDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrHistDetailForm"
 */
public class MaEqMstrHistDetailForm extends BaseForm
{
    //========================================================================
    /** 설비변경이력 공통 */ 
    private MaEqMstrHistListDTO maEqMstrHistListDTO = new MaEqMstrHistListDTO();
    //========================================================================
    /** 설비변경이력 상세 */
    private MaEqMstrHistDetailDTO maEqMstrHistDetailDTO = new MaEqMstrHistDetailDTO();

    public MaEqMstrHistListDTO getMaEqMstrHistListDTO() {
		return maEqMstrHistListDTO;
	}

    public void setMaEqMstrHistListDTO(MaEqMstrHistListDTO maEqMstrHistListDTO) {
		this.maEqMstrHistListDTO = maEqMstrHistListDTO;
	}

	public MaEqMstrHistDetailDTO getMaEqMstrHistDetailDTO() {
		return maEqMstrHistDetailDTO;
	}

	public void setMaEqMstrHistDetailDTO(MaEqMstrHistDetailDTO maEqMstrHistDetailDTO) {
		this.maEqMstrHistDetailDTO = maEqMstrHistDetailDTO;
	}
}
