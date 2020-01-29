package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;

/**
 * ������� ���
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrAsmbListForm"
 */
public class MaEqMstrAsmbListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  ������� ���  */
    private MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = new MaEqMstrAsmbListDTO();
    /**  ������� ��  */
    private MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = new MaEqMstrAsmbDetailDTO();

    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();


    public MaEqMstrDetailDTO getMaEqMstrDetailDTO() {
		return maEqMstrDetailDTO;
	}
	
    public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		this.maEqMstrDetailDTO = maEqMstrDetailDTO;
	}
	
    public MaEqMstrAsmbDetailDTO getMaEqMstrAsmbDetailDTO() {
		return maEqMstrAsmbDetailDTO;
	}
	
    public void setMaEqMstrAsmbDetailDTO(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO) {
		this.maEqMstrAsmbDetailDTO = maEqMstrAsmbDetailDTO;
	}
	
    public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	
    public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	
    public MaEqMstrAsmbListDTO getMaEqMstrAsmbListDTO() {
		return maEqMstrAsmbListDTO;
	}
	
    public void setMaEqMstrAsmbListDTO(MaEqMstrAsmbListDTO maEqMstrAsmbListDTO) {
		this.maEqMstrAsmbListDTO = maEqMstrAsmbListDTO;
	}
    
}
