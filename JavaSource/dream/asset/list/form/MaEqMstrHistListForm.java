package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrHistListDTO;

/**
 * ���񺯰��̷� - ��� form
 * @author  kim21017
 * @version $Id: MaEqMstrHistListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrHistListForm"
 */
public class MaEqMstrHistListForm extends BaseForm
{    
    //===============================================================
    /** ���񺯰��̷� ���� */
    private MaEqMstrHistListDTO maEqMstrHistListDTO = new MaEqMstrHistListDTO();
    
	public MaEqMstrHistListDTO getMaEqMstrHistListDTO() {
		return maEqMstrHistListDTO;
	}

	public void setMaEqMstrHistListDTO(MaEqMstrHistListDTO maEqMstrHistListDTO) {
		this.maEqMstrHistListDTO = maEqMstrHistListDTO;
	}
}
