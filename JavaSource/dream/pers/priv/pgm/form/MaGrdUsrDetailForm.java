package dream.pers.priv.pgm.form;

import common.struts.BaseForm;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * ȭ�麰 ��ư �� 
 * @author  kim2107
 * @version $Id: MaGrdUsrDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maGrdUsrDetailForm"
 */
public class MaGrdUsrDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaGrdUsrCommonDTO maGrdUsrCommonDTO = new MaGrdUsrCommonDTO();

	/** �� DTO  */
    private MaGrdUsrDetailDTO maGrdUsrDetailDTO = new MaGrdUsrDetailDTO();

	public MaGrdUsrDetailDTO getMaGrdUsrDetailDTO() {
		return maGrdUsrDetailDTO;
	}
	public void setMaGrdUsrDetailDTO(MaGrdUsrDetailDTO maGrdUsrDetailDTO) {
		this.maGrdUsrDetailDTO = maGrdUsrDetailDTO;
	}
	public MaGrdUsrCommonDTO getMaGrdUsrCommonDTO() {
		return maGrdUsrCommonDTO;
	}
	public void setMaGrdUsrCommonDTO(MaGrdUsrCommonDTO maGrdUsrCommonDTO) {
		this.maGrdUsrCommonDTO = maGrdUsrCommonDTO;
	}
}
