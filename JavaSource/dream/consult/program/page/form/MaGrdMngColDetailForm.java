package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaGrdMngColDetailDTO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * ȭ�麰 ��ư �� 
 * @author  kim2107
 * @version $Id: MaGrdMngColDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maGrdMngColDetailForm"
 */
public class MaGrdMngColDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaGrdMngCommonDTO maGrdMngCommonDTO = new MaGrdMngCommonDTO();

	/** �� DTO  */
    private MaGrdMngColDetailDTO maGrdMngColDetailDTO = new MaGrdMngColDetailDTO();

	public MaGrdMngColDetailDTO getMaGrdMngColDetailDTO() {
		return maGrdMngColDetailDTO;
	}
	public void setMaGrdMngColDetailDTO(MaGrdMngColDetailDTO maGrdMngColDetailDTO) {
		this.maGrdMngColDetailDTO = maGrdMngColDetailDTO;
	}
	public MaGrdMngCommonDTO getMaGrdMngCommonDTO() {
		return maGrdMngCommonDTO;
	}
	public void setMaGrdMngCommonDTO(MaGrdMngCommonDTO maGrdMngCommonDTO) {
		this.maGrdMngCommonDTO = maGrdMngCommonDTO;
	}
}
