package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;

/**
 * �������� ���
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldModHistListForm"
 */
public class MaEqMstrMoldModHistListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  ��������ǰ ���  */
    private MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO = new MaEqMstrMoldModHistListDTO();
    
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
	


	
}
