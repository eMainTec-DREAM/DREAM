package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;

/**
 * ��������
 * @author  kim2107
 * @version $Id: MaEqMstrMoldModHistDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldModHistDetailForm"
 */
public class MaEqMstrMoldModHistDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** ��������ǰ ��� DTO  */
    private MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO = new MaEqMstrMoldModHistListDTO();
	/** ��������ǰ �� DTO  */
    private MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO = new MaEqMstrMoldModHistDetailDTO();
    
    
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
	public MaEqMstrMoldModHistDetailDTO getMaEqMstrMoldModHistDetailDTO() {
		return maEqMstrMoldModHistDetailDTO;
	}
	public void setMaEqMstrMoldModHistDetailDTO(
			MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO) {
		this.maEqMstrMoldModHistDetailDTO = maEqMstrMoldModHistDetailDTO;
	}
    
	
	
}
