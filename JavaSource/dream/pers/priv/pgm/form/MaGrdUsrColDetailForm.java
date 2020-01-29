package dream.pers.priv.pgm.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaGrdUsrColDetailDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * 화면별 버튼 상세 
 * @author  kim2107
 * @version $Id: MaGrdUsrColDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maGrdUsrColDetailForm"
 */
public class MaGrdUsrColDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaGrdUsrCommonDTO maGrdUsrCommonDTO = new MaGrdUsrCommonDTO();

	/** 상세 DTO  */
    private MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = new MaGrdUsrColDetailDTO();
    
    private MaGrdUsrDetailDTO maGrdUsrDetailDTO = new MaGrdUsrDetailDTO();

    
	public MaGrdUsrDetailDTO getMaGrdUsrDetailDTO()
    {
        return maGrdUsrDetailDTO;
    }
    public void setMaGrdUsrDetailDTO(MaGrdUsrDetailDTO maGrdUsrDetailDTO)
    {
        this.maGrdUsrDetailDTO = maGrdUsrDetailDTO;
    }
    public MaGrdUsrColDetailDTO getMaGrdUsrColDetailDTO() {
		return maGrdUsrColDetailDTO;
	}
	public void setMaGrdUsrColDetailDTO(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO) {
		this.maGrdUsrColDetailDTO = maGrdUsrColDetailDTO;
	}
	public MaGrdUsrCommonDTO getMaGrdUsrCommonDTO() {
		return maGrdUsrCommonDTO;
	}
	public void setMaGrdUsrCommonDTO(MaGrdUsrCommonDTO maGrdUsrCommonDTO) {
		this.maGrdUsrCommonDTO = maGrdUsrCommonDTO;
	}
}
