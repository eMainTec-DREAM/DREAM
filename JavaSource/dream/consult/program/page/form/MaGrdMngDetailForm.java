package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaGrdMngDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * �� Form
 * @author  jung7126
 * @version $Id: MaGrdMngDetailForm.java,v 1.0 2015/12/02 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maGrdMngDetailForm"
 */
public class MaGrdMngDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    /** ���� */ 
    private MaGrdMngCommonDTO maGrdMngCommonDTO = new MaGrdMngCommonDTO();
    //========================================================================
    /** �� */
    private MaGrdMngDetailDTO maGrdMngDetailDTO = new MaGrdMngDetailDTO();
    

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

	public MaGrdMngDetailDTO getMaGrdMngDetailDTO() {
		return maGrdMngDetailDTO;
	}

	public void setMaGrdMngDetailDTO(MaGrdMngDetailDTO maGrdMngDetailDTO) {
		this.maGrdMngDetailDTO = maGrdMngDetailDTO;
	}
}
