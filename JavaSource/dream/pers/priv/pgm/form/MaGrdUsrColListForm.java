package dream.pers.priv.pgm.form;

import common.struts.BaseForm;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * 칼럼 목록
 * @author  jung7126
 * @version $Id: MaGrdUsrColListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maGrdUsrColListForm"
 */
public class MaGrdUsrColListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaGrdUsrCommonDTO maGrdUsrCommonDTO = new MaGrdUsrCommonDTO();
    /** 상세 DTO  */
    private MaGrdUsrDetailDTO maGrdUsrDetailDTO = new MaGrdUsrDetailDTO();

    
	public MaGrdUsrDetailDTO getMaGrdUsrDetailDTO()
    {
        return maGrdUsrDetailDTO;
    }

    public void setMaGrdUsrDetailDTO(MaGrdUsrDetailDTO maGrdUsrDetailDTO)
    {
        this.maGrdUsrDetailDTO = maGrdUsrDetailDTO;
    }

    public MaGrdUsrCommonDTO getMaGrdUsrCommonDTO() {
		return maGrdUsrCommonDTO;
	}

	public void setMaGrdUsrCommonDTO(MaGrdUsrCommonDTO maGrdUsrCommonDTO) {
		this.maGrdUsrCommonDTO = maGrdUsrCommonDTO;
	}
}
