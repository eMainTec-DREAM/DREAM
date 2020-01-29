package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;

/**
 * ��������(����) ���
 * @author  kim21017
 * @version $Id: MaEqMstrSpecListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrSpecListForm"
 */
public class MaEqMstrSpecListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  ��������(����) ���  */
    private MaEqMstrSpecListDTO maEqMstrSpecListDTO = new MaEqMstrSpecListDTO();
    /**  ��������(����) ��  */
    private MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = new MaEqMstrSpecDetailDTO();
	
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();

	public MaEqMstrSpecDetailDTO getMaEqMstrSpecDetailDTO() {
		return maEqMstrSpecDetailDTO;
	}

	public MaEqMstrDetailDTO getMaEqMstrDetailDTO() {
		return maEqMstrDetailDTO;
	}

	public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		this.maEqMstrDetailDTO = maEqMstrDetailDTO;
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

	public MaEqMstrSpecListDTO getMaEqMstrSpecListDTO() {
		return maEqMstrSpecListDTO;
	}

	public void setMaEqMstrSpecListDTO(MaEqMstrSpecListDTO maEqMstrSpecListDTO) {
		this.maEqMstrSpecListDTO = maEqMstrSpecListDTO;
	}
}
