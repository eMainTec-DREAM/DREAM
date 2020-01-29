package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;

/**
 * ��������(����)
 * @author  kim2107
 * @version $Id: MaEqMstrSpecDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrSpecDetailForm"
 */
public class MaEqMstrSpecDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** ��������(����) ��� DTO  */
    private MaEqMstrSpecListDTO maEqMstrSpecListDTO = new MaEqMstrSpecListDTO();
	/** ��������(����) �� DTO  */
    private MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = new MaEqMstrSpecDetailDTO();
    
	public MaEqMstrSpecListDTO getMaEqMstrSpecListDTO() {
		return maEqMstrSpecListDTO;
	}
	public void setMaEqMstrSpecListDTO(MaEqMstrSpecListDTO maEqMstrSpecListDTO) {
		this.maEqMstrSpecListDTO = maEqMstrSpecListDTO;
	}
	public MaEqMstrSpecDetailDTO getMaEqMstrSpecDetailDTO() {
		return maEqMstrSpecDetailDTO;
	}
	public void setMaEqMstrSpecDetailDTO(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO) {
		this.maEqMstrSpecDetailDTO = maEqMstrSpecDetailDTO;
	}
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
}
