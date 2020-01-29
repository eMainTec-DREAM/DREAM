package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * ȭ�� - ��� form
 * @author  jung7126
 * @version $Id: MaGrdMngListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maGrdMngListForm"
 */
public class MaGrdMngListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    /** ȭ�� ���� */
    private MaGrdMngCommonDTO maGrdMngCommonDTO = new MaGrdMngCommonDTO();
    
	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}

	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}

	public MaGrdMngCommonDTO getMaGrdMngCommonDTO() {
		return maGrdMngCommonDTO;
	}

	public void setMaGrdMngCommonDTO(MaGrdMngCommonDTO maGrdMngCommonDTO) {
		this.maGrdMngCommonDTO = maGrdMngCommonDTO;
	}
}
