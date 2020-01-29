package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * 칼럼 목록
 * @author  jung7126
 * @version $Id: MaGrdMngColListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maGrdMngColListForm"
 */
public class MaGrdMngColListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaGrdMngCommonDTO maGrdMngCommonDTO = new MaGrdMngCommonDTO();

	public MaGrdMngCommonDTO getMaGrdMngCommonDTO() {
		return maGrdMngCommonDTO;
	}

	public void setMaGrdMngCommonDTO(MaGrdMngCommonDTO maGrdMngCommonDTO) {
		this.maGrdMngCommonDTO = maGrdMngCommonDTO;
	}
}
